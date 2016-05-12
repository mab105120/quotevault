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
        QuotesVaultDAO dao = new QuotesVaultDAO(hibernate.getSessionFactory());
    	QuoteVaultResource qvResource = new QuoteVaultResource(dao);
        environment.jersey().register(qvResource);
    }
    
    private HibernateBundle<QuoteVaultConfiguration> hibernate = new HibernateBundle<QuoteVaultConfiguration>(Quote.class) {

		@Override
		public DataSourceFactory getDataSourceFactory(QuoteVaultConfiguration config) {
			return config.getDataSourceFactory();
		}
    	
    };
    
    public static void main(String[] args) throws Exception { 
    	new QuoteVaultApplication().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<QuoteVaultConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }
}