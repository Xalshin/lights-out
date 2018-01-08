
import coding.challenge.grid.LightGrid;
import coding.challenge.instructions.reader.InstructionReader;

public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		LightGrid  result = InstructionReader.readAndExecuteInstructions("instructions.txt");
		System.out.println(result.getNumberOfLightsOn());
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);

	}

}
