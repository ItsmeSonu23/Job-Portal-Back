package com.jobportal.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.dto.NotificationStatus;
import com.jobportal.entity.Notification;

public interface NotificationRepo extends MongoRepository<Notification, Long> {
   public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
