package ro.teamnet.zthbo.util;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {
    private final int TTL = 60000;
    private final Map<String, Tuple<Date, Object>> map = new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        map.put(key, new Tuple<>(new Date(), value));
    }

    public Object get(String key) {
        Tuple<Date, Object> tuple = map.get(key);
        if (tuple == null
                || new Date().getTime() - tuple.getT1().getTime() > TTL) {
            return null;
        }

        return tuple.getT2();
    }
}
