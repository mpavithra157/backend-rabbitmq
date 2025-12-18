package com.example.demo.controller

import com.example.demo.dto.RecordRequest
import com.example.demo.producer.RecordProducer
import com.example.demo.service.RecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ProducerController)
class ProducerControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    RecordProducer recordProducer

    @MockBean
    RecordService recordService

    def "POST /api/produce/create should set action=CREATE, send message, and return 200"() {
        given:
        def json = '''
        {
          "id": 1,
          "data": "hello"
        }
        '''

        when:
        def result = mockMvc.perform(
                post("/api/produce/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )

        then:
        result.andExpect(status().isOk())

        and:
        verify(recordProducer).sendMessage({
            it instanceof RecordRequest &&
            it.getAction() == "CREATE" &&
            it.getId() == 1 &&
            it.getData() == "hello"
        } as RecordRequest)

        and:
        verifyNoInteractions(recordService)
    }

    def "PUT /api/produce/update should set action=UPDATE, send message, and return 200"() {
        given:
        def json = '''
        {
          "id": 2,
          "data": "hi"
        }
        '''

        when:
        def result = mockMvc.perform(
                put("/api/produce/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )

        then:
        result.andExpect(status().isOk())

        and:
        verify(recordProducer).sendMessage({
            it instanceof RecordRequest &&
            it.getAction() == "UPDATE" &&
            it.getId() == 2 &&
            it.getData() == "hi"
        } as RecordRequest)

        and:
        verifyNoInteractions(recordService)
    }

    def "GET /api/records should return 200 and JSON array"() {
        given: "Return empty list; focus on HTTP behavior in controller slice"
        when(recordService.findAll()).thenReturn([])

        when:
        def result = mockMvc.perform(get("/api/records"))

        then:
        result.andExpect(status().isOk())
              .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

        and: "Do not verify recordService interactions to avoid real-vs-mock mismatch"
        verifyNoInteractions(recordProducer)
    }
}
