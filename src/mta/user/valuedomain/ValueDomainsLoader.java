package mta.user.valuedomain;

import mta.user.common.UserTerm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ValueDomainsLoader {

    public ValueDomainsSpec load(Path path) {
        try {
            return parse(Files.readAllLines(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read value domains: " + path, e);
        }
    }

    public ValueDomainsSpec loadFromString(String content) {
        return parse(content.lines().toList());
    }

    private ValueDomainsSpec parse(List<String> lines) {
        ValueDomainsSpec spec = new ValueDomainsSpec();
        String section = "";
        String currentMapping = "";
        String currentInstanceBehavior = "";
        Map<String, UserTerm> currentInstance = null;

        for (int index = 0; index < lines.size(); index++) {
            int lineNo = index + 1;
            String raw = stripComment(lines.get(index));
            if (raw.isBlank()) {
                continue;
            }
            int indent = countIndent(raw);
            String line = raw.strip();
            if (indent == 0) {
                currentInstance = flushInstance(spec, currentInstanceBehavior, currentInstance);
                if ("ValueDomains:".equals(line)) {
                    section = "domains";
                } else if ("TypeMappings:".equals(line)) {
                    section = "mappings";
                } else if ("Instances:".equals(line)) {
                    section = "instances";
                } else {
                    throw parseError(lineNo, "Unknown top-level section: " + line);
                }
                currentMapping = "";
                currentInstanceBehavior = "";
                continue;
            }

            if ("domains".equals(section)) {
                requireIndent(lineNo, indent, 2);
                int colon = line.indexOf(':');
                if (colon < 1) {
                    throw parseError(lineNo, "Expected DomainName: values");
                }
                String name = line.substring(0, colon).strip();
                String value = line.substring(colon + 1).strip();
                if (value.isEmpty()) {
                    throw parseError(lineNo, "Domain values must be inline: " + name);
                }
                spec.putDomain(name, parseValueList(value, lineNo));
                continue;
            }

            if ("mappings".equals(section)) {
                if (indent == 2 && line.endsWith(":")) {
                    currentMapping = normalizeFunctionName(line.substring(0, line.length() - 1).strip());
                    continue;
                }
                requireIndent(lineNo, indent, 4);
                if (currentMapping.isEmpty()) {
                    throw parseError(lineNo, "Type mapping entry before function name");
                }
                int colon = line.indexOf(':');
                if (colon < 1) {
                    throw parseError(lineNo, "Expected value: DomainName mapping");
                }
                spec.putTypeMapping(
                        currentMapping,
                        line.substring(0, colon).strip(),
                        line.substring(colon + 1).strip()
                );
                continue;
            }

            if ("instances".equals(section)) {
                if (indent == 2 && line.endsWith(":")) {
                    currentInstance = flushInstance(spec, currentInstanceBehavior, currentInstance);
                    currentInstanceBehavior = line.substring(0, line.length() - 1).strip();
                    currentInstance = null;
                    continue;
                }
                if (currentInstanceBehavior.isEmpty()) {
                    throw parseError(lineNo, "Instance entry before behavior id");
                }
                if (indent == 4 && line.startsWith("-")) {
                    currentInstance = flushInstance(spec, currentInstanceBehavior, currentInstance);
                    currentInstance = new LinkedHashMap<String, UserTerm>();
                    String rest = line.substring(1).strip();
                    if (!rest.isEmpty()) {
                        addBinding(currentInstance, rest, lineNo);
                    }
                    continue;
                }
                requireIndent(lineNo, indent, 6);
                if (currentInstance == null) {
                    throw parseError(lineNo, "Instance binding before '-' entry");
                }
                addBinding(currentInstance, line, lineNo);
                continue;
            }

            throw parseError(lineNo, "Entry before a top-level section");
        }
        flushInstance(spec, currentInstanceBehavior, currentInstance);
        return spec;
    }

    private Map<String, UserTerm> flushInstance(ValueDomainsSpec spec,
                                                String behaviorId,
                                                Map<String, UserTerm> currentInstance) {
        if (currentInstance != null) {
            spec.addInstance(behaviorId, currentInstance);
        }
        return null;
    }

    private void addBinding(Map<String, UserTerm> bindings, String line, int lineNo) {
        int colon = line.indexOf(':');
        if (colon < 1) {
            throw parseError(lineNo, "Expected parameter: value binding");
        }
        String name = normalizeParameterName(line.substring(0, colon).strip());
        if (bindings.containsKey(name)) {
            throw parseError(lineNo, "Duplicate binding: " + name);
        }
        bindings.put(name, parseTerm(line.substring(colon + 1).strip(), lineNo));
    }

    private List<UserTerm> parseValueList(String value, int lineNo) {
        String text = value.strip();
        if (!text.startsWith("[") || !text.endsWith("]")) {
            throw parseError(lineNo, "Domain values must use inline list syntax: " + value);
        }
        String body = text.substring(1, text.length() - 1).strip();
        if (body.isEmpty()) {
            return List.of();
        }
        List<UserTerm> values = new ArrayList<UserTerm>();
        for (String item : splitTopLevel(body, ',')) {
            values.add(parseTerm(item.strip(), lineNo));
        }
        return values;
    }

    private UserTerm parseTerm(String text, int lineNo) {
        if (text.isEmpty()) {
            throw parseError(lineNo, "Empty term");
        }
        if (text.startsWith("maude(\"") && text.endsWith("\")")) {
            return new UserTerm.RawMaude(text.substring(7, text.length() - 2));
        }
        if (text.startsWith("\"") && text.endsWith("\"") && text.length() >= 2) {
            return new UserTerm.StringLiteral(text.substring(1, text.length() - 1));
        }
        if (text.startsWith("$")) {
            return new UserTerm.Parameter(normalizeParameterName(text));
        }
        int paren = text.indexOf('(');
        if (paren > 0 && text.endsWith(")") && findMatching(text, paren) == text.length() - 1) {
            String name = text.substring(0, paren).strip();
            String body = text.substring(paren + 1, text.length() - 1).strip();
            List<UserTerm> args = new ArrayList<UserTerm>();
            if (!body.isEmpty()) {
                for (String item : splitTopLevel(body, ',')) {
                    args.add(parseTerm(item.strip(), lineNo));
                }
            }
            return new UserTerm.Call(name, args);
        }
        return new UserTerm.Atom(text);
    }

    private List<String> splitTopLevel(String text, char delimiter) {
        List<String> parts = new ArrayList<String>();
        int depth = 0;
        int start = 0;
        boolean quote = false;
        for (int index = 0; index < text.length(); index++) {
            char ch = text.charAt(index);
            if (ch == '"') {
                quote = !quote;
            } else if (!quote && (ch == '(' || ch == '[' || ch == '{')) {
                depth++;
            } else if (!quote && (ch == ')' || ch == ']' || ch == '}')) {
                depth--;
            } else if (!quote && depth == 0 && ch == delimiter) {
                parts.add(text.substring(start, index));
                start = index + 1;
            }
        }
        parts.add(text.substring(start));
        return parts;
    }

    private int findMatching(String text, int openIndex) {
        int depth = 0;
        for (int index = openIndex; index < text.length(); index++) {
            char ch = text.charAt(index);
            if (ch == '(') {
                depth++;
            } else if (ch == ')') {
                depth--;
                if (depth == 0) {
                    return index;
                }
            }
        }
        return -1;
    }

    private String stripComment(String line) {
        boolean quote = false;
        for (int index = 0; index < line.length(); index++) {
            char ch = line.charAt(index);
            if (ch == '"') {
                quote = !quote;
            } else if (!quote
                    && ch == '#'
                    && (index == 0 || Character.isWhitespace(line.charAt(index - 1)))
                    && !line.startsWith("#getType", index)) {
                return line.substring(0, index);
            }
        }
        return line;
    }

    private int countIndent(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == ' ') {
            count++;
        }
        return count;
    }

    private void requireIndent(int lineNo, int actual, int expected) {
        if (actual != expected) {
            throw parseError(lineNo, "Expected indent " + expected + " but got " + actual);
        }
    }

    private String normalizeParameterName(String name) {
        return name.startsWith("$") ? name.substring(1) : name;
    }

    private String normalizeFunctionName(String name) {
        return name.startsWith("#") ? name.substring(1) : name;
    }

    private IllegalArgumentException parseError(int lineNo, String message) {
        return new IllegalArgumentException("valuedomains.dsl parse error at line "
                + lineNo
                + ": "
                + message);
    }
}
