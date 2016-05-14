package com.bourji.software.app;

import com.bourji.software.utils.HibernateUtil;
import domain.Quote;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class QuotesVaultDAO {

    private HibernateUtil hibernate;

    public QuotesVaultDAO(HibernateUtil hibernate) {
        this.hibernate = hibernate;
    }

    public List<Quote> getAllQuotes() {
        Session session = hibernate.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Quote.class);
        List result = cr.list();
        List<Quote> quotes = new ArrayList<>();
        for (Object o : result) {
            Quote q = (Quote) o;
            quotes.add(q);
        }
        session.close();
        return quotes;
    }

    private Quote getQuoteById(int id) {
        Session session = hibernate.getSessionFactory().openSession();
        Quote q = (Quote) session.get(Quote.class, id);
        session.close();
        return q;
    }

    public List<Quote> getQuotesByAuthor(String author) {
        Session session = hibernate.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Quote.class);
        cr.add(Restrictions.eq("author", author));
        return cr.list();
    }

    public void addQuote(Quote quote) {
        Session session = hibernate.getSessionFactory().openSession();
        session.save(quote);
        session.close();
    }

    public void removeQuote(int id) {
        Session session = hibernate.getSessionFactory().openSession();
        session.delete(getQuoteById(id));
        session.close();
    }
}