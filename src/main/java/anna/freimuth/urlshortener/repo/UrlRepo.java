package anna.freimuth.urlshortener.repo;

import anna.freimuth.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends CrudRepository<Url,Long> {
    @Query("SELECT u FROM Url u WHERE u.expirationDate >= current_date AND u.id = :id")
    Optional<Url> findNonExpiredById(@Param("id") Long id);
}
