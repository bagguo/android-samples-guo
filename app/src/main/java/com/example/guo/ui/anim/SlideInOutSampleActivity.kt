package com.example.guo.ui.anim

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R
import com.example.guo.databinding.ActivitySlideInOutSampleBinding

class SlideInOutSampleActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, SlideInOutSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivitySlideInOutSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, AFragment())
                .commit()
        }
    }
}
