package mta.maude.constant;

/** Symbolic name of an OCSP response fixture; the DER bytes are not part of the Maude term. */
public enum OcspResponseSpec {
    GOOD_LEAF("good-leaf"),
    VALID_FOR_LEAF("valid-for-leaf"),
    WRONG_CERTIFICATE("wrong-certificate"),
    MALFORMED("malformed");

    private final String fixtureId;

    OcspResponseSpec(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public String fixtureId() {
        return fixtureId;
    }

    public static OcspResponseSpec fromFixtureId(String fixtureId) {
        for (OcspResponseSpec spec : values()) {
            if (spec.fixtureId.equals(fixtureId)) {
                return spec;
            }
        }
        throw new IllegalArgumentException("Unknown OCSP response fixture ID: " + fixtureId);
    }
}
