package com.jobportal.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.entity.Job;

/**
 * Repository interface for Job entities.
 * Extends MongoRepository to provide CRUD operations for Job documents.
 */
public interface JobRepo extends MongoRepository<Job, Long>{
    /**
     * Finds all jobs posted by a specific user
     * @param postedBy The ID of the user who posted the jobs
     * @return List of jobs posted by the specified user
     */
    public List<Job> findByPostedBy(Long postedBy);
}
