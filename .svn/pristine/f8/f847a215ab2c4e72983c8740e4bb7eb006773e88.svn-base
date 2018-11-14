package physique;

import editeur.Outils;
import entites.EntitePhysique;
import vecteurs.FormeVecteur;

/**
 * Classe qui cree et definit une position
 * @author Antoine
 *
 */
public class Position {
	
	private double composantePositionX, composantePositionY;
	
	/**
	 * Constructeur d'une position nulle
	 */
	public Position() {
		this(0.0,0.0);
	}
	/**
	 * Constructeur d'une position avec des composantes
	 * @param composantePositionX La composante x d'une position
	 * @param composantePositionY La composante y d'une position
	 */
	public Position(double composantePositionX, double composantePositionY) {
		this.composantePositionX = composantePositionX;
		this.composantePositionY = composantePositionY;
	}
	/**
	 * Ajoute la vitesse a la position
	 * @param vitesse Une vitesse
	 */
	public void ajouterVitesse(Vitesse vitesse) {
		this.composantePositionX += vitesse.getComposanteVitesseX();
		this.composantePositionY += vitesse.getComposanteVitesseY();
	}
	/**
	 * Additionne une vitesse a une position et la renvoye
	 * @param vit Une vitesse
	 * @return Une position
	 */
	public Position additionnerUneVitesse(Vitesse vit) {
		return (new Position(this.composantePositionX + vit.getComposanteVitesseX(),
							 this.composantePositionY + vit.getComposanteVitesseY()));
	}
	/**
	 * Permet d'afficher une fleche qui vas dans la direction d'une autre entite physique
	 * @param ep Une entite physique
	 * @return Une forme vecteur 
	 */
	public FormeVecteur flecheVersAutreEntite(EntitePhysique ep) {
		return (new FormeVecteur(ep.getPositionX(), ep.getPositionY(), this.composantePositionX, this.composantePositionY));
	}
	/**
	 * Calcule la distance entre deux positions
	 * @param position La position avec laquelle calculer la distance
	 * @return distance La distance
	 */
	public double distance(Position position){
		double distanceX = (position.getComposantePositionX()-this.composantePositionX);
		double distanceY = (position.getComposantePositionY()-this.composantePositionY);
		double distance = Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
		return distance;
	}
	/**
	 * Teste si deux positions sont egales
	 * @param obj Une position
	 * @param EPSILON test de precision
	 * @return S'ils sont egales
	 */
	public boolean equals(Object obj, double EPSILON) {
	    if(this == obj)
	    	return true;	    
	    if(obj == null)
	    	return false;	    
	    if(!(obj instanceof Position))
	    	return false;	    
	    Position other = (Position) obj;	    
	    //Comparaison des valeurs x,y  en utilisant la precision de EPSILON modulee par la valeur a comparer
	    if(Math.abs(composantePositionX - other.composantePositionX) > Math.abs(composantePositionX)*EPSILON)
	    	return false;	    
	    if(Math.abs(composantePositionY - other.composantePositionY) > Math.abs(composantePositionY)*EPSILON)
	    	return false;	    
	    return true;
	  }
	/**
	 * Convertit la position en chaine de caracteres
	 * @return Une chaine de caracteres
	 */
	@Override
	public String toString() {
		return "(" +  Outils.ajusterPrecision(composantePositionX, 5) + " u, " +  Outils.ajusterPrecision(composantePositionY, 5) + " u)";
	}
	//debut getters
	/**
	 * Envoye la composante x de la position
	 * @return composantePositionX La composante x de la position
	 */
	public double getComposantePositionX() {
		return composantePositionX;
	}	
	/**
	 * Envoye la composante y de la position
	 * @return composantePositionY La composante y de la position
	 */
	public double getComposantePositionY() {
		return composantePositionY;
	}
	//fin getters
	
	//debut setters
	/**
	 * Definit la composante x de la position 
	 * @param composantePositionX La composante x d'une position
	 */
	public void setComposantePositionX(double composantePositionX) {
		this.composantePositionX = composantePositionX;
	}
	/**
	 * Definit la composante y de la position
	 * @param composantePositionY La composante y d'une position
	 */
	public void setComposantePositionY(double composantePositionY) {
		this.composantePositionY = composantePositionY;
	}	
}
