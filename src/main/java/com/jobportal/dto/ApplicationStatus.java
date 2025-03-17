package com.jobportal.dto;

/**
 * Enumeration representing the possible statuses of a job application.
 * This enum is used to track the progress of applications through the hiring process.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
public enum ApplicationStatus {
    /** Initial status when candidate submits application */
    APPLIED,
    
    /** Status indicating candidate is in interview process */
    INTERVIEWING,
    
    /** Status indicating job offer has been extended */
    OFFERED,
    
    /** Status indicating application was not successful */
    REJECTED
}
