package datastructures;

public class Queue {
	public  class SNode{
        Object data;
        SNode next;
       }
	SNode head; // head of list 
    SNode tail;
    int size=0;
	
    public void enqueue(Object item) {
    	SNode tmp=new SNode();
		tmp.data=item;
		if(size==0) {
			head=tail=tmp;
		}	
		else {
		tail.next=tmp;
		tail=tmp;
		tail.next=null;
		}
		size++;
	}
	
	public Object dequeue() {
		if(isEmpty()) {
			throw new RuntimeException("The Queue is Empty");
		}
		else {
			Object temp=head.data;
			head=head.next;
			size--;
			return temp;
		}
		
	}
	
	public boolean isEmpty() {
		return(size==0);
	}
	
	public int size() {
		return size;
	}
	public Object GetFirstElement() {
		if(isEmpty()) {
			throw new RuntimeException("The Queue is Empty");
		}
		else {
			return head.data;
		}
		
	}
	
	
	public void putElementsOfQueueInArr(String arr[]) {
		SNode h=head;int i=0;
		while(h!=null) {
			arr[i]=h.data.toString();i++;
			h=h.next;
		}
	}
}
