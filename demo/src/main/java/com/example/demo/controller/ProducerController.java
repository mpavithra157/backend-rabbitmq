package com.example.demo.controller;
 
import com.example.demo.dto.RecordRequest;
import com.example.demo.model.Record;
import com.example.demo.producer.RecordProducer;
import com.example.demo.service.RecordService;
 
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
 
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "Producer APIs", description = "APIs to send messages to RabbitMQ")
public class ProducerController {
 
    private final RecordProducer producer;
    private final RecordService service;
 
    public ProducerController(RecordProducer producer, RecordService service) {
        this.producer = producer;
        this.service  = service;
    }
 
    @PostMapping("/produce/create")
    @Operation(
        summary = "Send CREATE request to RabbitMQ",
        description = "Sends a CREATE action request to the RabbitMQ queue",
        responses = {
            @ApiResponse(responseCode = "200", description = "Request sent successfully")
        }
    )
    public String create(@RequestBody RecordRequest req) {
        req.setAction("CREATE");
        producer.sendMessage(req);
        return "Create request sent to RabbitMQ";
    }
 
    @PutMapping("/produce/update")
    @Operation(
        summary = "Send UPDATE request to RabbitMQ",
        description = "Sends an UPDATE action request to the RabbitMQ queue",
        responses = {
            @ApiResponse(responseCode = "200", description = "Request sent successfully")
        }
    )
    public String update(@RequestBody RecordRequest req) {
        req.setAction("UPDATE");
        producer.sendMessage(req);
        return "Update request sent to RabbitMQ";
    }
 
    @GetMapping("/records")
    @Operation(
        summary = "Get all records",
        description = "Fetch all records stored/processed",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "List of records returned",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Record.class)
                )
            )
        }
    )
    public List<Record> getAll() {
        return service.findAll();
    }
}