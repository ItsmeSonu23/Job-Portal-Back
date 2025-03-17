package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing login credentials in the job portal.
 * This class is used to transfer login information between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    /** Email address used for authentication */
    private String email;
    
    /** Password used for authentication */
    private String password;
}
