package com.theskysid.gemailreceiver.Service;

import com.theskysid.gemailreceiver.Entity.Email;
import jakarta.annotation.PostConstruct;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class GEmailReceiverService {

   private static List<Email> allEmailsList = new ArrayList<Email>(); //this list will contain all the emails
   private int lastMessageCount = -1; //total message count
   private boolean initialFetch = false;

   @PostConstruct() //
   public void initialMails(){
      fetchAllEmails();
      initialFetch =  true;
   }

   @Scheduled(fixedRate = 10000) //runs after every 10 seconds
   public void newlyReceivedEmail(){ //responsible to capture the newly added mails
      if (!initialFetch) {
         initialMails();
         return;
      }

      try{
         Properties props = new Properties();
         props.put("mail.store.protocol", "imap");
         props.put("mail.imap.host", "imap.gmail.com");
         props.put("mail.imap.port", "993");
         props.put("mail.imap.ssl.enable", "true");

         Session session = Session.getInstance(props);
         Store store = session.getStore("imap");
         store.connect("imap.gmail.com", "rastogisiddhant23102003@gmail.com","");

         Folder inboxFolder = store.getFolder("INBOX"); //this folder contain all the inbox
         inboxFolder.open(Folder.READ_ONLY); //opening the folder

         int currentMailsCount = inboxFolder.getMessageCount();

         if (currentMailsCount > lastMessageCount) {
            Message[] newmMessages = inboxFolder.getMessages(lastMessageCount +1, currentMailsCount);
            System.out.println("New Mails Count : " +  newmMessages.length);

            for(Message message : newmMessages){
               Email newEmail = new Email();
               newEmail.setFrom(InternetAddress.toString(message.getFrom()));
               newEmail.setSubject(message.getSubject());
               newEmail.setReceivedDate(message.getReceivedDate());
               newEmail.setContent(getTextFromMessage(message));
               allEmailsList.add(0,newEmail);
            }

            lastMessageCount = currentMailsCount;


         } else {
            System.out.println("No new mails found");
         }

         inboxFolder.close(false);
         store.close();

      } catch (Exception e) {
         System.out.println("Exception : " + e.getMessage());
      }

   }

   private void fetchAllEmails(){ // we are ready to return this ... like a preload to save time
      try{
         Properties props = new Properties();
         props.put("mail.store.protocol", "imap");
         props.put("mail.imap.host", "imap.gmail.com");
         props.put("mail.imap.port", "993");
         props.put("mail.imap.ssl.enable", "true");

         Session session = Session.getInstance(props);
         Store store = session.getStore("imap");
         store.connect("imap.gmail.com", "rastogisiddhant23102003@gmail.com","");

         Folder inboxFolder = store.getFolder("INBOX"); //this folder contain all the inbox
         inboxFolder.open(Folder.READ_ONLY); //opening the folder

         Message[] allMails =  inboxFolder.getMessages(); //from the inboxFolder we are getting messages in the allMails Array of Message type
         System.out.println("Initial Fetch total mails count " + allMails.length);

         for(Message message : allMails){
            Email email = new Email();
            email.setFrom(InternetAddress.toString(message.getFrom()));
            email.setSubject(message.getSubject());
            email.setReceivedDate(message.getReceivedDate()); //in entity we have written Data as its type here it is wrong i guess
            email.setContent(getTextFromMessage(message));
            allEmailsList.add(email);
         }

         lastMessageCount = inboxFolder.getMessageCount();
         System.out.println("Last Message count " + lastMessageCount);

         inboxFolder.close(false);
         store.close();

      } catch (Exception e) {
         System.out.println("Error while fetching all mails " + e.getMessage());
      }



   }

   private String getTextFromMessage(Message message) throws Exception {
      if (message.isMimeType("text/plain")){
         return message.getContentType().toString();
      } else if (message.isMimeType("multipart/*")) {
         MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
         return getTextFromMimeMultipart(mimeMultipart);
      }
      return "";
   }

   public String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
      StringBuilder builder = new StringBuilder();

      for (int i = 0; i < mimeMultipart.getCount(); i++) {
         BodyPart bodyPart = mimeMultipart.getBodyPart(i);
         if (bodyPart.isMimeType("text/plain")) {
            builder.append(bodyPart.getContent());
         }
      }
      return builder.toString();
   }


   public static List<Email> getEmails() {
      return allEmailsList;
   }

}
