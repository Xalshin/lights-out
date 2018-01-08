package coding.challenge.grid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LightLine extends HashMap<Integer, Boolean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int indexY;
	
	public LightLine() {
		super();
	}
	
	public void turnOnOffOrToggle(int indexXStart, int indexXStop, Boolean onOff) {
		boolean defaultValue = true;
		
		if(onOff != null) {
			defaultValue = onOff;
		}
		
		Map<Integer,Boolean> temp = new HashMap<>();
		for(int i = indexXStart ; i <= indexXStop; i++) {
			temp.put(i, defaultValue);
		}
		
		Set<Integer> alreadyExistingLights = keySet().stream().filter(index -> index >= indexXStart && index <= indexXStop).collect(Collectors.toSet());
		
		for(Integer existingIndex : alreadyExistingLights) {
			temp.put(existingIndex - indexXStart, onOff != null ? onOff :!get(existingIndex));
		}
		
		Map<Integer, Boolean> alreadyExistingLowerIndexLights = entrySet().stream().filter(entry -> entry.getKey() < indexXStart).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		Map<Integer, Boolean> alreadyExistingHigherIndexLights =entrySet().stream().filter(entry -> entry.getKey() > indexXStop).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		
		this.clear();
		this.putAll(alreadyExistingLowerIndexLights);
		this.putAll(temp);
		this.putAll(alreadyExistingHigherIndexLights);
	}

	public int getIndex() {
		return indexY;
	}

	public long getNumberOfLightsOn() {
		return entrySet().stream().filter(entry -> entry.getValue()).count();
	}
	
	
	
	

}
