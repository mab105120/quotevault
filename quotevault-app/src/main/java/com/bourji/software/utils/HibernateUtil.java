package com.bourji.software.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Moe on 5/13/2016.
 */
public class HibernateUtil {

    private SessionFactory sessionFactory;
    private String configPath;

    public HibernateUtil(String configPath) {
        this.configPath = configPath;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure(configPath).buildSessionFactory();
            } catch (Throwable e) {
                throw new RuntimeException("Unable to create session factory. Details: " + e.getMessage());
            }
        }
        return sessionFactory;
    }
}