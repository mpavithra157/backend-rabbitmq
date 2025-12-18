package com.example.demo.service
 
import com.example.demo.model.Record
import com.example.demo.repository.RecordRepository
import spock.lang.Specification
 
import java.util.Optional
 
class RecordServiceSpec extends Specification {
 
    RecordRepository repo = Stub(RecordRepository)
    RecordService service = new RecordService(repo)
 
    def "create should save new record"() {
        given:
        repo.save(_ as Record) >> new Record("test-data")
 
        when:
        def result = service.create("test-data")
 
        then:
        result != null
        result.data == "test-data"
    }
 
    def "update should update existing record"() {
        given:
        def existing = new Record("old")
        repo.findById(_ as Long) >> Optional.of(existing)
        repo.save(_ as Record) >> existing
 
        when:
        def result = service.update(1L, "new-data")
 
        then:
        result != null
        result.data == "new-data"
    }
 
    def "update should create new record if id not found"() {
        given:
        repo.findById(_ as Long) >> Optional.empty()
        repo.save(_ as Record) >> new Record("fallback")
 
        when:
        def result = service.update(99L, "fallback")
 
        then:
        result != null
        result.data == "fallback"
    }
 
    def "findAll should return all records"() {
        given:
        repo.findAll() >> [
                new Record("a"),
                new Record("b")
        ]
 
        when:
        def result = service.findAll()
 
        then:
        result != null
        result.size() == 2
        result[0].data == "a"
        result[1].data == "b"
    }
}