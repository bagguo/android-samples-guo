package com.example.guo.ui.view.loading

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityLoadingBinding

class LoadingActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoadingActivity::class.java)
            context.startActivity(intent)
        }
    }

    val binding: ActivityLoadingBinding by lazy {
        ActivityLoadingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            loadingBtn.isEnabled = true
            loadingBtn.setOnClickListener {
                loadingBtn.isEnabled = false
                loadingBtn.setLoading(false)
            }
        }
    }
}