package com.bourji.software;

import com.bourji.software.app.QuotesVaultDAO;
import com.bourji.software.utils.HibernateUtil;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.net.URL;

/**
 * Created by Moe on 5/6/2016.
 */
public class QuoteVaultApplication extends Application<QuoteVaultConfiguration> {

    public void run(QuoteVaultConfiguration quoteVaultConfiguration, Environment environment) throws Exception {
    	QuoteVaultResource qvResource = new QuoteVaultResource(getQuotesVaultDAO(quoteVaultConfiguration));
        environment.jersey().register(qvResource);
    }

    protected QuotesVaultDAO getQuotesVaultDAO(QuoteVaultConfiguration config) {
//        URL url = getClass().getClassLoader().getResource("hibernate.cfg.xml");
        String path = config.getHibernateConfig();
//        if (url != null)
//            path = url.getPath();
//        else throw new RuntimeException("Unable to find hibernate configuration file");
        System.out.println("PRINT configuration path: " + path);
        HibernateUtil hibernate = new HibernateUtil(path);
        return new QuotesVaultDAO(hibernate);
    }
    
    public static void main(String[] args) throws Exception { 
    	new QuoteVaultApplication().run(args);
    }
}