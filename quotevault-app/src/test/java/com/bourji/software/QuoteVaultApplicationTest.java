package com.bourji.software;

import com.bourji.software.app.QuotesVaultDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Quote;
import io.dropwizard.testing.FixtureHelpers;

import java.io.IOException;
import java.util.List;

/**
 * Created by Moe on 5/14/16.
 */
public class QuoteVaultApplicationTest extends QuoteVaultApplication {

    @Override
    protected QuotesVaultDAO getDao() {
        QuotesVaultDAO dao = new QuotesVaultDAO(hibernate.getSessionFactory());
        setupTestEnvironment(dao);
        return dao;
    }

    private void setupTestEnvironment(QuotesVaultDAO dao) {
        List<Quote> quotes;
        try {
            quotes = getTestQuotes();
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to get test quotes to setup test database");
        }
        dao.addQuotes(quotes);
    }

    private List<Quote> getTestQuotes() throws IOException {
        return new ObjectMapper().readValue(FixtureHelpers.fixture("quotes.json"),
                new TypeReference<List<Quote>>(){});
    }
}