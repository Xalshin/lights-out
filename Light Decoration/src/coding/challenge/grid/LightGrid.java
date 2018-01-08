package coding.challenge.grid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LightGrid extends HashMap<Integer,LightLine>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void turnOnOffOrToggleGrid(int indexStartX, int indexStartY, int indexStopX, int indexStopY, Boolean onOff) {
		
		Map<Integer,LightLine> temp = new HashMap<>();
		
		for(int i = indexStartY ; i <= indexStopY; i++) {
			LightLine line = new LightLine();
			line.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
			temp.put(i,line);
		}
		
		Map<Integer,LightLine> alreadyExistingLines = entrySet().stream().filter(entry -> entry.getKey() >= indexStartY &&  entry.getKey() <= indexStopY).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		for(Entry<Integer, LightLine> existing : alreadyExistingLines.entrySet()) {
			existing.getValue().turnOnOffOrToggle(indexStartX, indexStopX, onOff);
			temp.put(existing.getKey() - indexStartY, existing.getValue());
		}
		
		Map<Integer,LightLine> alreadyExistingLowerIndexLines = entrySet().stream().filter(entry -> entry.getKey() < indexStartY).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		
		Map<Integer,LightLine> alreadyExistingHigherIndexLines = entrySet().stream().filter(entry -> entry.getKey() < indexStopY).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		
		this.clear();
		this.putAll(alreadyExistingLowerIndexLines);
		this.putAll(temp);
		this.putAll(alreadyExistingHigherIndexLines);
		
	}
	public void toggle(int indexStartX, int indexStartY, int indexStopX, int indexStopY) {
		turnOnOffOrToggleGrid(indexStartX, indexStartY, indexStopX, indexStopY, null);
	}
	
	public void turn(boolean onOff, int indexStartX, int indexStartY, int indexStopX, int indexStopY) {
		turnOnOffOrToggleGrid(indexStartX, indexStartY, indexStopX, indexStopY, Boolean.valueOf(onOff));
	}
	
	public long getNumberOfLightsOn() {
		long numberLightOn = 0;
		for(Entry<Integer, LightLine> entry : entrySet()) {
			numberLightOn += entry.getValue().getNumberOfLightsOn();
		}
		return numberLightOn;
	}
}
