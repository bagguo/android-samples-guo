package com.example.guo.ui.textview.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R
import com.example.guo.databinding.ActivityTextSelectSampleBinding
import com.example.guo.widgets.text.select.OnSelectListener
import com.example.guo.widgets.text.select.SelectableTextHelper

class TextSelectSampleActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TextSelectSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityTextSelectSampleBinding

    private var mSelectableTextHelper: SelectableTextHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTextSelectSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            tv.text = "ppppppppxxxxxxxxxxxxxxxppppppppxxxxxxxxxxxxxxxppppppppxxxxxxxxxxxxxxxppppppp"

            tv.setTextIsSelectable(true);
            mSelectableTextHelper = SelectableTextHelper.Builder(tv)
                .setSelectedColor(resources.getColor(R.color.selected_blue))
                .setCursorHandleSizeInDp(20f)
                .setCursorHandleColor(resources.getColor(R.color.cursor_handle_color))
                .build()

            mSelectableTextHelper?.setSelectListener(OnSelectListener {
                Toast.makeText(this@TextSelectSampleActivity, "select ===", Toast.LENGTH_SHORT)
                    .show()
            })
        }
    }


}