package com.jobportal.dto;

/**
 * Enum representing the possible statuses of a job posting in the job portal.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
public enum JobStatus {
    /** Job posting is currently active and accepting applications */
    ACTIVE,
    
    /** Job posting has been closed and is no longer accepting applications */
    CLOSED,
    
    /** Job posting is saved as a draft and not yet published */
    DRAFT
}
