package environnement;

import java.awt.geom.Rectangle2D;

import entites.EntitePhysique;
import interfaces.Collision;
import interfaces.Constantes;
import physique.Force;

/**
 * Classe qui cree l'environemment
 * @author Antoine
 *
 */
public class Environnement implements Collision {
	private final double COEF_DE_BASE = 1;
	private Rectangle2D.Double zoneCollision;
	private double forceFriction;
	private double coef = COEF_DE_BASE;
	
	/**
	 * Gere l'effet du contact d'un objet avec l'environnement
	 */
	public void effetContact(Object obj) {
		if(obj.getClass()==EntitePhysique.class)
			((EntitePhysique) obj).ajouterForce(new Force("Force normale", 0.0, ((EntitePhysique) obj).getMasse()*Constantes.K_GRAVITE));
	}
	/**
	 * Envoye la zone de collision
	 * @return zoneCollision La zone de collision
	 */
	public Rectangle2D.Double getZoneCollision() {
		return zoneCollision;
	}
	/**
	 * Methode non utilise 
	 * @return null
	 */
	public Rectangle2D.Double getPieds() {
		return null;
	}
	/**
	 * Methode non utilise (l'environnement n'a pas de pieds)
	 * @return false
	 */
	public boolean contactPieds(Environnement env, int cas) {
		return false;
	}
	/**
	 * Teste si l'environneent est en contact avec une entite
	 * @return false
	 */
	public boolean contact(EntitePhysique ent) {
		return false;
	}
	/**
	 * Definit l'effet du contact d'un objet avec l'environnement
	 * @param obj L'objet
	 */
	public void effetContactBloc(Object obj) {
		EntitePhysique ent = (EntitePhysique)obj;
		setForceFriction(0);
		double forceNormale = Constantes.K_GRAVITE*ent.getMasse();		
		
		setForceFriction(ent.getVitesse().getComposanteVitesseX() > 0 ? -coef*forceNormale : coef*forceNormale);	
	}
	/**
	 * Envoye la force de friction
	 * @return forceFriciton La force de friction
	 */
	public double getForceFriction() {
		return forceFriction;
	}
	/**
	 * Definit la force de fricition
	 * @param forceFriction La force de friction
	 */
	public void setForceFriction(double forceFriction) {
		this.forceFriction = forceFriction;
	}	
}
