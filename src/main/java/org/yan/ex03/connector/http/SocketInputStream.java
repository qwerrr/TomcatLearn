package org.yan.ex03.connector.http;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Http协议读取请求数据的inputstream实现
 * @author YanMeng
 * @date 16-7-22
 */
public class SocketInputStream extends BufferedInputStream{

    public SocketInputStream(InputStream in) {
        super(in);
    }

    public SocketInputStream(InputStream in, int size) {
        super(in, size);
    }

    public void readRequestLine(HttpRequestLine httpRequestLine){

    }

    public void readRequestHeader(HttpRequestHeader httpRequestHeader){

    }

}
