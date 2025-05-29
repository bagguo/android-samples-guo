package com.example.guo.ext

import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder


private const val TAG = "ByteExt"

/**
 * ByteArray -> HexString
 */
fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }

/**
 * HexString -> ByteArray
 */
fun String.hexToBytes(): ByteArray? {
    val cleanHex = this.removePrefix("0x")
    return try {
        ByteArray(cleanHex.length / 2) {
            cleanHex.substring(it * 2, it * 2 + 2).toInt(16).toByte()
        }
    } catch (e: Exception) {
        Log.e(TAG,"hexToBytes:  ${e.message}")
        e.printStackTrace()
        null
    }
}

/**
 * ByteArray -> Int
 * 网络传输或区块链常用 大端（Big Endian）格式
 */
fun ByteArray.toInt(order: ByteOrder = ByteOrder.BIG_ENDIAN): Int {
    return ByteBuffer.wrap(this).order(order).int
}

fun Int.toBytes(order: ByteOrder = ByteOrder.BIG_ENDIAN): ByteArray {
    return ByteBuffer.allocate(4).order(order).putInt(this).array()
}

fun Int.toByte(): Byte {
    return (this and 0xFF).toByte() // 通过 and 0xFF，我们保留了 低 8 位二进制位
}

/**
 * 将 ByteArray 补齐到指定长度，如果原始长度不足，则在前面补 0（padding）字节
 */
fun ByteArray.padToLength(length: Int): ByteArray {
    return if (this.size >= length) this else ByteArray(length - this.size) + this
}