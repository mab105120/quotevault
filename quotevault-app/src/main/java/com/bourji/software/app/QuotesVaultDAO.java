package com.bourji.software.app;

import domain.Quote;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class QuotesVaultDAO {

    private SessionFactory sessionFactory;

//    private HibernateUtil hibernate;

//    public QuotesVaultDAO(HibernateUtil hibernate) {
//        this.hibernate = hibernate;
//    }

    public QuotesVaultDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Quote> getAllQuotes() {
        Session session = sessionFactory.openSession();
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
        Session session = sessionFactory.openSession();
        Quote q = (Quote) session.get(Quote.class, id);
        session.close();
        return q;
    }

    public List<Quote> getQuotesByAuthor(String author) {
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(Quote.class);
        cr.add(Restrictions.eq("author", author));
        List<Quote> quotesByAuthor = new ArrayList<>();
        for(Object o: cr.list()) {
            Quote q = (Quote) o;
            quotesByAuthor.add(q);
        }
        return quotesByAuthor;
    }

    public void addQuotes(List<Quote> quotes) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        quotes.forEach(session::save);
        session.getTransaction().commit();
        session.close();
    }

    public void addQuote(Quote quote) {
        Session session = sessionFactory.openSession();
        session.save(quote);
        session.close();
    }

    public void removeQuote(int id) {
        Quote tobeRemoved = getQuoteById(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(tobeRemoved);
        session.getTransaction().commit();
        session.close();
    }
}