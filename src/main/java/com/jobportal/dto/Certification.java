package com.jobportal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a professional certification in a user's profile.
 * This class is used to transfer certification information between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
    /** Title or name of the certification */
    private String title;

    /** Organization or institution that issued the certification */
    private String issuer;

    /** Date when the certification was issued */
    private LocalDateTime issueDate;

    /** Unique identifier or serial number of the certification */
    private String certificateId;
}
