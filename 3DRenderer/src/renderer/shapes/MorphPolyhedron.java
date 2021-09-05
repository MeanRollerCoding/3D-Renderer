package renderer.shapes;

import java.awt.Color;

public class MorphPolyhedron extends Polyhedron
{
	public MorphPolyhedron( Color color, boolean decayColor, MorphPolygon... polygons )
	{
		super( color, decayColor, polygons );
	}

	public MorphPolyhedron( MyPolygon... polygons )
	{
		super( polygons );
	}

	public void morph( double morphSpeed )
	{
		for ( MyPolygon poly : this.polygons )
		{
			( ( MorphPolygon ) poly ).morph( morphSpeed );
		}
	}
}
