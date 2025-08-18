package com.theskysid.gmail.Controller;

import com.theskysid.gmail.Model.GEmailSender;
import com.theskysid.gmail.Service.GEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GEmailSenderController {

   @Autowired
   GEmailSenderService gEmailSenderService;

   @GetMapping("/")
   public String emailform() {
      return "GEmailSenderForm";
   }


   @PostMapping("/sendmail")
   @ResponseBody //temporary to show send on the page
   public String sendMail(@RequestBody GEmailSender emailSender){
      gEmailSenderService.sendmail(emailSender);

      return "sent";
   }
}
