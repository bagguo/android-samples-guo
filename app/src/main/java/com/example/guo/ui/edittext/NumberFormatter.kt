package com.example.guo.ui.edittext

object NumberFormatter {
    fun format(
        text: String,
        decimalSeparatorSymbol: String,
        groupingSeparatorSymbol: String,
    ): String {
        val textNoSeparator = removeSeparators(text, groupingSeparatorSymbol)
        val numbersList = extractString(textNoSeparator, decimalSeparatorSymbol)
        val numbersWithSeparators =
            addSeparators(
                numbersList,
                decimalSeparatorSymbol,
                groupingSeparatorSymbol,
            )

        val newString = StringBuilder()

        for (item in numbersWithSeparators) {
            newString.append(item)
        }

        return newString.toString()
    }

    // This function was changed to extract all elements from the input string, not just numbers.
    // This returns a list of all elements. Once the numbers have had separators added, a new string
    // can be constructed from the list.
    private fun extractString(
        text: String,
        decimalSeparatorSymbol: String,
    ): List<String> {
        val result = mutableListOf<String>()
        var currentNumber = StringBuilder()

        for (char in text) {
            when {
                char.isDigit() || char == decimalSeparatorSymbol.single() -> {
                    currentNumber.append(char)
                }

                else -> {
                    if (currentNumber.isNotEmpty()) {
                        result.add(currentNumber.toString())
                        currentNumber = StringBuilder()
                    }
                    result.add(char.toString())
                }
            }
        }

        if (currentNumber.isNotEmpty()) {
            result.add(currentNumber.toString())
        }

        return result
    }

    private fun addSeparators(
        numbersList: List<String>,
        decimalSeparatorSymbol: String,
        groupingSeparatorSymbol: String,
    ): List<String> =
        numbersList.map {
            if (it.contains(decimalSeparatorSymbol)) {
                if (it.first() == decimalSeparatorSymbol[0]) {
                    // this means the floating point number doesn't have integers
                    it
                } else {
                    val integersPart = it.substring(0, it.indexOf(decimalSeparatorSymbol))
                    val fractions = it.substring(it.indexOf(decimalSeparatorSymbol) + 1)
                    formatIntegers(
                        integersPart,
                        groupingSeparatorSymbol,
                    ) + decimalSeparatorSymbol + fractions
                }
            } else {
                formatIntegers(
                    it,
                    groupingSeparatorSymbol,
                )
            }
        }

    private fun formatIntegers(
        integers: String,
        groupingSeparatorSymbol: String,
    ): String {
        return integers
            .reversed() // reversed      : 01100
            .chunked(3) // chunked       : [011, 00]
            .joinToString(groupingSeparatorSymbol) // joinedToString: 011,00
            .reversed() // reversed      : 00,110
    }

    private fun removeSeparators(
        text: String,
        groupingSeparatorSymbol: String,
    ): String = text.replace(groupingSeparatorSymbol, "")
}

enum class NumberingSystem(
    val value: Int,
    val description: String,
) {
    INTERNATIONAL(0, "International Numbering System"),
    INDIAN(1, "Indian Numbering System"),
    ;

    companion object {
        fun getDescription(value: Int): String =
            when (value) {
                0 -> INTERNATIONAL.description
                1 -> INDIAN.description
                else -> INTERNATIONAL.description
            }

        fun Int.toNumberingSystem(): NumberingSystem =
            when (this) {
                0 -> INTERNATIONAL
                1 -> INDIAN
                else -> INTERNATIONAL
            }
    }
}
