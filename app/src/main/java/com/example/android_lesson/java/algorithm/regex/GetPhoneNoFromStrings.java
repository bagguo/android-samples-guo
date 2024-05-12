package com.example.android_lesson.java.algorithm.regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPhoneNoFromStrings {

    private static final String REGEX_MOBILE = "((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}";
    private static final String REGEX_TEL = "((\\d{3,4}-)" + //010- 0943-
            "|(\\(\\d{3,4}\\))" + //(021) (0943)
            "|\\d{3,4}" + //021 0943
            "|\\d{3,4}\\ )?" + //021+‘ ’ 0943+‘ ’
            "\\d{6,8}"; // 123456 1234567 12345678

    public static void main(String[] args) {
        /**
         * 1.从文本中匹配并获取移动/固话电话号码
         */
        String content = "你们今天在哪里\n我的电话号码是18305917500njbjjjn" +
                "0138111100002\n" + "前后有数字" +
                "13659142300\n" + "分割" +
                "14905917500\n" + "分割" +
                "19999999999\n" + "分割" +
                "13456789000\n" + "分割" +
//                "+861380000000\n" + "分割" +
//                "+861380000000\n" + "分割" +
//                "(+86)13800000000" + "分割" +
//                "(+86)13800000000" + "分割" +
                "0101234567" + "分割" +
                "02584453326" + "分割" +
                "094384453326" + "分割" +
                "010-1234567" + "分割" +
                "0590-88888888" + "分割" +
                "010 1234567" + "分割" +
                "0590 88888888" + "分割" +
                "(021)1234567" + "分割" +
                "(021)12345678" + "分割" +
                "(0590)88888888" + "分割" +
                "10086" + "分割";
        GetPhoneNoFromStrings phoneNo = new GetPhoneNoFromStrings();
        phoneNo.getPhone(content);

        /**
         * 2.获取固话
         */
        String telephoneContent =
//                "010-1234567" + "分割" +
//                "(021)1234567" + "分割" +
//                "10086" + "分割" +
//                "02584453326" + "分割" +
//                "(021)12345678" + "分割" +
//                "0590 88888888" + "分割" +
                "0590-88888888";
//                "(0590)88888888";
//        phoneNo.getTelPhone(telephoneContent);
//        phoneNo.getPhoneNumFromStrIntoSet(telephoneContent);

        /**
         * 3.获取邮箱地址
         */
        String contentMail = "jinlin@cib.com.cn" + "分割" +
                "test@qq.com" + "分割" +
                "123@ciit.com.cn" + "分割" +
                "456@cib-leasing.com.cn";
        phoneNo.getMail(contentMail);
    }


    /**
     * 从一段文本中获取所有的移动 固定电话号码
     */
    public List<String> getPhone(String content) {
        List<String> result = new ArrayList<>();
        if (content == null) return result;

        Pattern pattern = Pattern.compile(REGEX_MOBILE + "|" + REGEX_TEL);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("-->" + matcher.group());
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * 从文本中获取所有的移动电话号码
     */
    public List<String> getMobilePhone(String content) {

        String regex = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        List<String> result = new ArrayList<>();
        if (content == null) return result;

        Pattern pattern = Pattern.compile("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)");
        Matcher matcher = pattern.matcher(content);
        StringBuffer bf = new StringBuffer(64);
        while (matcher.find()) {
            System.out.println("-->" + matcher.group());
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
            String phone = bf.toString();
            String[] phoneSplit = phone.split(",");
            for (String telephone : phoneSplit) {
                System.out.println("-->telephone:" + telephone);
                boolean isTruePhone = Pattern.matches(regex, telephone);
                if (isTruePhone) {
                    result.add(telephone);
                    System.out.println("mobile phone-->" + telephone);
                }
            }
        }
        return result;
    }


    /**
     * 从文本中获取所有的固话号码
     */
    public List<String> getTelPhone(String content) {
        Pattern pattern = Pattern.compile(REGEX_TEL);
        Matcher matcher = pattern.matcher(content);

        List<String> result = new ArrayList<>();

        while (matcher.find()) {
            System.out.println("-->" + matcher.group());
            result.add(matcher.group());
        }
        return result;
    }

    private static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    /**
     * 从文本中获取所有的邮箱地址
     */
    public List<String> getMail(String content) {
        List<String> result = new ArrayList<>();
        if (content == null) return result;

        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("-->" + matcher.group());
            result.add(matcher.group());
        }
        return result;
    }
}