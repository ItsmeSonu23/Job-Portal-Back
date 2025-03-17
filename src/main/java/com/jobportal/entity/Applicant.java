package com.jobportal.entity;

import java.time.LocalDateTime;

import com.jobportal.dto.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a job applicant in the job portal.
 * This class contains all the information related to a job application.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {
    /** Unique identifier for the applicant */
    private Long applicantId;

    /** Full name of the applicant */
    private String name;

    /** Email address of the applicant */
    private String email;

    /** Phone number of the applicant */
    private Long phone;

    /** Professional website or portfolio URL of the applicant */
    private String website;

    /** Applicant's resume stored as byte array */
    private byte[] resume;

    /** Cover letter submitted by the applicant */
    private String coverLetter;

    /** Timestamp when the application was submitted */
    private LocalDateTime timestamp;

    /** Current status of the application (e.g., PENDING, ACCEPTED, REJECTED) */
    private ApplicationStatus applicationStatus;

    /** Scheduled interview time if applicable */
    private LocalDateTime interviewTime;
}
