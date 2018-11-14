package interfaces;

import java.awt.Color;

import entites.Sprite;
import environnement.Coffre;
import interfaces.Enumerations.Effet;
import item.Item;
import personnages.StatsMonstres;

/**
 * Classe qui initialise les constantes
 * @author Antoine
 * @author Mihai
 * @author Olivier
 *
 */
public interface Constantes {
	final double K_GRAVITE = 1;
	final double FPS = 60;
	final int NIVEAU_MAX = 10;

	final Coffre coffresFermes[] = {
			new Coffre("chest_0_0.png"),
			new Coffre("chest_1_0.png"),
			new Coffre("chest_2_0.png"),
			new Coffre("chest_3_0.png")
			
	};

	final Coffre coffresOuverts[] = {
			new Coffre("chest_0_1.png"),
			new Coffre("chest_1_1.png"),
			new Coffre("chest_2_1.png"),
			new Coffre("chest_3_1.png")
			};
	
	final Sprite plateformes[] = {
			new Sprite("plateforme0"),
			new Sprite("plateforme1"),
			new Sprite("plateforme2"),
			new Sprite("plateforme3"),
			new Sprite("plateforme4"),
			new Sprite("plateforme5"),
			new Sprite("plateforme6"),
			new Sprite("plateforme7"),
			new Sprite("plateforme8"),
			new Sprite("plateforme9")
	};
	final String nomBiomes[] = {
		"Herbe",
		"Neige",
		"Volcan",
		"Desert",
		"Montagne",
		"Shadow",
		"Mort",
		"Château",
		"Candy land",
		"70s"
	};
	final Color couleurs[] = {
			new Color(87,173,46), 	  //Herbe
			new Color(239,239,239),   //Neige
			new Color(85,13,13), 	  //Volcan
			new Color(255, 232, 137), //Desert
			new Color(182, 178, 150), //Montagne
			new Color(66,40,79), 	  //Shadow
			new Color(30,29,29),      //Mort
			new Color(94,93,92),      //Chateau
			new Color(55,167,255),    //Candy land
			new Color(255,180,253)    //70s
	};
	
	final Item armes[] = {
			new Item("00"),
			new Item("01"),
			new Item("02"),
			new Item("03"),
			new Item("04"),
			new Item("05"),
			new Item("06"),
			new Item("07"),
			new Item("08"),
			new Item("09"),
			new Item("10"),
			new Item("11"),
			new Item("12"),
			new Item("13"),
			new Item("14"),
			new Item("15"),
			new Item("16"),
			new Item("17")
	};
	
	final Item boucliers[] = {
			new Item("18"),
			new Item("19"),
			new Item("20"),
			new Item("21"),
			new Item("22"),
			new Item("23"),
			new Item("24"),
			new Item("25"),
			new Item("26"),
			new Item("27")
	};
	
	final Effet effets[] = {
			Effet.GAZON, 
			Effet.GLACE,
			Effet.VOLCANIQUE,
			Effet.SABLE,
			Effet.MONTAGNE,
			Effet.SHADOW,
			Effet.BOUE, 
			Effet.PIERRE,
			Effet.DONUT,
			Effet.TASSE			
	};
	final StatsMonstres monstresParBiome[] = {
			new StatsMonstres(0),
			new StatsMonstres(1),
			new StatsMonstres(2),
			new StatsMonstres(3),
			new StatsMonstres(4),
			new StatsMonstres(5),
			new StatsMonstres(6),
			new StatsMonstres(7),
			new StatsMonstres(8),
			new StatsMonstres(9)		
	};

	final double positionsMainsViking[][][] = {
			{//attaque charge

				{2,5,13,5, 270},//frame 0
				{1,6,13,6, 300},
				{1,4,12,7, 330},
				{2,10,13,7,  0},
				{8,5,14,7, 60},
				{6,4,13,6, 90}

			},
			{//bloque
				{8,5,14,7, 60},
				{6,4,13,6, 90}
			}
	};

	final double positionsMainsGoblin[][][] = {
			{//attaque charge

				{2,4,13,4, 270},//frame 0
				{1,5,13,5, 300},
				{1,3,12,6, 330},
				{2,9,13,6, 0},
				{8,4,14,6, 60},
				{6,3,13,5, 90}

			},
			{//bloque
				{8,4,14,6, 60},
				{6,3,13,5, 90}
			}
	};

	final double positionsMainsLeprechaun[][][] = {
			{//attaque charge

				{2,3,13,3, 270},//frame 0
				{1,4,13,4, 300},
				{1,2,12,5, 330},
				{2,8,13,5, 0},
				{8,3,14,5, 60},
				{6,2,13,4, 90}

			},
			{//bloque
				{8,3,14,5, 60},
				{6,2,13,4, 90}
			}
	};

}