package org.yan.test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @desc
 * @auther YanMeng
 * @data 16-7-24.
 */
public class StringManagerTest {
    public static void main(String[] args) {
        //测试默认
        ResourceBundle bundle = ResourceBundle.getBundle("org.yan.test.LocalString");
        System.out.println("本地信息:"+bundle.getLocale().toString());
        System.out.println("测试获取当前包内资源信息:"+bundle.getString("abc"));

        //测试日文
        Locale locale_ja = new Locale("ja");
        ResourceBundle bundle_ja = ResourceBundle.getBundle("org.yan.test.LocalString", locale_ja);
        System.out.println("本地信息:"+bundle_ja.getLocale().toString());
        System.out.println("测试获取当前包内资源信息:"+bundle_ja.getString("abc"));


        Locale locale_en = new Locale("en");
        ResourceBundle bundle_en = ResourceBundle.getBundle("org.yan.test.LocalString", locale_en);
        System.out.println("本地信息:"+bundle_en.getLocale().toString());
        System.out.println("测试获取当前包内资源信息:"+bundle_en.getString("abc"));
    }
}
