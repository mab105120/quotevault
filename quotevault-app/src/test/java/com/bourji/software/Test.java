package com.bourji.software;

import com.bourji.software.utils.HibernateUtil;
import domain.Quote;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;

/**
 * Created by Moe on 5/15/2016.
 */
public class Test {

    @org.junit.Test
    public void test() {
        URL url = getClass().getClassLoader().getResource("hibernate.cfg.xml");
        String path;
        if (url != null)
            path = url.getPath();
        else throw new RuntimeException("Unable to find hibernate configuration file");
        System.out.println(path);
        HibernateUtil hibernate = new HibernateUtil(path);
        Session session = hibernate.getSession();
        Criteria cr = session.createCriteria(Quote.class);
        List rslt = cr.list();
        for (Object o : rslt) {
            Quote q = (Quote) o;
            System.out.println(q.getBody());
        }
    }

}
