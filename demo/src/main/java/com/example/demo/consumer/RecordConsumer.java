package com.example.demo.consumer;
 
import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.RecordRequest;
import com.example.demo.service.RecordService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
@Component
public class RecordConsumer {
 
    private final RecordService service;
 
    public RecordConsumer(RecordService service) {
        this.service = service;
    }
 
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessage(RecordRequest req) {
        System.out.println("[Consumer] Received: " + req.getAction() + " : " + req.getData());
 
        if ("CREATE".equalsIgnoreCase(req.getAction())) {
            service.create(req.getData());
        } else if ("UPDATE".equalsIgnoreCase(req.getAction())) {
            service.update(req.getId(), req.getData());
        }
    }
}