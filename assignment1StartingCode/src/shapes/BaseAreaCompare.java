package shapes;

import java.util.Comparator;

public class BaseAreaCompare implements Comparator<Shape>
{

	@Override
	public int compare( Shape shp1, Shape shp2 )
	{
		return Double.valueOf( shp1.calcBaseArea() ).compareTo( shp2.calcBaseArea() );
	}

}
