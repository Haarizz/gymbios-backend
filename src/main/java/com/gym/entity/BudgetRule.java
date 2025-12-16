package com.gym.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "budget_rules")
public class BudgetRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private Boolean active = Boolean.TRUE;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public Boolean getActive(){return active;}
    public void setActive(Boolean active){this.active=active;}
}
