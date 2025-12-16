package com.gym.controller;

import com.gym.dto.ProfileResponse;
import com.gym.dto.ProfileUpdateRequest;
import com.gym.entity.User;
import com.gym.entity.UserProfile;
import com.gym.repository.UserProfileRepository;
import com.gym.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserRepository userRepo;
    private final UserProfileRepository profileRepo;

    public ProfileController(UserRepository userRepo, UserProfileRepository profileRepo) {
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }

    // 🔹 GET PROFILE
    @GetMapping
    public ProfileResponse getProfile() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepo.findByEmail(email).orElseThrow();

        UserProfile profile = profileRepo.findByUser(user)
                .orElseGet(() -> {
                    UserProfile p = new UserProfile();
                    p.setUser(user);
                    return profileRepo.save(p);
                });

        ProfileResponse res = new ProfileResponse();
        res.email = user.getEmail();
        res.role = user.getRole();
        res.fullName = profile.getFullName();
        res.phoneNumber = profile.getPhoneNumber();
        res.address = profile.getAddress();

        return res;
    }

    // 🔹 UPDATE PROFILE
    @PutMapping
    public void updateProfile(@RequestBody ProfileUpdateRequest req) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepo.findByEmail(email).orElseThrow();

        UserProfile profile = profileRepo.findByUser(user).orElseThrow();

        profile.setFullName(req.fullName);
        profile.setPhoneNumber(req.phoneNumber);
        profile.setAddress(req.address);

        profileRepo.save(profile);
    }
}
