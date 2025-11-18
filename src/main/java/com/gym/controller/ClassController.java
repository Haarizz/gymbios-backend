package com.gym.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gym.entity.ClassEntity;
import com.gym.service.ClassService;	

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClassEntity> list(){
        return service.list();
    }

    @PostMapping
    public ClassEntity create(@RequestBody ClassEntity c){
        return service.create(c);
    }

    @PutMapping("/{id}")
    public ClassEntity update(@PathVariable Integer id,@RequestBody ClassEntity c){
        return service.update(id,c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

