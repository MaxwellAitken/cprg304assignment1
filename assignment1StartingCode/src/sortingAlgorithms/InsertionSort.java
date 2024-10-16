package sortingAlgorithms;

import java.util.Comparator;


public class InsertionSort 
{

	@SuppressWarnings("unchecked")
	public static <T> void insertionSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
	    int length = array.length;
	    
	    
	    if (comp == null) 
	    {
		    for (int i = 1; i < length; ++i) 
		    {
		        Comparable<T> key = array[i];
		        int j = i - 1;
		
		        while ( j >= 0 && ( (Comparable<T>) array[j] ).compareTo( (T) key )  == -1 ) 
		        {
		            array[j + 1] = array[j];
		            j = j - 1;
		        }
		        array[j + 1] = key;
		    }
	    }
	    
	    
	    else 
	    {
		    for (int i = 1; i < length; ++i) 
		    {
		        Comparable<T> key = array[i];
		        int j = i - 1;
		
		        while (j >= 0 && ( comp.compare( (T) array[j], (T) key ) == -1 ) ) 
		        {
		            array[j + 1] = array[j];
		            j = j - 1;
		        }
		        array[j + 1] = key;
		    }
	    }
    }
}
