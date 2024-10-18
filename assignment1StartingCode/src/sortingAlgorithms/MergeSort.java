package sortingAlgorithms;

import java.util.Comparator;

public class MergeSort 
{

	private static Comparator compareType;


	public static <T> void mergeSort( Comparable<T>[] array)
	{
		compareType = null;
		int length = array.length;
		int f = 0;
        sort( array, f, length - 1 );
	}

	public static <T> void mergeSort( Comparable<T>[] array, Comparator<? super T> comp )
	{
		compareType = comp;
		int length = array.length;
		int f = 0;
        sort( array, f, length - 1 );
	}

	
	public static <T> void sort( Comparable<T>[] array, int f, int n )
	{
//		f: first index, m: midpoint, n: last index

		if (f < n) 
		{
			int m = f + ( n - f ) / 2 ;

//	 		Sort the first and second halves
	        sort( array, f, m );
	        sort( array, m + 1, n );

//	 		Merge the two halves
	        merge( array, f, m, n );
		}
	}

	
	@SuppressWarnings("unchecked")
	public static <T> void merge( Comparable<T>[] array, int f, int m, int n )
	{
		
//		f: start index, m: midpoint, n: last index

//		a1: subarray at array[f..m]
//		a2: subarray at array[m+1..n]
        int a1 = m - f + 1;
        int a2 = n - m;

//		Create the two subarrays
        Comparable<T>[] L = new Comparable[a1];
        Comparable<T>[] R = new Comparable[a2];

// 		Copy data from original array into subarrays
        for (int i = 0; i < a1; ++i) 
        {
            L[i] = array[f + i];
        }
        for (int j = 0; j < a2; ++j) 
        {
            R[j] = array[m + 1 + j];
        }

        
        int i = 0;
        int j = 0;
        int k = f;


		if (compareType == null) 
		{
	        while (i < a1 && j < a2) 
	        {
	            if ( L[i].compareTo( (T) R[j] )  >= 0 )
	            {
	                array[k] = L[i];
	                i++;
	            }
	            else 
	            {
	                array[k] = R[j];
	                j++;
	            }
	            k++;
	        }
		}
		
		else 
		{
	        while (i < a1 && j < a2) 
	        {
	            if ( compareType.compare( (T) L[i], (T) R[j] ) >= 0 ) 
	            {
	                array[k] = L[i];
	                i++;
	            }
	            else 
	            {
	                array[k] = R[j];
	                j++;
	            }
	            k++;
	        }
		}
        
        while (i < a1) 
        {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < a2) 
        {
            array[k] = R[j];
            j++;
            k++;
        } 
	}

}
