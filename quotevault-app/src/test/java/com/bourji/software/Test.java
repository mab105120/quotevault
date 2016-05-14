package com.bourji.software;

import io.dropwizard.testing.ResourceHelpers;

/**
 * Created by Moe on 5/14/2016.
 */
public class Test {

    @org.junit.Test
    public void test() {
        System.out.println(ResourceHelpers.resourceFilePath("test.yml"));
    }

}
