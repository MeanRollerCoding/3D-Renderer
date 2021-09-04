package renderer.entity.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.point.MyPoint;
import renderer.shapes.MyPolygon;
import renderer.shapes.Polyhedron;

public class ComplexEntityBuilder
{

	public static IEntity createRubiksCube( double size, double centerX, double centerY, double centerZ )
	{
		List<Polyhedron> tetras = new ArrayList<Polyhedron>( );

		double cubeSpacing = 10;

		for ( int i = -1; i < 2; i++ )
		{
			double cubeCenterX = i * ( size + cubeSpacing ) + centerX;
			for ( int j = -1; j < 2; j++ )
			{
				double cubeCenterY = j * ( size + cubeSpacing ) + centerY;
				for ( int k = -1; k < 2; k++ )
				{
					if ( i == 0 && j == 0 && k == 0 )
						continue;
					double cubeCenterZ = k * ( size + cubeSpacing ) + centerZ;

					MyPoint p1 = new MyPoint( cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2 );
					MyPoint p2 = new MyPoint( cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2 );
					MyPoint p3 = new MyPoint( cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2 );
					MyPoint p4 = new MyPoint( cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2 );
					MyPoint p5 = new MyPoint( cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2 );
					MyPoint p6 = new MyPoint( cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2 );
					MyPoint p7 = new MyPoint( cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2 );
					MyPoint p8 = new MyPoint( cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2 );

					MyPolygon polyRed = new MyPolygon( Color.RED, p5, p6, p8, p7 );
					MyPolygon polyWhite = new MyPolygon( Color.WHITE, p2, p4, p8, p6 );
					MyPolygon polyBlue = new MyPolygon( Color.BLUE, p3, p7, p8, p4 );
					MyPolygon polyGreen = new MyPolygon( Color.GREEN, p1, p2, p6, p5 );
					MyPolygon polyOrange = new MyPolygon( new Color( 255, 120, 30 ), p1, p3, p4, p2 );
					MyPolygon polyYellow = new MyPolygon( Color.YELLOW, p1, p5, p7, p3 );

					Polyhedron tetra = new Polyhedron( polyRed, polyWhite, polyBlue, polyGreen, polyOrange, polyYellow );
					tetras.add( tetra );
				}
			}
		}

		return new Entity( tetras );
	}

}
