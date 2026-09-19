import com.kroxaboom.skazka.media.CanonicalMetadataRef;
import com.kroxaboom.skazka.media.ContentKind;
import com.kroxaboom.skazka.media.ContentRouter;
import com.kroxaboom.skazka.media.MediaSourceRef;
import com.kroxaboom.skazka.media.RendererKind;
import com.kroxaboom.skazka.media.VideoCatalogItem;
import com.kroxaboom.skazka.media.VideoKind;
import com.kroxaboom.skazka.media.image.ImageLayout;
import com.kroxaboom.skazka.media.text.TextDocument;

public final class MediaCoreSelfTest {
    public static void main(String[] args) {
        check(ContentKind.parse("webtoon") == ContentKind.IMAGE, "webtoon kind");
        check(ContentKind.parse("audio_book") == ContentKind.AUDIO, "audio kind");
        check(ContentRouter.rendererFor(ContentKind.VIDEO) == RendererKind.MEDIA, "video route");
        check(ContentRouter.rendererFor(ContentKind.UNKNOWN) == RendererKind.UNSUPPORTED, "unknown route");

        CanonicalMetadataRef metadata = new CanonicalMetadataRef("TMDB", "157336");
        VideoCatalogItem movie = new VideoCatalogItem(
                "Интерстеллар",
                "Interstellar",
                2014,
                VideoKind.MOVIE,
                null,
                null,
                metadata
        );
        check("tmdb:157336".equals(movie.catalogKey()), "canonical catalog key");

        MediaSourceRef source = new MediaSourceRef(
                "telegram",
                "-1001000000001",
                "42",
                "AQAD-stable-file-id",
                1_000_000L,
                "video/mp4"
        );
        MediaSourceRef vaultCopy = new MediaSourceRef(
                "telegram",
                "-1002000000002",
                "7",
                "AQAD-stable-file-id",
                1_000_000L,
                "video/mp4"
        );
        check(source.dedupKey().equals(vaultCopy.dedupKey()), "stable media dedup");
        check(VideoKind.parse("episode") == VideoKind.EPISODE, "episode kind");

        TextDocument text = TextDocument.plain(
                "1",
                "Chapter",
                "line 1  \r\n\r\n\r\nline 2"
        );
        check("line 1\n\nline 2".equals(text.body()), "plain text normalization");

        check(ImageLayout.baseScale(1000, 2000, 500, 1000, true, true) == 0.5f, "fit scale");
        check(ImageLayout.pageStep(20, 100, false) == -1, "LTR page step");
        check(ImageLayout.pageStep(20, 100, true) == 1, "RTL page step");
        check(ImageLayout.croppedSize(1000, true) < 1000, "crop helper");

        System.out.println("PASS: Skazka Media content/text/image/video core");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
