package com.bourji.software;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Quote;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Client;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Moe on 5/13/2016.
 */
@Ignore
public class QuoteVaultResourceTest {

    @ClassRule
    public static final DropwizardAppRule<QuoteVaultConfiguration> RULE =
            new DropwizardAppRule<>(QuoteVaultApplication.class, ResourceHelpers.resourceFilePath("test.yml"));

    @Test
    public void test() {
        Client client = new JerseyClientBuilder().build();

        JsonNode jNode = client.target(String.format("http://localhost:%d/quotes/all", RULE.getLocalPort()))
                .request()
                .get(JsonNode.class);

        final ObjectMapper MAPPER = new ObjectMapper();

        List<Quote> quotes = MAPPER.convertValue(jNode, new TypeReference<List<Quote>>() {
        });

        assertEquals(2, quotes.size());


    }
}
