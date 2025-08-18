package com.theskysid.gemailreceiver.Controller;

import com.theskysid.gemailreceiver.Entity.Email;
import com.theskysid.gemailreceiver.Entity.EmailRequests;
import com.theskysid.gemailreceiver.Service.GEmailReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.awt.*;
import java.util.List;

@Controller
public class GEmailReceiverController {

   @Autowired
   private GEmailReceiverService gEmailReceiverService;

   private RestTemplate restTemplate;

   //rest template is now initialised
   public GEmailReceiverController(RestTemplateBuilder restTemplateBuilder){
      this.restTemplate = restTemplateBuilder.build();
   }

   @GetMapping("/")
   public String inbox(Model model){
      List<Email> emailsList = GEmailReceiverService.getEmails();
      model.addAttribute("emailslist", emailsList);
      return "inbox";
   }

   @PostMapping("/sendmail")
   public String sendMail(@ModelAttribute EmailRequests emailRequests, Model model){

      try {
         String url = "http://localhost:8080/sendmail";
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);

         HttpEntity<EmailRequests> httpEntity = new HttpEntity<EmailRequests>(emailRequests, headers);

         ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

         if (response.getStatusCode() == HttpStatus.OK){
            model.addAttribute("success", "Email sent successfully");
         } else {
            model.addAttribute("failed", "Failed to sent email");
         }

      } catch (Exception e) {
         model.addAttribute("ErrorMessage", "Process Failed with the message" + e.getMessage());
      }

      return "inbox";


   }
}
