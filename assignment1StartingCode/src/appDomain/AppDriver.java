/*********************************************************
** Program Name:                              Shape Sorter
** Author:                                  Maxwell Aitken
** Created:                             September 18, 2024
** Description:       
**		Sorts shapes using a one of multiple sorting algorithms. 
**		Compares shapes using compareTo for height and
**		compare for base area and volume
*
**	***Comments inside of code refer to line(s) below the comment.***
*********************************************************/

package appDomain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import shapes.Shape;


public class AppDriver
{
	
//	compareType is either: h (height), v (volume), or a (base area)
	private static String compareType; 
	
//	sortType is either: b (bubble), i (insertion), s (selection), m (merge), q (quick), or z (heap)
	private static String sortType;
	
//	filePath is the file path containing the shape data to be sorted 
	private static String filePath;
	private static FileReader fileReader;
	
//	The array that will be filled with shapes from the data file
	private static Comparable[] shapeArray;

	private static Map<String, String> sortMap; 
	private static Map<String, String> compMap; 
	
	
	@SuppressWarnings("unchecked")
	public static void main( String[] args )
	{
		/*
		*   These maps will be used in the sortShapes method to invoke
		*   a sorting method using reflection.
		*/
		sortMap = new HashMap<>();
		sortMap.put("b", "BubbleSort");
		sortMap.put("i", "InsertionSort");
		sortMap.put("s", "SelectionSort");
		sortMap.put("m", "MergeSort");
		sortMap.put("q", "QuickSort");
		sortMap.put("z", "HeapSort");

		compMap = new HashMap<>();
		compMap.put("h", null);
		compMap.put("v", "VolumeCompare");
		compMap.put("a", "BaseAreaCompare");

		
//		If command line args were correctly inputed
		if (new AppDriver().parseArgs( args )) 
		{
//			Calls method to fill an array with shapes from data file.
			new AppDriver().fillShapeArray();
			
			new AppDriver().sortShapes(shapeArray);
		}
	}

	
	private Boolean parseArgs( String[] args )
	{
		System.out.println("\nTesting command line args...\n");

		for (String arg : args)
		{
//							 –   -
			arg = arg.replace("–", "-").toLowerCase();
			
			String argId = arg.substring(0, 2);
			
			if ( argId.equals("-t") )
			{
				this.compareType = arg.substring(2, arg.length()).toLowerCase();
				
			}

			if ( argId.equals("-s") )
			{
				this.sortType = arg.substring(2, arg.length()).toLowerCase();
			}

			if ( argId.equals("-f") )
			{
				filePath = arg.substring(2, arg.length()).replaceAll("\"", "").toLowerCase();

				try
				{
					fileReader = new FileReader( filePath );
				}
				catch( FileNotFoundException e )
				{
					try 
					{
						filePath += ".txt";
						fileReader = new FileReader( filePath );
					} 
					catch ( FileNotFoundException e1 ) 
					{ 
						System.out.println( "Error: invalid file path \"" + filePath + "\"" ); 
						System.out.println( "There was no file found at the specified location." ); 
						System.out.println( "Please enter the file path as follows:");
						System.out.println( "   -f\"{filename.extension}\"\n\n" );
						return false;
					}
				}
			}
		}
		
		if ( compareType == null || !compMap.containsKey(compareType) ) 
		{
			System.out.println( "Error: invalid compare type \"" + compareType + "\"" );
			System.out.println( "Please enter the compare type as follows:");
			System.out.println( "   -t{c}  where c is one of: " + compMap.keySet() + "\n\n" );
			return false;
		}

		else if ( sortType == null || !sortMap.containsKey(sortType) ) 
		{
			System.out.println( "Error: invalid sort type \"" + sortType + "\"" );
			System.out.println( "Please enter the sort type as follows:" );
			System.out.println( "   -s{x}  where x is one of: " + sortMap.keySet() + "\n\n" );
			return false;
		}
		
		else if ( fileReader == null ) return false;

		else  
		{
			System.out.println( "Testing successful.\n\n\n" );
			System.out.println( "Shapes from the file:\t\"" + filePath + "\"" );
			System.out.println( "\nWill be sorted using:\t" + sortMap.get(sortType).replace("Sort", " Sort") );
			System.out.println( "\nAnd compared using:\t" + ( compareType.equals("h") ? "height" : compMap.get(compareType).replace("Compare", "") )  + "\n\n" );
			return true;
		}
	}
	
	
	
	private void fillShapeArray() 
	{
		try
		{
			BufferedReader buff = new BufferedReader( fileReader );
			
			/*  
			*  Read the first line which gives the number of shapes in the 
			*  file and initialize the array with that length. 
			*/
			shapeArray = new Comparable[Integer.valueOf( buff.readLine() )];
			
			String line = buff.readLine();
			int i = 0;
			
			while(line != null)
			{
				StringTokenizer st = new StringTokenizer(line," ");
			
//				The first token of the line is the class name
				Class cls = Class.forName( "shapes."+st.nextToken() );
				
				/*  
				*  The next two tokens in the line correspond to the height 
				*  and another parameter of the shape. Because all shape 
				*  types in this program have two parameters that are doubles, 
				*  we can use this code sequence for all shapes. 
				*/
				Constructor ct = cls.getConstructor( Double.TYPE, Double.TYPE );
				
				Object argList[] = new Object[] { Double.valueOf(st.nextToken()), Double.valueOf(st.nextToken()) };
				
				shapeArray[i] = (Comparable) ct.newInstance(argList);
				
				i++;
				line = buff.readLine();
			}	
			buff.close();	
		}	
		
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (NoSuchMethodException e) { e.printStackTrace(); }
		catch (IllegalArgumentException e) { e.printStackTrace(); }
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (InvocationTargetException e) { e.printStackTrace(); }
	}
	

	@SuppressWarnings("unchecked")
	private <T> void sortShapes(Comparable<T>[] array) 
	{

		try 
		{
			/*
			*   Get the name of the sorting class (BubbleSort, InsertionSort, etc.)
			*   and create a class object to reference the sorting class.
			*   Get the name of the main method from that class.
			*/
			String sortClassName = "sortingAlgorithms." + sortMap.get(sortType);
			Class sortClass = Class.forName( sortClassName );

			String sortMethodName = sortClassName.toLowerCase().charAt(18) + sortClassName.substring(19);
			Method sortMethod;
			Object sortMethodParams[] = new Object[] {shapeArray};
			
			
			/*
			*   ***Comparing by height***
			*   Define the sorting method using its name and parameter types.
			*   We will pass the array of shapes as the only parameter 
			*   into the sorting method.
			*/
			if (compareType.equals("h")) 
			{
				sortMethod = sortClass.getMethod( ( sortMethodName ), Comparable[].class);
			}

			
			/*
			*   ***Comparing by base area or volume***
			*   Get the name of the compare class (BaseAreaCompare or VolumeCompare)
			*   and create a new instance of that class.
			*   We will pass the array of shapes and an instance of the compare class
			*   into the sorting method.
			*/
			else 
			{
				String compClassName = "shapes." + compMap.get(compareType);
				Object comp = Class.forName(compClassName).getConstructor().newInstance();
				sortMethodParams = new Object[] {shapeArray, comp};
				sortMethod = sortClass.getMethod( ( sortMethodName ), Comparable[].class, Comparator.class);
			}


			System.out.println("\nNow sorting...\n");

//			Start benchmarking test
			long start, stop;
			start = System.nanoTime();

//			Invoke the sorting method passing the parameters we set earlier
			sortMethod.invoke(null, sortMethodParams);
			
//			Stop benchmarking test
			stop = System.nanoTime();
//			Display the results after sorting
			System.out.println("...sorting complete!\n\n\n");
			displaySortedArray( shapeArray );
//			Display the benchmark results
			System.out.println( "\n" + sortType + " run time was: " + (( stop - start ) / 1000000) + " millisecond(s)\n");
		}
		
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (NoSuchMethodException e) { e.printStackTrace(); }
		catch (IllegalArgumentException e) { e.printStackTrace(); }
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (InvocationTargetException e) { e.printStackTrace(); } 
		catch (SecurityException e) { e.printStackTrace(); }
	}

	

	private <T> void displaySortedArray(Comparable<T>[] array) 
	{
		System.out.println( "First element is: " + ( (Shape) array[0]).toString(compareType) );
		
		for( int i = 999; i < array.length - 1; i += 1000 )
		{
			System.out.println( (i + 1) + "-th element is: " + ( (Shape) array[i]).toString(compareType) );
		}

		System.out.println( "Last element is: " + ( (Shape) array[array.length - 1]).toString(compareType) );
	}
	
}
