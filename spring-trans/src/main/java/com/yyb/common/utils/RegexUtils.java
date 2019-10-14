package com.yyb.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegexUtils {
    /**
     * 验证手机号码
     */
    public static final String MOBILE_REGEX = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
    /**
     * 验证座机号码
     */
    public static final String TEL_REGEX = "0\\d{2,3}-\\d{7,8}";
    /**
     * 验证邮箱
     */
    public static final String EMAIL_REGEX = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 验证特殊字符
     */
    public static final String SPECIAL_CHARACTOR_REGEX = "^[\u4e00-\u9fa5a-zA-Z0-9]+$";

    /**
     * 验证正整数
     */
    public static final String POSITIVE_NUMBER_REGEX = "^[0-9]+$";

    /**
     * 验证正实数（正数、正小数）
     */
    public static final String POSITIVE_REAL_NUMBER_REGEX = "^[0-9]+([.][0-9]+)?$";

    /**
     * 验证所有数字（正数、负数、小数）
     */
    public static final String ALL_NUMBER_REGEX = "^[-]?[0-9]+([.][0-9]+)?$";

    /**
     * 验证emoji表情
     */
    public static final String EMOJI_REGEX = "[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]";


    /**
     * 验证方法
     *
     * @param str   待验证字符串
     * @param regex 正则表达式
     * @return
     */
    public static boolean validate(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }

    /**
     * 验证一个字符串是否符合中国大陆地区手机号码格式
     */
    public static boolean isPhone(String str) {
        return validate(str, MOBILE_REGEX);
    }

    /**
     * 验证一个字符串是否符合中国大陆地区座机电话或手机格式
     */
    public static boolean isTelephone(String str) {
        boolean res = validate(str, TEL_REGEX);
        if (!res) {
            res = isPhone(str);
        }
        return res;
    }

    /**
     * 校验邮箱格式
     */
    public static boolean isEmail(String str) {
        return validate(str, EMAIL_REGEX);
    }

    /**
     * 验证字符串不能有特殊符号
     */
    public static boolean string(String str) {
        return validate(str, SPECIAL_CHARACTOR_REGEX);
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return validate(str, POSITIVE_NUMBER_REGEX);
    }

    /**
     * 判断是否是正整数或正浮点数
     */
    public static boolean IsPlusNum(String str) {
        return validate(str, POSITIVE_REAL_NUMBER_REGEX);
    }

    /**
     * 验证是否是数字，包括正、负（浮点）数
     */
    public static boolean isAllNum(String str) {
        return validate(str, ALL_NUMBER_REGEX);
    }

    /**
     * 验证字符串中是否包含emoji表情
     */
    public static boolean hasEmoji(String content){
        Pattern pattern = Pattern.compile(EMOJI_REGEX);
        Matcher matcher = pattern.matcher(content);
        if(matcher .find()){
            return true;
        }
        return false;
    }
}
