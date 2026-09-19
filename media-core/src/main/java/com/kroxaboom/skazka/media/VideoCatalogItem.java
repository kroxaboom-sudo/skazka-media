package com.kroxaboom.skazka.media;

import java.util.Objects;

/**
 * RU: Нормализованная видеозапись каталога до привязки к UI или базе данных.
 * EN: Normalized catalog video entry before binding it to a UI or database.
 */
public record VideoCatalogItem(
        String title,
        String originalTitle,
        Integer year,
        VideoKind kind,
        Integer season,
        Integer episode,
        CanonicalMetadataRef metadata
) {
    public VideoCatalogItem {
        title = requireText(title, "title");
        originalTitle = trimToNull(originalTitle);
        kind = kind == null ? VideoKind.UNKNOWN : kind;
        validateYear(year);
        validatePositive(season, "season");
        validatePositive(episode, "episode");

        if (kind != VideoKind.EPISODE && (season != null || episode != null)) {
            throw new IllegalArgumentException("season/episode are only valid for EPISODE");
        }
    }

    public String catalogKey() {
        if (metadata != null) {
            return metadata.key();
        }

        String normalized = title.toLowerCase(java.util.Locale.ROOT)
                .replaceAll("[^\\p{L}\\p{N}]+", " ")
                .trim()
                .replace(' ', '-');
        return year == null ? normalized : normalized + ":" + year;
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

    private static void validateYear(Integer year) {
        if (year != null && (year < 1888 || year > 2200)) {
            throw new IllegalArgumentException("year is outside the supported range");
        }
    }

    private static void validatePositive(Integer value, String name) {
        if (value != null && value < 1) {
            throw new IllegalArgumentException(name + " must be positive");
        }
    }
}
