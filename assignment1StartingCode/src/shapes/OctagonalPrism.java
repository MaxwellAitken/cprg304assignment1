package shapes;

public class OctagonalPrism extends Prism
{

	@Override
	public double calcBaseArea() {
		
//		A = 2(1 + √2)s²
		return 2 * ( 1 + Math.sqrt(2) ) * Math.pow(this.getSide(), 2);
	}
	
}
