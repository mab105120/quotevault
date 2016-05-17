package com.bourji.software;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Quote;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Moe on 5/16/2016.
 */
public class QVResourceGetQuotesByAuthor {

    @ClassRule
    public static final DropwizardAppRule<QuoteVaultConfiguration> RULE
            = new DropwizardAppRule<>(QuoteVaultApplicationTest.class, ResourceHelpers.resourceFilePath("test.yml"));

    @Before
    public void createQuotesWithSameAuthor() {
        Client client = new JerseyClientBuilder().build();
        Quote q1 = new Quote("something", "sameone", "somewhere");
        client.target(String.format("http://localhost:%d/quotes/getquotes?author=sameone", RULE.getLocalPort()))
                .request()
                .post(Entity.json(q1));
        Quote q2 = new Quote("something else", "sameone", "somewhere else");
        client.target(String.format("http://localhost:%d/quotes", RULE.getLocalPort()))
                .request()
                .post(Entity.json(q2));
    }

    @Test
    public void getQuotesByAuthor() {
        Client client = new JerseyClientBuilder().build();
        List<Quote> quotesBySameAuthor;
        JsonNode jNode = client.target(String.format("http://localhost:%d/quotes", RULE.getLocalPort()))
                .request()
                .get(JsonNode.class);
        quotesBySameAuthor = new ObjectMapper().convertValue(jNode, new TypeReference<List<Quote>>() {
        });
        assertEquals(2, quotesBySameAuthor.size());
    }
}