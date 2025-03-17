package com.jobportal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.dto.LoginDto;
import com.jobportal.dto.ResponseDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.Otp;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repo.OtpRepo;
import com.jobportal.repo.UserRepo;
import com.jobportal.utility.Data;
import com.jobportal.utility.Utilities;
import com.jobportal.dto.NotificationDto;

import jakarta.mail.internet.MimeMessage;

/**
 * Implementation of the UserService interface that provides user management functionality.
 * Handles user registration, authentication, password management and OTP operations.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationService notificationService;

    /**
     * Registers a new user in the system.
     * Creates a profile, encodes the password, and saves the user details.
     *
     * @param userDto The user details to register
     * @return UserDto containing the registered user's information
     * @throws JobPortalException if user already exists
     */
    @Override
    public UserDto registerUser(UserDto userDto) throws JobPortalException {
        Optional<User> optional = userRepo.findByEmail(userDto.getEmail());
        if (optional.isPresent()) {
            throw new JobPortalException("User Already Exist");
        }
        userDto.setProfileId(profileService.createProfile(userDto.getEmail()));
        userDto.setId(Utilities.getNextSequence("users"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = modelMapper.map(userDto, User.class);
        user = userRepo.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    /**
     * Authenticates a user and logs them into the system.
     * Verifies the provided credentials against stored user data.
     *
     * @param loginDto The login credentials
     * @return UserDto containing the logged in user's information
     * @throws JobPortalException if credentials are invalid or user not found
     */
    @Override
    public UserDto loginUser(LoginDto loginDto) throws JobPortalException {
        User user = userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new JobPortalException("INVALID_CREDENTIALS");
        }

        return modelMapper.map(user, UserDto.class);
    }

    /**
     * Generates and sends an OTP to the specified email address.
     * Creates an OTP record and sends it via email to the user.
     *
     * @param email The email address to send OTP to
     * @return Boolean indicating if OTP was sent successfully
     * @throws Exception if email is invalid or sending fails
     */
    @Override
    public Boolean sendOtp(String email) throws Exception {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper msg = new MimeMessageHelper(mm, true);
        msg.setTo(email);
        msg.setSubject("Your OTP code from Clover");

        String genOtp = Utilities.generateOTP();

        Otp otp = new Otp(email, genOtp, LocalDateTime.now());
        otpRepo.save(otp);
        msg.setText(Data.getMessageBody(user.getName(), genOtp), true);
        mailSender.send(mm);
        return true;
    }

    /**
     * Verifies the OTP entered by the user.
     * Checks if the provided OTP matches the stored OTP for the email.
     *
     * @param email The email address associated with the OTP
     * @param otp The OTP code to verify
     * @return Boolean indicating if OTP verification was successful
     * @throws Exception if OTP is invalid or expired
     */
    @Override
    public Boolean verifyOtp(String email, String otp) throws Exception {
        Otp otpentity = otpRepo.findById(email).orElseThrow(() -> new JobPortalException("OTP_NOT_FOUND"));
        if (!otpentity.getOtpCode().equals(otp))
            throw new JobPortalException("OTP_INCORRECT");
        return true;
    }

    /**
     * Changes the user's password after successful OTP verification.
     * Updates the stored password with a new encoded password.
     *
     * @param loginDto Contains the email and new password
     * @return ResponseDto containing success message
     * @throws JobPortalException if user not found
     */
    @Override
    public ResponseDto changePassword(LoginDto loginDto) throws JobPortalException {
        User user = userRepo.findByEmail(loginDto.getEmail()).orElseThrow(() -> new JobPortalException("OTP_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        userRepo.save(user);
        NotificationDto noti = new NotificationDto();
        noti.setUserId(user.getId());
        noti.setMessage("Password changed successfully");
        noti.setAction("Password Reset");
        notificationService.sendNotification(noti);
        return new ResponseDto("Password changed succesfully!");
    }

    /**
     * Scheduled task to remove expired OTPs from the database.
     * Runs every minute and removes OTPs older than 5 minutes.
     */
    @Scheduled(fixedRate = 60000)
    public void removeExpiredOtps(){
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
        List<Otp> expiredOtps =  otpRepo.findByCreationTimeBefore(expiry);
        if(!expiredOtps.isEmpty()){
            otpRepo.deleteAll(expiredOtps);
        }
    }

    @Override
    public UserDto getUserByEmail(String email) throws JobPortalException {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        return modelMapper.map(user, UserDto.class);
    }
}
