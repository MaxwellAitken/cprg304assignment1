package shapes;

public class Pyramid extends Shape
{

	public double side;
	
	public double getSide() 
	{
		return side;
	}

	public void setSide(double radius) 
	{
		this.side = radius;
	}

	public Pyramid(double height, double side) 
	{
		this.height = height;
		this.side = side;
	}

	@Override
	public double calcVolume() 
	{
		
//		V = Ah / 3
		return ( this.calcBaseArea() * this.getHeight() ) / 3;
	}

	@Override
	public double calcBaseArea() 
	{
		
//		A = s²
		return Math.pow(this.getSide(), 2);
	}

}
