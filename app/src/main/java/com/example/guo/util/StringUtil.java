package com.example.guo.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class StringUtil {

    public static String bigInteger2Hex(String bigIntegerString) {
        BigInteger bigInteger = new BigInteger(bigIntegerString, 10);
        return bigInteger.toString(16);
    }

    public static String bigInteger2String(String bigIntegerString) {
        BigInteger bigInteger = new BigInteger(bigIntegerString, 10);
        return bigInteger.toString(10);
    }

    /**
     * 转换成符合 decimal 的数值
     */

    public static String toDecimal(int decimal, BigInteger integer) {
//		String substring = str.substring(str.length() - decimal);
        StringBuilder sbf = new StringBuilder("1");
        for (int i = 0; i < decimal; i++) {
            sbf.append("0");
        }
        BigDecimal divisor = new BigDecimal(sbf.toString());
        BigDecimal bigDecimal = new BigDecimal(integer);
        BigDecimal divide = bigDecimal.divide(divisor, 18, RoundingMode.DOWN);
        return divide.toPlainString();
    }

    public static String getAddressFromWalletJsonFileName(String s) {
        if (TextUtils.isEmpty(s)) return "";

        int begin = s.lastIndexOf("--") + 2;
        int end = s.lastIndexOf(".");
        return s.substring(begin, end);
    }


}
