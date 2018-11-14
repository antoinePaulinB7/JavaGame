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
 * Démarre le jeu en mode Solo
 * @author Mihai
 *
 */
public class FenetreModeSolo extends JFrame{

	private static final long serialVersionUID = 1L;
	private FenetreAffichageScientifique fenetreAff;
	private JPanel contentPane;
	//private FloatControl gainControl;

	/**
	 * Creation de la fenetre mode solo
	 */
	public FenetreModeSolo(Joueur[] joueurs, Difficulte difficulte, int iSeed, Controle[] controles, BindingsManette[] bindings, BindingsClavier keyJoueurs, App10Plateforme menu, long score, boolean[] biomesDecouverts) {
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
		
		 // Creation de la fenêtre affichage scientifique
		fenetreAff = new FenetreAffichageScientifique("Affichage scientifique", Location.TOP_LEFT);
		fenetreAff.setVisible(false); 
			
		Seed seed = new Seed(iSeed);
		
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		Monde monde = new Monde(joueurs, difficulte, seed, controles, bindings, keyJoueurs, menu, this, score, biomesDecouverts);
		contentPane.add(monde);
		
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
		
		//début événements personnalisés
		monde.addMondeListenerJ1(new MondeListenerJ1() {
			public void framesParSecondeJ1(long frames) {
				fenetreAff.setLabelCompteurFrames(frames+"");
			}
			public void affichageScientifiqueParFrameJ1(Position pos, Vitesse vit, Acceleration accel, Etat etat, long score) {
				//affiche des trucs scientifiques par frame
				fenetreAff.setLabelsAffichageParFrame(pos.toString(), vit.toString(), accel.toString(), etat + "", score+"");
			}

			public void affichageScientifiqueChangementTableauJ1(int distX, int distY, int biome, int danger, Difficulte difficulte,
			Seed seed, int nbPlateformes, int nbMonstres, double vitesseDeplacement, double hauteurSaut) {
				//affiche des trucs scientifiques par changeent de tableau
				fenetreAff.setLabelsAffichageParTableau(distX + " tableaux", distY + " tableaux", biome+"", danger+"", difficulte.toString(), seed.toString(),
						nbPlateformes + " plateformes", nbMonstres + " monstres", Outils.ajusterPrecision(vitesseDeplacement, 5) + " u/s", hauteurSaut + "unites");
			}
			
			//utiliser pour le menu du level up
			public void statsJoueur1(double experience, double pointsDeVie, int niveau, int pointsAttribuables, int[] stats) {
				//affiche des trucs dans le menu lvl up
				fenetreAff.setLabelAffichageStats(experience+"", pointsDeVie+"", niveau+"", pointsAttribuables+"");
			}
		});
		//fin événements personnalisés
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/**
	 * Ferme touts les fenetres lies a au mode de jeu solo
	 */
	public void terminate() {
		fenetreAff.setVisible(false);
		fenetreAff.dispose();
		setVisible(false);						
		dispose();		
	}
	/**
	 * Ferme ou ouvre la fenetre affichage scientifique
	 * @param afficher La condition d'affichage
	 */
	public void afficherFenetreAffichageScientifique(boolean afficher) {
		fenetreAff.setVisible(afficher);
	}
}
