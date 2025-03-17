package com.jobportal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a job applicant in the job portal.
 * This class is used to transfer applicant information between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto {
    /** Unique identifier for the applicant */
    private Long applicantId;

    /** Full name of the applicant */
    private String name;

    /** Email address for communication */
    private String email;

    /** Contact phone number */
    private Long phone;

    /** Professional website or portfolio URL */
    private String website;

    /** Path or URL to applicant's resume document */
    private String resume;

    /** Path or URL to applicant's cover letter document */
    private String coverLetter;

    /** Timestamp when the application was submitted */
    private LocalDateTime timestamp;

    /** Current status of the application in the hiring process */
    private ApplicationStatus applicationStatus;

    /** Scheduled date and time for the interview */
    private LocalDateTime interviewTime;
}
