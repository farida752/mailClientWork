package models;

import java.util.LinkedList;
import java.util.Queue;

public class MailBuilder {
  
	private String subject;
	private String sender;
	private String date;
	private String priority;
	private String content;
	private LinkedList attachments ;
	private Queue recievers;
	private Mail mail;
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setAttachments(LinkedList attachments) {
		this.attachments = attachments;
	}
	public void setRecievers(Queue recievers) {
		this.recievers = recievers;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}
	
	public Mail getMail() {
		mail =new Mail();
		mail.setSubject(subject);
		mail.setSender(sender);
		mail.setDate(date);
		mail.setContent(content);
		mail.setPriority(priority);
		mail.setAttachments(attachments);
		mail.setRecievers(recievers);
		return mail;
			
	}
}
