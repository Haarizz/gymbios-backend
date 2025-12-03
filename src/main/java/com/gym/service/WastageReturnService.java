package com.gym.service;

import com.gym.entity.WastageReturn;
import com.gym.repository.WastageReturnRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WastageReturnService {

    private final WastageReturnRepository repo;

    public WastageReturnService(WastageReturnRepository repo) {
        this.repo = repo;
    }

    public List<WastageReturn> getAll() {
        return repo.findAll();
    }

    public WastageReturn getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public WastageReturn save(WastageReturn wr) {
        return repo.save(wr);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}