package com.jobportal.dto;

import java.util.Base64;
import java.util.List;

import com.jobportal.entity.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a user's professional profile in the job portal.
 * This class is used to transfer profile-related data between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    /** Unique identifier for the profile */
    private Long id;

    /** Name of the user */
    private String name;

    /** Email address associated with the profile */
    private String email;

    /** Current or desired job title */
    private String jobTitle;

    /** Current or most recent company */
    private String company;

    /** Geographic location of the user */
    private String location;

    /** Brief professional summary or bio */
    private String about;

    /** Base64 encoded profile picture */
    private String picture;

    /** Total experience of the user */
    private Long totalExp;

    /** List of professional skills and competencies */
    private List<String> skills;

    /** List of work experiences */
    private List<Expirience> expiriences;

    /** List of professional certifications */
    private List<Certification> certifications;

    /** List of job IDs saved by the user for later reference */
    private List<Long> savedJobs;
}
