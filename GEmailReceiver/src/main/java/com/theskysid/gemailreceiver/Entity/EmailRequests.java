package com.theskysid.gemailreceiver.Entity;

import lombok.Data;


public class EmailRequests {

   public String getTo() {
      return to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   private String to;
   private String subject;
   private String message;

}
