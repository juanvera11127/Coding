
public class Cow
	{
	private String color;
	private int fed;
	private int washed;
	private int exercised;
	private int milked;
	
	public Cow(String color, int fed, int washed, int exercised, int milked) {
		this.color = color;
		this.fed = fed;
		this.washed = washed;
		this.exercised = exercised;
		this.milked = milked;
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
	public void incrementMilked(int weeks) {
		milked *= weeks;
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
	public void setMilked(int n) {
		milked = n;
	}
	
	public int getMilked() {
		return milked;
	}
	
	public String getColor() {
		return color;
	}
}