package com.example.demo.model;
 
import jakarta.persistence.*;
 
@Entity
public class Record {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String data;
 
    public Record() {}   
 
    public Record(String data) {
        this.data = data;
    }
 
    public Long getId() {
        return id;
    }
 
    public String getData() {
        return data;
    }
 
    public void setData(String data) {
        this.data = data;
    }
}
 
        
    
        
