package anna.freimuth.urlshortener.controller;

import anna.freimuth.urlshortener.dto.StatisticDto;
import anna.freimuth.urlshortener.service.StatisticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/statistics")
@RestController
@CrossOrigin("http://localhost:4200")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping()
    public List<StatisticDto> statistics(){
        return statisticsService.getTopCalled();
    }
}
