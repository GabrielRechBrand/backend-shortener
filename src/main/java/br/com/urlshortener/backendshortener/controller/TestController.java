package br.com.urlshortener.backendshortener.controller;

import br.com.urlshortener.backendshortener.model.Client;
import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
//@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @Autowired
    UrlRepository urlRepository;

    @GetMapping("/{shortened}")
    public ResponseEntity<?> test(@PathVariable String shortened) throws URISyntaxException {
        Url url = urlRepository.findByShortenedUrl(shortened);
        System.out.println(url.getClient().getId()  );
        URI uri = new URI(url.getOriginalUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
    }
}

