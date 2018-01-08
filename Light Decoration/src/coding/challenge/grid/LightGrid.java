package coding.challenge.grid;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

public class LightGrid extends Vector<LightLine>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void turnOnOffOrToggleGrid(int indexStartX, int indexStartY, int indexStopX, int indexStopY, Boolean onOff) {
		
		Vector<LightLine> temp = new Vector<>();
		
		for(int i = indexStartY ; i <= indexStopY; i++) {
			LightLine line = new LightLine(i);
			line.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
			temp.add(line);
		}
		
		Set<LightLine> alreadyExistingLines = stream().filter(light -> light.getIndex() >= indexStartY && light.getIndex() <= indexStopY).collect(Collectors.toSet());
		for(LightLine existing : alreadyExistingLines) {
			existing.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
			temp.set(existing.getIndex() - indexStartY, existing);
		}
		
		this.removeAll(alreadyExistingLines);
		
		Set<LightLine> alreadyExistingLowerIndexLines = stream().filter(light -> light.getIndex() < indexStartY).collect(Collectors.toSet());
		this.removeAll(alreadyExistingLowerIndexLines);
		
		Set<LightLine> alreadyExistingHigherIndexLines = stream().filter(light -> light.getIndex() < indexStopY).collect(Collectors.toSet());
		
		this.clear();
		this.addAll(alreadyExistingLowerIndexLines);
		this.addAll(temp);
		this.addAll(alreadyExistingHigherIndexLines);
		
	}
	public void toggle(int indexStartX, int indexStartY, int indexStopX, int indexStopY) {
		turnOnOffOrToggleGrid(indexStartX, indexStartY, indexStopX, indexStopY, null);
	}
	
	public void turn(boolean onOff, int indexStartX, int indexStartY, int indexStopX, int indexStopY) {
		turnOnOffOrToggleGrid(indexStartX, indexStartY, indexStopX, indexStopY, Boolean.valueOf(onOff));
	}
	
	public long getNumberOfLightsOn() {
		long numberLightOn = 0;
		for(int i = 0; i <size(); i++) {
			numberLightOn += get(i).getNumberOfLightsOn();
		}
		return numberLightOn;
	}
}
