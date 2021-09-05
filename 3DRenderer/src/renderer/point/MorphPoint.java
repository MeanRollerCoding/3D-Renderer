package renderer.point;

public class MorphPoint extends MyPoint
{
	public MyPoint p1, p2;

	private int target;

	public MorphPoint( double x1, double y1, double z1, double x2, double y2, double z2 )
	{
		super( x1, y1, z1 );

		this.p1 = new MyPoint( x1, y1, z1 );
		this.p2 = new MyPoint( x2, y2, z2 );

		this.target = 2;
	}

	public void morph( double morphSpeed )
	{
		double dist = Math.sqrt( Math.pow( x - p1.x, 2 ) + Math.pow( y - p1.y, 2 ) + Math.pow( z - p1.z, 2 ) );
		double totalDist = Math.sqrt( Math.pow( p2.x - p1.x, 2 ) + Math.pow( p2.y - p1.y, 2 ) + Math.pow( p2.z - p1.z, 2 ) );
		double progress = this.target == 2 ? dist / totalDist : 1 - dist / totalDist;

		if ( progress + morphSpeed >= 1 )
		{
			this.x = this.target == 2 ? p2.x : p1.x;
			this.y = this.target == 2 ? p2.y : p1.y;
			this.z = this.target == 2 ? p2.z : p1.z;

			this.target = this.target == 2 ? 1 : 2;
		}
		else
		{
			double deltaX = ( p2.x - p1.x ) * morphSpeed;
			double deltaY = ( p2.y - p1.y ) * morphSpeed;
			double deltaZ = ( p2.z - p1.z ) * morphSpeed;

			this.x += this.target == 2 ? deltaX : -deltaX;
			this.y += this.target == 2 ? deltaY : -deltaY;
			this.z += this.target == 2 ? deltaZ : -deltaZ;
		}
	}

}
