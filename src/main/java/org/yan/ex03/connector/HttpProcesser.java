package org.yan.ex03.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.yan.ex03.connector.http.*;

/**
 * Http协议处理器
 * @author YanMeng
 * @date 16-7-21
 */
public class HttpProcesser {

    private HttpConnector connector;

    private HttpRequest request;
    private HttpResponse response;

    public HttpProcesser(HttpConnector connector){
        this.connector = connector;
    }

    public void process(Socket socket){
        InputStream is = null;
        OutputStream os = null;
        try {

            is = socket.getInputStream();
            os = socket.getOutputStream();

            //0. 创建Request
            request = new HttpRequest(is);
            //1. 创建Response
            response = new HttpResponse(os);

            SocketInputStream inputStream = new SocketInputStream(is);

            //2. 解析请求行(请求第一行)
            parseRequestLine(inputStream, os);

            //3. 解析请求头(请求头中除了第一行以外的其他行)
            parseHeader(inputStream);

            //4. 处理请求, 根据请求内容, 调用Servlet或者静态资源

            is.close();
            socket.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析请求行
     * @param inputStream
     * @param os
     */
    private void parseRequestLine(SocketInputStream inputStream, OutputStream os) throws IOException {
        HttpRequestLine requestLine = new HttpRequestLine();
        inputStream.readRequestLine(requestLine);
        byte[] bytes = requestLine.requestLineBytes;
        String requestLineStr = new String(bytes);

        int first = requestLineStr.indexOf(" ");
        int last = requestLineStr.lastIndexOf(" ");
        int question = requestLineStr.indexOf("?");
        //截取请求头
        requestLine.method = requestLineStr.substring(0,first);
        requestLine.url = requestLineStr.substring(first+1, last);
        requestLine.protocol = requestLineStr.substring(last+1, requestLineStr.length() - 2);
        requestLine.isParmUrl = (question != -1);
        if(requestLine.isParmUrl) {
            requestLine.uri = requestLineStr.substring(first + 1, question);
            requestLine.urlParam = requestLineStr.substring(question, last);
        }else {
            requestLine.uri = requestLine.url;
        }

        //解析url中的参数
        if(requestLine.isParmUrl){


            String urlParm = requestLineStr.substring(requestLine.url.indexOf("?"), last);
            String[] urlParmSplit = urlParm.split("&");
            for(String paramKV : urlParmSplit){
                String[] kv = urlParm.split("=");
                if(kv.length != 2 || kv[0].trim().length() == 0 || kv[0].trim().length() == 0){
                    continue;
                }
//                request.setAttribute();
            }
        }
    }

    private void parseHeader(SocketInputStream inputStream) throws IOException {
        HttpRequestHeader header = new HttpRequestHeader();
        inputStream.readRequestHeader(header);
    }


}
