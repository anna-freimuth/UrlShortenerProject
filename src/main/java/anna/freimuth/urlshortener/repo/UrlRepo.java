package anna.freimuth.urlshortener.repo;

import anna.freimuth.urlshortener.entity.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends CrudRepository<Url,Integer> {
}
