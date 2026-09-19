package com.kroxaboom.skazka.media;

import java.util.Locale;

/**
 * RU: Стабильные source-neutral семейства контента.
 * EN: Stable source-neutral content families.
 */
public enum ContentKind {
    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    MIXED,
    UNKNOWN;

    public static ContentKind parse(String raw) {
        if (raw == null) {
            return UNKNOWN;
        }

        String value = raw.trim().toLowerCase(Locale.ROOT).replace('-', '_');
        return switch (value) {
            case "text", "html", "article", "book", "epub" -> TEXT;
            case "image", "images", "page", "pages", "comic", "manga", "webtoon", "cbz", "cbr" -> IMAGE;
            case "audio", "sound", "audiobook", "audio_book" -> AUDIO;
            case "video", "movie", "episode" -> VIDEO;
            case "mixed", "hybrid" -> MIXED;
            default -> UNKNOWN;
        };
    }
}
