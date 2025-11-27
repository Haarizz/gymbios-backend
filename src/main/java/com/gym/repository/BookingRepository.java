// src/main/java/com/gym/repository/BookingRepository.java
package com.gym.repository;

import com.gym.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Add custom queries if needed (findByMemberId, findByTrainingClassId)
}
