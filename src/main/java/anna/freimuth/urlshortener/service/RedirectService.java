package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.helper.StringShortenerHelper;
import anna.freimuth.urlshortener.repo.UrlRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RedirectService {

    private final UrlRepo urlRepo;

    public RedirectService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public LongUrlDto findLongUrl(String shortUrl) {
        long id = StringShortenerHelper.shortUrlToId(shortUrl);
        Url url = unpackUrl(urlRepo.findNonExpiredById(id));
        return new LongUrlDto(url.getId(), url.getLongUrl(), url.getExpirationDate(), url.getUserId());
    }

    private Url unpackUrl(Optional<Url> url){
        if(url.isPresent()) return url.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "url is expired or does not exist");
    }
}
