package anna.freimuth.urlshortener;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.repo.UrlRepo;
import anna.freimuth.urlshortener.service.RedirectService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class RedirectServiceTest {

    @MockBean
    UrlRepo urlRepo;

    @Test
    void findLongUrl() {

        RedirectService redirectService = new RedirectService(urlRepo);

        String shortUrl = "do";
        Long id = 103L;
        String longUrlExpected = "https://www.example.org";

        Url tmpDatabaseObject = new Url();
        tmpDatabaseObject.setId(id);
        tmpDatabaseObject.setLongUrl(longUrlExpected);
        Optional<Url> databaseObject = Optional.of(tmpDatabaseObject);

        when(urlRepo.findNonExpiredById(id)).thenReturn(databaseObject);

        LongUrlDto response = redirectService.findLongUrl(shortUrl);

        assertEquals(longUrlExpected, response.getLongUrl());
        assertEquals(id, response.getId());
    }

    @Test
    void expiredLongUrl() {

        RedirectService redirectService = new RedirectService(urlRepo);

        String shortUrl = "do";
        Long id = 103L;

        Optional<Url> databaseObject = Optional.empty();

        when(urlRepo.findNonExpiredById(id)).thenReturn(databaseObject);

        assertThrows(ResponseStatusException.class, () -> redirectService.findLongUrl(shortUrl));
    }
}

