package org.yan.ex03.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.yan.ex03.connector.http.HttpRequest;
import org.yan.ex03.connector.http.HttpResponse;

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
        InputStream sis = null;
        OutputStream os = null;

        //0. 创建Request
        HttpRequest request = new HttpRequest(sis);
        //1. 创建Response
        HttpResponse response = new HttpResponse(os);

        //2. 读取请求
        //3. 解析请求行(请求第一行)

        //4. 解析请求头(请求头中除了第一行以外的其他行)
        //5. 解析请求Cookie

        //6. 处理请求, 根据请求内容, 调用Servlet或者静态资源


        try {
            sis.close();
            socket.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析请求行
     * @param is
     * @param os
     */
    private void parseRequestLine(InputStream is, OutputStream os){

    }

    private void parseHeader(InputStream is){

    }
}
