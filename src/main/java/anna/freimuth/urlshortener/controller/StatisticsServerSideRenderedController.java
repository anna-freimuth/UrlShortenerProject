package anna.freimuth.urlshortener.controller;

import anna.freimuth.urlshortener.dto.StatisticDto;
import anna.freimuth.urlshortener.service.StatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/statistics/web")
@Controller
public class StatisticsServerSideRenderedController {

    private final StatisticsService statisticsService;
    private final String host;

    public StatisticsServerSideRenderedController(StatisticsService statisticsService, @Value("${host}") String host) {
        this.statisticsService = statisticsService;
        this.host = host;
    }

    @GetMapping()
    public String statistics(Model model){
        List<StatisticDto> statistic = statisticsService.getTopCalled();
        model.addAttribute("statistic", statistic);
        model.addAttribute("host", host);
        return "statistics";
    }
}
