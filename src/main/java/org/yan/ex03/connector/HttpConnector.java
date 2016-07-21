package org.yan.ex03.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.yan.TomcatLearnContext;

/**
 * 连接器
 *
 * @author YanMeng
 * @date 16-7-21
 */
public class HttpConnector implements Runnable {

    private boolean started = false;
    private boolean stoping = false;
    private String scheme = "http";

    public String getScheme() {
        return scheme;
    }

    public void start(){
        if(!started && !stoping){
            Thread thread = new Thread(this);
            thread.start();
            started = true;
        }
    }

    public void stop(){
        stoping = (started && !stoping);
    }

    public void run() {

        ServerSocket ss = null;
        try {
            ss = new ServerSocket(
                    TomcatLearnContext.SERVER_PORT,
                    TomcatLearnContext.BACK_LOG,
                    InetAddress.getByName(TomcatLearnContext.SERVER_IP));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stoping){
            Socket socket = null;
            try {
                socket = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            //接收到socket后创建HttpProcesser, 并执行process方法

        }

    }
}
