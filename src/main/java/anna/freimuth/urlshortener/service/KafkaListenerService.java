package anna.freimuth.urlshortener.service;


import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class KafkaListenerService {

    private final StatisticsService statisticsService;

    public KafkaListenerService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @KafkaListener(topics = "redirect_statistic")
    public void msgListener(Long id) {
        System.out.println(id);
        statisticsService.countRedirects(id, 1);
    }
}
