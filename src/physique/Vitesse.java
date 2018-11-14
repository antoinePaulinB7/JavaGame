package physique;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import editeur.Outils;
import interfaces.Dessinable;
import vecteurs.FormeVecteur;
import vecteurs.Vecteur;

/**
 * Classe qui cree et definit une vitesse
 * @author Antoine
 *
 */
public class Vitesse implements Dessinable {
	
	private double composanteVitesseX, composanteVitesseY;
	private Vecteur vecteurVitesse;
	private FormeVecteur formeVecteurVitesse;
	
	/**
	 * Constructeur d'une vitesse nulle
	 */
	public Vitesse(){
		this(0.0,0.0);
	}
	/**
	 * Constructeur d'une vitesse avec des composantes
	 * @param composanteVitesseX La composante x d'une vitesse
	 * @param composanteVitesseY La composante y d'une vitesse
	 */
	public Vitesse(double composanteVitesseX, double composanteVitesseY){
		this.composanteVitesseX = composanteVitesseX;
		this.composanteVitesseY = composanteVitesseY;		
		this.vecteurVitesse = new Vecteur(composanteVitesseX,composanteVitesseY);		
		this.formeVecteurVitesse = new FormeVecteur(vecteurVitesse);
	}
	/**
	 * Dessine une vitesse
	 * @param g2d Le contexte graphique
	 * @param mat Une matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		formeVecteurVitesse.dessiner(g2d, mat);
	}
	/**
	 * Ajoute une acceleration a la vitesse
	 * @param acceleration Une acceleration
	 */
	public void ajouterAcceleration(Acceleration acceleration){
		this.composanteVitesseX += acceleration.getComposanteAccelX();
		this.composanteVitesseY += acceleration.getComposanteAccelY();
		this.vecteurVitesse = new Vecteur(composanteVitesseX,composanteVitesseY);		
		this.formeVecteurVitesse = new FormeVecteur(vecteurVitesse);
	}
	/**
	 * Converti la vitesse en chaine de caracteres
	 * @return Une chaine de caracteres
	 */
	public String toString() {
		return "(" + Outils.ajusterPrecision(composanteVitesseX, 5) + " u/s , " +  Outils.ajusterPrecision(composanteVitesseY, 5) + " u/s)";
	}
	//debut getters
	/**
	 * Envoye la composante x de la vitesse
	 * @return composanteVitesseX La composante x de la vitesse
	 */
	public double getComposanteVitesseX() {
		return composanteVitesseX;
	}
	/**
	 * Envoye la composante y de la vitesse
	 * @return composanteVitesseY La composante y de la vitesse
	 */
	public double getComposanteVitesseY() {
		return composanteVitesseY;
	}
	//fin getters
	
	//debut setters
	/**
	 * Definit la composante x de la vitesse
	 * @param composanteVitesseX La composante x d'une vitesse
	 */
	public void setComposanteVitesseX(double composanteVitesseX) {
		this.composanteVitesseX = composanteVitesseX;
	}
	/**
	 * Definit la composante y de la vitesse
	 * @param composanteVitesseY La composante y d'une vitesse
	 */
	public void setComposanteVitesseY(double composanteVitesseY) {
		this.composanteVitesseY = composanteVitesseY;
	}
	//fin setters	
}
