package sortingAlgorithms;

import java.util.Comparator;

public class InsertionSort 
{

	@SuppressWarnings("unchecked")
	public static <T> void insertionSort( Comparable<T>[] array)
	{
		
	    int length = array.length;
	    
	    for (int i = 1; i < length; ++i) 
	    {
	        Comparable<T> key = array[i];
	        int j = i - 1;
	
	        while ( j >= 0 && array[j].compareTo( (T) key )  == -1 ) 
	        {
	            array[j + 1] = array[j];
	            j = j - 1;
	        }
	        array[j + 1] = key;
	    }
    }
	

//	The same method except it uses compare instead of compareTo
//	Used if comparing shapes by base area or volume
	@SuppressWarnings("unchecked")
	public static <T> void insertionSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		
	    int length = array.length;

	    for ( int i = 1; i < length; ++i ) 
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
