package mta.maude.result;

import mta.maude.result.antlr.GeneratedScenarioListLexer;
import mta.maude.result.antlr.GeneratedScenarioListParser;
import mta.scenario.antlr.ScenarioLexer;
import mta.scenario.antlr.ScenarioParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratedScenarioResultExtractor {

    public static final String TESTER_SCENARIO_EXTENSION = ".tester_scenario";

    private static final Pattern RESULT_LINE =
            Pattern.compile("^result\\s+([^:]+):\\s*(.*)$");
    private static final Pattern RESIDUAL_GENERATOR =
            Pattern.compile("(?<![A-Za-z0-9_-])gen\\s*\\(");
    private static final BaseErrorListener THROWING_ERROR_LISTENER = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            throw new IllegalArgumentException("Failed to parse generated tester scenario at line "
                    + line + ":" + charPositionInLine + " " + msg, e);
        }
    };

    public List<Path> extractTesterScenarioFiles(Path maudeLogPath, Path outputDirectory) {
        try {
            return writeTesterScenarioFiles(Files.readString(maudeLogPath), outputDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Maude log: " + maudeLogPath, e);
        }
    }

    public List<Path> writeTesterScenarioFiles(String maudeOutput, Path outputDirectory) {
        List<String> scenarios = extractTesterScenarios(maudeOutput);
        List<Path> paths = new ArrayList<Path>();
        try {
            Files.createDirectories(outputDirectory);
            for (int index = 0; index < scenarios.size(); index++) {
                Path path = outputDirectory.resolve("scenario_" + index + TESTER_SCENARIO_EXTENSION);
                Files.writeString(path, scenarios.get(index) + System.lineSeparator());
                paths.add(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write generated tester scenarios under " + outputDirectory, e);
        }
        return paths;
    }

    public List<String> extractTesterScenarios(String maudeOutput) {
        ResultBlock result = readResultBlock(maudeOutput);
        if (!result.isScenarioResult()) {
            throw new IllegalArgumentException("Expected List{Scen} or NeList{Scen} result, found " + result.sort);
        }
        if (isEmptyList(result.term)) {
            return List.of();
        }

        validateGeneratedScenarioTerm(result.term);
        String inner = stripOuterList(result.term);
        List<String> rawScenarios = splitScenarioList(inner);
        List<String> normalizedScenarios = new ArrayList<String>();
        for (String rawScenario : rawScenarios) {
            String scenario = normalizeScenario(rawScenario);
            validateRunnableScenario(scenario);
            normalizedScenarios.add(scenario);
        }
        return normalizedScenarios;
    }

    private ResultBlock readResultBlock(String output) {
        String[] lines = output.split("\\R", -1);
        for (int index = 0; index < lines.length; index++) {
            Matcher matcher = RESULT_LINE.matcher(lines[index]);
            if (!matcher.matches()) {
                continue;
            }

            String sort = matcher.group(1).trim();
            StringBuilder term = new StringBuilder(matcher.group(2));
            for (int termLine = index + 1; termLine < lines.length; termLine++) {
                String line = lines[termLine];
                if (line.startsWith("Maude>") || line.startsWith("Bye.") || line.startsWith("result ")) {
                    break;
                }
                term.append(System.lineSeparator()).append(line);
            }
            return new ResultBlock(sort, term.toString().trim());
        }
        throw new IllegalArgumentException("Maude output does not contain a result block");
    }

    private boolean isEmptyList(String term) {
        String compact = term.replaceAll("\\s+", "");
        return compact.equals("[]")
                || compact.equals("([]).List{Scen}")
                || compact.equals("nil")
                || compact.isEmpty();
    }

    private void validateGeneratedScenarioTerm(String term) {
        GeneratedScenarioListLexer lexer = new GeneratedScenarioListLexer(CharStreams.fromString(term));
        lexer.removeErrorListeners();
        lexer.addErrorListener(THROWING_ERROR_LISTENER);

        GeneratedScenarioListParser parser = new GeneratedScenarioListParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(THROWING_ERROR_LISTENER);
        parser.resultTerm();
    }

    private String stripOuterList(String term) {
        String trimmed = term.trim();
        if (!trimmed.startsWith("[") || !trimmed.endsWith("]")) {
            throw new IllegalArgumentException("Expected generated scenario list enclosed in '[' and ']': " + trimmed);
        }
        int closingIndex = findMatching(trimmed, 0, '[', ']');
        if (closingIndex != trimmed.length() - 1) {
            throw new IllegalArgumentException("Generated scenario list has trailing text: " + trimmed);
        }
        return trimmed.substring(1, closingIndex);
    }

    private List<String> splitScenarioList(String innerList) {
        String trimmed = innerList.trim();
        if (trimmed.isEmpty()) {
            return List.of();
        }
        if (hasTopLevelSemicolon(trimmed)) {
            return List.of(trimmed);
        }

        List<String> scenarios = new ArrayList<String>();
        int index = 0;
        while (index < trimmed.length()) {
            index = skipWhitespace(trimmed, index);
            if (index >= trimmed.length()) {
                break;
            }

            int end;
            if (trimmed.charAt(index) == '(') {
                end = findMatching(trimmed, index, '(', ')') + 1;
                scenarios.add(trimmed.substring(index + 1, end - 1));
            } else {
                end = findSingleStatementEnd(trimmed, index);
                scenarios.add(trimmed.substring(index, end));
            }
            index = end;
        }
        return scenarios;
    }

    private boolean hasTopLevelSemicolon(String text) {
        int parenDepth = 0;
        int bracketDepth = 0;
        int braceDepth = 0;
        for (int index = 0; index < text.length(); index++) {
            char current = text.charAt(index);
            if (current == '(') {
                parenDepth++;
            } else if (current == ')') {
                parenDepth--;
            } else if (current == '[') {
                bracketDepth++;
            } else if (current == ']') {
                bracketDepth--;
            } else if (current == '{') {
                braceDepth++;
            } else if (current == '}') {
                braceDepth--;
            } else if (current == ';' && parenDepth == 0 && bracketDepth == 0 && braceDepth == 0) {
                return true;
            }
        }
        return false;
    }

    private int findSingleStatementEnd(String text, int start) {
        int parenDepth = 0;
        int bracketDepth = 0;
        int braceDepth = 0;
        boolean sawTopLevelCallClose = false;
        for (int index = start; index < text.length(); index++) {
            char current = text.charAt(index);
            if (current == '(') {
                parenDepth++;
            } else if (current == ')') {
                parenDepth--;
                if (parenDepth == 0 && bracketDepth == 0 && braceDepth == 0) {
                    sawTopLevelCallClose = true;
                }
            } else if (current == '[') {
                bracketDepth++;
            } else if (current == ']') {
                bracketDepth--;
            } else if (current == '{') {
                braceDepth++;
            } else if (current == '}') {
                braceDepth--;
            }

            if (parenDepth < 0 || bracketDepth < 0 || braceDepth < 0) {
                throw new IllegalArgumentException("Unbalanced generated scenario list near: " + text.substring(start));
            }
            if (sawTopLevelCallClose && (index + 1 == text.length() || Character.isWhitespace(text.charAt(index + 1)))) {
                return index + 1;
            }
        }
        throw new IllegalArgumentException("Could not find end of generated scenario statement near: " + text.substring(start));
    }

    private int findMatching(String text, int start, char open, char close) {
        int depth = 0;
        for (int index = start; index < text.length(); index++) {
            char current = text.charAt(index);
            if (current == open) {
                depth++;
            } else if (current == close) {
                depth--;
                if (depth == 0) {
                    return index;
                }
            }
        }
        throw new IllegalArgumentException("Unbalanced '" + open + "' in generated scenario result");
    }

    private int skipWhitespace(String text, int index) {
        int current = index;
        while (current < text.length() && Character.isWhitespace(text.charAt(current))) {
            current++;
        }
        return current;
    }

    private String normalizeScenario(String rawScenario) {
        String scenario = rawScenario.trim();
        if (scenario.isEmpty()) {
            throw new IllegalArgumentException("Generated scenario is empty");
        }
        if (scenario.contains("result ") || scenario.contains("Bye.") || scenario.contains("|->")) {
            throw new IllegalArgumentException("Generated scenario contains non-runnable Maude result text: " + scenario);
        }
        if (RESIDUAL_GENERATOR.matcher(scenario).find()) {
            throw new IllegalArgumentException("Generated scenario still contains unresolved gen(...): " + scenario);
        }
        if (!scenario.endsWith(";")) {
            scenario = scenario + " ;";
        }
        return scenario;
    }

    private void validateRunnableScenario(String scenario) {
        ScenarioLexer lexer = new ScenarioLexer(CharStreams.fromString(scenario));
        lexer.removeErrorListeners();
        lexer.addErrorListener(THROWING_ERROR_LISTENER);

        ScenarioParser parser = new ScenarioParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(THROWING_ERROR_LISTENER);
        parser.program();
    }

    private static class ResultBlock {
        private final String sort;
        private final String term;

        private ResultBlock(String sort, String term) {
            this.sort = sort;
            this.term = term;
        }

        private boolean isScenarioResult() {
            return sort.equals("List{Scen}") || sort.equals("NeList{Scen}");
        }
    }
}
