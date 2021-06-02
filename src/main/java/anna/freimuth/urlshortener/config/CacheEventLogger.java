package anna.freimuth.urlshortener.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheEventLogger
    implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(
        CacheEvent<?, ?> cacheEvent) {
        System.out.printf(
            "CacheEvent: Type %s - Key %s, Old Value %s, New Value %s%n",
            cacheEvent.getType(),
            cacheEvent.getKey(),
            cacheEvent.getOldValue(),
            cacheEvent.getNewValue()
        );
    }
}
