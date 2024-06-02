package com.example.central_dogma;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ConcurrentReferenceHashMap;

@Slf4j
@RequiredArgsConstructor
public class InMemoryQueryHolder implements QueryHolder{

    private final ConcurrentReferenceHashMap<String, String> map;

    @Override
    public void initCache(String fileName, String query) {
        log.info("Hold query : {}", fileName);
        map.putIfAbsent(fileName, query);
    }

    @Override
    public void updateCache(String fileName, String query) {
        log.info("Update query : {}", fileName);
        var val = map.get(fileName);
        if (val == null) {
            log.error("doesnt exist query cache");
            return;
        }
        map.replace(fileName, query);
        log.info("Success for updating query : {}", fileName);
    }
}
