package anna.freimuth.urlshortener.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="url", schema = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name="long_url")
    private  String longUrl;

    @Column(name="expiration_date")
    private Timestamp expirationDate = Timestamp.from(
            LocalDateTime.now().toInstant(ZoneOffset.UTC).plus(3L, ChronoUnit.DAYS)
    );

    @Column(name="user_id")
    private  long userId;

    @Column(name="amount")
    private  long amount;

    public long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public long getUserId() {
        return userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
