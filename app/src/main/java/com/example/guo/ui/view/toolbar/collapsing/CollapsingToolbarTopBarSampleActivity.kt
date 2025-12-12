package com.example.guo.ui.view.toolbar.collapsing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.guo.R
import com.example.guo.databinding.ActivityCollapsingToolbarTopBarSampleBinding

/**
 * CollapsingToolbar实现吸顶效果
 */
class CollapsingToolbarTopBarSampleActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, CollapsingToolbarTopBarSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy {
        ActivityCollapsingToolbarTopBarSampleBinding.inflate(
            layoutInflater,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val view: View = binding.root
        val toolbar = view.findViewById<Toolbar?>(R.id.toolbar)
        val activity = this as AppCompatActivity
        activity.setSupportActionBar(toolbar)
    }
}
