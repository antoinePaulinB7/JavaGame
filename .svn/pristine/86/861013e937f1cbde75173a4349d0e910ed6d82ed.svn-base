package personnages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

import entites.EntitePhysique;
import entites.Monstre;
import entites.Sprite;
import environnement.Environnement;
import interfaces.Dessinable;
import physique.Position;
import physique.Vitesse;

/**
 * Classe qui gere le mouvement d'un projectile
 * @author Mihai
 *
 */
public class Balle extends JPanel implements Dessinable {

	private static final long serialVersionUID = 1L;
	private Position position;
	private Vitesse vitesse;
	private double largeur = 0.4, hauteur = 0.15;
	private Ellipse2D.Double balle;
	private Color couleur = Color.gray;
	private boolean alive = true;
	private double dmg;
	private Sprite sprite;
	private long lifespan = 10000; //en ms

	/**
	 * Constructeur d'une balle
	 * @param posX La composante x de la position
	 * @param posY La composante y de la position
	 * @param largeur Une largeur
	 * @param hauteur Une hauteur
	 * @param vitX La vitesse en x
	 * @param dmg Les degats faits
	 * @param couleur La couleur
	 */
	public Balle (double posX, double posY, double largeur, double hauteur, double vitX, double dmg, Color couleur) {
		position        = new Position(posX, posY);
		this.largeur    = largeur;
		this.hauteur    = hauteur;
		this.dmg        = dmg;
		this.couleur    = couleur;;
		vitesse = new Vitesse(vitX, 0);
		startLifespan();
	}
	/**
	 * 
	 * @param posX La composante en x de la position
	 * @param posY La composante en y de la position
	 * @param largeur Une largeur
	 * @param hauteur Une hauteur
	 * @param vitX La vitesse en x
	 * @param dmg Les degats faits
	 * @param projectileNom Le nom du projectile
	 */
	public Balle (double posX, double posY, double largeur, double hauteur, double vitX, double dmg, String nomProjectile) {
		position     = new Position(posX, posY);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.dmg     = dmg;
		this.sprite  = new Sprite(nomProjectile);
		vitesse      = new Vitesse(vitX, 0);		
		sprite.demarrer();
		startLifespan();
	}
	/**
	 * Dessin la balle
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		AffineTransform matLocale = new AffineTransform(mat);
		balle = new Ellipse2D.Double(position.getComposantePositionX(),position.getComposantePositionY(), largeur, hauteur);
		if(sprite != null){			
			matLocale.translate(position.getComposantePositionX(), position.getComposantePositionY());
			if (vitesse.getComposanteVitesseX() > 0) 
				matLocale.scale(largeur/4, hauteur/4);	
			else 
				matLocale.scale(-largeur/4, hauteur/4);			
			sprite.dessiner(g2d, matLocale);
		}else{				
			g2d.setColor(couleur);
			g2d.fill(matLocale.createTransformedShape(balle));
		}
	}
	
	/**
	 * Commence la duree de vie d'une balle
	 */
	public void startLifespan() {
		Thread lifeThread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(lifespan);					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				alive = false;
			}
			
		}); lifeThread.start();
	}
	/**
	 * Met a jour la balle
	 */
	public void update() {
		position.ajouterVitesse(vitesse);
	}
	/**
	 * Verifie si la balle est toujours vivante
	 * @return Si la balle est toujours vivante
	 */
	public boolean isAlive() {
		return alive;
	}
	/**
	 * Tue la balle 
	 * @param alive Si la balle reste en vie
	 */
	public void killBullet(boolean alive) {
		this.alive = alive;
	}
	/**
	 * Detecte la collision de la balle avec un environnement
	 * @param rectEnv La zone de collision de l'environnement
	 */
	public void collisionEnvironnement(Rectangle2D.Double rectEnv) {
		Area aireBalle = new Area();
		if (balle != null) aireBalle = new Area(balle);
		if (aireBalle.intersects(rectEnv)) 
			alive = false; //tue la balle
	}
	/**
	 * Detecte la collision de la balle avec un monstre
	 * @param monstre Un monstre
	 */
	public void collisionMonstre(EntitePhysique entite) {
		Area aireBalle = new Area();
		if (balle != null) aireBalle = new Area(balle);
		if (aireBalle.intersects(entite.getZoneCollision())) {
			entite.enleverPointsDeVie(dmg);
			entite.setHorsCombat(false);
			if (sprite != null && sprite.getNom().equals("mediumcoin")) {
				Random rand = new Random();
				if (rand.nextInt(3) == 1)
					((Monstre)entite).addGold();
			}
			alive = false; //tue la balle
			System.out.println("Le monstre a pris " + dmg + " , il lui reste " + entite.getPointsDeVie() + " points de vie");
		}			
	}
	/**
	 * Verifie si la balle est proche d'une plateforme
	 * @param plateforme Une plateforme
	 * @return Si la balle est proche d'une plateforme
	 */
	public boolean autourYPlateforme(Environnement plateforme) {
		double yPlateforme = plateforme.getZoneCollision().getY();
		if (position.getComposantePositionY() > yPlateforme &&
			position.getComposantePositionY() < yPlateforme+1)
			return true;
		return false;
	}
	/**
	 * Envoye le sprite
	 * @return sprite Le sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
	/**
	 * Definit le sprite
	 * @param sprite Le sprite
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
