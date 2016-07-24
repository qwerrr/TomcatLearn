package org.yan.ex03.connector.http;

/**
 * 请求行实体, 用于盛放请求行(请求头第一行)数据
 * @author YanMeng
 * @date 16-7-22
 */
public class HttpRequestLine {

    /**
     * 请求方法:GET/POST/...
     */
    public String method;
    /**
     * 请求地址, 包括可能的参数
     */
    public String url;
    /**
     * 请求地址, 不包括可能的参数
     */
    public String uri;
    /**
     * 请求地址中可能的参数
     */
    public String urlParam;
    /**
     * 是否为带参数请求地址
     */
    public boolean isParmUrl;
    /**
     * 协议:HTTP/1.1 ...
     */
    public String protocol;

    /**
     * 整个
     */
    public byte[] requestLineBytes;
}
