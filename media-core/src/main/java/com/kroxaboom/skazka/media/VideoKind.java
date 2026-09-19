package com.kroxaboom.skazka.media;

import java.util.Locale;

/**
 * RU: Тип видеосущности в каталоге, не зависящий от конкретного источника.
 * EN: Source-neutral video entity kind used by the catalog.
 */
public enum VideoKind {
    MOVIE,
    SERIES,
    EPISODE,
    CLIP,
    UNKNOWN;

    public static VideoKind parse(String raw) {
        if (raw == null) {
            return UNKNOWN;
        }

        return switch (raw.trim().toLowerCase(Locale.ROOT).replace('-', '_')) {
            case "movie", "film" -> MOVIE;
            case "series", "show", "tv" -> SERIES;
            case "episode", "ep" -> EPISODE;
            case "clip", "trailer", "extra" -> CLIP;
            default -> UNKNOWN;
        };
    }
}
