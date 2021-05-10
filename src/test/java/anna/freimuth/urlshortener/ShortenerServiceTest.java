package anna.freimuth.urlshortener;
import anna.freimuth.urlshortener.helper.StringShortenerHelper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShortenerServiceTest {

    @Test
    public void max_8_char_number_decode() {

        String max8CharNumber = "eeeeeeee";
        long number = StringShortenerHelper.shortUrlToId(max8CharNumber);
        assertEquals(2821109907455L, number);
    }

    @Test
    public void max_8_char_number_encode() {

        long max8CharNumber = 2821109907455L;
        String encoded = StringShortenerHelper.idToShortUrl(max8CharNumber);
        assertEquals("eeeeeeee", encoded);
    }

    @Test
    public void min_number_decode() {

        String minNumber = "w";
        long number = StringShortenerHelper.shortUrlToId(minNumber);
        assertEquals(0L, number);
    }

    @Test
    public void min_number_encode() {

        long minNumber = 0L;
        String encoded = StringShortenerHelper.idToShortUrl(minNumber);
        assertEquals("w", encoded);
    }
}
