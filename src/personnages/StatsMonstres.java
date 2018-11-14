package personnages;

import entites.Sprite;

/**
 * Classe qui definit les stats des monstres et leurs sprites
 * @author Mihai
 *
 */
public class StatsMonstres {
	private double vitesseDeDeplacement, hauteurDeSaut, masse, largeur, hauteur, vieRegenereParSeconde;
	private Sprite sprite = null;
	
	/**
	 * Constructeur des caracteristiques d'un monstre selon le biome
	 * @param biome Le biome 
	 */
	public StatsMonstres (int biome) {
		//tout modifier quand les monstres selon dessines (effacer ce commentaire apres que la tache est faite)
		switch (biome) { //stats du monstre selon le biome
		case 0://foret	
			sprite = new Sprite("goblin");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 50;
			largeur = 4;
			hauteur = 4;
			setVieRegenereParSeconde(0.3);
			break;
		case 1://neige
			sprite = new Sprite("yeticlops");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 2.25;
			hauteur = 1.5;
			setVieRegenereParSeconde(0.4);
			break;
		case 2://feu
			sprite = new Sprite("flamelizard");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 1.5;
			hauteur = 1.5;
			setVieRegenereParSeconde(0.5);
			break;
		case 3://desert
			sprite = new Sprite("golem");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 2;
			hauteur = 2;
			setVieRegenereParSeconde(0.4);
			break;
		case 4://roche
			sprite = new Sprite("rock");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 1;
			hauteur = 1;
			setVieRegenereParSeconde(1);
			break;
		case 5://foret //shadow
			sprite = new Sprite("fantome");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 4;
			hauteur = 4;
			setVieRegenereParSeconde(2);
			break;
		case 6://death
			sprite = new Sprite("skeleton");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 1.5;
			hauteur = 1.5;
			setVieRegenereParSeconde(0.1);
			break;
		case 7://chateau
			sprite = new Sprite("bigknight");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 20;
			largeur = 4;
			hauteur = 5;
			setVieRegenereParSeconde(0.7);
			break;
		case 8://bonbon
			sprite = new Sprite("crazyguimauve");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 1;
			hauteur = 1;
			setVieRegenereParSeconde(0.6);
			break;
		case 9://annees 70
			sprite = new Sprite("golem");
			vitesseDeDeplacement = 0.1;
			hauteurDeSaut = 0.3;
			masse = 70;
			largeur = 1;
			hauteur = 1;
			setVieRegenereParSeconde(0.5);
			break;
		}
		sprite.demarrer();
	}
	/**
	 * Envoye la vitesse de deplacement
	 * @return vitesseDeDeplaceent La vitesse de deplacement
	 */
	public double getVitesseDeDeplacement() {
		return vitesseDeDeplacement;
	}
	/**
	 * Envoye la hauteur de saut 
	 * @return hauteurDeSaut La hauteur de saut
	 */
	public double getHauteurDeSaut() {
		return hauteurDeSaut;
	}
	/**
	 * Envoye la masse
	 * @return masse La masse
	 */
	public double getMasse() {
		return masse;
	}
	/**
	 * Envoye la largeur
	 * @return largeur La largeur
	 */
	public double getLargeur() {
		return largeur;
	}
	/**
	 * Envoye la hauteur
	 * @return hauteur La hauteur
	 */
	public double getHauteur() {
		return hauteur;
	}
	/**
	 * Envoye le sprite 
	 * @return sprite Le sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
	/**
	 * Envoye la vie regenere par seconde
	 * @return vieRegenereParSeconde La vie regenere par seconde
	 */
	public double getVieRegenereParSeconde() {
		return vieRegenereParSeconde;
	}
	/**
	 * Definit la vie regenere par seconde
	 * @param vieRegenereParSeconde La vie regenere par seconde
	 */
	public void setVieRegenereParSeconde(double vieRegenereParSeconde) {
		this.vieRegenereParSeconde = vieRegenereParSeconde;
	}
}
