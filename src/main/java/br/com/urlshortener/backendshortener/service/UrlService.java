package br.com.urlshortener.backendshortener.service;

import br.com.urlshortener.backendshortener.exception.BadRequestException;
import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.repository.UrlRepository;
import br.com.urlshortener.backendshortener.Dto.UrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<Url> createUrl(UrlDto urlCreationRequest) {
        if(urlRepository.existsByAlias(urlCreationRequest.getAlias())) {
            throw new BadRequestException("Url already exists!");
        }
        System.out.println("Url request: " + urlCreationRequest.toString());
        Url url = new Url(urlCreationRequest.getAlias(), urlCreationRequest.getOriginalUrl(), Calendar.getInstance());

        Url postSaveUrl = urlRepository.save(url);
        System.out.println("Url: " + postSaveUrl);


        return Optional.ofNullable(postSaveUrl);
    }

    public Url getUrl(String alias) {
        Url url = urlRepository.findByAlias(alias);
        return url;
    }

    public void updateUrl(Url url) {
        urlRepository.save(url);
    }

    public List<Url> findAll() {
        List<Url> urlList = urlRepository.findAll();
        return urlList;
    }
}
