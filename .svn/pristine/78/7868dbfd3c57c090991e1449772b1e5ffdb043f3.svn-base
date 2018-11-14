package aaplication;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bindings.BindingsClavier;
import bindings.BindingsManette;
import ecouteurs.MondeListenerJ1;
import ecouteurs.MondeListenerJ2;
import editeur.Outils;
import entites.Joueur;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import interfaces.Enumerations.Etat;
import interfaces.Enumerations.Location;
import monde.Monde;
import physique.Acceleration;
import physique.Position;
import physique.Vitesse;
import procedural.Seed;
/**
 * Démarre le jeu en mode coop
 * @author Mihai
 *
 */
public class FenetreModeCoop extends JFrame {	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FenetreAffichageScientifique fenetre1, fenetre2;
	//private FloatControl gainControl;

	/**
	 * Creation de la fenetre monde coop
	 */
	public FenetreModeCoop(Joueur[] joueurs, Difficulte difficulte, int iSeed, Controle[] controles, 
			BindingsManette[] bindings, BindingsClavier keyJoueurs, App10Plateforme menu, long score, boolean[] biomesDecouverts) {
		addWindowListener(new WindowAdapter() {
			/**
			 * Maximise la fenêtre à sa création
			 */
			public void windowActivated(WindowEvent arg0) {
				//debut
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				//fin
			}
		});
		setTitle("Jeu de plateformes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 773, 458);

		this.contentPane = new JPanel();
		setContentPane(this.contentPane);

		contentPane.setLayout(new GridLayout(1, 0));	
		
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("music/MusicTheme.wav"));
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

		// Creation de la fenêtre secondaire
		fenetre1 = new FenetreAffichageScientifique("Affichage scientifique joueur 1", Location.TOP_LEFT);
		fenetre1.setVisible(false); 

		fenetre2 = new FenetreAffichageScientifique("Affichage scientifique joueur 2", Location.TOP_RIGHT);
		fenetre2.setVisible(false);

		Seed seed = new Seed(iSeed);
		Monde monde = new Monde(joueurs, difficulte, seed, controles, bindings, keyJoueurs, menu, this, score, biomesDecouverts);
		contentPane.add(monde);	

		//début événements personnalisés joueur 1
		monde.addMondeListenerJ1(new MondeListenerJ1() {
			public void framesParSecondeJ1(long frames) {
				fenetre1.setLabelCompteurFrames(frames+"");
				fenetre2.setLabelCompteurFrames(frames+""); //meme frames pour les deux joueurs
			}
			
			public void affichageScientifiqueParFrameJ1(Position pos, Vitesse vit, Acceleration accel, Etat etat, long score) {
				//affiche des trucs scientifiques par frame
				fenetre1.setLabelsAffichageParFrame(pos.toString(), vit.toString(), accel.toString(), etat+"", score+"");
			}

			public void affichageScientifiqueChangementTableauJ1(int distX, int distY, int biome, int danger, Difficulte difficulte,
					Seed seed, int nbPlateformes, int nbMonstres, double vitesseDeplacement, double hauteurSaut) {
				//affiche des trucs scientifiques par changeent de tableau
				fenetre1.setLabelsAffichageParTableau(distX + " tableaux", distY + " tableaux", biome+"", danger+"", difficulte.toString(),	seed.toString(),
						nbPlateformes + " plateformes", nbMonstres + " monstres", Outils.ajusterPrecision(vitesseDeplacement, 5) + " u/s", hauteurSaut + "unites");
			}

			//utiliser pour le menu du level up
			public void statsJoueur1(double experience, double pointsDeVie, int niveau, int pointsAttribuables, int[] stats) {
				//affiche des trucs dans le menu lvl up
				fenetre1.setLabelAffichageStats(experience+"", Outils.ajusterPrecision(pointsDeVie, 3)+"", niveau+"", pointsAttribuables+"");
			}
		});
		//fin événements personnalisés joueur 1

		//début événements personnalisés joueur 2
		monde.addMondeListenerJ2(new MondeListenerJ2() {
			public void framesParSecondeJ2(long frames) {
				fenetre1.setLabelCompteurFrames(frames+"");
			}
			public void affichageScientifiqueParFrameJ2(Position pos, Vitesse vit, Acceleration accel,long framesAffiches, Etat etat, long score) {
				//affiche des trucs scientifiques par frame
				fenetre2.setLabelsAffichageParFrame(pos.toString(), vit.toString(), accel.toString(), etat +"", score+"");
			}

			public void affichageScientifiqueChangementTableauJ2(int distX, int distY, int biome, int danger, Difficulte difficulte,
					Seed seed, int nbPlateformes, int nbMonstres, double vitesseDeplacement, double hauteurSaut) {
				//affiche des trucs scientifiques par changeent de tableau
				fenetre2.setLabelsAffichageParTableau(distX + " tableaux", distY + " tableaux", biome+"", danger+"", difficulte.toString(), seed.toString(), 
						nbPlateformes + " plateformes", nbMonstres + " monstres", Outils.ajusterPrecision(vitesseDeplacement, 5) + " u/s", hauteurSaut + "unites");
			}

			//utiliser pour le menu du level up
			public void statsJoueur2(double experience, double pointsDeVie, int niveau, int pointsAttribuables, int[] stats) {
				//affiche des trucs dans le menu lvl up
				fenetre2.setLabelAffichageStats(experience+"", Outils.ajusterPrecision(pointsDeVie, 3)+"", niveau+"", pointsAttribuables+"");
			}
		});
		//fin événements personnalisés joueur 2
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/**
	 * Ferme touts les fenetres lies a au mode de jeu coop
	 */
	public void terminate() {
		fenetre1.setVisible(false);
		fenetre1.dispose();
		fenetre2.setVisible(false);
		fenetre2.dispose();
		setVisible(false);
		dispose();
	}
	/**
	 * Ferme ou ouvre les fenetres de l'affichage scientifique
	 * @param afficher La condition d'affichage
	 */
	public void afficherFenetreAffichageScientifique(boolean afficher) {
		fenetre1.setVisible(afficher);
		fenetre2.setVisible(afficher);
	}
}