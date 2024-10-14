package shapes;

public class TriangularPrism extends Prism
{

	public TriangularPrism(double height, double side) 
	{
		super(height, side);
	}

	@Override
	public double calcBaseArea() 
	{

//		A = s²√3 / 4
		return ( Math.pow(this.getSide(), 2) * Math.sqrt(3) ) / 4;
	}
	
}
