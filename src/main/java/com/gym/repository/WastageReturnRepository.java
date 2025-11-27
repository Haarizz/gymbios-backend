package com.gym.repository;

import com.gym.entity.WastageReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WastageReturnRepository extends JpaRepository<WastageReturn, Long> {
}
