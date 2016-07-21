package org.yan.ex03.startup;

import org.yan.ex03.connector.HttpConnector;

/**
 * 启动器
 *
 * 这里将主方法抽出来并没有啥实际意义, 只是为后续章节扩展启动器打个基础
 *
 * @author YanMeng
 * @date 16-7-21
 */
public class BootStrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
