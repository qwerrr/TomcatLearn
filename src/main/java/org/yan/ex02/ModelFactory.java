package org.yan.ex02;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yan.ex02.impl.HttpServletRequestImpl;

/**
 * ServletRequest和ServletResponse的静态工厂
 * 这里叫做ModelFactory不太合适,但是想不出来其他名称了
 * 另外因为是用于学习的例子,所以不需要考虑扩展性, 没必要使用抽象工厂
 *
 * @author YanMeng
 * @date 16-7-20
 */
public class ModelFactory {

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

    /**
     *
     * @return
     */
    public ServletRequest createRequest(InputStream inputStream) throws Exception {
        read(inputStream);
        parse();
        return new HttpServletRequestImpl(inputStream, head, body);
    }

    private String read(InputStream is) throws IOException, InterruptedException {
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
    private void parse() throws Exception {
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



    public ServletResponse createResponse(){
        return null;
    }
}
