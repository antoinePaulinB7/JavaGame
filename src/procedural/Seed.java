package procedural;

import java.io.Serializable;

/**
 * Classe qui gere le fonctionneent du seed
 * @author Antoine 
 * 
 */
public class Seed implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int[] tableau = new int[]{0,0,0,0,0};
	private int compteur = 0;
	private int depart = 0;
	private int danger = 0;
	private int hauteur = 0;
	private String seedActuel; 
	//private int x = 0;
	//private int y = 0;
	
	/**
	 * Constructeur du seed qui prend une chaine de characteres
	 * @param seed Un seed
	 */
	public Seed(String seed){
		compteur = Integer.parseInt(seed);
		depart = compteur;

		for(int i = 0; i<tableau.length; i++ ){

			try {
				tableau[i] = Integer.parseInt(seed.charAt(seed.length()-5+i)+"");
			} catch (StringIndexOutOfBoundsException e) {
				tableau[i]=0;
				//e.printStackTrace();
			} catch (NumberFormatException e){
				tableau[i]=0;
				//e.printStackTrace();
			}

		}

		danger = 0;
		hauteur = 0;
	}
	/**
	 * Constructeur du seed qui prend un entier
	 * @param seed Un seed
	 */
	public Seed(int seed){
		this(seed+"");
	}
	/**
	 * Calcule les nouvelles parametres
	 * @param x La composante x
	 * @param y La composante y
	 */
	public void calculerParametres (int x, int y){
		//this.x = x;
		//this.y = y;

		compteur = depart + /*768*/14675*x + 3578*Math.abs(y);
				
		seedActuel = compteur+"";

		hauteur = y;
		danger = (int) Math.min(Math.round(Math.sqrt(x*x + y*y)), 10);

		for(int i = 0; i < tableau.length; i++ ){
			try {
				tableau[i] = Integer.parseInt(seedActuel.charAt(seedActuel.length()-5+i)+"");
			} catch (StringIndexOutOfBoundsException e) {
				tableau[i]=0;
			} catch (NumberFormatException e){
				tableau[i]=0;
			}
		}
	}
	/**
	 * Envoye une chaine de characteres representative au seed
	 * @return Une chaine de characteres
	 */
	public String toString() {
		return "(" + depart + ")";
				//"Seed [tableau=" + Arrays.toString(tableau) + ", compteur=" + compteur + ", depart=" + depart
				//+ ", danger=" + danger + ", hauteur=" + hauteur + ", x=" + x + ", y=" + y + "]";
	}
	/**
	 * Envoye le seed de depart
	 * @return depart Le seed
	 */
	public int getSeedDepart() {
		return depart;
	}
	/**
	 * Le seed actuel
	 * @return seedActuel Le seed
	 */
	public String getSeedActuel() {
		return seedActuel;
	}
	/**
	 * Envoye le tableau
	 * @return tableau Le tableau
	 */
	public int[] getTableau(){
		return tableau;
	}
	/**
	 * Envoye le compteur
	 * @return compteur Le compteur
	 */
	public int getCompteur(){
		return compteur;
	}
	/**
	 * Envoye la valeur de danger
	 * @return danger La valeur de danger
	 */
	public int getDanger() {
		return danger;
	}
	/**
	 * Envoye la valeur de hauteur
	 * @return hauteur La valeur de hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}
	/**
	 * Envoye la valeur du biome
	 * @return La valeur du biome
	 */
	public int getBiome(){
		return tableau[0];
	}
	/**
	 * Envoye la valeur du boss
	 * @return La valeur du boss
	 */
	public int getBoss(){
		return tableau[1];
	}
	/**
	 * Envoye la valeur du loot
	 * @return La valeur du loot
	 */
	public int getLoot(){
		return tableau[2];
	}
	/**
	 * Envoye la valeur des plateformes disponibles
	 * @return La valeur des plateformes disponibles
	 */
	public int getDispoPlateformes(){
		return tableau[3];
	}
	/**
	 * Envoye la valeur du gold
	 * @return La valeur du gold
	 */
	public int getGold(){
		return tableau[4];
	}
}
