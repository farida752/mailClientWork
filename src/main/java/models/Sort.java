package models;

import datastructures.DLinkedList;
import datastructures.DLinkedList.Dnode;
import datastructures.Priority_Queue;
import datastructures.QuickSortByStack;
import datastructures.StackMethods;

public class Sort {
 private String sortAccordingTo;

public Sort(String sortAccordingTo) {
	this.sortAccordingTo = sortAccordingTo;
}

public void swap(DLinkedList d,Dnode a,Dnode b) {
	Mail tmp;
	tmp=a.getElement();
	a.setElement(b.getElement());b.setElement(tmp);
}


public void SortTmpListForSearching(DLinkedList l) {
	if(sortAccordingTo.contentEquals("dateAsc")) {
		if(l.size()>0) {
		String arrOfDates[]=new String[l.size()];int counter =0;
		Dnode v=l.head.getNext();
		while(counter<l.size()) {
			arrOfDates[counter]=v.getElement().getDate();
			counter++;
			v=v.getNext();
		}
		QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfDates);int i=0;

		//loop for sorting list
		v=l.head.getNext();i=0;int x=0;counter=0;
		while(counter<l.size()) {
			if(v.getElement().getDate().contentEquals(arrOfDates[i])==false) {
				x=counter;Dnode y=v;
				while(x<l.size()) {
					if(y.getElement().getDate().contentEquals(arrOfDates[i])==false) {
						x++;y=y.getNext();
					}else {
						break;
					}
				}
				i++;counter++;
				swap(l,y,v);v=v.getNext();
			}else {
				i++;counter++;v=v.getNext();
			}
		}

		}
		
		
	}
	else if(sortAccordingTo.contentEquals("dateDesc")) {
		if(l.size()>0) {
		String arrOfDates[]=new String[l.size()];int counter =0;
		Dnode v=l.head.getNext();
		while(counter<l.size()) {
			arrOfDates[counter]=v.getElement().getDate();
			counter++;
			v=v.getNext();
		}
		QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfDates);int i=0;

		//loop for sorting list
		v=l.head.getNext();i=arrOfDates.length-1;int x=0;counter=0;
		while(counter<l.size()) {
			if(v.getElement().getDate().contentEquals(arrOfDates[i])==false) {
				x=counter;Dnode y=v;
				while(x<l.size()) {
					if(y.getElement().getDate().contentEquals(arrOfDates[i])==false) {
						x++;y=y.getNext();
					}else {
						break;
					}
				}
				i--;counter++;
				swap(l,y,v);v=v.getNext();
			}else {
				i--;counter++;v=v.getNext();
			}
		}

		}
		
		
	}
	else if(sortAccordingTo.contentEquals("subject")) {
		
		if(l.size()>0) {
			String arrOfSubjects[]=new String[l.size()];int counter =0;
			Dnode v=l.head.getNext();
			while(counter<l.size()) {
				arrOfSubjects[counter]=v.getElement().getSubject();
				counter++;
				v=v.getNext();
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfSubjects);int i=0;
			
			//loop for sorting list
			v=l.head.getNext();i=0;int x=0;counter=0;
			while(counter<l.size()) {
				if(v.getElement().getSubject().contentEquals(arrOfSubjects[i])==false) {
					x=counter;Dnode y=v;
					while(x<l.size()) {
						if(y.getElement().getSubject().contentEquals(arrOfSubjects[i])==false) {
							x++;y=y.getNext();
						}else {
							break;
						}
					}
					i++;counter++;
					swap(l,y,v);v=v.getNext();
				}else {
					i++;counter++;v=v.getNext();
				}
			}
			
			
			}
		
	}else if(sortAccordingTo.contentEquals("sender")) {
		
		if(l.size()>0) {
			String arrOfSenders[]=new String[l.size()];int counter =0;
			
			Dnode v=l.head.getNext();
			while(counter<l.size()) {
				arrOfSenders[counter]=v.getElement().getSender();
				counter++;
				v=v.getNext();
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfSenders);int i=0;
			
			//loop for sorting list
			v=l.head.getNext();i=0;int x=0;counter=0;
			while(counter<l.size()) {
				if(v.getElement().getSender().contentEquals(arrOfSenders[i])==false) {
					x=counter;Dnode y=v;
					while(x<l.size()) {
						if(y.getElement().getSender().contentEquals(arrOfSenders[i])==false) {
							x++;y=y.getNext();
						}else {
							break;
						}
					}
					i++;counter++;
					swap(l,y,v);v=v.getNext();
				}else {
					i++;counter++;v=v.getNext();
				}
			}
			counter=0;Dnode h=l.head.getNext();
			}
		
	}else if(sortAccordingTo.contentEquals("body")) {
		
		if(l.size()>0) {
			String arrOfBodies[]=new String[l.size()];int counter =0;
			Dnode v=l.head.getNext();
			while(counter<l.size()) {
				arrOfBodies[counter]=v.getElement().getContent();
				counter++;
				v=v.getNext();
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfBodies);int i=0;
			
			//loop for sorting list
			v=l.head.getNext();i=0;int x=0;counter=0;
			while(counter<l.size()) {
				if(v.getElement().getContent().contentEquals(arrOfBodies[i])==false) {
					x=counter;Dnode y=v;
					while(x<l.size()) {
						if(y.getElement().getContent().contentEquals(arrOfBodies[i])==false) {
							x++;y=y.getNext();
						}else {
							break;
						}
					}
					i++;counter++;
					swap(l,y,v);v=v.getNext();
				}else {
					i++;counter++;v=v.getNext();
				}
			}
			counter=0;Dnode h=l.head.getNext();
			
			}
		
	}
}
public void SortAccToPriority(DLinkedList list) {
	//put elements in priority queue
	Dnode d=list.head.getNext();int key;
	int counter=0;Priority_Queue p=new Priority_Queue();
	while(counter<list.size()) {//"Urgent/Important", "Not Urgent/Important", "Urgent/Not Important", "Not Urgent/Not Important"
		if(d.getElement().getPriority().contentEquals("Urgent/Important")) {
			key=1;
		}else if(d.getElement().getPriority().contentEquals("Not Urgent/Important")) {
			key=2;
		}else if(d.getElement().getPriority().contentEquals("Urgent/Not Important")) {
			key=3;
		}else {//if(d.getElement().priority.contentEquals("Not Urgent/Not Important")) 
			key=4;
		}
		p.insert(d.getElement(), key);
		counter++;d=d.getNext();
	}
	if(p.isEmpty()==false) {
		list.clear();
		while(p.size()>0) {
		list.add((Mail)p.removeMin());
		}
	}	
	
 }
public String[] Quicksort1(String[]A) {
	StackMethods stack=new StackMethods();
	stack.push(0);
	stack.push(A.length-1);
	while(!stack.isEmpty()) {
		int end=(Integer)stack.pop();
		int start=(Integer)stack.pop();
       // if(end-start<2) {
		if(start<end) {
		
		int pindex=partitioning1(start,end,A);
        stack.push(start);
        stack.push(pindex-1);
        stack.push(pindex+1);
        stack.push(end);}
	}
	
	return A;
}
public int partitioning1(int start,int end,String[]A ) {
	String pivot=A[end];
	
	int pindex= start;
	for(int i=start;i<=end-1;i++) {
		if((A[i].compareToIgnoreCase(pivot))<=0) {
			String temp=A[pindex];
			A[pindex]=A[i];
			A[i]=temp;
			pindex++;
			////////////////////////
			
		}
	} 
	/////////////////////
	String temp=A[pindex];
	A[pindex]=A[end];
	A[end]=temp;
	//////////////////////////////////
	 
	return pindex;
	
}
public String[] Quicksort(String[]sub,String[]A) {
	StackMethods stack=new StackMethods();
	stack.push(0);
	stack.push(A.length-1);
	while(!stack.isEmpty()) {
		int end=(Integer)stack.pop();
		int start=(Integer)stack.pop();
       // if(end-start<2) {
		if(start<end) {
		
		int pindex=partitioning(start,end,A,sub);
        stack.push(start);
        stack.push(pindex-1);
        stack.push(pindex+1);
        stack.push(end);}
	}
	
	return sub;
}
public int partitioning(int start,int end,String[]A,String []sub ) {
	String pivot=A[end];
	String pivot2=sub[end];
	int pindex= start;
	for(int i=start;i<=end-1;i++) {
		if((A[i].compareToIgnoreCase(pivot))<=0) {
			String temp=A[pindex];
			A[pindex]=A[i];
			A[i]=temp;
			////////////////////////
			 temp=sub[pindex];
			sub[pindex]=sub[i];
			sub[i]=temp;
			pindex++;
		}
	} 
	/////////////////////
	String temp=A[pindex];
	A[pindex]=A[end];
	A[end]=temp;
	//////////////////////////////////
	 temp=sub[pindex];
	sub[pindex]=sub[end];
	sub[end]=temp;
	return pindex;
	
}

}
