@file:Suppress("kt lint:standard:filename")

package com.example.guo.util

/**
 * 判断文本是否全是 Emoji
 */
fun isAllEmoji(text: String): Boolean {
    if (text.isEmpty()) return false

    val emojiRegex =
        Regex(
            pattern =
                "(?:[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+|" + // surrogate pairs (基本 emoji)
                    "[\\u2600-\\u27BF]|" + // 杂项符号
                    "\\uFE0F|" + // 变体选择符
                    "\\u200D)+",
        )

    // 所有字符都必须匹配 emoji 正则
    val allEmojis = emojiRegex.findAll(text).joinToString("") { it.value }
    return allEmojis == text
}

/**
 * 提取字符串中的表情
 */
fun extractEmojis(text: String): List<String> {
    // Unicode 范围基本覆盖表情符号
    val emojiRegex = Regex("[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+")
    return emojiRegex.findAll(text).map { it.value }.toList()
}

// test
fun main() {
    val input = "😇😇😗😗你好"
    val emojis = extractEmojis(input)
    println(emojis) // 输出：[😇, 😇, 😗, 😗]

    val isAllEmoji = isAllEmoji(input)
    println("isAllEmoji: $isAllEmoji")
}
