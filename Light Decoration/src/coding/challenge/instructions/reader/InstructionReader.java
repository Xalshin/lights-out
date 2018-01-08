package coding.challenge.instructions.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import coding.challenge.grid.LightGrid;

public class InstructionReader {
	public static LightGrid readAndExecuteInstructions(String path) {
		
		 Pattern p = Pattern.compile("^.*(turn\\s(on|off)|toggle)\\s(\\d{1,3})[,](\\d{1,3})\\sthrough\\s(\\d{1,3})[,](\\d{1,3}).*$");
		
		LightGrid result = new LightGrid();
		
		ClassLoader classLoader = InstructionReader.class.getClassLoader();
		InputStream imputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(imputStream));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				Matcher resultMatch = p.matcher(line);
				resultMatch.matches();
				resultMatch.group(0);
				if(resultMatch.matches()) {
					boolean toggle = "toggle".equals(resultMatch.group(1));
					boolean onOff = "on".equals(resultMatch.group(2));
					int indexStartX = Integer.parseInt(resultMatch.group(3));
					int indexStartY = Integer.parseInt(resultMatch.group(4));
					int indexStopX = Integer.parseInt(resultMatch.group(5));
					int indexStopY = Integer.parseInt(resultMatch.group(6));
					if(toggle) {
						result.toggle(indexStartX, indexStartY, indexStopX, indexStopY);
					}else{
						result.turn(onOff, indexStartX, indexStartY, indexStopX, indexStopY);
					}
					
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
