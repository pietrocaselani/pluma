/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_flipstudio_pluma_Database */

#ifndef _Included_com_flipstudio_pluma_Database
#define _Included_com_flipstudio_pluma_Database
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jlong JNICALL Java_com_flipstudio_pluma_Database_open
(JNIEnv *, jclass, jstring, jint, jintArray, jobjectArray);

JNIEXPORT jlong JNICALL Java_com_flipstudio_pluma_Database_prepare
(JNIEnv *, jobject, jlong, jstring, jintArray);

JNIEXPORT jint JNICALL Java_com_flipstudio_pluma_Database_exec
(JNIEnv *, jobject, jlong, jstring, jobjectArray);

JNIEXPORT jint JNICALL Java_com_flipstudio_pluma_Database_close
(JNIEnv *, jobject, jlong);

JNIEXPORT jlong JNICALL Java_com_flipstudio_pluma_Database_lastInsertId
(JNIEnv *, jobject, jlong);

JNIEXPORT jstring JNICALL Java_com_flipstudio_pluma_Database_lastErrorMessage
(JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_flipstudio_pluma_Database_DatabaseListener */

#ifndef _Included_com_flipstudio_pluma_Database_DatabaseListener
#define _Included_com_flipstudio_pluma_Database_DatabaseListener
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
}
#endif
#endif
