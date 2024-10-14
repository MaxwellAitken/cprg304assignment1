package shapes;

public class Cylinder extends Shape
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

//		V = Ah
		return this.calcBaseArea() * this.getHeight();
	}

	@Override
	public double calcBaseArea() {
		
//		A = 𝝅r²
		return Math.PI * Math.pow(this.getRadius(), 2);
	}
	
}
