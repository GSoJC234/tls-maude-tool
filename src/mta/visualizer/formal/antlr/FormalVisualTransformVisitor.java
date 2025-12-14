package mta.visualizer.formal.antlr;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;

import java.util.*;
import java.util.stream.Collectors;

public class FormalVisualTransformVisitor extends FormalVisualBaseVisitor<Object> {

    private final String title;
    private final List<String> nodeIds;
    private int seqCounter = 0;
    private final Map<String, String> aliasToNodeId = Map.of(
            "N2 . SI", "server",
            "N1 . CI", "client"
    );


    public FormalVisualTransformVisitor(String title, List<String> nodeIds) {
        this.title = title != null ? title : "requirement";
        this.nodeIds = (nodeIds != null && !nodeIds.isEmpty())
                ? nodeIds
                : Arrays.asList("client", "server");
    }

    @Override
    public Object visitFile(FormalVisualParser.FileContext ctx) {
        List<Object> sends = new ArrayList<>();
        for (FormalVisualParser.SendStmtContext s : ctx.sendStmt()) {
            sends.add(visitSendStmt(s));
        }

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("Title", title);
        root.put("NodeNum", nodeIds.size());
        root.put("NodeId", nodeIds);
        root.put("MessageSequence", sends);
        return root;
    }

    @Override
    public Object visitSendStmt(FormalVisualParser.SendStmtContext ctx) {
        this.seqCounter++;

        String sender = aliasToNodeId.get(visitActorToString(ctx.actor(0)));
        String receiver = aliasToNodeId.get(visitActorToString(ctx.actor(1)));
        @SuppressWarnings("unchecked")
        Map<String, Object> content = (Map<String, Object>) visitContent(ctx.content());

        String label = deriveLabel(content);

        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", this.seqCounter);
        obj.put("label", label);
        obj.put("from", sender);
        obj.put("to", receiver);
        obj.put("content", content);
        return obj;
    }

    private String visitActorToString(FormalVisualParser.ActorContext ctx) {
        if (ctx.refExpr() != null) {
            List<String> parts = ctx.refExpr().IDENT()
                    .stream().map(n -> n.getText()).collect(Collectors.toList());
            return String.join(" . ", parts);
        }
        return ctx.IDENT().getText();
    }

    @Override
    public Object visitContent(FormalVisualParser.ContentContext ctx) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (FormalVisualParser.PairContext p : ctx.pair()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Object> kv = (Map.Entry<String, Object>) visit(p);
            String key = kv.getKey();
            Object val = kv.getValue();

            if (map.containsKey(key)) {
                Object prev = map.get(key);
                if (prev instanceof List<?>) {
                    ((List<Object>) prev).add(val);
                } else {
                    List<Object> arr = new ArrayList<>();
                    arr.add(prev);
                    arr.add(val);
                    map.put(key, arr);
                }
            } else {
                map.put(key, val);
            }
        }
        return map;
    }

    @Override
    public Object visitPair(FormalVisualParser.PairContext ctx) {
        if (ctx.internalPair() != null) return visitInternalPair(ctx.internalPair());
        return visitLeafPair(ctx.leafPair());
    }

    @Override
    public Object visitInternalPair(FormalVisualParser.InternalPairContext ctx) {
        String key = ctx.getChild(0).getText(); // EXTENSION or CERTIFICATE
        List<FormalVisualParser.PairContext> innerPairs = ctx.pair();

        Object value;
        if (innerPairs != null && !innerPairs.isEmpty()) {
            // 내부에 pair들이 있으면 맵으로 구성
            Map<String, Object> inner = new LinkedHashMap<>();
            for (FormalVisualParser.PairContext p : innerPairs) {
                @SuppressWarnings("unchecked")
                Map.Entry<String, Object> kv = (Map.Entry<String, Object>) visit(p);
                String ik = kv.getKey();
                Object iv = kv.getValue();
                if (inner.containsKey(ik)) {
                    Object prev = inner.get(ik);
                    if (prev instanceof List) {
                        ((List<Object>) prev).add(iv);
                    } else {
                        List<Object> arr = new ArrayList<>();
                        arr.add(prev);
                        arr.add(iv);
                        inner.put(ik, arr);
                    }
                } else {
                    inner.put(ik, iv);
                }
            }
            value = inner;
        } else {
            // certificate(nil) 같은 경우: 원문 값(공백 보존) 추출
            String raw = extractRawInside(ctx.LPAREN().getSymbol(), ctx.RPAREN().getSymbol(), true);
            // ↓↓↓ certificate 원시값에도 동일 규칙(여러 {..} / func(..) / 토큰 다수 → 리스트) 적용하려면 주석 해제
            // value = normalizeValueExtended(raw);
            // ↑↑↑ 필요 없으면 한 문자열로만 두고 싶다면 아래 한 줄 사용
            value = raw;
        }
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    @Override
    public Object visitLeafPair(FormalVisualParser.LeafPairContext ctx) {
        String key = ctx.leafKey().getText();
        if (ctx.LPAREN() == null || ctx.RPAREN() == null || ctx.anyText() == null) {
            return new AbstractMap.SimpleEntry<>(key, "");
        }
        String raw = extractRawInside(ctx.LPAREN().getSymbol(), ctx.RPAREN().getSymbol(), true);

        Object normalized = normalizeValueExtended(raw);
        return new AbstractMap.SimpleEntry<>(key, normalized);
    }

    /**
     * 여는 괄호 '(' 토큰과 닫는 괄호 ')' 토큰 사이의 원문을 그대로 잘라온다.
     * WS가 skip되어도, 원문 슬라이스는 입력 스트림에서 직접 가져오기 때문에 공백/개행이 보존된다.
     */
    private String extractRawInside(Token lpar, Token rpar, boolean trim) {
        int startIdx = lpar.getStopIndex() + 1;  // '(' 바로 뒤
        int stopIdx = rpar.getStartIndex() - 1;  // ')' 바로 앞
        String raw = lpar.getInputStream().getText(Interval.of(startIdx, stopIdx));
        return trim ? raw.trim() : raw;
    }

    private String deriveLabel(Map<String, Object> content) {
        if (content.containsKey("handshakeType")) {
            String raw = valueToString(content.get("handshakeType"));
            return normalizeLabelFromHandshake(raw);
        }
        if ("alert".equalsIgnoreCase(valueToString(content.get("contentType")))) {
            return "Alert";
        }
        if ("change-cipher-spec".equalsIgnoreCase(valueToString(content.get("contentType")))) {
            return "ChangeCipherSpec";
        }
        return "Message";
    }

    private String valueToString(Object v) {
        if (v == null) return "";
        if (v instanceof List) {
            return ((List<?>) v).stream().map(String::valueOf).collect(Collectors.joining(" "));
        }
        return String.valueOf(v);
    }

    private String normalizeLabelFromHandshake(String s) {
        String base = s.trim();
        base = base.replaceAll("-(v\\d+)$", "");
        String[] parts = base.split("-");
        if (parts.length == 0) return capitalize(base);
        String joined = Arrays.stream(parts).map(this::capitalize).collect(Collectors.joining(""));
        if (joined.equalsIgnoreCase("EncryptedExtension")) return "EncryptedExtensions";
        return joined;
    }

    private String capitalize(String w) {
        if (w.isEmpty()) return w;
        return Character.toUpperCase(w.charAt(0)) + w.substring(1).toLowerCase();
    }

    // ===================== 확장: value 토크나이저 =====================

    /**
     * leaf 괄호 안 문자열을 "value" 단위로 tokenize:
     *  - { ... } (중첩 균형)  → 1 value
     *  - IDENT( ... ) (중첩 균형, 함수호출) → 1 value
     *  - "string literal" → 1 value
     *  - @@PLACEHOLDER@@  → 1 value
     *  - 그 외 토큰(공백/쉼표로 구분) → 각 1 value
     * 결과가 1개면 String, 2개 이상이면 List<String> 반환
     */
    private Object normalizeValueExtended(String raw) {
        List<String> values = tokenizeValues(raw);

        if (values.isEmpty()) return "";
        if (values.size() == 1) return values.get(0);
        return values;
    }

    /** raw를 top-level value 단위로 분해 */
    private List<String> tokenizeValues(String raw) {
        List<String> out = new ArrayList<>();
        String s = raw.trim();
        int i = 0, n = s.length();

        while (i < n) {
            // skip whitespace & commas
            while (i < n && isSpaceOrComma(s.charAt(i))) i++;
            if (i >= n) break;

            char c = s.charAt(i);

            if (c == '{') {
                int j = matchBalanced(s, i, '{', '}');
                out.add(s.substring(i, j + 1).trim());
                i = j + 1;
                continue;
            }

            if (c == '"') {
                int j = matchStringLiteral(s, i);
                out.add(s.substring(i, j + 1)); // 그대로
                i = j + 1;
                continue;
            }

            if (c == '@' && i + 1 < n && s.charAt(i + 1) == '@') {
                int j = i + 2;
                while (j + 1 < n && !(s.charAt(j) == '@' && s.charAt(j + 1) == '@')) j++;
                if (j + 1 < n) {
                    out.add(s.substring(i, j + 2));
                    i = j + 2;
                } else {
                    out.add(s.substring(i)); // 끝까지
                    i = n;
                }
                continue;
            }

            // 함수호출: IDENT '(' anyBalanced ')'
            if (isIdentStart(c)) {
                int idEnd = i + 1;
                while (idEnd < n && isIdentPart(s.charAt(idEnd))) idEnd++;

                int k = idEnd;
                // 중간 공백 허용
                while (k < n && Character.isWhitespace(s.charAt(k))) k++;

                if (k < n && s.charAt(k) == '(') {
                    int j = matchBalanced(s, k, '(', ')');
                    out.add(s.substring(i, j + 1).trim()); // IDENT + (...) 전체
                    i = j + 1;
                    continue;
                }
                // 함수가 아니면 일반 토큰으로 계속 처리
            }

            // 일반 토큰: 공백/쉼표/괄호/brace 전까지
            int j = i;
            while (j < n && !isSpaceOrComma(s.charAt(j)) && "{}()".indexOf(s.charAt(j)) == -1) j++;
            if (j > i) {
                out.add(s.substring(i, j).trim());
                i = j;
                continue;
            }

            // 혹시 남은게 괄호 시작이면(leaf 내부에 괄호그룹만 있을 수도) → 그 자체를 값으로 본다
            if (s.charAt(i) == '(') {
                int j2 = matchBalanced(s, i, '(', ')');
                out.add(s.substring(i, j2 + 1).trim());
                i = j2 + 1;
                continue;
            }

            // 그 외 안전 탈출
            i++;
        }

        // 빈 문자열 제거
        out.removeIf(String::isEmpty);
        return out;
    }

    private boolean isSpaceOrComma(char c) {
        return Character.isWhitespace(c) || c == ',';
    }

    private boolean isIdentStart(char c) {
        return Character.isLetter(c) || c == '_';
    }

    private boolean isIdentPart(char c) {
        return Character.isLetterOrDigit(c) || c == '_' || c == '-';
    }

    /** 문자열 리터럴 닫힘(이스케이프 고려: \" \\ ) */
    private int matchStringLiteral(String s, int i) {
        int n = s.length();
        int j = i + 1;
        while (j < n) {
            char c = s.charAt(j);
            if (c == '\\') { // escape
                j += 2;
                continue;
            }
            if (c == '"') return j;
            j++;
        }
        return n - 1; // 비정상: 끝까지
    }

    /** 괄호/중괄호 균형 매칭 */
    private int matchBalanced(String s, int i, char open, char close) {
        int n = s.length();
        int depth = 0;
        for (int j = i; j < n; j++) {
            char c = s.charAt(j);
            if (c == open) depth++;
            else if (c == close) {
                depth--;
                if (depth == 0) return j;
            } else if (c == '"') {
                // 괄호 내부의 문자열은 통째로 스킵
                j = matchStringLiteral(s, j);
            }
        }
        return n - 1; // 비정상: 끝까지
    }

    // =================== (이전 간단 분해 로직이 필요 없으면 제거해도 됨) ===================

    /** 이전 간단 버전: {…} → 리스트, ","/공백 나열 분해 (현재는 사용 안 함) */
    private Object normalizeValue(String raw) {
        String s = raw.trim();

        if (s.startsWith("{") && s.endsWith("}")) {
            String inner = s.substring(1, s.length() - 1);
            return splitList(inner);
        }
        if (!s.contains("(") && !s.contains(")") && !s.contains("{") && !s.contains("}")) {
            if (s.contains(",")) return splitList(s);
            List<String> parts = Arrays.stream(s.split("\\s+"))
                    .map(String::trim).filter(t -> !t.isEmpty())
                    .collect(Collectors.toList());
            if (parts.size() > 1) return parts;
        }
        return s;
    }

    private List<String> splitList(String inner) {
        String[] tokens = (inner.contains(",")) ? inner.split(",") : inner.split("\\s+");
        List<String> out = Arrays.stream(tokens)
                .map(String::trim)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
        return out;
    }
}