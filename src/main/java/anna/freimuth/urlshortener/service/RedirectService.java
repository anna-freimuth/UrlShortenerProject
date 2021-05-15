package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.helper.StringShortenerHelper;
import anna.freimuth.urlshortener.repo.UrlRepo;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {

    private final UrlRepo urlRepo;

    public RedirectService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public LongUrlDto findLongUrl(String shortUrl) {
        long id = StringShortenerHelper.shortUrlToId(shortUrl);
        Url url = urlRepo.findById(id).get();
        return new LongUrlDto(url.getId(), url.getLongUrl(), url.getExpirationDate(), url.getUserId());
    }
}
