
import coding.challenge.grid.LightGrid;
import coding.challenge.instructions.reader.InstructionReader;

public class Main {

	public static void main(String[] args) {
	
		LightGrid  result = InstructionReader.readAndExecuteInstructions("instructions.txt");
		System.out.println(result.getNumberOfLightsOn());

	}

}
