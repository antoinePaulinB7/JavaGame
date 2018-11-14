package physique;
/**
 * Classe qui gere les calculs de l'energie
 * @author Olivier
 *
 */
public class Energie {
	
	/**
	 * Permet de calculer l'�nergie cin�tique d'un objet
	 * @param masse Masse de l'objet
	 * @param vitesse Vitesse de l'objet
	 * @return Retourne l'�nergie cin�tique de l'objet
	 */
	public static double energieCinetique(double masse, double vitesse){
		double vit = Math.abs(vitesse);
		return 0.5*masse*vit*vit;
	}	
}
