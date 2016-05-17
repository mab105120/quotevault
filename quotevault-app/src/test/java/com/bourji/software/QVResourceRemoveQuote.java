package com.bourji.software;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Quote;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Moe on 5/15/2016.
 */
public class QVResourceRemoveQuote {

    @ClassRule
    public static final DropwizardAppRule<QuoteVaultConfiguration> RULE =
            new DropwizardAppRule<>(QuoteVaultApplicationTest.class, ResourceHelpers.resourceFilePath("test.yml"));

    @Test
    public void removeQuote() {
        final String END_POINT = "http://localhost:%d/quotes";
        Client client = new JerseyClientBuilder().build();
        client.target(String.format(END_POINT + "/removequote?id=1", RULE.getLocalPort()))
                .request()
                .delete();
        JsonNode jNode = client.target(String.format(END_POINT + "/all", RULE.getLocalPort()))
                .request()
                .get(JsonNode.class);
        List<Quote> quotes = new ObjectMapper().convertValue(jNode, new TypeReference<List<Quote>>() {
        });
        assertTrue(quotes.size() == 1);
        assertTrue(quotes.contains(new Quote("something else","someone else","somewhere else")));
        assertFalse(quotes.contains(new Quote("something","someone","somewhere")));
    }
}