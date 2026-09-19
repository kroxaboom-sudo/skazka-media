# Skazka Media

> RU — основной язык · EN — required second language

## RU

Общие source-neutral компоненты отображения и нормализации контента.

**Статус:** `0.1.0-preview`.

- `media-core` — `ContentKind`, выбор семейства renderer и базовые video-catalog primitives.
- Video catalog primitives: `VideoKind`, `VideoCatalogItem`, `CanonicalMetadataRef`, `MediaSourceRef`.
- `MediaSourceRef.dedupKey()` предпочитает стабильный ID медиа и позволяет не размножать одну Telegram-медиа после переноса между source/Vault.
- `text-reader-core` — независимая модель/нормализация текстового документа.
- `image-viewer-core` — чистая геометрия масштабирования, pan, page-step и crop.
- Android View-слой текстовой читалки и image viewer пока остаётся в Skazka Hub и будет вынесен отдельно после разрыва зависимостей от `NativeTextReader` / `NativeReader`.
- AUDIO и VIDEO маршрутизируются в MEDIA. Модель видеокаталога уже начата, но production playback engine пока не реализован.

Проверено на HOSTKEY: media self-test — PASS.

## EN

Shared source-neutral content presentation and normalization building blocks.

**Status:** `0.1.0-preview`.

- `media-core` — `ContentKind`, renderer-family routing, and basic video-catalog primitives.
- Video catalog primitives: `VideoKind`, `VideoCatalogItem`, `CanonicalMetadataRef`, `MediaSourceRef`.
- `MediaSourceRef.dedupKey()` prefers a stable media ID so the same Telegram media is not duplicated after moving between a source and the Vault.
- `text-reader-core` — independent text-document model and normalization.
- `image-viewer-core` — pure scaling, pan, page-step, and crop geometry.
- The Android View layer for the text reader and image viewer still lives in Skazka Hub and will be extracted after its `NativeTextReader` / `NativeReader` coupling is removed.
- AUDIO and VIDEO route to MEDIA. The video catalog model has started, but a production playback engine is not implemented yet.

Verified on HOSTKEY: media self-test — PASS.

See [DEVELOPMENT_RULES.md](DEVELOPMENT_RULES.md).

> A license will be selected before the first stable public release. Until then, publication of the source does not grant reuse rights.
