package com.example.demo.consumer
 
import com.example.demo.dto.RecordRequest
import com.example.demo.service.RecordService
import spock.lang.Specification
 
class RecordConsumerSpec extends Specification {
 
    RecordService service = Mock()
    RecordConsumer consumer = new RecordConsumer(service)
 
    def "consumeMessage should call create when action is CREATE"() {
        given:
        def request = new RecordRequest()
        request.setAction("CREATE")
        request.setData("test-data")
 
        when:
        consumer.consumeMessage(request)
 
        then:
        1 * service.create("test-data")
        0 * _
    }
 
    def "consumeMessage should call update when action is UPDATE"() {
        given:
        def request = new RecordRequest()
        request.setAction("UPDATE")
        request.setId(1L)
        request.setData("updated-data")
 
        when:
        consumer.consumeMessage(request)
 
        then:
        1 * service.update(1L, "updated-data")
        0 * _
    }
 
    def "consumeMessage should do nothing for unknown action"() {
        given:
        def request = new RecordRequest()
        request.setAction("DELETE")
        request.setData("ignored")
 
        when:
        consumer.consumeMessage(request)
 
        then:
        0 * service._
    }
}