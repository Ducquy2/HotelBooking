package com.ducquy.notification;

import com.ducquy.dtos.NotificationDTO;
import com.ducquy.entitys.Notification;
import com.ducquy.enums.NotificationType;
import com.ducquy.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceimpl implements NotificationService {


    private final JavaMailSender javaMailSender;

    private final NotificationRepository notificationRepository;

    @Override
    public void sendEmail(NotificationDTO notificationDTO) {

        log.info("Sending Email...");
        // Logic to send SMS
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationDTO.getRecipient()); // Đặt địa chỉ email người nhận
        simpleMailMessage.setSubject(notificationDTO.getSubject()); // Dặt tiêu đề email
        simpleMailMessage.setText(notificationDTO.getBody()); // Đặt nội dung email

        javaMailSender.send(simpleMailMessage); // Gửi email
        // Lưu thông tin email vào cơ sở dữ liệu
        Notification notificationToSave = Notification.builder()
                .recipient(notificationDTO.getRecipient())
                .subject(notificationDTO.getSubject())
                .body(notificationDTO.getBody())
                .bookingReference(notificationDTO.getBookingReference())
                .type(NotificationType.EMAIL)
                .build();

        notificationRepository.save(notificationToSave); // Lưu thông báo vào cơ sở dữ liệu
    }

    @Override
    @Async
    public void sendSMS() {



    }

    @Override
    public void sendWhatsapp() {

    }
}
