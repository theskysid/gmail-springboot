package com.theskysid.gemailreceiver.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GEmailReceiverController {


   @GetMapping("/")
   public String inbox(){
      return "inbox";
   }

}
