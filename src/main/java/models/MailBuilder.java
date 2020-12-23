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
	private Mail mail ;
	
	public MailBuilder setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public MailBuilder setSender(String sender) {
		this.sender = sender;
		return this;
	}
	public MailBuilder setDate(String date) {
		this.date = date;
		return this;
	}
	public MailBuilder setPriority(String priority) {
		this.priority = priority;
		return this;
	}
	public MailBuilder setContent(String content) {
		this.content = content;
		return this;
	}
	public MailBuilder setAttachments(LinkedList attachments) {
		this.attachments = attachments;
		return this;
	}
	public MailBuilder setRecievers(Queue recievers) {
		this.recievers = recievers;
		return this;
	}
	
	
	public Mail getMail() {
		
		return new Mail(subject,sender,date,priority,content,attachments,recievers);
			
	}
}
