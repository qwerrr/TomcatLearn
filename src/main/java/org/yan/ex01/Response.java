package org.yan.ex01;

import org.yan.MyCodeContext;

import java.io.*;

/**
 * @desc
 * @auther YanMeng
 * @data 16-7-3.
 */
public class Response implements IResponse{

    private static int BUFFER_LENGTH = 2048;

    private OutputStream os;
    private Request request;

    public Response(OutputStream os, Request request) {
        this.os = os;
        this.request = request;
    }

    public void sendStaticResource() throws IOException {

        FileInputStream fileInputStream = null;
        try {
            File file = new File(MyCodeContext.WEB_APP_PATH, request.getUri());
            //文件存在,读取文件将数据扔到outputstream
            if (file.exists()){
                fileInputStream = new FileInputStream(file);
                byte[] buffer = new byte[fileInputStream.available()];
                while (fileInputStream.read(buffer) != -1){
                    os.write(buffer);
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

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
