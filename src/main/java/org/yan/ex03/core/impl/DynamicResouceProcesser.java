package org.yan.ex03.core.impl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.yan.ex03.Processer;
import org.yan.ex02.servlet.PrimitiveServlet;

/**
 * 动态资源处理器
 * tomcat在处理动态资源时需要通过url路径地址确认用户在调用哪个servlet, 所以应该还需要有一套策略用于
 * 加载/管理 url和servlet之间的映射
 *
 * @author YanMeng
 * @date 16-7-20
 */
public class DynamicResouceProcesser implements Processer{

    private static final int BUFFER_LENGTH = 1024;

    public void process(ServletRequest request, ServletResponse response) {

        PrimitiveServlet servlet = new PrimitiveServlet();
        try {
            PrintWriter pw = response.getWriter();
            String success200 = "HTTP/1.1 200 OK\r\n" +
                    "Connection: keep-alive\r\n" +
                    "Content-Type: text/html\r\n" +
                    "\r\n";
            pw.write(success200);
            servlet.service(request,response);
            pw.flush();
            pw.close();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
