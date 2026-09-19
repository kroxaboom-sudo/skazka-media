#!/usr/bin/env bash
set -euo pipefail

rm -rf build/self-test
mkdir -p build/self-test

javac -encoding UTF-8 -d build/self-test \
  media-core/src/main/java/com/kroxaboom/skazka/media/*.java \
  text-reader-core/src/main/java/com/kroxaboom/skazka/media/text/*.java \
  image-viewer-core/src/main/java/com/kroxaboom/skazka/media/image/*.java \
  tests/MediaCoreSelfTest.java

java -cp build/self-test MediaCoreSelfTest
