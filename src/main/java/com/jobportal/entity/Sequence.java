package com.jobportal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Entity class representing a sequence generator in the job portal.
 * This class is mapped to the "sequence" collection in MongoDB and is used
 * to generate sequential IDs for other entities.
 * 
 * @author Sonu Mishra
 * @version 1.0
 */
@Document(collection = "sequence")
@Data
public class Sequence {
    /** Identifier for the sequence */
    @Id
    private String id;

    /** Current sequence value */
    private Long seq;
}
