package shapes;

public class Cone extends Shape
{

	public double radius;
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	

	@Override
	public double calcVolume() {
		
//		V = Ah / 3
		return ( this.calcBaseArea() * this.getHeight() ) / 3;
	}

	@Override
	public double calcBaseArea() {
		
//		A = 𝝅r²
		return Math.PI * Math.pow(this.getRadius(), 2);
	}

}
