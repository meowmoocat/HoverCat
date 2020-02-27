package util;

public class Eagle extends GameObject{
	Areas attack = new Areas(new Point3f(0,0,0), 1,1);
	Areas weak = new Areas(new Point3f(0,0,0),1, 1);

	public Eagle(String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
	}
}
