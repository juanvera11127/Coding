
public class Pig
	{
	//include number of times fed, watered, and exercised(daily) + milked
	private String color;
	private int fed;
	private int washed;
	private int exercised;
	private int mud;
	
	public Pig(String color, int fed, int washed, int exercised, int mud) {
		this.color = color;
		this.fed = fed;
		this.washed = washed;
		this.exercised = exercised;
		this.mud = mud;
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
	public void incrementMud(int weeks) {
		mud *= weeks;
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
	public void setMud(int n) {
		mud = n;
	}
	
	public int getMud() {
		return mud;
	}
	
	public String getColor() {
		return color;
	}
}