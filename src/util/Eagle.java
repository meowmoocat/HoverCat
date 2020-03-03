/*
	Órla Keating
	15205679
*/
package util;

public class Eagle extends GameObject{
	Areas attack1 = new Areas(new Point3f(95,-45,0), 90,20);
	Areas attack2 = new Areas(new Point3f(30,45,0), 90,150);
	Areas weak = new Areas(new Point3f(-10,-45,0),90, 190);

	public Eagle(String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
		if(direction == 'l') {
			attack1 = new Areas(new Point3f(-95,-45,0), 90,20);
			attack2 = new Areas(new Point3f(-30,45,0), 90,150);
			weak = new Areas(new Point3f(10,-45,0),90, 190);
		}
		else {
			attack1 = new Areas(new Point3f(95,-45,0), 90,20);
			attack2 = new Areas(new Point3f(30,45,0), 90,150);
			weak = new Areas(new Point3f(-10,-45,0),90, 190);
		}
	}

	public Boolean eagleAttacked(Hovercat player) {
		Areas eagleAttack1 = new Areas(this.attack1.getCentre().plusPoint(this.getCentre()), this.attack1.getHeight(), this.attack1.getWidth());
		Areas eagleAttack2 = new Areas(this.attack2.getCentre().plusPoint(this.getCentre()), this.attack2.getHeight(), this.attack2.getWidth());
		Areas eagleWeak = new Areas(this.weak.getCentre().plusPoint(this.getCentre()), this.weak.getHeight(), this.weak.getWidth());

		if(eagleAttack1.Intercepts(player.getAttack1()) || eagleAttack1.Intercepts(player.getAttack2())
				|| eagleAttack1.Intercepts(player.getWeak()) || eagleAttack2.Intercepts(player.getAttack1())
				|| eagleAttack2.Intercepts(player.getAttack2()) || eagleAttack2.Intercepts(player.getWeak())) {
			player.setCentre(new Point3f((float)Math.random()*700, (float)Math.random()*(700/2)+(700/2), 0));
			player.setLives(player.getLives()-1);
		}
		if(eagleWeak.Intercepts(player.getAttack1()) || eagleWeak.Intercepts(player.getAttack2()))
		{
			attack1 = new Areas(new Point3f(0,0,0),0,0);
			attack2 = new Areas(new Point3f(0,0,0),0,0);
			player.setLives(player.getLives()+1);
			return true;
		}
		return false;
	}
}
