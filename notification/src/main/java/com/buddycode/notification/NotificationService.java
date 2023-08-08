package com.buddycode.notification;

import com.buddycode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    public void sendNotification(NotificationRequest notificationRequest) {

        notificationRepository.save(Notification.builder()
                        .message(notificationRequest.message())
                        .sender("BuddyCode")
                        .sentAt(LocalDateTime.now())
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                .build());
    }
}
