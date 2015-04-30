/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_flipstudio_pluma_SQLiteFunction */

#ifndef _Included_com_flipstudio_pluma_SQLiteFunction
#define _Included_com_flipstudio_pluma_SQLiteFunction
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT jlong JNICALL Java_com_flipstudio_pluma_SQLiteFunction_init
		(JNIEnv*, jobject);

JNIEXPORT void JNICALL Java_com_flipstudio_pluma_SQLiteFunction_setErrorResult
		(JNIEnv*, jobject, jlong, jstring, jint);

JNIEXPORT void JNICALL Java_com_flipstudio_pluma_SQLiteFunction_setDoubleResult
		(JNIEnv*, jobject, jlong, jdouble);

JNIEXPORT void JNICALL Java_com_flipstudio_pluma_SQLiteFunction_setIntResult
		(JNIEnv*, jobject, jlong, jint);

JNIEXPORT void JNICALL Java_com_flipstudio_pluma_SQLiteFunction_setLongResult
		(JNIEnv*, jobject, jlong, jlong);

JNIEXPORT void JNICALL Java_com_flipstudio_pluma_SQLiteFunction_setStringResult
		(JNIEnv*, jobject, jlong, jstring);

JNIEXPORT void JNICALL Java_com_flipstudio_pluma_SQLiteFunction_setNullResult
		(JNIEnv*, jobject, jlong);

JNIEXPORT jdouble JNICALL Java_com_flipstudio_pluma_SQLiteFunction_getDoubleArg
		(JNIEnv*, jobject, jlong, jint);

JNIEXPORT jint JNICALL Java_com_flipstudio_pluma_SQLiteFunction_getIntArg
		(JNIEnv*, jobject, jlong, jint);

JNIEXPORT jlong JNICALL Java_com_flipstudio_pluma_SQLiteFunction_getLongArg
		(JNIEnv*, jobject, jlong, jint);

JNIEXPORT jstring JNICALL Java_com_flipstudio_pluma_SQLiteFunction_getStringArg
		(JNIEnv*, jobject, jlong, jint);

JNIEXPORT void JNICALL Java_com_flipstudio_pluma_SQLiteFunction_dispose
		(JNIEnv*, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif