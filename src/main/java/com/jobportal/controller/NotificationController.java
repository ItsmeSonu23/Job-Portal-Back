package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.entity.Notification;
import com.jobportal.dto.ResponseDto;
import com.jobportal.service.NotificationService;
import com.jobportal.exception.JobPortalException;

import java.util.List;

@RestController
@CrossOrigin // Allow requests from any origin
@RequestMapping("/notification") // Changed to plural for consistency
@Validated
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getUnreadNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<ResponseDto> readNotification(@PathVariable Long id) throws JobPortalException {
        notificationService.readNotification(id);
        return ResponseEntity.ok(new ResponseDto("Notification read successfully"));
    }       
}
