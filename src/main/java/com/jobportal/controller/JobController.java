package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ApplicantDto;
import com.jobportal.dto.Application;
import com.jobportal.dto.JobDto;
import com.jobportal.dto.ResponseDto;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.JobService;

import jakarta.validation.Valid;

/**
 * REST controller for managing job posting and application operations.
 * Handles job creation, retrieval, applications and status updates.
 */
@RestController
@CrossOrigin // Allow requests from any origin
@Validated
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    /**
     * Creates a new job posting.
     *
     * @param jobDto The job details to post
     * @return ResponseEntity containing the posted job information
     * @throws JobPortalException if job creation fails
     */
    @PostMapping("/post")
    public ResponseEntity<JobDto> postJob(@RequestBody @Valid JobDto jobDto) throws JobPortalException{
        return new ResponseEntity<>(jobService.postJob(jobDto),HttpStatus.CREATED);
    }

    /**
     * Retrieves all available job postings.
     *
     * @return ResponseEntity containing list of all job postings
     * @throws JobPortalException if retrieval fails
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<JobDto>> getAllJobs() throws JobPortalException{
        return new ResponseEntity<>(jobService.getAllJobs(),HttpStatus.OK);
    }

    /**
     * Retrieves a specific job posting by its ID.
     *
     * @param id The ID of the job to retrieve
     * @return ResponseEntity containing the job information
     * @throws JobPortalException if job not found
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) throws JobPortalException{
        return new ResponseEntity<>(jobService.getJobById(id),HttpStatus.OK);
    }

    /**
     * Submits a job application for a specific job posting.
     *
     * @param applicantDto The applicant's information
     * @param id The ID of the job being applied to
     * @return ResponseEntity containing success message
     * @throws JobPortalException if application submission fails
     */
    @PostMapping("/apply/{id}")
    public ResponseEntity<ResponseDto> applyJob(@RequestBody @Valid ApplicantDto applicantDto ,@PathVariable Long id) throws JobPortalException{
        jobService.applyJob(id,applicantDto);
        return new ResponseEntity<>(new ResponseDto("Applied Successfully"),HttpStatus.OK);
    }

    /**
     * Retrieves all jobs posted by a specific user/recruiter.
     *
     * @param id The ID of the user/recruiter
     * @return ResponseEntity containing list of the user's job postings
     * @throws JobPortalException if retrieval fails
     */
    @GetMapping("/postedBy/{id}")
    public ResponseEntity<List<JobDto>> getJobsPostedBy(@PathVariable Long id) throws JobPortalException{
        return new ResponseEntity<>(jobService.getJobsPostedBy(id),HttpStatus.OK);
    }

    /**
     * Updates the status of a job application.
     *
     * @param applicantion The application with updated status
     * @return ResponseEntity containing success message
     * @throws JobPortalException if status update fails
     */
    @PostMapping("/changeAppStatus")
    public ResponseEntity<ResponseDto> changeAppStatus(@RequestBody @Valid Application applicantion) throws JobPortalException{
        jobService.changeAppStatus(applicantion);
        return new ResponseEntity<>(new ResponseDto("Application Status changed successfully"),HttpStatus.OK);
    }

}
