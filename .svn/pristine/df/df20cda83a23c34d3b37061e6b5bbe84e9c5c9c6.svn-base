package monde;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_2;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickAxes;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickButtons;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.event.EventListenerList;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.lwjgl.glfw.GLFWVidMode;

import aaplication.App10Plateforme;
import aaplication.FenetreModeCoop;
import aaplication.FenetreModeSolo;
import bindings.BindingsClavier;
import bindings.BindingsManette;
import ecouteurs.JoueurListener;
import ecouteurs.MondeListenerInfosJ1;
import ecouteurs.MondeListenerInfosJ2;
import ecouteurs.MondeListenerJ1;
import ecouteurs.MondeListenerJ2;
import editeur.Outils;
import entites.Camera;
import entites.EntiteItem;
import entites.EntitePhysique;
import entites.Joueur;
import entites.Monstre;
import environnement.Coffre;
import environnement.Environnement;
import environnement.Plateforme;
import fusee.Fusee;
import intelligence.AStar;
import intelligence.Node;
import intelligence.PathFinding;
import interfaces.Constantes;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import interfaces.Enumerations.Direction;
import interfaces.Enumerations.Etat;
import interfaces.Enumerations.ModeDeJeu;
import item.Inventaire;
import item.Item;
import personnages.Balle;
import personnages.Quest;
import physique.Energie;
import physique.Force;
import physique.ModelePhysique;
import physique.Vitesse;
import procedural.Seed;
import procedural.Tableau;
import save.Sauvegarde;
import save.SaveJoueur;
/**
 * Classe qui gere le monde du jeu
 * @author Antoine
 * @author Mihai
 *
 */
public class Monde extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	private final EventListenerList OBJETS_ENREGISTRES_J1 = new EventListenerList(), OBJETS_ENREGISTRES_J2 = new EventListenerList();
	private final EventListenerList OBJETS_ENREGISTRES_INFOS_J1 = new EventListenerList(), OBJETS_ENREGISTRES_INFOS_J2 = new EventListenerList();
	private final double LARGEUR_INITIALE = 20;
	private List<CopyOnWriteArrayList<Tableau>> listeTableaux = new ArrayList<CopyOnWriteArrayList<Tableau>>(); //version thread-safe de ArrayList
	private Color backGroundColor = new Color(100,150,60);
	public static Difficulte difficulte = Difficulte.FACILE;
	private ModelePhysique modele;	
	private Controle[] controles;
	private Joueur[] joueurs;	
	private Thread threadJ1, threadJ2, threadM;
	private Camera[] cam;
	private Seed seed;
	private Area aireClip;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private JFrame frame;
	private boolean runnable = true, nightTime = false;
	private boolean utiliseFusee[] = new boolean[2];

	private boolean[] gauche = {false, false}, droite = {false, false}, haut = {false, false}, bas = {false, false};
	private boolean animationEnCours, duo, memeEcran = true, joueur1gauche, cameraSwitchable = true;
	private double largeur = LARGEUR_INITIALE, hauteur = 9*largeur/16, translateJoueurs[] = {0.5, 0.5};
	private int[] distX = new int[2], distY = new int[2];
	private int[] ancienDistX = new int[2], ancienDistY = new int[2];
	private long framesAffiches;
	private boolean block[] = {false, false};
	private boolean attack[] = {false, false};
	private int compteur = 0; //le vrai fps
	private long score = 0;
	private boolean biomesDecouverts[] = new boolean[10];
	private boolean afficherVecteursVitesse, afficherVecteursAcceleration, itemSpawnAcces;
	private int scoreParBiome = 80;
	private int scoreParGold = 4;
	private int scoreParMonstre = 40;
	//private int scoreParBoss = 1000;
	private double multiplicateurDeDifficulte;
	private boolean mort = false, premierFrame = true;
	private long reviveTime = 10000; //en ms
	private boolean timerRunning = false, timerHorsCombatRunning[] = {false, false};
	private long timeCombat[] = new long[2], timeHorsCombat[] = new long[2];
	private final long TEMPS_AVANT_HORSCOMBAT = 10000; // en ms
	private Quest quest;
	private int nightAlpha = 0;
	private BindingsClavier keyJoueurs;
	private BindingsManette bindings[]; 
	private MenuPause menuPause;
	private MenuMort menuMort;
	private App10Plateforme menuPrincipal;
	private long window;	
	private boolean inventoryOpen = false;
	private SpringLayout springLayout;
	private InformationsJeu informationsJeu;
	private SimpleAttributeSet red, black, orange, yellow; //couleurs du texte affiche		
	private List<EntiteItem> items = new CopyOnWriteArrayList<EntiteItem>();

	/**
	 * Constructeur du monde
	 * @param joueurs Les joueurs
	 * @param diffi La difficulte
	 * @param seed Le seed
	 * @param controleParJoueur Les controles de chaque joueur
	 * @param bindings Les bindings de la manette de chaque joueur
	 * @param keyJoueurs Les bindings du clavier de chaque joueur
	 * @param menuPrincipal Le menu principal
	 * @param frame La frame du monde
	 * @param score Le score
	 * @param biomesDecouverts Les biomes deja decouverts
	 */
	public Monde (Joueur[] joueurs, Difficulte diffi, Seed seed, Controle[] controleParJoueur, BindingsManette[] bindings,
			BindingsClavier keyJoueurs, App10Plateforme menuPrincipal, JFrame frame, long score, boolean[] biomesDecouverts){		
		this.joueurs = joueurs;
		this.cam = new Camera[joueurs.length];
		difficulte = diffi;
		this.seed = seed;
		this.controles = controleParJoueur;	
		this.bindings = bindings;
		this.keyJoueurs = keyJoueurs;
		this.menuPrincipal = menuPrincipal;
		this.frame = frame;
		this.score = score;
		this.biomesDecouverts = biomesDecouverts;
		duo = (joueurs.length == 2) ? true : false;	

		initialiserSimpleAttributes();		
		choisirMultiplicateurDeDifficulte();

		listeTableaux.add(new CopyOnWriteArrayList<Tableau>());
		if (duo) listeTableaux.add(new CopyOnWriteArrayList<Tableau>());

		//creation de la camera par joueur
		cam[0] = new Camera(joueurs[0].getPositionX(), joueurs[0].getPositionY());
		if(duo)  cam[1] = new Camera(joueurs[1].getPositionX(), joueurs[1].getPositionY());

		if (controles[0] == Controle.CLAVIER || (duo && controles[1] == Controle.CLAVIER)) {
			setFocusable(true); //demande le focus
			runThreadsKeyListeners(); //demarre le thread du clavier
		}		
		if (controles[0] == Controle.MANETTE || (duo && controles[1] == Controle.MANETTE))
			runTestControllers(); //demarre le thread des controles pour les manettes

		setBackground(backGroundColor);
		springLayout = new SpringLayout();
		setLayout(springLayout);

		dessinerMenuPause();
		dessinerInventaire();		
		dessinerInformations();

		quest = new Quest(informationsJeu);
		genererTableaux(Direction.AUCUNE, joueurs.length); //genere les plateformes de depart 

		activerEcouteursJoueurs();
		/*for(int i = 0; i<listeTableaux.get(0).get(4).getListNodes()[47][46].getSuccessors().size(); i++){
			System.out.println(listeTableaux.get(0).get(4).getListNodes()[47][46].getSuccessors().get(i).getX() + ", " + listeTableaux.get(0).get(4).getListNodes()[47][46].getSuccessors().get(i).getY());
		}*/
		//System.out.println((listeTableaux.get(0).get(4).getListNodes()[45][49].getSuccessors().size()));
		/*Node node1 = new Node();
		Node node2 = new Node();
		while(!node1.isVide()){
			node1 = new Node(listeTableaux.get(0).get(4).getListNodes()[(int)(Math.random()*49)][(int)(Math.random()*49)]);
			}
		while(!node2.isVide()){
			node2 = new Node(listeTableaux.get(0).get(4).getListNodes()[(int)(Math.random()*49)][(int)(Math.random()*49)]);
		}*/
		nodes  = AStar.aStar(listeTableaux.get(0).get(4).getListNodes()[9][5], listeTableaux.get(0).get(4).getListNodes()[1][47]);
		//nodes = AStar.aStar(node1, node2);
		//System.out.println(nodes.size());		

		demarrer(); //demarrer le main thread	
		startDayNightCycle();
	}
	/**
	 * Dessine les composantes du monde
	 * @param g Le contexte graphique
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		modele = new ModelePhysique(getWidth(), getHeight(),largeur);
		AffineTransform[] mat = {modele.getMatMC(), modele.getMatMC()};

		mat[0].scale(1, -1);
		mat[0].translate(translateJoueurs[0]*getWidth()/modele.getPixelsParUniteX() - cam[0].getPositionX(),
				-(getHeight()/2)/modele.getPixelsParUniteY() -cam[0].getPositionY());

		if(!memeEcran){ 
			if (joueur1gauche) aireClip = new Area(new Rectangle2D.Double(0,0,getWidth()/2,getHeight()));	
			else aireClip = new Area(new Rectangle2D.Double(getWidth()/2,0,getWidth()/2,getHeight()));				
			g2d.setClip(aireClip);
		}

		//dessine la couleur de fond pour le joueur 1
		for (int i = 0; i < listeTableaux.get(0).size(); i++) {	
			Tableau tab = listeTableaux.get(0).get(i);
			tab.dessiner(g2d, mat[0]);	

			//dessine les plateformes pour le joueur 1
			for (int j = 0; j < tab.getListePlateformes().size(); j++) 
				tab.getListePlateformes().get(j).dessiner(g2d, mat[0]);
		}
		for (int h = 0; h < listeTableaux.size(); h++) {
			for (int i = 0; i < listeTableaux.get(h).size(); i++) {	
				Tableau tab = listeTableaux.get(h).get(i);

				//dessine les monstres pour le joueur 1
				for (int j = 0; j < tab.getListeMonstres().size(); j++) 
					tab.getListeMonstres().get(j).dessiner(g2d, mat[0]);
				//dessine les coffres pour le joueur 1
				for (int k = 0; k < tab.getListeCoffres().size(); k++)
					tab.getListeCoffres().get(k).dessiner(g2d, mat[0]);				
			}
		}

		if (!memeEcran && duo) {
			mat[1].scale(1, -1);
			mat[1].translate(translateJoueurs[1]*getWidth()/modele.getPixelsParUniteX() - cam[1].getPositionX(),
					-(getHeight()/2)/modele.getPixelsParUniteY() -cam[1].getPositionY());

			if (joueur1gauche) aireClip = new Area(new Rectangle2D.Double(getWidth()/2,0,getWidth()/2,getHeight()));
			else aireClip = new Area(new Rectangle2D.Double(0,0,getWidth()/2,getHeight()));	
			g2d.setClip(aireClip);

			//dessine la couleur de fond pour le joueur 2
			for (int i = 0; i < listeTableaux.get(1).size(); i++) {
				Tableau tab = listeTableaux.get(1).get(i);
				tab.dessiner(g2d, mat[1]);

				//dessine les plateformes pour le joueur 2
				for (int j = 0; j < tab.getListePlateformes().size(); j++) 
					tab.getListePlateformes().get(j).dessiner(g2d, mat[1]);				
			}
			for (int h = 0; h < listeTableaux.size(); h++) {
				for (int i = 0; i < listeTableaux.get(h).size(); i++) {
					Tableau tab = listeTableaux.get(h).get(i);

					//dessine les monstres pour le joueur 2
					for (int j = 0; j < tab.getListeMonstres().size(); j++) 
						tab.getListeMonstres().get(j).dessiner(g2d, mat[1]);
					//dessine les coffres pour le joueur 1
					for (int k = 0; k < tab.getListeCoffres().size(); k++)
						tab.getListeCoffres().get(k).dessiner(g2d, mat[1]);					
				}
			}
		}	

		if (duo) {			
			if(memeEcran) {
				if (joueurs[1].getFusee() != null) joueurs[1].getFusee().dessiner(g2d, mat[0]);
				joueurs[1].dessiner(g2d, mat[0]); //DESSIN DU JOUEUR 2 meme ecran				
				aireClip = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
			}else{
				if (joueurs[1].getFusee() != null) joueurs[1].getFusee().dessiner(g2d, mat[1]);
				joueurs[1].dessiner(g2d, mat[1]); //DESSIN DU JOUEUR 2 split screen

				g2d.setColor(Color.black);

				if (joueur1gauche) { //dessine le joueur 1 a gauche
					aireClip = new Area(new Rectangle2D.Double(0,0,getWidth()/2,getHeight()));
					g2d.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
				}
				else { //dessine le joueur 1 a droite
					aireClip = new Area(new Rectangle2D.Double(getWidth()/2,0,getWidth()/2,getHeight()));
					g2d.drawLine(getWidth()/2 - 1, 0, getWidth()/2 - 1, getHeight());
				}
				//affichage vecteurs joueur 2 lorsque pas dans le meme ecran
				if (afficherVecteursAcceleration || afficherVecteursVitesse){
					AffineTransform matVecteurs2 = new AffineTransform(mat[1]);
					matVecteurs2.translate(joueurs[1].getPositionX() + joueurs[1].getLargeur()/2, joueurs[1].getPositionY() + joueurs[1].getHauteur()/2);

					if (afficherVecteursVitesse) joueurs[1].getVitesse().dessiner(g2d, matVecteurs2);
					g2d.setColor(Color.red);
					if (afficherVecteursAcceleration) joueurs[1].getAcceleration().dessiner(g2d, matVecteurs2);
				}
				g2d.setColor(Color.white);
				//affichage fleche vers l'autre joueur
				joueurs[0].getPosition().flecheVersAutreEntite(joueurs[1]).dessiner(g2d, mat[1]);
			}
		}
		g2d.setClip(aireClip);		
		joueurs[0].dessiner(g2d, mat[0]);	//DESSIN DU JOUEUR 1

		if(afficherVecteursAcceleration || afficherVecteursVitesse) {
			//affichage des vecteurs pour le joueur 1
			AffineTransform matVecteurs1 = new AffineTransform(mat[0]);
			matVecteurs1.translate(joueurs[0].getPositionX() + joueurs[0].getLargeur()/2, joueurs[0].getPositionY() + joueurs[0].getHauteur()/2);

			if (afficherVecteursVitesse) joueurs[0].getVitesse().dessiner(g2d, matVecteurs1);
			g2d.setColor(Color.red);
			if (afficherVecteursAcceleration) joueurs[0].getAcceleration().dessiner(g2d, matVecteurs1);

			if (duo) {
				g2d.setColor(Color.white);
				//affichage fleche vers l'autre joueur
				joueurs[0].getPosition().flecheVersAutreEntite(joueurs[1]).dessiner(g2d, mat[0]);
				if(memeEcran) {
					//affichage des vecteurs pour le joueur 2 dans le meme ecran
					AffineTransform matVecteurs2 = new AffineTransform(mat[0]);
					matVecteurs2.translate(joueurs[1].getPositionX() + joueurs[1].getLargeur()/2, joueurs[1].getPositionY() + joueurs[1].getHauteur()/2);

					if (afficherVecteursVitesse) joueurs[1].getVitesse().dessiner(g2d, matVecteurs2);
					g2d.setColor(Color.red);
					if (afficherVecteursAcceleration) joueurs[1].getAcceleration().dessiner(g2d, matVecteurs2);
				}
			}
		} 

		//dessin echelle
		g2d.setColor(Color.red);
		aireClip = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
		g2d.setClip(aireClip);
		String echelle = "echelle: 1 unite par carre ";
		g2d.drawString(echelle, getWidth()-g2d.getFontMetrics().stringWidth(echelle), getHeight()-5);

		/*Line2D.Double line = new Line2D.Double();
		//System.out.println(nodes.size());
		for (int i = 0; i<nodes.size()-1; i++){
			//System.out.println("Dessine");
			g2d.setColor(Color.BLACK);
			line  = new Line2D.Double(nodes.get(i).getX()+50*distX[0], nodes.get(i).getY()+50*distY[0], nodes.get(i+1).getX()+50*distX[0], nodes.get(i+1).getY()+50*distY[0]);
			g2d.draw((mat[0].createTransformedShape(line)));
		}*/

		for (int i = 0; i < items.size(); i++) 
			items.get(i).dessiner(g2d, mat[0]);

		g2d.setColor(new Color(10, 2, 10, nightAlpha));
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	/**
	 * Dessin de l'inventaire
	 */
	private void dessinerInventaire() {
		Inventaire inventaireJ1 = joueurs[0].getInventaire();
		inventaireJ1.setVisible(false);
		if (duo) {
			Inventaire inventaireJ2 = joueurs[1].getInventaire();
			inventaireJ2.setBounds(600, 50, 600, 300);
			inventaireJ2.setVisible(false);
		}		
		inventaireJ1.setBounds(50, 50, 550, 600);
		add(inventaireJ1);
		springLayout.putConstraint(SpringLayout.NORTH, inventaireJ1, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, inventaireJ1, 10, SpringLayout.WEST, this);
	}
	/**
	 * Dessin de la boite d'informations
	 */
	private void dessinerInformations() {
		informationsJeu = new InformationsJeu(this, score);
		springLayout.putConstraint(SpringLayout.SOUTH, informationsJeu, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, informationsJeu, 0, SpringLayout.EAST, this);
		add(informationsJeu);

	}
	/**
	 * Dessin du menu pause
	 */
	private void dessinerMenuPause() {
		menuPause = new MenuPause(this);
		menuPause.setVisible(false);
		springLayout.putConstraint(SpringLayout.WEST, menuPause, 150, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, this, 150, SpringLayout.EAST, menuPause);
		springLayout.putConstraint(SpringLayout.NORTH, menuPause, 100, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, this, 100, SpringLayout.SOUTH, menuPause);
		add(menuPause);
	}
	private void dessinerMenuMort() {
		menuMort = new MenuMort(this);
		menuMort.setVisible(false);
		springLayout.putConstraint(SpringLayout.WEST, menuMort, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, this, 0, SpringLayout.EAST, menuMort);
		springLayout.putConstraint(SpringLayout.NORTH, menuMort, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, menuMort);
		add(menuMort);
	}
	/**
	 * Active les ecouteurs des joueurs
	 */
	private void activerEcouteursJoueurs() {
		joueurs[0].addJoueurListener(new JoueurListener() {
			public void levelUp() {
				menuPause.setJoueur1(joueurs[0]);
				quest.setNiveau(joueurs[0].getNiveau());
				try {
					informationsJeu.getDocument().insertString(0, "Vous avez augmenté de niveau\n", orange);
				} catch (BadLocationException e) {}
				System.out.println("PLAYER UPDATED");
			}
		});
		if (duo) {
			joueurs[1].addJoueurListener(new JoueurListener() {
				public void levelUp() {
					menuPause.setJoueur2(joueurs[1]);
				}
			});
		}

	}
	/**
	 * Initialise les composantes simple attributes
	 */
	private void initialiserSimpleAttributes() {
		red = new SimpleAttributeSet();		
		StyleConstants.setForeground(red, Color.red);

		black = new SimpleAttributeSet();		
		StyleConstants.setForeground(black, Color.white);

		orange = new SimpleAttributeSet();		
		StyleConstants.setForeground(orange, Color.orange);

		yellow = new SimpleAttributeSet();
		StyleConstants.setForeground(yellow, Color.yellow);
	}
	private void startDayNightCycle() {
		Thread timer = new Thread(new Runnable() {
			public void run() {
				while (animationEnCours) {					
					try {
						nightTime = false;
						for (int d = nightAlpha; d < 20; d++) {
							nightAlpha++;
							Thread.sleep(3000);
						}
						for (int d = nightAlpha; d < 210; d++) {
							nightAlpha++;
							if (nightAlpha < 70)
								Thread.sleep(1250);							
							else {
								nightTime = true;
								Thread.sleep(500);
							}
						}
						for (int n = nightAlpha; n >= 20; n--) {
							nightAlpha--;
							if (nightAlpha > 70)
								Thread.sleep(500);
							else {
								nightTime = false;
								Thread.sleep(1250);
							}
						}
						for (int d = nightAlpha; d >= 0; d--){
							nightAlpha--;
							Thread.sleep(3000);
						}						
					} catch (InterruptedException e) {}
				}
			}	
		}); timer.start();
	}
	/**
	 * Genere les tableaux selon la direction dans laquelle le joueur se dirige
	 * @param dir Une direction
	 * @param joueur Le numero du joueur
	 */
	private void genererTableaux(Direction dir, int joueur){
		switch (dir) {
		case AUCUNE : 
			//clear tous les tableaux
			listeTableaux.get(0).clear();	
			if (duo) listeTableaux.get(1).clear();

			for (int i = 0; i < joueur; i++) 
				if (!estDansLePremierTableau(joueurs[i])) { //modifie la distance dans les tableaux si le joueur n'est pas dans le tableau de depart
					distX[i] = (int)Math.floor(joueurs[i].getPositionX() / 50.0);
					distY[i] = (int)Math.floor(joueurs[i].getPositionY() / 50.0);

					ancienDistX[i] = distX[i];
					ancienDistY[i] = distY[i];
				}						
			//ajoute 9 tableaux au depart
			for (int i = 0; i < joueur; i++) 
				for (int j = 1 + distY[i]; j >= -1 + distY[i]; j--) 
					for (int k = -1 + distX[i]; k <= 1 + distX[i]; k++)
						listeTableaux.get(i).add(new Tableau(seed, k, j));				
			break;
		case DROITE : 
			int indexD = 6;
			int distYtemp = distY[joueur];
			for (int k = -1 + distYtemp; k <= 1 + distYtemp; k++) {
				//enleve 3 tableaux de l'extreme gauche
				listeTableaux.get(joueur).remove(indexD);

				//ajoute 3 nouveaux tableaux a l'extreme droite
				listeTableaux.get(joueur).add(indexD + 2, new Tableau(seed, 1 + distX[joueur], k));

				indexD -= 3;
			}
			break;
		case GAUCHE : 
			int indexG = 8;
			for (int k = -1 + distY[joueur]; k <= 1 + distY[joueur]; k++) {
				//enleve 3 tableaux de l'extreme droite
				listeTableaux.get(joueur).remove(indexG);

				//ajoute 3 nouveaux tableaux a l'extreme droite
				listeTableaux.get(joueur).add(indexG - 2, new Tableau(seed, -1 + distX[joueur], k));

				indexG -= 3;
			}
			break;
		case BAS : 
			for (int j = -1 + distX[joueur]; j <= 1 + distX[joueur]; j++) {
				//enleve 3 tableaux de l'extreme haut
				listeTableaux.get(joueur).remove(0);	

				//ajoute 3 nouveaux tableaux a l'extreme bas
				listeTableaux.get(joueur).add(8, new Tableau(seed, j, -1 + distY[joueur])); 	
			}
			break;
		case HAUT : 
			for (int j = 1 + distX[joueur]; j >= -1 + distX[joueur]; j--) {
				//enleve 3 tableaux de l'extreme bas
				listeTableaux.get(joueur).remove(8);	

				//ajoute 3 nouveaux tableaux a l'extreme haut
				listeTableaux.get(joueur).add(0, new Tableau(seed, j, 1 + distY[joueur])); 
			}
			break;
		case RESPAWN :
			//clear tous les tableaux
			listeTableaux.get(joueur).clear();	
			distX[joueur] = 0;
			distY[joueur] = 0;

			//ajoute 9 tableaux au depart;
			for (int j = 1; j >= -1; j--) 
				for (int k = -1; k <= 1; k++) 
					listeTableaux.get(joueur).add(new Tableau(seed, k, j));
			break;
		}

		int biomeJ1 = listeTableaux.get(0).get(4).getBiome();		
		if(!biomesDecouverts[biomeJ1]) {
			score += scoreParBiome * multiplicateurDeDifficulte;
			biomesDecouverts[biomeJ1] = true;

			SimpleAttributeSet couleurBiome = new SimpleAttributeSet();		
			StyleConstants.setForeground(couleurBiome, Constantes.couleurs[biomeJ1]);
			try {				
				informationsJeu.getDocument().insertString(0, Constantes.nomBiomes[biomeJ1] + "\n", couleurBiome);
				informationsJeu.getDocument().insertString(0, "Vous avez découvert un nouveau biome. Le biome : ", black);						
			} catch (BadLocationException e) { }

			leverEvenUpdateScore();
		}		
		if (duo) {
			int biomeJ2 = listeTableaux.get(1).get(4).getBiome();
			if (biomeJ2 == quest.getBiomeToFind())
				quest.finish();
			if(!biomesDecouverts[biomeJ1]) {
				score += scoreParBiome * multiplicateurDeDifficulte;
				biomesDecouverts[biomeJ2] = true;

				SimpleAttributeSet couleurBiome = new SimpleAttributeSet();		
				StyleConstants.setForeground(couleurBiome, Constantes.couleurs[biomeJ1]);
				try {	
					informationsJeu.getDocument().insertString(0, Constantes.nomBiomes[biomeJ1] + "\n", couleurBiome);
					informationsJeu.getDocument().insertString(0, "Vous avez découvert un nouveau biome. Le biome : ", black);
				} catch (BadLocationException e) { }
				leverEvenUpdateScore();
			}						
		}
		if (biomeJ1 == quest.getBiomeToFind())
			quest.finish();		
		leverEvenChangementTableauJ1();
		if (duo) leverEvenChangementTableauJ2();
	}
	/**
	 * Verifie si le joueur est dans le premier tableau
	 * @param joueur Un joueur
	 * @return Si le joueur est dans le premier tableau
	 */
	private boolean estDansLePremierTableau(Joueur joueur) {
		return (joueur.getPositionX() > 0 && joueur.getPositionX() < 50 &&
				joueur.getPositionY() > 0 && joueur.getPositionY() < 50);
	}
	/**
	 * Demarre la regeneration de vie des entites dans le monde
	 */
	private void demarrerRegeneration() {
		Thread threadRegeneration = new Thread(new Runnable() {
			public void run() {
				while (animationEnCours) {
					//regenere la vie pour les joueurs
					for (int i = 0; i < joueurs.length; i++) 
						if (!joueurs[i].isDead() && joueurs[i].isHorsCombat() && joueurs[i].getPointsDeVie() < joueurs[i].getPointsDeVieMaximum()) {
							joueurs[i].ajouterPointsDeVie(joueurs[i].getVieRegenereParSeconde());			
							if (joueurs[i].getPointsDeVie() > joueurs[i].getPointsDeVieMaximum()) { //s'assure que la vie du joueur ne depasse pas le max
								joueurs[i].setPointsDeVie(joueurs[i].getPointsDeVieMaximum());
							}
						}											
					//regenere la vie pour les monstres
					for (int i = 0; i < listeTableaux.size(); i++) 
						for (int j = 0; j < listeTableaux.get(i).size(); j++) {
							int listeMonstresSize = listeTableaux.get(i).get(j).getListeMonstres().size();
							for (int k = 0; k < listeMonstresSize; k++) {
								Monstre monstre = listeTableaux.get(i).get(j).getListeMonstres().get(k);
								if(monstre.isHorsCombat() && monstre.getPointsDeVie() < monstre.getPointsDeVieMaximum()) {
									monstre.ajouterPointsDeVie(monstre.getVieRegenereParSeconde());
									if (monstre.getPointsDeVie() > monstre.getPointsDeVieMaximum()) { //s'assure que la vie du monstre ne depasse pas le max
										monstre.setPointsDeVie(monstre.getPointsDeVieMaximum());
									}
								}
							}
						}	
					try {
						Thread.sleep(1000);
						leverEvenUpdateVieJ1();
						if (duo) leverEvenUpdateVieJ2();
					} catch (InterruptedException e) {
						System.out.println("Interruption pendant le sleep...");
					}
				}
			}		
		}); threadRegeneration.start();
	}
	/**
	 * Demarre le thread qui met a jour le joueur 1 
	 */
	private void updatePlayer1() {
		threadJ1 = new Thread(new Runnable() {
			public void run() {					
				//a optimiser pour qu'il teste avec moins de plateformes?
				List<Plateforme> listePlateformesTemp = new ArrayList<Plateforme>();
				List<Coffre> listeCoffresTemp = new ArrayList<Coffre>();

				listePlateformesTemp.clear();
				listeCoffresTemp.clear();

				for (int i = 0; i < listeTableaux.get(0).size(); i++) {
					Tableau tab = listeTableaux.get(0).get(i);					
					listePlateformesTemp.addAll(tab.getListePlateformes());
					listeCoffresTemp.addAll(tab.getListeCoffres());
					if (joueurs[0].getMonstre() != null && joueurs[0].getMonstre().isDead()) {
						score += scoreParMonstre * multiplicateurDeDifficulte;
						score += joueurs[0].getMonstre().getGold() * scoreParGold * multiplicateurDeDifficulte;

						joueurs[0].ajouterGold(joueurs[0].getMonstre().getGold());						
						joueurs[0].ajouterExperience(joueurs[0].getMonstre().getExpDonnee());					
						joueurs[0].getInventaire().setGold(joueurs[0].getGold());

						joueurs[0].setMonstre(null);						
						leverEvenUpdateScore();
						leverEvenUpdateExpJ1();
					}
				}
				testCollisions(0, listePlateformesTemp, listeCoffresTemp);
				if(joueurs[0].isDead()) {
					//joueurs[0].setEtat(Etat.MORT);	
					if (duo) {
						if (!timerRunning)	
							reviveTimer(0);
						if (joueurs[0].isDead() && joueurs[1].isDead()) {
							arreter();
							mort = true;
							//ouvrir menu mort mode coop joueurs
						}
					} else {
						arreter();
						mort = true;
						mortSolo();
					}
				}				
				//algo hors combat
				if (timerHorsCombatRunning[0]) timeHorsCombat[0] = System.currentTimeMillis() - timeCombat[0];
				if (timeHorsCombat[0] > TEMPS_AVANT_HORSCOMBAT) {					
					joueurs[0].setHorsCombat(true);
					if (joueurs[0].getMonstre() != null) {
						joueurs[0].getMonstre().setHorsCombat(true);
						joueurs[0].setMonstre(null);
					}
				}


				for (int j = 0; j < joueurs[0].getBalles().size(); j++) {
					Balle balle = joueurs[0].getBalles().get(j);
					balle.update();
					if(!balle.isAlive())
						joueurs[0].getBalles().remove(balle);
				}					
			}
		}); threadJ1.start();
	}
	/**
	 * Demarre le thread qui met a jour le joueur 2
	 */
	private void updatePlayer2() {		
		threadJ2 = new Thread(new Runnable() {
			public void run() {
				//a optimiser pour qu'il teste avec moins de plateformes?
				List<Plateforme> listePlateformesTemp = new ArrayList<Plateforme>();
				List<Coffre> listeCoffresTemp = new ArrayList<Coffre>();

				listePlateformesTemp.clear();
				listeCoffresTemp.clear();
				if (memeEcran) {
					for (int i = 0; i < listeTableaux.get(0).size(); i++) {
						Tableau tab = listeTableaux.get(0).get(i);					
						listePlateformesTemp.addAll(tab.getListePlateformes());
						listeCoffresTemp.addAll(tab.getListeCoffres());
					}
				} else {
					for (int i = 0; i < listeTableaux.get(1).size(); i++) {
						Tableau tab = listeTableaux.get(1).get(i);					
						listePlateformesTemp.addAll(tab.getListePlateformes());
						listeCoffresTemp.addAll(tab.getListeCoffres());
					}
				}
				testCollisions(1, listePlateformesTemp, listeCoffresTemp);

				if (joueurs[1].isDead()) {
					if (!timerRunning)				
						reviveTimer(1);
				}
				//algo hors combat
				if (timerHorsCombatRunning[1]) timeHorsCombat[1] = System.currentTimeMillis() - timeCombat[1];
				if (timeHorsCombat[1] > TEMPS_AVANT_HORSCOMBAT) {					
					joueurs[1].setHorsCombat(true);
					if (joueurs[1].getMonstre() != null) {
						joueurs[1].getMonstre().setHorsCombat(true);
						joueurs[1].setMonstre(null);
					}
				}

				for (int j = 0; j < joueurs[1].getBalles().size(); j++) {
					Balle balle = joueurs[1].getBalles().get(j);
					balle.update();
					if(!balle.isAlive())
						joueurs[1].getBalles().remove(balle);
				}

				leverEvenUpdateExpJ2();
			}
		}); threadJ2.start();
	}
	/**
	 * Demarre le thread qui met a jour les monstres
	 */
	private void updateMonstres() {
		threadM = new Thread(new Runnable() {
			public void run() {
				List<Plateforme> listePlateformesTemp = new ArrayList<Plateforme>();
				List<Coffre> listeCoffresTemp = new ArrayList<Coffre>();
				for (int i = 0; i < listeTableaux.size(); i++) {
					for(int p=0; p < listeTableaux.get(i).get(4).getListeMonstres().size(); p++){
						Monstre monst2 =  listeTableaux.get(i).get(4).getListeMonstres().get(p);
						if(/*monst2.lineOfSight(joueurs[0], listeTableaux.get(0).get(4).getListePlateformes())
								&& !monst2.isTargeting() &&*/ 25 >= monst2.getPosition().distance(joueurs[0].getPosition())){
							//monstre.setIsTargeting(true);
							//System.out.println("Monstre voit");							
							nodes=AStar.aStar(listeTableaux.get(0).get(4).getListNodes()[(int)(Math.abs(monst2.getPositionX()%50))][(int)(Math.abs(monst2.getPositionY())%50)],
									listeTableaux.get(0).get(4).getListNodes()[(int)(Math.abs(joueurs[0].getPositionX()%50))][(int)(Math.abs(joueurs[0].getPositionY()%50))]);
							if(nodes.size() > 0){
								PathFinding.follow(nodes, monst2, distX[0], distY[0]);
							}
							//monst2.deplaceMonstre((int)monst2.getPositionX()+3, (int)monst2.getPositionY(), 4);
						} 
						if(duo && monst2.lineOfSight(joueurs[1], listeTableaux.get(1).get(4).getListePlateformes()) && !monst2.isTargeting()&& 10 >= monst2.getPosition().distance(joueurs[0].getPosition())){
							//monstre.setIsTargeting(true);
							//System.out.println("Monstre voit joueur2");
						}
					}
					listePlateformesTemp.clear();
					for (int j = 0; j < listeTableaux.get(i).size(); j++) {
						Tableau tab = listeTableaux.get(i).get(j);

						listePlateformesTemp.addAll(tab.getListePlateformes());
						listeCoffresTemp.addAll(tab.getListeCoffres());

						for (int k = 0; k < tab.getListeMonstres().size(); k++) {
							Monstre monstre = tab.getListeMonstres().get(k);
							monstre.setNightTime(nightTime);
							if (monstre.isDead()) {
								tab.getListeMonstres().remove(monstre);
								try {
									informationsJeu.getDocument().insertString(0, "Vous avez reçu " + Outils.ajusterPrecision(monstre.getExpDonnee(), 2)
									+ " experience et " + monstre.getGold() + " pièces d'or.\n", black);
								} catch (BadLocationException e) { }
								//debut kill quests
								int biome = tab.getBiome();
								if (quest.getQuestType() == 1)									
									if (tab.getListeMonstres().isEmpty())
										quest.finish();
									else {
										try {
											informationsJeu.getDocument().insertString(0, "monstres dans ce tableau.\n", black);
											informationsJeu.getDocument().insertString(0, (tab.getListeMonstres().size()+" "), red);
											informationsJeu.getDocument().insertString(0, "Il reste ", black);
										} catch (BadLocationException e) { }										 
									}
								else if (quest.getQuestType() == 2) {
									quest.addDifferentMonsterKilled(biome);
								}
								else if (quest.getQuestType() == 3) {
									List<Integer> tempList = new ArrayList<Integer>();
									for (int bio : quest.getSpecificMonstersToKill())
										tempList.add(bio);									
									for (int l = 0; l < quest.getSpecificMonstersToKill().size(); l++) {					
										if (tempList.remove((Integer)biome))
											System.out.println("Biome " + biome + " cleared");
									}									
									quest.setSpecificMonstersToKill(tempList);									
								}
								else if (quest.getQuestType() == 4) {
									quest.anyMonsterKilled();
								}								
								//fin kill quests


							} else {
								if (monstre.isEnChute() || (monstre.isEnMouvement() && !monstre.surQuelqueChose())){				
									findCollisionEnvironnement(monstre, 0, listePlateformesTemp, listeCoffresTemp);									
								} else if(monstre.isEnMouvement() && monstre.isAuSol()) { 
									findCollisionEnvironnement(monstre, 1, listePlateformesTemp, listeCoffresTemp);									
								}
								//ajouter sprites monstre
								//if (!ep.isEnMouvement()&&ep.isAuSol()) ((Joueur)ep).setEtat(Etat.IMMOBILE);

								//code pour AI
								/*if(monstre.lineOfSight(joueurs[0].getPosition(), listePlateformes.get(0)) && !monstre.isTargeting()){
								monstre.setIsTargeting(true);
							} 
							if(duo && monstre.lineOfSight(joueurs[1].getPosition(), listePlateformes.get(1)) && !monstre.isTargeting()){
								monstre.setIsTargeting(true);
							}*/
								monstre.update();
							} 
						}
					}
				}
			}
		}); threadM.start();
	}
	/**
	 * Commence le timer avant qu'un joueur ne peut plus revivre 
	 * @param j Le joueur 
	 */
	private void reviveTimer(int j) {		
		Thread timer = new Thread(new Runnable() {
			public void run() {
				try {					
					System.out.println("start timer");
					timerRunning = true;
					Thread.sleep(reviveTime);
					//joueurs[j].setRevivable(false);
					joueurs[j].respawn();					
					genererTableaux(Direction.RESPAWN, j);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}								
			}
		}); timer.start();
	}
	/**
	 * Le thread qui gere l'animation du monde
	 */
	public void run() {
		while(animationEnCours){
			long start = System.currentTimeMillis();
			if(premierFrame) { //fix lever evenement quand on ouvre une sauvegarde
				leverEvenChangementTableauJ1();
				if (duo) leverEvenChangementTableauJ2();
				if (compteur > 2) premierFrame = false;
			}

			//controles pour les joueurs
			for (int i = 0; i < joueurs.length; i++) {
				if(gauche[i] && !droite[i]) {
					if (!joueurs[i].isBlocking() && !joueurs[i].isRolling())
						//joueurs[i].setEtat(Etat.MOUVEMENT);
						joueurs[i].bougerGauche();	
					if (attack[i] && !block[i]) { 				
						joueurs[i].dashAttack();					
					}
				}
				if(droite[i] && !gauche[i]) {
					/*if (!joueurs[i].isRolling())
						joueurs[i].setEtat(Etat.MOUVEMENT);*/
					if (!joueurs[i].isBlocking() && !joueurs[i].isRolling())
						joueurs[i].bougerDroite();			
					if (attack[i] && !block[i]) {
						joueurs[i].dashAttack();	
					}
				}			
				if(bas[i]) {
					if (!joueurs[i].isBlocking()&& joueurs[i].isEnMouvement() && !joueurs[i].isDashing()) 
						joueurs[i].setEtat(Etat.DESCENDRE);
					joueurs[i].descendre();
				}
				if(attack[i] && block[i]) {
					joueurs[i].choisirSpecial();					
				}
				if(attack[i] && !block[i]) {
					joueurs[i].normalAttack(false);
				}
				if(block[i] && !attack[i]) {
					if(!droite[i] && !gauche[i]) 				
						joueurs[i].block();		
					if(droite[i])
						joueurs[i].roll(Direction.DROITE);
					else if(gauche[i]) 
						joueurs[i].roll(Direction.GAUCHE);
				}
				joueurs[i].utiliserFusee(utiliseFusee[i]); //update la fusee
			}

			updatePlayer1();
			if (duo) {
				updatePlayer2();
				try {
					threadJ2.join(); //attend la fin des calculs pour le joueur 2;
				} catch (InterruptedException e) {}
			}

			try {
				threadJ1.join(); //attend la fin des calculs pour le joueur 1 
			} catch (InterruptedException e) {}

			updateMonstres();

			int l = 0;
			for (int i = 0; i < joueurs.length; i++) { //calcul de la distance des personnages et positionnement de la camera

				distX[i] = (int) Math.floor(joueurs[i].getPositionX() / 50);

				distY[i] = (int) Math.floor(joueurs[i].getPositionY() / 50);

				if (ancienDistX[i] != distX[i] && ancienDistY[i] == distY[i]) {
					if (ancienDistX[i] > distX[i]) genererTableaux(Direction.GAUCHE, i);
					else genererTableaux(Direction.DROITE, i);

					ancienDistX[i] = distX[i];
				} 
				if (ancienDistY[i] != distY[i] && ancienDistX[i] == distX[i]) {
					if (ancienDistY[i] > distY[i]) genererTableaux(Direction.BAS, i);	
					else genererTableaux(Direction.HAUT, i);

					ancienDistY[i] = distY[i];
				}

				cam[l].moveTo(joueurs[i]);
				cam[l].update();
				if (!memeEcran) l = (l+1)%2;
			} 

			if (duo) {
				//verifie si les deux joueurs sont dans le meme ecran
				double distX = Math.abs(joueurs[0].getPositionX() - joueurs[1].getPositionX()); 
				if ((distX < largeur*0.5 ) &&
						(Math.abs(joueurs[0].getPositionY() - joueurs[1].getPositionY()) < hauteur*0.75)) { 
					//mode meme ecran
					memeEcran = true;
					cameraSwitchable = true;
					translateJoueurs[0] = 0.5;
					translateJoueurs[1] = 0.5;					
					largeur= LARGEUR_INITIALE + distX;
					hauteur = 9*largeur/16; 
				} else { 
					//mode split screen
					largeur = LARGEUR_INITIALE;
					memeEcran = false;
					positionTranslate();
				}
			}
			List<EntiteItem> temp = new CopyOnWriteArrayList<EntiteItem>();
			for (int i = 0; i < items.size(); i++)
				temp.add(items.get(i));
			for (int i = 0; i < temp.size(); i++) {
				if (testCollisionItems(temp.get(i)))
					temp.get(i).update();
			}

			repaint();

			try {					
				Thread.sleep((long)(1000/Constantes.FPS));						
			}
			catch (InterruptedException e) {
				System.out.println("Erreur pendant le sleep de l'animation");
			}
			framesAffiches += (System.currentTimeMillis() - start);		
			compteur++;
			if (framesAffiches >= 1000) { //calcule le nombre de frames par seconde
				leverEvenCompteurFramesJ1();
				if (duo) leverEvenCompteurFramesJ2();
				framesAffiches = 0;
				compteur = 0;
			}
			leverEvenChangementFrameJ1();
			if (duo) leverEvenChangementFrameJ2();

		}
	}
	private boolean testCollisionItems(EntiteItem item) {
		if (!item.isAuSol()) {
			for (int i = 0; i < listeTableaux.size(); i++)
				for (int j = 0; j < listeTableaux.get(i).size(); j++)
					for (int k = 0; k < listeTableaux.get(i).get(j).getListePlateformes().size(); k++)
						if (item.contactPieds(listeTableaux.get(i).get(j).getListePlateformes().get(k), 0)) {
							Environnement envi = listeTableaux.get(i).get(j).getListePlateformes().get(k);
							item.setAuSol(true);
							item.setEnChute(false);
							item.enleverForce(new Force("Gravite"));
							item.setVitesse(new Vitesse(0, 0));						
							item.setPositionY(envi.getZoneCollision().getY() + 1);
							item.setObjetEnvironnementSousLesPieds(envi);						
						}
			return true;
		} else {
			for (int i = 0; i < joueurs.length; i++) 
				if (joueurs[i].contact(item)) {
					joueurs[i].getInventaire().addItem(item.getItem());				
					items.remove(item);
					return false;
				}
		}
		return false;
	}
	/**
	 * Choisi la position de la camera split screen
	 */
	private void positionTranslate() {
		if (cameraSwitchable) {
			cameraSwitchable = false;
			if (joueurs[0].getPositionX() < joueurs[1].getPositionX()) {	
				joueur1gauche = true;
				translateJoueurs[0] = 0.25;
				translateJoueurs[1] = 0.75;				
			} else {	
				joueur1gauche = false;
				translateJoueurs[0] = 0.75;
				translateJoueurs[1] = 0.25;		
			}
		}
	}
	/**
	 * Teste les collisions d'un joueur avec le reste du monde
	 * @param nbJoueur Le numero du joueur
	 * @param listePlateformesTemp Une liste de plateformes temporaire
	 */
	private void testCollisions(int nbJoueur, List<Plateforme> listePlateformesTemp, List<Coffre> listeCoffresTemp) {
		EntitePhysique ep = joueurs[nbJoueur];
		synchronized (ep) {
			if (ep.isEnChute() || (ep.isEnMouvement() && !ep.surQuelqueChose()))				
				findCollisionEnvironnement(ep, 0, listePlateformesTemp, listeCoffresTemp);						
			else if(ep.isEnMouvement() && ep.isAuSol()) 
				findCollisionEnvironnement(ep, 1, listePlateformesTemp, listeCoffresTemp);		
			else 
				findCollisionEnvironnement(ep, 2, listePlateformesTemp, listeCoffresTemp);

			findCollisionAvecMonstres((Joueur)ep, nbJoueur);			

			if (ep.isAttacking() || ep.isDoingSpecial() || ep.isDashing()) {					
				findCollisionAttaque((Joueur)ep, nbJoueur);
			}			

			ep.update();
			if(nbJoueur == 0) leverEvenStatsJ1();
			if(nbJoueur == 1) leverEvenStatsJ2();			
		}		
	}	
	/**
	 * Cherche pour les collisions entre un entite physique et une platefrome
	 * @param ep Une entite physique
	 * @param cas Le cas de collision
	 */
	private void findCollisionEnvironnement(EntitePhysique ep, int cas, List<Plateforme> listePlateformesTemp, List<Coffre> listeCoffresTemp) {
		//Detection des collisions avec des coffres
		if (cas != 2) {
			for (int i = 0; i < listeCoffresTemp.size(); i++) {

				Environnement coffre = listeCoffresTemp.get(i);				

				if (coffre.getZoneCollision() != null && ep.nearEnvironnement(coffre, 2)) { //verifie si l'entite est proche du coffre
					if (cas == 0 && ep.contactPieds(coffre, cas)) {	
						if (ep.getClass() == Joueur.class) recevoirContenants((Coffre)coffre, (Joueur)ep);
					} else if (ep.contactPieds(coffre, cas)) {	
						if (ep.getClass() == Joueur.class) recevoirContenants((Coffre)coffre, (Joueur)ep);			
					}	
				}
			}
		}
		//Detection des collisions avec des plateformes
		for (int i = 0; i < listePlateformesTemp.size(); i++) {			
			Environnement plateforme = listePlateformesTemp.get(i);			
			if (ep.getClass() == Joueur.class) { //test les collisions de balles avec les plateformes pour le special du cowboy
				Joueur joueur = (Joueur)ep;
				//Joueur joueur = (Joueur)ep;
				if (!joueur.getBalles().isEmpty()) {
					for (int b = 0; b < joueur.getBalles().size(); b++) {
						Balle balle = joueur.getBalles().get(b);
						if (balle.autourYPlateforme(plateforme))
							balle.collisionEnvironnement(plateforme.getZoneCollision());
					}
				}
			}
			if (cas != 2 && ep.nearEnvironnement(plateforme, (int)plateforme.getZoneCollision().getWidth())) { //verifie si l'entite est proche de la plateforme) {
				if (cas == 0 && ep.contactPieds(plateforme, cas)) {
					//debut systeme de degats
					double energieDegats =  Energie.energieCinetique(ep.getMasse(), ep.getVitesse().getComposanteVitesseY());
					if (energieDegats > 5) {
						ep.enleverPointsDeVie(energieDegats/3);
						leverEvenUpdateVieJ1();
						if (duo) leverEvenUpdateVieJ2();
					}
					//fin systeme de degats

					ep.ajouterForce(new Force("Normale",0,Constantes.K_GRAVITE*ep.getMasse()));
					ep.setVitesse(new Vitesse(ep.getVitesse().getComposanteVitesseX(),0));			
					ep.setPositionY(plateforme.getZoneCollision().getY() + 1);
					ep.setObjetEnvironnementSousLesPieds(plateforme);
					ep.setEnChute(false);
					ep.setAuSol(true);
				} else if (ep.contactPieds(plateforme, cas)) {	
					double yPlateforme = plateforme.getZoneCollision().getY();
					if ((ep.getPositionY() >= yPlateforme-0.1) && ( ep.getPositionY() < yPlateforme + 1)) {
						ep.getVitesse().setComposanteVitesseX(-0.5*ep.getVitesse().getComposanteVitesseX());
						ep.setDirection( ep.getDirection() == Direction.DROITE ? Direction.GAUCHE : Direction.DROITE);
					}
					ep.ajouterForce(new Force("Normale",0,Constantes.K_GRAVITE*ep.getMasse()));				
					ep.setObjetEnvironnementSousLesPieds(plateforme);
				}
			}
		}

	}	
	/**
	 * Cherche les collisions entre deux entites physiques
	 * @param joueur Une entite physique
	 * @param nbJoueur Le numero du joueur
	 */
	private void findCollisionAvecMonstres(Joueur joueur, int nbJoueur) {
		List<Monstre> listeMonstresTemp = new ArrayList<Monstre>();				
		for (int h = 0; h < listeTableaux.size(); h++) {
			for (int i = 0; i < listeTableaux.get(h).size(); i++) {
				Tableau tab = listeTableaux.get(h).get(i);

				listeMonstresTemp.clear();
				listeMonstresTemp.addAll(tab.getListeMonstres());
				for (int j = 0; j < tab.getListeMonstres().size(); j++) {
					Monstre monstre = listeMonstresTemp.get(j);
					if (joueur.nearMonstre(monstre)) { //verifie si le monstre est proche du joueur
						if(joueur.contact(monstre)) {

							if (joueur.isEnChute() && joueur.enHaut(monstre))
								joueur.getVitesse().setComposanteVitesseY(0.1);
							else if (!joueur.enHaut(monstre))  {
								if(joueur.getDirection() == Direction.DROITE)
									joueur.setPositionX(monstre.getPositionX() - joueur.getLargeur());
								else if (joueur.getDirection() == Direction.GAUCHE) 
									joueur.setPositionX(monstre.getPositionX() + monstre.getLargeur());
							}
						}		
					}
					if (!joueur.getBalles().isEmpty()) {
						for (int b = 0; b < joueur.getBalles().size(); b++) {
							Balle balle = joueur.getBalles().get(b);
							balle.collisionMonstre(monstre);
						}
					}
				}
			}
		}
		if (duo) {
			Joueur autreJoueur = joueurs[(nbJoueur+1)%2];
			if(joueur.contact(autreJoueur)){
				if (autreJoueur.isDead()) {					
					if (autreJoueur.isRevivable()) {						
						System.out.println("joueur " + ((nbJoueur+1)%2 +1) + " a ete revive");
						timerRunning = false;
						autreJoueur.revivre();						
					}

				}
				if (joueur.isEnChute()) {
					//joueur.setEtat(Etat.IMMOBILE);
					//joueur.getVitesse().setComposanteVitesseY(0.1);
					System.out.println("END ME");
				} else if (!joueur.enHaut(autreJoueur)){
					if(joueur.getDirection() == Direction.DROITE)
						joueur.setPositionX(autreJoueur.getPositionX() - joueur.getLargeur());
					else if (joueur.getDirection() == Direction.GAUCHE) 
						joueur.setPositionX(autreJoueur.getPositionX() + autreJoueur.getLargeur());
				}
			}
		}
	}
	/**
	 * Detecte les collisions d'attaques pour les joueur
	 * @param joueur Un joueur
	 */
	private void findCollisionAttaque(Joueur joueur, int nbJ) {
		List<Monstre> listeMonstresTemp = new ArrayList<Monstre>();		
		for (int h = 0; h < listeTableaux.size(); h++) {
			for (int i = 0; i < listeTableaux.get(h).size(); i++) {
				Tableau tab = listeTableaux.get(h).get(i);

				listeMonstresTemp.clear();
				listeMonstresTemp.addAll(tab.getListeMonstres());
				for (int j = 0; j < tab.getListeMonstres().size(); j++) {
					Monstre monstre = listeMonstresTemp.get(j);
					if (joueur.nearMonstre(monstre)) { //verifie si le monstre est proche du joueur
						if (joueur.contactMonstre(monstre)) {					
							joueur.setHorsCombat(false);
							monstre.setHorsCombat(false);

							timeCombat[nbJ] = System.currentTimeMillis();
							timerHorsCombatRunning[nbJ] = true;
						}
					}
				}
			}
		}
	}
	private void recevoirContenants(Coffre coffre, Joueur joueur) {
		ArrayList<Object> contenants = coffre.ouvrirCoffre(); //liste des contentants

		for (Object obj : contenants) { //gere les cas des objets possibles
			if (obj.getClass() == Item.class) {				
				EntiteItem tempItem = new EntiteItem(joueur.getPositionX(), joueur.getPositionY() + 2, ((Item)obj).getId());
				items.add(tempItem);

				if (quest.getItemIdToFind().equals(((Item)obj).getId()))
					quest.finish();

				SimpleAttributeSet couleurRarete = new SimpleAttributeSet();
				StyleConstants.setForeground(couleurRarete, ((Item)obj).getCouleurRarete());

				try {
					informationsJeu.getDocument().insertString(0, ((Item)obj).getNomItem() + "\n", couleurRarete);
					informationsJeu.getDocument().insertString(0, "Vous avez recu l'item ", black);					
				} catch (BadLocationException e) { }
			}
			else if (obj.getClass() == Integer.class) {
				joueur.ajouterGold((int)obj);
				try {
					if ((int)obj > 0) {
						informationsJeu.getDocument().insertString(0, (int)obj + " pièces d'or.\n", yellow);
						informationsJeu.getDocument().insertString(0, "Vous avez reçu ", black);
					}
				} catch (BadLocationException e) {}
			}
			else if (obj.getClass() == Fusee.class) {
				joueurs[0].setFusee(new Fusee(1000, 0, joueurs[0].getMasse(), 10, 50, joueurs[0].getPositionX(), joueurs[0].getPositionY()));

				EntiteItem tempItem = new EntiteItem(joueur.getPositionX(), joueur.getPositionY() + 1, "28");				
				items.add(tempItem);

				SimpleAttributeSet couleurFusee = new SimpleAttributeSet();
				StyleConstants.setForeground(couleurFusee, new Color(255, 223, 0));

				try {
					informationsJeu.getDocument().insertString(0, "Vous avez reçu une fusée\n", couleurFusee);
				} catch (BadLocationException e) {	}
			}
		}

	}
	/**
	 * Demarre le main thread du monde
	 */
	public void demarrer(){
		if (!animationEnCours) { 
			System.out.println("Animation démarrée");
			Thread processusAnim = new Thread(this);
			processusAnim.start();
			animationEnCours = true;
			demarrerRegeneration(); // demarre le thread qui s'occupe de regenerer la vie des joueurs et des monstres
		}
	}
	/**
	 * Met en pause l'animation principale du monde
	 */
	private void arreter() {
		animationEnCours = false;
	}
	/**
	 * Les ecouteurs du clavier
	 */
	public void runThreadsKeyListeners() {
		if (controles[0] == Controle.CLAVIER) {
			Thread threadKeyJ1 = new Thread(new Runnable(){
				@Override
				public void run() {
					System.out.println("Thread clavier : joueur1 start");
					addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if (!joueurs[0].isDead()) {
								final int code = e.getKeyCode();							
								if (code == keyJoueurs.getKeyJoueurs(0, 0) ) { //saut
									if (inventoryOpen) joueurs[0].getInventaire().parcourirEnHaut();
									else if (!joueurs[0].isBlocking() && !joueurs[0].isAttacking()) { //pas de block attack ou block jump
										joueurs[0].setEtat(Etat.SAUT);
										joueurs[0].sauter();
										haut[0] = true;										
									}
								}
								else if (code == keyJoueurs.getKeyJoueurs(0, 1) && !joueurs[0].isRolling()) { //droite
									joueurs[0].setEnMouvement(true);
									droite[0] = true;									
								}
								else if (code == keyJoueurs.getKeyJoueurs(0, 2) && !joueurs[0].isRolling()) { //descendre
									if (inventoryOpen) joueurs[0].getInventaire().parcourirEnBas();
									else bas[0] = true;

								}
								else if (code == keyJoueurs.getKeyJoueurs(0, 3) && !joueurs[0].isRolling()) { //gauche
									joueurs[0].setEnMouvement(true);
									gauche[0] = true;									
								}
								else if (code == keyJoueurs.getKeyJoueurs(0, 4)) { //attaque								
									if (inventoryOpen) {	
										Item tempItem = joueurs[0].getInventaire().equipItem(joueurs[0].getListeItems());
										if (tempItem != null) 
											joueurs[0].equipItem(tempItem);
									}
									else attack[0] = true;		

								}
								else if (code == keyJoueurs.getKeyJoueurs(0, 5)) { //bloc
									block[0] = true;
								} 
								else if (code == KeyEvent.VK_ESCAPE && !mort) { //ouvrir menu									
									if (animationEnCours) {
										arreter();
										menuPause.setVisible(true);
										informationsJeu.setVisible(false);
									}
									else {
										demarrer();
										menuPause.setVisible(false);
										informationsJeu.setVisible(true);
									}
								} 
								else if (code == KeyEvent.VK_SPACE){
									if (joueurs[0].getInventaire().isVisible())
										joueurs[0].unequipFusee();
									else 
										utiliseFusee[0] = true;									
								}
								else if (code == KeyEvent.VK_I) {
									inventoryOpen = inventoryOpen ? false : true;
									joueurs[0].getInventaire().setVisible(inventoryOpen);
								}
								else if (code == KeyEvent.VK_H && itemSpawnAcces) {
									Item test  = Constantes.armes[ThreadLocalRandom.current().nextInt(0,17)];								
									joueurs[0].getInventaire().addItem(test);
								} 
								else if (code == KeyEvent.VK_J && itemSpawnAcces) {
									Item test2 = Constantes.boucliers[ThreadLocalRandom.current().nextInt(0,9)];
									joueurs[0].getInventaire().addItem(test2);
								}
								else if (code == KeyEvent.VK_K && itemSpawnAcces) {
									Item item = joueurs[0].getInventaire().sellItem();
									if (item != null) joueurs[0].ajouterGold(item.getGold());						
								}
							}
						}	
						public void keyReleased(KeyEvent e){				
							final int code = e.getKeyCode();
							if (code == keyJoueurs.getKeyJoueurs(0, 0)) { //saut
								haut[0] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(0, 1)) { //droite
								droite[0] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(0, 2)) { //descendre
								bas[0] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(0, 3)) { //gauche
								gauche[0] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(0, 4)) { //attaque
								attack[0] = false;							
							}
							else if (code == keyJoueurs.getKeyJoueurs(0, 5)) { //bloc
								block[0] = false;
								if (joueurs[0].isBlocking())
									joueurs[0].setEtat(Etat.IMMOBILE);
								joueurs[0].setBlocking(false);
							}
							else if(code == KeyEvent.VK_SPACE){
								utiliseFusee[0] = false;
							}
						}

					});
				}
			}); threadKeyJ1.start();
		}
		if (duo && controles[1] == Controle.CLAVIER) {
			Thread threadKeyJ2 = new Thread(new Runnable() {
				public void run() {
					System.out.println("Thread clavier : joueur2 start");
					addKeyListener(new KeyAdapter() {						
						public void keyPressed(KeyEvent e) {						
							if (!joueurs[1].isDead()) {
								final int code = e.getKeyCode();
								if (code == keyJoueurs.getKeyJoueurs(1, 0)) { //saut
									if (!joueurs[1].isBlocking() && !joueurs[1].isAttacking()) {
										joueurs[1].setEtat(Etat.SAUT);
										joueurs[1].sauter();
										haut[1] = true;
									}
								}
								else if (code == keyJoueurs.getKeyJoueurs(1, 1) && !joueurs[1].isRolling()) { //droite
									joueurs[1].setEnMouvement(true);
									droite[1] = true;
								}
								else if (code == keyJoueurs.getKeyJoueurs(1, 2) && !joueurs[1].isRolling()) { //descendre
									bas[1] = true;
								}
								else if (code == keyJoueurs.getKeyJoueurs(1, 3) && !joueurs[1].isRolling()) { //gauche
									joueurs[1].setEnMouvement(true);
									gauche[1] = true;
								}
								else if (code == keyJoueurs.getKeyJoueurs(1, 4)) { //attaque
									attack[1] = true;										
								}
								else if (code == keyJoueurs.getKeyJoueurs(1, 5)) { //bloc									
									block[1] = true;
								} 		
							}
						}
						public void keyReleased(KeyEvent e){

							final int code = e.getKeyCode();
							if (code == keyJoueurs.getKeyJoueurs(1, 0)) { //saut
								haut[1] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(1, 1)) { //droite
								droite[1] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(1, 2)) { //descendre
								bas[1] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(1, 3)) { //gauche
								gauche[1] = false;
							}
							else if (code == keyJoueurs.getKeyJoueurs(1, 4)) { //attaque
								attack[1] = false;							
							}
							else if (code == keyJoueurs.getKeyJoueurs(1, 5)) { //bloc
								block[1] = false;
								if (joueurs[1].isBlocking())
									joueurs[1].setEtat(Etat.IMMOBILE);
								joueurs[1].setBlocking(false);
							}

						}
					});
				}
			}); threadKeyJ2.start();
		}
	}
	/**
	 * Les ecouteurs de la manette
	 */
	public void runTestControllers(){
		Thread threadC = new Thread(new Runnable(){
			public void run() {				
				if(!glfwInit()){
					throw new IllegalStateException("Failed to initialize GLFW!");
				}
				glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
				window = glfwCreateWindow(640, 480, "Test manettes", 0, 0);

				if(window == 0){
					throw new IllegalStateException("Failed to create window!");
				}

				GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
				glfwSetWindowPos(window, (videoMode.width()-640)/2,(videoMode.height()-480)/2);				

				int ip = controles[0] == Controle.MANETTE ? 1 : 2;
				System.out.println("Thread manette : joueur" + ip + " start.");
				while(!glfwWindowShouldClose(window) && runnable){
					glfwPollEvents();
					try {
						if (controles[0] == Controle.MANETTE  || (duo && controles[1] == Controle.MANETTE && controles[0] == Controle.CLAVIER)) {
							FloatBuffer sticks = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
							ByteBuffer boutons = glfwGetJoystickButtons(GLFW_JOYSTICK_1);	

							int j = controles[0] == Controle.MANETTE ? 0 : 1;

							if(bindings[j].isJoystick()) {
								if(sticks.get(0)>0.5 && !joueurs[j].isRolling()){ //droite
									joueurs[j].setEnMouvement(true);							
									droite[j] = true;
									gauche[j] = false;
								}
								else if(sticks.get(1)<-0.5 && !joueurs[j].isRolling()){ //descendre				
									bas[j] = true;
								}
								else if(sticks.get(0)<-0.5 && !joueurs[j].isRolling()){ //gauche
									joueurs[j].setEnMouvement(true);
									gauche[j] = true;
									droite[j] = false;
								} else{ //reset
									droite[j] = false;
									gauche[j] = false;
									bas[j] = false;
								}
							} else {
								if(boutons.get(11) == 1 && !joueurs[j].isRolling()){ //droite
									joueurs[j].setEnMouvement(true);	
									droite[j] = true;
									gauche[j] = false;
								}
								else if(boutons.get(12) == 1 && !joueurs[j].isRolling()){ //descendre				
									bas[j] = true;
								}
								else if(boutons.get(13) == 1 && !joueurs[j].isRolling()){ //gauche
									joueurs[j].setEnMouvement(true);
									gauche[j] = true;
									droite[j] = false;
								} else{ //reset
									droite[j] = false;
									gauche[j] = false;
									bas[j] = false;
								}
							}
							if((bindings[j].getSticks()[0] && sticks.get(bindings[j].getMouvements()[0]) > -1) ||
									(!bindings[j].getSticks()[0] && boutons.getInt(bindings[j].getMouvements()[0]) == 1)) { //saut
								if (!joueurs[j].isBlocking() && !joueurs[j].isAttacking() && !joueurs[j].isStunned()) {
									joueurs[j].setEtat(Etat.SAUT);
									joueurs[j].sauter();
									haut[j] = true;
								}
							}						
							else if ((bindings[j].getSticks()[1] && sticks.get(bindings[j].getMouvements()[1]) > -1) ||
									(!bindings[j].getSticks()[1] && boutons.getInt(bindings[j].getMouvements()[1]) == 1)) { //bloc
								block[j]  = true; 								
								attack[j] = false;
							} 
							else if ((bindings[j].getSticks()[2] && sticks.get(bindings[j].getMouvements()[2]) > -1) ||
									(!bindings[j].getSticks()[2] && boutons.getInt(bindings[j].getMouvements()[2]) == 1)) { //attaque 
								attack[j] = true;
								block[j]  = false;
							} 
							else if ((bindings[j].getSticks()[3] && sticks.get(bindings[j].getMouvements()[3]) > -1) ||
									(!bindings[j].getSticks()[3] && boutons.getInt(bindings[j].getMouvements()[3]) == 1)) { //special
								attack[j] = true;
								block[j]  = true;
							}
							else {
								if(block[j] && !joueurs[j].isRolling()) joueurs[j].setEtat(Etat.IMMOBILE);
								haut[j] = false;
								attack[j] = false;											
								block[j] = false;
								joueurs[j].setBlocking(false);
							}		
							if (boutons.get(7) == 1) {							
								if (animationEnCours) {
									arreter();
									menuPause.setVisible(true);
								} else {
									demarrer();
									menuPause.setVisible(false);
								}								
							}
						} 
					} catch (NullPointerException e ) {						
						JOptionPane.showMessageDialog(null, "Il n'y a pas de manette branché\n"
								+ "Le jeu va maintenant retourner au menu.", 
								"Erreur manette non branché...", JOptionPane.ERROR_MESSAGE);		
						fermerMonde();					

					}
					//reste a changer les controles pour fonctionner avec deux manettes 
					if (duo && controles[1] == Controle.MANETTE && controles[0] == Controle.MANETTE) {	
						try {					
							FloatBuffer sticks2 = glfwGetJoystickAxes(GLFW_JOYSTICK_2);
							ByteBuffer boutons2 = glfwGetJoystickButtons(GLFW_JOYSTICK_2);

							if(bindings[1].isJoystick()) {
								if(sticks2.get(0)>0.5 && !joueurs[1].isRolling()){ //droite						
									joueurs[1].setEnMouvement(true);							
									droite[1] = true;
									gauche[1] = false;
								}
								else if(sticks2.get(1)<-0.5 && !joueurs[1].isRolling()){ //descendre
									bas[1] = true;
								}
								else if(sticks2.get(0)<-0.5 && !joueurs[1].isRolling()){ //gauche
									joueurs[1].setEnMouvement(true);							
									gauche[1] = true;
									droite[1] = false;
								} else{ //reset
									droite[1] = false;
									gauche[1] = false;							
									bas[1] = false;
									joueurs[1].setEtat(Etat.IMMOBILE);
								}
							} else {
								if(boutons2.get(11) == 1 && !joueurs[1].isRolling()){ //droite
									joueurs[1].setEnMouvement(true);	
									droite[1] = true;
									gauche[1] = false;
								}
								else if(boutons2.get(12) == 1 && !joueurs[1].isRolling()){ //descendre				
									bas[1] = true;
								}
								else if(boutons2.get(13) == 1 && !joueurs[1].isRolling()){ //gauche
									joueurs[1].setEnMouvement(true);
									gauche[1] = true;
									droite[1] = false;
								} else{ //reset
									droite[1] = false;
									gauche[1] = false;
									bas[1] = false;
								}
							}
							if((bindings[1].getSticks()[0] && sticks2.get(bindings[1].getMouvements()[0]) > -1) ||
									(!bindings[1].getSticks()[0] && boutons2.getInt(bindings[1].getMouvements()[0]) == 1)){ //saut
								if (!joueurs[1].isBlocking() && !joueurs[1].isAttacking() && !joueurs[1].isStunned()) { //saut
									joueurs[1].setEtat(Etat.SAUT);
									joueurs[1].sauter();
									haut[1] = true;
								}
							} else if ((bindings[1].getSticks()[1] && sticks2.get(bindings[1].getMouvements()[1]) > -1) ||
									(!bindings[1].getSticks()[1] && boutons2.getInt(bindings[1].getMouvements()[1]) == 1)) { //bloc
								block[1]  = true; 
								attack[1] = false;						
							} else if ((bindings[1].getSticks()[2] && sticks2.get(bindings[1].getMouvements()[2]) > -1) ||
									(!bindings[1].getSticks()[2] && boutons2.getInt(bindings[1].getMouvements()[2]) == 1)) { //attaque
								attack[1] = true;							
								block[1]  = false;
							}

							else if ((bindings[1].getSticks()[3] && sticks2.get(bindings[1].getMouvements()[3]) > -1) ||
									(!bindings[1].getSticks()[3] && boutons2.getInt(bindings[1].getMouvements()[3]) == 1)) { //special
								attack[1] = true;
								block[1]  = true;
							}
							else {		
								if (block[1]) joueurs[1].setEtat(Etat.IMMOBILE);
								haut[1] = false;
								attack[1] = false;
								block[1] = false;

								joueurs[1].setBlocking(false);
							}

						} catch (NullPointerException e) {
							arreter();
							JOptionPane.showMessageDialog(frame.getContentPane(), "Il n'y a pas de deuxième manette branché\n"
									+ "Le jeu va maintenant retourner au menu.", 
									"Erreur manette non branché...", JOptionPane.ERROR_MESSAGE);

							fermerMonde();
						}
					}					
				}
				glfwTerminate();
			}
		});

		threadC.start();
	}	
	/**
	 * Arrete le thread des manettes
	 */
	private void arreterThreadManettes() {
		runnable = false;
		glfwDestroyWindow(window);

	}
	//debut ecouteurs joueur 1
	/**
	 * Ajoute un ecouteur pour le joueur 1 dans le monde
	 * @param objEcouteur Un objet ecouteur
	 */
	public void addMondeListenerJ1(MondeListenerJ1 objEcouteur) {
		OBJETS_ENREGISTRES_J1.add(MondeListenerJ1.class, objEcouteur);
	}
	/**
	 * Ajoute un ecouteur pour le joueur 1 dans le monde
	 * @param objEcouteurs Un objet ecouteur
	 */
	public void addMondeListenerInfosJ1(MondeListenerInfosJ1 objEcouteurs) {
		OBJETS_ENREGISTRES_INFOS_J1.add(MondeListenerInfosJ1.class, objEcouteurs);
	}
	/**
	 * Detecte les changements des points de vie du joueur 1
	 */
	private void leverEvenUpdateVieJ1() {
		for (MondeListenerInfosJ1 ecout : OBJETS_ENREGISTRES_INFOS_J1.getListeners(MondeListenerInfosJ1.class)) {
			ecout.updateVieJ1(joueurs[0].getPointsDeVie());
		}
	}
	/**
	 * Detecte les changements de l'experience du joueur 1
	 */
	private void leverEvenUpdateExpJ1() {
		for (MondeListenerInfosJ1 ecout : OBJETS_ENREGISTRES_INFOS_J1.getListeners(MondeListenerInfosJ1.class)) {
			ecout.experienceAjouteJ1(joueurs[0].getNiveau(), joueurs[0].getExperience(), joueurs[0].getPointsDeVie());
		}
	}
	/**
	 * Detecte les changements du score
	 */
	private void leverEvenUpdateScore() {
		for (MondeListenerInfosJ1 ecout : OBJETS_ENREGISTRES_INFOS_J1.getListeners(MondeListenerInfosJ1.class)) {
			ecout.scoreAugmente(score);
		}
	}
	/**
	 * Detecte les changements de tableaux pour le joueur 1
	 */
	private void leverEvenChangementTableauJ1() {	
		for(MondeListenerJ1 ecout : OBJETS_ENREGISTRES_J1.getListeners(MondeListenerJ1.class) ) {	
			int nbPlateformes = 0, nbMonstres = 0;
			for (int i = 0; i < listeTableaux.get(0).size(); i++) {
				nbPlateformes += listeTableaux.get(0).get(i).getListePlateformes().size();
				nbMonstres += listeTableaux.get(0).get(i).getListeMonstres().size();
			}			
			int biome = listeTableaux.get(0).get(4).getBiome();
			int danger = listeTableaux.get(0).get(4).getDanger();
			ecout.affichageScientifiqueChangementTableauJ1(distX[0], distY[0], biome, danger, difficulte, seed, nbPlateformes, nbMonstres, joueurs[0].getVitesseDeDeplacement(), joueurs[0].getHauteurDeSaut());
		}
	}
	/**
	 * Calcule les frames par seconde pour le joueur 1
	 */
	private void leverEvenCompteurFramesJ1() {
		for(MondeListenerJ1 ecout : OBJETS_ENREGISTRES_J1.getListeners(MondeListenerJ1.class) ) {
			ecout.framesParSecondeJ1(compteur);
		}
	}
	/**
	 * Detecte les changements de frame pour le joueur 1
	 */
	private void leverEvenChangementFrameJ1() {	
		for(MondeListenerJ1 ecout : OBJETS_ENREGISTRES_J1.getListeners(MondeListenerJ1.class) ) {
			ecout.affichageScientifiqueParFrameJ1(joueurs[0].getPosition(), joueurs[0].getVitesse(), joueurs[0].getAcceleration(), joueurs[0].getEtat(), score);
		}
	}	
	/**
	 * Detecte les changements de stats du joueur 1
	 */
	private void leverEvenStatsJ1() {	
		for(MondeListenerJ1 ecout : OBJETS_ENREGISTRES_J1.getListeners(MondeListenerJ1.class) ) {
			ecout.statsJoueur1(joueurs[0].getExperience(), joueurs[0].getPointsDeVie(), joueurs[0].getNiveau(), joueurs[0].getPointsAttribuables(), joueurs[0].getStats());
		}
	}
	//fin ecouteurs joueur 1

	//debut ecouteurs joueur 2
	/**
	 * Ajoute un ecouteur pour le joueur 2 dans le monde
	 * @param objEcouteur Un objet ecouteur
	 */
	public void addMondeListenerJ2(MondeListenerJ2 objEcouteur) {
		OBJETS_ENREGISTRES_J2.add(MondeListenerJ2.class, objEcouteur);
	}
	/**
	 * Ajoute un ecouteur pour le joueur 2 dans le monde
	 * @param objEcouteurs Un objet ecouteur
	 */
	public void addMondeListenerInfosJ2(MondeListenerInfosJ2 objEcouteurs) {
		OBJETS_ENREGISTRES_INFOS_J2.add(MondeListenerInfosJ2.class, objEcouteurs);
	}
	/**
	 * Detecte les changements des points de vie du joueur 2
	 */
	private void leverEvenUpdateVieJ2() {
		for (MondeListenerInfosJ2 ecout : OBJETS_ENREGISTRES_INFOS_J2.getListeners(MondeListenerInfosJ2.class)) {
			ecout.updateVieJ2(joueurs[1].getPointsDeVie());
		}
	}
	/**
	 * Detecte les changements d'experience du joueur 2
	 */
	private void leverEvenUpdateExpJ2() {
		for (MondeListenerInfosJ2 ecout : OBJETS_ENREGISTRES_INFOS_J2.getListeners(MondeListenerInfosJ2.class)) {
			ecout.experienceAjouteJ2(joueurs[1].getNiveau(), joueurs[1].getExperience(), joueurs[1].getPointsDeVie());
		}
	}
	/**
	 * Detecte les changements de tableaux pour le joueur 1
	 */
	private void leverEvenChangementTableauJ2() {	
		for(MondeListenerJ2 ecout : OBJETS_ENREGISTRES_J2.getListeners(MondeListenerJ2.class) ) {
			int nbPlateformes = 0, nbMonstres = 0;
			for (int i = 0; i < listeTableaux.get(1).size(); i++) {
				nbPlateformes += listeTableaux.get(1).get(i).getListePlateformes().size();
				nbMonstres += listeTableaux.get(1).get(i).getListeMonstres().size();
			}
			int biome = listeTableaux.get(1).get(4).getBiome();
			int danger = listeTableaux.get(1).get(4).getDanger();
			ecout.affichageScientifiqueChangementTableauJ2(distX[1], distY[1], biome, danger, difficulte, seed, nbPlateformes, nbMonstres, joueurs[1].getVitesseDeDeplacement(), joueurs[1].getHauteurDeSaut());
		}
	}
	/**
	 * Calcule les changements de frame pour le joueur 2
	 */
	private void leverEvenCompteurFramesJ2() {
		for(MondeListenerJ2 ecout : OBJETS_ENREGISTRES_J2.getListeners(MondeListenerJ2.class) ) {
			ecout.framesParSecondeJ2(compteur);
		}
	}
	/**
	 * Detecte les changements de frame pour le joueur 1
	 */
	private void leverEvenChangementFrameJ2() {	
		for(MondeListenerJ2 ecout : OBJETS_ENREGISTRES_J2.getListeners(MondeListenerJ2.class) ) {
			ecout.affichageScientifiqueParFrameJ2(joueurs[1].getPosition(), joueurs[1].getVitesse(), joueurs[1].getAcceleration(), compteur, joueurs[1].getEtat(), score);
		}
	}	
	/**
	 * Detecte les changements de stats du joueur 1
	 */
	private void leverEvenStatsJ2() {	
		for(MondeListenerJ2 ecout : OBJETS_ENREGISTRES_J2.getListeners(MondeListenerJ2.class) ) {
			ecout.statsJoueur2(joueurs[1].getExperience(), joueurs[1].getPointsDeVie(), joueurs[1].getNiveau(), joueurs[1].getPointsAttribuables(), joueurs[1].getStats());
		}
	}
	/**
	 * Sauvegarde une partie selon un nom donnee
	 * @param nomSauvegarde Le nom de la sauvegarde
	 */
	public void sauvegarder(String nomSauvegarde) {
		SaveJoueur[] saves = new SaveJoueur[joueurs.length];
		saves[0] = joueurs[0].getSaveJoueur();
		if (duo) saves[1] = joueurs[1].getSaveJoueur();
		ModeDeJeu mode = duo ? ModeDeJeu.COOP : ModeDeJeu.SOLO;

		Sauvegarde save = new Sauvegarde(nomSauvegarde, saves, seed.getSeedDepart(), mode, difficulte, controles, bindings, score, keyJoueurs, biomesDecouverts);
		save.sauvegarder();	
	}
	private void mortSolo() {
		dessinerMenuMort();
		nightAlpha = 225;
		menuMort.setVisible(true);
		menuMort.setScore(score+"");
		informationsJeu.setVisible(false);
		for (int i = 0; i < joueurs.length; i++)
			joueurs[i].getInventaire().setVisible(false);
	}
	/**
	 * Ferme le monde et tue tous les threads
	 */
	public void fermerMonde() {
		arreter();
		arreterThreadManettes();
		menuPrincipal.setVisible(true);
		if (frame.getClass() == FenetreModeSolo.class) ((FenetreModeSolo) frame).terminate();						
		else if (frame.getClass() == FenetreModeCoop.class) ((FenetreModeCoop) frame).terminate();
	}
	/**
	 * Chosis le multiplicateur de difficulte
	 */
	private void choisirMultiplicateurDeDifficulte() {
		if 		(difficulte == Difficulte.FACILE) multiplicateurDeDifficulte = 1;
		else if (difficulte == Difficulte.MOYEN) multiplicateurDeDifficulte = 1.5;
		else if (difficulte == Difficulte.DIFFICILE) multiplicateurDeDifficulte = 2;
		else multiplicateurDeDifficulte = 0;
	}
	/**
	 * Modifie la difficulte du monde
	 * @param diffi Une difficulte
	 */
	public void changerDifficulte(Difficulte diffi) {
		choisirMultiplicateurDeDifficulte();
		difficulte = diffi;
		if (duo) genererTableaux(Direction.AUCUNE, 2);		
		else     genererTableaux(Direction.AUCUNE, 1);
	}
	/**
	 * Affiche le nav mesh selon la condition
	 * @param afficherMesh La condition d'affichage
	 */
	public void afficherNavMesh(boolean afficherMesh) {
		for (int i = 0; i < listeTableaux.size(); i++) 
			for (int j = 0; j < listeTableaux.get(i).size(); j++) 
				listeTableaux.get(i).get(j).setVoirNavMesh(afficherMesh);
	}
	/**
	 * Affiche les hitbox des joueurs selon la condition
	 * @param afficherHitbox La condition d'affichage
	 */
	public void afficherHitboxJoueurs(boolean afficherHitbox) {
		for (int i = 0; i < joueurs.length; i++) 
			joueurs[i].setAfficherHitbox(afficherHitbox);
	}
	/**
	 * Affiche les hitbox des monstres selon la condition
	 * @param afficherHitbox La condition d'affichage
	 */
	public void afficherHitboxMonstres(boolean afficherHitbox) {
		for (int i = 0; i < listeTableaux.size(); i++) 
			for (int j = 0; j < listeTableaux.get(i).size(); j++)
				for (int k = 0; k < listeTableaux.get(i).get(j).getListeMonstres().size(); k++) 
					listeTableaux.get(i).get(j).getListeMonstres().get(k).setAfficherHitbox(afficherHitbox);
	}
	/**
	 * Affiche l'affichage scientifique selon la condition
	 * @param affichageScientifique La condition d'affichage
	 */
	public void affichageScientifique(boolean affichageScientifique) {
		if (duo) ((FenetreModeCoop)frame).afficherFenetreAffichageScientifique(affichageScientifique);
		else 	 ((FenetreModeSolo)frame).afficherFenetreAffichageScientifique(affichageScientifique);
	}
	/**
	 * Affiche les vecteurs d'acceleration 
	 * @param afficherVecteursAcceleration La condition d'afficahge
	 */
	public void afficherVecteursAcceleration(boolean afficherVecteursAcceleration) {
		this.afficherVecteursAcceleration = afficherVecteursAcceleration;
	}
	/**
	 * Affiche les vecteurs de vitesse
	 * @param afficherVecteursVitesse La condition d'afficahge
	 */
	public void afficherVecteursVitesse(boolean afficherVecteursVitesse) {
		this.afficherVecteursVitesse = afficherVecteursVitesse;
	}
	/**
	 * Active le mode invulnerable
	 * @param godmode La condition d'activation
	 */
	public void activerGodMode(boolean godmode) {
		for (int i = 0; i < joueurs.length; i++) 
			joueurs[i].setVulnerable(!godmode);
	}
	/**
	 * Donne l'acces pour spawn des items
	 * @param spawnAcces La condition d'acces
	 */
	public void itemSpawnAcces(boolean spawnAcces) {
		this.itemSpawnAcces = spawnAcces;
	}
	/**
	 * Envoye le nom d'une sauvegarde qui contient le nom des joueurs et le score
	 * @return nomSauvegarde Le nom de la sauvegarde
	 */
	public String getNomSauvegarde() {
		String nomSauvegarde = joueurs[0].getPersonnage().getNom();
		if (duo)
			nomSauvegarde += " - " + joueurs[1].getPersonnage().getNom();
		nomSauvegarde += " - " + score;

		return nomSauvegarde;
	}
	/**
	 * Envoye le joueur 1
	 * @return Le joueur 1
	 */
	public Joueur getJoueur1() {
		return joueurs[0];
	}
	/**
	 * Envoye le joueur 2
	 * @return Le joueur 2
	 */
	public Joueur getJoueur2() {
		return joueurs[1];
	}
	/**
	 * Envoye les bindings du clavier des joueurs
	 * @return
	 */
	public BindingsClavier getKeyJoueurs() {
		return keyJoueurs;
	}
	public BindingsManette getBindingsManetteJ1() {
		return bindings[0];
	}
	public BindingsManette getBindingsManetteJ2() {
		return bindings[1];
	}
	/**
	 * Definit la largeur du monde
	 * @param largeur La largeur
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public void setControleJ1(Controle controle) {
		this.controles[0] = controle;
		if (controles[0] == Controle.MANETTE)
			runTestControllers(); //demarre le thread des controles pour les manettes
	}
	public void setControleJ2(Controle controle) {
		this.controles[1] = controle;
		if (controles[1] == Controle.MANETTE)
			runTestControllers(); //verifier si pas deja demarre
	}
	public boolean isDuo() {
		return duo;
	}
	public JFrame getFrame() {
		return frame;
	}
	public App10Plateforme getMenuPrincipal() {
		return menuPrincipal;
	}
	public void setNightAlpha(int nightAlpha) {
		this.nightAlpha = nightAlpha;
	}
}
