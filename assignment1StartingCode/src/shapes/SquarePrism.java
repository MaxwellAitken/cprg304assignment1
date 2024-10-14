package shapes;

public class SquarePrism extends Prism
{

	public SquarePrism(double height, double side) 
	{
		super(height, side);
	}

	@Override
	public double calcBaseArea() 
	{

//		A = s²
		return Math.pow(this.getSide(), 2);
	}
	
}
