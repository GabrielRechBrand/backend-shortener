package br.com.urlshortener.backendshortener.dto;

import javax.validation.constraints.NotNull;

public class UrlDto {

    @NotNull
    private String alias;
    @NotNull
    private String originalUrl;
    @NotNull
    private String shortenedUrl;

    public UrlDto(final String alias, final String originalUrl, final String shortenedUrl) {
        this.alias = alias;
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
    }

    public String getAlias() {
        return alias;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "alias='" + alias + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortenedUrl='" + shortenedUrl + '\'' +
                '}';
    }

}
