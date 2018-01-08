import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

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
		if(isEmpty() || indexXStart > get(size() -1).getIndex()){
			for(int i = indexXStart ; i <= indexXStop; i++) {
				add(new Light(defaultValue, i));
			}
		}else if (indexXStop > get(size() -1).getIndex()) {
			stream().
					filter(light -> light.getIndex() >= indexXStart && light.getIndex() <=  get(size() -1).getIndex()).
					forEach(light -> light.setTurnedOn(onOff != null ? onOff :!light.isTurnedOn()));
			
			for(int i = get(size() -1).getIndex() + 1 ; i <= indexXStop; i++) {
				add(new Light(defaultValue, i));
			}
			
			
		}else if(indexXStart < get(0).getIndex()) {
			int indexXStopAdd = indexXStop < get(0).getIndex() ? indexXStop : get(0).getIndex();
			for(int i = indexXStart ; i <= indexXStopAdd; i++) {
				add(new Light(defaultValue, i));
			}
			
			if(indexXStopAdd != indexXStop) {
				this.subList(indexXStart, indexXStop).forEach(light -> light.setTurnedOn(onOff != null ? onOff :!light.isTurnedOn()));
			}
		}else{
			Vector<Light> temp = new Vector<>();
			
			for(int i = indexXStart ; i <= indexXStop; i++) {
				temp.add(new Light(defaultValue, i));
			}
			List<Light> alreadyExistingLights = stream().filter(light -> light.getIndex() >= indexXStart && light.getIndex() <= indexXStop).collect(Collectors.toList());
			for(Light existing : alreadyExistingLights) {
				existing.setTurnedOn(onOff != null ? onOff :!existing.isTurnedOn());
				temp.set(existing.getIndex() - indexXStart, existing);
			}
			
			for(int i = 0; i< size() ; i++) {
				if(get(i).getIndex() < temp.get(i).getIndex()) {
					temp.add(i, get(i));
				}else if(get(i).getIndex() > temp.get(temp.size() -1).getIndex()) {
					temp.add(temp.size() -1, get(i));
				}
			}
			this.clear();
			this.addAll(temp);
		}
	}

	public int getIndex() {
		return indexY;
	}
	
	
	
	

}
