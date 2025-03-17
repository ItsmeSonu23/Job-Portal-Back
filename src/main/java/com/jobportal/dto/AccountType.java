package com.jobportal.dto;

/**
 * Enumeration representing the types of accounts in the job portal system.
 * This enum is used to distinguish between job seekers and company employees.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
public enum AccountType {
    /** Account type for job seekers who can apply to positions */
    APPLICANT,
    
    /** Account type for company employees who can post and manage job listings */
    EMPLOYEE
}
