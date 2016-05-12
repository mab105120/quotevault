package domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.testing.FixtureHelpers;
import domain.Quote;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Moe on 5/11/2016.
 */
public class QuoteTest {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void serializeToJson() throws IOException {

        final Quote quote = new Quote("Classes will dull your mind; destroy the potential for authentic creativity",
                "John Nash",
                "A Beautiful Mind: The Movie");
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(FixtureHelpers.fixture("quote.json"), Quote.class));
        System.out.println(expected);
    }

    @Test
    public void equals() {
        final Quote quote1 = new Quote("Classes will dull your mind; destroy the potential for authentic creativity",
                "John Nash",
                "A Beautiful Mind: The Movie");
        final Quote quote2 = new Quote("Classes will dull your mind; destroy the potential for authentic creativity",
                "John Nash",
                "A Beautiful Mind: The Movie");
        assertTrue(quote1.equals(quote2));
    }
}
