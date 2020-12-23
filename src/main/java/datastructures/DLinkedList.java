package datastructures;

import models.Mail;

public class DLinkedList {
	public class Dnode {
		
		private Mail element ;
		private Dnode prev;
		private Dnode next;
		
		public Dnode (Mail s ,Dnode p,Dnode n) {
			this.element =s;
			this.prev=p;
			this.next=n;
		}
		
		public Mail getElement() { return element; }
		public Dnode getNext() { return next; }
		public Dnode getprev() { return prev; }
		public void setElement(Mail newElem) {element = newElem;}
		public void setNext(Dnode newNext) { next = newNext; }
		public void setprev(Dnode newprev) { prev = newprev; }
	}
	
	public Dnode head;
	Dnode trailer;
	int size=0;
	public DLinkedList() {
		size = 0;
		head = new Dnode(null, null, null); // create header
		trailer = new Dnode(null, head, null); // create trailer
		head.setNext(trailer);
		            }

    public void add(int index, Mail element) {
    	if((size+1)>index&& index>=0) {
    		Dnode v=head;
    		for(int i=0;i<(index-1)+1;i++) {
    			v=v.getNext();
    		}
    		Dnode z =new Dnode(element,null,null);
    		Dnode w =v.getNext();
    		z.setprev(v);
    		z.setNext(w);
    		w.setprev(z);
    		v.setNext(z);
    		size=size+1;
    	}
    }
	
	public void add(Mail element) {
		Dnode z =new Dnode(element,null,null);
		Dnode w=trailer.getprev();
		z.setprev(w);
		z.setNext(trailer);
		w.setNext(z);
		trailer.setprev(z);
		size=size+1;
		
	}
	
	public Mail get(int index) {
		if(index<size&&index>=0) {
		Dnode v=head;
		for(int i=0;i<index+1;i++) {
			v=v.getNext();
		}
		return (v.getElement());
		}
		else {
			return null;
		}
	}
	
	public void set(int index, Mail element) {
		if(index<size&&index>=0) {
		Dnode v=head;
		for(int i=0;i<index+1;i++) {
			v=v.getNext();
		}
		v.setElement(element);
		}
	}
	
	public void clear() {
		head.setNext(trailer);
		trailer.setprev(head);
		size=0;
		
	}
	
	public boolean isEmpty() {
		 return (size ==0); 
	}
	
	public void remove(int index) {
		if(index<size&&index>=0) {
			Dnode v=head;
		for(int i=0;i<index+1;i++) {
			v=v.getNext();
		}
		Dnode p=v.getprev();
		Dnode n=v.getNext();
		p.setNext(n);
		n.setprev(p);
		v.setNext(null);
		v.setprev(null);
		size--;
	  }
	}
	
	public int size() {
		 return size;
	}
	
	public DLinkedList sublist(int fromIndex, int toIndex) {

		DLinkedList n =new DLinkedList();
	    
	    for(int i=fromIndex;i<=toIndex;i++) {
	    	n.add(get(i));
	    }
	    return n;
		
	}
	
	public boolean contains(Object o) {
		Dnode v=head;
		while(v.getNext()!=null) {
			v=v.getNext();
			if(v.getElement()==o) {
				return true;
			}
		}
		return false;
	}

}
