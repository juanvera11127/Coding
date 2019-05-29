
public abstract class Kart {

	private double topSpeed;
	private double acceleration;
	private double handling;
	
	public Kart(double topSpeed, double acceleration, double handling) {
		
		this.topSpeed = topSpeed;
		this.acceleration = acceleration;
		this.handling = handling;
	}
	
	public double getTopSpeed() {
		return topSpeed;
	}
	
	public void setTopSpeed(double n) {
		topSpeed = n;
	}
	
	public double getAcceleration() {
		return acceleration;
	}
	
	public void setAcceleration(double n) {
		acceleration = n;
	}
	
	public double getHandling() {
		return handling;
	}
	
	public void setHandling(double n) {
		handling = n;
	}
	
	public boolean equals(Kart a) {
		return(topSpeed == a.topSpeed && acceleration == a.acceleration && handling == a.handling);
	}
	public boolean isSlowerThan(Kart a) {
		return topSpeed < a.getTopSpeed();
	}
	public boolean isFasterThan(Kart a) {
		return topSpeed > a.getTopSpeed();
	}
	public boolean turnsFasterThan(Kart a) {
		return handling > a.getHandling();
	}
	public boolean turnsSlowerThan(Kart a) {
		return handling < a.getHandling();
	}
	public boolean acceleratesFasterThan(Kart a) {
		return acceleration > a.getAcceleration();
	}
	public boolean acceleratesSlowerThan(Kart a) {
		return acceleration < a.getAcceleration();
	}
	
	public abstract String getColor();
	
	public String toString() {
		return("Speed: " + topSpeed + ", Acceleration: " + acceleration + ", Handling: " + handling);
	}
	
}
