package aaplication;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bindings.BindingsClavier;
import bindings.BindingsManette;
import entites.Joueur;
import entites.Sprite;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import interfaces.Enumerations.ModeDeJeu;
import menus.PanelAide;
import personnages.Personnage;
import save.LireSauvegarde;
import save.Sauvegarde;
import save.SaveJoueur;
/**
 * Classe pour le JFrame du menu de lancement
 * @author Olivier
 *
 */

public class App10Plateforme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MenuJouer menuJouer;
	private MenuDepart menuDepart;
	private MenuNouvellePartie menuNouvellePartie;
	private MenuControlles menuControlles;
	private MenuContinuer menuContinuer;
	private MenuOptions menuOptions;
	private MenuCreationPersonnage menuCreation;
	private MenuAide menuAide;
	private static App10Plateforme frame;
	private CardLayout cardLayout;
	private Color couleurFondDEcran = new Color(178, 34, 34);
	private Color couleurPanels = new Color(225, 30, 30);
	private FloatControl gainControl;
	private double gain;
	private Float dB;
	private Clip clip;
	public Font base, title;

	/**
	 * Demarre l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new App10Plateforme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creation du frame de l'application
	 */
	public App10Plateforme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		//centre le jframe peut importe la taille de l'ecran
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Jeu de plateformes");
		setResizable(false);


		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("music/MusicMenu.wav"));
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		clip = null;
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
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gain = 0.5;
	    dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
	    gainControl.setValue(dB);
	    //System.out.println(gainControl.getMinimum() + ", " + gainControl.getMaximum());

		InputStream is = getClass().getClassLoader().getResourceAsStream("Pixellari.ttf");
		try {
			base = Font.createFont(Font.TRUETYPE_FONT, is);
			base = base.deriveFont(23f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		is = getClass().getClassLoader().getResourceAsStream("upheavtt.ttf");

		try {
			title = Font.createFont(Font.TRUETYPE_FONT, is);
			title = title.deriveFont(50f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setFont(base);

		contentPane = new JPanel();
		contentPane.setBackground(couleurFondDEcran);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout = new CardLayout());

		menuDepart = new MenuDepart();
		menuJouer = new MenuJouer();
		menuNouvellePartie = new MenuNouvellePartie();
		menuControlles = new MenuControlles();
		menuContinuer = new MenuContinuer();
		menuOptions = new MenuOptions();
		menuAide = new MenuAide();
		menuCreation = new MenuCreationPersonnage();


		contentPane.add(menuDepart, "menuDepart");
		contentPane.add(menuJouer, "menuJouer");
		contentPane.add(menuNouvellePartie, "menuNouvellePartie");
		contentPane.add(menuControlles, "menuControles");
		contentPane.add(menuContinuer, "menuContinuer");
		contentPane.add(menuOptions, "menuOptions");
		contentPane.add(menuAide, "menuAide");
		contentPane.add(menuCreation, "menuCreation");
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);

	}
	/**
	 * Créer le du départ
	 * @author Olivier
	 *
	 */
	public class MenuDepart extends JPanel {

		private static final long serialVersionUID = 1L;

		/**
		 * Creation du panel menu 1
		 */
		public MenuDepart() {

			setLayout(null);
			setBounds(0, 0, 100, 650);
			setBackground(couleurFondDEcran);


			JLabel lblTitre = new JLabel("Qu\u00EAte vers l'infini");
			lblTitre.setFont(title);
			lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitre.setBounds(224, 72, 540, 70);
			add(lblTitre);

			JButton btnJouer = new JButton("Jouer");
			btnJouer.addActionListener(new ActionListener() {
				/**
				 * Ouvre le menu Jouer
				 */
				public void actionPerformed(ActionEvent e) {
					//debut
					cardLayout.show(contentPane, "menuJouer");
					//fin
				}
			});
			btnJouer.setFont(base.deriveFont(32f));
			btnJouer.setBounds(380, 190, 225, 80);
			add(btnJouer);

			JButton btnOptions = new JButton("Options");
			btnOptions.addActionListener(new ActionListener() {
				/**
				 * Ouvre le menu Options
				 */
				public void actionPerformed(ActionEvent e) {
					//debut
					cardLayout.show(contentPane, "menuOptions");
					//fin
				}
			});
			btnOptions.setFont(base);
			btnOptions.setBounds(398, 290, 185, 60);
			add(btnOptions);

			JButton btnAide = new JButton("Aide");
			btnAide.addActionListener(new ActionListener() {
				/**
				 * Ouvre le menu Aide
				 */
				public void actionPerformed(ActionEvent arg0) {
					//debut
					cardLayout.show(contentPane, "menuAide");
					//fin
				}
			});
			btnAide.setFont(base);
			btnAide.setBounds(398, 370, 185, 60);
			add(btnAide);

			JButton btnQuitter = new JButton("Quitter");
			btnQuitter.addActionListener(new ActionListener() {
				/**
				 * Ferme l'application
				 */
				public void actionPerformed(ActionEvent arg0) {
					//debut
					System.exit(0);
					//fin
				}
			});
			btnQuitter.setFont(base);
			btnQuitter.setBounds(398, 450, 185, 60);
			add(btnQuitter);
		}
	}
	/**
	 * Créer le menu Jouer
	 * @author Olivier
	 *
	 */
	public class MenuJouer extends JPanel {

		private static final long serialVersionUID = 1L;

		/**
		 * Creation du panel menu joueur
		 */
		public MenuJouer() {
			setLayout(null);
			setBounds(0, 0, 1000, 650);
			setBackground(couleurFondDEcran);

			JLabel lblJouer = new JLabel("Jouer");
			lblJouer.setFont(title.deriveFont(55f));
			lblJouer.setHorizontalAlignment(SwingConstants.CENTER);
			lblJouer.setBounds(222, 70, 540, 70);
			add(lblJouer);

			JButton btnNouvPartie = new JButton("Nouvelle Partie");
			btnNouvPartie.addActionListener(new ActionListener() {
				/**
				 * Ouvre le menu Nouvelle Partie
				 */
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(contentPane, "menuNouvellePartie");
				}
			});
			btnNouvPartie.setFont(base);
			btnNouvPartie.setBounds(378, 200, 225, 60);
			add(btnNouvPartie);

			JButton btnContinuer = new JButton("Continuer");
			btnContinuer.addActionListener(new ActionListener() {
				/**
				 * Ouvre le menu Continuer
				 */
				public void actionPerformed(ActionEvent e) {
					menuContinuer.refreshFolder();
					cardLayout.show(contentPane, "menuContinuer");					
				}
			});
			btnContinuer.setFont(base);
			btnContinuer.setBounds(378, 280, 225, 60);
			add(btnContinuer);

			JButton btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {
				/**
				 * Retourne au menu précédent
				 */
				public void actionPerformed(ActionEvent e) {
					//debut
					cardLayout.show(contentPane, "menuDepart");
					//fin
				}
			});
			btnRetour.setFont(base.deriveFont(20f));
			btnRetour.setBounds(40, 510, 153, 60);
			add(btnRetour);
		}
	}
	/**
	 * Créer le menu Nouvelle Partie
	 * @author Olivier
	 *
	 */
	public class MenuNouvellePartie extends JPanel {

		private static final long serialVersionUID = 1L;
		private final ButtonGroup buttonGroup = new ButtonGroup();
		private JTextField textFieldSeed;
		private Difficulte difficulte = Difficulte.MOYEN;
		private ModeDeJeu mode = ModeDeJeu.SOLO;

		/**
		 * Creation du panel nouvelle partie
		 */
		public MenuNouvellePartie() {
			setLayout(null);
			setBounds(0, 0, 1000, 650);
			setBackground(couleurFondDEcran);

			JLabel lblNouvPartTitre = new JLabel("Nouvelle Partie");
			lblNouvPartTitre.setFont(title);
			lblNouvPartTitre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNouvPartTitre.setBounds(230, 70, 540, 70);
			add(lblNouvPartTitre);

			JButton btnSoloCoop = new JButton("Solo");
			btnSoloCoop.setFont(base);
			btnSoloCoop.setBounds(388, 178, 225, 60);
			btnSoloCoop.addActionListener(new ActionListener() {
				/**
				 * Écoute quelle mode est choisi entre Solo et Coop
				 */
				public void actionPerformed(ActionEvent e) {
					//debut
					if(btnSoloCoop.getText().equals("Solo")){
						btnSoloCoop.setText("Coop");
						mode = ModeDeJeu.COOP;
					} else {
						btnSoloCoop.setText("Solo");
						mode = ModeDeJeu.SOLO;
					} 						
					menuCreation.setMode(mode);		
					menuControlles.setMode(mode);
					menuCreation.setBtnCommencerDisabled();
					repaint();

					//fin
				}
			});			
			add(btnSoloCoop);

			JPanel panelDifficulte = new JPanel();
			panelDifficulte.setBounds(388, 260, 225, 90);		
			panelDifficulte.setBackground(couleurPanels);
			panelDifficulte.setLayout(null);
			add(panelDifficulte);

			JRadioButton rdbtnFacile = new JRadioButton("Facile");
			rdbtnFacile.addActionListener(new ActionListener() {
				/**
				 * Écoute quelle difficulté est choisie
				 */
				public void actionPerformed(ActionEvent e) {
					difficulte = Difficulte.FACILE;
				}
			});
			rdbtnFacile.setBounds(131, 9, 75, 25);
			rdbtnFacile.setFont(base.deriveFont(13f));
			rdbtnFacile.setBackground(couleurPanels);
			panelDifficulte.add(rdbtnFacile);
			buttonGroup.add(rdbtnFacile);

			JRadioButton rdbtnMoyen = new JRadioButton("Moyen");
			rdbtnMoyen.addActionListener(new ActionListener() {
				/**
				 * Écoute quelle difficulté est choisie
				 */
				public void actionPerformed(ActionEvent e) {
					difficulte = Difficulte.MOYEN;
				}
			});
			rdbtnMoyen.setBounds(131, 34, 75, 25);
			panelDifficulte.add(rdbtnMoyen);
			rdbtnMoyen.setSelected(true);
			rdbtnMoyen.setBackground(couleurPanels);
			rdbtnMoyen.setFont(base.deriveFont(13f));
			buttonGroup.add(rdbtnMoyen);

			JRadioButton rdbtnDifficile = new JRadioButton("Difficile");
			rdbtnDifficile.addActionListener(new ActionListener() {
				/**
				 * Écoute quelle difficulté est choisie
				 */
				public void actionPerformed(ActionEvent e) {
					difficulte = Difficulte.DIFFICILE;
				}
			});
			rdbtnDifficile.setBounds(131, 61, 85, 25);
			rdbtnDifficile.setBackground(couleurPanels);
			rdbtnDifficile.setFont(base.deriveFont(13f));
			panelDifficulte.add(rdbtnDifficile);
			buttonGroup.add(rdbtnDifficile);

			JLabel lblDifficult = new JLabel("Difficult\u00E9:");
			lblDifficult.setBounds(5, 11, 125, 71);
			panelDifficulte.add(lblDifficult);
			lblDifficult.setFont(base.deriveFont(22f));

			JPanel panelSeed = new JPanel();
			panelSeed.setBounds(388, 370, 225, 90);
			panelSeed.setBackground(couleurPanels);
			panelSeed.setLayout(null);
			add(panelSeed);			

			textFieldSeed = new JTextField("123");
			textFieldSeed.setBounds(103, 24, 110, 36);
			textFieldSeed.setFont(base.deriveFont(22f));
			panelSeed.add(textFieldSeed);
			textFieldSeed.setColumns(10);

			JLabel lblSeed = new JLabel("Seed:");
			lblSeed.setBounds(4, 24, 110, 36);
			panelSeed.add(lblSeed);
			lblSeed.setFont(base.deriveFont(22f));

			JButton btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {
				/**
				 * Retourne au menu précédent
				 */
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(contentPane, "menuJouer");
				}
			});
			btnRetour.setFont(base.deriveFont(20f));
			btnRetour.setBounds(40, 510, 153, 60);
			add(btnRetour);

			JButton btnContinuer = new JButton("Continuer");
			btnContinuer.addActionListener(new ActionListener() {
				/**
				 * Permet de continuer au menu Controles
				 */
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(contentPane, "menuControles");
					menuCreation.setMode(mode);
				}
			});
			btnContinuer.setFont(base.deriveFont(20f));
			btnContinuer.setBounds(792, 510, 153, 60);
			add(btnContinuer);
		}
		/**
		 * Envoye la difficulte
		 * @return difficulte La difficulte
		 */
		public Difficulte getDifficulte() {
			return difficulte;
		}
		/**
		 * Envoye le seed
		 * @return Le seed
		 */
		public int getSeed() {
			String seed = textFieldSeed.getText();
			try { //si le seed est un entier, il envoye l'entier 
				return Integer.parseInt(seed);
			} catch (NumberFormatException e) { //si le seed est une chaine de caracteres, il envoye son hashCode
				return seed.hashCode();
			}			
		}
		/**
		 * Envoye le mode de jeu
		 * @return mode Le mode de jeu
		 */
		public ModeDeJeu getMode() {
			return mode;
		}
	}
	/**
	 * Créer le menu Controles
	 * @author Olivier
	 *
	 */
	public class MenuControlles extends JPanel {

		private static final long serialVersionUID = 1L;
		private PanelControlles panelJ1, panelJ2;
		private ModeDeJeu mode = ModeDeJeu.SOLO;
		private BindingsClavier keyJoueurs = new BindingsClavier();
		private BindingsManette bindingsJ1 = new BindingsManette(true);
		private BindingsManette bindingsJ2 = new BindingsManette(true);
		
		/**
		 * Creation du panel menu controles
		 */
		public MenuControlles() {
			setLayout(null);
			setBounds(0, 0, 1000, 650);
			setBackground(couleurFondDEcran);

			panelJ1 = new PanelControlles(0, keyJoueurs, bindingsJ1);
			panelJ1.setBounds(274, 90, 450, 403);
			panelJ1.setBackground(couleurPanels);
			panelJ1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			add(panelJ1);

			JLabel lblChoixControles = new JLabel("Choisissez vos contr\u00F4les");
			lblChoixControles.setFont(title);
			lblChoixControles.setHorizontalAlignment(SwingConstants.CENTER);
			lblChoixControles.setBounds(128, 12, 750, 51);
			add(lblChoixControles);

			JButton btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {
				/**
				 * retourne au menu précédent
				 */
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(contentPane, "menuNouvellePartie");
				}
			});
			btnRetour.setFont(base.deriveFont(20f));
			btnRetour.setBounds(40, 510, 153, 60);
			add(btnRetour);

			JButton btnContinuer = new JButton("Continuer");
			btnContinuer.addActionListener(new ActionListener() {
				/**
				 * Ouvre le créateur de personnage
				 */
				public void actionPerformed(ActionEvent e) {				
					if ((panelJ1.getControle() == Controle.CLAVIER && panelJ1.existeControllesNulles()) ||
							(mode == ModeDeJeu.COOP && panelJ2.getControle() == Controle.CLAVIER && panelJ2.existeControllesNulles())) 
						JOptionPane.showMessageDialog(null, "Il y a des cases de controlles de clavier vides.\n"
								+ "Veuillez remplir toutes les cases avant de continuer.", 
								"Cases vides...", JOptionPane.WARNING_MESSAGE);
					else if ((panelJ1.getControle() == Controle.MANETTE && panelJ1.existeControllesManetteNules()) ||
							(mode == ModeDeJeu.COOP && panelJ2.getControle() == Controle.MANETTE && panelJ2.existeControllesManetteNules()))
						JOptionPane.showMessageDialog(null, "Il y a des cases de controlles de manette vides.\n"
								+ "Veuillez remplir toutes les cases avant de continuer.", 
								"Cases vides...", JOptionPane.WARNING_MESSAGE);
					else 
						cardLayout.show(contentPane, "menuCreation");					
				}
			});
			btnContinuer.setFont(base.deriveFont(20f));
			btnContinuer.setBounds(792, 510, 153, 60);
			add(btnContinuer);

		}
		/**
		 * Envoye les controles des joueurs
		 * @return controles Les controles
		 */
		public Controle[] getControles() {
			int nbJoueurs = mode == ModeDeJeu.SOLO ? 1 : 2;
			Controle[] controles = new Controle[nbJoueurs]; 
			controles[0] = panelJ1.getControle();
			if (nbJoueurs == 2) controles[1] = panelJ2.getControle();

			return controles;

		}
		/**
		 * Envoye les bindings du clavier des joueurs
		 * @return keyJoueurs Les bindings du clavier
		 */
		public BindingsClavier getKeyJoueurs() {
			return keyJoueurs;
		}
		/**
		 * Envoye les bindings des manettes des joueurs
		 * @return bindingsManette Les bindings des manettes
		 */
		public BindingsManette[] getBindingsManette() {
			int nbJoueurs = mode == ModeDeJeu.SOLO ? 1 : 2;
			BindingsManette[] bindingsManette = new BindingsManette[nbJoueurs];
			bindingsManette[0] = bindingsJ1;
			panelJ1.fermerThreadManettes(); //tue le thread
			if(nbJoueurs == 2 ) {
				bindingsManette[1] = bindingsJ2;
				panelJ2.fermerThreadManettes(); //tue le thread
			}
			return bindingsManette;
		}
		/**
		 * Update le mode de jeu pour les controles
		 * @param mode Le mode de jeu
		 */
		private void setMode(ModeDeJeu mode) {
			this.mode = mode;
			if (mode == ModeDeJeu.COOP) {
				panelJ1.setBounds(25, 90, 450, 403);
				panelJ2 = new PanelControlles(1, keyJoueurs, bindingsJ2);
				panelJ2.setBackground(couleurPanels);
				panelJ2.setBounds(508, 90, 450, 403);
				panelJ2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				add(panelJ2);
			} else if (panelJ2 != null) {
				remove(panelJ2);
				panelJ1.setBounds(270, 90, 450, 403);
			}
		}
	}
	/**
	 * Créer le menu Continuer
	 * @author Olivier
	 *
	 */
	public class MenuContinuer extends JPanel {
		private JList<String> list;
		private static final long serialVersionUID = 1L;
		private File folder;
		private List<File> listOfFiles = new ArrayList<File>();
		private List<String> listOfOgFileNames = new ArrayList<String>();
		private DefaultListModel<String> model;
		/**
		 * Creation du panel menu continuer
		 */
		public MenuContinuer() {
			setLayout(null);
			setBounds(0, 0, 1000, 650);
			setBackground(couleurFondDEcran);

			JLabel lblContinuerPartie = new JLabel("Continuer Une Partie");
			lblContinuerPartie.setFont(title);
			lblContinuerPartie.setHorizontalAlignment(SwingConstants.CENTER);
			lblContinuerPartie.setBounds(164, 70, 650, 70);
			add(lblContinuerPartie);

			JButton btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {
				/**
				 * retourne au menu précédent
				 */
				public void actionPerformed(ActionEvent e) { //retourne au menu précédent
					cardLayout.show(contentPane, "menuJouer");
				}
			});
			btnRetour.setFont(base.deriveFont(19f));
			btnRetour.setBounds(40, 510, 153, 60);
			add(btnRetour);

			JButton btnContinuer = new JButton("Continuer");
			btnContinuer.addActionListener(new ActionListener() {
				/**
				 * Démarre le jeu avec la sauvegarde sélectionnée
				 */
				public void actionPerformed(ActionEvent e) { 
					clip.stop();
					btnContinuer.setEnabled(false);
					ouvrirMondeSelonMode();		
				}
			});
			btnContinuer.setFont(base.deriveFont(19f));
			btnContinuer.setEnabled(false);
			btnContinuer.setBounds(792, 510, 153, 60);
			add(btnContinuer);
			
			folder = new File("sauvegarde");
			File[] listOfFilesTemp = folder.listFiles();
			for (int i = 0; i < listOfFilesTemp.length; i++) 
				listOfFiles.add(listOfFilesTemp[i]);

			
			for (int i = 0; i < listOfFiles.size(); i++) 
				listOfOgFileNames.add(listOfFiles.get(i).getName());

			model = new DefaultListModel<String>();
			for (int i = 0; i < listOfFiles.size(); i++) 
				if (listOfFiles.get(i).isFile())
					model.addElement(listOfFiles.get(i).getName().substring(0, listOfFiles.get(i).getName().length() - 4));

			list = new JList<String>(model);
			list.setFont(base.deriveFont(18f));
			list.setSelectedIndex(1);
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					btnContinuer.setEnabled(true);
				}
			});
				
			JScrollPane listScroll = new JScrollPane(list);
			listScroll.setBounds(266, 198, 450, 170);
			add(listScroll);
			if (!list.isSelectionEmpty())
				btnContinuer.setEnabled(true);

			JButton btnSupprimerSauvegarde = new JButton("Supprimer");
			btnSupprimerSauvegarde.setFont(base.deriveFont(19f));
			btnSupprimerSauvegarde.setBounds(416, 510, 153, 60);
			btnSupprimerSauvegarde.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object[] options = {"Oui", "Annuler" };					
					int n = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir supprimer la sauvegarde " + list.getSelectedValue() + " ?",
							"Supprimer sauvegarde", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if (n == 0) {
						int selectedIndex = list.getSelectedIndex();
						File fileToRemove = new File("sauvegarde/" + listOfOgFileNames.get(selectedIndex));
						if (fileToRemove.delete()) System.out.println(fileToRemove.getName() + " is deleted");
						else 				 	   System.out.println("Failed to delete file : " + fileToRemove.getName());
						
						model.remove(selectedIndex);
						listOfFiles.remove(selectedIndex);
						listOfOgFileNames.remove(selectedIndex);
						list.setSelectedIndex(selectedIndex);
						
						if (list.isSelectionEmpty()) 
							btnContinuer.setEnabled(false);
					}
				}				
			});
			add(btnSupprimerSauvegarde);
		}
		public void refreshFolder() {
			folder = new File("sauvegarde");
			File[] listeTemp = folder.listFiles();
			for (int i = 0; i < listeTemp.length; i++)
				if (!listOfFiles.contains(listeTemp[i])) {
					listOfFiles.add(listeTemp[i]);
					listOfOgFileNames.add(listeTemp[i].getName());
				}		
			for (int i = 0; i < listOfOgFileNames.size(); i++) {
				String nomTemp = listOfOgFileNames.get(i).substring(0, listOfOgFileNames.get(i).length() - 4);
				if (!model.contains(nomTemp))
					model.addElement(nomTemp);
			}
			list.setSelectedIndex(0);
		}
		/**
		 * Ouvre une sauvegarde selon le mode de jeu soit solo ou coop
		 */
		private void ouvrirMondeSelonMode() {

			Sauvegarde save = LireSauvegarde.loaderSauvegarde(list.getSelectedValue());
			SaveJoueur[] saveJoueurs = save.getJoueurs(); 
			Joueur[] joueurs = new Joueur[saveJoueurs.length];
			joueurs[0] = new Joueur(saveJoueurs[0].getPosX(), saveJoueurs[0].getPosY(), saveJoueurs[0].getPersonnage());
			joueurs[0].loadSave(saveJoueurs[0].getGold(), saveJoueurs[0].getExp(), saveJoueurs[0].getStats(), 
					saveJoueurs[0].getPointsAttribuables(), saveJoueurs[0].getListeItems(), saveJoueurs[0].getPointsDeVie(),
					saveJoueurs[0].getNiveau(), saveJoueurs[0].getListeItemsDuInventaire(), saveJoueurs[0].getCarburantFusee());
			if (joueurs.length == 2) {
				joueurs[1] = new Joueur(saveJoueurs[1].getPosX(), saveJoueurs[1].getPosY(), saveJoueurs[1].getPersonnage());
				joueurs[1].loadSave(saveJoueurs[1].getGold(), saveJoueurs[1].getExp(), saveJoueurs[1].getStats(), 
						saveJoueurs[1].getPointsAttribuables(),	saveJoueurs[1].getListeItems(), saveJoueurs[1].getPointsDeVie(),
						saveJoueurs[1].getNiveau(), saveJoueurs[1].getListeItemsDuInventaire(), saveJoueurs[1].getCarburantFusee());
			}
			Difficulte difficulte = save.getDifficulte();	
			int seed = save.getSeed();
			long score = save.getScore();
			Controle[] controles = save.getControles();					
			BindingsManette[] bindings = save.getBindings();
			BindingsClavier keyJoueurs = save.getKeyJoueurs();
			boolean[] biomesDecouverts = save.getBiomesDejaDecouverts();

			App10Plateforme menuPrincipal;
			switch(save.getMode()) {			
			case SOLO:
				menuPrincipal = new App10Plateforme();
				FenetreModeSolo solo = new FenetreModeSolo(joueurs, difficulte, seed, controles, bindings, keyJoueurs, menuPrincipal, score, biomesDecouverts);			
				solo.setVisible(true);
				frame.setVisible(false);
				break;
			case COOP:
				menuPrincipal = new App10Plateforme();
				FenetreModeCoop coop = new FenetreModeCoop(joueurs, difficulte, seed, controles, bindings, keyJoueurs, menuPrincipal, score, biomesDecouverts);
				coop.setVisible(true);		
				frame.setVisible(false);
				break;					
			}			
		}
	}
	/**
	 * Créer le menu Options
	 * @author Olivier
	 *
	 */
	public class MenuOptions extends JPanel {

		private static final long serialVersionUID = 1L;

		/**
		 * Creation du panel menu options
		 */
		public MenuOptions() {
			setLayout(null);
			setBounds(0, 0, 1000, 650);
			setBackground(couleurFondDEcran);


			JLabel lblOption = new JLabel("Options");
			lblOption.setFont(title.deriveFont(41f));
			lblOption.setHorizontalAlignment(SwingConstants.CENTER);
			lblOption.setBounds(155, 72, 540, 51);
			add(lblOption);

			JButton btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {
				/**
				 * Retourne au menu précédent
				 */
				public void actionPerformed(ActionEvent e) {
					//debut
					cardLayout.show(contentPane, "menuDepart");
					//fin
				}
			});
			btnRetour.setFont(base.deriveFont(19f));
			btnRetour.setBounds(40, 510, 153, 60);
			add(btnRetour);
			
			JSlider sliderMus = new JSlider();
		
			sliderMus.setBackground(new Color(178, 34, 34));
			sliderMus.setForeground(new Color(139, 0, 0));
			sliderMus.setBounds(300, 174, 249, 26);
			sliderMus.setMinimum(-80);
			sliderMus.setMaximum(6);
			sliderMus.setValue(-36);
			sliderMus.addChangeListener(new ChangeListener() {
				/**
				 * Écoute pour savoir quelle valeur associer au volume de la musique
				 */
				public void stateChanged(ChangeEvent arg0) {
					gain = sliderMus.getValue()/100;
					dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
					gainControl.setValue(sliderMus.getValue());
				}
			});
			add(sliderMus);

			JSlider sliderSon = new JSlider();
			sliderSon.addChangeListener(new ChangeListener() {
				/**
				 * Écoute pour savoir quelle valeur associer au volume du son
				 */
				public void stateChanged(ChangeEvent e) {
				}
			});
			sliderSon.setBackground(new Color(178, 34, 34));
			sliderSon.setBounds(300, 235, 249, 26);
			add(sliderSon);

			JLabel lblVolumeMus = new JLabel("Volume de la musique");
			lblVolumeMus.setFont(base.deriveFont(14f));
			lblVolumeMus.setBounds(88, 174, 170, 26);
			add(lblVolumeMus);

			JLabel lblVolumeDuSon = new JLabel("Volume du son");
			lblVolumeDuSon.setFont(base.deriveFont(14f));
			lblVolumeDuSon.setBounds(88, 235, 170, 26);
			add(lblVolumeDuSon);
		}
	}
	/**
	 * Créer le menu Aide
	 * @author Olivier
	 *
	 */
	public class MenuAide extends JPanel {

		private static final long serialVersionUID = 1L;

		/**
		 * Creation du panel menu aide
		 */
		public MenuAide() {

			setLayout(null);
			setBounds(0, 0, 1000, 650);
			setBackground(couleurFondDEcran);

			JLabel lblAide = new JLabel("Aide");
			lblAide.setFont(title.deriveFont(41f));
			lblAide.setHorizontalAlignment(SwingConstants.CENTER);
			lblAide.setBounds(155, 72, 540, 51);
			add(lblAide);

			JButton btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {
				/**
				 * Retourne au menu précédent
				 */
				public void actionPerformed(ActionEvent e) {
					//debut
					cardLayout.show(contentPane, "menuDepart");
					//fin
				}
			});
			btnRetour.setFont(base.deriveFont(19f));
			btnRetour.setBounds(40, 510, 153, 60);
			add(btnRetour);
			
			PanelAide panelAide = new PanelAide();
			panelAide.setBounds(100, 150, getWidth()-200, getHeight()/2);
			add(panelAide);
		}
	}
	/**
	 * Fenetre de l'editeur des personnages
	 * @author Mihai
	 *
	 */
	public class MenuCreationPersonnage extends JPanel {

		private static final long serialVersionUID = 1L;

		//Composantes du JFrame
		private JPanel panelJ1, panelJ2;;
		private JTextField txtNomJ1, txtNomJ2;
		private JTextPane txtPaneInfo, txtPaneInfo2;
		private JButton btnCreerPerso, btnCreerPerso2, btnRetour, btnCommencer;
		private JButton btnMoinsApparence, btnMoinsForce, btnMoinsChance, btnMoinsAgilite, btnMoinsResistance;
		private JButton btnMoinsApparance2, btnMoinsForce2, btnMoinsChance2, btnMoinsAgilite2, btnMoinsResistance2;
		private JButton btnPlusApparence, btnPlusForce, btnPlusChance, btnPlusAgilite, btnPlusResistance;	
		private JButton btnPlusApparence2, btnPlusForce2, btnPlusChance2, btnPlusAgilite2, btnPlusResistance2;
		private JLabel lblPoints, lblForce, lblChance, lblAgilite, lblResistance;
		private JLabel lblPoints2, lblForce2, lblChance2, lblAgilite2, lblResistance2;
		private JLabel lblTexteChoisirPersonnages, lblTextJoueur1, lblTextJoueur2;
		private ArrayList<JLabel> lblStats1 = new ArrayList<JLabel>();
		private ArrayList<JLabel> lblStats2 = new ArrayList<JLabel>();	

		//Variables
		private ArrayList<Sprite> listeSpritesJ1 = new ArrayList<Sprite>(), listeSpritesJ2 = new ArrayList<Sprite>();
		private int[] statsApparentsJ1 = {1, 1, 1, 1}; 
		private int[] statsApparentsJ2 = {1, 1, 1, 1}; 
		private int maxStat = 4, totalPointsJ1 = 5, totalPointsJ2 = 5;
		private int numeroPersonnageJ1 = 0, numeroPersonnageJ2 = 0, taille = 16*5;
		private String nomJ1 = null, nomJ2 = null;
		private Personnage personnageJ1, personnageJ2 = null;
		private List<Joueur> joueurs = new ArrayList<Joueur>();
		private ModeDeJeu mode = ModeDeJeu.SOLO;	
		private String[] descriptions = new String[5];

		public MenuCreationPersonnage() {
			setBackground(couleurFondDEcran);
			setForeground(Color.BLACK);
			setBorder(new EmptyBorder(5, 5, 5, 5));

			InputStream is = getClass().getClassLoader().getResourceAsStream("Pixellari.ttf");
			try {
				base = Font.createFont(Font.TRUETYPE_FONT, is);
				base = base.deriveFont(23f);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			is = getClass().getClassLoader().getResourceAsStream("upheavtt.ttf");

			try {
				title = Font.createFont(Font.TRUETYPE_FONT, is);
				title = title.deriveFont(50f);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}



			//ajout des sprites dans la liste
			listeSpritesJ1.add(new Sprite("viking"));
			listeSpritesJ1.add(new Sprite("cowboy"));
			listeSpritesJ1.add(new Sprite("thief"));
			listeSpritesJ1.add(new Sprite("goblin"));
			listeSpritesJ1.add(new Sprite("leprechaun"));

			listeSpritesJ2.add(new Sprite("viking"));
			listeSpritesJ2.add(new Sprite("cowboy"));
			listeSpritesJ2.add(new Sprite("thief"));
			listeSpritesJ2.add(new Sprite("goblin"));
			listeSpritesJ2.add(new Sprite("leprechaun"));		

			panelJ1 = new JPanel();
			panelJ1.setBounds(25, 90, 450, 403);
			panelJ1.setBackground(Color.RED);
			panelJ1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panelJ1.setToolTipText("");

			JLabel lblTextNom1 = new JLabel("Nom  :  ");
			lblTextNom1.setBounds(10, 14, 72, 17);
			lblTextNom1.setFont(base.deriveFont(18f));

			txtNomJ1 = new JTextField();
			txtNomJ1.setBounds(100, 11, 166, 23);
			txtNomJ1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
			txtNomJ1.setColumns(10);

			JLabel lblTextApparence = new JLabel("Apparence");
			lblTextApparence.setBounds(10, 80, 121, 22);
			lblTextApparence.setFont(base.deriveFont(18f));

			btnMoinsApparence = new JButton("");
			btnMoinsApparence.setBounds(115, 78, 29, 27);
			btnMoinsApparence.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remove(listeSpritesJ1.get(numeroPersonnageJ1));
					numeroPersonnageJ1--;
					if (numeroPersonnageJ1 < 0) numeroPersonnageJ1 = listeSpritesJ1.size()-1;
					afficherPersonnagePourJ1(mode);

				}
			});
			associerBoutonAvecImage(btnMoinsApparence, "minus.png");

			btnPlusApparence = new JButton("");
			btnPlusApparence.setBounds(237, 78, 29, 27);
			btnPlusApparence.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remove(listeSpritesJ1.get(numeroPersonnageJ1));
					numeroPersonnageJ1++;
					if (numeroPersonnageJ1 >= listeSpritesJ1.size()) numeroPersonnageJ1 = 0;
					afficherPersonnagePourJ1(mode);
				}
			});
			associerBoutonAvecImage(btnPlusApparence, "plus.png");

			JLabel lblTextForce = new JLabel("Force");
			lblTextForce.setBounds(31, 197, 88, 30);
			lblTextForce.setFont(base.deriveFont(18f));

			//debut boutons stats moins
			btnMoinsForce = new JButton("");
			btnMoinsForce.setBounds(129, 199, 31, 27);
			btnMoinsForce.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ1(0);
				}
			});
			associerBoutonAvecImage(btnMoinsForce, "minus.png");
			//fin boutons stats moins

			//debut boutons stats plus
			btnPlusForce = new JButton("");
			btnPlusForce.setBounds(168, 199, 34, 27);
			btnPlusForce.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ1(0);
				}
			});
			associerBoutonAvecImage(btnPlusForce, "plus.png");

			JLabel lblTextPoints = new JLabel("Points attribuables : ");
			lblTextPoints.setBounds(31, 157, 187, 28);
			lblTextPoints.setFont(base.deriveFont(18f));

			lblPoints = new JLabel("5");
			lblPoints.setBounds(220, 162, 52, 19);
			lblPoints.setFont(lblPoints.getFont().deriveFont(21f));

			lblForce = new JLabel("1");
			lblForce.setBounds(224, 201, 48, 22);
			lblForce.setFont(base.deriveFont(18f));

			lblStats1.add(lblForce);

			JLabel lblTextChance = new JLabel("Chance");
			lblTextChance.setBounds(31, 237, 88, 30);
			lblTextChance.setFont(base.deriveFont(18f));

			btnMoinsChance = new JButton("");
			btnMoinsChance.setBounds(129, 239, 31, 27);
			btnMoinsChance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ1(1);
				}
			});
			associerBoutonAvecImage(btnMoinsChance, "minus.png");

			btnPlusChance = new JButton("");
			btnPlusChance.setBounds(168, 239, 34, 27);
			btnPlusChance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ1(1);
				}
			});
			associerBoutonAvecImage(btnPlusChance, "plus.png");

			lblChance = new JLabel("1");
			lblChance.setBounds(224, 241, 48, 22);
			lblChance.setFont(base.deriveFont(18f));
			lblStats1.add(lblChance);

			lblAgilite = new JLabel("1");
			lblAgilite.setBounds(224, 282, 48, 23);
			lblAgilite.setFont(base.deriveFont(18f));
			lblStats1.add(lblAgilite);

			btnPlusAgilite = new JButton("");
			btnPlusAgilite.setBounds(168, 280, 34, 27);
			btnPlusAgilite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ1(2);
				}
			});
			associerBoutonAvecImage(btnPlusAgilite, "plus.png");

			btnMoinsAgilite = new JButton("");
			btnMoinsAgilite.setBounds(129, 280, 31, 27);
			btnMoinsAgilite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ1(2);
				}
			});
			associerBoutonAvecImage(btnMoinsAgilite, "minus.png");

			JLabel lblTextAgilite = new JLabel("Agilit\u00E9");
			lblTextAgilite.setBounds(31, 278, 88, 31);
			lblTextAgilite.setFont(base.deriveFont(18f));

			JLabel lblTextResistance = new JLabel("R\u00E9sitance");
			lblTextResistance.setBounds(31, 328, 88, 19);
			lblTextResistance.setFont(base.deriveFont(18f));

			btnMoinsResistance = new JButton("");
			btnMoinsResistance.setBounds(129, 324, 31, 27);
			btnMoinsResistance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ1(3);
				}
			});
			associerBoutonAvecImage(btnMoinsResistance, "minus.png");

			btnPlusResistance = new JButton("");
			btnPlusResistance.setBounds(168, 324, 34, 27);
			btnPlusResistance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ1(3);
				}
			});
			associerBoutonAvecImage(btnPlusResistance, "plus.png");

			lblResistance = new JLabel("1");
			lblResistance.setBounds(224, 329, 48, 17);
			lblResistance.setFont(base.deriveFont(18f));
			lblStats1.add(lblResistance);

			
			final String descriptionViking = "Suite au massacre de Karköhr, ce Viking décida d’aller sur ces terres pour combattre afin de mériter " 
										   + "sa place dans le Valhalla avec ses frères d’armes."
										   + "\n\nFrappe des dieux : Le Viking demande aux dieux de frapper les pas gentils."
										   + "\n\nBaroud d’honneur : Son expérience de combat lui permet de renvoyer sa douleur en force.";
			
			final String descriptionCowboy = "Après une défaite de la confédération du sud, ce Pistolero décida de fuir le pays et refaire sa vie dans un autre monde."
										   + "\n\nDernière volonté : Le Pistolero tire avec son six-coups dénommé “Karen” en souvenir de sa femme.";
			
			final String descriptionNinja = "Le dernier représentant de la lignée des Nakadashi quitta son pays afin de trouver un amour éternellement vert à longues oreilles."
										  + "\n\nLa technique du Netorare : C’est l’héritage du clan Nakadashi. Elle consiste à apparaitre "
										  + "derrière l’ennemi et d’assigner la colère des membres de son clan.";
			
			final String descriptionGoblin = "Ce monstre était destiné à devenir un vilain du monde infini, jusqu’au jour où son cœur fut rempli par la présence du Pistolero."
										   + "\n\nOuragan corporel : Cette attaque envahi leur espace personnel pendant plusieurs secondes.";

			final String descriptionFarfadet = "Le Farfadet est le gardien d’Irlande venu dans ce monde pour répandre la violence et l’or de ses pièces. "
											 + "Prenez garde de ce petit bonhomme vert et barbu!" 
											 + "\n\nLe tir en or : Le Farfadet utilise sa richesse pour vaincre les méchants. "
											 + "Ce pouvoir peut seulement être utilisé tant que le Farfadet est riche.";
			
			descriptions[0] = descriptionViking;
			descriptions[1] = descriptionCowboy;
			descriptions[2] = descriptionNinja;
			descriptions[3] = descriptionGoblin; 
			descriptions[4] = descriptionFarfadet;
			
			txtPaneInfo = new JTextPane();
			txtPaneInfo.setEditable(false);
			txtPaneInfo.setHighlighter(null);
			txtPaneInfo.setBounds(276, 11, 164, 326);
			txtPaneInfo.setText(descriptions[numeroPersonnageJ1]);			

			btnCreerPerso = new JButton("Cr\u00E9er personnage");
			btnCreerPerso.setBounds(276, 344, 164, 50);
			btnCreerPerso.setMargin(new Insets(0, 0, 0, 0));
			btnCreerPerso.setFont(base.deriveFont(18f));

			lblTexteChoisirPersonnages = new JLabel("Choisissez votre personnage");
			lblTexteChoisirPersonnages.setBounds(97, 0, 826, 54);
			lblTexteChoisirPersonnages.setFont(title);

			panelJ2 = new JPanel();
			panelJ2.setBounds(508, 90, 450, 403);
			panelJ2.setBackground(Color.RED);
			panelJ2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panelJ2.setToolTipText("");

			JLabel lblTextNom2 = new JLabel("Nom  :  ");
			lblTextNom2.setBounds(10, 14, 70, 17);
			lblTextNom2.setFont(base.deriveFont(18f));

			txtNomJ2 = new JTextField();
			txtNomJ2.setBounds(94, 11, 166, 23);
			txtNomJ2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
			txtNomJ2.setColumns(10);


			JLabel lblTextApparence2 = new JLabel("Apparence");
			lblTextApparence2.setBounds(10, 80, 124, 22);
			lblTextApparence2.setFont(base.deriveFont(18f));

			btnMoinsApparance2 = new JButton("");
			btnMoinsApparance2.setBounds(113, 78, 29, 27);
			btnMoinsApparance2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remove(listeSpritesJ2.get(numeroPersonnageJ2));
					numeroPersonnageJ2--;
					if (numeroPersonnageJ2 < 0) numeroPersonnageJ2 = listeSpritesJ2.size()-1;
					afficherPersonnagePourJ2();
				}
			});

			btnPlusApparence2 = new JButton("");
			btnPlusApparence2.setBounds(230, 78, 29, 27);
			btnPlusApparence2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remove(listeSpritesJ2.get(numeroPersonnageJ2));
					numeroPersonnageJ2++;
					if (numeroPersonnageJ2 >= listeSpritesJ2.size()) numeroPersonnageJ2 = 0;
					afficherPersonnagePourJ2();
				}
			});

			JLabel lblTextForce2 = new JLabel("Force");
			lblTextForce2.setBounds(30, 197, 90, 30);
			lblTextForce2.setFont(base.deriveFont(18f));

			btnMoinsForce2 = new JButton("");
			btnMoinsForce2.setBounds(130, 199, 29, 27);
			btnMoinsForce2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ2(0);
				}
			});

			btnPlusForce2 = new JButton("");
			btnPlusForce2.setBounds(167, 199, 29, 27);
			btnPlusForce2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ2(0);
				}
			});

			JLabel lblTextPoints2 = new JLabel("Points attribuables : ");
			lblTextPoints2.setBounds(30, 157, 186, 28);
			lblTextPoints2.setFont(base.deriveFont(18f));

			lblPoints2 = new JLabel("5");
			lblPoints2.setBounds(218, 162, 50, 19);
			lblPoints2.setFont(lblPoints2.getFont().deriveFont(21f));

			lblForce2 = new JLabel("1");
			lblForce2.setBounds(218, 201, 50, 22);
			lblForce2.setFont(base.deriveFont(18f));
			lblStats2.add(lblForce2);

			JLabel lblTextChance2 = new JLabel("Chance");
			lblTextChance2.setBounds(30, 237, 90, 30);
			lblTextChance2.setFont(base.deriveFont(18f));

			btnMoinsChance2 = new JButton("");
			btnMoinsChance2.setBounds(130, 239, 29, 27);
			btnMoinsChance2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ2(1);
				}
			});

			btnPlusChance2 = new JButton("");
			btnPlusChance2.setBounds(167, 239, 29, 27);
			btnPlusChance2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ2(1);
				}
			});

			lblChance2 = new JLabel("1");
			lblChance2.setBounds(218, 241, 50, 22);
			lblChance2.setFont(base.deriveFont(18f));
			lblStats2.add(lblChance2);

			lblAgilite2 = new JLabel("1");
			lblAgilite2.setBounds(218, 282, 50, 23);
			lblAgilite2.setFont(base.deriveFont(18f));
			lblStats2.add(lblAgilite2);

			btnPlusAgilite2 = new JButton("");
			btnPlusAgilite2.setBounds(167, 280, 29, 27);
			btnPlusAgilite2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ2(2);
				}
			});

			btnMoinsAgilite2 = new JButton("");
			btnMoinsAgilite2.setBounds(130, 280, 29, 27);
			btnMoinsAgilite2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ2(2);
				}
			});

			JLabel lblTextAgilite2 = new JLabel("Agilit\u00E9");
			lblTextAgilite2.setBounds(30, 278, 90, 31);
			lblTextAgilite2.setFont(base.deriveFont(18f));

			JLabel lblTextResistance2 = new JLabel("R\u00E9sitance");
			lblTextResistance2.setBounds(30, 328, 90, 19);
			lblTextResistance2.setFont(base.deriveFont(18f));

			btnMoinsResistance2 = new JButton("");
			btnMoinsResistance2.setBounds(130, 324, 29, 27);
			btnMoinsResistance2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moinsJ2(3);
				}
			});

			btnPlusResistance2 = new JButton("");
			btnPlusResistance2.setBounds(167, 324, 29, 27);
			btnPlusResistance2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					plusJ2(3);
				}
			});

			lblResistance2 = new JLabel("1");
			lblResistance2.setBounds(218, 329, 50, 17);
			lblResistance2.setFont(base.deriveFont(18f));
			lblStats2.add(lblResistance2);

			txtPaneInfo2 = new JTextPane();
			txtPaneInfo2.setEditable(false);
			txtPaneInfo2.setHighlighter(null);
			txtPaneInfo2.setBounds(276, 11, 164, 326);

			lblTextJoueur1 = new JLabel("Joueur 1");
			lblTextJoueur1.setBounds(196, 54, 109, 44);
			lblTextJoueur1.setFont(base.deriveFont(20f));

			btnRetour = new JButton("Retour");
			btnRetour.setBounds(40, 510, 153, 60);
			btnRetour.setFont(base.deriveFont(20f));
			btnRetour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					cardLayout.show(contentPane, "menuControles");
				}
			});

			btnCommencer = new JButton("Commencer");
			btnCommencer.setBounds(771, 506, 187, 68);
			btnCommencer.setEnabled(false);
			btnCommencer.setFont(base.deriveFont(20f));
			btnCommencer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (mode == ModeDeJeu.COOP) {
						if (personnageJ1 != null && personnageJ2 != null) {							
							setVisible(false);
							clip.stop();
							ouvrirMondeSelonModeDeJeu();
							System.out.println("Game start");
						} else {
							JOptionPane.showMessageDialog(null, "Veuillez creer un personnage pour tous les joueurs");
						}					
					} else {
						if (personnageJ1 != null) {							
							setVisible(false);
							clip.stop();
							ouvrirMondeSelonModeDeJeu();
							System.out.println("Game start");						
						} else {
							JOptionPane.showMessageDialog(null, "Veuillez creer un personnage");
						}
					}				
				}
			});

			lblTextJoueur2 = new JLabel("Joueur 2");
			lblTextJoueur2.setBounds(688, 54, 103, 44);
			lblTextJoueur2.setFont(base.deriveFont(20f));			

			btnCreerPerso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtNomJ1.getText().equals(""))
						nomJ1 = txtNomJ1.getText();
					else {
						nomJ1 = "Bob";
						txtNomJ1.setText(nomJ1);
					}
					if (nomJ1 != null ) {											
						personnageJ1 = new Personnage(nomJ1, listeSpritesJ1.get(numeroPersonnageJ1).getNom(), statsApparentsJ1);						
						if (mode == ModeDeJeu.SOLO || personnageJ2 != null) btnCommencer.setEnabled(true);
						if (!joueurs.isEmpty() && personnageJ2 == null) joueurs.remove(0);
						joueurs.add(0, new Joueur(25, 0, personnageJ1));
					} 
				}
			});		
			btnCreerPerso2 = new JButton("Cr\u00E9er personnage");
			btnCreerPerso2.setBounds(276, 344, 164, 50);
			btnCreerPerso2.setMargin(new Insets(0, 0, 0, 0));
			btnCreerPerso2.setFont(base.deriveFont(18f));
			btnCreerPerso2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtNomJ2.getText().equals(""))
						nomJ2 = txtNomJ2.getText();
					else {
						nomJ1 = "Bob";
						nomJ2 = "Bobette";

						txtNomJ1.setText(nomJ1);
						txtNomJ2.setText(nomJ2);
					}
					if(nomJ2 != null) {
						personnageJ2 = new Personnage(nomJ2, listeSpritesJ2.get(numeroPersonnageJ2).getNom(), statsApparentsJ2);
						if (joueurs.size() > 1) joueurs.remove(1);					
						joueurs.add(new Joueur(26, 0, personnageJ2));
						if (personnageJ1 != null) btnCommencer.setEnabled(true);
					}
				}
			});

			afficherPersonnagePourJ1(mode);
			afficherPersonnagePourJ2();
			setLayout(null);
			add(lblTextJoueur1);
			add(lblTextJoueur2);
			add(panelJ1);
			panelJ1.setLayout(null);
			panelJ1.add(lblTextNom1);
			panelJ1.add(txtNomJ1);
			panelJ1.add(lblTextApparence);
			panelJ1.add(btnMoinsApparence);
			panelJ1.add(btnPlusApparence);
			panelJ1.add(lblTextPoints);
			panelJ1.add(lblPoints);
			panelJ1.add(lblTextForce);
			panelJ1.add(btnMoinsForce);
			panelJ1.add(btnPlusForce);
			panelJ1.add(lblForce);
			panelJ1.add(lblTextChance);
			panelJ1.add(btnMoinsChance);
			panelJ1.add(btnPlusChance);
			panelJ1.add(lblChance);
			panelJ1.add(lblTextAgilite);
			panelJ1.add(btnMoinsAgilite);
			panelJ1.add(btnPlusAgilite);
			panelJ1.add(lblAgilite);
			panelJ1.add(lblTextResistance);
			panelJ1.add(btnMoinsResistance);
			panelJ1.add(btnPlusResistance);
			panelJ1.add(lblResistance);
			panelJ1.add(txtPaneInfo);
			panelJ1.add(btnCreerPerso);
			add(panelJ2);
			panelJ2.setLayout(null);
			panelJ2.add(lblTextNom2);
			panelJ2.add(txtNomJ2);
			panelJ2.add(lblTextApparence2);
			panelJ2.add(btnMoinsApparance2);
			panelJ2.add(btnPlusApparence2);
			panelJ2.add(lblTextPoints2);
			panelJ2.add(lblPoints2);
			panelJ2.add(lblTextForce2);
			panelJ2.add(btnMoinsForce2);
			panelJ2.add(btnPlusForce2);
			panelJ2.add(lblForce2);
			panelJ2.add(lblTextChance2);
			panelJ2.add(btnMoinsChance2);
			panelJ2.add(btnPlusChance2);
			panelJ2.add(lblChance2);
			panelJ2.add(lblTextAgilite2);
			panelJ2.add(btnMoinsAgilite2);
			panelJ2.add(btnPlusAgilite2);
			panelJ2.add(lblAgilite2);
			panelJ2.add(lblTextResistance2);
			panelJ2.add(btnMoinsResistance2);
			panelJ2.add(btnPlusResistance2);
			panelJ2.add(lblResistance2);
			panelJ2.add(txtPaneInfo2);
			panelJ2.add(btnCreerPerso2);
			add(btnRetour);
			add(btnCommencer);
			add(lblTexteChoisirPersonnages);

			panelJ1.setBounds(25, 90, 450, 403);

		}
		/**
		 * Ouvre un monde selon le mode de jeu
		 */
		private void ouvrirMondeSelonModeDeJeu() {
			btnCommencer.setEnabled(false);
			switch (mode) {
			case COOP:				
				personnageJ1.getSprite().pause();
				personnageJ2.getSprite().pause();	
				menuControlles.getKeyJoueurs().setDefaultIfEmpty(0);
				menuControlles.getKeyJoueurs().setDefaultIfEmpty(1);
				menuControlles.getBindingsManette()[0].setDefaultIfEmpty();
				menuControlles.getBindingsManette()[1].setDefaultIfEmpty();
				Joueur[] joueursTempCoop = {joueurs.get(0), joueurs.get(1)};
				FenetreModeCoop jeuCoop = new FenetreModeCoop(joueursTempCoop, menuNouvellePartie.getDifficulte(), menuNouvellePartie.getSeed(), menuControlles.getControles(),
						menuControlles.getBindingsManette(), menuControlles.getKeyJoueurs(), frame, 0, new boolean[10]);
				jeuCoop.setVisible(true);
				frame.setVisible(false);
				break;
			case SOLO:
				personnageJ1.getSprite().pause();
				menuControlles.getKeyJoueurs().setDefaultIfEmpty(0);
				menuControlles.getBindingsManette()[0].setDefaultIfEmpty();
				Joueur[] joueursTempSolo = {joueurs.get(0)};
				FenetreModeSolo jeuSolo = new FenetreModeSolo(joueursTempSolo, menuNouvellePartie.getDifficulte(), menuNouvellePartie.getSeed(), menuControlles.getControles(),
						menuControlles.getBindingsManette(), menuControlles.getKeyJoueurs(), frame, 0, new boolean[10]);
				jeuSolo.setVisible(true);
				frame.setVisible(false);
				break;		
			default:
				System.exit(ERROR);
				break;
			}

		}
		/**
		 * Disable le bouton comencer si un des personnages n'a pas ete cree
		 */
		public void setBtnCommencerDisabled() {
			if (personnageJ1 == null || personnageJ2 == null) btnCommencer.setEnabled(false);
		}
		/**
		 * Enleve des points du joueur 1 d'une caracteristique
		 * @param nb Le numero representant la caracteristique
		 */
		private void moinsJ1(int nb) {
			if(statsApparentsJ1[nb] != 1) {
				statsApparentsJ1[nb]--;
				totalPointsJ1++;
				lblStats1.get(nb).setText(statsApparentsJ1[nb]+"");
				lblPoints.setText(totalPointsJ1+"");
			}
		}
		/**
		 * Enleve des points du joueur 2 d'une caracteristique
		 * @param nb Le numero representant la caracteristique
		 */
		private void moinsJ2(int nb) {
			if(statsApparentsJ2[nb] != 1) {
				statsApparentsJ2[nb]--;
				totalPointsJ2++;
				lblStats2.get(nb).setText(statsApparentsJ2[nb]+"");
				lblPoints2.setText(totalPointsJ2+"");
			}
		}
		/**
		 * Ajoute des points du joueur 1 d'une caracteristique
		 * @param nb Le numero representant la caracteristique
		 */
		private void plusJ1(int nb) {
			if(statsApparentsJ1[nb] != maxStat && totalPointsJ1 !=0) {
				statsApparentsJ1[nb]++;
				totalPointsJ1--;
				lblStats1.get(nb).setText(statsApparentsJ1[nb]+"");
				lblPoints.setText(totalPointsJ1+"");
			}
		}
		/**
		 * Ajoute des points du joueur 2 d'une caracteristique
		 * @param nb Le numero representant la caracteristique
		 */
		private void plusJ2(int nb) {
			if(statsApparentsJ2[nb] != maxStat && totalPointsJ2 !=0) {
				statsApparentsJ2[nb]++;
				totalPointsJ2--;
				lblStats2.get(nb).setText(statsApparentsJ2[nb]+"");
				lblPoints2.setText(totalPointsJ2+"");
			}
		}
		/**
		 * Affiche le sprite du joueur 1
		 */
		private void afficherPersonnagePourJ1(ModeDeJeu mode) {
			int xBounds, yBounds;
			if (mode == ModeDeJeu.COOP) {
				xBounds = 175;
				yBounds = 140;
			} else {
				xBounds = 420;
				yBounds = 140;
			}			
			listeSpritesJ1.forEach(sprite -> sprite.setBounds( xBounds, yBounds, taille, taille));
			add(listeSpritesJ1.get(numeroPersonnageJ1));
			listeSpritesJ1.get(numeroPersonnageJ1).demarrer();
			txtPaneInfo.setText(descriptions[numeroPersonnageJ1]);
		}
		/**
		 * Affiche le sprite du joueur 2
		 */
		private void afficherPersonnagePourJ2() {
			listeSpritesJ2.forEach(sprite -> sprite.setBounds( 654, 140, taille, taille));
			add(listeSpritesJ2.get(numeroPersonnageJ2));
			listeSpritesJ2.get(numeroPersonnageJ2).demarrer();
			txtPaneInfo2.setText(descriptions[numeroPersonnageJ2]);
		}
		private void enleverPersonnagePourJ2() {
			remove(listeSpritesJ2.get(numeroPersonnageJ2));
		}
		/**
		 * Méthode qui redimensionne l'image d'un bouton pour qu'elle ait la même taille que le bouton
		 * @param leBouton Le bouton
		 * @param fichierImage L'image 
		 */
		private void associerBoutonAvecImage(JButton leBouton, String fichierImage) {		
			Image imgLue=null;
			URL urlImage = getClass().getClassLoader().getResource(fichierImage);  
			if (urlImage == null) {
				JOptionPane.showMessageDialog(null , "Fichier " + fichierImage + " introuvable");
				System.exit(0);
			} 
			try {
				imgLue = ImageIO.read(urlImage);
			}
			catch (IOException e) {
				System.out.println("Erreur pendant la lecture du fichier d'image");
			}
			//redimensionner l'image de la meme grandeur que le bouton
			Image imgRedim = imgLue.getScaledInstance( leBouton.getWidth() , leBouton.getHeight() ,Image.SCALE_SMOOTH);

			//associer l'image au bouton
			leBouton.setIcon( new ImageIcon(imgRedim) );

			//on se debarasse des images intermédiaires
			imgLue.flush();
			imgRedim.flush();
		}			
		/**
		 * Update le mode de jeu du panel creation personnages
		 * @param mode Le mode de jeu
		 */
		public void setMode(ModeDeJeu mode) {
			this.mode = mode;
			if (mode == ModeDeJeu.SOLO) {	
				remove(panelJ2);
				remove(lblTextJoueur2);
				afficherPersonnagePourJ1(mode);
				enleverPersonnagePourJ2();	
				panelJ1.setBounds(270, 90, 450, 403);
				lblTextJoueur1.setBounds(456, 54, 109, 44);
			} else {
				add(panelJ2);
				panelJ1.setBounds(25, 90, 450, 403);
				lblTextJoueur1.setBounds(205, 54, 109, 44);
				afficherPersonnagePourJ1(mode);
				afficherPersonnagePourJ2();			
				add(lblTextJoueur2);
			}		
		}
	}
}