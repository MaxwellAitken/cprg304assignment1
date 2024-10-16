package sortingAlgorithms;


import java.util.Comparator;


public class BubbleSort 
{

	@SuppressWarnings("unchecked")
	public static <T> void bubbleSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		int length = array.length;
		

		if (comp == null) 
		{
			for (int i = 0; i < length - 1; i++) 
			{
				for (int j = 0; j < length - i - 1; j++) 
				{
					if ( ( ( Comparable<T> ) array[j] ).compareTo((T) array[j + 1]) == -1) 
					{
						Comparable<T> temp = array[j];
						array[j] = array[j + 1];
						array[j + 1] = temp;
					}
				}
			}
		}
		
		
		else 
		{
			for (int i = 0; i < length - 1; i++) 
			{
				for (int j = 0; j < length - i - 1; j++) 
				{
					if (comp.compare((T) array[j], (T) array[j + 1]) == -1) 
					{
						Comparable<T> temp = array[j];
						array[j] = array[j + 1];
						array[j + 1] = temp;
					}
				}
			}
		}
	}

}
