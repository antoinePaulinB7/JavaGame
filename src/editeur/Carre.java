package editeur;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;
/**
 * Classe qui gere la creation d'un bloc
 * @author Antoine
 *
 */
public class Carre implements Dessinable{
	private Rectangle2D.Double carre;
	private boolean selected = false;
	
	/**
	 * Constructeur d'un carre
	 * @param x La composante x 
	 * @param y La composante y
	 */
	public Carre(double x, double y){
		carre = new Rectangle2D.Double(x,y,1,1);
	}
	/**
	 * Envoye le carre
	 * @return carre Le carre
	 */
	public Rectangle2D.Double getCarre(){
		return carre;
	}
	/**
	 * Dessine le carre
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		Color temp = g2d.getColor();
		if(selected){
			g2d.setColor(Color.green);
		}else{
			g2d.setColor(Color.blue);
		}
		g2d.fill(mat.createTransformedShape(carre));
		g2d.setColor(temp);
	}
	/**
	 * Envoye si le carre est choisi
	 * @return selected Si le carre est choisi
	 */
	public boolean isSelected(){
		return selected;
	}
	/**
	 * Definit si le carre est choisi
	 * @param selected Si le carre est choisi
	 */
	public void setSelected(boolean selected){
		this.selected = selected;
	}
}
