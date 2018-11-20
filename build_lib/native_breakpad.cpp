#include "include/com_andye_cocrash_NativeCoCrash.h"
#include "include/common.h"
#include "google_breakpad/breakpad/src/client/linux/handler/exception_handler.h"
#include "google_breakpad/breakpad/src/client/linux/handler/minidump_descriptor.h"

JavaVM* g_jvm;

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    g_jvm = vm;
    JNIEnv *env;
    if (JNI_OK != vm->GetEnv(reinterpret_cast<void**> (&env),JNI_VERSION_1_4)) {
        LOGE("JNI_OnLoad yeyeye====> could not get JNI env");
        return JNI_ERR;
    }

    return JNI_VERSION_1_4;
}

bool DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
                  void* context,
                  bool succeeded){
  LOGE("DumpCallback yeyeye ===> succeeded %d", succeeded);
  return succeeded;
}

JNIEXPORT jint JNICALL Java_com_andye_cocrash_NativeCoCrash_nativeInit(JNIEnv* env,
                                                                                  jclass obj,
                                                                                  jstring crash_dump_path){
    const char* path = (char *)env->GetStringUTFChars(crash_dump_path, NULL);
    google_breakpad::MinidumpDescriptor descriptor(path);
    static google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback, NULL, true, -1);
    env->ReleaseStringUTFChars(crash_dump_path, path);
    LOGD("nativeInit yeyeye===> breakpad initialized succeeded, dump file will be saved at %s", path);
    return 0;
}

JNIEXPORT jint JNICALL Java_com_andye_cocrash_NativeCoCrash_nativeTestCrash
(JNIEnv* env, jclass obj) {
    LOGE("ye native crash capture begin");
    char *ptr = NULL; *ptr = 1;
    LOGE("ye native crash capture end");
    return 0;
}
