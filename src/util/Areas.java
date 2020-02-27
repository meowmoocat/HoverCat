package util;

public class Areas {
	Point3f Centre;
	int Height;
	int Width;

	public Areas(Point3f centre, int height, int width) {
		this.Centre = centre;
		this.Height = height;
		this.Width = width;
	}

	public Point3f getCentre() {
		return Centre;
	}
	public int getHeight() {
		return Height;
	}
	public int getWidth() {
		return Width;
	}

	public void setCentre(Point3f centre) {
		Centre = centre;
	}

	public Boolean Intercepts(Areas player) {
		Point3f distance = new Point3f(Math.abs(this.getCentre().getX() - player.getCentre().getX()),
				Math.abs(this.getCentre().getY() - player.getCentre().getY()),
				Math.abs(this.getCentre().getZ() - player.getCentre().getZ()));

		float collisionDistanceHeight = (this.getHeight()+player.getHeight()) / 2.0f;
		float collisionDistanceWidth = (this.getWidth()+player.getWidth()) / 2.0f;

		return (distance.getX() < collisionDistanceWidth && distance.getY() < collisionDistanceHeight);
	}
}
