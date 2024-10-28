package com.myBank.service.ServiceImpl;


import com.myBank.Repository.NotificationRepository;
import com.myBank.entity.Notification;
import com.myBank.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Notification notification) {
        // Save notification to the database
        notificationRepository.save(notification);
    }
}
