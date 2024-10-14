package shapes;

public class SquarePrism extends Prism
{

	@Override
	public double calcBaseArea() {

//		A = s²
		return Math.pow(this.getSide(), 2);
	}
	
}
