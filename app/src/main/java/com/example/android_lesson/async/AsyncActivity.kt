package com.example.android_lesson.async

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.bagguo.lib_base.constan.RouterTable
import com.example.android_lesson.async.rxjava.RxjavaActivity
import com.example.android_lesson.databinding.ActivityAsyncBinding

@Route(path= RouterTable.Main.ASYNC)
class AsyncActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, AsyncActivity::class.java)
            context.startActivity(intent)
        }
    }

     private lateinit var binding:ActivityAsyncBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRxjava.setOnClickListener { RxjavaActivity.start(this) }
    }
}