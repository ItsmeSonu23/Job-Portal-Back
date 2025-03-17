package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.LoginDto;
import com.jobportal.dto.ResponseDto;
import com.jobportal.dto.UserDto;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing user operations.
 * Handles user registration, authentication, password management and OTP verification.
 */
@RestController
@CrossOrigin // Allow requests from any origin
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user in the system.
     *
     * @param userDto The user details for registration
     * @return ResponseEntity containing the registered user information
     * @throws JobPortalException if registration fails
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) throws JobPortalException {
        userDto = userService.registerUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    /**
     * Authenticates a user and returns their information.
     *
     * @param loginDto The login credentials
     * @return ResponseEntity containing the authenticated user information
     * @throws JobPortalException if authentication fails
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody @Valid LoginDto loginDto) throws JobPortalException {
        return new ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);
    }

    /**
     * Changes a user's password.
     *
     * @param loginDto Contains the email and new password
     * @return ResponseEntity containing the response message
     * @throws JobPortalException if password change fails
     */
    @PostMapping("/changePass")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody @Valid LoginDto loginDto) throws JobPortalException {
        return new ResponseEntity<>(userService.changePassword(loginDto), HttpStatus.OK);
    }

    /**
     * Sends an OTP to the specified email address.
     *
     * @param email The email address to send OTP to
     * @return ResponseEntity containing the response message
     * @throws Exception if OTP sending fails
     */
    @PostMapping("sendOtp/{email}")
    public ResponseEntity<ResponseDto> sendOtp(@PathVariable @Email(message = "{user.email.invalid}") String email) throws Exception{
        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDto("OTP sent successfully"), HttpStatus.OK);
    }

    /**
     * Verifies an OTP for a given email address.
     *
     * @param email The email address associated with the OTP
     * @param otp The OTP to verify (must be 6 digits)
     * @return ResponseEntity containing the response message
     * @throws Exception if OTP verification fails
     */
    @GetMapping("verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDto> verifyOtp(@PathVariable @Email(message = "{user.email.invalid}") String email,@PathVariable @Pattern(regexp = "^[0-9]{6}$", message="{otp.invalid}") String otp) throws Exception{
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDto("OTP verified successfully"), HttpStatus.OK);
    }

}
