package com.theskysid.gemailreceiver.Service;

import com.theskysid.gemailreceiver.Entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GEmailReceiverService {

   private List<Email> emails = new ArrayList<Email>();
   private int lastMessageCount = -1; //



}
