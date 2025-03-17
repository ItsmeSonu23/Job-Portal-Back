package com.jobportal.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a job posting in the job portal.
 * This class is used to transfer job-related data between different layers of the application.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    /** Unique identifier for the job posting */
    private Long id;

    /** Title or designation of the job position */
    private String jobTitle;

    /** Name of the company offering the job */
    private String company;

    /** List of applicants who have applied for this job */
    private List<ApplicantDto> applicants;

    /** Brief overview of the company or job position */
    private String about;

    /** Required work experience for the position */
    private String expirience;

    /** Type of employment (e.g., Full-time, Part-time, Contract) */
    private String jobType;

    /** Location where the job is based */
    private String location;

    /** Annual salary package offered (in base currency) */
    private Long packageOffered;

    /** Timestamp when the job was posted */
    private LocalDateTime postTime;

    /** Detailed description of the job responsibilities and requirements */
    private String description;

    /** List of technical or professional skills required for the position */
    private List<String> skillsRequired;

    /** Current status of the job posting (e.g., ACTIVE, CLOSED) */
    private JobStatus jobStatus;

    /** ID of the recruiter who posted the job */
    private Long postedBy;
}
