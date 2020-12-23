package models;

import java.util.Stack;

import datastructures.DLinkedList;
import datastructures.DoubleLinkedList;
import datastructures.StackMethods;

public class Filter {
 private String filterAccordingTo;
 private String wordToFilter;
 

 
public Filter(String filterAccordingTo, String wordToFilter) {
	super();
	this.filterAccordingTo = filterAccordingTo;
	this.wordToFilter = wordToFilter;
}


public DLinkedList Filtering(DLinkedList mails) {
	DLinkedList f1 = new DLinkedList();
	if(filterAccordingTo.equalsIgnoreCase("none")) {
		f1=mails;
		return f1;
	}
	
	String[]sub=new String [mails.size()];
	String[]sender= new String[mails.size()];
	
	
	
	DoubleLinkedList exists= new DoubleLinkedList();
	DLinkedList mailExists= new DLinkedList();
	

	for(int i=0;i<mails.size();i++) {
		Mail s=(Mail)mails.get(i);
		sub[i]=s.getSubject();
		sender[i]=s.getSender();
		
		
	}
	
	Sort S= new Sort(filterAccordingTo);
	sub = S.Quicksort1(sub);
	sender=S.Quicksort1(sender);
     S.SortTmpListForSearching(mails);
	
	
	
	
	
	if(filterAccordingTo.equalsIgnoreCase("subject")) {
		exists=BinarySearch(sub,mails.size(),wordToFilter,1);
		
	}
	
	else if(filterAccordingTo.equalsIgnoreCase("sender")) {
		
		
		exists=BinarySearch(sender,mails.size(),this.wordToFilter,0);
		
	}
	for (int j=0;j<exists.size();j++) {
		mailExists.add((Mail)mails.get((Integer)exists.get(j)));
		
	}
	return mailExists;
	
	
}
///////////////////////////////////////////////////////////////////////////////

public DoubleLinkedList BinarySearch(String[] A,int n,String x,int y) {
	DoubleLinkedList Index= new DoubleLinkedList();   
	Stack Stack=new Stack();
	Stack.push(0);
	Stack.push(n-1);
	int start; int end;  int mid; 
	while(!Stack.isEmpty()) {
		end=(Integer)Stack.pop();
		start=(Integer)Stack.pop();
		if(start>end==false) {
			mid=(start+end)/2;
			
			if(A[mid].equalsIgnoreCase(x)&&(y==0))
			{
				Index.add(mid);
				A[mid]="";
				Stack.push(mid+1);
				Stack.push(end);
				Stack.push(start);
				Stack.push(mid-1);
			}
			else if((A[mid].contains(x)==true)&&(y==1)) 
			{
				Index.add(mid);
				A[mid]="";
				Stack.push(mid+1);
				Stack.push(end);
				Stack.push(start);
				Stack.push(mid-1);
			}
			else if(x.compareToIgnoreCase(A[mid])<0)
			{
				Stack.push(0);
				Stack.push(mid-1);
			}
			else if(x.compareToIgnoreCase(A[mid])>0) 
			{
				Stack.push(mid+1);
				Stack.push(end);
			}
		}
		
	}
	return Index;
	
}

}
