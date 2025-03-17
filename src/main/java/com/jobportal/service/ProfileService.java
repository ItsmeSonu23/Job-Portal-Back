package com.jobportal.service;

import com.jobportal.dto.ProfileDto;
import com.jobportal.exception.JobPortalException;
import java.util.List;

/**
 * Service interface defining profile management operations.
 * Handles creating, retrieving and updating user profiles.
 */
public interface ProfileService {
 
    /**
     * Creates a new profile for a user with the given email.
     *
     * @param email The email address of the user
     * @return Long ID of the created profile
     * @throws JobPortalException if profile creation fails
     */
    public Long createProfile(String email) throws JobPortalException;

    /**
     * Retrieves a profile by its ID.
     *
     * @param id The ID of the profile to retrieve
     * @return ProfileDto containing the profile information
     * @throws JobPortalException if profile not found
     */
        public ProfileDto getProfile(Long id) throws JobPortalException;

    /**
     * Retrieves all profiles.
     *
     * @return List of ProfileDto containing all profiles
     * @throws JobPortalException if profiles retrieval fails
     */
    public List<ProfileDto> getAllProfile() throws JobPortalException;

    /**
     * Updates an existing profile with new information.
     *
     * @param profileDto The profile data to update
     * @return ProfileDto containing the updated profile information
     * @throws JobPortalException if profile not found or update fails
     */
    public ProfileDto updateProfile(ProfileDto profileDto) throws JobPortalException;
    
}
