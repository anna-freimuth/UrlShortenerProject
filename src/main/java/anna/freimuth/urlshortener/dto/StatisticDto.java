package anna.freimuth.urlshortener.dto;

import anna.freimuth.urlshortener.entity.Statistic;

import java.io.Serializable;

public class StatisticDto implements Serializable {

    public final long id;
    public final String shortUrl;
    public final String longUrl;
    public long amount = 0;

    public StatisticDto(long id, String shortUrl, String longUrl, long amount) {
        this.shortUrl = shortUrl;
        this.id = id;
        this.longUrl = longUrl;
        this.amount = amount;
    }

    public StatisticDto(Statistic statistic) {
        this.shortUrl = statistic.getShortUrl();
        this.id = statistic.getId();
        this.longUrl = statistic.getLongUrl();
        this.amount = statistic.getAmount();
    }

}
