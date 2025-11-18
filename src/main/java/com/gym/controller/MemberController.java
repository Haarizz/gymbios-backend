package com.gym.controller;

import com.gym.entity.Member;
import com.gym.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // -----------------------------
    // GET ALL MEMBERS
    // -----------------------------
    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // -----------------------------
    // GET MEMBER BY ID
    // -----------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -----------------------------
    // CREATE A NEW MEMBER
    // -----------------------------
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        try {
            Member saved = memberRepository.save(member);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // -----------------------------
    // UPDATE MEMBER
    // -----------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Long id,
            @RequestBody Member updatedMember) {

        return memberRepository.findById(id)
                .map(existingMember -> {

                    // Map fields one by one (IMPORTANT)
                    existingMember.setFirstname(updatedMember.getFirstname());
                    existingMember.setLastname(updatedMember.getLastname());
                    existingMember.setEmail(updatedMember.getEmail());
                    existingMember.setPhone(updatedMember.getPhone());
                    existingMember.setGender(updatedMember.getGender());
                    existingMember.setNationality(updatedMember.getNationality());
                    existingMember.setBirthday(updatedMember.getBirthday());
                    existingMember.setAddress(updatedMember.getAddress());
                    existingMember.setStatus(updatedMember.getStatus());
                    existingMember.setRole(updatedMember.getRole());
                    existingMember.setMemberId(updatedMember.getMemberId());

                    // Membership details
                    existingMember.setMembership_type(updatedMember.getMembership_type());
                    existingMember.setMembership_plan(updatedMember.getMembership_plan());

                    // Identity
                    existingMember.setId_type(updatedMember.getId_type());
                    existingMember.setId_number(updatedMember.getId_number());

                    // Health
                    existingMember.setMedicalConditions(updatedMember.getMedicalConditions());
                    existingMember.setMedications(updatedMember.getMedications());
                    existingMember.setChronicIllness(updatedMember.getChronicIllness());
                    existingMember.setAllergies(updatedMember.getAllergies());
                    existingMember.setBloodType(updatedMember.getBloodType());
                    existingMember.setHeight(updatedMember.getHeight());
                    existingMember.setWeight(updatedMember.getWeight());

                    // Emergency
                    existingMember.setEmergency_contact(updatedMember.getEmergency_contact());
                    existingMember.setEmergency_phone(updatedMember.getEmergency_phone());

                    Member saved = memberRepository.save(existingMember);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // -----------------------------
    // DELETE MEMBER
    // -----------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    memberRepository.delete(member);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
