package org.yan.ex03.connector.http;

import java.util.HashMap;

/**
 * Http请求头, 用于盛放请求请求头除第一行的数据
 * @author YanMeng
 * @date 16-7-22
 */
public class HttpRequestHeader {
    HashMap<String,String> headers = new HashMap<String, String>();
    byte[] headersBytes;

}
