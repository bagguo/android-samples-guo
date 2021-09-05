package com.example.android_lesson.rxjava;

import android.app.ProgressDialog;
import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NetObserver implements Observer {

    private Context context;
    private ProgressDialog progressDialog;

    public NetObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
    }

    @Override
    public void onNext(Object o) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onComplete() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
