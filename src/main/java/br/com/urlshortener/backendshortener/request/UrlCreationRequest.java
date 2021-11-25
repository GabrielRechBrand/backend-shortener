package br.com.urlshortener.backendshortener.request;

import javax.validation.constraints.NotNull;

public class UrlCreationRequest {

    @NotNull
    private String alias;
    @NotNull
    private String originalUrl;

    public UrlCreationRequest(final String alias, final String originalUrl) {
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
        return "RedirectCreationRequest{" +
                "alias='" + alias + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                '}';
    }

}
