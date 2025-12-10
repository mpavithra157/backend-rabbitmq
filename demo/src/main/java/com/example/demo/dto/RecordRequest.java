package com.example.demo.dto;
 
import io.swagger.v3.oas.annotations.media.Schema;
 
@Schema(description = "Request object for Producer API")
public class RecordRequest {
 
    @Schema(description = "Unique record ID", example = "101")
    private Long id;
 
    @Schema(description = "Data content of the record", example = "Sample data")
    private String data;
 
    @Schema(description = "Action type for the record (CREATE or UPDATE)", example = "CREATE")
    private String action; // CREATE / UPDATE
 
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
 
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
 
    @Override
    public String toString() {
        return "RecordRequest{id=" + id + ", data='" + data + "', action='" + action + "'}";
    }
}

    
        