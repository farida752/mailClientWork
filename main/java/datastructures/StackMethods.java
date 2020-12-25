package datastructures;

public class StackMethods {
	class SNode{
		Object data;
		SNode next;
	}
	class LinkedList{
		SNode head;
		SNode tail;
		int size=0;
	}
	LinkedList s=new LinkedList();
	public Object pop() {
		if(s.size==1) {
			SNode h=s.head;
			s.head=s.tail=null;
			s.size--;
			return h.data;
		}
		else if(s.size==2) {
			SNode tmp=s.head.next;
			s.head.next=null;
			s.tail=s.head;
			s.size--;
			return tmp.data;
		}
		else if(s.size>2) {
		SNode h=s.head;
		SNode tmp=new SNode();
		while(h.next.next!=null) {
			h=h.next;
		}
		tmp.data=h.next.data;
		tmp.next=null;
		h.next=null;
		s.tail=h;
		s.size--;
		return tmp.data;
		}
		else{
			throw new RuntimeException("The Stack is empty (pop)");
		}
		
	}
	
	
	public Object peek() {
		if(s.size>0) {
		return s.tail.data;
		}
		else {
			throw new RuntimeException("The Stack is empty (peek)");
		}
	}
	
	
	public void push(Object element) {
		//Add last
		SNode tmp=new SNode();
		tmp.data=element;
		tmp.next=null;
		if(s.size==0) {
			//add at head
			s.head=tmp;
			s.tail=s.head;
			s.size++;
		}
		else {
			SNode h=s.head;
			while(h.next!=null) {
				h=h.next;
			}
			h.next=tmp;
			s.tail=tmp;
			s.size++;
		}
	}
	
	
	public boolean isEmpty() {
		if(s.size==0||s.head==null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public int size() {
		return s.size;
	}

}
