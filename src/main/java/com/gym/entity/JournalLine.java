package com.gym.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "journal_lines")
public class JournalLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ledger;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double debit = 0.0;
    private Double credit = 0.0;

    private String costCentre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id")
    @JsonBackReference
    private Journal journal;

    public JournalLine() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLedger() { return ledger; }
    public void setLedger(String ledger) { this.ledger = ledger; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getDebit() { return debit; }
    public void setDebit(Double debit) { this.debit = debit; }

    public Double getCredit() { return credit; }
    public void setCredit(Double credit) { this.credit = credit; }

    public String getCostCentre() { return costCentre; }
    public void setCostCentre(String costCentre) { this.costCentre = costCentre; }

    public Journal getJournal() { return journal; }
    public void setJournal(Journal journal) { this.journal = journal; }
}
