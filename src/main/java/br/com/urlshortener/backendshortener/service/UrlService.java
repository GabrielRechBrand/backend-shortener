package br.com.urlshortener.backendshortener.service;

import br.com.urlshortener.backendshortener.exception.BadRequestException;
import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.repository.UrlRepository;
import br.com.urlshortener.backendshortener.request.UrlCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UrlService {

    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<Url> createUrl(UrlCreationRequest urlCreationRequest) {
        if(urlRepository.existsByAlias(urlCreationRequest.getAlias())) {
            throw new BadRequestException("Url already exists!");
        }
        System.out.println("Url request: " + urlCreationRequest.toString());
        Url url = new Url(urlCreationRequest.getAlias(), urlCreationRequest.getOriginalUrl());

        Url postSaveUrl = urlRepository.save(url);
        System.out.println("Url: " + postSaveUrl);

        return Optional.ofNullable(postSaveUrl);
    }

    public Url getUrl(String alias) {
        Url url = urlRepository.findByAlias(alias);
        return url;
    }
}
