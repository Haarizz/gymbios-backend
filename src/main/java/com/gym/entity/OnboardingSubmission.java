package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "onboarding_submissions")
public class OnboardingSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Step 1: Business Basics ---
    private String businessName;
    private String yearsInBusiness;
    private String branches;

    @ElementCollection
    @CollectionTable(name = "onboarding_business_types", joinColumns = @JoinColumn(name = "submission_id"))
    @Column(name = "type")
    private List<String> businessType;

    // --- Step 2: Location (Embedded Object) ---
    @Embedded
    private LocationInfo location;

    // --- Step 3: Services & System ---
    @ElementCollection
    @CollectionTable(name = "onboarding_services", joinColumns = @JoinColumn(name = "submission_id"))
    @Column(name = "service_name")
    private List<String> services;

    private String software;

    @ElementCollection
    @CollectionTable(name = "onboarding_reasons", joinColumns = @JoinColumn(name = "submission_id"))
    @Column(name = "reason")
    private List<String> reasons;

    // --- Step 4: Goals & Contact ---
    @ElementCollection
    @CollectionTable(name = "onboarding_goals", joinColumns = @JoinColumn(name = "submission_id"))
    @Column(name = "goal")
    private List<String> goals;

    @Embedded
    private ContactInfo contact;

    @Column(updatable = false)
    private LocalDateTime submittedAt;

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
    }

    // ==========================
    // MANUAL GETTERS & SETTERS
    // ==========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getYearsInBusiness() { return yearsInBusiness; }
    public void setYearsInBusiness(String yearsInBusiness) { this.yearsInBusiness = yearsInBusiness; }

    public String getBranches() { return branches; }
    public void setBranches(String branches) { this.branches = branches; }

    public List<String> getBusinessType() { return businessType; }
    public void setBusinessType(List<String> businessType) { this.businessType = businessType; }

    public LocationInfo getLocation() { return location; }
    public void setLocation(LocationInfo location) { this.location = location; }

    public List<String> getServices() { return services; }
    public void setServices(List<String> services) { this.services = services; }

    public String getSoftware() { return software; }
    public void setSoftware(String software) { this.software = software; }

    public List<String> getReasons() { return reasons; }
    public void setReasons(List<String> reasons) { this.reasons = reasons; }

    public List<String> getGoals() { return goals; }
    public void setGoals(List<String> goals) { this.goals = goals; }

    public ContactInfo getContact() { return contact; }
    public void setContact(ContactInfo contact) { this.contact = contact; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    // ==========================
    // EMBEDDED CLASSES
    // ==========================
    
    @Embeddable
    public static class LocationInfo {
        private String country;
        private String state;
        private String city;
        private String memberCount;
        private String staffCount;
        @Column(columnDefinition = "TEXT")
        private String address;

        // Getters & Setters
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }

        public String getState() { return state; }
        public void setState(String state) { this.state = state; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getMemberCount() { return memberCount; }
        public void setMemberCount(String memberCount) { this.memberCount = memberCount; }

        public String getStaffCount() { return staffCount; }
        public void setStaffCount(String staffCount) { this.staffCount = staffCount; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }

    @Embeddable
    public static class ContactInfo {
        @Column(name = "contact_name")
        private String name;
        @Column(name = "contact_email")
        private String email;
        @Column(name = "contact_phone")
        private String phone;
        private String whatsapp;
        @Column(columnDefinition = "TEXT")
        private String notes;

        // Getters & Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getWhatsapp() { return whatsapp; }
        public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }
}