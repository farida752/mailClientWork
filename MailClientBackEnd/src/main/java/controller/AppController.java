package controller;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	 
    @GetMapping("/signin")
	public boolean signin(@RequestParam(value = "email")String email,@RequestParam(value = "password") String password) {
		
    	
    	return app.signin(email,password);
		
	}
    @GetMapping("/signup")
	public boolean signup(@RequestParam(value = "email")String email,@RequestParam(value = "password") String password,
			@RequestParam(value = "username") String username) {
    	Contact contact = new Contact (email,password,username);
		return app.signup(contact);
	}
	
    @PutMapping("/setViewingOptions")
	public void setViewingOptions(@RequestParam(value = "currentFolder")String currentFolder,@RequestParam(value = "showingInboxDefault")String showingInboxDefault,
			@RequestParam(value = "filterAccordingTo")String filterAccordingTo,@RequestParam(value = "wordToFilter")String wordToFilter,
			@RequestParam(value = "sortAccordingTo")String sortAccordingTo,
			@RequestParam(value = "searchAccordingTo")String searchAccordingTo) {
	Folder folder = new Folder(currentFolder,showingInboxDefault);
	Filter filter = new Filter(filterAccordingTo,wordToFilter);
	Sort sort =new Sort(sortAccordingTo);
	Search search = new Search(searchAccordingTo);
    app.setViewingOptions(folder, filter, sort,search);
	}
	
    public Mail[] listEmails(int page){
    	
    	return new Mail [50];
    }
    
    public void deleteEmails(LinkedList mails) {}
   
    public void moveEmails(LinkedList mails, Folder des) {}
  
   public boolean compose(Mail email) {
	   return false;
   }
}
