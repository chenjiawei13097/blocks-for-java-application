package org.firefly.provider.junit;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AssertionDemoTest {
    @Test
    public void testAssertions() {
        // 这些断言都可以在第一个参数前插入失败时的提示消息

        assertEquals("test", "test");
        assertEquals(34.3, 35.6, 2);
        assertNotEquals("test01", "test02");
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        assertTrue(true);
        assertFalse(false); // assertTrue基础上对condition取反得到

        assertNull(null);
        assertNotNull(new Object());

        Object o1 = new Object();
        Object o2 = o1;
        assertSame(o1, o2);
        assertNotSame(new Object(), new Object());
    }

    @Test(expected = AssertionError.class)
    public void testFail() {
        fail();
    }

    @Test
    public void testAssertThat() {
        assertThat("Hello, World!", is("Hello, World!"));
        assertThat("Hello, World!", equalTo("Hello, World!"));
        assertThat("Hello, World!", startsWith("Hello"));
        assertThat("Hello, World!", endsWith("World!"));
        assertThat("Hello, World!", containsString("llo, Wor"));

        assertThat(6, not(8));

        assertThat("Hello, World!", anyOf(startsWith("Hello"), endsWith("Welcome")));
        assertThat("Hello, World!", either(startsWith("Hello")).or(endsWith("Welcome")));

        assertThat("Hello, World!", allOf(startsWith("Hello"), endsWith("World!")));
        assertThat("Hello, World!", both(startsWith("Hello")).and(endsWith("World!")));

        Object o = new Object();
        assertThat(o, sameInstance(o));

        assertThat(asList(1, 2, 3), hasItem(3));
        assertThat(asList(1, 2, 3), everyItem(not(8)));
    }
}
