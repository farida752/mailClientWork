package controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


import models.Contact;
import models.Filter;
import models.Folder;
import models.Mail;
import models.Search;
import models.Sort;


public class App {
	
private static  App appInstance;
private String email;
private String password;
private String UserName;
	
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
	//set user name
		public void setUserName(String UserName) {
			this.UserName = UserName;
		}
		////////////////////////////////////////////////////////
	public boolean signin(String email, String password) {
		this.email = email;
		this.password = password;
		return true;
		
	}
	
	public boolean signup(Contact contact){
		//in order to write in the file we use mapper.writeValue

			//creating email folder in accounts folder
			 String ss = "accounts\\"+contact.getEmail();
			 File file = new File(ss);
			 file.mkdir();

			 //creating inbox folder in email folder
			 ss = "accounts\\"+contact.getEmail()+"\\Inbox";
			 File inbox = new File(ss);
			 inbox.mkdirs();

			 //creating index.json in inbox folder
		     ss = "accounts\\"+contact.getEmail()+"\\Inbox\\index.json";
			 File indexInbox = new File(ss);
			 try {
				 indexInbox.createNewFile();
			 } catch (IOException e) {
				e.printStackTrace();
			 }

			 //creating sent folder in email folder
			 ss = "accounts\\"+ contact.getEmail()+"\\sent";
			 File sent = new File(ss);
			 sent.mkdirs();

			 //creating index.json in sent folder
			 ss = "accounts\\"+ contact.getEmail()+"\\sent\\index.json";
		 	 File indexSent = new File(ss);
		 	 try {
				 indexSent.createNewFile();
		 	 } catch (IOException e) {
				e.printStackTrace();
			 }

			 //creating drafts folder in email folder
		     ss = "accounts\\"+contact.getEmail()+"\\Drafts";
			 File draft = new File(ss);
			 draft.mkdirs();

			 //creating index.json in drafts folder
			 ss = "accounts\\"+contact.getEmail()+"\\Drafts\\index.json";
			 File indexDrafts = new File(ss);
			 try {
		 	  	 indexDrafts.createNewFile();
		 	 } catch (IOException e) {
				 e.printStackTrace();
			 }

		     //creating trash folder in email folder
		     ss ="accounts\\"+ contact.getEmail()+"\\Trash";
			 File trash = new File(ss);
			 trash.mkdirs();

			 //creating index.json in trash folder
			 ss ="accounts\\"+ contact.getEmail()+"\\Trash\\index.json";
			 File indexTrash = new File(ss);
			 try {
				 indexTrash.createNewFile();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }

			 //creating contacts.json in email folder
			 ss = "accounts\\"+contact.getEmail()+"\\contacts.json";
			 File cont = new File(ss);
			 try {
				cont.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return true;
	}
	////////////////////////////////////////////////////////////////////////////////////
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
