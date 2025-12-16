package com.gym.repository;

import com.gym.entity.Journal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

	Optional<Journal> findTopByOrderByIdDesc();



}
