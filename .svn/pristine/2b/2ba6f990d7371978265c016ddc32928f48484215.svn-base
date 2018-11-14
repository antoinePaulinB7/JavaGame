package aaplication;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_2;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickAxes;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickButtons;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickName;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.lwjgl.glfw.GLFWVidMode;

import bindings.BindingsClavier;
import bindings.BindingsManette;
import interfaces.Enumerations.Controle;

/**
 * Panel qui permet a l'utilisateur de modifier les controles soit du clavier ou de la manette
 * @author Mihai
 *
 */
public class PanelControlles extends JPanel {

	private static final long serialVersionUID = 1L;
	private Controle controle = Controle.CLAVIER;
	private Color couleurPanel = new Color(225, 30, 30);

	//variable manette
	private JButton imgClavier, imgManette;
	private boolean joystick = true;
	private BindingsManette bindings;
	private int actionManette;
	private JButton boutonActuelManette;
	private Set<JButton> boutonsManette = new HashSet<JButton>();
	private boolean modif = false;
	private boolean running = false;
	private long window;

	//variables clavier
	private int actionClavier;
	private int nbBoutons = 6;
	private JButton boutonActuelClavier;
	private int numJoueur;		  //joueur 1
	/*private int keyJoueurs[][] = {{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, 
		KeyEvent.VK_A, KeyEvent.VK_V, KeyEvent.VK_B},
			//joueur 2
			{KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, 
			KeyEvent.VK_LEFT, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2}};*/
	private BindingsClavier keyJoueurs;

	//composantes modifiables clavier
	private JButton btnGauche, btnDroite, btnDescendre, btnSaut, btnAttaque, btnBloc;
	private Set<JButton> boutonsClavier = new HashSet<JButton>();
	private JLabel lblGauche, lblDroite, lblDescend, lblSaut, lblAttaque, lblBloc;
	private JLabel  lblCombosPossibles, lblDash, lblDescDash, lblRoll, lblDescRoll, lblSpecial, lblDescSpecial;
	private JPanel panelClavier;

	//composantes modifiable manette
	private JButton imgJoystick, imgFleches, btnMSaut, btnSpecial, btnMAttaque, btnMBloc;
	private JLabel lblMSaut, lblMSpecial, lblMAttaque,lblMBloc;
	private JLabel lblMCombosPossibles, lblMDash, lblMRoll, lblMDescDash, lblMDescRoll;	
	private JPanel panelManette; 

	/**
	 * Constructeur du panel
	 * @param numJoueur Le numero du joueur
	 * @param keyJoueurs Les bindings du clavier du joueur
	 */
	public PanelControlles(int numJoueur, BindingsClavier keyJoueurs, BindingsManette bindings) {
		this.numJoueur = numJoueur;
		this.keyJoueurs = keyJoueurs;
		this.bindings = bindings;
		setBounds(4, 55, 450, 403);
		setLayout(null);		

		imgClavier = new JButton("Clavier");
		imgClavier.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imgClavier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setControle(Controle.CLAVIER);				
			}
		});
		imgClavier.setBounds(10, 11, 202, 100);
		add(imgClavier);

		imgManette = new JButton("Manette");
		imgManette.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imgManette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setControle(Controle.MANETTE);
				if (!running) {
					running = true;
					runManettes();				
				}
			}
		});
		imgManette.setBounds(238, 11, 202, 100);
		add(imgManette);
		
		//debut controlles clavier
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				verifyDupesClavier(key);				
				boutonActuelClavier.setText(KeyEvent.getKeyText(key));		
				boutonActuelClavier.setMargin(new Insets(0, 0, 0, 0));

				keyJoueurs.addBinding(numJoueur, actionClavier, key);
				lblDash.requestFocus(); //perdre le focus

			}
		});

		lblGauche = new JLabel("Gauche");
		lblGauche.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGauche.setBounds(10, 122, 91, 27);
		add(lblGauche);

		lblDroite = new JLabel("Droite");
		lblDroite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDroite.setBounds(10, 169, 91, 27);
		add(lblDroite);

		lblDescend = new JLabel("Descend");
		lblDescend.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescend.setBounds(10, 214, 91, 27);
		add(lblDescend);

		lblSaut = new JLabel("Sauter");
		lblSaut.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSaut.setBounds(238, 122, 91, 27);
		add(lblSaut);

		lblAttaque = new JLabel("Attaque");
		lblAttaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttaque.setBounds(238, 169, 91, 27);
		add(lblAttaque);

		lblBloc = new JLabel("Bloquer");
		lblBloc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBloc.setBounds(238, 214, 91, 27);
		add(lblBloc);

		btnGauche = new JButton("A");
		btnGauche.setFont(new Font("Tahoma", Font.BOLD, 14));
		if(numJoueur == 1) btnGauche.setText("Gauche");
		btnGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				requestFocus();
				boutonActuelClavier = btnGauche;
				actionClavier = 3;
			}
		});
		btnGauche.setBounds(74, 122, 138, 23);
		add(btnGauche);
		boutonsClavier.add(btnGauche);

		btnDroite = new JButton("D");
		btnDroite.setFont(new Font("Tahoma", Font.BOLD, 14));
		if(numJoueur == 1) btnDroite.setText("Droite");
		btnDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocus();
				boutonActuelClavier = btnDroite;
				actionClavier = 1;
			}
		});
		btnDroite.setBounds(74, 171, 138, 23);
		add(btnDroite);
		boutonsClavier.add(btnDroite);

		btnDescendre = new JButton("S");
		btnDescendre.setFont(new Font("Tahoma", Font.BOLD, 14));
		if(numJoueur == 1) btnDescendre.setText("Bas");
		btnDescendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocus();
				boutonActuelClavier = btnDescendre;
				actionClavier = 2;
			}
		});
		btnDescendre.setBounds(74, 216, 138, 23);
		add(btnDescendre);
		boutonsClavier.add(btnDescendre);

		btnSaut = new JButton("W");
		btnSaut.setFont(new Font("Tahoma", Font.BOLD, 14));
		if(numJoueur == 1) btnSaut.setText("Haut");
		btnSaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocus();
				boutonActuelClavier = btnSaut;
				actionClavier = 0;
			}
		});
		btnSaut.setBounds(302, 124, 138, 23);
		add(btnSaut);
		boutonsClavier.add(btnSaut);

		btnAttaque = new JButton("V");
		btnAttaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		if(numJoueur == 1) btnAttaque.setText("Pavé numérique-1");
		btnAttaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocus();
				boutonActuelClavier = btnAttaque;
				actionClavier = 4;
			}
		});
		btnAttaque.setMargin(new Insets(0, 0, 0, 0));
		btnAttaque.setBounds(302, 171, 138, 23);
		add(btnAttaque);
		boutonsClavier.add(btnAttaque);

		btnBloc = new JButton("B");
		btnBloc.setFont(new Font("Tahoma", Font.BOLD, 14));
		if(numJoueur == 1) btnBloc.setText("Pavé numérique-2"); 
		btnBloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocus();
				boutonActuelClavier = btnBloc;
				actionClavier = 5;
			}
		});
		btnBloc.setMargin(new Insets(0, 0, 0, 0));
		btnBloc.setBounds(302, 216, 138, 23);
		add(btnBloc);
		boutonsClavier.add(btnBloc);

		lblCombosPossibles = new JLabel("Combos possibles:");
		lblCombosPossibles.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCombosPossibles.setBounds(144, 243, 180, 50);
		add(lblCombosPossibles);

		panelClavier = new JPanel();
		panelClavier.setBounds(17, 281, 412, 112);
		panelClavier.setBackground(couleurPanel);
		add(panelClavier);
		panelClavier.setLayout(new GridLayout(0, 2, 0, 0));

		lblDash = new JLabel("Attaque propuls\u00E9 --->");
		lblDash.setHorizontalAlignment(SwingConstants.CENTER);			
		lblDash.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClavier.add(lblDash);

		lblDescDash = new JLabel("Attaque + gauche ou droite");
		lblDescDash.setHorizontalAlignment(SwingConstants.CENTER);			
		lblDescDash.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClavier.add(lblDescDash);

		lblRoll = new JLabel("Rouler --->");
		lblRoll.setHorizontalAlignment(SwingConstants.CENTER);			
		lblRoll.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClavier.add(lblRoll);

		lblDescRoll = new JLabel("Bloc + gauche ou droite");
		lblDescRoll.setHorizontalAlignment(SwingConstants.CENTER);			
		lblDescRoll.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClavier.add(lblDescRoll);

		lblSpecial = new JLabel("Attaque speciale --->");
		lblSpecial.setHorizontalAlignment(SwingConstants.CENTER);			
		lblSpecial.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClavier.add(lblSpecial);

		lblDescSpecial = new JLabel(" Attaque + bloc");
		lblDescSpecial.setHorizontalAlignment(SwingConstants.CENTER);			
		lblDescSpecial.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClavier.add(lblDescSpecial);
		//fin controlles clavier
		
		//debut controlles manette
		imgJoystick = new JButton("Joystick");
		imgJoystick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joystick = true;
				bindings.setJoystick(joystick);
			}
		});
		imgJoystick.setBounds(10, 132, 120, 115);
		add(imgJoystick);

		imgFleches = new JButton("Fleches");
		imgFleches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joystick = false;
				bindings.setJoystick(joystick);
			}
		});
		imgFleches.setBounds(10, 275, 120, 115);
		add(imgFleches);

		lblMSaut = new JLabel("Saut");
		lblMSaut.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMSaut.setBounds(158, 122, 73, 23);
		add(lblMSaut);

		lblMSpecial = new JLabel("Special");
		lblMSpecial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMSpecial.setBounds(158, 224, 73, 23);
		add(lblMSpecial);

		lblMAttaque = new JLabel("Attaque");
		lblMAttaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMAttaque.setBounds(158, 156, 73, 23);
		add(lblMAttaque);

		lblMBloc = new JLabel("Bloquer");
		lblMBloc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMBloc.setBounds(158, 190, 73, 23);
		add(lblMBloc);

		btnMSaut = new JButton("A");
		btnMSaut.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMSaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonActuelManette = btnMSaut;
				modif = true;
				actionManette = 0;
			}
		});
		btnMSaut.setBounds(253, 122, 173, 23);
		add(btnMSaut);
		boutonsManette.add(btnMSaut);

		btnSpecial = new JButton("Y");
		btnSpecial.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonActuelManette = btnSpecial;
				modif = true;
				actionManette = 3;
			}
		});
		btnSpecial.setBounds(253, 224, 173, 23);
		add(btnSpecial);
		boutonsManette.add(btnSpecial);

		btnMAttaque = new JButton("X");
		btnMAttaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMAttaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonActuelManette = btnMAttaque;
				modif = true;
				actionManette = 2;
			}
		});
		btnMAttaque.setBounds(253, 156, 173, 23);
		add(btnMAttaque);
		boutonsManette.add(btnMAttaque);

		btnMBloc = new JButton("B");
		btnMBloc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMBloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonActuelManette = btnMBloc;
				modif = true;
				actionManette = 1;
			}
		});
		btnMBloc.setBounds(253, 190, 173, 23);
		add(btnMBloc);
		boutonsManette.add(btnMBloc);

		panelManette = new JPanel();
		panelManette.setBounds(140, 275, 300, 115);
		panelManette.setBackground(couleurPanel);
		add(panelManette);
		panelManette.setLayout(new GridLayout(2, 2, 0, 0));

		lblMDash = new JLabel("Attaque propuls\u00E9 -->");
		lblMDash.setHorizontalAlignment(SwingConstants.CENTER);
		lblMDash.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelManette.add(lblMDash);

		lblMDescDash = new JLabel("<html>Attaque + <br>gauche ou droite </html>");
		lblMDescDash.setHorizontalAlignment(SwingConstants.CENTER);
		lblMDescDash.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelManette.add(lblMDescDash);

		lblMRoll = new JLabel("Rouler -->");
		lblMRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblMRoll.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelManette.add(lblMRoll);

		lblMDescRoll = new JLabel("<html>Bloc + <br>gauche ou droite</html>");
		lblMDescRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblMDescRoll.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelManette.add(lblMDescRoll);

		lblMCombosPossibles = new JLabel("Combos possibles");
		lblMCombosPossibles.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMCombosPossibles.setBounds(207, 244, 207, 40);
		add(lblMCombosPossibles);
		//fin controle manette
		
		cacherComposantes(controle);
	}
	/**
	 * Definit le type de controle 
	 * @param controle Un type de controle
	 */
	private void setControle(Controle controle) {
		this.controle = controle;
		cacherComposantes(controle);
	} 
	/**
	 * Cache les composantes du controle choisi
	 * @param controle Un controle
	 */
	private void cacherComposantes(Controle controle) {
		if (controle == Controle.CLAVIER) {
			//cacher composantes manette
			panelManette.setVisible(false);
			lblMCombosPossibles.setVisible(false);
			imgJoystick.setVisible(false);
			imgFleches.setVisible(false);
			btnMSaut.setVisible(false);
			btnSpecial.setVisible(false);
			btnMAttaque.setVisible(false);
			btnMBloc.setVisible(false);
			lblMSaut.setVisible(false);
			lblMSpecial.setVisible(false);
			lblMAttaque.setVisible(false);
			lblMBloc.setVisible(false);

			//afficher composantes clavier
			boutonsClavier.forEach(bouton -> bouton.setVisible(true));
			panelClavier.setVisible(true);
			lblCombosPossibles.setVisible(true);
			lblGauche.setVisible(true);
			lblDroite.setVisible(true);
			lblDescend.setVisible(true);
			lblSaut.setVisible(true);
			lblAttaque.setVisible(true);
			lblBloc.setVisible(true);	

		} else {
			//cacher composantes clavier
			boutonsClavier.forEach(bouton -> bouton.setVisible(false));
			panelClavier.setVisible(false);
			lblCombosPossibles.setVisible(false);
			lblGauche.setVisible(false);
			lblDroite.setVisible(false);
			lblDescend.setVisible(false);
			lblSaut.setVisible(false);
			lblAttaque.setVisible(false);
			lblBloc.setVisible(false);	

			//afficher composantes manette
			panelManette.setVisible(true);
			lblMCombosPossibles.setVisible(true);
			imgJoystick.setVisible(true);
			imgFleches.setVisible(true);
			btnMSaut.setVisible(true);
			btnSpecial.setVisible(true);
			btnMAttaque.setVisible(true);
			btnMBloc.setVisible(true);
			lblMSaut.setVisible(true);
			lblMSpecial.setVisible(true);
			lblMAttaque.setVisible(true);
			lblMBloc.setVisible(true);
		}		
	}
	/**
	 * Envoye le type de controle
	 * @return controle Le type de controle
	 */
	public Controle getControle() {
		return controle;
	}
	/**
	 * Reinitialiser les controles
	 */
	public void reinitialiser() {
		controle = Controle.CLAVIER;
		keyJoueurs = new BindingsClavier();
		bindings = new BindingsManette(true);		
	}

	//debut clavier
	/**
	 * Verifie pour qu'il n'y a pas de doublons des controles du clavier
	 * @param key L'index du key a verifier
	 */
	private void verifyDupesClavier(int key) {
		for (int i = 0; i < nbBoutons; i++) {
			if(keyJoueurs.getKeyJoueurs(numJoueur, i) == key) {
				keyJoueurs.addBinding(numJoueur, i, 0);
			}
		}	
		for (JButton bouton : boutonsClavier)			
			if (bouton.getText().equals(KeyEvent.getKeyText(key))) 
				bouton.setText("");	
	}
	/**
	 * Verifie s'il y a des controles de clavier nulles
	 * @return S'il y a des controles de clavier nulles
	 */
	public boolean existeControllesNulles() {
		for(int i = 0; i < nbBoutons; i++) 
			if (keyJoueurs.getKeyJoueurs(numJoueur, i) == 0) 
				return true;				
		return false;
	}
	//fin clavier 
	 
	//debut manette
	/**
	 * Verifie s'il existe des controles de manette nulles
	 * @return s'il existe des controles de manette nulles
	 */
	public boolean existeControllesManetteNules() {
		for(JButton bouton : boutonsManette)			
			if (bouton.getText().equals(""))
				return true;				
		return false;
	}
	/**
	 * Verifie s'il y a des doublons des controles de la manette
	 * @param texte Le doublon a verifier
	 */
	private void verifierDupesManette(String texte) {		
		int i = 0;
		for (JButton bouton : boutonsManette) {			
			if (bouton.getText().equals(texte)) {
				bindings.setMouvements(i, -1);
				bouton.setText("");	
			}			
			i++;
		}

	}	
	/**
	 * Envoye les bindings de la manette
	 * @return bindings Les bindings de la manette
	 */
	public BindingsManette getBindingsManette() {
		return bindings;
	}
	/**
	 * Ferme le thread qui ecoute les touches de la manette
	 */
	public void fermerThreadManettes() {
		if (running) {
			running = false;
			glfwDestroyWindow(window);
			System.out.println("terminated thread manettes menu");
		}	
	}
	/**
	 * Demarre le thread d'ecouteurs de la manette
	 */
	private void runManettes() {
		Thread threadManettes = new Thread(new Runnable() {
			public void run() {
				if(!glfwInit())	{
					throw new IllegalStateException("Failed to initialize GLFW!");
				}
				glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
				window = glfwCreateWindow(640, 480, "Test manettes", 0, 0);

				if(window == 0) {
					throw new IllegalStateException("Failed to create window!");
				}
				GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
				glfwSetWindowPos(window, (videoMode.width()-640)/2,(videoMode.height()-480)/2);
				System.out.println("Thread manettes menu started");
				while(running/*!glfwWindowShouldClose(window)*/){
					glfwPollEvents();
					FloatBuffer s = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
					ByteBuffer b = glfwGetJoystickButtons(GLFW_JOYSTICK_1);
					if (boutonActuelManette != null && modif) {
						String texte;
						if (b.get(0) == 1) {
							texte = "A";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);		
							modif = false;
							bindings.addBinding(actionManette, false, 0);
						} else if (b.get(1) == 1) {
							texte = "B";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);		
							modif = false;
							bindings.addBinding(actionManette, false, 1);
						} else if (b.get(2) == 1) {
							texte = "X";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 2);
						} else if (b.get(3) == 1) {
							texte = "Y";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 3);
						} else if (b.get(4) == 1) {
							texte = "Bouton gauche";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 4);
						} else if (b.get(5) == 1) {
							texte = "Bouton droite";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 5);
						} else if (b.get(8) == 1) {
							texte = "Manette gauche"; 
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 8);
						} else if (b.get(9) == 1) {
							texte = "Manette droite";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 9);
						} else if (b.get(10) == 1 && joystick) {
							texte = "Fleche haut";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 10);
						} else if (b.get(11) == 1 && joystick) {
							texte = "Fleche droite";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 11);
						} else if (b.get(12) == 1 && joystick) {
							texte = "Fleche bas";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 12);
						} else if (b.get(13) == 1 && joystick) {
							texte = "Fleche gauche";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, false, 13);
						} else if (s.get(4) == 1 ) {
							texte = "Gâchette gauche";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false;
							bindings.addBinding(actionManette, true, 4);
						} else if (s.get(5) == 1) {
							texte = "Gâchette droite";
							verifierDupesManette(texte);
							boutonActuelManette.setText(texte);
							modif = false; 
							bindings.addBinding(actionManette, true, 5);
						}						
					}

					//System.out.println(boutons.get(7));
					//System.out.println(boutons.hasArray());

					String controller2 = glfwGetJoystickName(GLFW_JOYSTICK_2);
					//System.out.println(controller2);
					if(controller2 != null){
						FloatBuffer sticks2 = glfwGetJoystickAxes(GLFW_JOYSTICK_2);
						System.out.println(sticks2.get(0));
						System.out.println(sticks2.get(1));

						ByteBuffer boutons2 = glfwGetJoystickButtons(GLFW_JOYSTICK_2);
						System.out.println(boutons2.getInt(0));
					}
				}
				//	glfwTerminate();
			}

		}); threadManettes.start();
	}
}
