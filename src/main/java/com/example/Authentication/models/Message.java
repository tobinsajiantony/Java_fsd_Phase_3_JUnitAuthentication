package com.example.Authentication.models;

public class Message {
   private boolean valid;
   private String data;
   
   public Message(boolean valid, String data) {
	   this.valid = valid;
	   this.data = data;
   }

public boolean isValid() {
	return valid;
}

public void setValid(boolean valid) {
	this.valid = valid;
}

public String getData() {
	return data;
}

public void setData(String data) {
	this.data = data;
}
   
   
}
