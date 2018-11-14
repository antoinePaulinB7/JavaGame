package item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaces.Dessinable;

/**
 * Classe qui gere les fonctionnalitees des items
 * @author Antoine
 *
 */
public class Item implements Dessinable {
	
	private final int GOLD_PER_WORTH = 3;
	private ImageIcon image;
	private String nomFichier;
	private URL url = null;
	private Area zoneCollision;
	private double largeur, hauteur;
	private String id;
	private String nomItem;
	private int worth, gold;
	private Color couleurRarete;
	private double bonus[] = new double[4]; // Force : Agilite : Chance : Resistance
	
	/**
	 * Constructeur d'un item selon un nom
	 * @param id Le id du item
	 */
	public Item(String id){
		this.setId(id);
		nomFichier = "item_"+id+".png";
		url = getClass().getClassLoader().getResource(nomFichier);

		try {
			image = new ImageIcon(ImageIO.read(url));
		} 
		catch (IOException e) {
			System.out.println("IOException lors de la lecture avec ImageIO");
		}
		lireDescription(Integer.parseInt(id));
		
		if (worth >= 0 && worth <= 2)
			couleurRarete = Color.white;
		else if (worth > 2 && worth <= 4)
			couleurRarete = Color.green;
		else if (worth > 4 && worth <= 6)
			couleurRarete = new Color(128, 0, 128);
		else if (worth > 6 && worth <= 10)
			couleurRarete = new Color(255, 223, 0);
	}
	/**
	 * Definit les dimensions de l'image
	 */
	private void setDimensionImage(){
		largeur = image.getImage().getWidth(null);
		hauteur = image.getImage().getHeight(null);
	}
	/**
	 * Dessine le item
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		setDimensionImage();
		AffineTransform matLocale = new AffineTransform(mat);
		try {
			g2d.drawImage(image.getImage(), matLocale, null);				
		} catch (IndexOutOfBoundsException e) {
			//System.out.println("out of bounds");
		}
	}
	/**
	 * Lis la description d'un item
	 * @param id Le id
	 */
	private void lireDescription(int id) {	
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			fr = new FileReader("ItemList.txt");
			br = new BufferedReader(fr);
			
			String ligneCourante = null;			
			br.readLine(); //skip toujours la premiere ligne
			
			for (int i = 0; i < id + 1; i++) 
				ligneCourante = br.readLine();

			String[] valeurs = ligneCourante.trim().split(" ");	
			nomItem = valeurs[1];
			
			for (int i = 0; i < nomItem.length(); i++) 
				if(nomItem.charAt(i) == '_') {
					String temp1 = nomItem.substring(0, i);
					String temp2 = nomItem.substring(i+1);
					nomItem = temp1 + " " + temp2;
				}
			
			worth = Integer.parseInt(valeurs[2]);
			gold = worth * GOLD_PER_WORTH;
			
			for (int i = 0; i < 4; i++) 
				bonus[i] = Integer.parseInt(valeurs[i+3]);

		}catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//debut getters
	/**
	 * Envoye la largeur du item
	 * @return largeur La largeur
	 */
	public double getLargeur() {
		return largeur;
	}
	/**
	 * Envoye la hauteur du item
	 * @return hauteur La hauteur
	 */
	public double getHauteur() {
		return hauteur;
	}
	/**
	 * Envoye le rectangle de la zone de collision
	 * @return zoneCollision La zone de collision
	 */
	public Area getZoneCollision() {
		return zoneCollision;
	}
	/**
	 * Envoye les bonus du item dans l'ordre suivante: att : def : hp : speed : saut : dotDamage : dotTimer
	 * @return bonus Les bonus
	 */
	public double[] getBonus() {
		return bonus;
	}
	/**
	 * Envoye le nom du item
	 * @return nomIte Le nom du item
	 */
	public String getNomItem() {
		return nomItem;
	}
	/**
	 * Envoye le id du item
	 * @return id Le id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Envoye les pieces d'or que le item vaut
	 * @return gold Les pieces d'or
	 */
	public int getGold() {
		return gold;
	}
	/**
	 * Envoye la couleur de la rarete du item
	 * @return couleurRarete La couleur de rarete
	 */
	public Color getCouleurRarete() {
		return couleurRarete;
	}
	//fin getters
	
	//debut setters
	/**
	 * Definit la largeur du item
	 * @param largeur Une largeur
	 */
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}
	/**
	 * Definit la hauteur du item
	 * @param hauteur Une hauteur
	 */
	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
	/**
	 * Definit la zone de collision du item
	 * @param zoneCollision Une zone de collision
	 */
	public void setZoneCollision(Area zoneCollision) {
		this.zoneCollision = zoneCollision;
	}
	/**
	 * Definit les bonus du item dans l'ordre suivante: att : def : hp : speed : saut : dotDamage : dotTimer
	 * @param bonus Les bonus
	 */
	public void setBonus(double bonus[]) {
		this.bonus = bonus;
	}
	/**
	 * Definit le id du item
	 * @param id Le id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Definit les pieces d'or que le item vaut
	 * @param gold Les pieces d'or
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}
	//fin setters	
	/**
	 * Envoye les informations du item en chaine de caracteres
	 * @return Le informations du item
	 */
	public String toString() {
		return "<html>Nom: " + nomItem + " --- Gold : " + gold + " --- Force: " + bonus[0] + "<br>Agilité " + bonus[1] + " --- Chance: " + bonus[2] + " --- Résistance: " + bonus[3] + "</html>"; 
	}
	/**
	 * Envoye le hash code d'un item
	 * @return result Le hash code
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bonus);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomItem == null) ? 0 : nomItem.hashCode());
		return result;
	}
	/**
	 * Verifie si deux items sont equivalents
	 * @return Si deux items sont equivalents
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (!Arrays.equals(bonus, other.bonus))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomItem == null) {
			if (other.nomItem != null)
				return false;
		} else if (!nomItem.equals(other.nomItem))
			return false;
		return true;
	}
	
}