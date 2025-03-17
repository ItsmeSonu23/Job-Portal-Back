package com.jobportal.entity;

import java.util.Base64;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jobportal.dto.Certification;
import com.jobportal.dto.Expirience;
import com.jobportal.dto.ProfileDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a user profile in the job portal.
 * This class is mapped to the "profiles" collection in MongoDB.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {
    /** Unique identifier for the profile */
    @Id
    private Long id;

    /** Name of the user */
    private String name;

    /** Email address associated with the profile */
    private String email;

    /** Current or desired job title */
    private String jobTitle;

    /** Current or most recent company */
    private String company;

    /** Location of the user */
    private String location;

    /** Brief description about the user */
    private String about;

    /** Profile picture stored as byte array */
    private byte[] picture;

    /** Total experience of the user */
    private Long totalExp;

    /** List of professional skills */
    private List<String> skills;

    /** List of work experiences */
    private List<Expirience> expiriences;

    /** List of professional certifications */
    private List<Certification> certifications;

    /** List of job IDs saved by the user */
    private List<Long> savedJobs;
}
