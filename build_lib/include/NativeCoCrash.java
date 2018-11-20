package com.andye.cocrash;

/**
 * Created on by yejiurui
 */

public class NativeCoCrash {

    public static native int nativeInit(String dumpFileDir);
    public static native int nativeTestCrash();


}
