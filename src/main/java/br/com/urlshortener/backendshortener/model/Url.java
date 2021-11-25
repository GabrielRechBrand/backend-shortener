package br.com.urlshortener.backendshortener.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Url {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String shortenedUrl;
    private String alias;
    private String originalUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;  
    private Calendar creationDate;
    private Calendar lastAccessDate;
    private int accessNumber;

    public Url(String alias, String originalUrl, Calendar creationDate) {
        this.alias = alias;
        this.originalUrl = originalUrl;
        this.creationDate = creationDate;
    }

    public Url() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Calendar lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(int accessNumber) {
        this.accessNumber = accessNumber;
    }
}