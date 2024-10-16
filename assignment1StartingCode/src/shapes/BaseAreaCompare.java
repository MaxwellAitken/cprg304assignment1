package shapes;

import java.util.Comparator;

public class BaseAreaCompare implements Comparator<Shape>
{

	@Override
	public int compare( Shape shp1, Shape shp2 )
	{
		if( shp1.calcBaseArea() > shp2.calcBaseArea() )
		{
			return 1;
		}
		else if( shp1.calcBaseArea() < shp2.calcBaseArea() )
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

}