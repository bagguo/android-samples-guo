package com.example.guo.ui.edittext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityEditTextSampleBinding
import com.example.guo.util.addThousandsSeparator

class EditTextSampleActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "EditTextSampleActivity"

        fun launch(context: Context) {
            val intent = Intent(context, EditTextSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivityEditTextSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEditTextSampleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.et.setText("1")
        mBinding.et.addThousandsSeparator()
    }
}