package com.bourji.software;

import com.bourji.software.app.QuotesVaultDAO;
import com.bourji.software.utils.HibernateUtil;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Created by Moe on 5/6/2016.
 */
public class QuoteVaultApplication extends Application<QuoteVaultConfiguration> {


    public void run(QuoteVaultConfiguration quoteVaultConfiguration, Environment environment) throws Exception {
    	QuoteVaultResource qvResource = new QuoteVaultResource(getQuotesVaultDAO());
        environment.jersey().register(qvResource);
    }

    protected QuotesVaultDAO getQuotesVaultDAO() {
        HibernateUtil hibernate = new HibernateUtil("hibernate.cfg.xml");
        return new QuotesVaultDAO(hibernate);
    }

    
    public static void main(String[] args) throws Exception { 
    	new QuoteVaultApplication().run(args);
    }

}