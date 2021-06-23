package anna.freimuth.urlshortener.dto;

import anna.freimuth.urlshortener.entity.Url;

import java.io.Serializable;
import java.sql.Timestamp;

public class LongUrlDto implements Serializable {

    private long id;
    private final String longUrl;
    private Timestamp expirationDate;
    private long userId;

    public LongUrlDto(long id, String longUrl, Timestamp expirationDate, long userId) {
        this.id = id;
        this.longUrl = longUrl;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public LongUrlDto(Url url) {
        this.id = url.getId();
        this.longUrl = url.getLongUrl();
        this.expirationDate = url.getExpirationDate();
        this.userId = url.getUserId();
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

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

}
