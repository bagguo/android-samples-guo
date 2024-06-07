package com.bagguo.lib_base.utils

object UnicodeUtils {
    /**
     * 字符串转换unicode
     */
    fun string2Unicode(string: String): String {
        val unicode = StringBuffer()
        for (element in string) {
            //转换为unicode
            unicode.append("\\u" + Integer.toHexString(element.code))
        }
        return unicode.toString()
    }

    /**
     * unicode转字符串
     */
    fun unicode2String(unicode: String): String {
        val string = StringBuffer()
        val hex = unicode.split("\\\\u".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        for (i in hex.indices) {
            val data = hex[i].toInt(16)
            string.append(data.toChar())
        }
        return string.toString()
    }
}