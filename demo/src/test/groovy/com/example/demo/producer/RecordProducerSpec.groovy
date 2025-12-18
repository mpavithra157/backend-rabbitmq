package com.example.demo.producer
 
import com.example.demo.config.RabbitMQConfig
import com.example.demo.dto.RecordRequest
import org.springframework.amqp.rabbit.core.RabbitTemplate
import spock.lang.Specification
 
class RecordProducerSpec extends Specification {
 
    RabbitTemplate rabbitTemplate = Mock()
    RecordProducer producer = new RecordProducer(rabbitTemplate)
 
    def "sendMessage should publish message to RabbitMQ"() {
        given:
        RecordRequest request = new RecordRequest()
        request.setAction("CREATE")
        request.setData("sample-data")
 
        when:
        producer.sendMessage(request)
 
        then:
        1 * rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                request
        )
    }
 
    def "sendMessage should handle UPDATE action correctly"() {
        given:
        RecordRequest request = new RecordRequest()
        request.setAction("UPDATE")
        request.setData("update-data")
 
        when:
        producer.sendMessage(request)
 
        then:
        1 * rabbitTemplate.convertAndSend(_, _, request)
    }
}