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

		sortMap = new HashMap<>();
		sortMap.put("b", "BubbleSort");
		sortMap.put("i", "InsertionSort");
		sortMap.put("s", "SelectionSort");
		sortMap.put("m", "MergeSort");
		sortMap.put("q", "QuickSort");
		sortMap.put("z", "HashSort");

		compMap = new HashMap<>();
		compMap.put("h", null);
		compMap.put("v", "VolumeCompare");
		compMap.put("a", "BaseAreaCompare");

		new AppDriver().parseArgs( args );
		
		
		if (compareType != null && sortType != null && fileReader != null) 
		{
//			Calls method to fill an array with shapes from data file.
			new AppDriver().fillShapeArray();
			
			new AppDriver().sortShapes(shapeArray);
		}
		

		
//		The compare type will be null when comparing shapes by height.
//		This will tell each sorting algorithm to use the compareTo method.
//		Comparator<?> comp = null;
//		if (compareType.equals("v")) 
//		{
//			comp = new VolumeCompare();
//		}
//		else if (compareType.equals("a")) 
//		{
//			comp = new BaseAreaCompare();
//		}
////		Start benchmarking test
//		long start, stop;
//		start = System.nanoTime();
//		if (sortType.equals("b")) 
//		{
//			BubbleSort.bubbleSort(shapeArray, comp);
//		}
//		else if (sortType.equals("i")) 
//		{
//			InsertionSort.insertionSort(shapeArray, comp);
//		}
//		else if (sortType.equals("s")) 
//		{
//			SelectionSort.selectionSort(shapeArray, comp);
//		}
//		else if (sortType.equals("m")) 
//		{
//			MergeSort.mergeSort(shapeArray, comp);
//		}
//		else if (sortType.equals("q")) 
//		{
//			QuickSort.quickSort(shapeArray, comp);
//		}
//		else if (sortType.equals("z")) 
//		{
//			HeapSort.heapSort(shapeArray, comp);
//		}
////		Stop benchmarking test
//		stop = System.nanoTime();
//		new AppDriver().displaySortedArray( shapeArray );
//		System.out.println( "\n" + sortType + " run time was: " + (( stop - start ) / 1000000) + " millisecond(s)");
		
		
		
		// refer to demo01 Test.java for an example on how to parse command
		// line arguments and benchmarking tests

		// refer to demo02 Student.java for comparable implementation, and
		// NameCompare.java or GradeCompare for comparator implementations

		// refer to demo02 KittySort.java on how to use a custom sorting
		// algorithm on a list of comparables to sort using either the
		// natural order (comparable) or other orders (comparators)

		// refer to demo03 OfficeManager.java on how to create specific
		// objects using reflection from a String
	}

	
	private void parseArgs( String[] args )
	{
		System.out.println("Testing command line args...\n");
		
		for ( String arg : args ) 
		{
			String identifier = arg.substring(0, 2);
//			Each if statement has two options because there are two types of valid dashes. 
//			These are not the same: – -  
			if ( identifier.equalsIgnoreCase("-t") || identifier.equalsIgnoreCase("–t") )
			{
				this.compareType = arg.substring(2, arg.length()).toLowerCase();
			}
			
			else if ( identifier.equalsIgnoreCase("-s") || identifier.equalsIgnoreCase("–s") ) 
			{
				this.sortType = arg.substring(2, arg.length()).toLowerCase();
			}
			
			else if ( identifier.equalsIgnoreCase("-f") || identifier.equalsIgnoreCase("–f") ) 
			{
//				Make the file path lower case and remove any quotations
//				in case the user inputed it incorrectly.
				filePath = arg.substring(2, arg.length()).replaceAll("\"", "").toLowerCase();

				try
				{
					fileReader = new FileReader( filePath );
				}
				catch( FileNotFoundException e )
				{
					try 
					{
//						Tries finding the file by taking only the "shapesX.txt" part of the string
//						and adding res\ to the start. The result should be: "res\shapesX.txt"
						int f = filePath.indexOf("shapes");
						fileReader = new FileReader( "res\\" + filePath.substring(f, f + 11) );
					} 
					catch ( FileNotFoundException e1 ) 
					{ 
						System.out.println("Error: invalid file path"); 
						System.out.println("There was no file found at the specified location."); 
					}
				}
			}
		}

		assignArgs();
	}
	
	private void assignArgs() 
	{
		Boolean invalidComp = true;
		if (compMap.containsKey(compareType)) invalidComp = false;
		
		Boolean invalidSort = true;
		if (sortMap.containsKey(sortType)) invalidSort = false;
		
		
		if (compareType == null || invalidComp) 
		{
			System.out.println("Error: invalid compare type");
			System.out.println("Please enter the compare type as follows:");
			System.out.println("   -t{c}  where c is one of:" + compMap.keySet() + "\n\n");
		}
		
		else if (sortType == null || invalidSort) 
		{
			System.out.println("Error: invalid sort type");
			System.out.println("Please enter the sort type as follows:\n");
			System.out.println("   -t{s}  where s is one of:" + sortMap.keySet() + "\n\n");
		}
		
		else if (fileReader == null) ;
		
		else  
		{
			System.out.println("Testing successful.\n");
			System.out.println("Shapes from the file: \"" + filePath + "\"");
			System.out.println("will be sorted using " + sortMap.get(sortType));
			System.out.println("and compared using " + compMap.get(compareType) + ".\n\n");
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
			String line = buff.readLine();
	
			shapeArray = new Comparable[Integer.valueOf(line)];
			
			line = buff.readLine();
			int i = 0;
			
			while(line != null)
			{
				Comparable o = null;
				StringTokenizer st = new StringTokenizer(line," ");
			
//				The first token of the line is the class name
				String className = "shapes."+st.nextToken();
				Class cls = Class.forName(className);
				
				/*  
				*  The next two tokens in the line correspond to the height 
				*  and another parameter of the shape. Because all shape 
				*  types in this program have two parameters that are doubles, 
				*  we can use  this code sequence for all shapes. 
				*/
				Class paramTypes[] = new Class[2];
				paramTypes[0] = Double.TYPE;
				paramTypes[1] = Double.TYPE;
				
				Constructor ct = cls.getConstructor(paramTypes);
				
				Object argList[] = new Object[2];
				argList[0] = Double.valueOf(st.nextToken());
				argList[1] = Double.valueOf(st.nextToken());
				
				o = (Comparable) ct.newInstance(argList);
				shapeArray[i] = o;
				
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
			
			Object sort = null;

			String sortClassName = "sortingAlgorithms." + sortMap.get(sortType);
			
			Class sortClass = Class.forName(sortClassName);

			Method sortMethod = sortClass.getMethod( (sortType + sortClassName.substring(19) ), Comparable[].class, Comparator.class);


			Object comp = null;
			
			if (!compareType.equals("h")) 
			{
				String compClassName = "shapes." + compMap.get(compareType);
				
				Class compClass = Class.forName(compClassName);
				
				comp = compClass.getConstructor(null).newInstance(null);
			}
			
			
			


//			Start benchmarking test
			long start, stop;
			start = System.nanoTime();

//			Invoke the sorting method
			sortMethod.invoke(sort, shapeArray, comp);
			
			
//			Stop benchmarking test
			stop = System.nanoTime();
			displaySortedArray( shapeArray );
			System.out.println( "\n" + sortType + " run time was: " + (( stop - start ) / 1000000) + " millisecond(s)");
			
		}
		
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (NoSuchMethodException e) { e.printStackTrace(); }
		catch (IllegalArgumentException e) { e.printStackTrace(); }
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (InvocationTargetException e) { e.printStackTrace(); }
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
