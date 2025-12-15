package com.example.guo.ui.view.topbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.guo.R
import com.example.guo.databinding.ActivityTopbarSamplesBinding

class TopbarSamplesActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "TopbarSamplesActivity"

        fun launch(context: Context) {
            val intent = Intent(context, TopbarSamplesActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityTopbarSamplesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTopbarSamplesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.initListener()
    }

    private fun ActivityTopbarSamplesBinding.initListener() {
        btnSimple.setOnClickListener {
            TopBarSimpleActivity.launch(this@TopbarSamplesActivity)
        }

        btnScrollingViewsAS.setOnClickListener {
            ScrollingViewsASSampleActivity.launch(this@TopbarSamplesActivity)
        }

        btnCollapsingPined.setOnClickListener {
            CollapsingPinedSampleActivity.launch(this@TopbarSamplesActivity)
        }
    }
}
