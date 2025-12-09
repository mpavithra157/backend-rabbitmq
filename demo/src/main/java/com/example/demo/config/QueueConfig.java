package com.example.demo.config;

import com.example.demo.dto.RecordRequest;
import org.springframework.context.annotation.*;

import java.util.concurrent.*;

@Configuration
public class QueueConfig {

    @Bean
    public BlockingQueue<RecordRequest> recordQueue() {
        return new LinkedBlockingQueue<>();
    }
}

        