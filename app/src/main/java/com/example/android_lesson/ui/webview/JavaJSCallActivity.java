package com.example.android_lesson.ui.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.android_lesson.R;

public class JavaJSCallActivity extends AppCompatActivity {
    public static void start(Context context){
        Intent intent = new Intent(context, JavaJSCallActivity.class);
        context.startActivity(intent);
    }

    private WebView webview;
    private TextView tvJs;
    private TextView tvJsArgs;
    private TextView tvknoJsArgs;
    private TextView tvShowmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_jscall);

        setWebview();
        initView();
    }

    private void initView() {
        tvJs = (TextView) findViewById(R.id.tv_androidcalljs);
        tvJsArgs = (TextView) findViewById(R.id.tv_androidcalljsargs);
        tvknoJsArgs = (TextView) findViewById(R.id.tv_knoandroidcalljsargs);
        tvShowmsg = (TextView) findViewById(R.id.tv_showmsg);

        tvJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:javacalljs()");
            }
        });

        tvJsArgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:javacalljswith(" + "'Android传过来的参数'" + ")");
            }
        });

        tvknoJsArgs.setOnClickListener(v ->
                webview.loadUrl("javascript:knojavacalljswith(" + "'kno Android传过来的参数'" + ")")
        );
    }

    private void setWebview() {
        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //与js交互必须设置
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/html.html");
        webview.addJavascriptInterface(JavaJSCallActivity.this,"android");
    }

    @JavascriptInterface
    public void jsCallAndroid(){
        tvShowmsg.setText("Js调用Android方法");
    }

    @JavascriptInterface
    public void jsCallAndroidArgs(String args){
        tvShowmsg.setText(args);
    }
}