package com.jobportal.exception;

/**
 * Custom exception class for the job portal application.
 * This exception is thrown when business logic validation fails or other job portal specific errors occur.
 * The exception message is used as a key to lookup the actual error message from the properties file.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
public class JobPortalException extends Exception {
    /**
     * Constructs a new JobPortalException with the specified message.
     * 
     * @param message The error message key that maps to the actual error message in properties file
     */
    public JobPortalException(String message) {
        super(message);
    }
}
