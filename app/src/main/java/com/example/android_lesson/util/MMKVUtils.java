package com.example.android_lesson.util;


import com.tencent.mmkv.MMKV;

public class MMKVUtils {

    private static final MMKV mKv = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null);

    public static boolean putInt(String key, int value) {
        return mKv.encode(key, value);
    }

    public static boolean putDouble(String key, double value) {
        return mKv.encode(key, value);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, Integer defaultValue) {
        return mKv.decodeInt(key, defaultValue);
    }

    public static boolean putString(String key, String value) {
        return mKv.encode(key, value);
    }

    public static boolean putByte(String key, byte[] value) {
        return mKv.encode(key, value);
    }

    public static byte[] getByte(String key) {
        return mKv.decodeBytes(key);
    }

    public static String getString(String key) {
        return mKv.decodeString(key);
    }

    public static String getString(String key, String defaultValue) {
        return mKv.decodeString(key, defaultValue);
    }

    public static Double getDouble(String key) {
        return mKv.decodeDouble(key);
    }


    public static boolean putBoolean(String key, boolean value) {
        return mKv.encode(key, value);
    }

    public static boolean getBoolean(String key, boolean defaultV) {
        return mKv.decodeBool(key, defaultV);
    }

    public static long getLong(String key, long defaultValue) {
        return mKv.decodeLong(key, defaultValue);
    }

    public static boolean putLong(String key, long value) {
        return mKv.encode(key, value);
    }

    public static boolean putFloat(String key, float value) {
        return mKv.encode(key, value);
    }

    public static float getFloat(String key, float defaultValue) {
        return mKv.decodeFloat(key, defaultValue);
    }

    public static boolean clear(String key) {
        return mKv.encode(key, "");
    }

}
