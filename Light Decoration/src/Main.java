

public class Main {

	public static void main(String[] args) {
		
		
		LightGrid grid = new LightGrid();
		grid.toggle(461,550,564,900);
		
		System.out.println(grid.get(350).getIndex());

	}

}
