package com.jobportal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jobportal.dto.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a user in the job portal.
 * This class is mapped to the "users" collection in MongoDB.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    /** Unique identifier for the user */
    @Id
    private Long id;

    /** Full name of the user */
    private String name;

    /** Email address used for login and communication (unique) */
    @Indexed(unique = true)
    private String email;

    /** Encrypted password for user authentication */
    private String password;

    /** Type of account (e.g., JOB_SEEKER, RECRUITER) */
    private AccountType accountType;

    /** Reference to the user's profile document */
    private Long profileId;
}
