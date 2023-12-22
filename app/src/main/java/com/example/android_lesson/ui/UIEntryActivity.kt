package com.example.android_lesson.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivityUiEntryBinding
import com.example.android_lesson.ui.chapter_3.ViewActivity
import com.example.android_lesson.ui.circleprogress.CircleViewSampleActivity
import com.example.android_lesson.ui.constraintlayout.ConstraintLayoutSampleActivity
import com.example.android_lesson.ui.custom.CustomViewSimpleActivity
import com.example.android_lesson.ui.dialog.DialogDemoActivity
import com.example.android_lesson.ui.dynamic.DynamicLayoutActivity
import com.example.android_lesson.ui.gridview.GridViewDemoActivity
import com.example.android_lesson.ui.listview.ListViewDemoActivity
import com.example.android_lesson.ui.multitype.normal.NormalActivity
import com.example.android_lesson.ui.recyclerview.RecyclerViewActivity
import com.example.android_lesson.ui.tablayout.TabLayoutDemoActivity
import com.example.android_lesson.ui.toolbar.ToolbarSimpleActivity

class UIEntryActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, UIEntryActivity::class.java)
            context.startActivity(intent)
        }
    }


    private lateinit var mBinding: ActivityUiEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUiEntryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnRecyclerView.setOnClickListener { RecyclerViewActivity.start(this) }
        mBinding.btnConstraintLayout.setOnClickListener { ConstraintLayoutSampleActivity.start(this) }
        mBinding.btnCircleView.setOnClickListener { CircleViewSampleActivity.start(this) }
        mBinding.btnCustomViewSimple.setOnClickListener { CustomViewSimpleActivity.start(this) }
        mBinding.btnView.setOnClickListener { ViewActivity.start(this) }

        findViewById<View>(R.id.toolbar_simple).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                ToolbarSimpleActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_list_view).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                ListViewDemoActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_grid_view).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                GridViewDemoActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_tab_layout).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                TabLayoutDemoActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_butter_knife).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                ButterKnifeTestActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_multi_type).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                NormalActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_dialog).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                DialogDemoActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_dynamic_layout).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                DynamicLayoutActivity::class.java
            )
            startActivity(intent)
        }

    }
}