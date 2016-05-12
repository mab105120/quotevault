package com.bourji.software.app;

import java.util.ArrayList;
import java.util.List;

import domain.Quote;

public class QuoteVaultService {

	private List<Quote> quotes = new ArrayList();

    public void addQuote(Quote quote) {
        this.quotes.add(quote);
    }

    public List<Quote> getAllQuotes() { 
    	return this.quotes;
    }
    
    public List<Quote> getQuotesByAuthor(String author) {
        List<Quote> authorQuotes = new ArrayList<Quote>();
        for(Quote q: quotes) { 
        	if(q.getAuthor().equalsIgnoreCase(author))
        		authorQuotes.add(q);
        }
        return authorQuotes;
    }

    public List<Quote> getQuotesThatInclude(String description) { 
    	List<Quote> authorQuotes = new ArrayList<>();
        for(Quote q: quotes) { 
        	if(q.getBody().contains(description))
        		authorQuotes.add(q);
        }
        return authorQuotes;	
    }
	
}
