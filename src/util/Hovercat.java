/*
	Órla Keating
	15205679
*/
package util;

public class Hovercat extends GameObject{
	private int lives = 9;
	private Areas attack1 = new Areas(new Point3f(70,5,0), 40,10);
	private Areas attack2 = new Areas(new Point3f(25,13,0), 26,100);
	private Areas weak = new Areas(new Point3f(-5,-8,0),16, 140);

	public Hovercat(String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
	}

	public Areas getAttack1() {
		return (new Areas(this.attack1.getCentre().plusPoint(this.getCentre()), this.attack1.getHeight(), this.attack1.getWidth()));
	}

	public Areas getAttack2() {
		return (new Areas(this.attack2.getCentre().plusPoint(this.getCentre()), this.attack2.getHeight(), this.attack2.getWidth()));
	}

	public Areas getWeak() {
		return (new Areas(this.weak.getCentre().plusPoint(this.getCentre()), this.weak.getHeight(), this.weak.getWidth()));
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	@Override
	public void changeDirection(char direction) {
		super.changeDirection(direction);
		if (direction == 'l') {
			attack1.setCentre(new Point3f(-70,5,0));
			attack2.setCentre(new Point3f(-25,13,0));
			weak.setCentre(new Point3f(5,-8,0));
		} else {
			attack1.setCentre(new Point3f(70,5,0));
			attack2.setCentre(new Point3f(25,13,0));
			weak.setCentre(new Point3f(-5,-8,0));
		}
	}
}
