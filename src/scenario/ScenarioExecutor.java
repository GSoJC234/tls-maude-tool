package scenario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenarioExecutor {

    private static final Logger LOGGER = LogManager.getLogger();

    private String currentDir;
    private String scenario;
    private List<String> currentIPs;
    private List<Integer> currentPorts;
    private List<String> serverIPs;
    private List<Integer> serverPorts;

    public ScenarioExecutor(String scenario) {
        this.scenario = scenario;
        this.currentDir = Paths.get("").toAbsolutePath().toString();

    }

    public void setCurrentIPs(List<String> IPs){
        this.currentIPs = IPs;
    }

    public void setCurrentPorts(List<Integer> ports){
        this.currentPorts = ports;
    }

    public void setServerIPs(List<String> IPs){
        this.serverIPs = IPs;
    }

    public void setServerPorts(List<Integer> ports){
        this.serverPorts = ports;
    }

    public void execute(){
        // change connect or accept to include target IP address and port.
        scenario = processScenario(scenario, "accept", currentIPs, currentPorts);
        scenario = processScenario(scenario, "connect", serverIPs, serverPorts);

        // Java source code
        String classTemplate = "import maude.*;\n" +
                               "import protocol.*;\n" +
                               "import scenario.TLSAttacker;\n" +
                               "import scenario.session.TLSSession;\n"+

                               "public class ScenarioTest {\n" +
                               "  public static void main(String[] args) {\n" +
                               "    TLSAttacker protocol = new TLSAttacker(\"" + this.currentDir + "/resources/default_config3.xml\");\n" +
                               "    TLSSession session = new TLSSession();\n" +
                               "    session.setExecutor(protocol);\n" +
                               this.scenario + "\n" +
                               "    protocol.execute();\n" +
                               "  }\n" +
                               "}\n";

        LOGGER.info("classTemplate = {}", classTemplate);

        // JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Java Compiler is not available. Use a JDK instead of a JRE.");
        }
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject javaFile = new InMemoryJavaFileObject("ScenarioTest", classTemplate);
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
        InMemoryClassFileManager fileManager = new InMemoryClassFileManager(standardFileManager);

        String jarDirPath = this.currentDir + "/resources/jar";
        Iterable<String> options = Arrays.asList("-classpath", getClasspathWithJars(jarDirPath));

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);

        boolean success = task.call();

        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
            System.err.println(diagnostic.getMessage(null));
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
            mainMethod.invoke(null, (Object) new String[]{});
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private static String processScenario(String scenario, String keyword, List<String> ips, List<Integer> ports) {
        // 정규식
        String regex = keyword + "\\(\"(N\\d+ \\. \\w+)\"\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(scenario);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String matchedGroup = matcher.group(1);

            // ID 추출
            String idStr = matchedGroup.split(" ")[0].substring(1); // "N1" -> "1"
            int id = Integer.parseInt(idStr);

            // 리스트에서 값 가져오기
            String ip = ips.get(id - 1); // 리스트는 0부터 시작하므로 -1
            int port = ports.get(id - 1);

            // 새 문자열 생성
            String replacement = keyword + "(\"" + matchedGroup + "\", \"" + ip + "\", " + port + ")";
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private static String getClasspathWithJars(String jarDirPath) {
        File jarDir = new File(jarDirPath);
        if (!jarDir.isDirectory()) {
            throw new IllegalArgumentException("Invalid JAR directory path: " + jarDirPath);
        }

        StringBuilder classpath = new StringBuilder(".");
        for (File jarFile : jarDir.listFiles((dir, name) -> name.endsWith(".jar"))) {
            classpath.append(File.pathSeparator).append(jarFile.getAbsolutePath());
        }
        return classpath.toString();
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
