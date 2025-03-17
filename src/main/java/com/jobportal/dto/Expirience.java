package com.jobportal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a work experience entry in a user's professional profile.
 * This class is used to transfer work experience information between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expirience {
    /** Job title or position held */
    private String title;

    /** Name of the employer or company */
    private String company;

    /** Geographic location where the work was performed */
    private String location;

    /** Date when the position started */
    private LocalDateTime startDate;

    /** Date when the position ended, null if currently working */
    private LocalDateTime endDate;

    /** Flag indicating if this is the current position */
    private Boolean working;

    /** Detailed description of responsibilities and achievements */
    private String description;
}
