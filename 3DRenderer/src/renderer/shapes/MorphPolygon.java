package renderer.shapes;

import java.awt.Color;

import renderer.point.MorphPoint;
import renderer.point.MyPoint;

public class MorphPolygon extends MyPolygon
{
	public MorphPolygon( Color color, MorphPoint... points )
	{
		super( color, points );
	}

	public MorphPolygon( MorphPoint... points )
	{
		super( points );
	}

	public void morph( double morphSpeed )
	{
		for ( MyPoint p : this.points )
		{
			( ( MorphPoint ) p ).morph( morphSpeed );
		}
	}

	@Override
	protected void createPointsArray( MyPoint[ ] points )
	{
		this.points = new MyPoint[ points.length ];
		for ( int i = 0; i < points.length; i++ )
		{
			MorphPoint p = ( MorphPoint ) points[ i ];
			this.points[ i ] = new MorphPoint( p.p1.x, p.p1.y, p.p1.z, p.p2.x, p.p2.y, p.p2.z );
		}
	}
}
