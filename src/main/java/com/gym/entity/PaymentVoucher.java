package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_voucher")
public class PaymentVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voucherNo;
    private String party;
    private String type; // Supplier/Vendor/Employee
    private String billNo;

    private LocalDate paymentDate;

    private Double amount;

    private String method;
    private String status;
    private String narration;
    private String createdBy;
    private String bankAccount;
    private String description;

    private Double paidAmount = 0.0;

    // OneToMany relationship to transactions/payments
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VoucherTransaction> payments = new ArrayList<>();

    // getters / setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVoucherNo() { return voucherNo; }
    public void setVoucherNo(String voucherNo) { this.voucherNo = voucherNo; }

    public String getParty() { return party; }
    public void setParty(String party) { this.party = party; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBillNo() { return billNo; }
    public void setBillNo(String billNo) { this.billNo = billNo; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNarration() { return narration; }
    public void setNarration(String narration) { this.narration = narration; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getBankAccount() { return bankAccount; }
    public void setBankAccount(String bankAccount) { this.bankAccount = bankAccount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }

    public List<VoucherTransaction> getPayments() { return payments; }
    public void setPayments(List<VoucherTransaction> payments) { this.payments = payments; }

    // helper to add payment
    public void addPayment(VoucherTransaction txn) {
        txn.setVoucher(this);
        payments.add(txn);
        this.paidAmount = (this.paidAmount == null ? 0.0 : this.paidAmount) + (txn.getAmount() == null ? 0.0 : txn.getAmount());
        if (this.amount != null && this.paidAmount >= this.amount) {
            this.status = "Paid";
        } else if (!"Draft".equals(this.status)) {
            this.status = "Pending";
        }
    }
}
