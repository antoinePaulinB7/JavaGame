package fusee;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;



import interfaces.Dessinable;
import physique.Position;
import physique.Vitesse;

/**
 * Classe qui gere une particule et son affichage
 * @author Olivier
 *
 */
public class Particule implements Dessinable {
	private Position position;
	private Vitesse vitesse;
	private int duree;
	private Ellipse2D.Double cercle;
	private Color swage;
	private double rayon = 0.5;

	/**
	 * Constructeur de la particule
	 * @param x La composante en x de la position
	 * @param y La composante en y de la position
	 * @param vitesseX La composante en x de la vitesse
	 * @param vitesseY La composante en y de la vitesse
	 * @param duree La duree de vie
	 */
	public Particule(double x, double y, double vitesseX, double vitesseY, int duree){
		position = new Position (x, y);
		vitesse = new Vitesse (vitesseX, vitesseY);	
		this.duree = duree;
		swage = new Color(225, (int)(Math.random()*215), 0);
	}
	/**
	 * Dessine la particule
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		AffineTransform matLocale = new AffineTransform(mat);
		cercle = new Ellipse2D.Double(position.getComposantePositionX(),position.getComposantePositionY(), rayon, rayon);
		matLocale.translate(0.75, 0);
		//matLocale.translate(vitesseX, vitesseY);
		Color coulDebut = g2d.getColor();
		g2d.setColor(swage);
		g2d.fill(matLocale.createTransformedShape(cercle));
		g2d.setColor(coulDebut);
	}
	/**
	 * Met a jour la particule pendant qu'elle est vivante
	 * @return Si la particule est toujours vivante
	 */
	public boolean update(){
		position.ajouterVitesse(vitesse);
		//System.out.println(positionX);
		duree--;
		if(duree<=0){
			return true;
		}
		return false;
	}

}
