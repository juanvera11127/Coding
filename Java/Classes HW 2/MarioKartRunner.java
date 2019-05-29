
public class MarioKartRunner {

	public static void main(String[] args) {
		
		//CREATE KARTS (TOP SPEED, ACCELERATION, HANDLING)
		BasicKart mario = new BasicKart(1.0, 1.0, 1.0);
		SpeedKart luigi = new SpeedKart(2.0, 0.5, 0.5);
		HandlingKart toad = new HandlingKart(0.5, 0.5, 2.0);
		AccelKart peach = new AccelKart(0.5, 2.0, 0.5);
		System.out.println(luigi.isFasterThan(mario));
		System.out.println(mario);
		mario.setAcceleration(5.0);
		System.out.println(mario);
	}
}
