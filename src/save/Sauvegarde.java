package save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

import bindings.BindingsClavier;
import bindings.BindingsManette;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import interfaces.Enumerations.ModeDeJeu;

/**
 * Object qui garde en memoire tout les param�tres important d'une sauvegarde
 * @author Olivier
 * @author Mihai
 *
 */
public class Sauvegarde implements Serializable{
	
	private static final long serialVersionUID = -7815499402404413813L;
	private SaveJoueur[] joueurs;
	private int seed;
	private ModeDeJeu mode;
	private Difficulte difficulte;
	private Controle[] controles;
	private BindingsManette[] bindings;
	private long score;
	private String nomSauvegarde;
	private BindingsClavier keyJoueurs;
	private boolean[] biomesDecouverts;

	
	/**
	 * Creation d'une sauvegarde
	 * @param nomSauvegarde Le nom de la sauvegarde
	 * @param joueurs Les joueurs
	 * @param seed Le seed
	 * @param mode Le mode de jeu
	 * @param difficulte La difficulte
	 * @param controles Les controles
	 * @param bindings Les bidnings de la manette
	 * @param score Le score
	 * @param keyJoueurs Les bindings du clavier
	 * @param biomesDecouverts Les biomes deja decouverts
	 */
	public Sauvegarde(String nomSauvegarde, SaveJoueur[] joueurs, int seed, ModeDeJeu mode, Difficulte difficulte, 
			Controle[] controles, BindingsManette[] bindings, long score, BindingsClavier keyJoueurs, boolean[] biomesDecouverts){
		this.nomSauvegarde = nomSauvegarde;
		this.joueurs = joueurs;
		this.seed = seed;
		this.mode = mode;
		this.difficulte = difficulte;
		this.controles = controles;
		this.bindings = bindings;
		this.score = score;
		this.keyJoueurs = keyJoueurs;
		this.biomesDecouverts = biomesDecouverts;
	}
	/**
	 * M�thode qui sauvegarde un objet Sauvegarde
	 */
	public void sauvegarder(){
		final String NOM_FICHIER = nomSauvegarde + ".txt";
		File fichierDeTravail = new File( NOM_FICHIER);
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("sauvegarde/"+fichierDeTravail));		
			oos.writeObject(score);
			oos.writeObject(seed);
			oos.writeObject(mode);
			oos.writeObject(difficulte);
			oos.writeObject(controles[0]);
			if (controles.length == 2)
				oos.writeObject(controles[1]);
			oos.writeObject(bindings[0]);
			if (bindings.length == 2)
				oos.writeObject(bindings[1]);
			oos.writeObject(joueurs[0]);
			if (joueurs.length == 2) 
				oos.writeObject(joueurs[1]);	
			oos.writeObject(keyJoueurs);
			oos.writeObject(nomSauvegarde);
			oos.writeObject(biomesDecouverts);
		}
		catch (IOException e) {
			System.out.println("Erreur � l'�criture:");
			e.printStackTrace();
		}
		finally {
			//on ex�cutera toujours ceci, erreur ou pas
		  	try { 
		  		oos.close();  
		  	}
		    catch (IOException e) { 
		    	JOptionPane.showMessageDialog(null,"Erreur rencontr�e lors de la fermeture!"); 
		    }
		}//fin finally	
		
	}
	/**
	 * Envoye les joueurs
	 * @return joueurs Les joueurs
	 */
	public SaveJoueur[] getJoueurs() {
		return joueurs;
	}
	/**
	 * Envoye le seed
	 * @return seed Le seed
	 */
	public int getSeed() {
		return seed;
	}
	/**
	 * Envoye le mode de jeu
	 * @return mode Le mode de jeu
	 */
	public ModeDeJeu getMode() {
		return mode;
	}
	/**
	 * Envoye la difficulte
	 * @return difficulte La difficulte
	 */
	public Difficulte getDifficulte() {
		return difficulte;
	}
	/**
	 * Envoye les controles
	 * @return controles Les controles
	 */
	public Controle[] getControles() {
		return controles;
	}
	/**
	 * Envoye les bindings de la manette
	 * @return bindings Les bindings manette
	 */
	public BindingsManette[] getBindings() {
		return bindings;
	}
	/**
	 * Envoye le nom de la sauvegarde
	 * @return nomSauvegarde Le nom de la sauvegarde
	 */
	public String getNomSauvegarde() {
		return nomSauvegarde;
	}
	/**
	 * Envoye le score
	 * @return score Le score
	 */
	public long getScore() {
		return score;
	}
	/**
	 * Envoye les bindings du clavier
	 * @return keyJoueurs Le bindings du clavier
	 */
	public BindingsClavier getKeyJoueurs() {
		return keyJoueurs;
	}
	/**
	 * Envoye les biomes deja decouverts
	 * @return biomesDecouverts Les biomes deja decouverts
	 */
	public boolean[] getBiomesDejaDecouverts() {
		return biomesDecouverts;
	}
}
