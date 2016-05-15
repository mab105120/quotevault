package com.bourji.software;

import com.bourji.software.utils.HibernateUtil;
import io.dropwizard.testing.ResourceHelpers;

import java.net.URL;

/**
 * Created by Moe on 5/14/16.
 */
public class Test {

    @org.junit.Test
    public void test() {
        URL url = getClass().getClassLoader().getResource("hibernate.cfg.xml");
        String path;
        if (url != null)
            path = url.getPath();
        else
            throw new RuntimeException("Configuration file not found");
        new HibernateUtil(path);

    }

}
