package br.com.urlshortener.backendshortener.Dto;

import javax.validation.constraints.NotNull;

public class UrlDto {

    @NotNull
    private String alias;
    @NotNull
    private String originalUrl;

    public UrlDto(final String alias, final String originalUrl) {
        this.alias = alias;
        this.originalUrl = originalUrl;
    }

    public String getAlias() {
        return alias;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "alias='" + alias + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                '}';
    }

}
