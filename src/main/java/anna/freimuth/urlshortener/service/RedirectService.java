package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.LongUrlDto;
import anna.freimuth.urlshortener.entity.Url;
import anna.freimuth.urlshortener.exception.EntityNotFoundException;
import anna.freimuth.urlshortener.helper.StringShortenerHelper;
import anna.freimuth.urlshortener.repo.UrlRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectService {

    private final UrlRepo urlRepo;
    private final KafkaProducerService kafkaProducerService;

    public RedirectService(UrlRepo urlRepo, KafkaProducerService kafkaProducerService) {
        this.urlRepo = urlRepo;
        this.kafkaProducerService = kafkaProducerService;
    }

    public LongUrlDto findLongUrl(String shortUrl) throws EntityNotFoundException {
        LongUrlDto response = _findLongUrl(shortUrl);
        kafkaProducerService.send(response.getId());
        return response;
    }

    @Cacheable(value = "longUrlCache", key = "#shortUrl")
    public LongUrlDto _findLongUrl(String shortUrl) throws EntityNotFoundException {
        long id = StringShortenerHelper.shortUrlToId(shortUrl);
        Url url = unpackUrl(urlRepo.findNonExpiredById(id));
        return new LongUrlDto(url.getId(), url.getLongUrl(), url.getExpirationDate(), url.getUserId());
    }

    private Url unpackUrl(Optional<Url> url) throws EntityNotFoundException {
        if (url.isPresent()) return url.get();
        throw new EntityNotFoundException("Url not found");
    }

}
