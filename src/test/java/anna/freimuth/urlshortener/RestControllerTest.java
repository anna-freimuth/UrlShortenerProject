package anna.freimuth.urlshortener;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.repo.UrlRepo;
import anna.freimuth.urlshortener.service.ShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class RestControllerTest {

    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;
    @MockBean
    ShortenerService shortenerService;
    @MockBean
    UrlRepo urlRepo;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    void returnStatusCreated() throws Exception {
        when(shortenerService.saveLongUrl(any(LongUrlDto.class))).thenReturn("coded");
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/url")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"long_url\": \"https://www.google.com\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("{\"short_url\": \"http://localhost:8080/coded\"}"))
        ;
    }
}
