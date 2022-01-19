package br.com.urlshortener.backendshortener.controller;

import br.com.urlshortener.backendshortener.model.Url;
import br.com.urlshortener.backendshortener.repository.UrlRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController("/app")
@CrossOrigin(origins = "http://localhost:8081")
public class VueController {

    @Autowired
    private UrlRepository urlRepository;

}
