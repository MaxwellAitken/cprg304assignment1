package sortingAlgorithms;

import java.util.Comparator;

public class QuickSort 
{

	private static Comparator compareType;

	
	public static <T> void quickSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		compareType = comp;
		int length = array.length;
		int f = 0;
        sort( array, f, length - 1 );
	}


	public static <T> void sort( Comparable<T>[] array, int low, int high )
	{
		if (low < high) 
		{
			int part = partition(array, low, high);

	        sort( array, low, part - 1 );
	        sort( array, part + 1, high );
		}
	}

	
	@SuppressWarnings("unchecked")
	public static <T> int partition( Comparable<T>[] array, int low, int high )
	{

		Comparable<T> pivot = array[high];

		int i = low - 1;
		
		if (compareType == null) 
		{
			for (int j = low; j <= high - 1; j++) 
			{
				if ( ( (Comparable<T>) array[j] ).compareTo( (T) pivot )  > 0 ) 
				{
					i++;
					swap(array, i, j);
				}
			}
		}
		
		else 
		{ 
			for (int j = low; j <= high - 1; j++) 
			{
				if ( compareType.compare( (T) array[j], (T) pivot ) > 0 ) 
				{
					i++;
					swap(array, i, j);
				}
			}
		}
		
		swap(array, i + 1, high);
		return i + 1;
	}
	

	public static <T> void swap( Comparable<T>[] array, int i, int j )
	{
		Comparable<T> temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
