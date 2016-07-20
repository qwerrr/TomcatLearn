package org.yan.ex02.impl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.*;

import org.yan.MyCodeContext;
import org.yan.ex02.Processer;

/**
 * 动态资源处理器
 * @author YanMeng
 * @date 16-7-20
 */
public class DynamicResouceProcesser implements Processer{

    private static final int BUFFER_LENGTH = 2048;

    public void process(ServletRequest request, ServletResponse response) {
        FileReader br = null;
        PrintWriter printWriter = null;
        try {
            File file = new File(MyCodeContext.WEB_APP_PATH, request.getLocalAddr());
            printWriter = response.getWriter();
            //文件存在,读取文件将数据扔到outputstream
            if (file.exists()){
                br = new FileReader(file);
                byte[] buffer = new byte[fileInputStream.available()];
                while (fileInputStream.read(buffer) != -1){
                    printWriter.write(buffer);

                }
            }else{
                String error404 = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                os.write(error404.getBytes());
            }
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null)
                fileInputStream.close();
        }
    }
}
