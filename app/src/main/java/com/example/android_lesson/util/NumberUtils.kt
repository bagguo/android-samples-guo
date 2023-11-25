package com.example.android_lesson.util

import android.text.TextUtils
import android.util.Log
import org.web3j.utils.Numeric
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

class NumberUtils {

    fun hex2BigDecimal(valueHex: String?): BigDecimal? {
        var valueHex = valueHex
        if (TextUtils.isEmpty(valueHex)) return BigDecimal("0")
        valueHex = Numeric.cleanHexPrefix(valueHex)
        val bigInteger = BigInteger(valueHex, 16)
        return BigDecimal(bigInteger)
    }


    fun hex2BigInteger(valueHex: String?): BigInteger? {
        var valueHex = valueHex
        if (TextUtils.isEmpty(valueHex)) return BigInteger("0")
        valueHex = Numeric.cleanHexPrefix(valueHex)
        return BigInteger(valueHex, 16)
    }

    fun bigInteger2Hex(bigIntegerString: String?): String? {
        val bigInteger = BigInteger(bigIntegerString, 10)
        return bigInteger.toString(16)
    }

    fun bigInteger2String(bigIntegerString: String?): String? {
        val bigInteger = BigInteger(bigIntegerString, 10)
        return bigInteger.toString(10)
    }

    /**
     * 小数点后保留1位；每3位用','隔开 11,290.2
     */
    fun format1decimalInternational(d: Double): String? {
        if (d == 0.0) return ""
        val df = DecimalFormat("#,##0.0")
        df.roundingMode = RoundingMode.HALF_UP //四舍五入
        return df.format(d)
    }

    /**
     * 小数点后保留3位；每3位用','隔开 11,290.240
     */
    fun format3decimalInternational(s: String): String? {
        if (TextUtils.isEmpty(s)) return ""
        val number = s.toDouble()
        if (number == 0.0) return "0"
        val df = DecimalFormat("#,##0.000")
        df.roundingMode = RoundingMode.HALF_UP //四舍五入
        return df.format(number)
    }

    fun format3decimalInternational(d: Double): String? {
        if (d == 0.0) return ""
        val df = DecimalFormat("#,##0.000")
        df.roundingMode = RoundingMode.HALF_UP //四舍五入
        return df.format(d)
    }

    fun formatIntegerInternational(l: Long): String? {
        if (l == 0L) return ""
        val df = DecimalFormat("#,###")
        return df.format(l)
    }


    fun format2Thousand(l: Int): String? {
        val divisor = BigDecimal("1000")
        return formatByDivisor(l, divisor)
    }

    fun format2Thousand(l: Long): String? {
        val divisor = BigDecimal("1000")
        return formatByDivisor(l, divisor)
    }


    fun format2Thousand(l: Double): String? {
        val millionDivisor = BigDecimal("1000")
        return formatByDivisor(l, millionDivisor)
    }

    fun format2Million(l: Double): String? {
        val millionDivisor = BigDecimal("1000000")
        return formatByDivisor(l, millionDivisor)
    }

    fun format2Million(l: Long): String? {
        val millionDivisor = BigDecimal("1000000")
        return formatByDivisor(l, millionDivisor)
    }

    fun format2Trillion(l: Long): String? {
        val millionDivisor = BigDecimal("1000000000")
        return formatByDivisor(l, millionDivisor)
    }

    fun testFormat() {
        Log.d("numberFilter:===", numberFilter(88))
        Log.d("numberFilter:===", numberFilter(9999))
        Log.d("numberFilter:===", numberFilter(99999))
        Log.d("numberFilter:===", numberFilter(287890))
        Log.d("numberFilter:===", numberFilter(99999997))
        Log.d("numberFilter:===", numberFilter(999999999))
    }

    private fun numberFilter(number: Int?): String {
        if (number == null) {
            return "0"
        }


        val thousandDivisor = BigDecimal("1000")
        val millionDivisor = BigDecimal("1000000")
        val trillionDivisor = BigDecimal("1000000000")

        return if (number < 1000) {
            number.toString()
        } else if (number in 1000..9999) {  //1千-1w 一位小数
            val df2 = DecimalFormat("##.#")
            val format = df2.format((number.toFloat() / 1000).toDouble())
            format + "K"
        } else if (number in 10000..999999) {  //数字上万，小于百万，保留一位小数点
            val df2 = DecimalFormat("##.#")
            val format = df2.format((number.toFloat() / 1000))
            format + "K"
        } else if (number in 1000000..99999998) {  //百万到千万不保留小数点
            val decimal = BigDecimal(number).divide(millionDivisor)
            val df = DecimalFormat()
            df.roundingMode = RoundingMode.HALF_UP //四舍五入
            df.format(decimal) + "M"
        } else {//上亿 1位小数
            val decimal = BigDecimal(number).divide(trillionDivisor)
            val df = DecimalFormat("##.#")
            df.roundingMode = RoundingMode.HALF_UP
            df.format(decimal) + "T"
        }

    }



    /**
     * 转为千、万、百万等
     *
     * @param divisor 千、万、百万
     */
    fun formatByDivisor(l: Int, divisor: BigDecimal?): String? {
        if (l == 0) return ""
        val decimal = BigDecimal(l).divide(divisor)
        val df = DecimalFormat("#,##0.000")
        df.roundingMode = RoundingMode.HALF_UP //四舍五入
        return df.format(decimal)
    }


    fun formatByDivisor(l: Double, divisor: BigDecimal?): String? {
        if (l == 0.0) return ""
        val decimal = BigDecimal(l).divide(divisor)
        val df = DecimalFormat("#,##0.000")
        df.roundingMode = RoundingMode.HALF_UP //四舍五入
        return df.format(decimal)
    }

    fun formatByDivisor(l: Long, divisor: BigDecimal?): String? {
        if (l == 0L) return ""
        val decimal = BigDecimal(l).divide(divisor)
        val df = DecimalFormat("#,##0.000")
        df.roundingMode = RoundingMode.HALF_UP //四舍五入
        return df.format(decimal)
    }



    fun formatPercent(s: String): String? {
        val d = s.toDouble()
        val divisor = BigDecimal("100")
        val decimal = BigDecimal(d).divide(divisor)
        val format = NumberFormat.getPercentInstance()
        format.maximumFractionDigits = 2 //保留两位小数点
        return format.format(decimal)
    }

    fun formatPercent(l: Long): String? {
        val divisor = BigDecimal("100")
        val decimal = BigDecimal(l).divide(divisor)
        val format = NumberFormat.getPercentInstance()
        format.maximumFractionDigits = 2 //保留两位小数点
        return format.format(decimal)
    }

    fun formatPercent(d: Double): String? {
        val divisor = BigDecimal("100")
        val decimal = BigDecimal(d).divide(divisor)
        val format = NumberFormat.getPercentInstance()
        format.maximumFractionDigits = 2 //保留两位小数点
        return format.format(decimal)
    }
}