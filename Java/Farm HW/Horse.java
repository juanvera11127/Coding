
public class Horse
	{
	private String color;
	private int fed;
	private int washed;
	private int exercised;
	private int combed;
	
	public Horse(String color, int fed, int washed, int exercised, int combed) {
		this.color = color;
		this.fed = fed;
		this.washed = washed;
		this.exercised = exercised;
		this.combed = combed;
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
	public void incrementCombed(int weeks) {
		combed *= weeks;
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
	public void setCombed(int n) {
		combed = n;
	}
	
	public int getCombed() {
		return combed;
	}
	
	public String getColor() {
		return color;
	}
}