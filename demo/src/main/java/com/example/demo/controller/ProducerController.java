package com.example.demo.controller;

import com.example.demo.dto.RecordRequest;
import com.example.demo.model.Record;
import com.example.demo.producer.RecordProducer;
import com.example.demo.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProducerController {

    private final RecordProducer producer;
    private final RecordService service;

    public ProducerController(RecordProducer producer, RecordService service) {
        this.producer = producer;
        this.service  = service;
    }
    @PostMapping("/produce/create")
    public String create(@RequestBody RecordRequest req) throws InterruptedException {
        req.setAction("CREATE");
        producer.produce(req);
        return "Create request enqueued";
    }
    @PutMapping("/produce/update")
    public String update(@RequestBody RecordRequest req) throws InterruptedException {
        req.setAction("UPDATE");
        producer.produce(req);
        return "Update request enqueued";
    }
    @GetMapping("/records")
    public List<Record> getAll() {
        return service.findAll();
    }

}
        
        
        