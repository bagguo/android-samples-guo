package com.example.guo.util

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText

private const val TAG = "EditTextUtil"

/**
 * et整数部份逢千加逗号
 * e.g. 9，999，999.99960
 *
 * todo: 光标控制有问题
 */
fun EditText.addThousandsSeparator() {
    var isEditing = false

    this.addTextChangedListener(
        object : TextWatcher {
            private var oldText = ""
            private var oldCursorPosition = 0

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
                oldText = s?.toString() ?: ""
                oldCursorPosition = selectionStart
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (isEditing || s.isNullOrEmpty()) return
                isEditing = true

                // reset
                val cleanText = s.toString().replace(",", "")

                // 拆分整数和小数部分
                val parts = cleanText.split(".")
                val integerPart = parts[0]
                val decimalPart = if (parts.size > 1) parts[1] else ""

                // 格式化整数部分
                val formattedInt =
                    integerPart
                        .reversed()
                        .chunked(3)
                        .joinToString(",")
                        .reversed()

                val formatted =
                    if (decimalPart.isEmpty()) {
                        if (cleanText.contains(".")) {
                            "$formattedInt."
                        } else {
                            formattedInt
                        }
                    } else {
                        "$formattedInt.$decimalPart"
                    }

//                Log.d(TAG, "afterTextChanged: ====formattedInt:$formattedInt formatted:$formatted ")
                // 计算光标新位置
                val commaCountBefore = oldText.take(oldCursorPosition).count { it == ',' }
                val commaCountAfter = formatted.take(oldCursorPosition).count { it == ',' }

                var newCursorPos = oldCursorPosition + (commaCountAfter - commaCountBefore)
                if (oldText.length < formatted.length) { // 增加操作， else为删减操作
                    newCursorPos++
                }
                val checkedNewCursorPos = newCursorPos.coerceIn(0, formatted.length) // 校准
                Log.d(
                    TAG,
                    "afterTextChanged:====commaCountBefore: $commaCountBefore," +
                        " commaCountAfter: $commaCountAfter, " +
                        "oldCursorPosition: $oldCursorPosition, " +
                        "newCursorPos: $checkedNewCursorPos",
                )

                // 更新文本与光标位置
                this@addThousandsSeparator.setText(formatted)
                this@addThousandsSeparator.setSelection(checkedNewCursorPos)
//                this@addThousandsSeparator.setSelection(formatted.length)

                isEditing = false
            }
        },
    )
}
