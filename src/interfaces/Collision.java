package interfaces;

import java.awt.geom.Rectangle2D;

import entites.EntitePhysique;
import environnement.Environnement;

/**
 * Interface qui gere les collisions
 * @author Antoine
 *
 */
public interface Collision {
	/**
	 * Envoye la zone de collision
	 * @return Une zone de collision
	 */
	Rectangle2D.Double getZoneCollision();
	/**
	 * Envoye les pieds
	 * @return Les pieds
	 */
	Rectangle2D.Double getPieds();
	/**
	 * Teste s'il y a eu une collision avec une entite
	 * @param ent Une entite physique
	 * @return s'il y a eu contact
	 */
	boolean contact(EntitePhysique ent);
	/**
	 * Gere l'effet du contact selon un objet
	 * @param obj Un objet
	 */
	void effetContact(Object obj);
	/**
	 * Teste s'il ya eu contact avec un environnement
	 * @param env Un environnement
	 * @param cas Le cas de collision
	 * @return S'il ya eu contact
	 */
	boolean contactPieds(Environnement env, int cas);
	
}
