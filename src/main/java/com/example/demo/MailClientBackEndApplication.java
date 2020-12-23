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
		
		
		
		
		/*Filter f =new Filter("subject","mail3");
		DLinkedList k = f.Filtering(l);
		for(int i=0; i<k.size();i++) {
		System.out.println(((Mail)k.get(i)).toString());
			}*/
		Search s = new Search("eman");
		DLinkedList k =s.search(l);
		for(int i=0; i<k.size();i++) {
			System.out.println(((Mail)k.get(i)).toString());
				}
}}
