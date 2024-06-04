package com.example.android_lesson.util;


import com.example.android_lesson.App;
import com.tencent.mmkv.MMKV;

public class MMKVUtils {

    private static volatile MMKVUtils mInstance;
    private final MMKV kv;

    private MMKVUtils() {
        // /data/user/0/package/files/mmkv
        MMKV.initialize(App.mContext);
        kv = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null);
    }

    public static MMKVUtils getInstance() {
        if (mInstance == null) {
            synchronized (MMKVUtils.class) {
                if (mInstance == null) {
                    mInstance = new MMKVUtils();
                }
            }
        }
        return mInstance;
    }


    public void putInt(String key, int value) {
        kv.encode(key, value);
    }

    public void putDouble(String key, double value) {
        kv.encode(key, value);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, Integer defaultValue) {
        return kv.decodeInt(key, defaultValue);
    }

    public void putString(String key, String value) {
        kv.encode(key, value);
    }

    public void putByte(String key, byte[] value) {
        kv.encode(key, value);
    }

    public byte[] getByte(String key) {
        return kv.decodeBytes(key);
    }

    public String getString(String key) {
        return kv.decodeString(key);
    }

    public String getString(String key, String defaultValue) {
        return kv.decodeString(key, defaultValue);
    }

    public Double getDouble(String key) {
        return kv.decodeDouble(key);
    }


    public void putBoolean(String key, boolean value) {
        kv.encode(key, value);
    }

    public boolean getBoolean(String key, boolean defaultV) {
        return kv.decodeBool(key, defaultV);
    }

    public long getLong(String key, long defaultValue) {
        return kv.decodeLong(key, defaultValue);
    }

    public void putLong(String key, long value) {
        kv.encode(key, value);
    }

    public void putFloat(String key, float value) {
        kv.encode(key, value);
    }

    public float getFloat(String key, float defaultValue) {
        return kv.decodeFloat(key, defaultValue);
    }

    public void clear(String key) {
        kv.encode(key, "");
    }

}
