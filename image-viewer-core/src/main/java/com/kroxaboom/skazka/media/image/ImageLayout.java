package com.kroxaboom.skazka.media.image;

/**
 * RU: Чистая геометрия image viewer без зависимости от Android UI.
 * EN: Pure image-viewer geometry with no Android UI dependency.
 */
public final class ImageLayout {
    private ImageLayout() {}

    public static float baseScale(
            int imageWidth,
            int imageHeight,
            int viewWidth,
            int viewHeight,
            boolean paged,
            boolean fitScreen
    ) {
        if (imageWidth <= 0 || imageHeight <= 0 || viewWidth <= 0) {
            return 1f;
        }

        float widthScale = (float) viewWidth / imageWidth;
        if (!paged || !fitScreen || viewHeight <= 0) {
            return widthScale;
        }
        return Math.min(widthScale, (float) viewHeight / imageHeight);
    }

    public static float displayWidth(
            int imageWidth,
            int imageHeight,
            int viewWidth,
            int viewHeight,
            boolean paged,
            boolean fitScreen,
            float zoom
    ) {
        return imageWidth
                * baseScale(imageWidth, imageHeight, viewWidth, viewHeight, paged, fitScreen)
                * Math.max(1f, zoom);
    }

    public static float displayHeight(
            int imageWidth,
            int imageHeight,
            int viewWidth,
            int viewHeight,
            boolean paged,
            boolean fitScreen,
            float zoom
    ) {
        return imageHeight
                * baseScale(imageWidth, imageHeight, viewWidth, viewHeight, paged, fitScreen)
                * Math.max(1f, zoom);
    }

    public static float maxPan(float displayWidth, int viewWidth) {
        return Math.max(0, displayWidth - Math.max(0, viewWidth));
    }

    public static float left(float displayWidth, int viewWidth, float pan) {
        if (displayWidth <= viewWidth) {
            return Math.max(0, (viewWidth - displayWidth) / 2f);
        }
        return -Math.max(0, Math.min(maxPan(displayWidth, viewWidth), pan));
    }

    public static int pageStep(float startX, float endX, boolean rtl) {
        if (endX == startX) {
            return 0;
        }
        boolean swipeRight = endX > startX;
        return swipeRight == rtl ? 1 : -1;
    }

    public static float panForZoom(
            float focusX,
            float oldLeft,
            float oldWidth,
            float newWidth,
            int viewWidth
    ) {
        if (oldWidth <= 0 || newWidth <= viewWidth) {
            return 0;
        }

        float relative = Math.max(0, Math.min(1, (focusX - oldLeft) / oldWidth));
        float desiredLeft = focusX - relative * newWidth;
        return Math.max(0, Math.min(maxPan(newWidth, viewWidth), -desiredLeft));
    }

    public static int cropInset(int sourceSize, boolean enabled) {
        if (!enabled || sourceSize <= 2) {
            return 0;
        }
        return Math.min((sourceSize - 1) / 2, Math.max(1, Math.round(sourceSize * 0.02f)));
    }

    public static int croppedSize(int sourceSize, boolean enabled) {
        int safe = Math.max(1, sourceSize);
        int inset = cropInset(safe, enabled);
        return Math.max(1, safe - inset * 2);
    }
}
