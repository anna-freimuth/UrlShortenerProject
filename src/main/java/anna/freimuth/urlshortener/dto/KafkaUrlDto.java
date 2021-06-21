package anna.freimuth.urlshortener.dto;

import java.io.Serializable;

public class KafkaUrlDto implements Serializable {

    public String shortUrl;
    public String longUrl;

    public KafkaUrlDto(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }
}
