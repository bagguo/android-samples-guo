package com.example.guo.ui.customview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guo.R

class CustomViewSimpleActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, CustomViewSimpleActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_simple)

        val button = Button(this)
        button.text = "动态添加button"
        addContentView(button, android.view.ViewGroup.LayoutParams(200, 200))
    }
}