package com.gym.repository;

import com.gym.entity.ClassBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassBookingRepository extends JpaRepository<ClassBooking, Integer> {
	
}
