package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.KafkaUrlDto;
import anna.freimuth.urlshortener.dto.StatisticDto;
import anna.freimuth.urlshortener.entity.Statistic;
import anna.freimuth.urlshortener.repo.StatisticsRepo;
import kotlin.jvm.Synchronized;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final StatisticsRepo statisticsRepo;

    public StatisticsService(StatisticsRepo statisticsRepo) {
        this.statisticsRepo = statisticsRepo;
    }

    @Synchronized
    public void countRedirects(long countAdded, KafkaUrlDto kafkaUrlDto) {
        Optional<Statistic> previousEntry = statisticsRepo.findByShortUrl(kafkaUrlDto.shortUrl);
        if (previousEntry.isEmpty()) {
            Statistic newEntry = new Statistic(kafkaUrlDto.shortUrl, kafkaUrlDto.longUrl, countAdded);
            statisticsRepo.save(newEntry);
        } else {
            Statistic entry = previousEntry.get();
            entry.setAmount(entry.getAmount() + countAdded);
            statisticsRepo.save(entry);
        }
    }

    public List<StatisticDto> getTopCalled() {
        return statisticsRepo.topCalled().stream().map(StatisticDto::new).collect(Collectors.toList());
    }
}
