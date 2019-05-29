
public class Block {

	private int mass;
	private int velocity;
	private int size;
	
	public Block(int mass, int velocity, int size) {
		this.setMass(mass);
		this.setVelocity(velocity);
		this.setSize(size);
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
