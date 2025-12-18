package com.example.demo.model
 
import spock.lang.Specification
 
class RecordSpec extends Specification {
 
    def "no-arg constructor should create record"() {
        when:
        def record = new Record()
 
        then:
        record != null
        record.getId() == null
        record.getData() == null
    }
 
    def "parameterized constructor should set data"() {
        when:
        def record = new Record("test-data")
 
        then:
        record.getData() == "test-data"
    }
 
    def "setData should update data field"() {
        given:
        def record = new Record("old")
 
        when:
        record.setData("new")
 
        then:
        record.getData() == "new"
    }
}
 