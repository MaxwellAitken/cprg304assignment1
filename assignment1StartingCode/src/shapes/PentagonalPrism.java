package shapes;

public class PentagonalPrism extends Prism
{

	public PentagonalPrism(double height, double side) 
	{
		super(height, side);
	}

	@Override
	public double calcBaseArea() 
	{

//		A = 5s²tan(54°) / 4
		return ( 5 * Math.pow(this.getSide(), 2) * Math.tan(Math.toRadians(54)) ) / 4;
	}
	
}
