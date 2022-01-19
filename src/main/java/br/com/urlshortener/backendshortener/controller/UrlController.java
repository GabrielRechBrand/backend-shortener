package br.com.urlshortener.backendshortener.controller;

import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.dto.UrlDto;
import br.com.urlshortener.backendshortener.service.UrlService;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
        urlService.getUrl(alias).setAccessNumber(url.getAccessNumber() + 1);
        url.setLastAccessDate(Calendar.getInstance());
        urlService.updateUrl(url);
        URI uri = new URI(url.getOriginalUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);

    }

    @GetMapping("/urls/all")
    public List<Url> handleRedirect() {
        List<Url> urlList = urlService.findAllByOrderByAliasAsc();
        return urlList;
    }

    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody UrlDto urlDto) {
        return ResponseEntity.ok(urlService.createUrl(urlDto));
    }

    @DeleteMapping("/{alias}")
    @Transactional
    public ResponseEntity<?> deleteRedirect(@PathVariable String alias) {
        urlService.deleteByAlias(alias);
        return new ResponseEntity<>(alias, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<?> editRedirect(@PathVariable(value = "id") Long id, @Valid @RequestBody UrlDto urlDto) throws HibernateException {
        Url url = urlService.findById(id);
        url.setAlias(urlDto.getAlias());
        url.setOriginalUrl(urlDto.getOriginalUrl());
        final Url updatedUrl = urlService.updateUrl(url);
        return new ResponseEntity<>(updatedUrl, HttpStatus.OK);
    }

}

