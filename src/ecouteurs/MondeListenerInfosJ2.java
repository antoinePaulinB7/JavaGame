package ecouteurs;

import java.util.EventListener;
/**
 * Ecouteur des informations du joueur 2
 * @author Mihai
 *
 */
public interface MondeListenerInfosJ2 extends EventListener {
	/**
	 * Detecte les changements d'experience du joueur 2
	 * @param niveau Le niveau
	 * @param experience L'experience
	 * @param pointsDeVie Les points de vie
	 */
	public void experienceAjouteJ2(int niveau, double experience, double pointsDeVie);
	/**
	 * Detecte les changements des points de vie du joueur 2
	 * @param pointsDeVie Les points de vie
	 */
	public void updateVieJ2(double pointsDeVie);

}
