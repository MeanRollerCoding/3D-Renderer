package renderer.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import renderer.entity.builder.BasicEntityBuilder;
import renderer.input.ClickType;
import renderer.input.Keyboard;
import renderer.input.Mouse;
import renderer.input.UserInput;
import renderer.point.MyVector;
import renderer.point.PointConverter;
import renderer.world.Camera;

public class EntityManager
{

	private List<IEntity> entities;
	private int initialX, initialY;
	private double mouseSensitivity = 2.5;
	private double moveSpeed = 10;
	private MyVector lightVector = MyVector.normalize( new MyVector( 1, 1, 1 ) );
	private Mouse mouse;
	private Keyboard keyboard;
	private Camera camera;

	public EntityManager( )
	{
		this.entities = new ArrayList<IEntity>( );
		this.camera = new Camera( 100, 0, 0 );
	}

	public void init( UserInput userInput )
	{
		this.mouse = userInput.mouse;
		this.keyboard = userInput.keyboard;
		// this.entities.add(ComplexEntityBuilder.createRubiksCube(100, 0, 0, 0));
		// this.entities.add( BasicEntityBuilder.createDiamond( Color.CYAN, 100, 0, 0, 0
		// ) );
		// this.entities.add( BasicEntityBuilder.createSphere( Color.RED, 100, 25, 0, 0,
		// 0 ) );
		this.entities.add( BasicEntityBuilder.createMorphCube( 100, Color.RED, 0, 0, 0 ) );
		this.setLighting( );
		for ( IEntity entity : this.entities )
		{
			entity.translate( -this.camera.getX( ), -this.camera.getY( ), -this.camera.getZ( ) );
		}
	}

	public void update( )
	{
		int x = this.mouse.getX( );
		int y = this.mouse.getY( );
		if ( this.mouse.getButton( ) == ClickType.LeftClick )
		{
			int xDif = x - initialX;
			int yDif = y - initialY;

			this.rotate( true, 0, -yDif / mouseSensitivity, -xDif / mouseSensitivity );
		}
		else if ( this.mouse.getButton( ) == ClickType.RightClick )
		{
			int xDif = x - initialX;

			this.rotate( true, -xDif / mouseSensitivity, 0, 0 );
		}

		if ( this.mouse.isScrollingUp( ) )
		{
			PointConverter.zoomIn( );
		}
		else if ( this.mouse.isScrollingDown( ) )
		{
			PointConverter.zoomOut( );
		}

		if ( this.keyboard.getLeft( ) )
		{
			this.camera.translate( 0, -moveSpeed, 0 );
			for ( IEntity entity : this.entities )
			{
				entity.translate( 0, moveSpeed, 0 );
			}
		}
		if ( this.keyboard.getRight( ) )
		{
			this.camera.translate( 0, moveSpeed, 0 );
			for ( IEntity entity : this.entities )
			{
				entity.translate( 0, -moveSpeed, 0 );
			}
		}
		if ( this.keyboard.getUp( ) )
		{
			this.camera.translate( 0, 0, moveSpeed );
			for ( IEntity entity : this.entities )
			{
				entity.translate( 0, 0, -moveSpeed );
			}
		}
		if ( this.keyboard.getDown( ) )
		{
			this.camera.translate( 0, 0, -moveSpeed );
			for ( IEntity entity : this.entities )
			{
				entity.translate( 0, 0, moveSpeed );
			}
		}
		if ( this.keyboard.getForward( ) )
		{
			this.camera.translate( -moveSpeed, 0, 0 );
			for ( IEntity entity : this.entities )
			{
				entity.translate( moveSpeed, 0, 0 );
			}
		}
		if ( this.keyboard.getBackward( ) )
		{
			this.camera.translate( moveSpeed, 0, 0 );
			for ( IEntity entity : this.entities )
			{
				entity.translate( -moveSpeed, 0, 0 );
			}
		}

		for ( IEntity entity : this.entities )
		{
			// entity.shift( );
			if ( entity instanceof MorphEntity )
			{
				( ( MorphEntity ) entity ).morph( 0.1 );
			}
		}

		this.mouse.resetScroll( );
		this.keyboard.update( );

		initialX = x;
		initialY = y;
	}

	public void render( Graphics g )
	{
		for ( IEntity entity : this.entities )
		{
			entity.render( g );
		}
	}

	private void rotate( boolean CW, double xDegrees, double yDegrees, double zDegrees )
	{
		for ( IEntity entity : this.entities )
		{
			entity.rotate( CW, xDegrees, yDegrees, zDegrees, this.lightVector );
		}
	}

	private void setLighting( )
	{
		for ( IEntity entity : this.entities )
		{
			entity.setLighting( this.lightVector );
		}
	}

}
