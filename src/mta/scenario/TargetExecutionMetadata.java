package mta.scenario;

public record TargetExecutionMetadata(
        String libraryName,
        String libraryVersion,
        String libraryPath,
        String tlsRole,
        String executionMode,
        String runtimePlatform,
        String dockerImage,
        String buildProfile,
        String binaryPath) {

    private static final TargetExecutionMetadata EMPTY =
            new TargetExecutionMetadata("", "", "", "", "", "", "", "", "");

    public static TargetExecutionMetadata empty() {
        return EMPTY;
    }
}
