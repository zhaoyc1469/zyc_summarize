package com.mylibrary.magiccamera.utils;

public class NativeTest {

    static {
        System.loadLibrary("native-lib");
    }


    public native String stringFromJNI();
}
