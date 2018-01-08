import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class LightGrid extends Vector<LightLine>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void turnOnOffOrToggleGrid(int indexStartX, int indexStartY, int indexStopX, int indexStopY, Boolean onOff) {
		if(isEmpty() || indexStartY > get(size() -1).getIndex()){
			for(int i = indexStartY ; i <= indexStopY; i++) {
				LightLine line = new LightLine(i);
				line.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
				add(line);
			}
		}else if (indexStopY > get(size() -1).getIndex()) {
			stream().
					filter(line -> line.getIndex() >= indexStartY && line.getIndex() <=  get(size() -1).getIndex()).
					forEach(line -> line.turnOnOffOrToggle(indexStartX, indexStopX, onOff));
			
			for(int i = get(size() -1).getIndex() + 1 ; i <= indexStopY; i++) {
				LightLine line = new LightLine(i);
				line.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
				add(line);
			}
			
			
		}else if(indexStartY < get(0).getIndex()) {
			int indexStopYAdd = indexStopY < get(0).getIndex() ? indexStopY : get(0).getIndex();
			for(int i = indexStartY ; i <= indexStopYAdd; i++) {
				LightLine line = new LightLine(i);
				line.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
				add(line);
			}
			
			if(indexStopYAdd != indexStopY) {
				this.subList(indexStartY, indexStopY).forEach(line -> line.turnOnOffOrToggle(indexStartX, indexStopX, onOff));
			}
		}else{
			Vector<LightLine> temp = new Vector<>();
			
			for(int i = indexStartY ; i <= indexStopY; i++) {
				LightLine line = new LightLine(i);
				line.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
				temp.add(line);
			}
			List<LightLine> alreadyExistingLights = stream().filter(light -> light.getIndex() >= indexStartY && light.getIndex() <= indexStopY).collect(Collectors.toList());
			for(LightLine existing : alreadyExistingLights) {
				existing.turnOnOffOrToggle(indexStartX, indexStopX, onOff);
				temp.set(existing.getIndex() - indexStartY, existing);
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
	public void toggle(int indexStartX, int indexStartY, int indexStopX, int indexStopY) {
		turnOnOffOrToggleGrid(indexStartX, indexStartY, indexStopX, indexStopY, null);
	}
	
	public void turn(boolean onOff, int indexStartX, int indexStartY, int indexStopX, int indexStopY) {
		turnOnOffOrToggleGrid(indexStartX, indexStartY, indexStopX, indexStopY, Boolean.valueOf(onOff));
	}
}
