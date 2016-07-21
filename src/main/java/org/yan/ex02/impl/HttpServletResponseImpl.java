package org.yan.ex02.impl;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * ServletResponse的简单实现
 * 封装了响应信息
 *
 * @author YanMeng
 * @date 16-7-20
 */
public class HttpServletResponseImpl implements ServletResponse {

    private OutputStream outputStream;
    private PrintWriter printWriter;

    public HttpServletResponseImpl(OutputStream outputStream){
        this.outputStream = outputStream;
        this.printWriter = new PrintWriter(new PrintStream(outputStream));
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
