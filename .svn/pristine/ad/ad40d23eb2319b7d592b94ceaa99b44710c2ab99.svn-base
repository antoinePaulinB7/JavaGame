package physique;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import interfaces.Dessinable;
import vecteurs.FormeVecteur;
import vecteurs.Vecteur;

/**
 * Classe qui cree et definit une acceleration
 * @author Antoine
 *
 */
public class Acceleration implements Dessinable {
	
	private double composanteAccelX, composanteAccelY;
	private Vecteur vecteurAcceleration;
	private FormeVecteur formeVecteurAcceleration;
	
	/**
	 * Constructeur de l'acceleration nulle
	 */
	public Acceleration(){
		this(0.0,0.0);
	}
	/**
	 * Constructeur qui prend des composantes en parametre
	 * @param composanteAccelX La composante x d'une acceleration
	 * @param composanteAccelY La composante y d'une acceleration
	 */
	public Acceleration(double composanteAccelX, double composanteAccelY){
		this.composanteAccelX         = composanteAccelX;
		this.composanteAccelY         = composanteAccelY;		
		this.vecteurAcceleration      = new Vecteur(composanteAccelX,composanteAccelY);		
		this.formeVecteurAcceleration = new FormeVecteur(vecteurAcceleration);
	}
	/**
	 * Dessine le vecteur acceleration
	 * @param g2d Le contexte graphique
	 * @param mat Une matrice de transformation
	 */
	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		formeVecteurAcceleration.dessiner(g2d, mat);
	}
	/**
	 * Divise les composantes de l'acceleration par un nombre reel et envoye cette nouvelle acceleration
	 * @param nb Un nombre reel
	 * @return Une acceleration
	 */
	public Acceleration diviser(double nb) {
		return (new Acceleration(this.composanteAccelX/nb, this.composanteAccelY/nb));
	}
	/**
	 * Envoye la composante x de l'acceleration
	 * @return composanteAccelX La composante x de l'acceleration
	 */
	public double getComposanteAccelX() {
		return composanteAccelX;
	}
	/**
	 * Envoye la composante y de l'acceleration
	 * @return composanteAccelY La composante y de l'acceleartion
	 */
	public double getComposanteAccelY() {
		return composanteAccelY;
	}
	/**
	 * Definit la composante x de l'acceleration
	 * @param composanteAccelX La composante x d'une acceleration
	 */
	public void setComposanteAccelX(double composanteAccelX) {
		this.composanteAccelX = composanteAccelX;
	}
	/**
	 * Definit la composante y de l'acceleration
	 * @param composanteAccelY La composante y d'une acceleration
	 */
	public void setComposanteAccelY(double composanteAccelY) {
		this.composanteAccelY = composanteAccelY;
	}
	/**
	 * Envoye une chaine de characteres representative a l'acceleration
	 * @return Une chaine de characteres
	 */
	public String toString() {
		return "(" + composanteAccelX + " u/s^2, " + composanteAccelY + " u/s^2)";
	}
	
}
