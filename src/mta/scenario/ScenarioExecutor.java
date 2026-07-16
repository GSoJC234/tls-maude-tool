package mta.scenario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;

public class ScenarioExecutor {

    private static final Logger LOGGER = LogManager.getLogger(ScenarioExecutor.class);

    private final String scenario;
    private final String tlsAttackerConfigPath;
    private final TargetExecutionMetadata targetExecutionMetadata;

    public ScenarioExecutor(String scenario, String tlsAttackerConfigPath) {
        this(scenario, tlsAttackerConfigPath, TargetExecutionMetadata.empty());
    }

    public ScenarioExecutor(String scenario,
                            String tlsAttackerConfigPath,
                            TargetExecutionMetadata targetExecutionMetadata) {
        this.scenario = scenario;
        this.tlsAttackerConfigPath = tlsAttackerConfigPath;
        this.targetExecutionMetadata = targetExecutionMetadata == null
                ? TargetExecutionMetadata.empty()
                : targetExecutionMetadata;
    }

    public void execute(){
         // Java source code
        String classTemplate = "import mta.maude.constant.*;\n" +
                               "import mta.protocol.*;\n" +
                               "import mta.scenario.ScenarioExecutionContextHolder;\n" +
                               "import mta.scenario.session.TLSSession;\n"+
                               "import java.io.File;\n"+
                               "import java.io.PrintStream;\n"+
                               "import java.io.FileNotFoundException;\n"+
                               "public class ScenarioTest {\n" +
                               "  public static void main(String[] args) {\n" +
                               "    TLSSession session = null;\n" +
                               "    try {\n" +
                               "      session = new TLSSession(\"" + javaString(this.tlsAttackerConfigPath) + "\", ScenarioExecutionContextHolder.currentOrEmpty());\n" +
                                      this.scenario + "\n" +
                               "      session.execute();\n" +
                               "    } finally {\n" +
                               "      if (session != null) {\n" +
                               "        session.exit();\n" +
                               "      }\n" +
                               "    }\n" +
                               "  }\n" +
                               "}\n";

        LOGGER.debug("classTemplate = {}", classTemplate);

        // JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Java Compiler is not available. Use a JDK instead of a JRE.");
        }
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject javaFile = new InMemoryJavaFileObject("ScenarioTest", classTemplate);
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
        InMemoryClassFileManager fileManager = new InMemoryClassFileManager(standardFileManager);

        Iterable<String> options = Arrays.asList(
                "-proc:none",
                "-classpath", System.getProperty("java.class.path")
        );

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);

        boolean success = false;
        success = task.call();

        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
            System.err.println(diagnostic.getMessage(null));
        }

        if (!success) {
            throw new RuntimeException("ScenarioTest compilation failed");
        }

        ClassLoader classLoader = fileManager.getClassLoader(null);
        Class<?> compiledClass = null;
        try {
            compiledClass = classLoader.loadClass("ScenarioTest");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Method mainMethod = null;
        try {
            mainMethod = compiledClass.getMethod("main", String[].class);
            ScenarioExecutionContextHolder.set(targetExecutionMetadata);
            mainMethod.invoke(null, (Object) new String[]{});
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ScenarioExecutionContextHolder.clear();
        }
    }

    private static String javaString(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    static class InMemoryClassFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
        private final Map<String, ByteArrayJavaFileObject> classFileObjects = new HashMap<>();

        protected InMemoryClassFileManager(StandardJavaFileManager fileManager) {
            super(fileManager);
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
            ByteArrayJavaFileObject fileObject = new ByteArrayJavaFileObject(className, kind);
            classFileObjects.put(className, fileObject);
            return fileObject;
        }

        @Override
        public ClassLoader getClassLoader(Location location) {
            return new InMemoryClassLoader(classFileObjects);
        }
    }

    static class InMemoryJavaFileObject extends SimpleJavaFileObject {
        private final String sourceCode;

        public InMemoryJavaFileObject(String className, String sourceCode) {
            super(URI.create("string:///" + className + Kind.SOURCE.extension), Kind.SOURCE);
            this.sourceCode = sourceCode;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return sourceCode;
        }
    }


    static class ByteArrayJavaFileObject extends SimpleJavaFileObject {
        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        protected ByteArrayJavaFileObject(String className, Kind kind) {
            super(java.net.URI.create("bytes:///" + className.replace('.', '/') + kind.extension), kind);
        }

        @Override
        public OutputStream openOutputStream() {
            return outputStream;
        }

        public byte[] getBytes() {
            return outputStream.toByteArray();
        }
    }

    static class InMemoryClassLoader extends ClassLoader {
        private final Map<String, ByteArrayJavaFileObject> classFileObjects;

        public InMemoryClassLoader(Map<String, ByteArrayJavaFileObject> classFileObjects) {
            this.classFileObjects = classFileObjects;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            ByteArrayJavaFileObject fileObject = classFileObjects.get(name);
            if (fileObject == null) {
                return super.findClass(name);
            }
            byte[] bytes = fileObject.getBytes();
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
