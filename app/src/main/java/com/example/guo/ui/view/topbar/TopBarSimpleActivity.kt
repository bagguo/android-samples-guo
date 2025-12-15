package com.example.guo.ui.view.topbar

import android.animation.ArgbEvaluator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.guo.R
import com.example.guo.databinding.ActivityTopBarSimpleBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.math.abs

class TopBarSimpleActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, TopBarSimpleActivity::class.java)
            context.startActivity(intent)
        }
    }
    private lateinit var binding: ActivityTopBarSimpleBinding

    private lateinit var appBar: AppBarLayout
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar

    private val startColor = Color.TRANSPARENT
    private val endColor by lazy { getColor(R.color.white) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        binding = ActivityTopBarSimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appBar = findViewById(R.id.appBarLayout)
        collapsingToolbar = findViewById(R.id.collapsingToolbar)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        setupScrollAnimation()

//        val toolbar = findViewById<Toolbar?>(R.id.toolbar)
//        val activity = this as AppCompatActivity
//        activity.setSupportActionBar(toolbar)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.subtitle = "subtitle"
    }

    private fun setupScrollAnimation() {
        appBar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { layout, offset ->

                val total = layout.totalScrollRange
                val fraction = abs(offset).toFloat() / total

                // 颜色渐变
                val color = ArgbEvaluator().evaluate(fraction, startColor, endColor) as Int

                // 状态栏颜色渐变
                window.statusBarColor = color

                // CollapsingScrim 渐变
                collapsingToolbar.setContentScrimColor(color)
                collapsingToolbar.setStatusBarScrimColor(color)

                // Toolbar 背景渐变
                toolbar.setBackgroundColor(color)
            },
        )
    }
}
