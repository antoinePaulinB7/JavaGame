package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestLireStatsItems {

	public static void main(String[] args) {
		
		int id = 2;
		String itemName = null;
		int gold = 0;
		int bonuses[] = new int[4];
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			fr = new FileReader("ItemList.txt");
			br = new BufferedReader(fr);
			
			String ligneCourante = null;
			
			br.readLine();
			
			for (int i = 0; i < id + 1; i++) 
				ligneCourante = br.readLine();

			String[] valeurs = ligneCourante.trim().split(" ");		
				
			itemName = valeurs[1];
			gold = Integer.parseInt(valeurs[2]);
			System.out.println("Id : " + id + " Name : " + itemName + " Gold : " + gold + " Bonuses: ");
			
			for (int i = 0; i < 4; i++) {
				bonuses[i] = Integer.parseInt(valeurs[i+3]);
				System.out.print(bonuses[i] + "  ");
			}
			
			
			
			
		}catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

	}
	
	
	

}
