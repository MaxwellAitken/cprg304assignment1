package sortingAlgorithms;

import java.util.Comparator;

public class HeapSort {

	private static Comparator compareType;


	public static <T> void heapSort( Comparable<T>[] array)
	{
		compareType = null;
        sort( array );
	}

	public static <T> void heapSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		compareType = comp;
        sort( array );
	}


	public static <T> void sort( Comparable<T>[] array ) 
	{
	    int length = array.length;
	
	    for ( int i = length / 2 - 1; i >= 0; i-- ) 
	    {
	        heap( array, length, i );
	    }
	
	    for ( int i = length - 1; i > 0; i-- ) 
	    {
	
	        Comparable<T> temp = array[0]; 
	        array[0] = array[i];
	        array[i] = temp;
	        heap( array, i, 0 );
	    }
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> void heap( Comparable<T>[] array, int n, int i ) 
	{
	
	    int largest = i; 
	
	    int l = 2 * i + 1; 
	
	    int r = 2 * i + 2;
	    
		if ( compareType == null ) 
		{
		    if ( l < n && array[l].compareTo( (T) array[largest] ) == -1 ) 
		    {
		        largest = l;
		    }
		    if ( r < n && array[r].compareTo( (T) array[largest] ) == -1 ) 
		    {
		        largest = r;
		    }
		}
		else 
		{
		    if ( l < n && ( compareType.compare( array[l], array[largest] ) == -1 ) )
		    {
		        largest = l;
		    }
		    if ( r < n && ( compareType.compare( array[r], array[largest] ) == -1 ) )
		    {
		        largest = r;
		    }
		}
	
	
	    if ( largest != i ) 
	    {
	        Comparable<T> temp = array[i];
	        array[i] = array[largest];
	        array[largest] = temp;
	        heap( array, n, largest );
	    }
	}
	
}