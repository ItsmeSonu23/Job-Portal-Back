package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.ApplicantDto;
import com.jobportal.dto.Application;
import com.jobportal.dto.JobDto;
import com.jobportal.exception.JobPortalException;

/**
 * Service interface defining job posting and application management operations.
 * Handles job creation, retrieval, applications and status updates.
 */
public interface JobService {

    /**
     * Creates a new job posting.
     *
     * @param jobDto The job details to post
     * @return JobDto containing the posted job information
     * @throws JobPortalException if job creation fails
     */
    public JobDto postJob(JobDto jobDto) throws JobPortalException;

    /**
     * Retrieves all available job postings.
     *
     * @return List of JobDto containing all job postings
     * @throws JobPortalException if retrieval fails
     */
    public List<JobDto> getAllJobs() throws JobPortalException;

    /**
     * Retrieves a specific job posting by its ID.
     *
     * @param id The ID of the job to retrieve
     * @return JobDto containing the job information
     * @throws JobPortalException if job not found
     */
    public JobDto getJobById(Long id) throws JobPortalException;

    /**
     * Submits a job application for a specific job posting.
     *
     * @param id The ID of the job being applied to
     * @param applicantDto The applicant's information
     * @throws JobPortalException if application submission fails
     */
    public void applyJob(Long id,ApplicantDto applicantDto) throws JobPortalException;

    /**
     * Retrieves all jobs posted by a specific user/recruiter.
     *
     * @param id The ID of the user/recruiter
     * @return List of JobDto containing the user's job postings
     * @throws JobPortalException if retrieval fails
     */
    public List<JobDto> getJobsPostedBy(Long id) throws JobPortalException;

    /**
     * Updates the status of a job application.
     *
     * @param applicantion The application with updated status
     * @throws JobPortalException if status update fails
     */
    public void changeAppStatus(Application applicantion) throws JobPortalException;
}