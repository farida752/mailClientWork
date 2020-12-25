package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import datastructures.DLinkedList;
import datastructures.Priority_Queue;
import datastructures.Queue;

public class Folder {
 
	private String currentFolder;
	private String  showingInboxDefault;
	

	public Folder(String currentFolder, String showingInboxDefault) {
		super();
		this.currentFolder = currentFolder;
		this.showingInboxDefault = showingInboxDefault;
	}


	public String getCurrentFolder() {
		return currentFolder;
	}


	public void setCurrentFolder(String currentFolder) {
		this.currentFolder = currentFolder;
	}


	public String getShowingInboxDefault() {
		return showingInboxDefault;
	}


	public void setShowingInboxDefault(String showingInboxDefault) {
		this.showingInboxDefault = showingInboxDefault;
	}
	public DLinkedList foldermails(String user,DLinkedList list) {//list is the list mails to be shown in the current folder
		//now we will read the index file in the current folder//this list has email with all its attributes
		//this method only reads the mails and put them in this list
		String open = "accounts//"+user+"//"+currentFolder+"//index.json";
		JSONParser jsonP=new JSONParser();
		
		try {
			File file=new File(open);
			if(file.length()>0) {
			BufferedReader br = new BufferedReader(new FileReader(open));
			Queue listOfRecievers=new Queue();
			LinkedList attachments=new LinkedList();
			Object obj=jsonP.parse(br);//this will give us all data from this index file.json
			JSONObject obj2=(JSONObject)obj;
			JSONArray mailsInf=(JSONArray)obj2.get("Mails");                           //we will tranfer data to a json array
			MailBuilder myMail=new MailBuilder();
			Priority_Queue p=new Priority_Queue();
			if(mailsInf!=null) {
			for (Object mail : mailsInf) {
				JSONObject mm = (JSONObject) mail;
	            //these are the mail subject,sender,date ,priority available in this file
	           // Mail myMail=new Mail((String) mm.get("Subject"), (String) mm.get("Sender"),
	            //		(String) mm.get("Date"),(String) mm.get("Priority"),"",attachments,listOfRecievers );
	            
	            myMail=new MailBuilder();
	            //(String) mm.get("Subject"), (String) mm.get("Sender"),
        		//(String) mm.get("Date"),(String) mm.get("Priority"),"",attachments,listOfRecievers
	            myMail.setSubject((String) mm.get("Subject"));
	            myMail.setDate((String) mm.get("Date"));
	            myMail.setPriority((String) mm.get("Priority"));
	            myMail.setRecievers(listOfRecievers);
	            myMail.setAttachments(attachments);
	            myMail.setSender((String) mm.get("Sender"));
	            myMail.setContent("");
	            Mail myMailInst=myMail.getMail();
               File f = new File("accounts//"+user+"//"+currentFolder+"//"+myMailInst.getSender()
               +myMailInst.getSubject()
               +myMailInst.getDate());
				
				if( f.exists()) {
					String mopen = "accounts//"+user+"//"+currentFolder+"//"+myMailInst.getSender()+
							myMailInst.getSubject()+myMailInst.getDate()+"//message.json";
					BufferedReader br2  ;
					try (FileReader x = new FileReader(mopen)){
						file=new File(mopen);
						if(file.length()>0) {
						br2 = new BufferedReader(x);
						obj=jsonP.parse(br2);//this will give us all data from this index file.json
						JSONObject all=(JSONObject)obj;//we converted the whole file to object
						JSONObject getMessages=(JSONObject)all.get("Message");
						JSONArray arrOfRecievers=(JSONArray)getMessages.get("Receivers");
						myMail.setContent((String)getMessages.get("Content"));
						listOfRecievers=new Queue();
						System.out.println(arrOfRecievers.get(0)+"the rec");
						if(arrOfRecievers!=null) {
							int len=arrOfRecievers.size();
							for(int i=0;i<len;i++) {
								listOfRecievers.enqueue(arrOfRecievers.get(i));
							}
						}
						myMail.setRecievers(listOfRecievers);
						//System.out.println(myMail.getRecievers().GetFirstElement());
						br2.close();
						x.close();	
					}
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					try {
						FileReader x =new FileReader("accounts//"+user+"//"+currentFolder+"//"+
								myMailInst.getSender()+myMailInst.getSubject()
								+myMailInst.getDate()+"//attachments//list.json");
						br2 = new BufferedReader(x);
						file=new File("accounts//"+user+"//"+currentFolder+"//"+
								myMailInst.getSender()+myMailInst.getSubject()+myMailInst.getDate()+"//attachments//list.json");
						if(file.length()>0) {
						obj=jsonP.parse(br2);//this will give us all data from this index file.json
						JSONObject att=(JSONObject)obj;
						JSONArray attachment=(JSONArray) att.get("Attachments");
						attachments=new LinkedList<>();
						if(attachment!=null) {
							int len=attachment.size();
							for(int i=0;i<len;i++) {
								attachments.add((attachment.get(i).toString()));
							}
						}
						myMail.setAttachments(attachments);
						br2.close();
						x.close();
						}
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					list.add(myMail.getMail());
	            //we will add this mail to the list 
			}
			br.close();
			}
			}
			//now we have the list of mails lets check if the current folder is inbox then check showing inbox default :
			//priority or default
			if(showingInboxDefault.contentEquals("priority")&&currentFolder.contentEquals("Inbox")) {
				//then sort the list acc to priority first 
				p=new Priority_Queue();
				int i=0;int key=0;
				while(i<list.size()) {
					
					if(list.get(i).getPriority().contentEquals("Urgent/Important")) {
						key=1;
					}else if(list.get(i).getPriority().contentEquals("Not Urgent/Important")) {
						key=2;
					}else if(list.get(i).getPriority().contentEquals("Urgent/Not Important")) {
						key=3;
					}else {//list.get(i).getPriority().contentEquals("Not Urgent/Not Important")
						key=4;
					}
					p.insert(list.get(i),key );
					i++;
				}
				//now the list which will be shown is the arranged queue
				//in queue the mails of smallest key are those in the first of queue so the are remover first
				list.clear();
				while(p.size()>0) {
					list.add((Mail)p.removeMin());
				}
				//the new list is arranged acc to priority 
			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void trashmails(String user,DLinkedList list) {
		String open = "accounts//"+user+"//Trash"+"//index.json";
		
		BufferedReader br ;
		try {
			br = new BufferedReader(new FileReader(open));
			Queue listOfRecievers=new Queue();
			LinkedList attachments=new LinkedList();
			JSONParser jsonP=new JSONParser();
			
			Object obj=jsonP.parse(br);//this will give us all data from this index file.json
			JSONObject obj2=(JSONObject)obj;
			JSONArray mailsInf=(JSONArray)obj2.get("Mails");                     //we will tranfer data to a json array
			MailBuilder myMail=new MailBuilder();
			if(mailsInf!=null) {
			for (Object mail : mailsInf) {
				JSONObject mm = (JSONObject) mail;
	            //these are the mail subject,sender,date ,priority available in this file
				 //Mail myMail=new Mail((String) mm.get("Subject"), (String) mm.get("Sender"),
		         //   		(String) mm.get("Date"),(String) mm.get("Priority"),"",attachments,listOfRecievers );
	            
				myMail=new MailBuilder();
	            //(String) mm.get("Subject"), (String) mm.get("Sender"),
        		//(String) mm.get("Date"),(String) mm.get("Priority"),"",attachments,listOfRecievers
	            myMail.setSubject((String) mm.get("Subject"));
	            myMail.setDate((String) mm.get("Date"));
	            myMail.setPriority((String) mm.get("Priority"));
	            myMail.setRecievers(listOfRecievers);
	            myMail.setAttachments(attachments);
	            myMail.setSender((String) mm.get("Sender"));
	            myMail.setContent("");
	            Mail myMailInst=myMail.getMail();
	            File f = new File("accounts//"+user+"//Trash"+"//"+myMailInst.getSender()+myMailInst.getSubject()
	            +myMailInst.getDate());
				long diff = System.currentTimeMillis() - f.lastModified();
				
				if( diff<(30 * 24 * 60 * 60 * 1000.0)) {
					
					String mopen = "accounts//"+user+"//"+"Trash"+"//"+myMailInst.getSender()+
							myMailInst.getSubject()+myMailInst.getDate()+"//message.json";
					BufferedReader br2  ;
					File file=new File(mopen);
					if(mopen.length()>0) {
						try (FileReader x = new FileReader(mopen)){
							file=new File(mopen);
							if(file.length()>0) {
							br2 = new BufferedReader(x);
							obj=jsonP.parse(br2);//this will give us all data from this index file.json
							JSONObject all=(JSONObject)obj;//we converted the whole file to object
							JSONObject getMessages=(JSONObject)all.get("Message");
							myMail.setContent((String)getMessages.get("Content"));
							System.out.println(myMailInst.getContent()+" = content");
							JSONArray arrOfRecievers=(JSONArray)getMessages.get("Receivers");
							listOfRecievers =new Queue();
							int len=arrOfRecievers.size();
								for(int i=0;i<len;i++) {
									listOfRecievers.enqueue(arrOfRecievers.get(i));
									System.out.println(listOfRecievers.GetFirstElement()+"first");
								}
						    
							myMail.setRecievers(listOfRecievers);
							br2.close();
							x.close();	
						}
						}
						catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
					try {
						file=new File("accounts//"+user+"//"+currentFolder+"//"+
								myMailInst.getSender()+myMailInst.getSubject()+myMailInst.getDate()+"//attachments//list.json");
						FileReader x =new FileReader("accounts//"+user+"//"+currentFolder+"//"+
								myMailInst.getSender()+myMailInst.getSubject()+myMailInst.getDate()+"//attachments//list.json");
						if(file.length()>0) {
						br2 = new BufferedReader(x);
						obj=jsonP.parse(br2);//this will give us all data from this index file.json
						JSONObject att=(JSONObject)obj;
						JSONArray attachment=(JSONArray)att.get("Attachments");
						attachments=new LinkedList<>();
						if(attachment!=null) {
							int len=attachment.size();
							for(int i=0;i<len;i++) {
								attachments.add((attachment.get(i).toString()));
							}
						}
						myMail.setAttachments(attachments);
						br2.close();
						x.close();
						}
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					list.add(myMail.getMail());
					
				}
				else {
					deleteFile(f);
				}
			}
		}
			br.close();
		}catch(Exception e) {
			
		}
			
		if(list.size()!=0) {
   		
	    	File oo= new File(open);
	    	FileOutputStream fos;
			try {
				fos = new FileOutputStream(oo);
				for(int i=0;i<list.size();i++) {
					try {
						fos = new FileOutputStream(oo,true);
						BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
						Mail y = (Mail) list.get(i);
						JSONArray jsonArray = new JSONArray();
			            JSONObject object = new JSONObject();
		                JSONObject jsonObject = new JSONObject();
		                jsonObject.put("Subject", y.getSubject());
		                jsonObject.put("Sender", y.getSender());
		                jsonObject.put("Date", y.getDate());
		                jsonObject.put("Priority", y.getPriority());
		                jsonArray.add(jsonObject);
		                object.put("Mails", jsonArray);
		                f.write(object.toString());
		                f.close();	
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
   	 else {
   		 String o ="accounts//"+user+"//Trash"+"//index.json";
 	    	File oo= new File(o);
 	    	FileOutputStream fos;
   		 try {
					fos = new FileOutputStream(oo);
					BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
						f.write("");
						f.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	 }
   	 
		
	}
	
	
	
	public static void deleteFile(File element) {
	    if (element.isDirectory()) {
	        for (File sub : element.listFiles()) {
	            deleteFile(sub);
	        }
	    }
	    element.delete();
	}
}
