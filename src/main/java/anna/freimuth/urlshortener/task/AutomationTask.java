package anna.freimuth.urlshortener.task;

import anna.freimuth.urlshortener.repo.UrlScheduledRepo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class AutomationTask {

    private final UrlScheduledRepo urlScheduledRepo;

    public AutomationTask(UrlScheduledRepo urlScheduledRepo) {
        this.urlScheduledRepo = urlScheduledRepo;
    }

    @Scheduled(fixedRate = 5 * 1000 * 60)
    public void deleteExpired() {
        urlScheduledRepo.deleteExpired();
    }
}
