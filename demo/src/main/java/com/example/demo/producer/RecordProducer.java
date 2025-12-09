package com.example.demo.producer;

import com.example.demo.dto.RecordRequest;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class RecordProducer {

    private final BlockingQueue<RecordRequest> queue;

    public RecordProducer(BlockingQueue<RecordRequest> queue) {
        this.queue = queue;
    }

    public void produce(RecordRequest req) throws InterruptedException {
        queue.put(req);
        System.out.println("[Producer] queued: " + req.getData());
    }
}