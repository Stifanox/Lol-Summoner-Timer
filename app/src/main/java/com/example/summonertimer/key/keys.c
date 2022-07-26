//
// Created by Stifano on 26.07.2022.
//

#include <jni.h>



JNIEXPORT jstring JNICALL
Java_com_example_summonertimer_presentation_enemies_1summoners_1spells_utils_Wrapper_getKey(
        JNIEnv *env, jobject thiz) {
    return (*env) ->NewStringUTF(env,"");
}