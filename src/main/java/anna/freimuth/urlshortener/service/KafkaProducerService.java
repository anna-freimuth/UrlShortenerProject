package anna.freimuth.urlshortener.service;

import anna.freimuth.urlshortener.dto.KafkaUrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(KafkaUrlDto kafkaUrlDto) {
        kafkaTemplate.send("redirect_statistic", kafkaUrlDto.shortUrl + "___" + kafkaUrlDto.longUrl);
    }

}
