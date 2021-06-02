package anna.freimuth.urlshortener.repo;

import anna.freimuth.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UrlScheduledRepo extends CrudRepository<Url, Long> {

    @Transactional
    @Modifying
    @Query("delete from Url where current_timestamp > expirationDate")
    void deleteExpired();
}
