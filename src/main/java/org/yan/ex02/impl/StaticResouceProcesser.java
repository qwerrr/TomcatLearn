package org.yan.ex02.impl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.*;

import org.yan.TomcatLearnContext;
import org.yan.ex02.Processer;

/**
 * 静态资源处理器
 * @author YanMeng
 * @date 16-7-20
 */
public class StaticResouceProcesser implements Processer{

    private static final int BUFFER_LENGTH = 1024;

    public void process(ServletRequest request, ServletResponse response) {
        FileReader br = null;
        PrintWriter pw = null;
        try {
            System.out.println("请求链接:" + request.getLocalAddr());
            File file = new File(TomcatLearnContext.WEB_APP_PATH, request.getLocalAddr());
            pw = response.getWriter();
            if (file.exists() && file.isFile()){
                String success200 = "HTTP/1.1 200 OK\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: "+file.length()+"\r\n"
                        +"\r\n";
                pw.write(success200);
                br = new FileReader(file);
                char[] buffer = new char[BUFFER_LENGTH];
                while (br.read(buffer) != -1){
                    pw.write(buffer);
                }
            }else{
                String error404 = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                pw.write(error404);
            }
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
