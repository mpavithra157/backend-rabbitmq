package com.example.demo.config;
 
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String EXCHANGE = "record_exchange";
    public static final String QUEUE = "record_queue";
    public static final String ROUTING_KEY = "record_routing_key";
 
    @Bean
    public Queue recordQueue() {
        return new Queue(QUEUE, true);
    }
 
    @Bean
    public DirectExchange recordExchange() {
        return new DirectExchange(EXCHANGE);
    }
 
    @Bean
    public Binding binding(Queue recordQueue, DirectExchange recordExchange) {
        return BindingBuilder.bind(recordQueue).to(recordExchange).with(ROUTING_KEY);
    }

    // JSON converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
 
    // RabbitTemplate with JSON converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}