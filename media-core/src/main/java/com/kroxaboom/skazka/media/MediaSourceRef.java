package com.kroxaboom.skazka.media;

import java.util.Locale;
import java.util.Objects;

/**
 * RU:
 * Указывает на конкретный медиаобъект у источника. stableMediaId используется для
 * дедупликации, когда провайдер гарантирует стабильный идентификатор файла.
 *
 * EN:
 * Points to a concrete media object at a source. stableMediaId is used for
 * deduplication when the provider guarantees a stable file identifier.
 */
public record MediaSourceRef(
        String provider,
        String containerId,
        String itemId,
        String stableMediaId,
        long sizeBytes,
        String mimeType
) {
    public MediaSourceRef {
        provider = requireText(provider, "provider").toLowerCase(Locale.ROOT);
        containerId = requireText(containerId, "containerId");
        itemId = requireText(itemId, "itemId");
        stableMediaId = trimToNull(stableMediaId);
        mimeType = trimToNull(mimeType);
        if (sizeBytes < -1) {
            throw new IllegalArgumentException("sizeBytes must be -1 or greater");
        }
    }

    /**
     * RU: Стабильный ID файла предпочтительнее координат сообщения.
     * EN: Prefer a stable file ID over message coordinates when available.
     */
    public String dedupKey() {
        if (stableMediaId != null) {
            return provider + ":media:" + stableMediaId;
        }
        return provider + ":item:" + containerId + ":" + itemId;
    }

    private static String requireText(String value, String name) {
        Objects.requireNonNull(value, name);
        String result = value.trim();
        if (result.isEmpty()) {
            throw new IllegalArgumentException(name + " must not be blank");
        }
        return result;
    }

    private static String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String result = value.trim();
        return result.isEmpty() ? null : result;
    }
}
