// src/main/java/com/gym/entity/Booking.java
package com.gym.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // store references by id only (no JPA relationships)
    @NotNull
    @Column(name = "member_id")
    private Long memberId;

    @NotNull
    @Column(name = "training_class_id")
    private Long trainingClassId;

    @NotBlank
    private String date; // YYYY-MM-DD (or use LocalDate with converters)

    @NotBlank
    private String time; // HH:mm or ISO time

    private Double price;

    @NotBlank
    private String status; // pending, confirmed, checked-in, cancelled

    private String type; // "Class" or "Pt" or similar (optional, duplicated from TrainingClass for convenience)

    // standard getters/setters
    public Booking() {}

    // getters & setters...
    // (generate or use Lombok if desired)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public Long getTrainingClassId() { return trainingClassId; }
    public void setTrainingClassId(Long trainingClassId) { this.trainingClassId = trainingClassId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
