package com.jobportal.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a one-time password (OTP) in the job portal.
 * This class is mapped to the "otp" collection in MongoDB.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
@Document(collection ="otp" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
    
    /** Email address used as the unique identifier for the OTP */
    @Id
    private String email;

    /** The generated OTP code */
    private String otpCode;

    /** Timestamp when the OTP was created */
    private LocalDateTime creationTime;
}
