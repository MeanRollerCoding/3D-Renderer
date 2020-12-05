package renderer.point;

public class MyPoint {

	public double x, y, z;
	public double xOffset, yOffset, zOffset;
	
	public MyPoint(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.xOffset = 0;
		this.yOffset = 0;
		this.zOffset = 0;
	}
	
	public double getAdjustedX() {
		return this.x + this.xOffset;
	}
	
	public double getAdjustedY() {
		return this.y + this.yOffset;
	}
	
	public double getAdjustedZ() {
		return this.z + this.zOffset;
	}

}
