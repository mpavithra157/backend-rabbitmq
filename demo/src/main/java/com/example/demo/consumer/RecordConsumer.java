package com.example.demo.consumer;

import com.example.demo.dto.RecordRequest;
import com.example.demo.service.RecordService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class RecordConsumer {

    private final BlockingQueue<RecordRequest> queue;
    private final RecordService service;

    public RecordConsumer(BlockingQueue<RecordRequest> queue, RecordService service) {
        this.queue = queue;
        this.service = service;
    }

    @PostConstruct
    public void start() {
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                RecordRequest req = queue.take();  // Blocking
                System.out.println("[Consumer] processing: " + req.getData());

                if ("CREATE".equalsIgnoreCase(req.getAction())) {
                    service.create(req.getData());
                    } else if ("UPDATE".equalsIgnoreCase(req.getAction())) {
                        service.update(req.getId(), req.getData());
                    }
                }                    
        });
    }
}