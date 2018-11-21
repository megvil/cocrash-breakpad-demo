#!/usr/bin/env bash

rm ./app/libs/cocrashlib.aar

chmod +x gradlew
./gradlew cocrashlib:clean
./gradlew cocrashlib:assembleRelease


mkdir -p release/libs
cp ./cocrashlib/build/outputs/aar/cocrashlib-release.aar ./app/libs/cocrashlib.aar
cp ./app/libs/cocrashlib.aar ./release/libs/cocrashlib.aar


./gradlew app:clean
./gradlew app:assembleRelease

cp -r app/build/outputs/apk/release/* ./release



