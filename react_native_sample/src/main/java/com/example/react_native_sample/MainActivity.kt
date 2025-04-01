package com.example.react_native_sample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactApplication
import com.facebook.react.ReactInstanceManager


class MainActivity : AppCompatActivity() {


//    private val mReactInstanceManager: ReactInstanceManager by lazy {
//        (application as ReactApplication).reactNativeHost.reactInstanceManager
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mReactInstanceManager.createReactContextInBackground()

        findViewById<Button>(R.id.btn).setOnClickListener {
            ExtendReactActivity.launch(this@MainActivity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        mReactInstanceManager.onHostDestroy(this)
    }
}