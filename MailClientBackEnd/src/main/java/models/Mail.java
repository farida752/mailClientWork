package models;

import java.util.LinkedList;
import java.util.Queue;

public class Mail {
	
	private String subject;
	private String sender;
	private String date;
	private String priority;
	private String content;
	private LinkedList attachments ;
	private Queue recievers;
	
	
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LinkedList getAttachments() {
		return attachments;
	}
	public void setAttachments(LinkedList attachments) {
		this.attachments = attachments;
	}
	public Queue getRecievers() {
		return recievers;
	}
	public void setRecievers(Queue recievers) {
		this.recievers = recievers;
	}

	@Override
	public String toString() {
		return "Mail [subject=" + subject + ", sender=" + sender + ", date=" + date + ", priority=" + priority
				+ ", content=" + content + ", attachments=" + attachments + ", recievers=" + recievers + "]";
	}
	
	
	

}
