package datastructures;

public class QuickSortByStack {
	public int partition(String arr[], int start, int end) {
	    String pivot = arr[end];
	    int partitionIndex=start;
	    int i;
	    for(i=start;i<end;i++) {
	    	if((arr[i].compareTo(pivot)==0)||(arr[i].compareTo(pivot)<0)) {//arr[i]<=pivot
	    		//swap arr[i],arr[partitionindex]//we want all the elements 
	    		//less than or equal to the pivot before partion index 
	    		String tmp=arr[partitionIndex];arr[partitionIndex]=arr[i];
	    		arr[i]=tmp;
	    		partitionIndex++;
	    	}
	    }
	    //swap arr[partitionindex],arr[end]
	    String tmp=arr[partitionIndex];arr[partitionIndex]=arr[end];
		arr[end]=tmp;
		return partitionIndex;
	}
	public void IterativeQuickSort(String[]arr) {
		if(arr.length>1) {
		StackMethods s=new StackMethods();
		s.push(0);s.push(arr.length-1);
		while(s.isEmpty()==false) {
		int  end=(Integer) s.pop();
		int start=(Integer) s.pop(); 
		if(end-start+1<2) {
			continue;
		}
		int partionIndex=partition(arr, start, end);
		s.push(partionIndex+1);s.push(end);s.push(start);s.push(partionIndex-1);
		}
		}
	}

}
