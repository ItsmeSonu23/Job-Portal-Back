package com.jobportal.service;


import com.jobportal.dto.LoginDto;
import com.jobportal.dto.ResponseDto;
import com.jobportal.dto.UserDto;
import com.jobportal.exception.JobPortalException;

/**
 * Service interface defining user management operations.
 * Handles user registration, authentication, password management and OTP verification.
 */
public interface UserService {
    
    /**
     * Registers a new user in the system.
     *
     * @param userDto The user details to register
     * @return UserDto containing the registered user's information
     * @throws JobPortalException if user already exists or validation fails
     */
    public UserDto registerUser(UserDto userDto) throws JobPortalException;

    /**
     * Authenticates a user and logs them into the system.
     *
     * @param loginDto The login credentials
     * @return UserDto containing the logged in user's information
     * @throws JobPortalException if credentials are invalid or user not found
     */
    public UserDto loginUser(LoginDto loginDto) throws JobPortalException;

    /**
     * Generates and sends an OTP to the specified email address.
     *
     * @param email The email address to send OTP to
     * @return Boolean indicating if OTP was sent successfully
     * @throws Exception if email is invalid or sending fails
     */
    public Boolean sendOtp(String email) throws Exception;

    /**
     * Verifies the OTP entered by the user.
     *
     * @param email The email address associated with the OTP
     * @param otp The OTP code to verify
     * @return Boolean indicating if OTP verification was successful
     * @throws Exception if OTP is invalid or expired
     */
    public Boolean verifyOtp(String email, String otp) throws Exception;

    /**
     * Changes the user's password after successful OTP verification.
     *
     * @param loginDto Contains the email and new password
     * @return ResponseDto containing success/failure message
     * @throws JobPortalException if password change fails
     */
    public ResponseDto changePassword(LoginDto loginDto) throws JobPortalException;

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve
     * @return UserDto containing the user's information
     * @throws JobPortalException if user is not found
     */
    public UserDto getUserByEmail(String email) throws JobPortalException;
    
    
}
