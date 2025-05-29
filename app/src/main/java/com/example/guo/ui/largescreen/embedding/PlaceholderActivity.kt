package com.example.guo.ui.largescreen.embedding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityPlaceHolderBinding

class PlaceholderActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, PlaceholderActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivityPlaceHolderBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

    }
}