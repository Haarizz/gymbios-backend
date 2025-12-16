package com.gym.repository;
import com.gym.entity.PosCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosCustomerRepository extends JpaRepository<PosCustomer, Long> {}