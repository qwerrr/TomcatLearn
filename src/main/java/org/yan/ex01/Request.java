package org.yan.ex01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc
 * @auther YanMeng
 * @data 16-7-3.
 */
public class Request implements IRequest{

    private static final int BUFFER_LENGTH = 2048;
    private static final String HEAD_BODY_SEPARATOR = "\r\n\r\n";

    private static final String HEAD_FIELD_SEPARATOR = "\r\n";
    private static final String HEAD_DECLARE_SEPARATOR = " ";
    private static final String HEAD_KV_SEPARATOR = ":";
    private static final String HEAD_PROTOCOL_SEPARATOR = "/";

    private static final String BODY_FIELD_SEPARATOR = "&";
    private static final String BODY_KV_SEPARATOR = "=";

    private String method;
    private String uri;
    private String protocol;
    private String version;

    /**
     * 请求头,排除请求第一行声明部分
     */
    private Map<String, String> head = new HashMap<String, String>();
    /**
     * 请求体参数map
     */
    private Map<String, String> body = new HashMap<String, String>();

    private InputStream is;
    private String requestStr;

    public Request(InputStream is) throws IOException, InterruptedException {
        this.is = is;
        this.requestStr = read(is);
    }

    /**
     * 将InputStream中的数据读取为String
     * @param is
     * @return
     * @throws IOException
     */
    private String read(InputStream is) throws IOException, InterruptedException {
        //因为从SocketInputStream中read数据时无法接收到结束符EOF,所以在最后一次read之后会在read方法上阻塞,所以弃用
//        StringBuffer sb = new StringBuffer();
//        byte[] buffer = new byte[BUFFER_LENGTH];
//        int len = 0;
//        while((len = is.read(buffer)) != -1){
//            sb.append(new String(buffer));  //有隐藏的bug,如果请求体中包含中文时可能会造成乱码问题,因为无法保证单次循环的结尾会将整个汉字拿到
//            buffer = new byte[BUFFER_LENGTH];
//        }
//        return sb.toString();

        //简单处理
        byte[] buffer = new byte[BUFFER_LENGTH];
        int len = is.read(buffer);
        byte[] result = new byte[len];
        System.arraycopy(buffer,0,result,0,len);
        return new String(result);
    }

    /**
     * 解析字符串并填充到实体中
     * @throws IOException
     */
    public void parse() throws Exception {
        try {
            String[] requestSplit = requestStr.split(HEAD_BODY_SEPARATOR);
            String headStr = requestSplit[0];
            //解析head
            String[] headSplit = headStr.split(HEAD_FIELD_SEPARATOR);

            String declareStr = headSplit[0];
            String[] declareSplit = declareStr.split(HEAD_DECLARE_SEPARATOR);
            this.method = declareSplit[0];
            this.uri = declareSplit[1];
            String[] protocolSplit = declareSplit[2].split(HEAD_PROTOCOL_SEPARATOR);
            this.protocol = protocolSplit[0];
            this.version = protocolSplit[1];

            for(int i = 1; i < headSplit.length; i++){
                String fieldStr = headSplit[i];
                if(fieldStr.trim() == ""){
                    continue;
                }
                int kvSepaIndex = fieldStr.indexOf(HEAD_KV_SEPARATOR);
                if(kvSepaIndex == -1){
                    continue;
                }
                String key = fieldStr.substring(0,kvSepaIndex);
                String value = fieldStr.substring(kvSepaIndex+1,fieldStr.length());
                this.head.put(key, value.replace("\015",""));
            }
            //解析body
            if(requestSplit.length == 2){
                String bodyStr = requestSplit[1];
                String[] bodySplit = bodyStr.split(BODY_FIELD_SEPARATOR);
                for(int i = 0; i < bodySplit.length; i++){
                    String fieldStr = bodySplit[i];
                    String[] fieldSplit = fieldStr.split(BODY_KV_SEPARATOR);
                    this.body.put(fieldSplit[0].trim(), fieldSplit[1]);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("解析请求数据异常:");
        }
    }

    @Override
    public String toString(){

        StringBuffer sb = new StringBuffer(1000);
        sb.append("\n{\n")
            .append("method:").append(method).append(",\n")
            .append("uri:").append(uri).append(",\n")
            .append("protocol:").append(protocol).append(",\n")
            .append("version:").append(version).append(",\n")
            .append("head:").append(mapToString(head)).append(",\n")
            .append("body:").append(mapToString(body)).append(",\n")
            .append("requestStr:").append(requestStr).append("\n")
            .append("}\n");
        return sb.toString();
    }

    private <K,V> String mapToString(Map<K,V> map){
        if(map == null)
            return null;
        if(map.isEmpty()){
            return "{}";
        }

        StringBuffer sb = new StringBuffer("{");
        for(Map.Entry<K,V> entry : map.entrySet()){
            sb.append(entry.getKey().toString())
                .append(":")
                .append(entry.getValue().toString())
                .append(",");
        }
        sb.replace(sb.length()-1,sb.length(),"}");
        return sb.toString();
    }

    private void printBytes(byte[] bytes){
        System.out.printf("[");
        for(int i = 0; i < bytes.length; i++){
            System.out.printf(bytes[i]+",");
        }
        System.out.printf("[\n");
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
