#!/bin/sh

rm -rf src/main/libs
rm -rf src/main/obj

ndk-build NDK_PROJECT_PATH=src/main clean
ndk-build NDK_PROJECT_PATH=src/main

PLATFORM='unknown'
if [[ "$(uname)" == 'Darwin' ]]; then
   PLATFORM='osx'
else
   PLATFORM='unix'
fi

mkdir -p build/libs/$PLATFORM
cd build/libs/$PLATFORM
cmake ../../../
cd ../../../
cmake --build build/libs/$PLATFORM --target all
mkdir -p src/main/libs/$PLATFORM
cp -irfv build/libs/$PLATFORM/libpluma.* src/main/libs/