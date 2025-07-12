package com.ducquy.notification;

import com.ducquy.dtos.NotificationDTO;

public interface NotificationService {


    void sendEmail(NotificationDTO notificationDTO);

    void sendSMS();

    void sendWhatsapp();

}
