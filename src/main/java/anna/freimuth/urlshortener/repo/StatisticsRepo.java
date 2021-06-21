package anna.freimuth.urlshortener.repo;

import anna.freimuth.urlshortener.entity.Statistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatisticsRepo extends CrudRepository<Statistic, Long> {

    @Query("SELECT s FROM Statistic s WHERE s.shortUrl = :shortUrl")
    Optional<Statistic> findByShortUrl(@Param("shortUrl") String shortUrl);

    @Query("SELECT  u FROM Statistic u ORDER BY u.amount DESC")
    List<Statistic> topCalled();
}
