package tests;

public class TestFichier {

	public static void main(String[] args) {
		
		String seed = "allo";
		int newSeed;
		
		try {
			newSeed = Integer.parseInt(seed);
		} catch (NumberFormatException e) {
			newSeed = seed.hashCode();
		}
		
		System.out.println(newSeed);
	
	
	}
	
		
}
