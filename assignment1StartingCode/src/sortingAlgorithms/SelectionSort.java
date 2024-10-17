package sortingAlgorithms;

import java.util.Comparator;

public class SelectionSort 
{

	@SuppressWarnings("unchecked")
	public static <T> void selectionSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		
		int length = array.length;

		
		if (comp == null) 
		{
		    for (int i = 0; i < length - 1; i++) 
		    {
		        int minIndex = i;
		
		        for (int j = i + 1; j < length; j++) 
		        {
		            if ( ( ( Comparable<T> ) array[j] ).compareTo((T) array[minIndex]) == 1) 
		            {
		                minIndex = j;
		            }
		        }
		
		        Comparable<T> temp = array[i];
		        array[i] = array[minIndex];
		        array[minIndex] = temp;           
		    }
		}
		
		
		else 
		{
		    for (int i = 0; i < length - 1; i++) 
		    {
		        int minIndex = i;
		
		        for (int j = i + 1; j < length; j++) 
		        {
		            if ( comp.compare((T) array[j], (T) array[minIndex] ) == 1 ) 
		            {
		                minIndex = j;
		            }
		        }
		
		        Comparable<T> temp = array[i];
		        array[i] = array[minIndex];
		        array[minIndex] = temp;           
		    }
		}
	}
}
