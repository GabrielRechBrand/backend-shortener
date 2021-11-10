package br.com.urlshortener.backendshortener.controller;

import br.com.urlshortener.backendshortener.model.Client;
import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/test")
public class TestController {

    @Autowired
    UrlRepository urlRepository;

    @GetMapping
    public String test() {

        List<Url> urls = urlRepository.findAll();
        return urls.stream().map(Url::getOriginalUrl).collect(Collectors.joining(" ,"));

    }
}

