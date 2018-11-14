package interfaces;

import java.io.Serializable;
/**
 * Interface des enumerations 
 * @author Mihai 
 *
 */
public interface Enumerations {
	/**
	 * Enumeration des difficultes
	 */
	public enum Difficulte {
		FACILE, MOYEN, DIFFICILE, GODMODE
	}
	/**
	 * Enumeration des controles
	 */
	public enum Controle { 
		CLAVIER, MANETTE
	}
	/**
	 * Enumeration des effets
	 */
	public enum Effet{
		GAZON, GLACE, SABLE, VOLCANIQUE, PIERRE, MONTAGNE, SHADOW, DONUT, TASSE, BOUE 
	}
	/**
	 * Enumeration des etats
	 */
	public enum Etat implements Serializable{
		IMMOBILE, MOUVEMENT, SAUT, ATTAQUE_CHARGE, ATTAQUE_NORMAL, MORT, DASH, BLOCK, DESCENDRE, ROLLING,
		SPECIAL, RECHARGE
	}
	/**
	 * Enumeration des directions
	 */
	public enum Direction {
		GAUCHE, DROITE, BAS, HAUT, AUCUNE, RESPAWN
	}
	/**
	 * Enumeration des mode de jeu
	 */
	public enum ModeDeJeu {
		SOLO, COOP
	}
	/**
	 * Enumeration de la location
	 */
	public enum Location {
		TOP_LEFT, TOP_RIGHT
	}
	/**
	 * Enumeration des actions de l'inventaire
	 */
	public enum InventoryAction {
		EQUIP, SELL
	}
}
