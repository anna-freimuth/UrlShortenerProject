package anna.freimuth.urlshortener;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.repo.UrlRepo;
import anna.freimuth.urlshortener.service.RedirectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class RedirectControllerTest {

    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;
    @MockBean
    RedirectService redirectService;

    @MockBean
    UrlRepo urlRepo;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    void redirectToLongUrl() throws Exception {
        String shortUrl = "shortUrl";
        String longUrl = "https://www.example.org";
        LongUrlDto longUrlDto = new LongUrlDto(5, longUrl, Timestamp.valueOf(LocalDateTime.now()), 0L);
        when(redirectService.findLongUrl(shortUrl))
                .thenReturn(longUrlDto);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/"+ shortUrl))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location", longUrl))
                .andReturn();
        ;
    }
}
