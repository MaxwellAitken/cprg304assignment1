package shapes;

import java.util.Comparator;

public class VolumeCompare implements Comparator<Shape>
{

	@Override
	public int compare( Shape shp1, Shape shp2 )
	{
		if( shp1.calcVolume() > shp2.calcVolume() )
		{
			return 1;
		}
		else if( shp1.calcVolume() < shp2.calcVolume() )
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

}
