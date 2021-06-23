package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.KafkaUrlDto;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EnableKafka
@Service
public class KafkaListenerService {

    private final StatisticsService statisticsService;

    public KafkaListenerService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @KafkaListener(topics = "redirect_statistic", containerFactory = "kafkaListenerContainerFactory")
    public void msgListener(List<KafkaUrlDto> kafkaUrlDto) {

        Map<KafkaUrlDto, Long> kafkaUrlDtoSplitUp = kafkaUrlDto
            .stream()
            .collect(
                Collectors.groupingBy(
                    dto -> dto,
                    Collectors.counting()
                )
            );
        for (Map.Entry<KafkaUrlDto, Long> urlDtoEntry : kafkaUrlDtoSplitUp.entrySet()) {
            statisticsService.countRedirects(urlDtoEntry.getValue(), urlDtoEntry.getKey());
        }
    }
}
