
public class SpeedKart extends Kart implements KartOperators {

	private String color = "red";
	
	public SpeedKart(double topSpeed, double acceleration, double handling) {

		super(topSpeed, acceleration, handling);
	}
	
	public String getColor() {
		return color;
	}
	
	public String toString() {
		String str = super.toString() + ", Color: " + this.getColor();
		return str;
		}
}
