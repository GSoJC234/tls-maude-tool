package docker;

import de.rub.nds.tls.subject.TlsImplementationType;
import de.rub.nds.tls.subject.docker.DockerTlsClientInstance;
import de.rub.nds.tls.subject.docker.DockerTlsManagerFactory;
import de.rub.nds.tls.subject.docker.DockerTlsServerInstance;

import java.util.Map;
// Target Library에 대한 구체적인 상황을 지정하기가 쉽지 않음
// WolfSSL와 mbedTLS의 경우, supported groups를 지정할 수 없으며,
// 다른 gnuTLS, openSSL 등 다른 라이브러리들도 구체적인 상황을 지정하는데 어려움이 있음.
public class DockerRun {

    public Map<String, String> latestVersion = Map.of(
            "wolfssl", "5.8.0-stable",
            "openssl", "3.5.0"
    );

    public long run(String library, String arguments, boolean isClient){
        return 0;
    }

    public long connect(String library, String host, int targetPort, String additionalParameters){
        try {
            DockerTlsClientInstance instance = DockerTlsManagerFactory.getTlsClientBuilder(libraryType(library), latestVersion.get(library)).build();
            instance.start();
            instance.connect(host, targetPort, additionalParameters);
            return instance.getExitCode();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public long accept(String library, String arguments){
        return 0;
    }

    public long accept(String library, String host, int targetPort, String additionalParameters){
        try {
            DockerTlsServerInstance instance = DockerTlsManagerFactory.getTlsServerBuilder(libraryType(library), latestVersion.get(library))
                                                .hostname(host)
                                                .port(targetPort)
                                                .additionalParameters(additionalParameters)
                                                .build();
            instance.start();
            return instance.getExitCode();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private TlsImplementationType libraryType(String library){
        if (library.equals("wolfssl")){
            return TlsImplementationType.WOLFSSL;
        } else if (library.equals("openssl")){
            return TlsImplementationType.OPENSSL;
        }
        return null;
    }
}