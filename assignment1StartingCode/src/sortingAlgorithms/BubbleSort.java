package sortingAlgorithms;

import shapes.Shape;

public class BubbleSort 
{

	
	@SuppressWarnings("unchecked")
	public <T> void bubbleSort( Comparable[] array, String compareType ) 
	{
		int length = array.length;
		
		if ( compareType.equals( "h" ) ) 
		{
			for (int i = 0; i < length - 1; i++) 
			{
				for (int j = 0; j < length - i - 1; j++) 
				{
					if (array[j].compareTo(array[j + 1]) == -1) 
					{
						Comparable temp = array[j];
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
					if (((Shape) array[j]).compare((Shape) array[j + 1], compareType) == -1) 
					{
						Comparable temp = array[j];
						array[j] = array[j + 1];
						array[j + 1] = temp;
					}
				}
			}
		}
	}
}
