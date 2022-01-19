package br.com.urlshortener.backendshortener.service;

import br.com.urlshortener.backendshortener.exception.BadRequestException;
import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.repository.UrlRepository;
import br.com.urlshortener.backendshortener.dto.UrlDto;

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
        String shortenedUrl = "http://localhost:8080/" + urlCreationRequest.getAlias();
        Url url = new Url(shortenedUrl, urlCreationRequest.getAlias(), urlCreationRequest.getOriginalUrl(), Calendar.getInstance(), Calendar.getInstance());

        Url postSaveUrl = urlRepository.save(url);
        System.out.println("Url: " + postSaveUrl);

        return Optional.ofNullable(postSaveUrl);
    }

    public Url getUrl(String alias) {
        Url url = urlRepository.findByAlias(alias);
        return url;
    }

    public Url findById(Long id) {
        Url url = urlRepository.getById(id);
        return url;
    }

    public Url updateUrl(Url url) {
        urlRepository.save(url);
        return url;
    }

    public List<Url> findAllByOrderByAliasAsc() {
        List<Url> urlList = urlRepository.findAllByOrderByAliasAsc();
        return urlList;
    }

    public void deleteByAlias(String alias) {
        urlRepository.deleteByAlias(alias);
    }

}
