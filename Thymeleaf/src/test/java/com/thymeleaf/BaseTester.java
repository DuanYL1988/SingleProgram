package com.thymeleaf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class BaseTester {

    @Test
    public void testStream() {
        String[] roleArr = { "admin", "fireemblem", "fgo" };
        List<String> roleList = new ArrayList<>(Arrays.asList(roleArr));
        System.out.println(roleList);
        // stream流
        List<String> list1 = roleList.stream() //
                .filter(role -> !"admin".equals(role)).collect(Collectors.toList());
        System.out.println(list1);
    }
}
