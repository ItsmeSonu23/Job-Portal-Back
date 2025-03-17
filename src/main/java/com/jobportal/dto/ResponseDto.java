package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a generic response in the job portal.
 * This class is used to transfer response messages between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    /** Response message containing feedback or status information */
    private String message;
}
