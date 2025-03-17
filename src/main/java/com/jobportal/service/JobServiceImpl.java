package com.jobportal.service; // Package declaration for the JobService implementation

import java.time.LocalDateTime; // Importing LocalDateTime for handling timestamps
import java.util.ArrayList; // Importing ArrayList for dynamic array functionality
import java.util.List; // Importing List interface for list operations
import java.util.stream.Collectors; // Importing Collectors for stream operations

import org.modelmapper.ModelMapper; // Importing ModelMapper for object mapping
import org.springframework.beans.factory.annotation.Autowired; // Importing Autowired for dependency injection
import org.springframework.stereotype.Service; // Importing Service annotation for service layer

import com.jobportal.dto.ApplicantDto; // Importing ApplicantDto for applicant data transfer
import com.jobportal.dto.Application; // Importing Application for application data transfer
import com.jobportal.dto.ApplicationStatus; // Importing ApplicationStatus for application status enumeration
import com.jobportal.dto.JobDto; // Importing JobDto for job data transfer
import com.jobportal.dto.JobStatus; // Importing JobStatus for job status enumeration
import com.jobportal.dto.NotificationDto;
import com.jobportal.entity.Applicant; // Importing Applicant entity
import com.jobportal.entity.Job; // Importing Job entity
import com.jobportal.exception.JobPortalException; // Importing custom exception for job portal
import com.jobportal.repo.JobRepo; // Importing JobRepo for job repository interface
import com.jobportal.utility.Utilities; // Importing Utilities for utility functions

/**
 * Implementation of the JobService interface that provides job posting and
 * application management functionality.
 * Handles job creation, retrieval, applications and status updates.
 */
@Service("jobService") // Service annotation to indicate this class is a service
public class JobServiceImpl implements JobService { // Class implementing JobService interface
    @Autowired // Automatically injects JobRepo dependency
    private JobRepo jobRepo; // Repository for job entity

    @Autowired // Automatically injects ModelMapper dependency
    private ModelMapper modelMapper; // Mapper for converting between DTOs and entities\

    @Autowired
    private NotificationService notificationService;

    /**
     * Creates a new job posting with auto-generated ID and current timestamp.
     *
     * @param jobDto The job details to post
     * @return JobDto containing the posted job information
     * @throws JobPortalException if job creation fails
     */
    @Override // Indicates this method overrides a method in the interface
    public JobDto postJob(JobDto jobDto) throws JobPortalException { // Method to post a job
        // Validate jobDto
        if (jobDto == null) { // Check if jobDto is null
            throw new JobPortalException("JOB_DTO_CANNOT_BE_NULL"); // Throw exception if null
        }

        // Set ID and post time
        if (jobDto.getId() == null || jobDto.getId() == 0) { // Check if ID is not set
            jobDto.setId(Utilities.getNextSequence("jobs")); // Generate next ID
            jobDto.setPostTime(LocalDateTime.now()); // Set current timestamp
            NotificationDto notiDto = new NotificationDto();
            notiDto.setAction("Job Posted");
            notiDto.setMessage("New Job Posted Successfully for " + jobDto.getJobTitle() + "at" + jobDto.getCompany());
            notiDto.setUserId(jobDto.getPostedBy());
            notiDto.setRoute("/posted-job/"+jobDto.getId());
           try {
            notificationService.sendNotification(notiDto);
           } catch (Exception e) {
                e.printStackTrace();
           }
        } else { // If ID is already set
            Job job = jobRepo.findById(jobDto.getId()) // Find job by ID
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")); // Throw exception if not found
            if (job.getJobStatus() == JobStatus.DRAFT || jobDto.getJobStatus() == JobStatus.CLOSED) { // Check job status
                jobDto.setPostTime(LocalDateTime.now()); // Set current timestamp
            } else { // If job status is not allowed
                // Resolved JOB_STATUS_NOT_ALLOWED exception
                jobDto.setJobStatus(JobStatus.ACTIVE); // Set to ACTIVE to allow posting
                jobDto.setPostTime(LocalDateTime.now()); // Set current timestamp
            }
        }
        // Map JobDto to JobEntity using ModelMapper
        Job job = modelMapper.map(jobDto, Job.class); // Convert JobDto to Job entity
        // Save JobEntity to the repository
        Job savedJob = jobRepo.save(job); // Save job entity to the repository
        // Optionally map saved JobEntity back to JobDto to return it
        return modelMapper.map(savedJob, JobDto.class); // Convert saved job entity back to JobDto
    }

    /**
     * Retrieves all available job postings.
     *
     * @return List of JobDto containing all job postings
     * @throws JobPortalException if retrieval fails
     */
    @Override // Indicates this method overrides a method in the interface
    public List<JobDto> getAllJobs() throws JobPortalException { // Method to retrieve all jobs
        return jobRepo.findAll().stream() // Retrieve all jobs from repository and convert to stream
                .map(jobEntity -> modelMapper.map(jobEntity, JobDto.class)) // Map each Job entity to JobDto
                .collect(Collectors.toList()); // Collect results into a List
    }

    /**
     * Retrieves a specific job posting by its ID.
     *
     * @param id The ID of the job to retrieve
     * @return JobDto containing the job information
     * @throws JobPortalException if job not found
     */
    @Override // Indicates this method overrides a method in the interface
    public JobDto getJobById(Long id) throws JobPortalException { // Method to retrieve job by ID
        Job job = jobRepo.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")); // Find job by ID
        return modelMapper.map(job, JobDto.class); // Convert Job entity to JobDto and return
    }

    /**
     * Submits a job application for a specific job posting.
     * Checks if applicant has already applied and initializes application status.
     *
     * @param id           The ID of the job being applied to
     * @param applicantDto The applicant's information
     * @throws JobPortalException if job not found or applicant has already applied
     */
    @Override // Indicates this method overrides a method in the interface
    public void applyJob(Long id, ApplicantDto applicantDto) throws JobPortalException { // Method to apply for a job
        // Find job by ID
        Job job = jobRepo.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")); // Retrieve job by ID

        // Get the list of applicants
        List<Applicant> applicants = job.getApplicants(); // Get current list of applicants
        if (applicants == null) // Check if applicants list is null
            applicants = new ArrayList<>(); // Initialize to an empty list if null

        // Check if the applicant has already applied
        boolean isAlreadyApplied = applicants.stream() // Stream through applicants
                .anyMatch(applicant -> applicant.getApplicantId().equals(applicantDto.getApplicantId())); // Check for existing applicant

        if (isAlreadyApplied) { // If applicant has already applied
            throw new JobPortalException("JOB_APPLIED_ALREADY"); // Throw exception
        }

        // Set the application status
        applicantDto.setApplicationStatus(ApplicationStatus.APPLIED); // Set application status to APPLIED

        // Use ModelMapper to map the ApplicantDto to Applicant entity
        Applicant applicant = modelMapper.map(applicantDto, Applicant.class); // Convert ApplicantDto to Applicant entity

        // Add the applicant to the list
        applicants.add(applicant); // Add new applicant to the list

        // Set the updated applicants list back to the job
        job.setApplicants(applicants); // Update job with new applicants list

        // Save the job
        jobRepo.save(job); // Save updated job entity to the repository
    }

    /**
     * Retrieves all jobs posted by a specific user/recruiter.
     *
     * @param id The ID of the user/recruiter
     * @return List of JobDto containing the user's job postings
     * @throws JobPortalException if retrieval fails
     */
    @Override // Indicates this method overrides a method in the interface
    public List<JobDto> getJobsPostedBy(Long id) throws JobPortalException { // Method to retrieve jobs posted by a user
        return jobRepo.findByPostedBy(id).stream().map((x) -> modelMapper.map(x, JobDto.class)).toList(); // Map each job to JobDto
    }

    /**
     * Updates the status of a job application.
     * Sets interview time if status is changed to INTERVIEWING.
     *
     * @param applicantion The application with updated status
     * @throws JobPortalException if job not found
     */
    @Override // Indicates this method overrides a method in the interface
    public void changeAppStatus(Application applicantion) throws JobPortalException { // Method to change application status
        Job job = jobRepo.findById(applicantion.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")); // Find job by application ID

        // Get the list of applicants
        List<Applicant> applicants = job.getApplicants().stream().map((x) -> { // Stream through applicants
            if (applicantion.getApplicantId() == x.getApplicantId()) { // Check if applicant matches
                x.setApplicationStatus(applicantion.getApplicationStatus()); // Update application status
                if (applicantion.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) { // If status is INTERVIEWING
                    x.setInterviewTime(applicantion.getInterviewTime()); // Set interview time
                    NotificationDto notiDto = new NotificationDto();
                    notiDto.setAction("Scheduled");
                    notiDto.setMessage("You have an interview scheduled at " + applicantion.getInterviewTime() + "for job id" + applicantion.getId());
                    notiDto.setUserId(applicantion.getApplicantId());
                    notiDto.setRoute("/job-history");
                   try {
                    notificationService.sendNotification(notiDto);
                   } catch (Exception e) {
                        e.printStackTrace();
                   }
                }
                
            }
            return x; // Return updated applicant
        }).toList(); // Collect updated applicants into a list
        job.setApplicants((applicants)); // Update job with new applicants list
        jobRepo.save(job); // Save updated job entity to the repository
    }
}
