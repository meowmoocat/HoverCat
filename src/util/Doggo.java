package util;

public class Doggo extends GameObject {
	private Areas attack = new Areas(new Point3f(-45,0,0), 90,50);
	private Areas weak = new Areas(new Point3f(-25,0,0),90, 90);

	public Doggo(String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
	}

	@Override
	public void changeDirection(char direction) {
		super.changeDirection(direction);
		if(direction == 'l') {
			attack.setCentre(new Point3f(-45,0,0));
			weak.setCentre(new Point3f(-25,0,0));
		}
		else {
			attack.setCentre(new Point3f(45,0,0));
			weak.setCentre(new Point3f(25,0,0));
		}
	}

	public Boolean doggoattacked(Hovercat player) {
		Areas dogAttack = new Areas(this.attack.getCentre().plusPoint(this.getCentre()), this.attack.getHeight(), this.attack.getWidth());
		Areas dogWeak = new Areas(this.weak.getCentre().plusPoint(this.getCentre()), this.weak.getHeight(), this.weak.getWidth());

		if(dogAttack.Intercepts(player.getAttack1()) || dogAttack.Intercepts(player.getAttack2()) || dogAttack.Intercepts(player.getWeak())) {
			player.setCentre(new Point3f(100, 300, 0));
			player.setLives(player.getLives()-1);
		}
		else if(dogWeak.Intercepts(player.getWeak())) {
			if(this.getDirection() == 'l') {
				this.changeDirection('r');
				this.setTexture("res/dogb.png");
			}
			else if(this.getDirection() == 'r') {
				this.changeDirection('l');
				this.setTexture("res/dog.png");
			}
		}
		else if(dogWeak.Intercepts(player.getAttack1()) || dogWeak.Intercepts(player.getAttack2()))
		{
			attack = new Areas(new Point3f(0,0,0),0,0);
			if(this.getDirection() == 'l') {
				this.getCentre().ApplyVector(new Vector3f(-10, 0, 0));
			}
			else {
				this.getCentre().ApplyVector(new Vector3f(10, 0, 0));
			}
			return true;
		}
		return false;
	}
}
