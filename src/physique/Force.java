package physique;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import interfaces.Dessinable;
import vecteurs.FormeVecteur;
import vecteurs.Vecteur;

/**
 * Classe qui cree et gere les forces dans le monde
 * @author Antoine
 *
 */
public class Force implements Dessinable {

	private String nom;
	private double composanteForceX, composanteForceY;
	private Vecteur vecteurForce;
	private FormeVecteur formeVecteurForce;
	
	/**
	 * Constructeur d'une force vide
	 * @param nom Le nom de la force
	 */
	public Force(String nom){
		this(nom, 0.0,0.0);
	}
	/**
	 * Constructeur d'une force 
	 * @param nom Le nom de la force
	 * @param composanteForceX La composante x de la force
	 * @param composanteForceY La composante y de la force
	 */
	public Force(String nom, double composanteForceX, double composanteForceY){
		this.nom 			   = nom;
		this.composanteForceX  = composanteForceX;
		this.composanteForceY  = composanteForceY;
		this.vecteurForce      = new Vecteur(composanteForceX,composanteForceY);
		this.formeVecteurForce = new FormeVecteur(vecteurForce);
	}
	/**
	 * Dessine la force
	 * @param g2d Le contexte graphique
	 * @param mat Une matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		formeVecteurForce.dessiner(g2d,mat);
	}
	/**
	 * Additionne tous les forces
	 * @param a Une force
	 */
	public void additionner(Force a){
		this.composanteForceX += a.composanteForceX;
		this.composanteForceY += a.composanteForceY;
		this.vecteurForce      = new Vecteur(composanteForceX,composanteForceY);
		this.formeVecteurForce = new FormeVecteur(vecteurForce);
	}
	/**
	 * Calcule l'acceleration selon l'ensemble des forces
	 * @param masse Une masse
	 * @return Une acceleration
	 */
	public Acceleration calculerAcceleration(double masse){
		Acceleration nouvAcceleration = new Acceleration(this.composanteForceX/masse,this.composanteForceY/masse);		
		return nouvAcceleration;
	}
	/**
	 * Envoye une chaine de characteres representative a la force
	 * @return Une chaine de characteres
	 */
	public String toString() {
		return nom + "(" + composanteForceX + "N, " + composanteForceY + "N)";
	}
	/**
	 * Le hash code de la force
	 * @return result Le hashcode
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}	
	/**
	 * Verifie si la force est pareil qu'un autre objet
	 * @return Si la force est pareil qu'un autre objet
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Force other = (Force) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	//debut getters
	/**
	 * Envoye la composante x de la force
	 * @return composanteForceX La composante x de la force
	 */
	public double getComposanteForceX() {
		return composanteForceX;
	}
	/**
	 * Envoye la composante y de la force
	 * @return composanteForceY La composante y de la force
	 */
	public double getComposanteForceY() {
		return composanteForceY;
	}
	/**
	 * Envoye le vecteur qui represente la force
	 * @return vecteurForce Le vecteur qui represente la force
	 */
	public Vecteur getVecteurForce() {
		return vecteurForce;
	}
	//fin getters
	
	//debut setters
	/**
	 * Definit la composante x de la force 
	 * @param composanteForceX La composante x d'une force
	 */
	public void setComposanteForceX(double composanteForceX) {
		this.composanteForceX = composanteForceX;
	}
	/**
	 * Definit la composante y de la force
	 * @param composanteForceY La composante y d'une force
	 */
	public void setComposanteForceY(double composanteForceY) {
		this.composanteForceY = composanteForceY;
	}
	/**
	 * Definit le vecteur de la force
	 * @param vecteurForce Un vecteur
	 */
	public void setVecteurForce(Vecteur vecteurForce) {
		this.vecteurForce = vecteurForce;
	}
	//fin setters
}
