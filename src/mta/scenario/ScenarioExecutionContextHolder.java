package mta.scenario;

public final class ScenarioExecutionContextHolder {

    private static final ThreadLocal<TargetExecutionMetadata> TARGET_METADATA = new ThreadLocal<>();

    private ScenarioExecutionContextHolder() {
    }

    public static void set(TargetExecutionMetadata metadata) {
        TARGET_METADATA.set(metadata == null ? TargetExecutionMetadata.empty() : metadata);
    }

    public static TargetExecutionMetadata currentOrEmpty() {
        TargetExecutionMetadata metadata = TARGET_METADATA.get();
        return metadata == null ? TargetExecutionMetadata.empty() : metadata;
    }

    public static void clear() {
        TARGET_METADATA.remove();
    }
}
