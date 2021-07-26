package anna.freimuth.urlshortener.controller;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.exception.EntityNotFoundException;
import anna.freimuth.urlshortener.service.RedirectService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

private RedirectService redirectService;

    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/api/{shortUrl}")
    public ResponseEntity<String> redirectToLongUrl(@PathVariable String shortUrl) throws EntityNotFoundException {
        LongUrlDto response = redirectService.findLongUrl(shortUrl);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location", response.getLongUrl());
        return ResponseEntity.status(301).headers(responseHeaders).body("");
    }
}
