package anna.freimuth.urlshortener.controller;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.dto.ShortUrlDto;
import anna.freimuth.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/url")
@RestController
public class LongToShortUrlController {
    private final UrlService urlService;
    private final String HOST;

    public LongToShortUrlController(UrlService urlService, @Value("${host}") String host) {
        this.urlService = urlService;
        this.HOST = host;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlDto shortenLongUrl(@RequestBody LongUrlDto longUrlDto) {
        String shortenedUrl = urlService.saveLongUrl(longUrlDto);
        return new ShortUrlDto(HOST + "/" + shortenedUrl);
    }
}
