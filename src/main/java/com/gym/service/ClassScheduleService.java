package com.gym.service;

import com.gym.entity.ClassSchedule;
import com.gym.repository.ClassScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassScheduleService {
    private final ClassScheduleRepository repo;

    public ClassScheduleService(ClassScheduleRepository repo){ this.repo = repo; }

    public List<ClassSchedule> list(){ return repo.findAll(); }

    public ClassSchedule create(ClassSchedule s){ return repo.save(s); }

    public ClassSchedule update(Integer id, ClassSchedule s){
        s.setId(id);
        return repo.save(s);
    }

    public void delete(Integer id){ repo.deleteById(id); }
}
