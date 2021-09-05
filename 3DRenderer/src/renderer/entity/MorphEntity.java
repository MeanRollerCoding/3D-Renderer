package renderer.entity;

import java.util.List;

import renderer.shapes.MorphPolyhedron;
import renderer.shapes.Polyhedron;

public class MorphEntity extends Entity
{

	public MorphEntity( List<Polyhedron> polyhedrons )
	{
		super( polyhedrons );
	}

	public void morph( double morphSpeed )
	{
		for ( Polyhedron poly : this.polyhedrons )
		{
			( ( MorphPolyhedron ) poly ).morph( morphSpeed );
		}
	}

}
