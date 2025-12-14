package mta.visualizer.real.antlr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RealVisualTransformVisitor extends RealVisualBaseVisitor<Object> {

    private final String title;
    private final List<String> nodeIds;
    private int seqCounter = 0;
    private final Map<String, String> aliasToNodeId = Map.of(
            "N2 . SI", "server",
            "N1 . CI", "client"
    );

    private final Map<String, String> expectedValue = null;
    private final Map<String, String> errorValue = null;

    public RealVisualTransformVisitor(String title, List<String> nodeIds) {
        this.title = title;
        this.nodeIds = nodeIds;
    }

    public Map<String, String> getExpectedValue() {
        return expectedValue;
    }

    public Map<String, String> getErrorValue() {
        return errorValue;
    }

    @Override
    public Object visitFile(RealVisualParser.FileContext ctx) {
        List<Object> messageSequences = new ArrayList<>();
        for (RealVisualParser.StatementsContext s :  ctx.statements()){
            messageSequences.add(visitStatements(s));
        }

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("Title", title);
        root.put("NodeNum", nodeIds.size());
        root.put("NodeId", nodeIds);
        root.put("MessageSequence", messageSequences);
        return root;
    }



    @Override
    public Object visitStatements(RealVisualParser.StatementsContext ctx) {
        if (ctx.sendStmt() != null){
            return visitSendStmt(ctx.sendStmt());
        } else if (ctx.recvStmt() != null){
            Map<String, Object> recvStmt = (Map<String, Object>) visitRecvStmt(ctx.recvStmt());
            for (RealVisualParser.AssertionContext context : ctx.assertion()){
                visitAssertion(recvStmt, context);
            }
            return recvStmt;
        }
        return null;
    }

    public void visitAssertion(Map<String, Object> recvMessage, RealVisualParser.AssertionContext ctx) {
        String key = ctx.key().getText();
        String expected = ctx.value(0).getText();
        if (ctx.value(1) == null) {
            return; // Only shows first error assertion
        }
        String error = ctx.value(1).getText();

        Map<String, Object> content = (Map<String, Object>) recvMessage.get("content");
        if(content.containsKey(key)){
            content.remove(key);
            content.put("expected_value(" + key + ")", "0x" + expected);
            content.put("error_value(" + key + ")", "0x" + error);
        } else if (((Map<String, Object>) content.get("extension")).containsKey(key)) {
            Map<String, Object> extensionContent = (Map<String, Object>) content.get("extension");
            extensionContent.remove(key);
            extensionContent.put("expected_value(" + key + ")", "0x" + expected);
            extensionContent.put("error_value(" + key + ")", "0x" + error);
        }

    }

    @Override public Object visitSendStmt(RealVisualParser.SendStmtContext ctx) {
        this.seqCounter++;

        String receiver = aliasToNodeId.get(visitAliasToString(ctx.alias()));
        String sender = aliasToNodeId.get(visitAliasToString(ctx.alias()).equals("N2 . SI") ? "N1 . CI" : "N2 . SI");

        Map<String, Object> content = (Map<String, Object>) visitLayers(ctx.layers());

        String label = deriveLabel(content);
        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", this.seqCounter);
        obj.put("label", label);
        obj.put("from", sender);
        obj.put("to", receiver);
        obj.put("content", content);

        return obj;
    }

    @Override public Object visitRecvStmt(RealVisualParser.RecvStmtContext ctx) {
        this.seqCounter++;

        String sender = aliasToNodeId.get(visitAliasToString(ctx.alias()));
        String receiver = aliasToNodeId.get(visitAliasToString(ctx.alias()).equals("N2 . SI") ? "N1 . CI" : "N2 . SI");

        Map<String, Object> content = (Map<String, Object>) visitLayers(ctx.layers());

        String label = deriveLabel(content);
        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", this.seqCounter);
        obj.put("label", label);
        obj.put("from", sender);
        obj.put("to", receiver);
        obj.put("content", content);

        return obj;
    }




    private String visitAliasToString(RealVisualParser.AliasContext ctx) {
        List<String> parts = ctx.IDENT()
                .stream().map(n -> n.getText()).collect(Collectors.toList());
        return String.join(" . ", parts);
    }

    private String deriveLabel(Map<String, Object> content) {
        Object contentValue = content.get("contentType");
        // handshake type or encrypted handshake (a.k.a application type)
        if (contentValue.equals("0x16") || contentValue.equals("0x17")){
            Object hexValue = content.get("handshakeType");
            if (hexValue != null) {
                switch (hexValue.toString()) {
                    case "0x00": return "HelloRequest";
                    case "0x01": return "ClientHello";
                    case "0x02": return "ServerHello";
                    case "0x08": return "EncryptedExtension";
                    case "0x0B": return "Certificate";
                    case "0x0C": return "ServerKeyExchange";
                    case "0x0D": return "CertificateRequest";
                    case "0x0E": return "ServerHelloDone";
                    case "0x0F": return "CertificateVerify";
                    case "0x10": return "ClientKeyExchange";
                    case "0x14": return "Finished";
                }
            }
        } else if (contentValue.equals("0x15")) {
            return "Alert";
        } else if (contentValue.equals("0x14")) {
            return "ChangeCipherSpec";
        }
        return "Unknown";
    }

    @Override
    public Object visitLayers(RealVisualParser.LayersContext ctx) {
        Map<String, Object> layers = new LinkedHashMap<>();
        for (RealVisualParser.LayerContext layer : ctx.layer()){
            Object layerMap = visitLayer(layer);
            layers.putAll((Map<String, Object>) layerMap);
        }
        return layers;
    }

    @Override
    public Object visitMessage(RealVisualParser.MessageContext ctx) {
        Map<String, Object> contents = new LinkedHashMap<>();
        for (RealVisualParser.ContentContext cctx : ctx.content()){
            Map<String, Object> content = (Map<String, Object>) visitContent(cctx);
            contents.putAll((Map<String, Object>) content);
        }
        return contents;
    }

    @Override
    public Object visitContent(RealVisualParser.ContentContext ctx) {
        Map<String, Object> content = new LinkedHashMap<>();
        if (ctx.EXTENSION() != null){
            Map<String, Object> extension = new LinkedHashMap<>();
            for (RealVisualParser.ContentContext cctx : ctx.content()){
                Map<String, Object> extensionContent = (Map<String, Object>) visitContent(cctx);
                extension.putAll((Map<String, Object>) extensionContent);
            }
            if(!extension.isEmpty()) {
                content.put("extension", extension);
            }
        } else {
            if(!ctx.value().getText().contains("null")) {
                String value = "0x" + ctx.value().getText();
                content.put(ctx.key().getText(), value);
            }
        }
        return content;
    }
}
