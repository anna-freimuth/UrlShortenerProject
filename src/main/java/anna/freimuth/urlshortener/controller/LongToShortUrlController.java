package anna.freimuth.urlshortener.controller;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.dto.ShortUrlDto;
import anna.freimuth.urlshortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/url")
@RestController
@CrossOrigin("http://localhost:4200")
public class LongToShortUrlController {

    private final ShortenerService shortenerService;
    private final String HOST;

    public LongToShortUrlController(ShortenerService shortenerService, @Value("${host}") String host) {
        this.shortenerService = shortenerService;
        this.HOST = host;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlDto shortenLongUrl(@RequestBody LongUrlDto longUrlDto) {
        String shortenedUrl = shortenerService.saveLongUrl(longUrlDto);
        return new ShortUrlDto(HOST + "/api/" + shortenedUrl);
    }
}
