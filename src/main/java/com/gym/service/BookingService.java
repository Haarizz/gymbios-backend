// src/main/java/com/gym/service/BookingService.java
package com.gym.service;

import com.gym.entity.Booking;
import com.gym.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Page<Booking> getAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    public Optional<Booking> getById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking create(Booking booking) {
        booking.setId(null);
        return bookingRepository.save(booking);
    }

    public Booking update(Long id, Booking updated) {
        return bookingRepository.findById(id).map(b -> {
            b.setMemberId(updated.getMemberId());
            b.setTrainingClassId(updated.getTrainingClassId());
            b.setDate(updated.getDate());
            b.setTime(updated.getTime());
            b.setPrice(updated.getPrice());
            b.setStatus(updated.getStatus());
            b.setType(updated.getType());
            return bookingRepository.save(b);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}