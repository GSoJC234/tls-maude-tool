package mta.scenario.antlr;

import mta.config.Node;
import mta.maude.constant.*;

import java.util.List;
import java.util.stream.Collectors;

public class ScenarioTransformVisitor extends ScenarioBaseVisitor<String> {

    private List<Node> nodeList = null;
    public void setNodes(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String visitProgram(ScenarioParser.ProgramContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (ScenarioParser.StatementContext stmt : ctx.statement()) {
            sb.append(visit(stmt)).append(";").append("\n");
        }
        return sb.toString();
    }

    @Override
    public String visitStatement(ScenarioParser.StatementContext ctx) {
        if (ctx.assignment() != null) {
            return visit(ctx.assignment());
        }
        if (ctx.functionCall() != null) {
            return handleFunctionCall(ctx.functionCall());
        }
        return "";
    }

    private String handleFunctionCall(ScenarioParser.FunctionCallContext ctx) {
        if (ctx.certificate_entry_status_request_call() != null) {
            return visit(ctx);
        }
        final String name = ctx.function_name().getText();
        if ("accept".equals(name) || "connect".equals(name)) {
            return buildSessionCall(name, visit(ctx.argumentList()), nodeList.get(0).getIp(), nodeList.get(0).getPort());
        } else {
            return visit(ctx);
        }
    }

    private String buildSessionCall(String funcName, String args, String ip, int port) {
        return String.format("session.%s(%s, \"%s\", %d)", funcName, args, ip, port);
    }

    @Override
    public String visitAssignment(ScenarioParser.AssignmentContext ctx) {
        String var = visit(ctx.variable());
        String expr = visit(ctx.expr());
        return "Variable " + var + " = " + expr;
    }

    @Override
    public String visitFunctionCall(ScenarioParser.FunctionCallContext ctx) {
        if (ctx.certificate_entry_status_request_call() != null) {
            return visit(ctx.certificate_entry_status_request_call());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("session").append(".").append(visit(ctx.function_name())).append("(");
        if(ctx.argumentList() != null) {
            sb.append(visit(ctx.argumentList()));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override public String visitArgumentList(ScenarioParser.ArgumentListContext ctx) {
        return ctx.argument().stream()
                .map(this::visit)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String visitOid_filter_constant(ScenarioParser.Oid_filter_constantContext ctx) {
        String values = ctx.oid_filter_value().stream()
                .map(this::visit)
                .collect(Collectors.joining(", "));
        return "new OidFilterSpec(" + visit(ctx.certificate_extension_oid()) + ", " + values + ")";
    }

    @Override
    public String visitOcsp_response_constant(ScenarioParser.Ocsp_response_constantContext ctx) {
        return "OcspResponseSpec."
                + OcspResponseSpec.fromFixtureId(ctx.OCSP_FIXTURE_ID().getText()).name();
    }

    @Override
    public String visitCertificate_entry_status_request_call(
            ScenarioParser.Certificate_entry_status_request_callContext ctx) {
        return "session.addCertificateEntryStatusRequestExtension("
                + tidLiteral(ctx.TID().getText()) + ", "
                + visit(ctx.variable()) + ", "
                + ctx.NAT().getText() + ", "
                + visit(ctx.maude_constant_list()) + ")";
    }

    @Override
    public String visitCertificate_extension_oid(ScenarioParser.Certificate_extension_oidContext ctx) {
        return switch (ctx.getText()) {
            case "key-usage" -> "CertificateExtensionOid.KEY_USAGE";
            case "extended-key-usage" -> "CertificateExtensionOid.EXTENDED_KEY_USAGE";
            default -> throw new IllegalArgumentException("Unknown certificate extension OID: " + ctx.getText());
        };
    }

    @Override
    public String visitOid_filter_value(ScenarioParser.Oid_filter_valueContext ctx) {
        return ctx.key_usage_value() != null
                ? visit(ctx.key_usage_value())
                : visit(ctx.extended_key_usage_value());
    }

    @Override
    public String visitKey_usage_value(ScenarioParser.Key_usage_valueContext ctx) {
        return "KeyUsageValue." + ctx.getText().replace('-', '_').toUpperCase(java.util.Locale.ROOT);
    }

    @Override
    public String visitExtended_key_usage_value(ScenarioParser.Extended_key_usage_valueContext ctx) {
        return "ExtendedKeyUsageValue." + ctx.getText().replace('-', '_').toUpperCase(java.util.Locale.ROOT);
    }

    @Override public String visitArgument(ScenarioParser.ArgumentContext ctx) {
        if(ctx.maude_constant_list() != null) {
            return visit(ctx.maude_constant_list());
        } else if (ctx.functionCall() != null) {
            return visit(ctx.functionCall());
        } else if (ctx.variable() != null) {
            return visit(ctx.variable());
        } else if (ctx.value() != null) {
            return visit(ctx.value());
        } else if (ctx.TID() != null) {
            return tidLiteral(ctx.TID().getText());
        } else if (ctx.nonce() != null){
            return visit(ctx.nonce());
        } else if (ctx.other_constant() != null){
            return visit(ctx.other_constant());
        } else if (ctx.session_id_constant() != null) {
            return visit(ctx.session_id_constant());
        } else if (ctx.session_id_size_constant() != null) {
            return visit(ctx.session_id_size_constant());
        }
        else {
            return "";
        }
    }

    private String tidLiteral(String rawTid) {
        return javaStringLiteral(rawTid.trim().replaceAll("\\s*\\.\\s*", " . "));
    }

    private String javaStringLiteral(String value) {
        return "\"" + value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n") + "\"";
    }

    @Override public String visitNonce(ScenarioParser.NonceContext ctx) {
        if(ctx.realNonce != null){
            return Random.title() + "." + Random.NONCE.name();
        } else if (ctx.noNonce != null){
            return Random.title() + "." + Random.EMPTY.name();
        } else if (ctx.hrrNonce != null){
            return Random.title() + "." + Random.HRR_NONCE.name();
        } else {
            return "";
        }
    }

    @Override
    public String visitVariable(ScenarioParser.VariableContext ctx) {
        return "v" + ctx.NAT().getText();
    }

    @Override
    public String visitValue(ScenarioParser.ValueContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitExpr(ScenarioParser.ExprContext ctx) {
        return visit(ctx.functionCall());
    }

    @Override
    public String visitFunction_name(ScenarioParser.Function_nameContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitMaude_constant_list(ScenarioParser.Maude_constant_listContext ctx) {
        if (ctx.application_data_payload() != null) {
            return "session.generateRandom(" + visit(ctx.application_data_payload().nonce()) + ")";
        }
        if (ctx.early_data_payload() != null) {
            return "session.earlyDataPayload()";
        }
        if (ctx.maude_constant().size() == 1) {
            ScenarioParser.Maude_constantContext constant = ctx.maude_constant(0);
            if (constant.session_id_constant() != null
                    || constant.session_id_size_constant() != null) {
                return visit(constant);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("session");
        sb.append(".");

        sb.append("constant");
        sb.append("(");
        if (ctx.maude_constant().size() > 1){
            sb.append(ctx.maude_constant().stream()
                    .map(this::visit)
                    .collect(Collectors.joining(", ")));
        } else {
            sb.append(visit(ctx.maude_constant().get(0)));
        }

        sb.append(")");

        return sb.toString();
    }

    @Override public String visitAlert_constant(ScenarioParser.Alert_constantContext ctx) {
        if(ctx.alert_description() != null) {
            return visit(ctx.alert_description());
        } else if (ctx.alert_level() != null) {
            return visit(ctx.alert_level());
        } else {
            return "";
        }
    }

    @Override
    public String visitAlert_level(ScenarioParser.Alert_levelContext ctx) {
        switch (ctx.getText()){
            case "fatal": return AlertLevel.title() + "." + AlertLevel.FATAL.name();
            case "warn" : return AlertLevel.title() + "." + AlertLevel.WARNING.name();
            default :     return AlertLevel.title() + "." + AlertLevel.UNDEFINED.name();
        }
    }

    @Override
    public String visitAlert_description(ScenarioParser.Alert_descriptionContext ctx) {
        switch (ctx.getText()){
            case "close-notify": return AlertDescription.title() + "." + AlertDescription.CLOSE_NOTIFY.name();
            case "unexpected-message": return AlertDescription.title() + "." + AlertDescription.UNEXPECTED_MESSAGE.name();
            case "bad-record-mac": return AlertDescription.title() + "." + AlertDescription.BAD_RECORD_MAC.name();
            case "record-overflow": return AlertDescription.title() + "." + AlertDescription.RECORD_OVERFLOW.name();
            case "decompression-failure": return AlertDescription.title() + "." + AlertDescription.DECOMPRESSION_FAILURE.name();
            case "handshake-failure": return AlertDescription.title() + "." + AlertDescription.HANDSHAKE_FAILURE.name();
            case "no-certificate-reserved": return AlertDescription.title() + "." + AlertDescription.NO_CERTIFICATE_RESERVED.name();
            case "bad-certificate": return AlertDescription.title() + "." + AlertDescription.BAD_CERTIFICATE.name();
            case "unsupported-certificate": return AlertDescription.title() + "." + AlertDescription.UNSUPPORTED_CERTIFICATE.name();
            case "certificate-revoked": return AlertDescription.title() + "." + AlertDescription.CERTIFICATE_REVOKED.name();
            case "certificate-expired": return AlertDescription.title() + "." + AlertDescription.CERTIFICATE_EXPIRED.name();
            case "certificate-unknown": return AlertDescription.title() + "." + AlertDescription.CERTIFICATE_UNKNOWN.name();
            case "illegal-parameter": return AlertDescription.title() + "." + AlertDescription.ILLEGAL_PARAMETER.name();
            case "unknown-ca": return AlertDescription.title() + "." + AlertDescription.UNKNOWN_CA.name();
            case "access-denied": return AlertDescription.title() + "." + AlertDescription.ACCESS_DENIED.name();
            case "decode-error": return AlertDescription.title() + "." + AlertDescription.DECODE_ERROR.name();
            case "decrypt-error": return AlertDescription.title() + "." + AlertDescription.DECRYPT_ERROR.name();
            case "protocol-version": return AlertDescription.title() + "." + AlertDescription.PROTOCOL_VERSION.name();
            case "insufficient-security": return AlertDescription.title() + "." + AlertDescription.INSUFFICIENT_SECURITY.name();
            case "internal-error": return AlertDescription.title() + "." + AlertDescription.INTERNAL_ERROR.name();
            case "inappropriate-fallback": return AlertDescription.title() + "." + AlertDescription.INAPPROPRIATE_FALLBACK.name();
            case "user-canceled": return AlertDescription.title() + "." + AlertDescription.USER_CANCELED.name();
            case "no-renogitation": return AlertDescription.title() + "." + AlertDescription.NO_RENEGOTIATION.name();
            case "unsupported-extension": return AlertDescription.title() + "." + AlertDescription.UNSUPPORTED_EXTENSION.name();
            case "missing-extension": return AlertDescription.title() + "." + AlertDescription.MISSING_EXTENSION.name();
            default : return "";
        }
    }

    @Override
    public String visitProtocol_type_constant(ScenarioParser.Protocol_type_constantContext ctx) {
        switch (ctx.getText()){
            case "handshake": return ProtocolMessageType.title() + "." + ProtocolMessageType.HANDSHAKE.name();
            case "alert": return ProtocolMessageType.title() + "." + ProtocolMessageType.ALERT.name();
            case "change-cipher-spec": return ProtocolMessageType.title() + "." + ProtocolMessageType.CHANGE_CIPHER_SPEC.name();
            case "application-data": return ProtocolMessageType.title() + "." + ProtocolMessageType.APPLICATAION_DATA.name();
            default : return "";
        }
    }

    @Override
    public String visitProtocol_version_constant(ScenarioParser.Protocol_version_constantContext ctx) {
        switch (ctx.getText()){
            case "SSL-30": return ProtocolVersion.title() + "." + ProtocolVersion.SSL30.name();
            case "TLS-10": return ProtocolVersion.title() + "." + ProtocolVersion.TLS10.name();
            case "TLS-11": return ProtocolVersion.title() + "." + ProtocolVersion.TLS11.name();
            case "TLS-12": return ProtocolVersion.title() + "." + ProtocolVersion.TLS12.name();
            case "TLS-13": return ProtocolVersion.title() + "." + ProtocolVersion.TLS13.name();
            case "UNKNOWN": return ProtocolVersion.title() + "." + ProtocolVersion.UNKNOWN.name();
            default : return "";
        }
    }

    @Override
    public String visitHandshake_type_constant(ScenarioParser.Handshake_type_constantContext ctx) {
        switch (ctx.getText()){
            case "client-hello": return HandshakeMessageType.title() + "." + HandshakeMessageType.CLIENT_HELLO.name();
            case "server-hello": return HandshakeMessageType.title() + "." + HandshakeMessageType.SERVER_HELLO.name();
            case "certificate": return HandshakeMessageType.title() + "." + HandshakeMessageType.CERTIFICATE.name();
            case "server-key-exchange": return HandshakeMessageType.title() + "." + HandshakeMessageType.SERVER_KEY_EXCHANGE.name();
            case "certificate-request": return HandshakeMessageType.title() + "." + HandshakeMessageType.CERTIFICATE_REQUEST.name();
            case "server-hello-done": return HandshakeMessageType.title() + "." + HandshakeMessageType.SERVER_HELLO_DONE.name();
            case "client-key-exchange": return HandshakeMessageType.title() + "." + HandshakeMessageType.CLIENT_KEY_EXCHANGE.name();
            case "certificate-verify": return HandshakeMessageType.title() + "." + HandshakeMessageType.CERTIFICATE_VERIFY.name();
            case "finished": return HandshakeMessageType.title() + "." + HandshakeMessageType.FINISHED.name();
            case "hello-retry-request": return HandshakeMessageType.title() + "." + HandshakeMessageType.SERVER_HELLO.name();
            case "encrypted-extension": return HandshakeMessageType.title() + "." + HandshakeMessageType.ENCRYPTED_EXTENSION.name();
            case "new-session-ticket": return HandshakeMessageType.title() + "." + HandshakeMessageType.NEW_SESSION_TICKET.name();
            case "key-update-request": return HandshakeMessageType.title() + "." + HandshakeMessageType.KEY_UPDATE_REQUEST.name();
            default : return "";
        }
    }

    @Override
    public String visitCiphersuite_constant(ScenarioParser.Ciphersuite_constantContext ctx) {
        switch (ctx.getText()){
            case "TLS-DHE-RSA-WITH-3DES-EDE-CBC-SHA": return CipherSuite.title() + "."           + CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA.name();
            case "TLS-DHE-RSA-WITH-AES-256-CBC-SHA": return CipherSuite.title() + "."            + CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA.name();
            case "TLS-DHE-RSA-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."            + CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA.name();
            case "TLS-DH-anon-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."            + CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA.name();
            case "TLS-RSA-WITH-AES-256-CBC-SHA": return CipherSuite.title() + "."                + CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA.name();
            case "TLS-RSA-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."                + CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA.name();
            case "TLS-RSA-WITH-NULL-MD5": return CipherSuite.title() + "."                       + CipherSuite.TLS_RSA_WITH_NULL_MD5.name();
            case "TLS-RSA-WITH-NULL-SHA": return CipherSuite.title() + "."                       + CipherSuite.TLS_RSA_WITH_NULL_SHA.name();
            case "TLS-PSK-WITH-AES-256-CBC-SHA": return CipherSuite.title() + "."                + CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA.name();
            case "TLS-PSK-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."             + CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256.name();
            case "TLS-PSK-WITH-AES-256-CBC-SHA384": return CipherSuite.title() + "."             + CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384.name();
            case "TLS-PSK-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."                + CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA.name();
            case "TLS-PSK-WITH-NULL-SHA256": return CipherSuite.title() + "."                    + CipherSuite.TLS_PSK_WITH_NULL_SHA256.name();
            case "TLS-PSK-WITH-NULL-SHA384": return CipherSuite.title() + "."                    + CipherSuite.TLS_PSK_WITH_NULL_SHA384.name();
            case "TLS-PSK-WITH-NULL-SHA": return CipherSuite.title() + "."                       + CipherSuite.TLS_PSK_WITH_NULL_SHA.name();
            case "TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA": return CipherSuite.title() + "."          + CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA.name();
            case "TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."          + CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA": return CipherSuite.title() + "."        + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."        + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA.name();
            case "TLS-ECDHE-RSA-WITH-RC4-128-SHA": return CipherSuite.title() + "."              + CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA.name();
            case "TLS-ECDHE-ECDSA-WITH-RC4-128-SHA": return CipherSuite.title() + "."            + CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA.name();
            case "TLS-ECDHE-RSA-WITH-3DES-EDE-CBC-SHA": return CipherSuite.title() + "."         + CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA.name();
            case "TLS-ECDHE-ECDSA-WITH-3DES-EDE-CBC-SHA": return CipherSuite.title() + "."       + CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA.name();
            case "TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."       + CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."     + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256.name();
            case "TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA384": return CipherSuite.title() + "."       + CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA384": return CipherSuite.title() + "."     + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384.name();
            case "TLS-ECDHE-ECDSA-WITH-NULL-SHA": return CipherSuite.title() + "."               + CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA.name();
            case "TLS-ECDHE-PSK-WITH-NULL-SHA256": return CipherSuite.title() + "."              + CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256.name();
            case "TLS-ECDHE-PSK-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."       + CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256.name();
            case "TLS-ECDH-RSA-WITH-AES-256-CBC-SHA": return CipherSuite.title() + "."           + CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA.name();
            case "TLS-ECDH-RSA-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."           + CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA.name();
            case "TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA": return CipherSuite.title() + "."         + CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA.name();
            case "TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA": return CipherSuite.title() + "."         + CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA.name();
            case "TLS-ECDH-RSA-WITH-RC4-128-SHA": return CipherSuite.title() + "."               + CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA.name();
            case "TLS-ECDH-ECDSA-WITH-RC4-128-SHA": return CipherSuite.title() + "."             + CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA.name();
            case "TLS-ECDH-RSA-WITH-3DES-EDE-CBC-SHA": return CipherSuite.title() + "."          + CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA.name();
            case "TLS-ECDH-ECDSA-WITH-3DES-EDE-CBC-SHA": return CipherSuite.title() + "."        + CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA.name();
            case "TLS-ECDH-RSA-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."        + CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256.name();
            case "TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."      + CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256.name();
            case "TLS-ECDH-RSA-WITH-AES-256-CBC-SHA384": return CipherSuite.title() + "."        + CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384.name();
            case "TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA384": return CipherSuite.title() + "."      + CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384.name();
            case "TLS-DHE-RSA-WITH-AES-256-CBC-SHA256": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256.name();
            case "TLS-DHE-RSA-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256.name();
            case "TLS-RSA-WITH-AES-256-CBC-SHA256": return CipherSuite.title() + "."             + CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256.name();
            case "TLS-RSA-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."             + CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256.name();
            case "TLS-RSA-WITH-NULL-SHA256": return CipherSuite.title() + "."                    + CipherSuite.TLS_RSA_WITH_NULL_SHA256.name();
            case "TLS-DHE-PSK-WITH-AES-128-CBC-SHA256": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256.name();
            case "TLS-DHE-PSK-WITH-NULL-SHA256": return CipherSuite.title() + "."                + CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256.name();
            case "TLS-DHE-PSK-WITH-AES-256-CBC-SHA384": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384.name();
            case "TLS-DHE-PSK-WITH-NULL-SHA384": return CipherSuite.title() + "."                + CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384.name();
            case "TLS-RSA-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."             + CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256.name();
            case "TLS-RSA-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."             + CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384.name();
            case "TLS-DHE-RSA-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256.name();
            case "TLS-DHE-RSA-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384.name();
            case "TLS-DH-anon-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."         + CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384.name();
            case "TLS-PSK-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."             + CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256.name();
            case "TLS-PSK-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."             + CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384.name();
            case "TLS-DHE-PSK-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256.name();
            case "TLS-DHE-PSK-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."         + CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."     + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."     + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384.name();
            case "TLS-ECDH-ECDSA-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."      + CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256.name();
            case "TLS-ECDH-ECDSA-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."      + CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384.name();
            case "TLS-ECDHE-RSA-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."       + CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256.name();
            case "TLS-ECDHE-RSA-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."       + CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384.name();
            case "TLS-ECDH-RSA-WITH-AES-128-GCM-SHA256": return CipherSuite.title() + "."        + CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256.name();
            case "TLS-ECDH-RSA-WITH-AES-256-GCM-SHA384": return CipherSuite.title() + "."        + CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384.name();
            case "TLS-RSA-WITH-AES-128-CCM-8": return CipherSuite.title() + "."                  + CipherSuite.TLS_RSA_WITH_AES_128_CCM_8.name();
            case "TLS-RSA-WITH-AES-256-CCM-8": return CipherSuite.title() + "."                  + CipherSuite.TLS_RSA_WITH_AES_256_CCM_8.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-128-CCM": return CipherSuite.title() + "."            + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-128-CCM-8": return CipherSuite.title() + "."          + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8.name();
            case "TLS-ECDHE-ECDSA-WITH-AES-256-CCM-8": return CipherSuite.title() + "."          + CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8.name();
            case "TLS-PSK-WITH-AES-128-CCM": return CipherSuite.title() + "."                    + CipherSuite.TLS_PSK_WITH_AES_128_CCM.name();
            case "TLS-PSK-WITH-AES-256-CCM": return CipherSuite.title() + "."                    + CipherSuite.TLS_PSK_WITH_AES_256_CCM.name();
            case "TLS-PSK-WITH-AES-128-CCM-8": return CipherSuite.title() + "."                  + CipherSuite.TLS_PSK_WITH_AES_128_CCM_8.name();
            case "TLS-PSK-WITH-AES-256-CCM-8": return CipherSuite.title() + "."                  + CipherSuite.TLS_PSK_WITH_AES_256_CCM_8.name();
            case "TLS-DHE-PSK-WITH-AES-128-CCM": return CipherSuite.title() + "."                + CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM.name();
            case "TLS-DHE-PSK-WITH-AES-256-CCM": return CipherSuite.title() + "."                + CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM.name();
            case "TLS-AES-128-CCM-SHA256": return CipherSuite.title() + "."                      + CipherSuite.TLS_AES_128_CCM_SHA256.name();
            case "TLS-AES-128-CCM-8-SHA256": return CipherSuite.title() + "."                    + CipherSuite.TLS_AES_128_CCM_8_SHA256.name();
            case "TLS-AES-128-GCM-SHA256": return CipherSuite.title() + "."                      + CipherSuite.TLS_AES_128_GCM_SHA256.name();
            case "TLS-AES-256-GCM-SHA384": return CipherSuite.title() + "."                      + CipherSuite.TLS_AES_256_GCM_SHA384.name();
            default: return "";
        }
    }

    @Override
    public String visitCompression_constant(ScenarioParser.Compression_constantContext ctx) {
        switch (ctx.getText()) {
            case "no-compression": return CompressionMethod.title() + "." + CompressionMethod.NO_COMPRESSION.name();
            case "zlib-compression": return CompressionMethod.title() + "." + CompressionMethod.DEFLATE.name();
            case "invalid-compression": return CompressionMethod.title() + "." + CompressionMethod.INVALID_COMPRESSION.name();
            default: return "";
        }
    }

    @Override
    public String visitSignature_and_hash_algorithm_constant(ScenarioParser.Signature_and_hash_algorithm_constantContext ctx) {
        switch (ctx.getText()) {
            case "{anon,sha}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ANON_SHA.name();
            case "{anon,sha224}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ANON_SHA224.name();
            case "{anon,sha256}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ANON_SHA256.name();
            case "{anon,sha384}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ANON_SHA384.name();
            case "{anon,sha512}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ANON_SHA512.name();
            case "{anon,md5}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ANON_MD5.name();
            case "{rsa,sha}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_SHA1.name();
            case "{rsa,sha224}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_SHA224.name();
            case "{rsa,sha256}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_SHA256.name();
            case "{rsa,sha384}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_SHA384.name();
            case "{rsa,sha512}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_SHA512.name();
            case "{rsa,md5}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_MD5.name();
            case "{rsa-pss-rsae,sha256}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_PSS_RSAE_SHA256.name();
            case "{rsa-pss-rsae,sha384}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_PSS_RSAE_SHA384.name();
            case "{rsa-pss-rsae,sha512}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_PSS_RSAE_SHA512.name();
            case "{rsa-pss-pss,sha256}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_PSS_PSS_SHA256.name();
            case "{rsa-pss-pss,sha384}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_PSS_PSS_SHA384.name();
            case "{rsa-pss-pss,sha512}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.RSA_PSS_PSS_SHA512.name();
            case "{ecdsa,sha}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ECDSA_SHA1.name();
            case "{ecdsa,sha224}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ECDSA_SHA224.name();
            case "{ecdsa,sha256}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ECDSA_SHA256.name();
            case "{ecdsa,sha384}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ECDSA_SHA384.name();
            case "{ecdsa,sha512}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ECDSA_SHA512.name();
            case "{ecdsa,md5}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.ECDSA_MD5.name();
            case "{dsa,sha}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.DSA_SHA1.name();
            case "{dsa,sha224}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.DSA_SHA224.name();
            case "{dsa,sha256}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.DSA_SHA256.name();
            case "{dsa,sha384}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.DSA_SHA384.name();
            case "{dsa,sha512}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.DSA_SHA512.name();
            case "{dsa,md5}": return SignatureAlgorithm.title() + "." + SignatureAlgorithm.DSA_MD5.name();
            default: return "";
        }    }

    @Override
    public String visitNamed_group_constant(ScenarioParser.Named_group_constantContext ctx) {
        switch (ctx.getText()){
            case "secp160k1": return NamedGroup.title() + "." + NamedGroup.SECP160K1.name();
            case "secp160r1": return NamedGroup.title() + "." + NamedGroup.SECP160R1.name();
            case "secp160r2": return NamedGroup.title() + "." + NamedGroup.SECP160R2.name();
            case "secp192k1": return NamedGroup.title() + "." + NamedGroup.SECP192K1.name();
            case "secp192r1": return NamedGroup.title() + "." + NamedGroup.SECP192R1.name();
            case "secp224k1": return NamedGroup.title() + "." + NamedGroup.SECP224K1.name();
            case "secp224r1": return NamedGroup.title() + "." + NamedGroup.SECP224R1.name();
            case "secp256k1": return NamedGroup.title() + "." + NamedGroup.SECP256K1.name();
            case "secp256r1": return NamedGroup.title() + "." + NamedGroup.SECP256R1.name();
            case "secp384r1": return NamedGroup.title() + "." + NamedGroup.SECP384R1.name();
            case "secp521r1": return NamedGroup.title() + "." + NamedGroup.SECP521R1.name();
            case "ffdhe2048": return NamedGroup.title() + "." + NamedGroup.FFDHE2048.name();
            case "ffdhe3072": return NamedGroup.title() + "." + NamedGroup.FFDHE3072.name();
            case "ffdhe4096": return NamedGroup.title() + "." + NamedGroup.FFDHE4096.name();
            case "ffdhe6144": return NamedGroup.title() + "." + NamedGroup.FFDHE6144.name();
            case "ffdhe8192": return NamedGroup.title() + "." + NamedGroup.FFDHE8192.name();
            default: return "";
        }
    }

    @Override
    public String visitPsk_key_exchange_mode(ScenarioParser.Psk_key_exchange_modeContext ctx) {
        switch (ctx.getText()){
            case "psk-ke": return PskKeyExchangeMode.title() + "." + PskKeyExchangeMode.PSK_KE.name();
            case "psk-dhe-ke": return PskKeyExchangeMode.title() + "." + PskKeyExchangeMode.PSK_DHE_KE.name();
            default: return "";
        }
    }

    @Override
    public String visitMsg_size_constant(ScenarioParser.Msg_size_constantContext ctx){
        switch (ctx.getText()){
            case "valid": return MessageSize.title() + "." + MessageSize.VALID.name();
            case "smaller": return MessageSize.title() + "." + MessageSize.SMALLER.name();
            case "larger": return MessageSize.title() + "." + MessageSize.LARGER.name();
            case "maxSize": return MessageSize.title() + "." + MessageSize.MAXSIZE.name();
            case "minSize": return MessageSize.title() + "." + MessageSize.MINSIZE.name();
            default: return "";
        }
    }

    @Override
    public String visitCurve_type_constant(ScenarioParser.Curve_type_constantContext ctx) {
        switch (ctx.getText()){
            case "namedcurve": return CurveType.title() + "." + CurveType.NAMED_CURVE.name();
            default: return "";
        }
    }

    @Override
    public String visitCertificate_type_constant(ScenarioParser.Certificate_type_constantContext ctx) {
        switch (ctx.getText()){
            case "rsa-sign": return CertificateType.title() + "." + CertificateType.RSA_SIGN.name();
            case "dss-sign": return CertificateType.title() + "." + CertificateType.DSS_SIGN.name();
            case "rsa-fixed-dh": return CertificateType.title() + "." + CertificateType.RSA_FIXED_DH.name();
            case "dss-fixed-dh": return CertificateType.title() + "." + CertificateType.DSS_FIXED_DH.name();
            case "ecdsa-sign": return CertificateType.title() + "." + CertificateType.ECDSA_SIGN.name();
            case "rsa-fixed-ecdh": return CertificateType.title() + "." + CertificateType.RSA_FIXED_ECDH.name();
            case "ecdsa-fixed-ecdh": return CertificateType.title() + "." + CertificateType.ECDSA_FIXED_ECDH.name();
            default: return "";
        }
    }

    @Override
    public String visitNumber_constant(ScenarioParser.Number_constantContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitMaude_constant(ScenarioParser.Maude_constantContext ctx) {
        String literal = ctx.getText();
        if ("true".equals(literal) || "false".equals(literal)) {
            return literal;
        }
        return super.visitMaude_constant(ctx);
    }

    @Override
    public String visitSession_id_constant(ScenarioParser.Session_id_constantContext ctx) {
        if (ctx.NAT() != null) {
            return "session.sessionId(" + ctx.NAT().getText() + ")";
        }
        return "session.sessionId(" + visit(ctx.msg_size_constant()) + ")";
    }

    @Override
    public String visitSession_id_size_constant(ScenarioParser.Session_id_size_constantContext ctx) {
        return "session.sessionIdSize(" + ctx.NAT().getText() + ")";
    }

    @Override
    public String visitOther_constant(ScenarioParser.Other_constantContext ctx) {
        if(ctx.getText().equals("prvkey-path")){
            return "\"" + nodeList.get(0).getPrivateKeyPath() + "\"";
        } else if(ctx.getText().equals("cert-path")){
            return "\"" + nodeList.get(0).getCertificatePath() + "\"";
        } else if (ctx.getText().equals("ca-names")) {
            return "\"" + nodeList.get(0).getCertificatePath() + "\"";
        } else {
            return "";
        }
    }

}
