
public class Chicken
	{
	//include number of times fed, watered, and exercised(daily) + milked
	private String color;
	private int fed;
	private int washed;
	private int exercised;
	private int eggs;
	
	public Chicken(String color, int fed, int washed, int exercised, int eggs) {
		this.color = color;
		this.fed = fed;
		this.washed = washed;
		this.exercised = exercised;
		this.eggs = eggs;
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
	public void incrementEggs(int weeks) {
		eggs *= weeks;
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
	public void setEggs(int n) {
		eggs = n;
	}
	
	public int getEggs() {
		return eggs;
	}
	
	public String getColor() {
		return color;
	}
}