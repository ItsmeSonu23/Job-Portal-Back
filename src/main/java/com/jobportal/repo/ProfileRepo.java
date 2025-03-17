package com.jobportal.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.entity.Profile;

/**
 * Repository interface for Profile entities.
 * Extends MongoRepository to provide CRUD operations for Profile documents.
 */
public interface ProfileRepo extends MongoRepository<Profile,Long> {
    
}
