
public class AnimalShelter {

	private Animal animal;
	private Volunteer volunteer;
	private Customer customer;
	
	public AnimalShelter(Animal animal, Volunteer volunteer, Customer customer) {
		this.animal = animal;
		this.volunteer = volunteer;
		this.customer = customer;
	}
	
	public void setAnimalType(String a) {
		animal.setType(a);
	}
	public void setAnimalCost(double n) {
		animal.setCost(n);
	}
	public void setVolunteerName(String a) {
		volunteer.setname(a);
	}
	public void setCustomerName(String a) {
		customer.setname(a);
	}
	
	public String getAnimalType() {
		return animal.getType();
	}
	public double getAnimalCost() {
		return animal.getCost();
	}
	public String getVolunteerName() {
		return volunteer.getName();
	}
	public String getCustomerName() {
		return customer.getName();
	}

	
}
