
public class Light {
	private boolean turnedOn = false;
	private int index;
	
	
	public Light(boolean turnedOn, int index) {
		super();
		this.turnedOn = turnedOn;
		this.index = index;
	}
	
	public Light(int index) {
		super();
		this.index = index;
	}


	public boolean isTurnedOn() {
		return turnedOn;
	}
	public void setTurnedOn(boolean turnedOn) {
		this.turnedOn = turnedOn;
	}
	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "Light [turnedOn=" + turnedOn + ", index=" + index + "]";
	}
	
}
