package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.HeapSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.MergeSort;
import sortingAlgorithms.QuickSort;
import sortingAlgorithms.SelectionSort;
import shapes.Cone;
import shapes.Cylinder;
import shapes.OctagonalPrism;
import shapes.PentagonalPrism;
import shapes.Pyramid;
import shapes.Shape;
import shapes.SquarePrism;
import shapes.TriangularPrism;
import shapes.VolumeCompare;
import shapes.BaseAreaCompare;


public class AppDriver
{

	private static String compareType;
	private static String sortType;
	private static String shapeFile;
	
	
	public static void main( String[] args )
	{


		new AppDriver().parseArgs( args );
		
		
		Scanner input = null;
		
		try
		{
			input = new Scanner( new File( shapeFile ) );
		}
		catch( FileNotFoundException e )
		{
			try 
			{
//				Tries finding the file by taking only the "shapesX.txt" part of the string
//				and adding res\ to the start. The result should be: "res\shapesX.txt"
				int f = shapeFile.indexOf("shapes");
				input = new Scanner( new File( "res\\" + shapeFile.substring(f, f + 11)  ) );
			} 
			catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			}
		}

		
		
		
//		Array of shapes with length corresponding to the first line of the file
		Shape[] shapes = new Shape[ Integer.valueOf( input.nextLine() ) ];

		
		for ( int i = 0; i < shapes.length; i++ ) 
		{
			
//			Splits a line of the file at each space into an array of strings
			String[] shapeData = input.nextLine().split(" ");
			
			
//			All shapes have height, and height is always the first number of each line
			double height = Double.valueOf(shapeData[1]);
			
			
			switch( shapeData[0].substring(0, 2) ) 
			{
//				Each shape can be identified by the first two characters,
//				so there is no need to check the whole string
				case "Cy": 
//					Identify the type of shape and add a new instance of that shape to the array
					shapes[i] = new Cylinder(height, Double.valueOf(shapeData[2]));
					break;
				case "Co": 
					shapes[i] = new Cone(height, Double.valueOf(shapeData[2]));
					break;
				case "Py": 
					shapes[i] = new Pyramid(height, Double.valueOf(shapeData[2]));
					break;
				case "Sq": 
					shapes[i] = new SquarePrism(height, Double.valueOf(shapeData[2]));
					break;
				case "Tr": 
					shapes[i] = new TriangularPrism(height, Double.valueOf(shapeData[2]));
					break;
				case "Pe": 
					shapes[i] = new PentagonalPrism(height, Double.valueOf(shapeData[2]));
					break;
				case "Oc": 
					shapes[i] = new OctagonalPrism(height, Double.valueOf(shapeData[2]));
					break;
				default:
					System.out.println( "Invalid format found in text file." );
			}
		}
		

		Comparator<Shape> comp = null;
		
		if (compareType.equals("v")) 
		{
			comp = new VolumeCompare();
		}
		
		else if (compareType.equals("a")) 
		{
			comp = new BaseAreaCompare();
		}



//		Start benchmarking test
		long start, stop;
		start = System.nanoTime();
		
		
//		Bubble Sort TEST 
		if (sortType.equals("b")) 
		{
//			perform the bubble sort, passing the shape array and the compare type
			BubbleSort.bubbleSort(shapes, comp);
		}


//		Insertion Sort TEST 
		else if (sortType.equals("i")) 
		{
			InsertionSort.insertionSort(shapes, comp);
		}


//		Selection Sort TEST 
		else if (sortType.equals("s")) 
		{
			SelectionSort.selectionSort(shapes, comp);
		}


//		Merge Sort TEST 
		else if (sortType.equals("m")) 
		{
			MergeSort.mergeSort(shapes, comp);
		}


//		Quick Sort TEST 
		else if (sortType.equals("q")) 
		{
			QuickSort.quickSort(shapes, comp);
		}


//		Heap Sort TEST 
		else if (sortType.equals("z")) 
		{
			HeapSort.heapSort(shapes, comp);
		}

		

//		Stop benchmarking test
		stop = System.nanoTime();
		new AppDriver().displayResults( shapes );
		System.out.println( "\n" + sortType + " run time was: " + (( stop - start ) / 1000000) + " millisecond(s)");
		
		
		
		// refer to demo01 Test.java for an example on how to parse command
		// line arguments and benchmarking tests

		// refer to demo02 Student.java for comparable implementation, and
		// NameCompare.java or GradeCompare for comparator implementations

		// refer to demo02 KittySort.java on how to use a custom sorting
		// algorithm on a list of comparables to sort using either the
		// natural order (comparable) or other orders (comparators)

		// refer to demo03 OfficeManager.java on how to create specific
		// objects using reflection from a String
		input.close();
	}

	private void parseArgs( String[] args )
	{
		if( args.length < 3 )
		{
			System.out.println( "Not enough arguments." );
			return;
		}
		
		for (String arg : args) 
		{
//			Each one of these if statements has two options because
//			there are two different types of valid dashes. 
//			These are not the same: – -  
			
			if (arg.substring(0, 2).equalsIgnoreCase("-t") || 
				arg.substring(0, 2).equalsIgnoreCase("–t"))
			{
				this.compareType = arg.substring(2, arg.length()).toLowerCase();
			}
			
			else if (arg.substring(0, 2).equalsIgnoreCase("-s") || 
					 arg.substring(0, 2).equalsIgnoreCase("–s")) 
			{
				this.sortType = arg.substring(2, arg.length()).toLowerCase();
			}
			
			else if (arg.substring(0, 2).equalsIgnoreCase("-f") || 
					 arg.substring(0, 2).equalsIgnoreCase("–f")) 
			{
//				Make the file path lower case and remove any quotations
//				in case the user inputed it incorrectly.
				this.shapeFile = arg.substring(2, arg.length()).replaceAll("\"", "").toLowerCase();
			}
		}
	}
	
	private void displayResults(Shape[] array) 
	{
		
//		Display the first element of the sorted array
		System.out.println( "First element is: " + array[0].toString(compareType)  );
		
//		Display every thousandth element of the array
		for( int i = 999; i < array.length - 1; i += 1000 )
		{
			System.out.println( (i + 1) + "-th element is: " + array[i].toString(compareType) );
		}
		
//		Display the last element of the array
		System.out.println( "Last element is: " + array[array.length - 1].toString(compareType)  );
	}
	
}
