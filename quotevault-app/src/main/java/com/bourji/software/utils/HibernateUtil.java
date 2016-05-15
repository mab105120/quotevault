package com.bourji.software.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Created by Moe on 5/13/2016.
 */
public class HibernateUtil {

    private SessionFactory sessionFactory;

    public HibernateUtil(String configPath) {
        try {
            File config = new File(configPath);
            sessionFactory = new Configuration().configure(config).buildSessionFactory();
        } catch (Throwable e) {
            throw new RuntimeException("Unable to create session factory. Details: " + e.getMessage());
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}