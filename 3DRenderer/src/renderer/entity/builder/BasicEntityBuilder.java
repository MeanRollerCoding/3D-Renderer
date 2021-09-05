package renderer.entity.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.entity.MorphEntity;
import renderer.point.MorphPoint;
import renderer.point.MyPoint;
import renderer.shapes.MorphPolygon;
import renderer.shapes.MorphPolyhedron;
import renderer.shapes.MyPolygon;
import renderer.shapes.Polyhedron;

public class BasicEntityBuilder
{
	public static IEntity createCube( double size, double centerX, double centerY, double centerZ )
	{
		MyPoint p1 = new MyPoint( centerX + size / 2, centerY + -size / 2, centerZ + -size / 2 );
		MyPoint p2 = new MyPoint( centerX + size / 2, centerY + size / 2, centerZ + -size / 2 );
		MyPoint p3 = new MyPoint( centerX + size / 2, centerY + size / 2, centerZ + size / 2 );
		MyPoint p4 = new MyPoint( centerX + size / 2, centerY + -size / 2, centerZ + size / 2 );
		MyPoint p5 = new MyPoint( centerX + -size / 2, centerY + -size / 2, centerZ + -size / 2 );
		MyPoint p6 = new MyPoint( centerX + -size / 2, centerY + size / 2, centerZ + -size / 2 );
		MyPoint p7 = new MyPoint( centerX + -size / 2, centerY + size / 2, centerZ + size / 2 );
		MyPoint p8 = new MyPoint( centerX + -size / 2, centerY + -size / 2, centerZ + size / 2 );

		Polyhedron tetra = new Polyhedron( new MyPolygon( Color.BLUE, p5, p6, p7, p8 ), new MyPolygon( Color.WHITE, p1, p2, p6, p5 ), new MyPolygon( Color.YELLOW, p1, p5, p8, p4 ),
				new MyPolygon( Color.GREEN, p2, p6, p7, p3 ), new MyPolygon( Color.ORANGE, p4, p3, p7, p8 ), new MyPolygon( Color.RED, p1, p2, p3, p4 ) );

		List<Polyhedron> tetras = new ArrayList<Polyhedron>( );
		tetras.add( tetra );

		return new Entity( tetras );
	}

	public static IEntity createMorphCube( double size, Color color, double centerX, double centerY, double centerZ )
	{
		MorphPoint p1 = new MorphPoint( centerX + size / 2, centerY + -size / 2, centerZ + -size / 2, centerX + size / 4, centerY + -size / 4, centerZ + -size / 4 );
		MorphPoint p2 = new MorphPoint( centerX + size / 2, centerY + size / 2, centerZ + -size / 2, centerX + size / 2, centerY + size / 2, centerZ + -size / 1 );
		MorphPoint p3 = new MorphPoint( centerX + size / 2, centerY + size / 2, centerZ + size / 2, centerX + size / 2, centerY + size / 2, centerZ + size / 1 );
		MorphPoint p4 = new MorphPoint( centerX + size / 2, centerY + -size / 2, centerZ + size / 2, centerX + size / 2, centerY + -size / 2, centerZ + size / 4 );
		MorphPoint p5 = new MorphPoint( centerX + -size / 2, centerY + -size / 2, centerZ + -size / 2, centerX + -size / 2, centerY + -size / 2, centerZ + -size / 1 );
		MorphPoint p6 = new MorphPoint( centerX + -size / 2, centerY + size / 2, centerZ + -size / 2, centerX + -size / 2, centerY + size / 2, centerZ + -size / 4 );
		MorphPoint p7 = new MorphPoint( centerX + -size / 2, centerY + size / 2, centerZ + size / 2, centerX + -size / 2, centerY + size / 2, centerZ + size / 4 );
		MorphPoint p8 = new MorphPoint( centerX + -size / 2, centerY + -size / 2, centerZ + size / 2, centerX + -size / 2, centerY + -size / 2, centerZ + size / 1 );

		Polyhedron tetra = new MorphPolyhedron( new MorphPolygon( color, p5, p6, p7, p8 ), new MorphPolygon( color, p1, p2, p6, p5 ), new MorphPolygon( color, p1, p5, p8, p4 ),
				new MorphPolygon( color, p2, p3, p7, p6 ), new MorphPolygon( color, p4, p8, p7, p3 ), new MorphPolygon( color, p1, p4, p3, p2 ) );

		List<Polyhedron> tetras = new ArrayList<Polyhedron>( );
		tetras.add( tetra );

		return new MorphEntity( tetras );
	}

	public static IEntity createDiamond( Color color, double size, double centerX, double centerY, double centerZ )
	{
		List<Polyhedron> tetras = new ArrayList<Polyhedron>( );

		int edges = 20;
		double inFactor = 0.8;
		MyPoint bottom = new MyPoint( centerX, centerY, centerZ - size / 2 );
		MyPoint[ ] outerPoints = new MyPoint[ edges ];
		MyPoint[ ] innerPoints = new MyPoint[ edges ];
		for ( int i = 0; i < edges; i++ )
		{
			double theta = 2 * Math.PI / edges * i;
			double xPos = -Math.sin( theta ) * size / 2;
			double yPos = Math.cos( theta ) * size / 2;
			double zPos = size / 2;
			outerPoints[ i ] = new MyPoint( centerX + xPos, centerY + yPos, centerZ + zPos * inFactor );
			innerPoints[ i ] = new MyPoint( centerX + xPos * inFactor, centerY + yPos * inFactor, centerZ + zPos );
		}

		MyPolygon polygons[] = new MyPolygon[ 2 * edges + 1 ];
		for ( int i = 0; i < edges; i++ )
		{
			polygons[ i ] = new MyPolygon( outerPoints[ i ], bottom, outerPoints[ ( i + 1 ) % edges ] );
		}
		for ( int i = 0; i < edges; i++ )
		{
			polygons[ i + edges ] = new MyPolygon( outerPoints[ i ], outerPoints[ ( i + 1 ) % edges ], innerPoints[ ( i + 1 ) % edges ], innerPoints[ i ] );
		}
		polygons[ edges * 2 ] = new MyPolygon( innerPoints );

		Polyhedron tetra = new Polyhedron( color, false, polygons );
		tetras.add( tetra );

		return new Entity( tetras );
	}

	public static IEntity createSphere( Color color, double size, int resolution, double centerX, double centerY, double centerZ )
	{
		List<Polyhedron> polyhedrons = new ArrayList<Polyhedron>( );
		List<MyPolygon> polygons = new ArrayList<MyPolygon>( );

		MyPoint bottom = new MyPoint( centerX, centerY, centerZ - size / 2 );
		MyPoint top = new MyPoint( centerX, centerY, centerZ + size / 2 );

		MyPoint[ ][ ] points = new MyPoint[ resolution - 1 ][ resolution ];

		for ( int i = 1; i < resolution; i++ )
		{
			double theta = Math.PI / resolution * i;
			double zPos = -Math.cos( theta ) * size / 2;
			double currentRadius = Math.abs( Math.sin( theta ) * size / 2 );
			for ( int j = 0; j < resolution; j++ )
			{
				double alpha = 2 * Math.PI / resolution * j;
				double xPos = -Math.sin( alpha ) * currentRadius;
				double yPos = Math.cos( alpha ) * currentRadius;
				points[ i - 1 ][ j ] = new MyPoint( centerX + xPos, centerY + yPos, centerZ + zPos );
			}
		}

		for ( int i = 1; i <= resolution; i++ )
		{
			for ( int j = 0; j < resolution; j++ )
			{
				if ( i == 1 )
				{
					polygons.add( new MyPolygon( points[ i - 1 ][ j ], points[ i - 1 ][ ( j + 1 ) % resolution ], bottom ) );
				}
				else if ( i == resolution )
				{
					polygons.add( new MyPolygon( points[ i - 2 ][ ( j + 1 ) % resolution ], points[ i - 2 ][ j ], top ) );
				}
				else
				{
					polygons.add( new MyPolygon( points[ i - 1 ][ j ], points[ i - 1 ][ ( j + 1 ) % resolution ], points[ i - 2 ][ ( j + 1 ) % resolution ], points[ i - 2 ][ j ] ) );
				}
			}
		}

		MyPolygon[ ] polygonArray = new MyPolygon[ polygons.size( ) ];
		polygonArray = polygons.toArray( polygonArray );

		Polyhedron polyhedron = new Polyhedron( color, false, polygonArray );
		polyhedrons.add( polyhedron );

		return new Entity( polyhedrons );
	}

}
