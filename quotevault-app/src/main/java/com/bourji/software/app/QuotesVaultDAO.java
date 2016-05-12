package com.bourji.software.app;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import domain.Quote;
import io.dropwizard.hibernate.AbstractDAO;

public class QuotesVaultDAO extends AbstractDAO<Quote> {

    public QuotesVaultDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Quote> getAllQuotes() {
        Criteria cr = criteria();
        return list(cr);
    }

    public List<Quote> getQuotesByAuthor(String author) {
        Criteria cr = criteria();
        cr.add(Restrictions.eq("author", author));
        return list(cr);
    }

    public void addQuote(Quote quote) {
        persist(quote);
    }
}