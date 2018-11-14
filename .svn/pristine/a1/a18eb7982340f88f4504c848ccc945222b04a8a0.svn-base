package entites;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import interfaces.Constantes;
import interfaces.Dessinable;
import interfaces.Enumerations.Etat;
/**
 * Classe qui gere l'animation des sprites
 * @author Antoine
 *
 */
public class Sprite extends JPanel implements Dessinable, Runnable, Serializable{

	private static final long serialVersionUID = 1L;
	private double largeur, hauteur;
	//private boolean estVisible;
	private ArrayList<ImageIcon> images;
	private ArrayList<ImageIcon> enCours;
	private ArrayList<ArrayList<ImageIcon>> frames;
	private URL url = null;
	private Etat etat;
	private String nom;
	private int indice = 0;
	private boolean enCoursDAnimation = false;
	private boolean rechercheEnCours = false;
	private int animation;
	private double positionsMains[];
	private boolean dashing = false;
	private float parametreFade;

	/**
	 * Constructeur d'un sprite
	 * @param nom Le nom du sprite
	 */
	public Sprite(String nom){
		this.nom = nom;
		this.etat = Etat.IMMOBILE;
		frames = new ArrayList<>();
		enCours = new ArrayList<>();
		images = new ArrayList<>();
		lireLesImages();
		changerAnimation();
		setBackground(Color.red);
	}
	/**
	 * Change l'animation du sprite selon l'etat
	 */
	private void changerAnimation(){
		switch(etat){
		case IMMOBILE :
			enCours = frames.get(0);
			break;
		case MOUVEMENT :
			enCours = frames.get(1);
			break;
		case SAUT :
			enCours = frames.get(2);
			break;
		case ATTAQUE_CHARGE :
			enCours = frames.get(3);
			break;
		case BLOCK :
			enCours = frames.get(4);
			break;
		case ROLLING :
			enCours = frames.get(5);
			break;
		case SPECIAL :
			enCours = frames.get(6);
			break;
		case MORT :
			enCours = frames.get(7);
			break;
		default :
			enCours = frames.get(0);
		}
		//demarrer();
	}
	/**
	 * Calcule la position des mains du sprite dépendemment de l'état
	 * @return Un tableau avec les coordonnées de chaque mains
	 */
	public double[] getPositionsMains(){

		switch(etat){
		case ATTAQUE_CHARGE :
			animation = 0;
			break;
		case BLOCK : 
			animation = 1;
			break;
		default :
			animation = 0;
		}

		switch(nom){//À compléter!!!
		case "viking" : 
			positionsMains = Constantes.positionsMainsViking[animation][indice];
			break;
		case "goblin" :
			positionsMains = Constantes.positionsMainsGoblin[animation][indice];
			break;
		case "leprechaun" :
			try {
				positionsMains = Constantes.positionsMainsLeprechaun[animation][indice];
			} catch (IndexOutOfBoundsException e) {};
			break;
		default :
			positionsMains = Constantes.positionsMainsViking[animation][indice];
		}

		return positionsMains;

	}
	/**
	 * Definit la largeur et la hautuer du sprite
	 */
	private void setDimensionImage(){
		if(!(frames.get(0).size()==0)){
			largeur = frames.get(0).get(0).getImage().getWidth(null);
			hauteur = frames.get(0).get(0).getImage().getHeight(null);
		}
	}
	/**
	 * Dessin des images du sprite
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		setDimensionImage();
		try {
			Composite tempComp = g2d.getComposite();
			if (dashing) 			
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,parametreFade));		
			g2d.drawImage(enCours.get(indice).getImage(), mat, null);
			g2d.setComposite(tempComp);
		} catch (IndexOutOfBoundsException e) {
			//System.out.println("out of bounds");
		}
	}
	/**
	 * Dessin du sprite
	 */
	public void paintComponent(Graphics g) { //temp ?
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		setDimensionImage();
		g2d.scale(5, 5);//Temporaire
		this.dessiner(g2d, new AffineTransform());

		//		try{
		//			System.out.println(indice+" "+getPositionsMains()[0]+" "+getPositionsMains()[1]);
		//		}catch(NullPointerException e){
		//			
		//		}
		//System.out.println(frames.toString());
		//System.out.println(frames.size());
		//System.out.println(frames.get(0).size());
		//System.out.println(frames.get(1).size());
		//System.out.println(frames.get(2).size());
		//System.out.println(frames.get(3).size());
	}
	/**
	 * Lis les images selon l'etat du sprite et les anime
	 */
	private void lireLesImages(){
		//Idle
		images = new ArrayList<ImageIcon>();
		String nomFichier;
		int compteur=0;
		do{
			nomFichier = "sprite_"+nom+"_idle_"+compteur+".png";
			//System.out.println(nomFichier);

			url = getClass().getClassLoader().getResource(nomFichier);
			//System.out.println(url);

			rechercheEnCours = !(url==null);

			if(rechercheEnCours){
				try {
					images.add(new ImageIcon(ImageIO.read(url)));
				} 
				catch (IOException e) {
					System.out.println("IOException lors de la lecture avec ImageIO");
				}
			}
			compteur++;
		}while(rechercheEnCours);

		frames.add(images);

		//Walk
		images = new ArrayList<ImageIcon>();
		compteur = 0;
		do{
			nomFichier = "sprite_"+nom+"_walk_"+compteur+".png";
			//System.out.println(nomFichier);

			url = getClass().getClassLoader().getResource(nomFichier);
			//System.out.println(url);

			rechercheEnCours = !(url==null);

			if(rechercheEnCours){
				try {
					images.add(new ImageIcon(ImageIO.read(url)));
				} 
				catch (IOException e) {
					System.out.println("IOException lors de la lecture avec ImageIO");
				}
			}
			compteur++;
		}while(rechercheEnCours);

		frames.add(images);

		//Jump
		images = new ArrayList<ImageIcon>();
		compteur = 0;
		do{
			nomFichier = "sprite_"+nom+"_jump_"+compteur+".png";
			//System.out.println(nomFichier);

			url = getClass().getClassLoader().getResource(nomFichier);
			//System.out.println(url);
			rechercheEnCours = !(url==null);

			if(rechercheEnCours){
				try {
					images.add(new ImageIcon(ImageIO.read(url)));
				} 
				catch (IOException e) {
					System.out.println("IOException lors de la lecture avec ImageIO");
				}
			}
			compteur++;
		}while(rechercheEnCours);

		frames.add(images);

		//Attaque
		images = new ArrayList<ImageIcon>();
		compteur = 0;
		do{
			nomFichier = "sprite_"+nom+"_attack_"+compteur+".png";
			//System.out.println(nomFichier);

			url = getClass().getClassLoader().getResource(nomFichier);
			//System.out.println(url);
			rechercheEnCours = !(url==null);
			if(rechercheEnCours){
				try {
					images.add(new ImageIcon(ImageIO.read(url)));
				} 
				catch (IOException e) {
					System.out.println("IOException lors de la lecture avec ImageIO");
				}
			}
			compteur++;
		}while(rechercheEnCours);

		frames.add(images);

		//Bloc
		images = new ArrayList<ImageIcon>();
		compteur = 0;
		do{
			nomFichier = "sprite_"+nom+"_block_"+compteur+".png";
			//System.out.println(nomFichier);

			url = getClass().getClassLoader().getResource(nomFichier);
			//System.out.println(url);
			rechercheEnCours = !(url==null);
			if(rechercheEnCours){
				try {
					images.add(new ImageIcon(ImageIO.read(url)));
				} 
				catch (IOException e) {
					System.out.println("IOException lors de la lecture avec ImageIO");
				}
			}
			compteur++;
		}while(rechercheEnCours);

		frames.add(images);

		//Roll
		images = new ArrayList<ImageIcon>();
		compteur = 0;
		do{
			nomFichier = "sprite_"+nom+"_roll_"+compteur+".png";
			//System.out.println(nomFichier);

			url = getClass().getClassLoader().getResource(nomFichier);
			//System.out.println(url);
			rechercheEnCours = !(url==null);
			if(rechercheEnCours){
				try {
					images.add(new ImageIcon(ImageIO.read(url)));
				} 
				catch (IOException e) {
					System.out.println("IOException lors de la lecture avec ImageIO");
				}
			}
			compteur++;
		}while(rechercheEnCours);

		frames.add(images);

		//Special
		images = new ArrayList<ImageIcon>();
		compteur = 0;
		do{
			nomFichier = "sprite_"+nom+"_special_"+compteur+".png";
			//System.out.println(nomFichier);

			url = getClass().getClassLoader().getResource(nomFichier);
			//System.out.println(url);
			rechercheEnCours = !(url==null);
			if(rechercheEnCours){
				try {
					images.add(new ImageIcon(ImageIO.read(url)));
				} 
				catch (IOException e) {
					System.out.println("IOException lors de la lecture avec ImageIO");
				}
			}
			compteur++;
		}while(rechercheEnCours);

		frames.add(images);
	}
	/**
	 * Le thread du sprite
	 */
	public void run() {
		while(enCoursDAnimation){
			//System.out.println(indice);
			changerAnimation();
			repaint();
			indice++;
			if(indice>enCours.size()-1){
				if(etat.equals(Etat.BLOCK)){
					indice--;
				}else{
					indice=0;
				}
			}
			try {
				Thread.sleep(1000/9);
			}
			catch (InterruptedException e) {
				System.out.println("Processus interrompu!");
			}
		}
	}
	/**
	 * Demarre l'animation du sprite
	 */
	public void demarrer() {
		if (!enCoursDAnimation ) { 
			Thread processusAnim = new Thread(this);
			processusAnim.start();
			enCoursDAnimation = true;
		}
	}
	/**
	 * Met en pause l'animation du sprite
	 */
	public void pause(){
		enCoursDAnimation = false;
	}
	//debut getters
	/**
	 * Envoye le nom du sprite
	 * @return nom Le nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Envoye la largeur du sprite
	 * @return largeur La largeur
	 */
	public double getLargeur() {
		return largeur;
	}
	/**
	 * Envoye la hauteur du sprite
	 * @return hauteur La hauteur du sprite
	 */
	public double getHauteur() {
		return hauteur;
	}
	/**
	 * Envoye l'etat du sprite
	 * @return etat L'etat
	 */
	public Etat getEtat(){
		return etat;
	}
	/**
	 * Envoye le parametre du fade
	 * @return parametreFade Le parametre du fade
	 */
	public float getParametreFade() {
		return parametreFade;
	}
	//fin getters
	
	//debut setters
	/**
	 * Definit l'etat du sprite
	 * @param etat L'etat
	 */
	public void setEtat(Etat etat){
		this.etat = etat;
		//System.out.println(etat);
		indice = 0;
		changerAnimation();
	}
	/**
	 * Definit si le joueur est entrain de faire une attaque propulsee
	 * @param dashing Si le joueur est entrain de faire une attaque propulsee
	 */
	public void setDashing(boolean dashing) {
		this.dashing = dashing;
	}
	/**
	 * Definit le parametre du fade
	 * @param parametreFade Le parametre du fade
	 */
	public void setParametreFade(float parametreFade) {
		this.parametreFade = parametreFade;
	}
	/**
	 * Definit l'etat de l'animation
	 * @param enCoursDAnimation L'etat de l'animation
	 */
	public void setEnCoursDAnimation(boolean enCoursDAnimation) {
		this.enCoursDAnimation = enCoursDAnimation;
	}
	//fin setters

}
