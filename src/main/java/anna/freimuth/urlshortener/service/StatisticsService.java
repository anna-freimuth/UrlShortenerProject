package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.StatisticDto;
import anna.freimuth.urlshortener.repo.UrlRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final UrlRepo urlRepo;

    public StatisticsService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public void countRedirects(long id, long countAdded) {
        urlRepo.addCount(id, countAdded);
    }

    public List<StatisticDto> getTopCalled() {
        return urlRepo.topCalled().stream().map(StatisticDto::new).collect(Collectors.toList());
    }
}
