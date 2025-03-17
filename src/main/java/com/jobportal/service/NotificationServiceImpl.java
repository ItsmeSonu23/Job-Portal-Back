package com.jobportal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.NotificationDto;
import com.jobportal.dto.NotificationStatus;
import com.jobportal.entity.Notification;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repo.NotificationRepo;
import com.jobportal.utility.Utilities;

import org.modelmapper.ModelMapper;
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void sendNotification(NotificationDto notificationDto) throws JobPortalException {
        notificationDto.setId(Utilities.getNextSequence("notification"));
        notificationDto.setTimestamp(LocalDateTime.now());
        notificationDto.setStatus(NotificationStatus.UNREAD);
        Notification notification = modelMapper.map(notificationDto, Notification.class);
        notificationRepo.save(notification);
    }

    @Override   
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepo.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void readNotification(Long id) throws JobPortalException {  
        Notification notification = notificationRepo.findById(id).orElseThrow(() -> new JobPortalException("Notification not found"));
        notification.setStatus(NotificationStatus.READ);
        notificationRepo.save(notification);
    }
}
