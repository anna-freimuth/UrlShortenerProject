package anna.freimuth.urlshortener.dto;

import java.io.Serializable;
import java.util.Objects;

public class KafkaUrlDto implements Serializable {

    public String shortUrl;
    public String longUrl;

    public KafkaUrlDto(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public KafkaUrlDto() {
    }

    public String getShortUrl() {
        return this.shortUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaUrlDto that = (KafkaUrlDto) o;
        return shortUrl.equals(that.shortUrl) && longUrl.equals(that.longUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortUrl, longUrl);
    }
}
