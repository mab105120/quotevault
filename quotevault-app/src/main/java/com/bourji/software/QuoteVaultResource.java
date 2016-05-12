package com.bourji.software;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bourji.software.app.QuotesVaultDAO;

import domain.Quote;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Created by Moe on 5/6/2016.
 */
@Path("/quotes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuoteVaultResource {

	private QuotesVaultDAO dao;

	QuoteVaultResource(QuotesVaultDAO dao) {
		this.dao = dao;
	}

	@GET
	@Path("/all")
	@UnitOfWork
	public List<Quote> getAllQuotes() {
		return dao.getAllQuotes();
	}

	@GET
	@UnitOfWork
	public List<Quote> getQuotesByAuthor(@QueryParam("author") String author) {
		return dao.getQuotesByAuthor(author);
	}

	@POST
	@UnitOfWork
	public void addQuote(Quote quote) {
		dao.addQuote(quote);
	}

}
