package org.yan.ex02;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @desc 请求处理器
 * @author YanMeng
 * @date 16-7-20
 */
public interface Processer {
    /**
     * 处理请求方法
     */
    public void process(ServletRequest request, ServletResponse response);
}
