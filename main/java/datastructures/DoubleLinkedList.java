package datastructures;


public class DoubleLinkedList {
	
	
		static class Node {
			   Object data;
			   Node next;
			   Node prev;
		   }
		   
		   Node head ;
		   public void addatstart(Object element) {
			   Node node=new Node();
				node.data=element;
				node.next=head;
				head.prev=node;
				head=node;}
			
		   
		   
		public void add(int index, Object element) { 
			 int s= this.size();
			  if((index>s-1)||(index<0)) {throw new NullPointerException("invalid index .") ; }
			if (index==0) {this.addatstart(element);}
			else { 
				Node node=new Node();
			    node.data=element;
				Node n=head;
				for (int i=0; i<index;i++) {
					n=n.next;
				}
				Node storeprev=n.prev;
				node.prev=storeprev;
				node.next=n;
				n.prev=node;
				storeprev.next=node;}}
				

		
		public void add(Object element) {
			Node node=new Node();
			node.data=element;
			if (head==null) {head=node;}
			else { Node n=head;
				while (n.next!=null) {
					n=n.next;
				}
				n.next =node;
				node.prev=n;
				node.next=null;}}
			

		
		public Object get(int index) {
			 int s= this.size();
			  if((index>s-1)||(index<0)) {throw new NullPointerException("invalid index .") ; }
			Node n=head;
			for(int i=0;i<index;i++) {
				n=n.next;
			}
			return n.data;
		}

		
		public void set(int index, Object element) {
			 int s= this.size();
			  if((index>s-1)||(index<0)) {throw new NullPointerException("invalid index .") ; }
			Node n=head;
			for(int i=0;i<index;i++) {
				n=n.next;
			}
			n.data=element;
			
		}

		
		public void clear() {
			for(int j=this.size()-1;j>-1;j--) {
				this.remove(j);
			}
			
		}

		
		public boolean isEmpty() {
			boolean t=false;
			try{
				int size=this.size();
				if( size!=0) {
		        t=false;
		}}
			catch(NullPointerException e){
				t= true;
			}
			return t;
		}

		
		public void remove(int index) {
			 int s= this.size();
			  if((index>s-1)||(index<0)) {throw new NullPointerException("invalid index .") ; }
			if(index==0) {head=head.next;}
			else {Node n=head;
				for(int i=0;i<index;i++) {
					n=n.next;
				}if (n.next==null) {
					Node storeprev=n.prev;
					storeprev.next=null;}
				
				else{Node storenext=n.next;
				Node storeprev=n.prev;
				storeprev.next=storenext;
				storenext.prev=storeprev;}}}
			

		
		public int size() {
			Node n=head;
			int s=0;
			while(n.next!=null) {
				n=n.next ;
				s++;
			}
			return s+1;
		}

		
		

		
		public boolean contains(Object o) {
			Node n=head;
			while(n.next!=null) {
				if(n.data==o) {
					return true;
				}
				n=n.next;
			}
			return false;
		}

		
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public void show() {
			  
			  try { Node n=head ;
				  while(n.next!=null) {
					  System.out.println(n.data);
					  n=n.next;
				  }
				  System.out.println(n.data); }
			  catch(NullPointerException e) {
				  System.out.println("empty list");
			  }
				  
			  }
		
	}
