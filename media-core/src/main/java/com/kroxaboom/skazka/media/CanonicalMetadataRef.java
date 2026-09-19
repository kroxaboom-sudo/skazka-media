package com.kroxaboom.skazka.media;

import java.util.Locale;
import java.util.Objects;

/**
 * RU: Ссылка на каноническую запись внешнего провайдера метаданных.
 * EN: Reference to a canonical record from an external metadata provider.
 */
public record CanonicalMetadataRef(String provider, String externalId) {
    public CanonicalMetadataRef {
        provider = requireText(provider, "provider").toLowerCase(Locale.ROOT);
        externalId = requireText(externalId, "externalId");
    }

    public String key() {
        return provider + ":" + externalId;
    }

    private static String requireText(String value, String name) {
        Objects.requireNonNull(value, name);
        String result = value.trim();
        if (result.isEmpty()) {
            throw new IllegalArgumentException(name + " must not be blank");
        }
        return result;
    }
}
