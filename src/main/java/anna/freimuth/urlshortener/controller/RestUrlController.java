package anna.freimuth.urlshortener.controller;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.dto.ShortUrlDto;
import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.repo.UrlRepo;
import anna.freimuth.urlshortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RequestMapping("/url")
@RestController
public class RestUrlController {

    private final ShortenerService shortenerService;
    private final UrlRepo urlRepo;
    private final String HOST;

    public RestUrlController(ShortenerService shortenerService, UrlRepo urlRepo,  @Value("${host}") String host) {
        this.shortenerService = shortenerService;
        this.urlRepo = urlRepo;
        this.HOST = host;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlDto convertLongUrl(@RequestBody LongUrlDto longUrlDto){
            Url url = new Url();

            url.setLong_url(longUrlDto.getLong_url());
            urlRepo.save(url);
            String shortenedUrl = shortenerService.encode(url.getId());
            return new ShortUrlDto(HOST + "/" + shortenedUrl);
    }
}
