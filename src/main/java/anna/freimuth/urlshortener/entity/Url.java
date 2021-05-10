package anna.freimuth.urlshortener.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="url", schema = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name="long_url")
    private  String long_url;

    @Column(name="expiration_date")
    private Timestamp expiration_date;

    @Column(name="user_id")
    private  long user_id;

    public long getId() {
        return id;
    }

    public String getLong_url() {
        return long_url;
    }

    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
