package anna.freimuth.urlshortener;


import anna.freimuth.urlshortener.controller.RestUrlController;
import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.dto.ShortUrlDto;
import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.repo.UrlRepo;
import anna.freimuth.urlshortener.service.ShortenerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RestControllerTest {

    @MockBean
    ShortenerService shortenerService;
    @MockBean
    UrlRepo urlRepo;

    LongUrlDto longUrlDto = new LongUrlDto(0, "long_string", Timestamp.valueOf(LocalDateTime.MIN), 0);

    @Test
    public void convertLongUrl (){

       Mockito.when(shortenerService.encode(any(Long.class))).thenReturn("shortened");
       RestUrlController restUrlController = new RestUrlController(shortenerService, urlRepo, "localhost");

       ShortUrlDto shortUrlDto = restUrlController.convertLongUrl(longUrlDto);

       Mockito.verify(urlRepo, Mockito.times(1)).save(any(Url.class));
       Mockito.verify(shortenerService, Mockito.times(1)).encode(any(Long.class));
       assertEquals(shortUrlDto.short_url, "localhost/shortened");
    }

}
