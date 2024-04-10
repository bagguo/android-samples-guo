package com.example.android_lesson.ui.recyclerview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_lesson.databinding.ActivityRecyclerViewEntryBinding
import com.example.android_lesson.ui.recyclerview.expandable.ExpandableUseActivity
import com.example.android_lesson.ui.recyclerview.headerfooter.HeaderAndFooterUseActivity
import com.example.android_lesson.ui.recyclerview.general.RecyclerViewActivity

class RecyclerViewEntryActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, RecyclerViewEntryActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivityRecyclerViewEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRecyclerViewEntryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.ryBtn.setOnClickListener { RecyclerViewActivity.start(this) }
        mBinding.expandableRyBtn.setOnClickListener { ExpandableUseActivity.start(this) }
        mBinding.headerFooterRyBtn.setOnClickListener { HeaderAndFooterUseActivity.start(this) }

    }
}