package com.gym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

	Optional<Staff> findByEmployeeId(String empId);
}
