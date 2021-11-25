package br.com.urlshortener.backendshortener.controller;

import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.Dto.UrlDto;
import br.com.urlshortener.backendshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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
    @Transactional
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException {
        Url url = urlService.getUrl(alias);
        url.setAccessNumber(url.getAccessNumber() + 1);
        url.setLastAccessDate(Calendar.getInstance());
        urlService.updateUrl(url);
        URI uri = new URI(url.getOriginalUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
    }

    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody UrlDto urlDto) {
        return ResponseEntity.ok(urlService.createUrl(urlDto));
    }

    @GetMapping("/urls/all")
    public List<Url> handleRedirect() {
        List<Url> urlList = urlService.findAll();
        return urlList;
    }

}

