package com.example.demo.service;

import com.example.demo.model.Record;
import com.example.demo.repository.RecordRepository;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    private final RecordRepository repo;
    public RecordService(RecordRepository repo) {
        this.repo = repo;
    }
    public Record create(String data) {
        return repo.save(new Record(data));
    }

    public Record update(Long id, String data) {
        return repo.findById(id)
                .map(r -> {
                    r.setData(data);
                    return repo.save(r);
                })
                .orElseGet(() -> repo.save(new Record(data)));
    }

    public java.util.List<Record> findAll() {
        return repo.findAll();
    }

}         
        
        

    