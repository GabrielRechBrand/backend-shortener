package br.com.urlshortener.backendshortener.controller;

import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.repository.UrlRepository;
import br.com.urlshortener.backendshortener.request.UrlCreationRequest;
import br.com.urlshortener.backendshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
//@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException {
        Url url = urlService.getUrl(alias);
        System.out.println(url.getClient().getId());
        URI uri = new URI(url.getOriginalUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
    }

    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody UrlCreationRequest urlCreationRequest) {
        return ResponseEntity.ok(urlService.createUrl(urlCreationRequest));
    }
}

