package com.example.guo.communication.ipc.binder.bigbitmap;

import android.graphics.drawable.Drawable;
import android.os.Binder;

public class ImageBinder extends Binder {
    private final Drawable drawable;

    public ImageBinder(Drawable drawable) {
        this.drawable = drawable;
    }

    Drawable getDrawable() {
        return drawable;
    }
}
