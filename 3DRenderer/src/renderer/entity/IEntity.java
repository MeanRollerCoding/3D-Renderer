package renderer.entity;

import java.awt.Graphics;

import renderer.point.MyVector;

public interface IEntity
{

	void render( Graphics g );

	void translate( double x, double y, double z );

	void rotate( boolean CW, double xDegrees, double yDegrees, double zDegrees, MyVector lightVector );

	void setLighting( MyVector lightVector );

	void shift( );

}
