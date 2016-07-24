package org.yan.ex03.connector.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Http协议读取请求数据的inputstream实现
 *
 * 需要保证三个方法readRequestLine,readRequestHeader,readRequestParamter三个方法依次执行, 否则读取会出现问题
 *
 * @author YanMeng
 * @date 16-7-22
 */
public class SocketInputStream extends BufferedInputStream{

    private static final int DEFAULT_BYTE_LENGTH = 1024 * 2;
    private int size;

    public SocketInputStream(InputStream in) {
        super(in);
        size = DEFAULT_BYTE_LENGTH;
    }

    public SocketInputStream(InputStream in, int size) {
        super(in);
        this.size = size;
    }

    public void readRequestLine(HttpRequestLine httpRequestLine) throws IOException {
        StringBuffer sb = new StringBuffer();
        byte[] requestLineBytes = new byte[size];
        int count = 0;
        int n;
        while ((n = super.read()) != -1){
            requestLineBytes[count++] = (byte) n;
            if(n == '\n')
                break;
        }
        httpRequestLine.requestLineBytes = cutOff(requestLineBytes);
    }

    public void readRequestHeader(HttpRequestHeader httpRequestHeader) throws IOException {
        byte[] headersBytes = new byte[size];
        int count = 0;
        int n;
        int lastMark = 0;
        while ((n = super.read()) != -1){
            headersBytes[count++] = (byte) n;
            if(n == '\n'){
                if(count - lastMark == 2){
                    break;
                }else{
                    lastMark = count;
                }
            }
        }
        httpRequestHeader.headersBytes = cutOff(headersBytes);
    }

    public byte[] readRequestParamter() throws IOException {
        byte[] headersBytes = new byte[size];
        int count = 0;
        int n;

        if(super.markSupported()){
            super.mark(size);
        }
        while ((n = super.read()) != -1){
            headersBytes[count++] = (byte) n;
        }
        return cutOff(headersBytes);
    }

    private byte[] cutOff(byte[] bytes){
        if(bytes == null)
            return null;
        int count;
        for(count = 0; bytes[count++] != 0; count++)
            ;
        byte[] copyOfBytes = new byte[count];
        System.arraycopy(bytes,0,copyOfBytes,0, count);
        return copyOfBytes;
    }
}
