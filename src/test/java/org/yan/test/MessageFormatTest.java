package org.yan.test;

import java.text.MessageFormat;

/**
 * @desc
 * @auther YanMeng
 * @data 16-7-25.
 */
public class MessageFormatTest {
    public static void main(String[] args) {
        System.out.printf(MessageFormat.format("{0}:{1}", "abc", "123"));
    }
}
