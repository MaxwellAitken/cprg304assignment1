package sortingAlgorithms;

import java.util.Comparator;

public class BubbleSort 
{

	@SuppressWarnings("unchecked")
	public static <T> void bubbleSort( Comparable<T>[] array )
	{
		int length = array.length;
		
		for ( int i = 0; i < length - 1; i++ ) 
		{
			for ( int j = 0; j < length - i - 1; j++ ) 
			{
//					If array[j] < array[j + 1]
				if ( array[j].compareTo( (T) array[j + 1] ) == -1 ) 
				{
					Comparable<T> temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	
//	The same method except it uses compare instead of compareTo
//	Used if comparing shapes by base area or volume
	@SuppressWarnings("unchecked")
	public static <T> void bubbleSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		
		int length = array.length;
		
		for ( int i = 0; i < length - 1; i++ ) 
		{
			for ( int j = 0; j < length - i - 1; j++ ) 
			{
				if ( comp.compare((T) array[j], (T) array[j + 1] ) == -1 ) 
				{
					Comparable<T> temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

}
