package com.jobportal.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jobportal.dto.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a job posting in the job portal.
 * This class is mapped to the "jobs" collection in MongoDB.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "jobs")
public class Job {
    /** Unique identifier for the job posting */
    @Id
    private Long id;

    /** Title of the job position */
    private String jobTitle;

    /** Name of the company offering the job */
    private String company;

    /** List of applicants who have applied for this job */
    private List<Applicant> applicants;

    /** Brief overview of the company or job position */
    private String about;

    /** Required experience level for the position */
    private String expirience;

    /** Type of job (e.g., Full-time, Part-time, Contract) */
    private String jobType;

    /** Location where the job is based */
    private String location;

    /** Annual package/salary offered for the position */
    private Long packageOffered;

    /** Timestamp when the job was posted */
    private LocalDateTime postTime;

    /** Detailed description of the job role and responsibilities */
    private String description;

    /** List of skills required for the position */
    private List<String> skillsRequired;

    /** Current status of the job posting (e.g., OPEN, CLOSED) */
    private JobStatus jobStatus;

    /** ID of the user/recruiter who posted the job */
    private Long postedBy;
}
