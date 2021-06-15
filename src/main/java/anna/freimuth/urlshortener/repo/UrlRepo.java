package anna.freimuth.urlshortener.repo;

import anna.freimuth.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepo extends CrudRepository<Url, Long> {
    @Query("SELECT u FROM Url u WHERE u.expirationDate >= current_date AND u.id = :id")
    Optional<Url> findNonExpiredById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Url SET amount = amount + :addCount WHERE id=:id")
    void addCount(@Param("id") Long id, @Param("addCount") Long addCount);

    @Query("SELECT  u FROM Url  u ORDER BY u.amount DESC")
    List<Url> topCalled();

}
