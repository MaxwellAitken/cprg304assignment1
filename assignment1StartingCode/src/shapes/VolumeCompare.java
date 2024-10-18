package shapes;

import java.util.Comparator;

public class VolumeCompare implements Comparator<Shape>
{

	@Override
	public int compare( Shape shp1, Shape shp2 )
	{
		return Double.valueOf( shp1.calcVolume() ).compareTo( shp2.calcVolume() );
	}

}
