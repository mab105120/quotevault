package com.bourji.software;

import com.bourji.software.app.QuotesVaultDAO;
import domain.Quote;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Moe on 5/6/2016.
 */
public class QuoteVaultApplication extends Application<QuoteVaultConfiguration> {

    public void run(QuoteVaultConfiguration quoteVaultConfiguration, Environment environment) throws Exception {
        QuoteVaultResource qvResource = new QuoteVaultResource(getDao());
        environment.jersey().register(qvResource);
    }

    protected QuotesVaultDAO getDao() {
        return new QuotesVaultDAO(hibernate.getSessionFactory());
    }

//    protected QuotesVaultDAO getQuotesVaultDAO(QuoteVaultConfiguration config) {
//        String path = config.getHibernateConfig();
//        HibernateUtil hibernate = new HibernateUtil(path);
//        return new QuotesVaultDAO(hibernate);
//    }

    protected final  HibernateBundle<QuoteVaultConfiguration> hibernate =
            new HibernateBundle<QuoteVaultConfiguration>(Quote.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(QuoteVaultConfiguration quoteVaultConfiguration) {
                    return quoteVaultConfiguration.getDataSourceFactory();
                }
            };

    @Override
    public void initialize(Bootstrap<QuoteVaultConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    public static void main(String[] args) throws Exception { 
    	new QuoteVaultApplication().run(args);
    }
}