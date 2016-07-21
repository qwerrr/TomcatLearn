package org.yan;

/**
 * HTTP协议符号
 *
 * [method uri protocol/version
 * field1: value1
 * field2: value2
 * ...
 * fieldN: valueN
 *
 * param1=value1&param2=value2....&paramN=valueN]
 *
 * HTTP协议符号
 * @author YanMeng
 * @date 16-7-21
 */
public class HttpProtocolSymbol {

    /**
     * 协议头和协议体分隔符
     * head[\r\n\r\n]param1=value1&param2=value2
     */
    public static final String HEAD_BODY_SEPARATOR = "\r\n\r\n";

    /**
     * 协议头各字段间分隔符,也是http协议第一行声明和各个字段的分隔符
     *
     * method uri protocol/version[\r\n]field1: value1[\r\n]field2:value2
     */
    public static final String HEAD_FIELD_SEPARATOR = "\r\n";
    /**
     * 协议头第一行声明的各个字段间的分隔符
     * method uri protocol/version
     */
    public static final String HEAD_DECLARE_SEPARATOR = " ";
    /**
     * 协议头各字段的kv分隔符
     * field: value
     */
    public static final String HEAD_KV_SEPARATOR = ":";
    /**
     * 协议头第一行声明的协议和版本的分隔符
     * protocol/version
     */
    public static final String HEAD_PROTOCOL_SEPARATOR = "/";

    /**
     * 请求体各参数间的分隔符
     * param1=value1&param2=value2
     */
    public static final String BODY_FIELD_SEPARATOR = "&";
    /**
     * 请求体参数名称和参数值之间的分隔符
     * param=value
     */
    public static final String BODY_KV_SEPARATOR = "=";
}
