package sortingAlgorithms;

import java.util.Comparator;

public class HeapSort {


	private static Comparator compareType;

	
	public static <T> void heapSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		compareType = comp;
        sort( array );
	}


	public static <T> void sort( Comparable<T>[] array ) 
	{
	    int length = array.length;
	
	    // Build heap
	    for (int i = length / 2 - 1; i >= 0; i--) 
	    {
	        heap(array, length, i);
	    }
	
	    // One by one extract an element from heap
	    for (int i = length - 1; i > 0; i--) 
	    {
	
	        // Move current root to end
	        Comparable<T> temp = array[0]; 
	        array[0] = array[i];
	        array[i] = temp;
	
	        // Call max heapify on the reduced heap
	        heap(array, i, 0);
	    }
	}
	
	
	// To heapify a subtree rooted with node i
	// which is an index in arr[].
	@SuppressWarnings("unchecked")
	public static <T> void heap(Comparable<T>[] array, int n, int i) 
	{
	
	    // Initialize largest as root
	    int largest = i; 
	
	    // left index
	    int l = 2 * i + 1; 
	
	    // right index
	    int r = 2 * i + 2;
	    
		if (compareType == null) 
		{
		    // If left child is larger than root
		    if (l < n && ( (Comparable<T>) array[l] ).compareTo( (T) array[largest] ) < 0 ) 
		    {
		        largest = l;
		    }
		    // If right child is larger than largest so far
		    if (r < n && ( (Comparable<T>) array[r] ).compareTo( (T) array[largest] ) < 0 ) 
		    {
		        largest = r;
		    }
		}
		else 
		{
		    // If left child is larger than root
		    if (l < n && ( compareType.compare( (T) array[l], (T) array[largest] ) < 0 ) )
		    {
		        largest = l;
		    }
		    // If right child is larger than largest so far
		    if (r < n && ( compareType.compare( (T) array[r], (T) array[largest] ) < 0 ) )
		    {
		        largest = r;
		    }
		}
	
	
	    // If largest is not root
	    if (largest != i) 
	    {
	        Comparable<T> temp = array[i];
	        array[i] = array[largest];
	        array[largest] = temp;
	
	        // Recursively heap the affected sub-tree
	        heap(array, n, largest);
	    }
	}
	
	
}