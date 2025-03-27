package com.cicdtest.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFail {

    @Test
    public void failTest(){
        boolean t = false;
        Assertions.assertTrue(t);
    }
}
