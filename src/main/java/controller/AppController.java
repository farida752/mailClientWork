package controller;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import models.Contact;
import models.Filter;
import models.Folder;
import models.Mail;
import models.Search;
import models.Sort;
 @RestController
 @CrossOrigin
public class AppController {
	 private App app = App.getInstance();
	 private proxyClass proxy = new proxyClass();
	 String[] viewMail = new String[7] ;
	
	 
    @GetMapping("/signin")
	public boolean signin(@RequestParam(value = "email")String email,@RequestParam(value = "password") String password) {
  
    	return proxy.signin(email,password);	
	}
    /////////////////////////////////////////////////////////////////////////
    @GetMapping("/signup")
	public boolean signup(@RequestParam(value = "email")String email,@RequestParam(value = "password") String password,
			@RequestParam(value = "username") String username) {
    	Contact contact = new Contact (email,password,username);
		return proxy.signup(contact);
	}
	/////////////////////////////////////////////////////////////////////////
    @PutMapping("/setViewingOptions")
	public void setViewingOptions(
			@RequestParam(value = "currentFolder")String currentFolder,@RequestParam(value = "showingInboxDefault")String showingInboxDefault,
			@RequestParam(value = "filterAccordingTo")String filterAccordingTo,@RequestParam(value = "wordToFilter")String wordToFilter,
			@RequestParam(value = "sortAccordingTo")String sortAccordingTo,
			@RequestParam(value = "searchAccordingTo")String searchAccordingTo) {
	Folder folder = new Folder(currentFolder,showingInboxDefault);
	Filter filter = new Filter(filterAccordingTo,wordToFilter);
	Sort sort =new Sort(sortAccordingTo);
	Search search = new Search(searchAccordingTo);
    app.setViewingOptions(folder, filter, sort,search);
	}
	 
    
    @GetMapping("/getCurrentPageMails")
    public Mail[] listEmails(int page){
    	return app.listEmails(page);
    }
    
    @DeleteMapping("/deleteMail")
    public void deleteEmails(@RequestParam(value = "selectedMails")String[][] selectedMails) {
   //fuction to transfer the array mails of strings in linkedlist 
    	//app.deleteEmails(mails);
    }
    @PutMapping("/moveEmails")
    public void moveEmails(@RequestParam(value = "selectedMails")String[][] selectedMails,
    		@RequestParam(value = "currentFolder")String currentFolder,@RequestParam(value = "showingInboxDefault")String showingInboxDefault) {
    	//use the same function to transfer array to linked list 
    }
    @GetMapping("/composeNewMail")
   public boolean compose(@RequestParam(value = "newMail")String[] newMail) {
	   //we need function to transfer this array to object of Mail
	   //return app.compose(email);
    	return false;
   }
    
    @GetMapping("/setViewMail")
	public void setViewMail (@RequestParam(value = "sender")String sender,@RequestParam(value = "subject")String subject,
			@RequestParam(value = "content")String content,@RequestParam(value = "priority")String priority,
			@RequestParam(value = "recievers")String recievers,@RequestParam(value = "attachments")String attachments) {
		this.viewMail[0]= recievers ;
		this.viewMail[1]= sender ;
		this.viewMail[2]= subject ;
		this.viewMail[3]= priority;
		this.viewMail[4]= content;
		this.viewMail[5]= attachments;
		
		//System.out.println(viewMail.toString());
	}
    @GetMapping("/getViewMail")
	public String[] setViewMail () {
		 return this.viewMail;
	}
}
