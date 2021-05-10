package anna.freimuth.urlshortener.dto;

import java.sql.Timestamp;

public class LongUrlDto {

    private int id;
    private final String long_url;
    private Timestamp expiration_date;
    private int user_id;

    public LongUrlDto(int id, String long_url, Timestamp expiration_date, int user_id) {
        this.id= id;
        this.long_url = long_url;
        this.expiration_date = expiration_date;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setId(int id) {
        this.id = id;
    }
}
