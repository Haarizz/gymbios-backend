package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget_approvals")
public class BudgetApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // reference only by id (no join)
    private Long budgetId;

    // submitter name (frontend will pass staff name)
    private String submittedBy;

    private String status;
    @Column(columnDefinition = "text")
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // getters/setters...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Long getBudgetId(){return budgetId;}
    public void setBudgetId(Long budgetId){this.budgetId=budgetId;}
    public String getSubmittedBy(){return submittedBy;}
    public void setSubmittedBy(String submittedBy){this.submittedBy=submittedBy;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public String getComment(){return comment;}
    public void setComment(String comment){this.comment=comment;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}


	public void setUpdatedAt(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}


}
