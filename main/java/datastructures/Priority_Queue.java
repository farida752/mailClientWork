package datastructures;

public class Priority_Queue {
int Queue_Size=0;//element with the highest priority will be removed first
	
	class SNode{
		Object data; SNode next; int priority;
	}
	
	class SingleLinkedList{
		SNode head; SNode tail;
	}
	
	SingleLinkedList s=new SingleLinkedList();
	
	public void insert(Object item, int key) {
		if(key>0) {
		SNode tmp=new SNode();tmp.data=item;tmp.priority=key;
		if(Queue_Size==0) {
		tmp.next=null;
		s.head=s.tail=tmp;
		}else {
			//Queue_Size=0
			SNode h=s.head;
			if(h.priority>key) {
				//insert at head
				tmp.next=s.head;
				s.head=tmp;
			}else {
			while(h.next!=null) {
				if(key<h.next.priority) {
					break;
				}
				h=h.next;
			}
			if(h.next==null) {
				//insert at tail
				tmp.next=null;
				s.tail.next=tmp;
				s.tail=tmp;
			}else {
				tmp.next=h.next;
				h.next=tmp;
			}
			}
		}
		Queue_Size++;
	}else {
		throw new RuntimeException("Invalid Key");
	}
	}
	
	public Object removeMin() {
		if(Queue_Size>0) {
		Object tmp=s.head.data;
		s.head=s.head.next;Queue_Size--;
		return tmp;
		}else {
			throw new RuntimeException("The Queue is Empty");
		}
		
	}
	
	public Object min() {
		if(Queue_Size>0) {
		return s.head.data;
		}else {
			throw new RuntimeException("The Queue is Empty");
		}
	}
	
	public boolean isEmpty() {
		return Queue_Size==0;
	}
	
	public int size() {
		return Queue_Size;
	}
	
	public void printQueue() {
		SNode h=s.head;
		while(h!=null) {
			System.out.print(h.data+" ");h=h.next;
		}
	}


}
