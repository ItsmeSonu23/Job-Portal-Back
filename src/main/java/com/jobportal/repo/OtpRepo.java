package com.jobportal.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.entity.Otp;
import java.time.LocalDateTime;

/**
 * Repository interface for OTP (One-Time Password) entities.
 * Extends MongoRepository to provide CRUD operations for OTP documents.
 */
public interface OtpRepo extends MongoRepository<Otp,String> {

    /**
     * Finds all OTP records created before a specified expiry time
     * @param expiry The LocalDateTime before which OTPs should be found
     * @return List of OTP records created before the specified time
     */
    List<Otp> findByCreationTimeBefore(LocalDateTime expiry);
}