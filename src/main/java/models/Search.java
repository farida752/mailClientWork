package models;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import datastructures.DLinkedList;

public class Search {
	 private String wordToSearch;

	public Search(String wordToSearch) {
		super();
		this.wordToSearch = wordToSearch;
	}
	
	Sort s = new Sort("sender");
	Stack index = new Stack();
	DLinkedList result = new DLinkedList(); 
	
	////////////////////////////////////////////////////////////////////////////
	public int BinarySearch(String[] A, String x) {
		String[] sortedA = s.Quicksort1(A);
		index.push(0);
		index.push(A.length-1);
		while(!index.isEmpty()) {
			int end = (Integer) index.pop();
			int start = (Integer) index.pop();
			if(end >= start) {
				int mid = (start + end )/2;
				if(sortedA[mid].compareToIgnoreCase(x) == 0 ) {
					return mid;
				}else if(x.compareToIgnoreCase(sortedA[mid]) >= 1 ) {
					index.push(mid+1);
					index.push(end);
				}else if (x.compareToIgnoreCase(sortedA[mid]) <= -1 ) {
					index.push(0);
					index.push(mid-1);
				}
			}else {
				break;
			}
			
		}
		return -1;
	}
	////////////////////////////////////////////////////////////////////////////
	public DLinkedList search(DLinkedList mails) {
		
		for(int i =0;i< mails.size();i++) {
			
			Mail s = (Mail) mails.get(i) ;
			
			//Search in Senders, Subjects, contents and dates
			String[] subject = convertSentance(s.getSubject());
			String[] content = convertSentance(s.getContent());
			String[] date = s.getDate().split("\\s+");
			String[] priority = convertSentance(s.getPriority());

			if(s.getSender().contains(wordToSearch) ||BinarySearch(subject,wordToSearch) != -1 
					||BinarySearch(content,wordToSearch) != -1 ||BinarySearch(date,wordToSearch)!= -1
					||BinarySearch(priority,wordToSearch) != -1) {
				result.add(mails.get(i));
				continue;
			}
			
			//Search in attachments
			LinkedList attach = ((Mail) mails.get(i)).getAttachments();
			for(int j=0;j< attach.size();j++) {
				if(attach.get(j).toString().contains(wordToSearch)) {
					result.add(mails.get(i));
					continue;
				}
			}
		/*	//Search in receivers
			 
			Queue Q=s.getRecievers();
			LinkedList r=new LinkedList();
			int u=0;
			
			while(!Q.isEmpty()) {
				r.add(Q.remove());
			}

			for(int y=0;y<r.size();y++) {
				Q.add(r.get(y));
			}
			 
			for(int j=0;j< r.size();j++) {
				if(r.get(j).toString().contains(wordToSearch)) {
					result.add(mails.get(i));
					continue;
				}
			}*/
			
		}
		return result;
		
	}

	public String[] convertSentance(String sentance ) {
		String[] words = sentance.split("\\s+");
		for (int i = 0; i < words.length; i++) {
		    words[i] = words[i].replaceAll("[^\\w]", "");
		}
		return words;
	}
	
}
