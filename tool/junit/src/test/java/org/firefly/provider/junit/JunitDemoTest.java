package org.firefly.provider.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JunitDemoTest {
    private JunitDemo junitDemo;

    @Before
    public void setUp() throws Exception {
        junitDemo = new JunitDemo();
    }

    @After
    public void tearDown() throws Exception {
        junitDemo = null;
    }

    @Test
    public void testGreet() {
        // 测试一般采用三段式结构：准备数据，执行动作，判断结果

        // 1.准备数据：调用方法的参数等的准备，这里没有

        // 2.执行动作
        String greetingWord = junitDemo.greet();

        // 3.判断结果
        assertEquals("Hello!", greetingWord);
    }
}