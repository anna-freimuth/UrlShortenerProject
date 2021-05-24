package anna.freimuth.urlshortener.dto;

import java.sql.Timestamp;

public class LongUrlDto {

    private long id;
    private final String longUrl;
    private Timestamp expirationDate;
    private long userId;

    public LongUrlDto(long id, String longUrl, Timestamp expirationDate, long userId) {
        this.id= id;
        this.longUrl = longUrl;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setId(int id) {
        this.id = id;
    }
}
