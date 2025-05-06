package com.example.android_lesson.ext

import android.net.Uri
import com.example.android_lesson.App
import java.io.File
import java.io.FileOutputStream

fun asset2Uri(fileName: String):Uri {
    return Uri.fromFile(asset2File(fileName))
}

fun asset2File(fileName: String): File {
    val context = App.mContext
    val outFile = File(context.cacheDir, fileName)
    if (!outFile.exists()) {
        context.assets.open(fileName).use { inputStream ->
            FileOutputStream(outFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
    }
    return outFile
}
