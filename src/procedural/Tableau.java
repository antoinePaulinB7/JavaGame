package procedural;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import entites.Monstre;
import environnement.Bloc;
import environnement.Coffre;
import environnement.Plateforme;
import intelligence.Node;
import interfaces.Constantes;
import monde.Monde;

/**
 * Gere la creation d'un tableau
 * @author Antoine
 *
 */
public class Tableau {
	private Seed seed;
	private int coordonneeX, coordonneeY, grandeur = 50;
	private int[][] map;
	private Node[][] listNodes;
	private List<Plateforme> listePlateformes;
	private List<Monstre> listeMonstres = new ArrayList<Monstre>();
	private List<Coffre> listeCoffres = new ArrayList<Coffre>();
	private Rectangle2D.Double rectTableau;
	private int distanceNodes = 3;
	private int biome;
	private int danger;
	private boolean voirNavMesh = false;
	
	/**
	 * Constructeur d'un tableau
	 * @param seed Un seed
	 * @param coordX La coordonnee x
	 * @param coordY La coordonnee y
	 */
	public Tableau (Seed seed, int coordX, int coordY){
		this.seed = seed;
		this.coordonneeX = coordX;
		this.coordonneeY = coordY;	
		listNodes = new Node[grandeur][grandeur];
		for(int i = 0; i < listNodes.length; i++){
			for( int j = 0; j < listNodes[0].length; j++){
				listNodes[i][j] = new Node(i+coordX, j+coordY, true);
			}
		}
		this.seed.calculerParametres(coordonneeX, coordonneeY);
		biome = this.seed.getBiome();
		danger = this.seed.getDanger();		
		rectTableau = new Rectangle2D.Double(0, 0, grandeur, grandeur);
		int monstresParTableau = (int) Math.round(calculerDifficulte());
				

		for (int i = 0; i < monstresParTableau; i++) {
			Monstre monstre = new Monstre(Constantes.monstresParBiome[biome]);
			monstre.setValeurDifficulte(calculerDifficulte());
			
			int positionX = ThreadLocalRandom.current().nextInt(coordonneeX*grandeur, coordonneeX*grandeur + grandeur + 1);
			int positionY = ThreadLocalRandom.current().nextInt(coordonneeY*grandeur, coordonneeY*grandeur + grandeur + 1);
			monstre.setPosition(positionX, positionY);
			listeMonstres.add(monstre);
		}

		this.map = depuisFichier("tableaux/tableau"+(seed.getDispoPlateformes())+".txt");

		ArrayList<Bloc> listeBlocsTemp = new ArrayList<>();
		listePlateformes = new ArrayList<>();

		for(int j = 0; j < map.length; j++){
			for(int i = 0; i < map[0].length; i++){
				if(map[j][i] == 1){
					listeBlocsTemp.add(new Bloc(i + grandeur*coordX, j + grandeur*coordY + 1));
					if(j<49){
						listNodes[i][j+1] = new Node( i+coordX, 1+j+coordY, false);
					}
					if(i==map[0].length-1){
						listePlateformes.add(new Plateforme(listeBlocsTemp, seed.getBiome(), Constantes.effets[seed.getBiome()]));
						listeBlocsTemp.clear();
					}					
				} else if(map[j][i] == 0){
					if(!listeBlocsTemp.isEmpty()){
						listePlateformes.add(new Plateforme(listeBlocsTemp, seed.getBiome(), Constantes.effets[seed.getBiome()]));
					}
					listeBlocsTemp.clear();
				}
			}
		}
		for(int i = 0; i < listNodes.length; i++){
			for( int j = 0; j < listNodes[0].length; j++){
				for( int k = -distanceNodes; k <= distanceNodes; k++){
					for( int l = -distanceNodes; l <= distanceNodes; l++){
						if(i < 50 - distanceNodes && i > distanceNodes && j < 50 - distanceNodes && j > distanceNodes){
							if( !listNodes[i+k][j+l].isVide() /*&& !listNodes[i][j].isVide()*/){
								/*if(listNodes[i][j].getSuccessors().size()>0){
									System.out.println(listNodes[i][j].getSuccessors().size() + ", " + i + ", " + j);
								}*/
								
								listNodes[i][j].addSuccessor(listNodes[i+k][j+l]);
								//System.out.println(listNodes[25][25].getSuccessors().size());
							}
						}
					}
				}
			}
		}
		
		int nbCoffres = seed.getLoot();
		for (int i = 0; i < nbCoffres; i++){
			int indiceRandom =  ThreadLocalRandom.current().nextInt(0,listePlateformes.size()-1);
			
			int rarete = calculerRarete();
			int gold = seed.getDanger()*seed.getGold();
			int nbItem = ThreadLocalRandom.current().nextInt(0,3);
			Plateforme platTemp = listePlateformes.get(indiceRandom);
			Coffre temp = new Coffre(platTemp.getPositionX(), platTemp.getPositionY(),rarete, gold, nbItem);
			listeCoffres.add(temp);
		}
	}
	/**
	 * Methode qui affiche le background de chaque tableau
	 * @param g2d Le contexte graphique
	 * @param mat Matrice de transformations du monde
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {	
		AffineTransform matLocale = new AffineTransform(mat);
		matLocale.translate(coordonneeX*grandeur, coordonneeY*grandeur + 1);
		
		g2d.setColor(Constantes.couleurs[biome]); //choisi la couleur selon le biome
		g2d.fill(matLocale.createTransformedShape(rectTableau)); //dessine le fond d'ecran du tableau
		
		Line2D.Double ligne;
		if(voirNavMesh){
			for(int i = 0; i < listNodes.length; i++){
				for( int j = 0; j < listNodes[0].length; j++){
					for( int k = 0; k < listNodes[i][j].getSuccessors().size(); k++){
						if(!listNodes[i][j].isVide() && !listNodes[i][j].getSuccessors().get(k).isVide()){
							ligne = new Line2D.Double(listNodes[i][j].getX(), listNodes[i][j].getY(), listNodes[i][j].getSuccessors().get(k).getX(), listNodes[i][j].getSuccessors().get(k).getY());
							g2d.setColor(Color.BLACK);
							g2d.draw(matLocale.createTransformedShape(ligne));
						}
					}
				}
			}
		}
	}
	/**
	 * Calcule la rarete du contenu d'un coffre
	 * @return la rarete, un indice de 0 à 3
	 */
	private int calculerRarete(){
		return (int)Math.round(seed.getDanger()/4);
	}
	
	/**
	 * Calcule la valeur de difficulte
	 * @return La valeur de difficulte
	 */
	private double calculerDifficulte() {
		int distance = danger; 
		switch(Monde.difficulte) {
			case FACILE:    return (Math.sin(distance) + 1.0544*distance) * 0.75;
			case MOYEN:     return (Math.sin(distance) + 1.0544*distance) * 0.50;
			case DIFFICILE: return (Math.sin(distance) + 1.0544*distance) * 1.00;
			default : return 1;
		}
	}
	/**
	 * Lit un fichier du tableau et le convertit en tableau
	 * @param nomFichier Le nom du fichier
	 * @return map Un tableau
	 */
	public static int[][] depuisFichier(String nomFichier){
		ArrayList<ArrayList<Integer>> mapTemp = new ArrayList<>();

		FileReader fr = null;
		BufferedReader br = null;

		try{
			fr = new FileReader(nomFichier);
			br = new BufferedReader(fr);

			String ligneCourante;

			ArrayList<Integer> rangee = new ArrayList<>();

			while((ligneCourante = br.readLine()) != null){
				if(ligneCourante.isEmpty()) continue;

				rangee = new ArrayList<>();

				String[] valeurs = ligneCourante.trim().split(" ");

				for(String temp : valeurs){
					if(!temp.isEmpty()){
						int id =  Integer.parseInt(temp);
						rangee.add(id);
					}
				}

				mapTemp.add(rangee);
			}

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

		int largeur = mapTemp.get(0).size();
		int hauteur = mapTemp.size();
		int [][] map = new int[hauteur][largeur];

		for(int j = 0; j < hauteur; j++){
			for(int i = 0; i < largeur; i++){
				map[j][i] = mapTemp.get(j).get(i);
			}
		}

		return map;
	}
	/**
	 * Verifie si l'objet tableau est pareil qu'un autre objet
	 * @return Si l'objet tableau est pareil qu'un autre objet
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tableau))
			return false;
		Tableau other = (Tableau) obj;
		if (coordonneeX != other.coordonneeX)
			return false;
		if (coordonneeY != other.coordonneeY)
			return false;
		return true;
	}
	//debut getters
	/**
	 * Envoye la map
	 * @return map La map
	 */
	public int[][] getMap() {
		return map;
	}
	/**
	 * Envoye le biome
	 * @return Le biome
	 */
	public int getBiome(){
		return biome;
	}
	/**
	 * Envoye le danger
	 * @return Le danger
	 */
	public int getDanger(){
		return danger;
	}
	/**
	 * Envoye la liste des monstres dans le tableau
	 * @return listeMonstres La liste de monstres
	 */
	public List<Monstre> getListeMonstres() {
		return listeMonstres;
	}
	/**
	 * Envoye la liste de plateformes
	 * @return listePlateformes La liste des plateformes
	 */
	public List<Plateforme> getListePlateformes() {
		return listePlateformes;
	}
	/**
	 * Envoye la liste des nodes
	 * @return listNodes La liste des nodes
	 */
	public Node[][] getListNodes() {
		return listNodes;
	}
	/**
	 * Envoye la liste des coffres
	 * @return listeCoffres la liste des coffres
	 */
	public List<Coffre> getListeCoffres() {
		return listeCoffres;
	}
	/**
	 * Permet de savoir si le NavMesh s'affiche déjà ou non
	 * @return True si le NavMesh s'affiche déjà, false sinon
	 */
	public boolean isVoirNavMesh() {
		return voirNavMesh;
	}
	//fin getters
	
	//debut setters
	/**
	 * Definit la liste des plateformes
	 * @param listePlateformes La liste des plateformes
	 */
	public void setListePlateformes(ArrayList<Plateforme> listePlateformes) {
		this.listePlateformes = listePlateformes;
	}		
	/**
	 * Definit la liste des nodes
	 * @param listNodes Une liste de nodes
	 */
	public void setListNodes(Node[][] listNodes) {
		this.listNodes = listNodes;
	}
	/**
	 * Permet de setter si le NavMesh est afficher ou non
	 * @param voirNavMesh Boolean qui permet de savoir si le NavMesh est affiché ou non
	 */
	public void setVoirNavMesh(boolean voirNavMesh) {
		this.voirNavMesh = voirNavMesh;
	}
	//fin setters
	
}