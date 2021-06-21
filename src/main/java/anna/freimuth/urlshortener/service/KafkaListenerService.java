package anna.freimuth.urlshortener.service;


import anna.freimuth.urlshortener.dto.KafkaUrlDto;
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
    public void msgListener(String redirectString) {

        String[] strings = redirectString.split("___");
        KafkaUrlDto kafkaUrlDto = new KafkaUrlDto(strings[0], strings[1]);
        statisticsService.countRedirects(1, kafkaUrlDto);
    }
}
