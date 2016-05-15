package domain;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.*;

/**
 * Created by Moe on 5/6/2016.
 */
@Entity
@Table(name = "Quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUOTEID", nullable = false)
    private int quoteId;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "BODY")
    private String body;
    @Column(name = "SOURCE")
    private String source;
    @Column(name = "DATE_INSERTED")
    private Date createdDate = new Date(Calendar.getInstance().getTimeInMillis());

    public Quote(String body, String author, String source) {
        this.body = body;
        this.author = author;
        this.source = source;
    }

    public Quote() {
        // default no arg constructor for Jackson
    }

    public int getQuoteId() {
        return quoteId;
    }

    public String getBody() {
        return this.body;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getSource() {
        return source;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Quote))
            return false;
        if (o == this)
            return true;
        Quote that = (Quote) o;
        return author.equalsIgnoreCase(that.author)
                && body.equalsIgnoreCase(that.body)
                && source.equalsIgnoreCase(that.source);
    }
}