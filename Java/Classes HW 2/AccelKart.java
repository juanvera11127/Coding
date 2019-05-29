
public class AccelKart extends Kart implements KartOperators {

	private String color = "blue";
	
	public AccelKart(double topSpeed, double acceleration, double handling) {

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
