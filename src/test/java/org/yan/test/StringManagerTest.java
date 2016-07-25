package org.yan.test;

/**
 * @author YanMeng
 * @date 16-7-25
 */
public class StringManagerTest {
    public static void main(String[] args) {
        System.out.println(
            StringManager.getManager("org.yan.test").getString("abc")
        );
        System.out.println(
            StringManager.getManager("org.yan.test").getString("def", "错误信息1", "错误信息2")
        );
    }
}
