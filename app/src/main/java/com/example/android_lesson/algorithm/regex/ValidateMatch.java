package com.example.android_lesson.algorithm.regex;

import java.util.regex.Pattern;

/**
 * 通过正则表达判断是否正确的手机号,固定电话,身份证,邮箱,url,车牌号,日期,ip地址,mac,人名等.
 */
public class ValidateMatch {

    /**
     * 正则：全球手机号
     *
     * 最短：加拿大7位
     * 最长：中国、日本等11位
     * 美国手机号和电话号一样
     */
    private static final String REGEX_MOBILE_GLOBAL = "^\\d{7,11}$";
    private static final Pattern PATTERN_REGEX_MOBILE_GLOBAL = Pattern.compile(REGEX_MOBILE_GLOBAL);

    /**
     * 正则：手机号（简单）, 1字头＋10位数字即可.
     */
    private static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    private static final Pattern PATTERN_REGEX_MOBILE_SIMPLE = Pattern.compile(REGEX_MOBILE_SIMPLE);

    /**
     * 正则：手机号（精确）, 已知3位前缀＋8位数字
     * <p>
     * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188
     * </p>
     * <p>
     * 联通：130、131、132、145、155、156、175、176、185、186
     * </p>
     * <p>
     * 电信：133、153、173、177、180、181、189
     * </p>
     * <p>
     * 全球星：1349
     * </p>
     * <p>
     * 虚拟运营商：170
     * </p>
     */
    private static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(16[0-9])|(17[0-9])|" +
            "(18[0-9]))\\d{8}$";
    private static final Pattern PATTERN_REGEX_MOBILE_EXACT = Pattern.compile(REGEX_MOBILE_EXACT);

    /**
     * 正则：固定电话号码,可带区号,然后至少6,8位数字
     */
//        private static final String REGEX_TEL = "^(\\d{3,4}-)?\\d{6,8}$";
    private static final String REGEX_TEL = "^((\\d{3,4}-)" + //010- 0943-
            "|(\\(\\d{3,4}\\))" + //(021) (0943)
            "|\\d{3,4}" + //021 0943
            "|\\d{3,4}\\ )?" + //021+‘ ’ 0943+‘ ’
            "\\d{6,8}$"; // 123456 1234567 12345678
    private static final Pattern PATTERN_REGEX_TEL = Pattern.compile(REGEX_TEL);

    /**
     * 正则：身份证号码15位, 数字且关于生日的部分必须正确
     */
    private static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    private static final Pattern PATTERN_REGEX_ID_CARD15 = Pattern.compile(REGEX_ID_CARD15);

    /**
     * 正则：身份证号码18位, 数字且关于生日的部分必须正确
     */
    private static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    private static final Pattern PATTERN_REGEX_ID_CARD18 = Pattern.compile(REGEX_ID_CARD18);

    /**
     * 正则：邮箱, 有效字符(不支持中文), 且中间必须有@,后半部分必须有.
     */
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final Pattern PATTERN_REGEX_EMAIL = Pattern.compile(REGEX_EMAIL);

    /**
     * 正则：URL, 必须有"://",前面必须是英文,后面不能有空格
     */
    private static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";
    private static final Pattern PATTERN_REGEX_URL = Pattern.compile(REGEX_URL);

    /**
     * 正则：yyyy-MM-dd格式的日期校验,已考虑平闰年
     */
    private static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    private static final Pattern PATTERN_REGEX_DATE = Pattern.compile(REGEX_DATE);

    /**
     * 正则：IP地址
     */
    private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
    private static final Pattern PATTERN_REGEX_IP = Pattern.compile(REGEX_IP);


    /**
     * 正则：车牌号
     */
    private static final String REGEX_CAR = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{3,4}[A-Z0-9挂学警港澳]{1}$";
    private static final Pattern PATTERN_REGEX_CAR = Pattern.compile(REGEX_CAR);


    /**
     * 正则：人名
     */
    private static final String REGEX_NAME = "^([\\u4e00-\u9fa5]{1,20}|[a-zA-Z\\.\\s]{1,20})$";
    private static final Pattern PATTERN_REGEX_NAME = Pattern.compile(REGEX_NAME);

    /**
     * 正则：mac地址
     */
    private static final String REGEX_MAC = "([A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2}";
    private static final Pattern PATTERN_REGEX_MAC = Pattern.compile(REGEX_MAC);


    //封装方法：
    /**
     * 验证手机号（全球）
     */
    public static boolean isMobileGlobal(String str) {
        return isMatch(PATTERN_REGEX_MOBILE_GLOBAL, str);
    }

    /**
     * 验证手机号（简单）
     */
    public static boolean isMobileSimple(String str) {
        return isMatch(PATTERN_REGEX_MOBILE_SIMPLE, str);
    }

    /**
     * 验证手机号（精确）
     */
    public static boolean isMobileExact(String str) {
        return isMatch(PATTERN_REGEX_MOBILE_EXACT, str);
    }

    /**
     * 验证固定电话号码
     */
    public static boolean isTel(String str) {
        return isMatch(PATTERN_REGEX_TEL, str);
    }

    /**
     * 验证15或18位身份证号码
     */
    public static boolean isIdCard(String str) {
        return isMatch(PATTERN_REGEX_ID_CARD15, str) || isMatch(PATTERN_REGEX_ID_CARD18, str);
    }

    /**
     * 验证邮箱
     */
    public static boolean isEmail(String str) {
        return isMatch(PATTERN_REGEX_EMAIL, str);
    }

    /**
     * 验证URL
     */
    public static boolean isUrl(String str) {
        return isMatch(PATTERN_REGEX_URL, str);
    }

    /**
     * 验证yyyy-MM-dd格式的日期校验,已考虑平闰年
     */
    public static boolean isDate(String str) {
        return isMatch(PATTERN_REGEX_DATE, str);
    }

    /**
     * 验证IP地址
     */
    public static boolean isIp(String str) {
        return isMatch(PATTERN_REGEX_IP, str);
    }

    /**
     * 验证车牌号
     */
    public static boolean isCar(String str) {
        return isMatch(PATTERN_REGEX_CAR, str);
    }

    /**
     * 验证人名
     */
    public static boolean isName(String str) {
        return isMatch(PATTERN_REGEX_NAME, str);
    }

    /**
     * 验证mac
     */
    public static boolean isMac(String str) {
        return isMatch(PATTERN_REGEX_MAC, str);
    }

    public static boolean isMatch(Pattern pattern, String str) {
        return str != null && !str.equals("") && !str.equals("null") && pattern.matcher(str).matches();
    }

    //测试方法
    public static void main(String[] args) {
        //电话号码测试
//            String car = "137888888888";
//            String car = "19999999999";
//            System.out.println(isMobileExact(car));

//            String tel = "010-1234567"; //pass
        String tel = "0101234567";//pass
//            String tel = "010 1234567";//pass
//            String tel = "(010)1234567";//pass

        //global phone NO
        System.out.println("" + isMobileGlobal(""));
        System.out.println("" + isMobileGlobal("123"));
        System.out.println("" + isMobileGlobal("1234567"));
        System.out.println("" + isMobileGlobal("12345678901"));
        System.out.println("" + isMobileGlobal("123456789012"));

        //邮箱测试
//                String mail = "jinlin@cib.com.cn"; //pass
//                String mail = "test@qq.com"; //pass
//                String mail = "123@ciit.com.cn"; //pass
//                String mail = "456@cib-leasing.com.cn"; //pass
        String mail = "789@ciamc.com.cn"; //pass
        System.out.println(isEmail(mail));
    }
}
