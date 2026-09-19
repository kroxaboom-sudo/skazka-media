package com.kroxaboom.skazka.media;

/**
 * RU: Выбирает семейство renderer без знания Android View или конкретного источника.
 * EN: Chooses a renderer family without knowing Android Views or concrete sources.
 */
public final class ContentRouter {
    private ContentRouter() {}

    public static RendererKind rendererFor(ContentKind kind) {
        if (kind == null) {
            return RendererKind.UNSUPPORTED;
        }

        return switch (kind) {
            case TEXT -> RendererKind.TEXT;
            case IMAGE -> RendererKind.IMAGE;
            case AUDIO, VIDEO -> RendererKind.MEDIA;
            case MIXED -> RendererKind.MIXED;
            case UNKNOWN -> RendererKind.UNSUPPORTED;
        };
    }
}
