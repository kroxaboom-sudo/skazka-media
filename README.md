# Skazka Media

**RU:** Набор независимых движков просмотра и воспроизведения контента.

**EN:** Independent content viewing and playback engines.

## Модули / Modules

- video — видеоплеер;
- audio — аудиоплеер;
- text — текстовая читалка;
- image — просмотрщик изображений и комиксов.

## Почему один репозиторий / Why one repository

У этих модулей разные задачи, поэтому они не смешиваются в один большой player. Общий репозиторий нужен только для согласованных contracts, общих тестовых утилит и интеграции с Download, Data и Screen Awake.

Each engine stays independent. The shared repository exists for common contracts, test helpers and integration points rather than for turning everything into one player.

## Граница / Boundary

UI конкретного приложения, source-specific логика и private endpoints сюда не входят.
