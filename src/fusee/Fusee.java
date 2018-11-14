package fusee;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaces.Constantes;
import interfaces.Dessinable;
import physique.Force;
import physique.Position;
import physique.Vitesse;
/**
 * Classe qui gere la fusee et son affichage
 * @author Olivier
 *
 */
public class Fusee implements Dessinable, Serializable {
	
	private static final long serialVersionUID = -5982177169393414517L;
	private double carburant;
	private double vitesseInitiale, vitesse;
	private Vitesse vitesse2;
	private double masseTotaleInitiale, masseTotale;
	private double pousseeMoteur;
	private double debit;
	private double x, y;
	private ArrayList<Particule> part;
	private Force poussee;
	private URL url = null;
	private ImageIcon image = null;
	private double largeurFusee = 0, hauteurFusee = 0;
	private final double MASSE_FUSEE = 100000;
	private final double VITESSE_CARBURANT = 60;
	private final double VITESSE_MAX = 0.2;
	
	/**
	 * Constructeur de fusee vide
	 */
	public Fusee() { }
	/**
	 * Constructeur d'une fusee
	 * @param carburant Le carburant
	 * @param vitesse La vitesse
	 * @param massePersonne La masse de la personne
	 * @param pousseeMoteur La poussee du moteur
	 * @param debit Le debit
	 * @param x La composante en x
	 * @param y La composante en y
	 */
	public Fusee(double carburant, double vitesse, double massePersonne, double pousseeMoteur, double debit, double x, double y){
		this.carburant = carburant;
		this.vitesse = vitesse;
		this.vitesseInitiale = vitesse;
		this.masseTotale = massePersonne + MASSE_FUSEE + carburant/10;
		this.masseTotaleInitiale = massePersonne + MASSE_FUSEE + carburant/10;
		this.pousseeMoteur = pousseeMoteur;
		this.debit = debit;
		part = new ArrayList<Particule>();
		this.x = x;
		this.y = y;
		poussee = new Force("Poussée", 0, 0);
		vitesse2 = new Vitesse( 0 , this.vitesse);

		url = getClass().getClassLoader().getResource("item_28.png");
		try {
			image = new ImageIcon(ImageIO.read(url));
		} 
		catch (IOException e) {
			System.out.println("IOException lors de la lecture avec ImageIO");
		}
	}
	/**
	 * Dessine la fusee
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		setDimensionFusee();
		AffineTransform matLocale = new AffineTransform(mat);
		AffineTransform matImage = new AffineTransform(mat);
		matImage.translate(x, y+1);
		//System.out.println(sprite.getX() + ", " + sprite.getY());
		//System.out.println("Fusee, " + x + ", " + y);
		//matLocale.translate(x, y);
		AffineTransform matParticules = new AffineTransform(matLocale);
		dessinerParticules(g2d, matParticules);	
		matImage.scale(0.0625, -0.0625);
		matImage.translate(-image.getIconWidth()/3, -3*image.getIconHeight()/4);
		try {
			g2d.drawImage(image.getImage(), matImage, null);		
		} catch (IndexOutOfBoundsException e) {
			System.out.println("out of bounds");
		}
		g2d.setColor(Color.BLACK);
		
		
	}
	/**
	 * Dessine les particules
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	private void dessinerParticules(Graphics2D g2d, AffineTransform mat){
		AffineTransform matLocale = new AffineTransform(mat);
		for(int i = 0; i <= part.size() - 1;i++){
			part.get(i).dessiner(g2d, matLocale);
			//System.out.println("Particule dessiné");
		}
	}
	/**
	 * Met a jour les composantes de la fusee
	 */
	public void update(boolean utiliseFusee){
		if(utiliseFusee){	
			if(carburant>0){
				masseTotale -= debit/Constantes.FPS;
				vitesse = formuleTsiolkovsky( vitesseInitiale, masseTotaleInitiale, masseTotale, pousseeMoteur );
				carburant -= debit/Constantes.FPS;
				ajouterParticules();
				poussee = new Force("Poussée", 0, debit*VITESSE_CARBURANT);
			}else{
				vitesse = 0;
				poussee = new Force("Poussée", 0, 0);
			}
			if(vitesse>VITESSE_MAX){
				vitesse = VITESSE_MAX;
			}
			
		}else{
			vitesse = 0;
			poussee = new Force("Poussée", 0, 0);
		}
		vitesse2 = new Vitesse(0, vitesse);
		//System.out.println(vitesse);
		for(int i = 0; i <= part.size() - 1;i++){
			if(part.get(i).update()){
				part.remove(i);
				//System.out.println("Particule enlevée");
			}
		}
	}
	/**
	 * Formule utilisé pour le calcul de la vitesse de la fusée
	 * @param vi Vitesse initiale
	 * @param mi Masse initiale
	 * @param m Masse finale
	 * @param u Energie potentielle
	 * @return La vitesse de la fusee
	 */
	private double formuleTsiolkovsky(double vi, double mi, double m, double u){
		return(u * Math.log(mi/m) + vi);
	}
	/**
	 * Ajoute les particules
	 */
	private void ajouterParticules(){
		for(int i = 0; i < 10; i++){
			part.add(new Particule(x-largeurFusee/6, y,0.05-0.1*Math.random()/*debit*/,-0.1, (int)(50-Math.random()*50)));
		}
		//System.out.println("Particule ajouté");
	}
	/**
	 * Envoye la vitesse de la fusee
	 * @return vitesse La vitesse
	 */
	public double getVitesse(){
		return vitesse;
	}
	/**
	 * Envoye la poussee de la fusee
	 * @return poussee La poussee
	 */
	public Force getPoussee() {
		return poussee;
	}
	/**
	 * Definit la poussee de la fusee
	 * @param poussee Une poussee
	 */
	public void setPoussee(Force poussee) {
		this.poussee = poussee;
	}
	/**
	 * Setter pour la position en x
	 * @param x
	 */
	public void setX(double x){
		this.x = x;
	}
	/**
	 * Setter pour la position en y
	 * @param y
	 */
	public void setY(double y){
		this.y = y;
	}
	/**
	 * Setter pour la position
	 * @param pos
	 */
	public void setPosition(Position pos){
		this.x = pos.getComposantePositionX();
		this.y = pos.getComposantePositionY();
	}
	/**
	 * Set les dimensions de la fusee selon l'image
	 */
	public void setDimensionFusee(){
		largeurFusee = image.getImage().getWidth(null)*0.0625;
		setHauteurFusee(image.getImage().getHeight(null)*0.0625);
	}
	/**
	 * Getter pour hauteurFusee
	 * @return hauteurFusee
	 */
	public double getHauteurFusee() {
		return hauteurFusee;
	}
	/**
	 * Setter pour hauteurFusee
	 * @param hauteurFusee
	 */
	public void setHauteurFusee(double hauteurFusee) {
		this.hauteurFusee = hauteurFusee;
	}
	/**
	 * Getter pour largeurFusee
	 * @return the largeurFusee
	 */
	public double getLargeurFusee() {
		return largeurFusee;
	}
	/**
	 * Setter pour largeurFusee
	 * @param largeurFusee 
	 */
	public void setLargeurFusee(double largeurFusee) {
		this.largeurFusee = largeurFusee;
	}
	/**
	 * Getter pour vitesse2
	 * @return vitesse2
	 */
	public Vitesse getVitesse2() {
		return vitesse2;
	}
	public double getCarburant() {
		return carburant;
	}
	/**
	 * Setter pour vitesse2
	 * @param vitesse2
	 */
	public void setVitesse2(Vitesse vitesse2) {
		this.vitesse2 = vitesse2;
	}
}
