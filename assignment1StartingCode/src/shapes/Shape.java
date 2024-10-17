package shapes;

public abstract class Shape implements Comparable<Shape> 
{

	public double height;

	public double getHeight() 
	{
		return height;
	}

	public void setHeight(double height) 
	{
		this.height = height;
	}

	
	public abstract double calcVolume();
	
	public abstract double calcBaseArea();
	
	
	@Override
	public int compareTo(Shape s) 
	{
		if ( this.getHeight() > s.getHeight() ) return 1;
		else if ( s.getHeight() > this.getHeight() ) return -1;
		else return 0;
	}
	
//	public int compare(Shape s, String compareType)
//	{
////		Comparing two shapes by volume
//		if (compareType.equals("v")) 
//		{
//			if ( this.calcVolume() > s.calcVolume() ) return 1;
//			else if ( this.calcVolume() < s.calcVolume() ) return -1;
//			else return 0;
//		}
////		Comparing two shapes by base area
//		else if (compareType.equals("a")) 
//		{
//			if ( this.calcBaseArea() > s.calcBaseArea() ) return 1;
//			else if ( this.calcBaseArea() < s.calcBaseArea() ) return -1;
//			else return 0;
//		}
//		else return 0;
//	}
	
	
	public String toString(String compareType) 
	{
		String customString;
		if (compareType.equals("h")) 
		{
			customString = "Height of: " + this.getHeight();
		}
		else if (compareType.equals("v")) {
			customString = "Volume of: " + this.calcVolume();
		}
		else {
			customString = "Base Area of: " + this.calcBaseArea();
		}
		
		return ( "The" + this.getClass() + " has a " + customString ).replace("class", "");
	}
}


