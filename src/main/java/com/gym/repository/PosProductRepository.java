package com.gym.repository;
import com.gym.entity.PosProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PosProductRepository extends JpaRepository<PosProduct, Long> {
    List<PosProduct> findByCategory(String category);
}