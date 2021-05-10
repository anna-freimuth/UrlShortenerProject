package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.helper.StringShortenerHelper;
import anna.freimuth.urlshortener.repo.UrlRepo;
import org.springframework.stereotype.Service;

@Service
public class ShortenerService {

    private final UrlRepo urlRepo;

    public ShortenerService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public String saveLongUrl(LongUrlDto longUrlDto){
        Url url = new Url();

        url.setLong_url(longUrlDto.getLong_url());
        urlRepo.save(url);
        return StringShortenerHelper.idToShortUrl(url.getId());
    }
}
