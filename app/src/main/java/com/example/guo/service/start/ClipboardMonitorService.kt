package com.example.guo.service.start

import android.app.Service
import android.content.ClipboardManager
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * todo: 未生效
 */
class ClipboardMonitorService : Service() {

    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipChangedListener: ClipboardManager.OnPrimaryClipChangedListener

    override fun onCreate() {
        super.onCreate()
        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        clipChangedListener = ClipboardManager.OnPrimaryClipChangedListener {
            // 处理剪贴板内容变化的逻辑
            val clipboardText = clipboardManager.primaryClip?.getItemAt(0)?.text.toString();
            Log.d("ClipboardMonitor", "Clipboard text changed: ====" + clipboardText);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clipboardManager.removePrimaryClipChangedListener(clipChangedListener)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}