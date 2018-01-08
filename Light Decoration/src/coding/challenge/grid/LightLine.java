package coding.challenge.grid;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import coding.challenge.grid.element.Light;

public class LightLine extends Vector<Light>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int indexY;
	
	public LightLine(int indexY) {
		super();
		this.indexY = indexY;
	}
	
	public void turnOnOffOrToggle(int indexXStart, int indexXStop, Boolean onOff) {
		boolean defaultValue = true;
		
		if(onOff != null) {
			defaultValue = onOff;
		}
		
		Vector<Light> temp = new Vector<>();
		for(int i = indexXStart ; i <= indexXStop; i++) {
			temp.add(new Light(defaultValue, i));
		}
		
		Set<Light> alreadyExistingLights = stream().filter(light -> light.getIndex() >= indexXStart && light.getIndex() <= indexXStop).collect(Collectors.toSet());
		
		for(Light existing : alreadyExistingLights) {
			existing.setTurnedOn(onOff != null ? onOff :!existing.isTurnedOn());
			temp.set(existing.getIndex() - indexXStart, existing);
		}
		this.removeAll(alreadyExistingLights);
		
		Set<Light> alreadyExistingLowerIndexLights = stream().filter(light -> light.getIndex() < indexXStart).collect(Collectors.toSet());
		this.removeAll(alreadyExistingLowerIndexLights);
		
		Set<Light> alreadyExistingHigherIndexLights = stream().filter(light -> light.getIndex() > indexXStop).collect(Collectors.toSet());
		
		this.clear();
		this.addAll(alreadyExistingLowerIndexLights);
		this.addAll(temp);
		this.addAll(alreadyExistingHigherIndexLights);
	}

	public int getIndex() {
		return indexY;
	}

	public long getNumberOfLightsOn() {
		return stream().filter(Light::isTurnedOn).count();
	}
	
	
	
	

}
