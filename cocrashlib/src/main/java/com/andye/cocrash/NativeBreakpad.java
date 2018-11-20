package com.andye.cocrash;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created on by yejiurui
 */

public class NativeBreakpad {
    private static String TAG = "NativeBreakpad";

    static boolean loadBreakpadSuccess = false;

    static {
        try {
            System.loadLibrary("cocrash");
            loadBreakpadSuccess = true;
        } catch (Exception e) {
            loadBreakpadSuccess = false;
            Log.e(TAG, "fail to load cocrash");
        }
    }

    /**
     * init breakpad
     *
     * @param dumpFileDir the directory of dump file
     * @return true: init success  false: init fail
     */
    public static boolean init(String dumpFileDir) {
        if (TextUtils.isEmpty(dumpFileDir)) {
            Log.e(TAG, "dumpFileDir can not be empty");
            return false;
        }
        if (loadBreakpadSuccess) {
            return NativeCoCrash.nativeInit(dumpFileDir) > 0;
        }
        return false;
    }



    /**
     * don't use this method in your production app!!
     */
    public static int testNativeCrash() {
        if (loadBreakpadSuccess) {
            Log.d(TAG, "test native crash .......................");
            return NativeCoCrash.nativeTestCrash();
        }
        return -1;
    }

}
