package com.gym.repository;

import com.gym.entity.TrainingStream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingStreamRepository extends JpaRepository<TrainingStream, Long> {
    List<TrainingStream> findByTitleContainingIgnoreCaseOrInstructorContainingIgnoreCase(String title, String instructor);
    List<TrainingStream> findByStatusIgnoreCase(String status);
}
