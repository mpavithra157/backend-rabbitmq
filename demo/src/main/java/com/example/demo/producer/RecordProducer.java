package com.example.demo.producer;
 
import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.RecordRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
 
@Component
public class RecordProducer {
 
    private final RabbitTemplate rabbitTemplate;
 
    public RecordProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
 
    public void sendMessage(RecordRequest request) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                request
        );
 
        System.out.println("[Producer] Sent message to RabbitMQ → " + request.getAction() + " : " + request.getData());
    }
}