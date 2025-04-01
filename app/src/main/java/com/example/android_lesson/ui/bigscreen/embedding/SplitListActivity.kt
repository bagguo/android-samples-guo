package com.example.android_lesson.ui.bigscreen.embedding

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Consumer
import androidx.window.embedding.SplitController
import androidx.window.embedding.SplitInfo
import com.example.android_lesson.databinding.ActivitySplitListBinding

class SplitListActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, SplitListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivitySplitListBinding.inflate(layoutInflater) }

//    private lateinit var splitController: SplitController

    val splitChangeListener = SplitStateChangeListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rootSplitActivityLayout.setBackgroundColor(Color.parseColor("#e0f7fa"))

//        splitController = SplitController.getInstance(this)

        binding.initListener()
    }

    private fun ActivitySplitListBinding.initListener() {
        infoButton.setOnClickListener {
            // SplitListActivity启动的activity默认在副容器
            SplitListActivityStartedActivity.launch(this@SplitListActivity)
        }
    }

    fun onItemClick(view: View) {
        val text = (view as TextView).text
        DetailActivity.launch(this)
    }

    override fun onStart() {
        super.onStart()
//        splitController.splitInfoList()
    }

    inner class SplitStateChangeListener : Consumer<List<SplitInfo>> {
        override fun accept(newSplitInfos: List<SplitInfo>) {
            binding.infoButton.visibility = if (newSplitInfos.isEmpty()) View.VISIBLE else View.GONE
        }

    }
}