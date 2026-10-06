package mta.scenario.session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import mta.maude.constant.OcspResponseSpec;

/** Resolves a symbolic OCSP response case to its exact, reproducible DER bytes. */
final class OcspResponseFixtureResolver {
    private static final int MAX_RESPONSE_LENGTH = 0xffff - 8;

    private OcspResponseFixtureResolver() {}

    static byte[] load(String fixtureDirectory, OcspResponseSpec spec) {
        if (spec == null) {
            throw new IllegalArgumentException("OCSP response specification is required");
        }
        if (fixtureDirectory == null || fixtureDirectory.isBlank()) {
            throw new IllegalArgumentException(
                    "TLSProfiles tester OCSPResponseFixtureDirectory is required for c[<fixture-id>] cases");
        }

        Path directory = Path.of(fixtureDirectory).toAbsolutePath().normalize();
        Path fixture = directory.resolve(spec.fixtureId() + ".der");
        if (!Files.isRegularFile(fixture)) {
            throw new IllegalArgumentException("OCSP response fixture not found: " + fixture);
        }
        try {
            long size = Files.size(fixture);
            if (size == 0 || size > MAX_RESPONSE_LENGTH) {
                throw new IllegalArgumentException(
                        "OCSP response fixture must contain 1.." + MAX_RESPONSE_LENGTH
                                + " bytes: " + fixture);
            }
            return Files.readAllBytes(fixture);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read OCSP response fixture: " + fixture, e);
        }
    }
}
