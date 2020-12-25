package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import datastructures.DLinkedList;
import models.Contact;
import models.Filter;
import models.Folder;
import models.Mail;
import models.MailBuilder;
import models.Search;
import models.Sort;


public class App {
	
private static  App appInstance;
private String email;
private String password;
private String UserName;
private static DLinkedList list=new DLinkedList();
String fol="";
int maxPages=0;	
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
	public DLinkedList getMailsList() {
		return this.list;
	}
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
	////////////////////////////////////////////////////////////////////////////
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
	public void setViewingOptions(Folder folder, Filter filter, Sort sort,Search search,boolean doSearch) {
		this.list.clear();
		
		Folder f = (Folder) folder;
		Filter fil= (Filter) filter;
	    Sort s = (Sort) sort;
	    Search srch = search;
		 
		if(folder.getCurrentFolder().equals("Trash")) {
			folder.trashmails(email,list);
			fol=folder.getCurrentFolder();
		}
		else {
			list=folder.foldermails(email,list);
		    fol=folder.getCurrentFolder();
		    
		}
		s.SortTmpListForSearching(list);
		list=fil.Filtering(list);
		if(doSearch) {
			list=srch.search(list);
		}
		 
        if(list.size()%10==0) {//for example if we have 11 mails so we need (11+10)/10= 2 pages to show them 
        	this.maxPages=list.size()/10;
        }
        else {
        	this.maxPages=(list.size()+10)/10;
        }
	}
	////////////////////////////////////////////////////////////////
	
    
    public void deleteEmails(LinkedList mails) {}
   
    public void moveEmails(LinkedList mails, Folder des) {}
    /////////////////////////////////////////////////////////////////////////
  
    public boolean compose(Mail mail,String To) {

 	   //check subject
 	   if (mail.getSubject().trim().isEmpty()) {
 		   mail.setSubject("");
 	   }

 	   //set date
 	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
 	   String date = format.format( new Date());
 	   mail.setDate(date);

 	   //sending message
 	   if (To.equalsIgnoreCase("inbox")){
 	   	mail.sendMailToSentbox();
 	   	mail.sendMailToInbox();
 	   }
 	   else{
 	   	mail.sendMailToDraft();
 	   }


 	   return true;
    }
    ///////////////////////////////////////////////////////////////////////
    public DLinkedList listEmails(int page) {
		Mail[] m = new Mail[10];//the list which will be sent to the front end
    	int j=0;
    	for(int i=0;i<10;i++) {
    		m[i]=null;
    	}
    	for(int i=(0+(page-1)*10);i<=(page*10);i++) {
    		//if we are in page 2 for example we will view emails from 11 to 20 (11+10-1) 10 mails per page
    		if(i!=list.size()) {
    			 
    			 MailBuilder b=new  MailBuilder();
    			  b.setContent(((Mail)list.get(i)).getContent());
    			  b.setRecievers(((Mail)list.get(i)).getRecievers());
    			  b.setDate(((Mail)list.get(i)).getDate());
    			  b.setAttachments(((Mail)list.get(i)).getAttachments());
    			  b.setPriority(((Mail)list.get(i)).getPriority());
    			  b.setSender(((Mail)list.get(i)).getSender());
    			  Mail mail=b.getMail();
    			  m[j]=mail;
    			  j++;
    		}
    		else {
    			break;
    		}
    	}
    	
    	//convert it to new small list then 2D array ro be given to front end
    	int i=0;
    	DLinkedList smalllistOfMails=new DLinkedList();
    	while(i<m.length) {
    		smalllistOfMails.add(m[i]);
    		i++;
    	}
    	
    	
    	return smalllistOfMails;//list of mails to be shown
	}
}
