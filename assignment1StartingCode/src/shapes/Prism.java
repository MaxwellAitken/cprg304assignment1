package shapes;

public abstract class Prism extends Shape 
{

	public double side;

	public double getSide() 
	{
		return side;
	}

	public void setSide(double side) 
	{
		this.side = side;
	}

	public Prism(double height, double side) 
	{
		this.height = height;
		this.side = side;
	}

	@Override
	public double calcVolume() 
	{
		
//		Volume of all prisms is base area * height, so this method can be defined here, in the parent class.
//		V = Ah
		return this.calcBaseArea() * this.getHeight();
	}
	
}
