/*
 * Copyright (c) 2017 TAN NGUYEN
 */
package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class SampleUnitTests.
 *
 * @author TanNguyen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class MoonParkUnitTests {

   /**
    * Just demo please read more description in test case file src/test/java/com/example/Test-Data/ut_0006.json
    * @throws Exception exception not expected
    */
    @Test
    public void ut_0006() throws Exception {
        String inputFilePath = "src/test/java/com/example/Test-Data/ut_0006.json";

        UtilTest.executeTestCase(inputFilePath);
    } 

   /**
    * Just demo please read more description in test case file src/test/java/com/example/Test-Data/ut_0001.json
    * @throws Exception exception not expected
    */
    @Test
    public void ut_0001() throws Exception {
        String inputFilePath = "src/test/java/com/example/Test-Data/ut_0001.json";

        UtilTest.executeTestCase(inputFilePath);
    } 

   /**
    * Just demo please read more description in test case file src/test/java/com/example/Test-Data/ut_0003.json
    * @throws Exception exception not expected
    */
    @Test
    public void ut_0003() throws Exception {
        String inputFilePath = "src/test/java/com/example/Test-Data/ut_0003.json";

        UtilTest.executeTestCase(inputFilePath);
    } 

   /**
    * Just demo please read more description in test case file src/test/java/com/example/Test-Data/ut_0002.json
    * @throws Exception exception not expected
    */
    @Test
    public void ut_0002() throws Exception {
        String inputFilePath = "src/test/java/com/example/Test-Data/ut_0002.json";

        UtilTest.executeTestCase(inputFilePath);
    } 

   /**
    * Just demo please read more description in test case file src/test/java/com/example/Test-Data/ut_0005.json
    * @throws Exception exception not expected
    */
    @Test
    public void ut_0005() throws Exception {
        String inputFilePath = "src/test/java/com/example/Test-Data/ut_0005.json";

        UtilTest.executeTestCase(inputFilePath);
    } 

   /**
    * Just demo please read more description in test case file src/test/java/com/example/Test-Data/ut_0004.json
    * @throws Exception exception not expected
    */
    @Test
    public void ut_0004() throws Exception {
        String inputFilePath = "src/test/java/com/example/Test-Data/ut_0004.json";

        UtilTest.executeTestCase(inputFilePath);
    } 


}
