package com.theskysid.gmail.Service;

import com.theskysid.gmail.Model.GEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class GEmailSenderService {

   @Autowired
   JavaMailSender javaMailSender;

   public void sendmail(GEmailSender gEmailSender) {

      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(gEmailSender.getTo());
      message.setSubject(gEmailSender.getSubject());
      message.setText(gEmailSender.getMessage());

      javaMailSender.send(message);
   }
}
