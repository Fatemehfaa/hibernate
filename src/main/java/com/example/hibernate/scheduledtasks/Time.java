package com.example.hibernate.scheduledtasks;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Time")
@Builder
public class Time implements Serializable {
    @Id
    private String id;
    private String className;
    private String methodName;
    private long totalTime;
}
