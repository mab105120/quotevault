package com.bourji.software;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * Created by Moe on 5/6/2016.
 */
public class QuoteVaultConfiguration extends Configuration {
	
	@Valid
	@NotNull
	@JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

	@JsonProperty("hibernate-cfg")
	private String hibernateConfig;

	public String getHibernateConfig() {
		return hibernateConfig;
	}

    public DataSourceFactory getDataSourceFactory() {
    	return this.database;
    }



}
