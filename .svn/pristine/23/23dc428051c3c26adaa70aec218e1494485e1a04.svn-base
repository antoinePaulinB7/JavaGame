package personnages;

import java.io.Serializable;

import entites.Sprite;
/**
 * Classe qui gere les personnages
 * @author Mihai
 *
 */
public class Personnage implements Serializable {

	private static final long serialVersionUID = -1876471139131559009L;
	private double masse, largeur, hauteur;
	private int[] stats; 
	private String nom, nomClasse;
	private Sprite sprite;
	private double vieRegenereParSeconde;

	/**
	 * Constructeur d'un personnage
	 * @param nom Le nom
	 * @param sprite Le sprite
	 * @param stats Les caracteristiques
	 */
	public Personnage(String nom, String nomClasse, int[] stats) {
		this.nom = nom;
		this.nomClasse = nomClasse;
		this.stats = stats;
		initializeStats();

		sprite = new Sprite(nomClasse);
	}
	/**
	 * Initialize les caracteristiques de base du personnage
	 */
	private void initializeStats() {
		switch(nomClasse) {
		case "viking" :			
			this.masse = 130;
			this.largeur = 1;
			this.hauteur = 1;
			vieRegenereParSeconde = 0.5;
			break;
		case "thief" :
			this.masse = 60;
			this.largeur = 1;
			this.hauteur = 1;
			vieRegenereParSeconde = 0.4;
			break;
		case "cowboy" :
			this.masse = 75;
			this.largeur = 1;
			this.hauteur = 1;
			vieRegenereParSeconde = 0.2;
			break;
		case "leprechaun" :
			this.masse = 45;
			this.largeur = 1;
			this.hauteur = 1;
			vieRegenereParSeconde = 0.2;
			break;
		case "goblin" :
			this.masse = 45;
			this.largeur = 1;
			this.hauteur = 1;
			vieRegenereParSeconde = 0.4;
			break;			
		}					
	}
	/**
	 * Cree une chaine de characteres representative au personnage
	 * @return Une chaine de characteres
	 */
	public String toString() {
		return nom + ", " + nomClasse;
	}
	//debut getters
	/**
	 * Envoye les stats du personnage
	 * @return stats En ordre: Force, Chance, Agilite, Resistance
	 */
	public int[] getStats() {
		return stats;
	}
	/**
	 * Envoye le nom du sprite du personnage
	 * @return Le nom du sprite
	 */
	public String getSpriteName() {
		return sprite.getNom();
	}
	/**
	 * Le sprite du personnage
	 * @return sprite Le sprite
	 */
	public Sprite getSprite(){
		return sprite;
	}
	/**
	 * Envoye le nom du personnage
	 * @return nom Le nom 
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Envoye le nom de la classe (ex: viking)
	 * @return nomClasse Le nom 
	 */
	public String getNomClasse() {
		return nomClasse;
	}
	/**
	 * Envoye la masse du personnage
	 * @return masse La masse
	 */
	public double getMasse() {
		return masse;
	}
	/**
	 * Envoye la largeur du personnage
	 * @return largeur La largeur
	 */
	public double getLargeur() {
		return largeur;
	}
	/**
	 * Envoye la hauteur du personnage
	 * @return hauteur La hauteur
	 */
	public double getHauteur() {
		return hauteur;
	}
	/**
	 * Envoye la regeneration de vie par seconde 
	 * @return vieRegenereParSeconde La vie regenere par seconde
	 */
	public double getVieRegenereParSeconde() {
		return vieRegenereParSeconde;
	}
	//fin getters
	
	//debut setters
	/**
	 * Definit la masse du personnage
	 * @param masse La masse
	 */
	public void setMasse(double masse) {
		this.masse = masse;
	}
	/**
	 * Definit la largeur du personnage
	 * @param largeur La largeur
	 */
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}
	/**
	 * Definit la hauteur du personnage
	 * @param hauteur La hauteur
	 */
	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
	/**
	 * Definit le sprite du personnage
	 * @param sprite Le sprite
	 */
	public void setSprite(Sprite sprite){
		this.sprite = sprite;
	}
	/**
	 * Definit le nom du personnage
	 * @param nom Le nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Definit les points de vie regeneres par seconde
	 * @param vieRegenereParSeconde La regeneration de vie par seconde
	 */
	public void setVieRegenereParSeconde(double vieRegenereParSeconde) {
		this.vieRegenereParSeconde = vieRegenereParSeconde;
	}
	//fin setters
	
}
