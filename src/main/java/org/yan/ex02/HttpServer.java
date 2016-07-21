package org.yan.ex02;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.yan.ex02.impl.DynamicResouceProcesser;
import org.yan.ex02.impl.StaticResouceProcesser;

/**
 * @desc
 * @auther YanMeng
 * @data 16-7-3.
 */
public class HttpServer {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8080;
    private static final String SHUT_DOWN_URI = "/shutdown";

    private static boolean shutDown = false;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT,1,InetAddress.getByName(IP));
        } catch (IOException e) {
            e.printStackTrace();
        }


        while(!shutDown){

            Socket socket = null;
            InputStream is = null;
            OutputStream os = null;

            try {
                socket = serverSocket.accept();
                is = socket.getInputStream();
                os = socket.getOutputStream();

                ModelFactory modelFactory = new ModelFactory();
                ServletRequest request = modelFactory.createRequest(is);
                System.out.println("请求:"+request.toString());

                ServletResponse response = modelFactory.createResponse(os);

                String uri = request.getLocalAddr();
                Processer processer = null;
                if(uri.startsWith("/servlet")){
                    processer = new DynamicResouceProcesser();
                }else{
                    processer = new StaticResouceProcesser();
                }
                processer.process(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    os.flush();
                    os.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
