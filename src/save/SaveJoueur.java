package save;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import entites.Sprite;
import personnages.Personnage;

/**
 * Sauvegarde les parametres importants du joueur
 * @author Mihai
 *
 */
public class SaveJoueur implements Serializable{

	private static final long serialVersionUID = -7565536617842811571L;
	private double posX, posY;
	private long gold;
	private double exp;
	private Sprite sprite;
	private int[] stats;
	private int pointsAttribuables;
	private List<String> listeItems = new ArrayList<String>();
	private double pointsDeVie;
	private int niveau;
	private long score;
	private Personnage personnage;
	private Set<String> listeItemsDuInventaire = new LinkedHashSet<String>();
	private double carburantFusee;
	
	/**
	 * Constructeur des parametres a sauvegarder d'un joueur
	 * @param posX La composante x de la position
	 * @param posY La composante y de la position
	 * @param gold Les pieces d'or
	 * @param exp L'experience
	 * @param stats Les caracteristiques
	 * @param pointsAttribuables Les points attribuables
	 * @param listeItems La liste d'items du joueur
	 * @param pointsDeVie Les points de vie 
	 * @param niveau Le niveau
	 * @param personnage Le personnage
	 * @param listeItemsDuInventaire Une liste d'items
	 */
	public SaveJoueur (double posX, double posY, long gold, double exp, int[] stats, int pointsAttribuables, List<String> listeItems,
			double pointsDeVie, int niveau, Personnage personnage, Set<String> listeItemsDuInventaire, double carburantFusee) {
		this.posX 					= posX;
		this.posY			    	= posY;
		this.gold 					= gold;
		this.exp 					= exp;
		this.stats 					= stats;
		this.pointsAttribuables		= pointsAttribuables;
		this.pointsDeVie 			= pointsDeVie;
		this.niveau 				= niveau;
		this.personnage			    = personnage;
		this.carburantFusee			= carburantFusee;
		for(String item : listeItems) 
			this.listeItems.add(item);
		listeItemsDuInventaire.forEach(item -> this.listeItemsDuInventaire.add(item));		
	}
	/**
	 * Envoye la composante x de la position
	 * @return posX La composante x
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * Envoye la composante y de la position
	 * @return posY La composante y
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * Envoye les pieces d'or
	 * @return gold Les pieces d'or
	 */
	public long getGold() {
		return gold;
	}
	/**
	 * Envoye l'experience
	 * @return exp L'experience
	 */
	public double getExp() {
		return exp;
	}
	/**
	 * Envoye le sprite
	 * @return sprite Le sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
	/**
	 * Envoye le caracteristiques
	 * @return stats Les caracteristiques
	 */
	public int[] getStats() {
		return stats;
	}
	/**
	 * Envoye les points attribuables
	 * @return pointsAttribuables Les points attribuables
	 */
	public int getPointsAttribuables() {
		return pointsAttribuables;
	}
	/**
	 * Envoye la liste d'items
	 * @return listeItems La liste d'items
	 */
	public List<String> getListeItems() {
		return listeItems;
	}
	/**
	 * Envoye les points de vie
	 * @return pointsDeVie Les points de vie
	 */
	public double getPointsDeVie() {
		return pointsDeVie;
	}
	/**
	 * Envoye le niveau
	 * @return niveau Le niveau
	 */
	public int getNiveau() {
		return niveau;
	}
	/**
	 * Envoye le score
	 * @return score Le score
	 */
	public long getScore() {
		return score;
	}
	/**
	 * Envoye le personnage
	 * @return personnage Le personnage
	 */
	public Personnage getPersonnage() {
		return personnage;
	}
	/**
	 * Envoye la liste des items dans l'inventaire
	 * @return listeItemsDuInventaire La liste des items
	 */
	public Set<String> getListeItemsDuInventaire() {
		return listeItemsDuInventaire;
	}
	/**
	 * Envoye le carburant de la fusee
	 * @return carburantFusee Le carburant
	 */
	public double getCarburantFusee() {
		return carburantFusee;
	}
}