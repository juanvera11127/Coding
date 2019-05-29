public class Animal {

	// fields
	private String type;
	private double cost;
	
	// constructor
	public Animal(String type, double cost) {
		this.type = type;
		this.cost = cost;
	}

	public void setType(String n) {
		type = n;
	}
	public String getType() {
		return type;
	}

	public void setCost(double n) {
		cost = n;
	}
	
	public double getCost() {
		return cost;
	}
	

}