package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.NotificationDto;
import com.jobportal.entity.Notification;
import com.jobportal.exception.JobPortalException;

public interface NotificationService {
    
    public void sendNotification(NotificationDto notificationDto) throws JobPortalException;
    public List<Notification> getUnreadNotifications(Long userId);
    public void readNotification(Long id) throws JobPortalException;
}
