package com.jobportal.service;

import java.util.ArrayList;
import java.util.Base64;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.ProfileDto;
import com.jobportal.entity.Profile;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repo.ProfileRepo;
import com.jobportal.utility.Utilities;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Implementation of the ProfileService interface that provides profile management functionality.
 * Handles creating, retrieving and updating user profiles.
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Creates a new profile for a user with the given email.
     * Initializes empty lists for skills, experiences and certifications.
     *
     * @param email The email address of the user
     * @return Long ID of the created profile
     * @throws JobPortalException if profile creation fails
     */
    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExpiriences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profileRepo.save(profile);
        return profile.getId();
    }

    /**
     * Retrieves a profile by its ID.
     * Fetches the profile from the repository and maps it to a DTO.
     *
     * @param id The ID of the profile to retrieve
     * @return ProfileDto containing the profile information
     * @throws JobPortalException if profile not found
     */
    @Override
    public ProfileDto getProfile(Long id) throws JobPortalException {
        // Fetch the profile entity from the repository
        Profile profile = profileRepo.findById(id).orElseThrow(() -> new JobPortalException("PROFILE_NOT_FOUND"));
        // Convert the Profile entity to ProfileDto using ModelMapper
        return modelMapper.map(profile, ProfileDto.class);
    }

    /**
     * Updates an existing profile with new information.
     * Handles profile picture updates by decoding Base64 image data.
     *
     * @param profileDto The profile data to update
     * @return ProfileDto containing the updated profile information
     * @throws JobPortalException if profile not found or update fails
     */
    @Override
    public ProfileDto updateProfile(ProfileDto profileDto) throws JobPortalException {
       Profile existingProfile =  profileRepo.findById(profileDto.getId()).orElseThrow(() -> new JobPortalException("PROFILE_NOT_FOUND"));
        if (profileDto.getPicture() != null) {
            existingProfile.setPicture(Base64.getDecoder().decode(profileDto.getPicture()));
        }
    
        profileRepo.save(modelMapper.map(profileDto, Profile.class));
        // Convert the Profile entity to ProfileDto using ModelMapper
        return profileDto;
        }

    /**
     * Retrieves all profiles.
     * Fetches all profiles from the repository and maps them to a list of ProfileDto.
     *
     * @return List of ProfileDto containing all profiles
     * @throws JobPortalException if profiles retrieval fails
     */
    @Override
    public List<ProfileDto> getAllProfile() throws JobPortalException {
        List<Profile> profiles = profileRepo.findAll();
        return profiles.stream().map(profile -> modelMapper.map(profile, ProfileDto.class)).collect(Collectors.toList());
    }
}
