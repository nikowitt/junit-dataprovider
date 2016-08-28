package com.tngtech.test.java.junit.dataprovider.junit5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(JUnitPlatform.class)
@ExtendWith(DataProviderMethodResolver.class)
public class DataProviderJUnit5AcceptanceTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("afterClass");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    public void tearDownEach() throws Exception {
        System.out.println("afterEach");
    }

    @AfterAll
    public static void tearDownAll() throws Exception {
        System.out.println("afterAll");
    }

    @DataProvider
    public static Object[][] dataProviderAdd() {
        //@formatter:off
        return new Object[][] {
            {  0,  0,  0 },
            {  0,  1,  1 },
            {  1,  0,  1 },
            {  1,  1,  2 },

            {  0, -1, -1 },
            { -1, -1, -2 },

            {  1, -1,  1 }, // fails
        };
        //@formatter:on
    }

    @org.junit.jupiter.api.Test
    // @ExtendWith(DataProviderMethodResolver.class)
    @UseDataProvider
    public void testAdd(int a, int b, int expected) throws Exception {
        System.out.println("test");

        // Expect:
        assertThat(a + b).isEqualTo(expected);
    }

    @org.junit.jupiter.api.Nested
    class Nested {
        // @DataProvider
        // public static Object[][] dataProviderAdd() {
//            //@formatter:off
//            return new Object[][] {
//                {  0,  0,  0 },
//                {  0,  1,  1 },
//                {  1,  0,  1 },
//                {  1,  1,  2 },
//
//                {  0, -1, -1 },
//                { -1, -1, -2 },
//
//                {  1, -1,  1 }, // fails
//            };
//            //@formatter:on
        // }
        //
        // @org.junit.jupiter.api.Test
        // @ExtendWith(DataProviderExtension.class)
        // @UseDataProvider
        // public void testAdd(int a, int b, int expected) throws Exception {
        // System.out.println("test");
        //
        // // Expect:
        // assertThat(a + b).isEqualTo(expected);
        // }

        @org.junit.jupiter.api.Test
        void myFirstTest() {
            assertThat(1 + 2).isEqualTo(3);
        }

        @TestFactory
        Collection<DynamicTest> dynamicTestsFromCollection() {
            return Arrays.asList(dynamicTest("dynamicTestsFromCollection[1]", () -> {
                System.out.println("dynamic");
                assertTrue(true);
            }), dynamicTest("dynamicTestsFromCollection[2]", () -> {
                System.out.println("dynamic");
                assertEquals(4, 2 * 2);
            }));
        }
    }

    @Ignore("to test ignore behavior")
    @UseDataProvider("dataProviderAdd")
    public void testAddIgnored(int a, int b, int expected) throws Exception {
        System.out.println("test");

        // Expect:
        assertThat(a + b).isEqualTo(expected);
    }

    @Test
    public void testMinus() throws Exception {
        System.out.println("test");

        // Expect:
        assertThat(2 - 1).isEqualTo(1);
    }

    @org.junit.jupiter.api.Test
    void myFirstTest() {
        assertThat(1 + 2).isEqualTo(3);
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(dynamicTest("dynamicTestsFromCollection[1]", () -> {
            System.out.println("dynamic");
            assertTrue(true);
        }), dynamicTest("dynamicTestsFromCollection[2]", () -> {
            System.out.println("dynamic");
            assertEquals(4, 2 * 2);
        }));
    }
}