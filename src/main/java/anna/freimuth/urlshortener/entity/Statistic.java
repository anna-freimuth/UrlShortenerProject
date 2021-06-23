package anna.freimuth.urlshortener.entity;

import javax.persistence.*;

@Entity
@Table(name="statistics", schema = "url")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private  String shortUrl;
    private  String longUrl;
    private long amount;

    public Statistic() {

    }

    public Statistic(String shortUrl, String longUrl, long amount) {

        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

}
