package anna.freimuth.urlshortener.dto;

import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.helper.StringShortenerHelper;

import java.io.Serializable;

public class StatisticDto implements Serializable {

    public final String shortUrl;
    public final String longUrl;
    public long amount = 0;

    public StatisticDto(long id, String longUrl, long amount) {
        this.shortUrl = StringShortenerHelper.idToShortUrl(id);
        this.longUrl = longUrl;
        this.amount = amount;
    }

    public StatisticDto(Url url) {
        this.shortUrl = StringShortenerHelper.idToShortUrl(url.getId());
        this.longUrl = url.getLongUrl();
        this.amount = url.getAmount();
    }
}
