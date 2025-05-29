package com.example.guo.communication.sharedvm

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.guo.R

class CActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        sharedViewModel = ViewModelProvider(
            this@CActivity,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[SharedViewModel::class.java]

        findViewById<Button>(R.id.btn_done).setOnClickListener {
            sharedViewModel.cResultLiveData.value = "来自 C 的结果"
            finish()
        }
    }
}