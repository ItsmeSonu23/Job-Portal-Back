package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ProfileDto;
import com.jobportal.service.ProfileService;
import java.util.List;

/**
 * REST controller for managing user profile operations.
 * Handles profile retrieval and updates.
 */
@RestController
@CrossOrigin // Allow requests from any origin
@Validated
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    /**
     * Retrieves a user profile by its ID.
     *
     * @param id The ID of the profile to retrieve
     * @return ResponseEntity containing the profile information
     * @throws Exception if profile retrieval fails
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDto> getProfiles(@PathVariable Long id) throws Exception{
        return new ResponseEntity<>(profileService.getProfile(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() throws Exception{
        return new ResponseEntity<>(profileService.getAllProfile(),HttpStatus.OK);
    }

    /**
     * Updates an existing user profile.
     *
     * @param profileDto The profile data to update
     * @return ResponseEntity containing the updated profile information
     * @throws Exception if profile update fails
     */
    @PutMapping("/update") 
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDto) throws Exception{
        return new ResponseEntity<>(profileService.updateProfile(profileDto),HttpStatus.OK);
    }
}
