package com.example.hibernate.scheduledtasks;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ScheduledTasks {
    TimeDao timeDao;

    @Scheduled(fixedDelay = 20000, initialDelay = 20000)
    public void average() throws InterruptedException {
        List<Time> times = timeDao.getAll();

        log.info("Average times : " +
                times.stream()
                        .mapToLong(Time::getTotalTime)
                        .average()
                        .orElseGet(() -> 0.0));

        timeDao.removeAll();
    }


}
