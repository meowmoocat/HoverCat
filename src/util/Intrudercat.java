/*
	Órla Keating
	15205679
*/
package util;

public class Intrudercat extends GameObject{
	private Areas attack1 = new Areas(new Point3f(-70,5,0), 40,10);
	private Areas attack2 = new Areas(new Point3f(-25,13,0), 26,100);
	private Areas weak = new Areas(new Point3f(5,-8,0),15, 140);

	public Intrudercat(String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
	}

	@Override
	public void changeDirection(char direction) {
		super.changeDirection(direction);
		if (direction == 'l') {
			attack1.setCentre(new Point3f(-70,5,0));
			attack2.setCentre(new Point3f(-25,13,0));
			weak.setCentre(new Point3f(5,-8,0));
			this.setTexture("res/intrudercatB.png");
		} else {
			attack1.setCentre(new Point3f(70,5,0));
			attack2.setCentre(new Point3f(25,13,0));
			weak.setCentre(new Point3f(-5,-8,0));
			this.setTexture("res/intrudercatA.png");
		}
	}

	public Boolean intrudercatAttacked(Hovercat player) {
		Areas catAttack1 = new Areas(this.attack1.getCentre().plusPoint(this.getCentre()), this.attack1.getHeight(), this.attack1.getWidth());
		Areas catAttack2 = new Areas(this.attack2.getCentre().plusPoint(this.getCentre()), this.attack2.getHeight(), this.attack2.getWidth());
		Areas catWeak = new Areas(this.weak.getCentre().plusPoint(this.getCentre()), this.weak.getHeight(), this.weak.getWidth());

		if(catAttack1.Intercepts(player.getAttack1()) || catAttack1.Intercepts(player.getAttack2())
				|| catAttack1.Intercepts(player.getWeak()) || catAttack2.Intercepts(player.getAttack1())
				|| catAttack2.Intercepts(player.getAttack2()) || catAttack2.Intercepts(player.getWeak())) {
			player.setCentre(new Point3f((float)Math.random()*700, (float)Math.random()*700, 0));
			player.setLives(player.getLives()-1);
		}
		else if(catWeak.Intercepts(player.getAttack1()) || catWeak.Intercepts(player.getAttack2()))
		{
			attack1 = new Areas(new Point3f(0,0,0),0,0);
			attack2 = new Areas(new Point3f(0,0,0),0,0);
			if(this.getDirection() == 'l') {
				super.changeDirection('r');
				this.getCentre().ApplyVector(new Vector3f(10, 0, 0));
			}
			else {
				super.changeDirection('l');
				this.getCentre().ApplyVector(new Vector3f(-10, 0, 0));
			}
			return true;
		}
		return false;
	}
}
