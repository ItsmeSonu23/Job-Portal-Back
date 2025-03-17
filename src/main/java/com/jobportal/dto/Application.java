package com.jobportal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a job application in the job portal.
 * This class is used to transfer job application data between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    /** Unique identifier for the job application */
    private Long id;

    /** ID of the user who submitted the application */
    private Long applicantId;

    /** Scheduled date and time for the interview */
    private LocalDateTime interviewTime;

    /** Current status of the application in the hiring process */
    private ApplicationStatus applicationStatus;
}
