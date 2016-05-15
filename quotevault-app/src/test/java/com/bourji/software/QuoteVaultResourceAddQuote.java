package com.bourji.software;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Quote;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Moe on 5/14/2016.
 */
public class QuoteVaultResourceAddQuote {

    @ClassRule
    public static final DropwizardAppRule<QuoteVaultConfiguration> RULE =
            new DropwizardAppRule<>(QuoteVaultApplicationTest.class, ResourceHelpers.resourceFilePath("test.yml"));

    @org.junit.Test
    public void addQuote() {
        final String END_POINT = "http://localhost:%d/quotes";
        Quote quote = new Quote("something new", "someone new", "somewhere new");
        Client client = new JerseyClientBuilder().build();
        client.target(String.format(END_POINT, RULE.getLocalPort()))
                .request()
                .post(Entity.json(quote));
        JsonNode jNode = client.target(String.format(END_POINT + "/all", RULE.getLocalPort()))
                .request()
                .get(JsonNode.class);
        List<Quote> quotes = new ObjectMapper().convertValue(jNode, new TypeReference<List<Quote>>() {
        });
        assertTrue(quotes.size() == 3);
        assertTrue(quotes.contains(quote));
    }
}