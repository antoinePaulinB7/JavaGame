package vecteurs;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 * Classe qui crée et dessine une réprésentation de la forme d'un vecteur donnée
 * @author Mihai
 */

public class FormeVecteur  {	
	private Vecteur vecteur;				  //le vecteur qu'on veut representer visuellement
	private Line2D.Double corps, traitDeTete; //pour tracer la flèche
	private double angleTete = 0.5;           //angle entre les deux segments formant la tete de fleche
	private double longueurTete = 0.3;        //longueur des segments formant la tete (en pixels)
	private double origX=0, origY=0;		  //le point de depart ou sera trace le vecteur
	private double scale = 1;
	private double ratio;
	private boolean dessinerTete = true;

	/**
	 * Constructeur qui initiliase seulement un vecteur
	 * @param v Le vecteur
	 */
	public FormeVecteur(Vecteur v) {
		vecteur = v; //on memorise une reference sur le vecteur qu'on represente
		this.origX = 0; //v.getX();
		this.origY = 0; //v.getY();
	}
	/**
	 * Constructeur qui initialise un vecteur et son origine
	 * @param v Le vecteur
	 * @param origX La composante des abscisses
	 * @param origY La composante des ordonnées 
	 */
	public FormeVecteur(Vecteur v, double origX, double origY) {
		this(v);
		this.origX = origX;
		this.origY = origY;
	}
	/**
	 * Constructeur qui initialise un vecteur en utilisants des composantes x et y et qui initialise son origine
	 * @param x La composante du vecteur en x
	 * @param y La composante du vecteur en y
	 * @param origX La composante des abscisses
	 * @param origY La composante des ordonnées
	 */
	public FormeVecteur(double x, double y, double origX, double origY) {		
		vecteur = new Vecteur(x, y);
		this.origX = origX;
		this.origY = origY;
		dessinerTete = false;
	}
	/**
	 * Constructeur qui initialise l'origine du vecteur et une mise à l'échelle
	 * @param origX La composante des abscisses
	 * @param origY La composante des ordonnées
	 * @param scale	La mise à l'échelle
	 */
	public FormeVecteur(double origX, double origY, double scale) {
		this.origX = origX;
		this.origY = origY;
		this.scale = scale;
		vecteur = new Vecteur(0, 0);
	}
	/**
	 * Cree les formes geometriques qui constituent le vecteur (à 0,0)
	 */
	private void creerRepresentationGeometrique() {
		//on cree le vecteur a l'origine. Il sera dessiné avec une translation si son origine est ailleurs
		corps = new Line2D.Double(origX, origY, vecteur.getX(), vecteur.getY());

		double moduleVec  = vecteur.module();
		ratio = (moduleVec - longueurTete)/moduleVec;
		traitDeTete = new Line2D.Double( vecteur.getX(), vecteur.getY(), vecteur.getX()*ratio, vecteur.getY()*ratio);
	}
	/**
	 * Dessine le vecteur sous la forme d'une flèche orientée
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {	

		creerRepresentationGeometrique();		
		AffineTransform matLocale = new AffineTransform(mat);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);


		matLocale.scale(scale, scale);
		//matLocale.translate(origX, origY);

		g2d.draw(matLocale.createTransformedShape(corps)); //ligne formant le vecteur lui-meme

		if (dessinerTete) {
			matLocale.rotate(angleTete/2, vecteur.getX(), vecteur.getY());
			g2d.draw(matLocale.createTransformedShape(traitDeTete)); //un des deux traits qui forment la tete du vecteur	

			matLocale.rotate(-angleTete, vecteur.getX(), vecteur.getY());
			g2d.draw(matLocale.createTransformedShape(traitDeTete));		
		}
	}// fin

	/**
	 * Méthode qui définit l'angle de la tête du vecteur
	 * @param angleTete L'angle de la tête du vecteur
	 */
	public void setAngleTete(double angleTete) {
		this.angleTete = angleTete;
	}
	/**
	 * Méthode qui définit l'origine en x et en y du vecteur
	 * @param origX L'origine en x
	 * @param origY L'origine en y
	 */
	public void setOrigineXY(double origX, double origY)  {
		this.origX = origX;
		this.origY = origY;
	}
	/**
	 * Méthode qui définit la longueur de la tête du vecteur
	 * @param longueurTete La longueur de la tête
	 */
	public void setLongueurTete(double longueurTete) {
		this.longueurTete = longueurTete;
	}
	/**
	 * Méthode qui définit le vecteur
	 * @param vecteur Le vecteur
	 */
	public void setVecteur(Vecteur vecteur) {
		this.vecteur = vecteur;
	}
}
