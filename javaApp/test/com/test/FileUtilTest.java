package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.application.util.FileUtils;

@RunWith(JUnit4.class)
public class FileUtilTest {

    @Test
    public void testGetFilesName() {
        FileUtils.getFilesname("D:\\picture\\农药");
    }

}
