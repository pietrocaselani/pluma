cmake_minimum_required(VERSION 3.2)
project(pluma)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(CMAKE_BUILD_TYPE Release)

set(SQLITE_PATH ${CMAKE_CURRENT_SOURCE_DIR}/../SQLite)

if(APPLE)
    if(TARGET pluma_static)
    	link_libraries(${SQLITE_PATH}/libs/osx/libfsqlite.a)
    else()
    	link_libraries(${SQLITE_PATH}/libs/osx/libfsqlite.dylib)
    endif()

elseif(UNIX)
    if(TARGET pluma_static)
    	link_libraries(${SQLITE_PATH}/libs/unix/libfsqlite.a)
    else()
    	link_libraries(${SQLITE_PATH}/libs/unix/libfsqlite.so)
	endif()
else()
    link_libraries(${SQLITE_PATH}/libs/win32/libfsqlite.dll)
endif()

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
include_directories(${SQLITE_PATH}/jni)

set(SOURCE_FILES
    src/main/jni/database.cpp
    src/main/jni/database.h
    src/main/jni/statement.cpp
    src/main/jni/statement.h
    src/main/jni/JSQLiteFunction.cpp
    src/main/jni/JSQLiteFunction.h
    src/main/jni/SQLiteFunction.cpp
    src/main/jni/SQLiteFunction.h
    src/main/jni/Utils.cpp
    src/main/jni/Utils.h
    src/main/jni/character_tokenizer.cpp
    src/main/jni/character_tokenizer.h
    src/main/jni/fts3_tokenizer.h)

add_library(pluma_shared SHARED ${SOURCE_FILES})
add_library(pluma_static STATIC ${SOURCE_FILES})

set_target_properties(pluma_shared PROPERTIES OUTPUT_NAME pluma)
set_target_properties(pluma_static PROPERTIES OUTPUT_NAME pluma)