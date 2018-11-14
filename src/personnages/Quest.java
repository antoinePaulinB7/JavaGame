package personnages;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import interfaces.Constantes;
import item.Item;
import monde.InformationsJeu;

/**
 * Classe qui gere la creation des quetes
 * @author Mihai
 *
 */
public class Quest {
	private final int TOTAL_QUESTS = 7;
	private int questType, biomeToFind = -1, differentMonstersToKill = -1, anyMonstersToKill = -1;
	private int niveau;
	private String itemIdToFind = "";
	private List<Integer> specificMonstersToKill = new ArrayList<Integer>();
	private List<Integer> differentMonstersKilled = new ArrayList<Integer>();
	private Random random = new Random();
	private InformationsJeu infos;
	private SimpleAttributeSet red, black, orange,green;
	
	/**
	 * Creation d'une quete
	 * @param infos Le bloc d'informations dans le monde
	 */
	public Quest(InformationsJeu infos) {
		this.infos = infos;
		initialiserSimpleAttributes();
		try {
			startQuest();
		} catch (BadLocationException e) {}
	}
	/**
	 * Initialise les composantes simple attributes
	 */
	private void initialiserSimpleAttributes() {
		red = new SimpleAttributeSet();		
		StyleConstants.setForeground(red, Color.red);

		black = new SimpleAttributeSet();		
		StyleConstants.setForeground(black, Color.white);

		orange = new SimpleAttributeSet();		
		StyleConstants.setForeground(orange, Color.orange);
				
		green = new SimpleAttributeSet();		
		StyleConstants.setForeground(green, Color.green);
	}
	/**
	 * Commence une nouvelle quete
	 * @throws BadLocationException 
	 */
	private void startQuest() throws BadLocationException {
		int bonusQuests = niveau > 9 ? 1 : 0;
		questType = random.nextInt(TOTAL_QUESTS + bonusQuests);
		infos.getDocument().insertString(0, "\nVous avez une nouvelle quête!\n", orange);
		
		switch (questType) {
		case 0 : 
			biomeToFind = random.nextInt(10);
			
			SimpleAttributeSet couleurBiome = new SimpleAttributeSet();		
			StyleConstants.setForeground(couleurBiome, Constantes.couleurs[biomeToFind]);
			
			infos.getDocument().insertString(0, Constantes.nomBiomes[biomeToFind] + "\n", couleurBiome);
			infos.getDocument().insertString(0, "Trouver le biome ", black);			
			break;
		case 1 : 
			infos.getDocument().insertString(0, "Videz un tableau de monstres.\n", black);
			break;
		case 2 : 
			differentMonstersToKill = random.nextInt(5) + 2;

			infos.getDocument().insertString(0, " monstres differents.\n", black);
			infos.getDocument().insertString(0, differentMonstersToKill +"", red);
			infos.getDocument().insertString(0, "Tuer ", black);			
			break;
		case 3 :
			int randomLength = random.nextInt(4) + 1;			
			infos.getDocument().insertString(0, "\n", black);
			
			for (int i = 0; i < randomLength; i++) {
				boolean differentBiomeNotFound = true;
				int biome;
				do {
					biome = random.nextInt(10);
					if (specificMonstersToKill.contains(biome)) biome = random.nextInt(10);
					else differentBiomeNotFound = false;
				} while(differentBiomeNotFound);
				specificMonstersToKill.add(biome);	
				
				SimpleAttributeSet couleurBiomeMonst = new SimpleAttributeSet();		
				StyleConstants.setForeground(couleurBiomeMonst, Constantes.couleurs[biome]);
				infos.getDocument().insertString(0, Constantes.nomBiomes[biome] + " ", couleurBiomeMonst);				
			}
			infos.getDocument().insertString(0, "Tuez des monstres dans les biomes suivants : " , black);
			break;
		case 4 : 
			anyMonstersToKill = random.nextInt(6) + 3;
			
			infos.getDocument().insertString(0, " monstres\n", black);
			infos.getDocument().insertString(0, anyMonstersToKill+"", red);
			infos.getDocument().insertString(0, "Tuer ", black); 
			break;
		case 5 : 
			int epeeIdToFind = random.nextInt(18);
			itemIdToFind = epeeIdToFind+"";
			if (itemIdToFind.length() == 1) itemIdToFind = "0" + itemIdToFind;
			Item epee = new Item(itemIdToFind);
			
			SimpleAttributeSet couleurRareteEpee = new SimpleAttributeSet();
			StyleConstants.setForeground(couleurRareteEpee, epee.getCouleurRarete());
			
			infos.getDocument().insertString(0, epee.getNomItem() + "\n", couleurRareteEpee);
			infos.getDocument().insertString(0, "Trouver l'épée suivante : ", black);
			break;
		case 6 : 
			int bouclierIdToFind = random.nextInt(10) + 18;
			itemIdToFind = bouclierIdToFind + "";
			Item bouclier = new Item(itemIdToFind);
			
			SimpleAttributeSet couleurRareteBouclier = new SimpleAttributeSet();
			StyleConstants.setForeground(couleurRareteBouclier, bouclier.getCouleurRarete());
			
			infos.getDocument().insertString(0, bouclier.getNomItem() + "\n", couleurRareteBouclier);
			infos.getDocument().insertString(0, "Trouver le bouclier suivant : ", black);
			break;
		case 7 :
			//si paradis et enfer deja trouve alors ne plus donner cette quete
			//si paradis trouve alors envoyer vers enfer
			//si enfer trouve alors envoyer vers paradis
			//sinon choisir entre enfer et paradis
			break;
		}
	}
	/**
	 * Fini une quete
	 */
	public void finish() {
		try {
			infos.getDocument().insertString(0, "Quête fini!\n", green);
			reinitialiser();		
			startQuest();
		} catch (BadLocationException e) { }
	}
	/**
	 * Reinitialise les varibales apres qu'une quete soit fini
	 */
	private void reinitialiser() {
		biomeToFind = -1;
		itemIdToFind = "";
		differentMonstersToKill = -1;
		anyMonstersToKill = -1;
	}
	/**
	 * Envoye le genre de la quete
	 * @return questType Le genre de la quete
	 */
	public int getQuestType() {
		return questType;
	}
	public String getItemIdToFind() {
		return itemIdToFind;
	}
	/**
	 * Envoye le biome qu'il faut trouver
	 * @return biomeToFind Le biome
	 */
	public int getBiomeToFind() {
		return biomeToFind;
	}
	/**
	 * Ajoute un monstre dans la liste des monstres differents s'il n'existe pas deja
	 * @param biome Le biome du monstre tue
	 */
	public void addDifferentMonsterKilled(int biome) {
		if (!differentMonstersKilled.contains(biome)) {
			differentMonstersKilled.add(biome);
			differentMonstersToKill--;
			try {
				infos.getDocument().insertString(0, " monstres differents a tuer\n", black);
				infos.getDocument().insertString(0, differentMonstersToKill +"", red);
				infos.getDocument().insertString(0, "Il vous reste ", black);
			} catch (BadLocationException e) { }
			if (differentMonstersToKill == 0)
				finish();
		}
	}
	/**
	 * Envoye la liste des biomes de monstres specifiques qu'il faut tuer
	 * @return specificMonstersToKill La liste des biomes
	 */
	public List<Integer> getSpecificMonstersToKill() {
		return specificMonstersToKill;
	}
	/**
	 * Modifie la liste des monstres specifiques qu'il faut tuer
	 * @param tempList La nouvelle liste
	 */
	public void setSpecificMonstersToKill(List<Integer> tempList) {
		this.specificMonstersToKill = tempList;
		if (specificMonstersToKill.isEmpty()) finish();
		else  {
			try {
				infos.getDocument().insertString(0, "\n", black);
				for (int mons : specificMonstersToKill) {
					SimpleAttributeSet couleurBiome = new SimpleAttributeSet();		
					StyleConstants.setForeground(couleurBiome, Constantes.couleurs[mons]);					
					infos.getDocument().insertString(0, Constantes.nomBiomes[mons] + " ", couleurBiome);
				}
				infos.getDocument().insertString(0, "Il vous reste les monstres dans les biomes suivants à tuer: ", black);
			} catch (BadLocationException e) { }
		}
	}
	/**
	 * Met a jour le nombre total des monstres tues
	 */
	public void anyMonsterKilled() {
		anyMonstersToKill--;
		if (anyMonstersToKill == 0) finish();
		else
			try {
				infos.getDocument().insertString(0, " monstres a tuer\n", black);
				infos.getDocument().insertString(0, anyMonstersToKill+"", red);
				infos.getDocument().insertString(0, "Il vous reste ",black);
			} catch (BadLocationException e) { }
		
	}
	/**
	 * Definit le niveau du joueur
	 * @param niveau Le niveau
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
}
