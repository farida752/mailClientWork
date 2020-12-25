package com.example.demo;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.boot.SpringApplication;
 //import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.AppController;
import datastructures.DLinkedList;
import datastructures.DoubleLinkedList;
import models.Contact;
import models.Filter;
import models.Mail;
import models.MailBuilder;
import models.Search;
import models.Sort;




@SpringBootApplication
@ComponentScan(basePackageClasses= AppController.class)
public class MailClientBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailClientBackEndApplication.class, args);
		
		LinkedList attachments = new LinkedList();
		attachments.add("//lgjkg");
		
		
		Mail m1 = new Mail("mail1","farida","12/12/2012","urgent","hi all",attachments,null);
		Mail m2 = new Mail("mail2","katrin","12/12/2012","urgent","hi all",attachments,null);
		Mail m3 = new Mail("mail3","eman"  ,"12/12/2012","urgent","hi all",attachments,null);
		Mail m4 = new Mail("mail4","sara"  ,"12/12/2012","unimportant","hi all",attachments,null);
		
		DLinkedList l = new DLinkedList ();
		l.add(m1);
		l.add(m4);
		l.add(m3);
		l.add(m2);
		
		Contact c1 = new Contact("farida","e");
		Contact c2 = new Contact("katrin","d");
		Contact c3 = new Contact("eman","c");
		Contact c4 = new Contact("sara","b");
		Contact c5 = new Contact("zyad","a");
		
		DoubleLinkedList contacts= new DoubleLinkedList();
		contacts.add(c1);
		contacts.add(c2);
		contacts.add(c3);
		contacts.add(c4);
		contacts.add(c5);
	
		
		/*Sort s = new Sort("e-mail");
		contacts= s.sortContact(contacts);*/
		Search s = new Search("zyad");
		contacts=s.searchContact(contacts);
		/*for(int i=0; i<contacts.size();i++) {
			System.out.println(((Contact)contacts.get(i)).toString());
				}*/
		/*Filter f =new Filter("subject","mail3");
		DLinkedList k = f.Filtering(l);
		for(int i=0; i<k.size();i++) {
		System.out.println(((Mail)k.get(i)).toString());
			}*/
		/*Search s = new Search("sara");
		DLinkedList k =s.search(l);
		for(int i=0; i<k.size();i++) {
			System.out.println(((Mail)k.get(i)).toString());
				}*/
}}
