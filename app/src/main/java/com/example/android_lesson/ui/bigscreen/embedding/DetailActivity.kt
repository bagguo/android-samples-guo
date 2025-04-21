package com.example.android_lesson.ui.bigscreen.embedding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.window.WindowSdkExtensions
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {

        fun launch(context: Context) {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_title))
        builder.setMessage(getString(R.string.dialog_message))
        builder.setPositiveButton(getString(R.string.button_yes)) { _, _ ->
            if (WindowSdkExtensions.getInstance().extensionVersion  >= 6) {
                DetailActivityStartedActivity.launch(this)
            }
        }
        builder.setNegativeButton(getString(R.string.button_cancel)) { _, _ ->
            // Cancel
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}