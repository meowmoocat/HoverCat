package util;

public class Area {
	private Point3f middle;
	private Point3f size;

	public Area(Point3f middle, Point3f size) {
		this.middle = middle;
		this.size = size;
	}

	public Point3f getMiddle() {
		return middle;
	}

	public Point3f getSize() {
		return size;
	}

	public void setMiddle(Point3f middle) {
		this.middle = middle;
	}

	public void setSize(Point3f size) {
		this.size = size;
	}
}
