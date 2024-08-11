package com.example.hibernate.distributedLock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class DistributedLock {
    public static void main(String[] args) {

        // Configuring Redis
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        // Acquiring the lock
        RLock lock = redisson.getLock("myLock");
        try {
            // Locking
            lock.lock();

            // Critical section
            System.out.println("Lock acquired, processing...");

        } finally {
            // Releasing the lock
            lock.unlock();
            System.out.println("Lock released.");
        }

        // Closing Redisson client
        redisson.shutdown();
    }

}
