package com.jobportal.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.entity.User;

/**
 * Repository interface for User entities.
 * Extends MongoRepository to provide CRUD operations for User documents.
 */
public interface UserRepo extends MongoRepository<User,Long> {
    /**
     * Finds a user by their email address
     * @param email The email address to search for
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<User> findByEmail(String email);
}
