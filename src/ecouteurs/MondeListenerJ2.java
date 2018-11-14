package ecouteurs;

import java.util.EventListener;

import interfaces.Enumerations.Difficulte;
import interfaces.Enumerations.Etat;
import physique.Acceleration;
import physique.Position;
import physique.Vitesse;
import procedural.Seed;
/**
 * Ecouteurs personnalises pour le joueur 2
 * @author Mihai
 *
 */
public interface MondeListenerJ2 extends EventListener{
	/**
	 * L'affichage de frames par seconde pour le joueur 2
	 * @param framesAffiches Les frames affiches
	 */
	public void framesParSecondeJ2(long framesAffiches);
	/**
	 * Les affichages sceintifiques par frame pour le joueur 2
	 * @param pos Une position
	 * @param vit Une vitesse
	 * @param accel Une acceleration
	 * @param framesAffiches Les frames affiches
	 * @param etat Un etat
	 * @param score Un score
	 */
	public void affichageScientifiqueParFrameJ2(Position pos, Vitesse vit,Acceleration accel, long framesAffiches, Etat etat, long score);
	/**
	 * Les affichages scientifiques lorsque le tableau change pour le joueur 2
	 * @param distX Une distance en x (1 distX = 50 carrees dans le monde)
	 * @param distY Une distance en y (1 distY = 50 carrees dans le monde)
	 * @param biome Un numero du biome
	 * @param danger Un numero du danger
	 * @param difficulte Une difficulte
	 * @param seed Un seed
	 * @param nbPlateformes Un nombre de plateformes
	 * @param nbMonstres Un nombre de monstres
	 * @param vitesseDeplacement Une vitesse de deplacement
	 * @param hauteurSaut Une hauteur de saut
	 */
	public void affichageScientifiqueChangementTableauJ2(int distX, int distY,int biome, int danger,Difficulte difficulte, Seed seed,
												int nbPlateformes, int nbMonstres,	double vitesseDeplacement, double hauteurSaut);
	/**
	 * Les affichages des stats du joueur 2
	 * @param experience L'experience
	 * @param hp Des points de vie
	 * @param niveau Un niveau
	 * @param pointsAttribuables Des points attribuables
	 * @param stats Des caracteristiques
	 */
	public void statsJoueur2(double experience, double hp, int niveau, int pointsAttribuables, int[] stats);
}
