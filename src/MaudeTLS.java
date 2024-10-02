import Maude.*;
import Maude.HandshakeMessageType;
import Maude.NamedGroup;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import de.rub.nds.modifiablevariable.util.Modifiable;
import de.rub.nds.tlsattacker.core.certificate.CertificateKeyPair;
import de.rub.nds.tlsattacker.core.certificate.PemUtil;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.constants.*;
import de.rub.nds.tlsattacker.core.constants.AlertLevel;
import de.rub.nds.tlsattacker.core.constants.CompressionMethod;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.HashAlgorithm;
import de.rub.nds.tlsattacker.core.constants.SignatureAlgorithm;
import de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.protocol.message.*;
import de.rub.nds.tlsattacker.core.protocol.message.extension.ECPointFormatExtensionMessage;
import de.rub.nds.tlsattacker.core.protocol.message.extension.EllipticCurvesExtensionMessage;
import de.rub.nds.tlsattacker.core.protocol.message.extension.ExtensionMessage;
import de.rub.nds.tlsattacker.core.protocol.message.extension.SignatureAndHashAlgorithmsExtensionMessage;
import de.rub.nds.tlsattacker.core.state.Context;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.workflow.action.ReceiveAction;
import de.rub.nds.tlsattacker.core.workflow.action.SendAction;
import de.rub.nds.tlsattacker.transport.TransportHandler;
import de.rub.nds.tlsattacker.transport.TransportHandlerFactory;
import org.bouncycastle.crypto.tls.Certificate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaudeTLS {
    private Config config;
    private Context context;
    private State state;

    private String alias;

    private Queue<ProtocolMessage> receivedMsgs;

    public MaudeTLS(){receivedMsgs = new LinkedList<ProtocolMessage>();}
    public MaudeTLS(String configPath){
        config = Config.createConfig(new File(configPath));
        receivedMsgs = new LinkedList<ProtocolMessage>();
    }

    public void setCertificateKeyPair(String certPath){
        try {
            config.setAutoSelectCertificate(false);
            config.setUseFreshRandom(true);
            Certificate caCertificate = PemUtil.readCertificate(new FileInputStream(new File(certPath)));
            config.setDefaultExplicitCertificateKeyPair(new CertificateKeyPair(caCertificate));
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCertificateKeyPair(String servPath, String keyPath){
        try {
            config.setAutoSelectCertificate(false);
            Certificate servCertificate = PemUtil.readCertificate(new FileInputStream(new File(servPath)));
            PrivateKey privateKey = PemUtil.readPrivateKey(new FileInputStream(new File(keyPath)));
            config.setDefaultExplicitCertificateKeyPair(new CertificateKeyPair(servCertificate, privateKey));
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(String ip, int port, String _alias){
        if(_alias.equals("server")){
            config.setDefaultRunningMode(RunningModeType.SERVER);
        } else if(_alias.equals("client")){
            config.setDefaultRunningMode(RunningModeType.CLIENT);
        } else {
            System.out.println("The given alias is not supported");
            return;
        }

        context = new Context(config);
        alias = _alias;
        AliasedConnection conn = context.getConnection();
        //timeout value should be changed!
        conn.setFirstTimeout(1000);
        conn.setTimeout(1000);
        conn.setConnectionTimeout(1000);
        conn.setIp(ip);
        conn.setPort(port);

        TransportHandler handler = TransportHandlerFactory.createTransportHandler(context.getConnection());
        try{
            handler.preInitialize();
            handler.initialize();
        } catch(Exception e){
            System.out.println(e.getCause());
        }
        context.setTransportHandler(handler);

        state = new State(config);
        state.getContext(alias).setTlsContext(context.getTlsContext());
    }

    private HelloMessage genHelloMessage(HelloMessage message, Maude.TLSVersion version, Maude.Random random, Maude.SessionId sid){
        switch(version){
            case TLS11:  message.setProtocolVersion(Modifiable.explicit(ProtocolVersion.TLS11.getValue())); break;
            case TLS12:  message.setProtocolVersion(Modifiable.explicit(ProtocolVersion.TLS12.getValue())); break;
            case TLS13:  message.setProtocolVersion(Modifiable.explicit(ProtocolVersion.TLS13.getValue())); break;
            case SSLV2 : message.setProtocolVersion(Modifiable.explicit(ProtocolVersion.SSL2.getValue())); break;
            default:    return null;
        }
        SecureRandom randomGenerator;
        try{
            randomGenerator = SecureRandom.getInstanceStrong();
        }catch (Exception e){
            return null;
        }
        switch(random){
            case EMPTY: message.setRandom(Modifiable.explicit(new byte[]{})); break;
            case NONCE: byte[] randomByte = new byte[32];
                randomGenerator.nextBytes(randomByte);
                message.setRandom(Modifiable.explicit(randomByte)); break;
            default: return null;
        }
        switch(sid){
            case EMPTY: message.setSessionId(Modifiable.explicit(new byte[]{}));
                message.setSessionIdLength(Modifiable.explicit(0));
                break;
            case NONCE: byte[] randomByte = new byte[32];
                randomGenerator.nextBytes(randomByte);
                message.setSessionId(Modifiable.explicit(randomByte));
                message.setSessionIdLength(Modifiable.explicit(32));
                break;
            default: return null;
        }
        return message;
    }

    public ClientHelloMessage genClientHelloMessage(Maude.TLSVersion version,
                                                    Maude.CipherSuite suite,
                                                    Maude.CompressionMethod method,
                                                    Maude.Random random,
                                                    Maude.SessionId sid) {
        ClientHelloMessage message = new ClientHelloMessage();
        message.setCipherSuiteLength(1);



        switch(suite){
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM: message.setCipherSuites(Modifiable.explicit(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM.getByteValue())); break;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CCM: message.setCipherSuites(Modifiable.explicit(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM.getByteValue())); break;
            default: return null;
        }
        message.setCompressionLength(1);
        switch(method){
            case NO_COMPRESSION: message.setCompressions(Modifiable.explicit(CompressionMethod.NULL.getArrayValue())); break;
            case DEFLATE: message.setCompressions(Modifiable.explicit(CompressionMethod.DEFLATE.getArrayValue())); break;
            default: return null;
        }
        return (ClientHelloMessage) genHelloMessage(message, version, random, sid);
    }

    public void addClientHelloExtension(ProtocolMessage message, Maude.NamedGroup namedGroup){
        if(message instanceof ClientHelloMessage){
            ClientHelloMessage chMsg = (ClientHelloMessage) message;

            EllipticCurvesExtensionMessage supported_ext = new EllipticCurvesExtensionMessage();
            supported_ext.setSupportedGroupsLength(Modifiable.explicit(2));
            supported_ext.setSupportedGroups(Modifiable.explicit(namedGroup.transform().getValue()));

            List<ExtensionMessage> extensionMessages = chMsg.getExtensions();
            extensionMessages.add(supported_ext);
            chMsg.setExtensions(extensionMessages);
        }
    }

    public void addClientHelloExtension(ProtocolMessage message, Maude.ECPointFormat pointFormat){
        if(message instanceof ClientHelloMessage){
            ClientHelloMessage chMsg = (ClientHelloMessage) message;

            ECPointFormatExtensionMessage supported_format = new ECPointFormatExtensionMessage();
            supported_format.setPointFormatsLength(Modifiable.explicit(2));
            supported_format.setPointFormats(pointFormat.transform().getArrayValue());

            List<ExtensionMessage> extensionMessages = chMsg.getExtensions();
            extensionMessages.add(supported_format);
            chMsg.setExtensions(extensionMessages);
        }
    }

    public void addClientHelloExtension(ProtocolMessage message, Maude.SignatureAndHashAlgorithm signatureAndHashAlgorithm){
        if(message instanceof ClientHelloMessage){
            ClientHelloMessage chMsg = (ClientHelloMessage) message;

            SignatureAndHashAlgorithmsExtensionMessage supported_hash = new SignatureAndHashAlgorithmsExtensionMessage();
            supported_hash.setSignatureAndHashAlgorithmsLength(Modifiable.explicit(2));
            supported_hash.setSignatureAndHashAlgorithms(signatureAndHashAlgorithm.transform().getByteValue());

            List<ExtensionMessage> extensionMessages = chMsg.getExtensions();
            extensionMessages.add(supported_hash);
            chMsg.setExtensions(extensionMessages);
        }
    }

    public ServerHelloMessage genServerHelloMessage(Maude.TLSVersion version, Maude.CipherSuite suite, Maude.CompressionMethod method, Maude.Random random, Maude.SessionId sid) {
        ServerHelloMessage message = new ServerHelloMessage();
        message.setExtensionBytes(Modifiable.explicit(new byte[]{}));
        switch(suite){
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM: message.setSelectedCipherSuite(Modifiable.explicit(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM.getByteValue())); break;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CCM: message.setSelectedCipherSuite(Modifiable.explicit(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM.getByteValue())); break;
            default: return null;
        }
        switch(method){
            case NO_COMPRESSION: message.setSelectedCompressionMethod(Modifiable.explicit(CompressionMethod.NULL.getValue())); break;
            case DEFLATE: message.setSelectedCompressionMethod(Modifiable.explicit(CompressionMethod.DEFLATE.getValue())); break;
            default: return null;
        }
        return (ServerHelloMessage) genHelloMessage(message, version, random, sid);
    }

    public void tempFunc(ProtocolMessage message, byte[] time, byte[] random, byte[] sessionId){
        ServerHelloMessage shMsg = (ServerHelloMessage) message;
        shMsg.setUnixTime(Modifiable.explicit(time));
        byte[] random2 = ArrayConverter.concatenate(time, random);
        shMsg.setRandom(Modifiable.explicit(random2));
        shMsg.setSessionId(Modifiable.explicit(sessionId));
    }

    public ClientKeyExchangeMessage genClientKeyExchange(){
        ECDHClientKeyExchangeMessage message = new ECDHClientKeyExchangeMessage();
        return message;
    }

    public ServerKeyExchangeMessage genServerKeyExchange(Maude.CurveType curveType, Maude.NamedGroup namedGroup) {
        ECDHEServerKeyExchangeMessage message = new ECDHEServerKeyExchangeMessage();
        byte err = 0;
        switch(curveType){
            case NAMED_CURVE: message.setCurveType(Modifiable.explicit(EllipticCurveType.NAMED_CURVE.getValue())); break;
            default:          message.setCurveType(Modifiable.explicit(err));
        }

        message.setNamedGroup(Modifiable.explicit(namedGroup.transform().getValue()));
        return message;
    }

    public ServerHelloDoneMessage genServerHelloDone() {
        ServerHelloDoneMessage message = new ServerHelloDoneMessage();
        return message;
    }

    public ChangeCipherSpecMessage genChangeCipherSpec(){
        ChangeCipherSpecMessage message = new ChangeCipherSpecMessage();
        return message;
    }

    public FinishedMessage genFinished(){
        FinishedMessage message = new FinishedMessage();
        return message;
    }



    public CertificateMessage genCertificate(){
        CertificateMessage message = new CertificateMessage();

        return message;
    }

    public CertificateRequestMessage genCertificateRequest(Maude.CertificateType type, Maude.SignatureAlgorithm sigAlgo, Maude.HashAlgorithm hashAlgo){
        CertificateRequestMessage message = new CertificateRequestMessage();

        byte[] certificateType;
        switch(type){
            case DSS_FIXED_DH:     certificateType = ClientCertificateType.DSS_FIXED_DH.getArrayValue();     break;
            case RSA_SIGN:         certificateType = ClientCertificateType.RSA_SIGN.getArrayValue();         break;
            case DSS_SIGN:         certificateType = ClientCertificateType.DSS_SIGN.getArrayValue();         break;
            case ECDSA_SIGN:       certificateType = ClientCertificateType.ECDSA_SIGN.getArrayValue();       break;
            case RSA_FIXED_DH:     certificateType = ClientCertificateType.RSA_FIXED_DH.getArrayValue();     break;
            case RSA_FIXED_ECDH:   certificateType = ClientCertificateType.RSA_FIXED_ECDH.getArrayValue();   break;
            case ECDSA_FIXED_ECDH: certificateType = ClientCertificateType.ECDSA_FIXED_ECDH.getArrayValue(); break;
            default:               certificateType = null;
        }
        message.setClientCertificateTypesCount(Modifiable.explicit(certificateType.length));
        message.setClientCertificateTypes(Modifiable.explicit(certificateType));

        SignatureAlgorithm signatureAlgorithm;
        switch(sigAlgo){
            case DSA:       signatureAlgorithm = SignatureAlgorithm.DSA;       break;
            case RSA:       signatureAlgorithm = SignatureAlgorithm.RSA;       break;
            case ECDSA:     signatureAlgorithm = SignatureAlgorithm.ECDSA;     break;
            case ANONYMOUS: signatureAlgorithm = SignatureAlgorithm.ANONYMOUS; break;
            default:        signatureAlgorithm = null;
        }
        HashAlgorithm hashAlgorithm;
        switch(hashAlgo){
            case NONE:   hashAlgorithm = HashAlgorithm.NONE;   break;
            case MD5:    hashAlgorithm = HashAlgorithm.MD5;    break;
            case SHA1:   hashAlgorithm = HashAlgorithm.SHA1;   break;
            case SAH224: hashAlgorithm = HashAlgorithm.SHA224; break;
            case SHA256: hashAlgorithm = HashAlgorithm.SHA256; break;
            case SHA384: hashAlgorithm = HashAlgorithm.SHA384; break;
            case SHA512: hashAlgorithm = HashAlgorithm.SHA512; break;
            default: hashAlgorithm = null;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = SignatureAndHashAlgorithm.getSignatureAndHashAlgorithm(signatureAlgorithm, hashAlgorithm);
        message.setSignatureHashAlgorithmsLength(Modifiable.explicit(signatureAndHashAlgorithm.getByteValue().length));
        message.setSignatureHashAlgorithms(Modifiable.explicit(signatureAndHashAlgorithm.getByteValue()));

        message.setDistinguishedNamesLength(Modifiable.explicit(0));
        message.setDistinguishedNames(Modifiable.explicit(new byte[]{}));

        return message;
    }

    public Maude.TLSVersion getProtocolVersion(ProtocolMessage message){
        if(message instanceof HelloMessage){
            HelloMessage shMsg = (HelloMessage) message;
            switch(ProtocolVersion.getProtocolVersion(shMsg.getProtocolVersion().getValue())){
                case TLS11: return Maude.TLSVersion.TLS11;
                case TLS12: return Maude.TLSVersion.TLS12;
                case TLS13: return Maude.TLSVersion.TLS13;
                case SSL2: return Maude.TLSVersion.SSLV2;
                default: return null;
            }
        }
        return null;
    }

    public Maude.CipherSuite getCipherSuite(ProtocolMessage message) {
        if(message instanceof ClientHelloMessage){
            ClientHelloMessage chMsg = (ClientHelloMessage) message;
            switch(CipherSuite.getCipherSuite(chMsg.getCipherSuites().getValue())){
                case TLS_ECDHE_ECDSA_WITH_AES_128_CCM: return Maude.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM;
                case TLS_ECDHE_ECDSA_WITH_AES_256_CCM: return Maude.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM;
                default: return null;
            }
        }
        return null;
    }

    public Maude.CipherSuite getSelectedCipherSuite(ProtocolMessage message) {
        if(message instanceof ServerHelloMessage){
            ServerHelloMessage shMsg = (ServerHelloMessage) message;
            switch(CipherSuite.getCipherSuite(shMsg.getSelectedCipherSuite().getValue())){
                case TLS_ECDHE_ECDSA_WITH_AES_128_CCM: return Maude.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM;
                case TLS_ECDHE_ECDSA_WITH_AES_256_CCM: return Maude.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM;
                default: return null;
            }
        }
        return null;
    }

    public Maude.CompressionMethod getSelectedCompressionMethod(ProtocolMessage message) {
        if(message instanceof ServerHelloMessage){
            ServerHelloMessage shMsg = (ServerHelloMessage) message;
            switch(CompressionMethod.getCompressionMethod(shMsg.getSelectedCompressionMethod().getValue())){
                case NULL: return Maude.CompressionMethod.NULL;
                case DEFLATE: return Maude.CompressionMethod.DEFLATE;
                case LZS: return Maude.CompressionMethod.LZS;
            }
        }
        return Maude.CompressionMethod.NULL;
    }


    public int getCompressionMethodLength(ProtocolMessage message) {
        if(message instanceof ClientHelloMessage){
            ClientHelloMessage chMsg = (ClientHelloMessage) message;
            return chMsg.getCompressionLength().getValue();
        }
        return 0;
    }

    public List<Maude.CompressionMethod> getCompressionMethods(ProtocolMessage message) {
        if(message instanceof ClientHelloMessage){
            ClientHelloMessage chMsg = (ClientHelloMessage) message;
            List<Maude.CompressionMethod> compList = new ArrayList<>();
            for (byte compression : chMsg.getCompressions().getValue()){
                switch(CompressionMethod.getCompressionMethod(compression)){
                    case NULL: compList.add(Maude.CompressionMethod.NULL);
                    case DEFLATE: compList.add(Maude.CompressionMethod.DEFLATE);
                    case LZS: compList.add(Maude.CompressionMethod.LZS);
                }
            }
            return compList;
        }
        return null;
    }

    public Maude.CertificateKeyType getCertificateKeyType(ProtocolMessage message){
        if(message instanceof CertificateMessage){
            CertificateMessage cMsg = (CertificateMessage)message;
            CertificateKeyPair pair = cMsg.getCertificateKeyPair();
            switch(pair.getCertSignatureType()){
                case ECDSA: return Maude.CertificateKeyType.ECDSA;
                case DSS: return Maude.CertificateKeyType.DSS;
                case RSA: return Maude.CertificateKeyType.RSA;
                case NONE: return Maude.CertificateKeyType.NONE;
            }
        }
        return null;
    }

    public NamedGroup getPublicKeyGroup(ProtocolMessage message){
        if(message instanceof CertificateMessage){
            CertificateMessage cMsg = (CertificateMessage)message;
            CertificateKeyPair pair = cMsg.getCertificateKeyPair();
            switch(pair.getPublicKeyGroup()){
                case SECP256R1: return NamedGroup.SECP256R1;
                default: return NamedGroup.NONE;
            }
        }
        return null;
    }

    public NamedGroup getSignatureGroup(ProtocolMessage message){
        if(message instanceof CertificateMessage){
            CertificateMessage cMsg = (CertificateMessage)message;
            CertificateKeyPair pair = cMsg.getCertificateKeyPair();
            switch(pair.getSignatureGroup()){
                case SECP256R1: return NamedGroup.SECP256R1;
                default: return NamedGroup.NONE;
            }
        }
        return null;
    }

    public boolean hasECDHClientPublicKey(ProtocolMessage message){
        if(message instanceof ClientKeyExchangeMessage){
            ClientKeyExchangeMessage ckMsg = (ClientKeyExchangeMessage)message;
            return ckMsg.getPublicKey() != null && ckMsg.getPublicKeyLength().getValue() > 0;
        }
        return false;
    }


    public boolean hasCertificateVerifySignature(ProtocolMessage message){
        if(message instanceof CertificateVerifyMessage){
            CertificateVerifyMessage cvMsg = (CertificateVerifyMessage)message;
            return cvMsg.getSignature() != null && cvMsg.getSignatureLength().getValue() > 0;
        }
        return false;
    }

    public Maude.SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(ProtocolMessage message){
        if (message instanceof CertificateMessage){
            CertificateMessage cert = (CertificateMessage) message;
            return Maude.SignatureAndHashAlgorithm.transform(cert.getCertificateKeyPair().getSignatureAndHashAlgorithm());
        }
        return null;
    }
    /*
      public Maude.SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(ProtocolMessage message){
          if(message instanceof CertificateVerifyMessage){
              CertificateVerifyMessage cvMsg = (CertificateVerifyMessage)message;
              switch(SignatureAndHashAlgorithm.getSignatureAndHashAlgorithm(cvMsg.getSignatureHashAlgorithm().getValue())){
                  case ECDSA_SHA256: return Maude.SignatureAndHashAlgorithm.ECDSA_SHA256;
                  case ECDSA_SHA384: return Maude.SignatureAndHashAlgorithm.ECDSA_SHA384;
                  case ECDSA_SHA512: return Maude.SignatureAndHashAlgorithm.ECDSA_SHA512;
                  default: return Maude.SignatureAndHashAlgorithm.NONE;
              }
          }
      }
    */

    public Maude.AlertLevel getAlertLevel(ProtocolMessage message){
        if(message instanceof AlertMessage){
            AlertMessage amsg = (AlertMessage) message;
            return Maude.AlertLevel.transform(AlertLevel.getAlertLevel(amsg.getLevel().getValue()));
        }
        return null;
    }

    public boolean hasRandom(ProtocolMessage message){
        if(message instanceof HelloMessage){
            HelloMessage hMsg = (HelloMessage) message;
            return hMsg.getRandom().getValue().length > 0;
        }
        return false;
    }

    public int getSessionIdLength(ProtocolMessage message){
        if(message instanceof HelloMessage){
            HelloMessage hMsg = (HelloMessage) message;
            return hMsg.getSessionIdLength().getValue();
        }
        return 0;
    }
    public boolean hasSessionId(ProtocolMessage message){
        if(message instanceof HelloMessage){
            HelloMessage hMsg = (HelloMessage) message;
            return hMsg.getSessionIdLength().getValue().intValue() > 0;
        }
        return false;
    }

    public MessageType getMessageType(ProtocolMessage message){
        switch(message.getProtocolMessageType()){
            case ALERT: return MessageType.ALERT;
            case HANDSHAKE: return MessageType.HANDSHAKE;
            case APPLICATION_DATA: return MessageType.APPLICATION_DATA;
            case CHANGE_CIPHER_SPEC: return MessageType.CHANGE_CIPHER_SPEC;
            default: return null;
        }
    }
    public HandshakeMessageType getHandshakeMessageType(ProtocolMessage message){
        if(message instanceof HandshakeMessage){
            HandshakeMessage hMsg = (HandshakeMessage) message;
            switch(hMsg.getHandshakeMessageType()){
                case CLIENT_HELLO: return HandshakeMessageType.CLIENT_HELLO;
                case SERVER_HELLO: return HandshakeMessageType.SERVER_HELLO;
                case SERVER_KEY_EXCHANGE: return HandshakeMessageType.SERVER_KEY_EXCHANGE;
                case CERTIFICATE: return HandshakeMessageType.CERTIFICATE;
                case CERTIFICATE_REQUEST: return HandshakeMessageType.CERTIFICATE_REQUEST;
                case SERVER_HELLO_DONE: return HandshakeMessageType.SERVER_HELLO_DONE;
                case CLIENT_KEY_EXCHANGE: return HandshakeMessageType.CLIENT_KEY_EXCHANGE;
                case CERTIFICATE_VERIFY: return HandshakeMessageType.CERTIFICATE_VERIFY;
                case FINISHED: return HandshakeMessageType.FINISHED;
                default: return null;
            }
        }
        return null;
    }
    public boolean hasPeerPublicKey(ProtocolMessage message){
        if(message instanceof CertificateMessage){
            CertificateMessage cMsg = (CertificateMessage) message;
            return cMsg.getCertificateKeyPair().getPublicKey() != null;
        }
        return false;
    }
    public boolean hasServerKey(ProtocolMessage message){
        if(message instanceof ServerKeyExchangeMessage){
            ServerKeyExchangeMessage sMsg = (ServerKeyExchangeMessage) message;
            return sMsg.getPublicKey() != null;
        }
        return false;
    }
    public boolean verifySignature(ProtocolMessage message){
        if(message instanceof ServerKeyExchangeMessage){
            ServerKeyExchangeMessage sMsg = (ServerKeyExchangeMessage) message;
            return sMsg.getSignature() != null;
        }
        return false;
    }
    public boolean verifyData(ProtocolMessage message){
        if(message instanceof FinishedMessage){
            FinishedMessage fMsg = (FinishedMessage) message;
            return fMsg.getVerifyData().getValue() != null;
        }
        return false;
    }

    public ProtocolMessage recv(){
        if(receivedMsgs.isEmpty()) {
            ReceiveAction action2 = new ReceiveAction(alias);
            action2.execute(state);
            receivedMsgs.addAll(action2.getReceivedMessages());
        }
        return receivedMsgs.poll();
    }

    public void send(ProtocolMessage msg){
        SendAction action = new SendAction(alias, msg);
        action.execute(state);
    }
}
