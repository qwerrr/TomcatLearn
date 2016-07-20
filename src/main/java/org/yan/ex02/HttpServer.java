package org.yan.ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.yan.ex01.Request;
import org.yan.ex01.Response;

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
                Request request = new Request(is);
                request.parse();
                System.out.println("请求:"+request.toString());

                Response response = new Response(os, request);
                response.sendStaticResource();

                if(request.getUri().equals(SHUT_DOWN_URI)){
                    shutDown = true;
                }
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
