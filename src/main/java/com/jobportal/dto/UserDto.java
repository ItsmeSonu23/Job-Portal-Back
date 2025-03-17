package com.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a user in the job portal.
 * This class is used to transfer user-related data between different layers of the application.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /** Unique identifier for the user */
    private Long id;

    /** Full name of the user */
    @NotBlank(message = "{user.name.absent}")
    private String name;

    /** Email address used for authentication and communication */
    @NotBlank(message = "{user.name.absent}")
    @Email(message = "{user.email.invalid}")
    private String email;

    /** 
     * Password for user authentication.
     * Must be 8-15 characters long and contain:
     * - At least one lowercase letter
     * - At least one uppercase letter  
     * - At least one digit
     * - At least one special character
     */
    @NotBlank(message = "{user.password.absent}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,15}$", message = "{user.password.invalid}")
    private String password;

    /** Type of user account (e.g., Job Seeker, Employer) */
    private AccountType accountType;

    /** Reference ID to the user's professional profile */
    private Long profileId;
}
