package com.example.ui.multitype.normal;

import androidx.annotation.NonNull;

import com.example.ui.multitype.Savable;
import com.google.gson.Gson;

import java.nio.charset.Charset;

public class TextItem implements Savable {
    public String text;

    public TextItem(@NonNull String text) {
        this.text = text;
    }

    public TextItem(@NonNull byte[] data) {
        init(data);
    }

    @Override
    public void init(@NonNull byte[] data) {
        String json = new String(data, UTF_8);
        this.text = new Gson().fromJson(json, TextItem.class).text;
    }

    @NonNull
    @Override
    public byte[] toBytes() {
        return new Gson().toJson(this).getBytes(UTF_8);
    }

    @NonNull
    @Override
    public String describe() {
        return "Text";
    }

    private static final Charset UTF_8 = Charset.forName("UTF-8");

}
