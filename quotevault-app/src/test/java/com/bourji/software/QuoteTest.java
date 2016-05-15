package com.bourji.software;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Quote;
import io.dropwizard.testing.FixtureHelpers;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Moe on 5/14/16.
 */
public class QuoteTest {

    @Test
    public void deserializeQuoteFromJson() throws IOException {
        final ObjectMapper MAPPER = new ObjectMapper();
        Quote actual = MAPPER.readValue(FixtureHelpers.fixture("quote.json"), Quote.class);
        Quote expected = new Quote("Something", "Someone", "Somewhere");
        assertEquals(expected, actual);
    }

    @Test
    public void deserializeQuotesFromJson() throws IOException {
        final ObjectMapper MAPPER = new ObjectMapper();
        List<Quote> quotes = MAPPER.readValue(FixtureHelpers.fixture("quotes.json"),
                new TypeReference<List<Quote>>() { });
        assertTrue(quotes.size() == 2);
        assertTrue(quotes.contains(new Quote("something", "someone", "somewhere")));
        assertTrue(quotes.contains(new Quote("something else", "someone else", "somewhere else")));
    }

    @Test
    public void equalQuotes() {
        Quote thisQuote = new Quote("something","someone","somewhere");
        Quote thatQuote = new Quote("something","someone","somewhere");
        assertTrue(thisQuote.equals(thatQuote));
    }

    @Test
    public void equalsNonQuoteObject() {
        Quote q = new Quote("something","someone","somewhere");
        Object o = new Object();
        assertFalse(q.equals(o));
    }

    @Test
    public void quoteEqualToItself() {
        Quote q = new Quote("something","someone","somewhere");
        assertTrue(q.equals(q));
    }

    @Test
    public void compareUnequalQuotes() {
        Quote thisQuote = new Quote("someone","something","somewhere");
        Quote thatQuote = new Quote("someone else","something else","somwehere else");
        assertFalse(thisQuote.equals(thatQuote));
    }

}
