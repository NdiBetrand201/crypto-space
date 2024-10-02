package com.Trading_plaform.Trading_platform.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailsender;

    public void sendVerificationOTPEmail(String email,String otp) throws MessagingException {
         MimeMessage mimeMessage = javaMailsender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,"utf-8");
      String subject="Verify OTP";
      String text="Your verification code is:"+otp;

      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText(text);
      mimeMessageHelper.setTo(email);

      try{
          javaMailsender.send(mimeMessage);
      }catch(MailException e){
          throw  new MailSendException(e.getMessage());

      }
    }
}
