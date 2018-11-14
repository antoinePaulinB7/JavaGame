package bindings;

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * Classe qui garde en memoire les controles du clavier pour les deux joueurs
 * @author Mihai
 *
 */
public class BindingsClavier implements Serializable{
	
	private static final long serialVersionUID = -1581637536665678968L;
												//Controles joueur 1
	private final int KEY_JOUEURS_DEFALUT[][] = {{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, 
												  KeyEvent.VK_A, KeyEvent.VK_V, KeyEvent.VK_B},
												//Controles joueur 2
												 {KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, 
												  KeyEvent.VK_LEFT, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2}};
	private int keyJoueurs[][];
	
	/**
	 * Constructeurs avec des bindings de base
	 */
	public BindingsClavier() {
		keyJoueurs = KEY_JOUEURS_DEFALUT;
	}
	/**
	 * Ajoute un nouveau binding d'une action pour un joueur specifique
 	 * @param nbJoueur Le numero du joueur entre 0 et 1
	 * @param action L'action a changer de binding
	 * @param key La nouvelle touche de l'action
	 */
	public void addBinding(int nbJoueur, int action, int key) {
		keyJoueurs[nbJoueur][action] = key;
	}
	/**
	 * Envoye la touche pour une action selon le joueur choisi
	 * @param nbJoueur Le joueur choisi
	 * @param action L'action
	 * @return La touche a utiliser
	 */
	public int getKeyJoueurs(int nbJoueur, int action) {
		return keyJoueurs[nbJoueur][action];
	}
	/**
	 * S'il y a une action sans touche et que le jeu est en jeu, on met les bindings par defaut pour un joueur choisi
	 * @param nbJoueur Le joueur
	 */
	public void setDefaultIfEmpty(int nbJoueur) {
		for(int i = 0; i < keyJoueurs[nbJoueur].length; i++)
			if(keyJoueurs[nbJoueur][i] == 0) 
				keyJoueurs[nbJoueur] = KEY_JOUEURS_DEFALUT[nbJoueur];	
	}
}
