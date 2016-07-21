package org.yan;

/**
 * @desc
 * @auther YanMeng
 * @data 16-7-3.
 */
public class TomcatLearnContext {

    /**
     * 服务器ip
     */
    public static final String SERVER_IP = "127.0.0.1";

    /**
     * 服务器开启端口
     */
    public static final int SERVER_PORT = 8080;

    /**
     * 服务器连接数
     */
    public static final int BACK_LOG = 50;

    /**
     * 项目根路径
     */
    public static final String PROGRAM_PATH = "/home/coderam/development/workspace/TomcatLearn";

    /**
     * 项目配置文件根路径
     */
    public static final String RESOURCE_PATH = PROGRAM_PATH + "/src/main/resources";

    /**
     * 项目资源根路径
     */
    public static final String WEB_APP_PATH = PROGRAM_PATH + "/src/main/webapp";
}
