package com.camera.magiccamera.utils;

public class NativeTest {

    static {
        System.loadLibrary("native-lib");
    }


    public native String stringFromJNI();
}
