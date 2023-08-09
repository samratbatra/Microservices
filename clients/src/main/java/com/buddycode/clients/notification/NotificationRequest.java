package com.buddycode.clients.notification;

import java.time.LocalDateTime;

public record NotificationRequest(String message,
                                  String toCustomerEmail,
                                  Integer toCustomerId) {

}
