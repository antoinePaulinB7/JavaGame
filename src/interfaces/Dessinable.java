package interfaces;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Interface des objets dessinables
 * @author Antoine
 *
 */
public interface Dessinable {
	/**
	 * M�thode qui dessine l'objet dessinable
	 * @param g2d Le contexte graphique
	 * @param mat Une matrice de transformation
	 */
	void dessiner(Graphics2D g2d, AffineTransform mat);
	
}
