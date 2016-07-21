package org.yan.ex03.connector;

import java.net.Socket;
import java.net.SocketI

/**
 * Http协议处理器
 * @author YanMeng
 * @date 16-7-21
 */
public class HttpProcesser {

    private HttpConnector connector;

    public HttpProcesser(HttpConnector connector){
        this.connector = connector;
    }

    public void process(Socket socket){
        SocketInputStream sis = null;
    }
}
