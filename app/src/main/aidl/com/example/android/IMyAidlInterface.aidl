// IMyAidlInterface.aidl
package com.example.android;

import com.example.android.AidlCallBackInterface;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            void callAidl(String text, AidlCallBackInterface callBack);
}





