package save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import bindings.BindingsClavier;
import bindings.BindingsManette;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import interfaces.Enumerations.ModeDeJeu;
/**
 * Classe qui lit les sauvegardes
 * @author Olivier
 * @author Mihai
 *
 */
public class LireSauvegarde {

	/**
	 * Lit un fichier de sauvegarde et retourne un object Sauvegarde
	 * @param nomSauvegarde Le nom de la sauvegarde
	 * @return sauvegarde La sauvegarde
	 */
	public static Sauvegarde loaderSauvegarde(String nomSauvegarde){
		SaveJoueur[] joueurs = new SaveJoueur[1];
		long score = 0;
		int seed = 0;
		BindingsClavier keyJoueurs = null;
		ModeDeJeu mode = ModeDeJeu.SOLO;
		Difficulte difficulte = Difficulte.MOYEN;
		Controle[] controles = new Controle[1];
		BindingsManette[] bindings = new BindingsManette[1];
		boolean[] biomesDecouverts = new boolean[10];
		Sauvegarde sauvegarde;
		SaveJoueur j1 = null, j2 = null;
		Controle c1 = null, c2 = null;
		BindingsManette b1 = null, b2 = null;

		Object object;

		final String NOM_FICHIER = nomSauvegarde + ".txt";
		ObjectInputStream ois = null;

		File fichierDeTravail = new File( NOM_FICHIER);

		try {
			ois = new ObjectInputStream(new FileInputStream("sauvegarde/"+fichierDeTravail));	

			score = (long)ois.readObject();
			seed = (int)ois.readObject();
			mode =  (ModeDeJeu)ois.readObject();
			difficulte = (Difficulte)ois.readObject();
			c1 = (Controle)ois.readObject();

			object = ois.readObject();
			if (object.getClass() == Controle.class) c2 = (Controle)object;
			else b1 = (BindingsManette)object;
			if (b1 == null) b1 = (BindingsManette)ois.readObject();

			object = ois.readObject();
			if (object.getClass() == BindingsManette.class) b2 = (BindingsManette)object;
			else j1 = (SaveJoueur)object;
			if (j1 == null) j1 = (SaveJoueur)ois.readObject();
			
			object = ois.readObject();			
			if (object.getClass() == SaveJoueur.class) j2 = (SaveJoueur)object;		
			else keyJoueurs = (BindingsClavier)object;
			if (keyJoueurs == null) keyJoueurs = (BindingsClavier)ois.readObject();
			
			ois.readObject();
			
			biomesDecouverts = (boolean[]) ois.readObject();
		} 	
		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"L'objet lu est d'une classe inconnue");
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fichier  " + fichierDeTravail.getAbsolutePath() + " introuvable!");
			System.exit(0);
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur rencontree lors de la lecture");
			e.printStackTrace();
			System.exit(0);
		} 
		finally {
			//on exécutera toujours ceci, erreur ou pas
			try { 
				ois.close();
			}
			catch (IOException e) { 
				JOptionPane.showMessageDialog(null,"Erreur rencontrée lors de la fermeture!"); 
			}
		}//fin finally
		if (j2 != null) {
			joueurs = new SaveJoueur[2];
			joueurs[1] = j2;
		}
		joueurs[0] = j1;

		if (c2 != null) {
			controles = new Controle[2];
			controles[1] = c2;
		}
		controles[0] = c1;

		if (b2 != null) {
			bindings = new BindingsManette[2];
			bindings[1] = b2;
		}
		bindings[0] = b1;

		sauvegarde = new Sauvegarde(nomSauvegarde,joueurs, seed, mode, difficulte, controles, bindings, score, keyJoueurs, biomesDecouverts);
		return sauvegarde;
	}
}
