package com.jobportal.utility;


import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.jobportal.entity.Sequence;
import com.jobportal.exception.JobPortalException;

/**
 * Utility class providing helper methods for sequence generation and OTP creation.
 */
@Component
public class Utilities {

    private static MongoOperations mongoOperations;

    /**
     * Sets the MongoDB operations instance.
     * Required for sequence generation functionality.
     *
     * @param mongoOperations The MongoOperations instance to be used
     */
    @Autowired
    public void setMongoOperation(MongoOperations mongoOperations){
        Utilities.mongoOperations = mongoOperations;
    }
    
    /**
     * Generates the next sequence number for a given key.
     * Uses MongoDB's findAndModify operation to atomically increment and return the sequence.
     *
     * @param key The identifier for the sequence to be incremented
     * @return The next value in the sequence
     * @throws JobPortalException if unable to generate or retrieve the sequence
     */
    public static Long getNextSequence(String key) throws JobPortalException{
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq",1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        Sequence seq = mongoOperations.findAndModify(query, update, options,Sequence.class);
        if(seq==null) throw new JobPortalException("unable to get sequence id for key : "+ key);
        return seq.getSeq();
    }

    /**
     * Generates a random 6-digit OTP (One Time Password).
     * Uses SecureRandom for cryptographically strong random number generation.
     *
     * @return A string containing the 6-digit OTP
     */
    public static String generateOTP(){
        StringBuilder otp = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i = 0; i<6;i++){
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
