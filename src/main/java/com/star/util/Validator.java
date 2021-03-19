package com.star.util;

import java.util.regex.Pattern;

/**
 * 验证地址工具类
 *
 * @Author: zzStar
 * @Date: 03-19-2021 11:35
 */
public class Validator {

    private final static String URL_REGEX = "(ftp|http|https):\\/\\/(\\w+:{0,1}\\w*@)?(\\S+)(:[0-9]+)?(\\/|\\/([\\w#!:.?+=&%@!\\-\\/]))?";

    /**
     * 验证URL地址
     *
     * @param url
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkUrl(String url) {
        return Pattern.matches(URL_REGEX, url);
    }

}
