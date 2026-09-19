# Skazka Media

> RU — основной язык · EN — required second language

## RU

Общие source-neutral компоненты отображения контента.

**Статус:** `0.1.0-preview`.

- `media-core` — `ContentKind` и выбор семейства renderer.
- `text-reader-core` — независимая модель/нормализация текстового документа.
- `image-viewer-core` — чистая геометрия масштабирования, pan, page-step и crop.
- Android View-слой текстовой читалки и image viewer пока остаётся в Skazka Hub и будет вынесен отдельно после разрыва зависимостей от `NativeTextReader` / `NativeReader`.
- AUDIO и VIDEO уже существуют как типы контента и маршрутизируются в MEDIA, но production playback engine пока не реализован. Эти модули считаются planned, а не готовыми.

Проверено на HOSTKEY: media self-test — PASS; Gradle `build` всех трёх модулей — PASS.

## EN

Shared source-neutral content presentation building blocks.

**Status:** `0.1.0-preview`.

- `media-core` — `ContentKind` and renderer-family routing.
- `text-reader-core` — independent text-document model and normalization.
- `image-viewer-core` — pure scaling, pan, page-step, and crop geometry.
- The Android View layer for the text reader and image viewer still lives in Skazka Hub and will be extracted after its `NativeTextReader` / `NativeReader` coupling is removed.
- AUDIO and VIDEO already exist as content types and route to MEDIA, but a production playback engine is not implemented yet. Those modules are planned, not claimed as complete.

Verified on HOSTKEY: media self-test — PASS; Gradle `build` for all three modules — PASS.

See [DEVELOPMENT_RULES.md](DEVELOPMENT_RULES.md).

> A license will be selected before the first stable public release. Until then, publication of the source does not grant reuse rights.
