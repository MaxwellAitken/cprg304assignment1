package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shapes.Cone;
import shapes.Cylinder;
import shapes.OctagonalPrism;
import shapes.PentagonalPrism;
import shapes.Pyramid;
import shapes.Shape;
import shapes.SquarePrism;
import shapes.TriangularPrism;


public class AppDriver
{

	private static String compareType;
	private static String shapeFile;
	
	
	public static void main( String[] args )
	{
		
		
//		*Important*: In the command line, input the compare type first (h, v, or a), then the file path.
		new AppDriver().parseArgs( args );
		
		
		File inputFile = new File( shapeFile );
		Scanner input = null;
		try
		{
			input = new Scanner( inputFile );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
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
		if( args.length < 2 )
		{
			System.out.println( "Not enough arguments." );
			return;
		}

		this.compareType = args[0];
		this.shapeFile = args[1];
	}
	
}
