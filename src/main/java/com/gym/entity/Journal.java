package com.gym.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journals", uniqueConstraints = {@UniqueConstraint(columnNames = {"voucher_no"})})
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voucher_no", unique = true)
    private String voucherNo;

    @Column(name = "journal_date")
    private LocalDate journalDate;

    private String reference;

    @Column(columnDefinition = "TEXT")
    private String narration;

    @Column(name = "total_debit")
    private Double totalDebit = 0.0;

    @Column(name = "total_credit")
    private Double totalCredit = 0.0;

    private String status = "Draft";

    @Column(name = "prepared_by")
    private String preparedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JournalLine> lines = new ArrayList<>();

    public Journal() {}

    // getters & setters (explicit)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVoucherNo() { return voucherNo; }
    public void setVoucherNo(String voucherNo) { this.voucherNo = voucherNo; }

    public LocalDate getJournalDate() { return journalDate; }
    public void setJournalDate(LocalDate journalDate) { this.journalDate = journalDate; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getNarration() { return narration; }
    public void setNarration(String narration) { this.narration = narration; }

    public Double getTotalDebit() { return totalDebit; }
    public void setTotalDebit(Double totalDebit) { this.totalDebit = totalDebit; }

    public Double getTotalCredit() { return totalCredit; }
    public void setTotalCredit(Double totalCredit) { this.totalCredit = totalCredit; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPreparedBy() { return preparedBy; }
    public void setPreparedBy(String preparedBy) { this.preparedBy = preparedBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<JournalLine> getLines() { return lines; }
    public void setLines(List<JournalLine> lines) {
        this.lines = new ArrayList<>();
        if (lines != null) {
            for (JournalLine ln : lines) {
                ln.setJournal(this);
                if (ln.getDebit() == null) ln.setDebit(0.0);
                if (ln.getCredit() == null) ln.setCredit(0.0);
                this.lines.add(ln);
            }
        }
    }

    /**
     * Replace lines and attach parent.
     */
    public void setLinesAndAttach(List<JournalLine> newLines) {
        this.lines.clear();
        if (newLines != null) {
            for (JournalLine ln : newLines) {
                ln.setJournal(this);
                if (ln.getDebit() == null) ln.setDebit(0.0);
                if (ln.getCredit() == null) ln.setCredit(0.0);
                this.lines.add(ln);
            }
        }
    }

    /**
     * Recalculate totals.
     */
    public void recalcTotals() {
        double d = 0.0, c = 0.0;
        for (JournalLine ln : this.lines) {
            d += ln.getDebit() == null ? 0.0 : ln.getDebit();
            c += ln.getCredit() == null ? 0.0 : ln.getCredit();
        }
        this.totalDebit = d;
        this.totalCredit = c;
    }
}
