package com.bourji.software;

import java.util.List;

import javax.ws.rs.*;
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
	@Path("/getquote")
	@UnitOfWork
	public List<Quote> getQuotesByAuthor(@QueryParam("author") String author) {
		return dao.getQuotesByAuthor(author);
	}

	@POST
	@UnitOfWork
	public void addQuote(Quote quote) {
		dao.addQuote(quote);
	}

	@DELETE
	@UnitOfWork
	@Path("/removequote")
	public void removeQuote(@QueryParam("id") int id) {
		dao.removeQuote(id);
	}
}