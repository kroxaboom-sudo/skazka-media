package com.kroxaboom.skazka.media.text;

/**
 * RU: Самодостаточный текстовый документ без сети и исполняемого markup.
 * EN: Self-contained text document with no network or executable markup.
 */
public record TextDocument(
        String id,
        String title,
        String body,
        Format format
) {
    public TextDocument {
        id = id == null ? "" : id;
        title = title == null ? "" : title;
        body = body == null ? "" : body;
        format = format == null ? Format.PLAIN : format;
    }

    public static TextDocument plain(String id, String title, String body) {
        return new TextDocument(id, title, normalizePlain(body), Format.PLAIN);
    }

    public static TextDocument html(String id, String title, String html) {
        return new TextDocument(id, title, html == null ? "" : html.trim(), Format.HTML);
    }

    public boolean isEmpty() {
        return body.trim().isEmpty();
    }

    public int length() {
        return body.length();
    }

    public static String normalizePlain(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }

        return value
                .replace("\r\n", "\n")
                .replace('\r', '\n')
                .replaceAll("[ \\t]+\\n", "\n")
                .replaceAll("\\n{3,}", "\n\n")
                .trim();
    }

    public enum Format {
        PLAIN,
        HTML
    }
}
