package com.example.guo.ui.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityClipboardSampleBinding

// TODO: 未完成
class ClipboardSampleActivity : AppCompatActivity() {

    private val binding by lazy { ActivityClipboardSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        var pasteData: String = ""

//        // Gets the ID of the "paste" menu item.
//        val pasteItem: MenuItem = menu.findItem(R.id.menu_paste)
//
//// If the clipboard doesn't contain data, disable the paste menu item.
//// If it does contain data, decide whether you can handle the data.
//        pasteItem.isEnabled = when {
//            !clipboard.hasPrimaryClip() -> {
//                false
//            }
//
//            !(clipboard.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN)) -> {
//                // Disables the paste menu item, since the clipboard has data but it
//                // isn't plain text.
//                false
//            }
//
//            else -> {
//                // Enables the paste menu item, since the clipboard contains plain text.
//                true
//            }
//        }
    }

    /**
     * 获取剪切板内容
     */
    fun getClipboardContent(): String? {
        // 获取系统剪贴板
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 返回数据
        val clipData: ClipData? = clipboard.primaryClip
        if (clipData == null || clipData.itemCount <= 0) {
            return ""
        }
        val item = clipData.getItemAt(0)
        /**
         *清空剪切板
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //要api28以上
            clipboard.clearPrimaryClip()
        } else {
            clipboard.setPrimaryClip(ClipData(null))
        }
        return if (item == null || item.text == null) {
            ""
        } else item.text.toString()
    }
}