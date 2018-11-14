package temporaire;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import aaplication.FenetreAffichageScientifique;
import bindings.BindingsManette;
import entites.Joueur;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import interfaces.Enumerations.Location;
import personnages.Personnage;
public class FenetreModeVSObsolete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JSplitPane splitPane;
	private FenetreAffichageScientifique fenetreAff1, fenetreAff2;

	/**
	 * Demmare l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					Controle[] controles1 = {Controle.CLAVIER};
					Controle[] controles2 = {Controle.MANETTE};

					int[] stats1 = {1, 1, 1, 1};
					int[] stats2 = {1, 1, 1, 1};
					Personnage personnages1 = new Personnage("bob",  "cowboy", stats1);
					Personnage personnages2 = new Personnage("boby", "viking", stats2);
					Joueur[] joueurs1 = {new Joueur(25,0, personnages1)};
					Joueur[] joueurs2 = {new Joueur(25,0, personnages2)};
					
					
					BindingsManette bindings1[] = {new BindingsManette(true)};
					BindingsManette bindings2[] = {new BindingsManette(true)};

					FenetreModeVSObsolete frame = new FenetreModeVSObsolete(joueurs1, joueurs2, Difficulte.MOYEN, 1234, controles1, controles2, bindings1, bindings2);
					frame.setVisible(true);
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

					//frame.setUndecorated(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creation de la fenetre mode versus
	 */
	public FenetreModeVSObsolete(Joueur[] joueurs1, Joueur[] joueurs2, Difficulte difficulte, int seed, Controle[] controles1, Controle[] controles2, BindingsManette[] bindings1, BindingsManette[] bindings2) {
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

		contentPane.setLayout(new GridLayout(1, 2, 0, 0));

		// Creation de la fenêtre affichage scientifique
		fenetreAff1 = new FenetreAffichageScientifique("Affichage scientifique joueur 1", Location.TOP_LEFT);
		fenetreAff1.setVisible(true); 

		// Creation de la fenêtre affichage scientifique
		fenetreAff2 = new FenetreAffichageScientifique("Affichage scientifique joueur 2", Location.TOP_RIGHT);
		fenetreAff2.setVisible(true); 

		//Seed seed1 = new Seed(seed);
		//Seed seed2 = new Seed(seed);
		
		if (controles1[0] == Controle.CLAVIER && controles2[0] == Controle.CLAVIER)  //s'assure que chaque joueur a des controles qui fonctionnenent
			controles2[0] = Controle.MANETTE;
		
//		Monde monde1 = new Monde(joueurs1, Difficulte.FACILE, seed1, controles1, bindings1);
//		Monde monde2 = new Monde(joueurs2, Difficulte.FACILE, seed2, controles2, bindings2);
//
//		début événements personnalisés monde 1
//		monde1.addMondeListenerJ1(new MondeListenerJ1() {
//			public void framesParSecondeJ1(long frames) {
//				fenetreAff1.setLabelCompteurFrames(frames+"");
//			}
//			public void affichageScientifiqueParFrameJ1(Position pos, Vitesse vit, Acceleration accel, Etat etat, long score) {
//				//affiche des trucs scientifiques par frame
//				fenetreAff1.setLabelsAffichageParFrame(pos.toString(), vit.toString(), accel.toString(), etat + "", score+"");
//			}
//
//			public void affichageScientifiqueChangementTableauJ1(int distX, int distY, int biome, int danger, Difficulte difficulte,
//					Seed seed, int nbPlateformes, int nbMonstres, double vitesseDeplacement, double hauteurSaut) {
//				//affiche des trucs scientifiques par changeent de tableau
//				fenetreAff1.setLabelsAffichageParTableau(distX + " tableaux", distY + " tableaux", biome+"", danger+"", difficulte.toString(),
//						seed.toString(), nbPlateformes + " plateformes", nbMonstres + " monstres", Outils.ajusterPrecision(vitesseDeplacement, 5) + " u/s", hauteurSaut + "unites");
//			}
//
//			//utiliser pour le menu du level up
//			public void statsJoueur1(double experience, double pointsDeVie, int niveau, int pointsAttribuables, int[] stats) {
//				//affiche des trucs dans le menu lvl up
//				fenetreAff1.setLabelAffichageStats(experience+"", pointsDeVie+"", niveau+"", pointsAttribuables+"");
//			}
//		});
//		//fin événements personnalisés monde 1
//
//		//début événements personnalisés monde 2
//		monde2.addMondeListenerJ1(new MondeListenerJ1() {
//			public void framesParSecondeJ1(long frames) {
//				fenetreAff2.setLabelCompteurFrames(frames+"");
//			}
//			public void affichageScientifiqueParFrameJ1(Position pos, Vitesse vit, Acceleration accel, Etat etat, long score) {
//				//affiche des trucs scientifiques par frame
//				fenetreAff2.setLabelsAffichageParFrame(pos.toString(), vit.toString(), accel.toString(), etat + "", score+"");
//			}
//
//			public void affichageScientifiqueChangementTableauJ1(int distX, int distY, int biome, int danger, Difficulte difficulte,
//					Seed seed, int nbPlateformes, int nbMonstres, double vitesseDeplacement, double hauteurSaut) {
//				//affiche des trucs scientifiques par changeent de tableau
//				fenetreAff2.setLabelsAffichageParTableau(distX + " tableaux", distY + " tableaux", biome+"", danger+"", difficulte.toString(),
//						seed.toString(), nbPlateformes + " plateformes", nbMonstres + " monstres", Outils.ajusterPrecision(vitesseDeplacement, 5) + " u/s", hauteurSaut + "unites");
//			}
//
//			//utiliser pour le menu du level up
//			public void statsJoueur1(double experience, double pointsDeVie, int niveau, int pointsAttribuables, int[] stats) {
//				//affiche des trucs dans le menu lvl up
//				fenetreAff2.setLabelAffichageStats(experience+"", pointsDeVie+"", niveau+"", pointsAttribuables+"");
//			}
//		});
//		//fin événements personnalisés monde 2	
//
//		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, monde1, monde2);
//		splitPane.setEnabled(false);
//		splitPane.setOpaque(false);
//		splitPane.setDividerSize(5);
//		splitPane.setResizeWeight(.5d);
//		contentPane.add(splitPane);
	}
}