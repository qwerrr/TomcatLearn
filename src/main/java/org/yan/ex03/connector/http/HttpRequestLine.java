package org.yan.ex03.connector.http;

/**
 * 请求行实体, 用于盛放请求行(请求头第一行)数据
 * @author YanMeng
 * @date 16-7-22
 */
public class HttpRequestLine {

    public static void main(String[] args) {
        char[] chars = {'1','2','3','4','a','b','c'};
        System.out.printf(new String(chars, 1, 3));
    }
}
