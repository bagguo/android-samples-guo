package com.example.android_lesson.ui.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.databinding.ActivityFragmentSamplesBinding
import com.example.android_lesson.ui.fragment.dialog.FullScreenSampleDialogFragment

class FragmentSamplesActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFragmentSamplesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.initListener()
    }

    private fun ActivityFragmentSamplesBinding.initListener() {
        btnFullScreenDialogFgm.setOnClickListener {
            FullScreenSampleDialogFragment
                .newInstance()
                .show(supportFragmentManager, FullScreenSampleDialogFragment.TAG)
        }
    }
}
