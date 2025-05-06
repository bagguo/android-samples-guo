package com.example.android_lesson.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivityUiEntryBinding
import com.example.android_lesson.fragment.bottomsheet.StickyBottomSheetFragment
import com.example.android_lesson.fragment.bottomsheet.TabVP2BottomSheetDialogFragment
import com.example.android_lesson.ui.activity.ActivitySamplesActivity
import com.example.android_lesson.ui.bigscreen.BigScreenSamplesActivity
import com.example.android_lesson.ui.immersive.ImmersiveStatusBarActivity
import com.example.android_lesson.ui.chapter_3.ViewActivity
import com.example.android_lesson.ui.circleprogress.CircleViewSampleActivity
import com.example.android_lesson.ui.textview.spannable.SpannableSampleActivity
import com.example.android_lesson.ui.custom.CustomViewSimpleActivity
import com.example.android_lesson.ui.dialog.DialogSampleActivity
import com.example.android_lesson.ui.dynamic.DynamicLayoutActivity
import com.example.android_lesson.ui.gridview.GridViewDemoActivity
import com.example.android_lesson.ui.input.SoftInputActivity
import com.example.android_lesson.ui.input.SoftInputSampleActivity
import com.example.android_lesson.ui.listview.ListViewDemoActivity
import com.example.android_lesson.ui.loading.LoadingActivity
import com.example.android_lesson.ui.multitype.normal.NormalActivity
import com.example.android_lesson.ui.recyclerview.RecyclerViewEntryActivity
import com.example.android_lesson.ui.sidebar.SideBarActivity
import com.example.android_lesson.ui.tablayout.IndicatorActivity
import com.example.android_lesson.ui.tablayout.TabLayoutSimpleActivity
import com.example.android_lesson.ui.tablayout.customtab.TabLayoutDemoActivity
import com.example.android_lesson.ui.textview.TextViewSampleActivity
import com.example.android_lesson.ui.toast.ToastSampleActivity
import com.example.android_lesson.ui.toolbar.ToolbarSimpleActivity
import com.example.android_lesson.ui.video.VideoEntryActivity
import com.example.android_lesson.util.callSystemShare

class UIEntryActivity : AppCompatActivity() {
    companion object {

        private const val TAG = "UIEntryActivity"

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

        mBinding.initView()

    }

    private fun ActivityUiEntryBinding.initView() {
        btnActivity.setOnClickListener {
            ActivitySamplesActivity.launch(this@UIEntryActivity)
        }

        btnTabVp2BottomSheetFragment.setOnClickListener {
            TabVP2BottomSheetDialogFragment.newInstance()
                .show(supportFragmentManager, "TabVP2BottomSheetDialogFragment")
        }

        btnStickBottomSheetFragment.setOnClickListener {
            StickyBottomSheetFragment.newInstance()
                .show(supportFragmentManager, "StickyBottomSheetFragment")
        }
        btnRecyclerView.setOnClickListener { RecyclerViewEntryActivity.start(this@UIEntryActivity) }
        btnConstraintLayout.setOnClickListener { SpannableSampleActivity.start(this@UIEntryActivity) }
        btnCircleView.setOnClickListener { CircleViewSampleActivity.start(this@UIEntryActivity) }
        btnCustomViewSimple.setOnClickListener { CustomViewSimpleActivity.start(this@UIEntryActivity) }
        btnView.setOnClickListener { ViewActivity.start(this@UIEntryActivity) }

        findViewById<View>(R.id.toolbar_simple).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                ToolbarSimpleActivity::class.java
            )
            startActivityForResult(intent, 1)
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

        findViewById<View>(R.id.btn_tab_layout).setOnClickListener {
            TabLayoutSimpleActivity.start(this@UIEntryActivity)
        }

        findViewById<View>(R.id.btn_tab_layout_custom).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                TabLayoutDemoActivity::class.java
            )
            startActivity(intent)
        }

        findViewById<View>(R.id.btn_tab_indicator).setOnClickListener {
            IndicatorActivity.start(this@UIEntryActivity)
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
                DialogSampleActivity::class.java
            )
            startActivity(intent)
        }

        btnToast.setOnClickListener { ToastSampleActivity.launch(this@UIEntryActivity) }

        findViewById<View>(R.id.btn_dynamic_layout).setOnClickListener { view: View? ->
            val intent = Intent(
                this@UIEntryActivity,
                DynamicLayoutActivity::class.java
            )
            startActivity(intent)
        }

        btnTextView.setOnClickListener { TextViewSampleActivity.start(this@UIEntryActivity) }
        btnLoading.setOnClickListener { LoadingActivity.start(this@UIEntryActivity) }
        btnSideBar.setOnClickListener { SideBarActivity.start(this@UIEntryActivity) }
        btnSoftInput.setOnClickListener { SoftInputSampleActivity.start(this@UIEntryActivity) }
        btnSoftInput2.setOnClickListener { SoftInputActivity.launch(this@UIEntryActivity) }
        btnImmersiveStatusBar.setOnClickListener { ImmersiveStatusBarActivity.start(this@UIEntryActivity) }
        btnVideo.setOnClickListener { VideoEntryActivity.launch(this@UIEntryActivity) }


        btnSystemShare.setOnClickListener {
            "www.baidu.com".callSystemShare(this@UIEntryActivity)
        }

        btnBigScreen.setOnClickListener {
            BigScreenSamplesActivity.launch(this@UIEntryActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            finish()
            Log.d(TAG, "onActivityResult: ====")
        }
    }
}