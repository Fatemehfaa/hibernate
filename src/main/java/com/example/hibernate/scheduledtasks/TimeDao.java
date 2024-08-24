package com.example.hibernate.scheduledtasks;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class TimeDao {

    RedissonClient redissonClient;
    RMap<String, Time> rMap;

    public TimeDao(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        this.rMap =redissonClient.getMap("Time");
    }

    public void save(Time time) {
        String id = UUID.randomUUID().toString();
        time.setId(id);
        rMap.put(id, time);
    }


    public List<Time> getAll() {
        return new ArrayList<>(rMap.values());
    }

    public void removeAll() {
        rMap.clear();
    }

}
