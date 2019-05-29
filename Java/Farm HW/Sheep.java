
public class Sheep
	{
	//include number of times fed, watered, and exercised(daily) + milked
	private String color;
	private int fed;
	private int washed;
	private int exercised;
	private int sheared;
	
	public Sheep(String color, int fed, int washed, int exercised, int sheared) {
		this.color = color;
		this.fed = fed;
		this.washed = washed;
		this.exercised = exercised;
		this.sheared = sheared;
	}
	
	public void incrementFed(int weeks) {
		fed *= weeks * 7;
	}
	public void incrementWashed(int weeks) {
		washed *= weeks;
	}
	public void incrementExercised(int weeks) {
		exercised *= weeks;
	}
	public void incrementSheared(int weeks) {
		sheared *= weeks/4;
	}
	
	public void setFed(int n) {
		fed = n;
	}
	
	public int getFed() {
		return fed;
	}
	
	public void setWashed(int n) {
		washed = n;
	}
	
	public int getWashed() {
		return washed;
	}
	public void setExercised(int n) {
		exercised = n;
	}
	
	public int getExercised() {
		return exercised;
	}
	public void setSheared(int n) {
		sheared = n;
	}
	
	public int getSheared() {
		return sheared;
	}
	
	public String getColor() {
		return color;
	}
}