package entites;

/**
 * Classe qui cree et gere les mouvements d'une entite camera
 * @author Mihai
 *
 */
public class Camera extends EntitePhysique{
	
	private static final long serialVersionUID = 1L;
	private double pourcentage = 0.1;

	/**
	 * Creation de la camera
	 * @param positionX La composante position en x
	 * @param positionY La composante position en y
	 */
	public Camera(double positionX, double positionY) {
		super(positionX, positionY);
	}
	
	/**
	 * Suit le joueur a l'aide de l'algorithme "Critically damped spring"
	 * @param j Un joueur
	 */
	public void moveTo (Joueur j) {		
		setPositionX(getPositionX() + (j.getPositionX() - getPositionX()) * pourcentage);
		setPositionY(getPositionY() + (j.getPositionY() - getPositionY()) * pourcentage);
	}
}
