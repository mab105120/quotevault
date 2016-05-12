package domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Moe on 5/6/2016.
 */
@Entity
@Table(name = "Quotes")
public class Quote {

	@Id
	@Column(name = "QUOTEID", nullable = false)
	private int quoteId;
	@Column(name = "AUTHOR")
	private String author;
	@Column(name = "BODY")
	private String body;
	@Column(name = "SOURCE")
	private String source;
	@Column(name = "DATE_INSERTED")
	private Date createdDate;

	public Quote(String body, String author, String source) {
		this.body = body;
		this.author = author;
		this.source = source;
	}

	public Quote() {
		// default no arg constructor for Jackson
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
}
