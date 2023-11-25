package com.example.android_lesson.ui.multitype;

import androidx.annotation.NonNull;

public interface Savable {
    void init(@NonNull byte[] data);
    @NonNull byte[] toBytes();
    @NonNull String describe();
}
