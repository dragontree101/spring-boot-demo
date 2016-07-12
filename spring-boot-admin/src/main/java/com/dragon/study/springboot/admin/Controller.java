package com.dragon.study.springboot.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by dragon on 16/6/20.
 */
@RestController
@RequestMapping("/person")
public class Controller {

  @Autowired
  private JavaMailSender mailSender;

  @RequestMapping("/send")
  public void sendMail() {
    MimeMessage mail = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setTo("longzhe@ricebook.com");
      helper.setFrom("longzhe@ricebook.com");
      helper.setSubject("subject");
      helper.setText("test text");
    } catch (MessagingException e) {
      e.printStackTrace();
    } finally {
    }
    mailSender.send(mail);
  }
}
