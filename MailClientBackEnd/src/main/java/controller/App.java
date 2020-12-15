package controller;

import java.util.LinkedList;


import models.Contact;
import models.Filter;
import models.Folder;
import models.Mail;
import models.Search;
import models.Sort;


public class App {
	
private static  App appInstance;
	
    //the singleton consept
    //private constructor
	private  App() {
		
	}
	
  //to get a the only one instance
	public static App getInstance() {
		if(appInstance==null) {
			synchronized (App.class) {
				if(appInstance==null) {
					appInstance=new App();
				}
			}
		}
		return appInstance;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean signin(String email, String password) {
		return false;
	}
	
	public boolean signup(Contact contact) {
		return false;
	}
	
	public void setViewingOptions(Folder folder, Filter filter, Sort sort,Search search) {
	
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
