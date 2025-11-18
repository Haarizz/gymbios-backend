package com.gym.service;

import com.gym.entity.ClassBooking;
import com.gym.repository.ClassBookingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassBookingService {
    private final ClassBookingRepository repo;

    public ClassBookingService(ClassBookingRepository repo) {
        this.repo = repo;
    }

    public List<ClassBooking> list() { return repo.findAll(); }
    public ClassBooking create(ClassBooking x) { return repo.save(x); }
    public ClassBooking update(Integer id, ClassBooking x) {
        x.setId(id);
        return repo.save(x);
    }

    public void delete(Integer id) { repo.deleteById(id); }
}
