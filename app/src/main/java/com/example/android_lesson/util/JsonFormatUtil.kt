package com.example.android_lesson.util

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by ldp.
 *
 *
 * Date: 2021-02-07
 *
 *
 * Summary: json 数据格式化显示
 */
object JsonFormatUtil {
    /**
     * json 数据格式化输出
     * @param response
     * @return
     */
    fun formatDataFromJson(response: String): String {
        try {
            if (response.startsWith("{")) {
                val jsonObject = JSONObject(response)
                return jsonObject.toString(4)
            } else if (response.startsWith("[")) {
                val jsonArray = JSONArray(response)
                return jsonArray.toString(4)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}