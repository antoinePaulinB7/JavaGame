package bindings;

import java.io.Serializable;

/**
 * Classe qui garde en memoire les bindings de la manette d'un joueur
 * @author Mihai
 *
 */
public class BindingsManette implements Serializable {
	
	private static final long serialVersionUID = -1471208149983455571L;
	private boolean joystick;
	private int[] mouvements = new int[4];
	private boolean[] sticks = new boolean[4];
	
	/**
	 * Constructeur des bindings de base
	 * @param joystick
	 */
	public BindingsManette(boolean joystick) {
		this.joystick = joystick;
		setDefault();
	}
	/**
	 * Modifie l'action d'un bouton de la manette 
	 * @param index L'index des mouvements dans cette ordre:  0: saut - 1: bloc - 2: attaque - 3: special
	 * @param stick Si c'est un stick ou un bouton
	 * @param button L'indice du bouton qui est appuye
	 */
	public void addBinding(int index, boolean stick, int button) {
		mouvements[index] = button;
		sticks[index] = stick;
	}
	/**
	 * S'il y a une action qui n'a pas de touche alors on met les bindings par defaut
	 */
	public void setDefaultIfEmpty() {
		for(int i = 0; i < mouvements.length; i++) 
			if(mouvements[i] == -1) 
				setDefault();
	}
	/**
	 * Definit les touches de base
	 */
	private void setDefault() {
		for(int i = 0; i < mouvements.length; i++)
			mouvements[i] = i;			
	}
	/**
	 * Verifie si le joueur utilise le joystick ou le les fleches
	 * @return Si le joueur utilise le joystick ou le les fleches
	 */
	public boolean isJoystick() {
		return joystick;
	}
	/**
	 * Envoye les bindings des actions
	 * @return mouvements Les bindings pour chaque action
	 */
	public int[] getMouvements() {
		return mouvements;
	}
	/**
	 * Envoye le tableau qui verifie quelle genre de touche est utilise
	 * @return Le tableau qui verifie quelle genre de touche est utilise
	 */
	public boolean[] getSticks() {
		return sticks;
	}
	/**
	 * Definit si le joystick est utilise
	 * @param joystick Si le joystick est utilise
	 */
	public void setJoystick(boolean joystick) {
		this.joystick = joystick;
	}
	/**
	 * Change la touche pour une action
	 * @param pos La position de l'action
	 * @param mouvements La nouvelle touche de l'action
	 */
	public void setMouvements(int pos, int mouvements) {
		this.mouvements[pos] = mouvements;
	}
	/**
	 * Definit le tableau qui qui verifie quelle genre de touche est utilise
	 * @param sticks Le tableau
	 */
	public void setSticks(boolean[] sticks) {
		this.sticks = sticks;
	}
}
