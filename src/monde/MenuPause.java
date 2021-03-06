package monde;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aaplication.PanelControlles;
import entites.Joueur;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import menus.FrameAide;
import net.miginfocom.swing.MigLayout;

/**
 * Le menu pause dans le monde
 * @author Mihai
 *
 */
public class MenuPause extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnRetournerAuJeu;
	private JLabel lblMusique;
	private JLabel lblVolumeEffets;
	private JPanel panelDifficulte;
	private JButton btnAide;
	private JButton btnSauvegarder;
	private JButton btnRetournerAuMenu;
	private JButton btnQuitter;
	private JSlider sliderMusique;
	private JSlider sliderEffets;
	private JPanel panelAffichage;
	private JTabbedPane tabbedPane;
	private JPanel panelLevelUp;
	private JPanel panelControlles;
	private JFrame frameAide = new FrameAide();
	private JLabel lblDifficulte;
	private JRadioButton rdbtnDifficile;
	private JRadioButton rdbtnMoyen;
	private JRadioButton rdbtnFacile;
	private JTabbedPane tabbedPaneControles;
	private JPanel panelTabControlesJ1;
	private JPanel panelTabControlesJ2;
	private PanelControlles panelControlesJ1;
	private PanelControlles panelControlesJ2;
	private PanelCaracteristiques panelCaracteristiquesJ1;
	private PanelCaracteristiques panelCaracteristiquesJ2;
	private JCheckBox chckbxAffichageScientifique;
	private JCheckBox chckbxVecteursAcceleration;
	private JCheckBox chckbxVecteursVitesse;
	private JCheckBox chckbxModeInvulnerable;
	private JCheckBox chckbxAfficherHitboxJoueurs;
	private JCheckBox chckbxAfficherHitboxMonstres;
	private JCheckBox chckbxAfficherMeshAI;
	private JCheckBox chckbxActiverItemSpawn;
	private Monde monde;
	private JButton btnAjouerExp;
	private JSlider slider;
	private JButton btnDefaultJ1;
	private JButton btnSauvegardercontrolesJ1;
	
	/**
	 * Constructeur du menu pause
	 * @param monde Le monde
	 */
	public MenuPause(Monde monde) {
		this.monde = monde;
		setLayout(new MigLayout("", "[11.00][97.00][113.00][34.00][40.00][81.00][40.00][39.00,grow][144.00][34.00][218.00][34.00][141.00][]", "[][grow][10.00][][23.00][133.00,grow][29.00][69.00][19.00][232.00,grow][18.00][27.00][17.00]"));

		btnRetournerAuJeu = new JButton("Retourner au jeu");
		btnRetournerAuJeu.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnRetournerAuJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.requestFocus(true);
				monde.demarrer();
				setVisible(false);
			}
		});

		lblMusique = new JLabel("Volume Musique");
		add(lblMusique, "cell 1 1,alignx center,aligny center");

		sliderMusique = new JSlider();
		sliderMusique.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//volume musique
			}
		});
		add(sliderMusique, "cell 2 1 3 1,growx");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "cell 7 1 6 9,grow");

		panelLevelUp = new JPanel();
		tabbedPane.addTab("Caract�ristiques", null, panelLevelUp, null);
		panelLevelUp.setLayout(new MigLayout("", "[80][220.00,grow][80][216.00,grow][80]", "[80][432.00,grow][80]"));
		
		panelCaracteristiquesJ1 = new PanelCaracteristiques(monde.getJoueur1());
		panelCaracteristiquesJ1.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panelLevelUp.add(panelCaracteristiquesJ1, "cell 1 1,grow");
		
		panelCaracteristiquesJ2 = new PanelCaracteristiques(monde.getJoueur1()); //MODIFIER PAR J2
		panelCaracteristiquesJ2.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panelLevelUp.add(panelCaracteristiquesJ2, "cell 3 1,grow");
		
		
		panelControlles = new JPanel();
		tabbedPane.addTab("Contr�les", null, panelControlles, null);
		panelControlles.setLayout(new GridLayout(1, 0, 0, 0));
		
		tabbedPaneControles = new JTabbedPane(JTabbedPane.TOP);
		panelControlles.add(tabbedPaneControles);
		
		panelTabControlesJ1 = new JPanel();
		tabbedPaneControles.addTab("Joueur 1", null, panelTabControlesJ1, null);
		panelTabControlesJ1.setLayout(new MigLayout("", "[][80.00px,grow][485.00][][80.00,grow]", "[120.00px,grow][438.00][120.00,grow]"));
		
		panelControlesJ1 = new PanelControlles(0, monde.getKeyJoueurs(), monde.getBindingsManetteJ1());
		//sl_panelTabControlesJ1.putConstraint(SpringLayout.VERTICAL_CENTER, panelControlesJ1, 5, SpringLayout.VERTICAL_CENTER, panelTabControlesJ1);
		panelTabControlesJ1.add(panelControlesJ1, "cell 2 1,grow");
		
		btnDefaultJ1 = new JButton("R\u00E9initialiser les controles");
		btnDefaultJ1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDefaultJ1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelControlesJ1.reinitialiser();
				monde.setControleJ1(Controle.CLAVIER);
			}
		});
		panelTabControlesJ1.add(btnDefaultJ1, "cell 0 2,alignx left");
		
		btnSauvegardercontrolesJ1 = new JButton("Sauvegarder les controles");
		btnSauvegardercontrolesJ1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSauvegardercontrolesJ1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				monde.setControleJ1(panelControlesJ1.getControle());
			}
		});
		panelTabControlesJ1.add(btnSauvegardercontrolesJ1, "cell 3 2,alignx right,aligny center");
		
		if (monde.isDuo()) {
			panelTabControlesJ2 = new JPanel();
			tabbedPaneControles.addTab("Joueur 2", null, panelTabControlesJ2, null);
			panelTabControlesJ2.setLayout(new MigLayout("", "[80.00px,grow][485.00][80.00,grow]", "[120.00px,grow][438.00][120.00,grow]"));
		
			panelControlesJ2 = new PanelControlles(1, monde.getKeyJoueurs(), monde.getBindingsManetteJ2());
			panelTabControlesJ2.add(panelControlesJ2, "cell 1 1,grow");
		}

		lblVolumeEffets = new JLabel("Volume Effets");
		add(lblVolumeEffets, "cell 1 3,alignx center,aligny center");

		sliderEffets = new JSlider();
		sliderEffets.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//musique effets
			}
		});
		add(sliderEffets, "cell 2 3 3 1,growx");

		panelDifficulte = new JPanel();
		panelDifficulte.setBackground(Color.RED);
		add(panelDifficulte, "cell 1 5 4 1,grow");
		panelDifficulte.setLayout(null);
		
		lblDifficulte = new JLabel("Difficult\u00E9");
		lblDifficulte.setBounds(10, 23, 66, 31);
		panelDifficulte.add(lblDifficulte);
		
		rdbtnFacile = new JRadioButton("D\u00E9butant");
		rdbtnFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.changerDifficulte(Difficulte.FACILE);
			}
		});
		rdbtnFacile.setBackground(Color.RED);
		rdbtnFacile.setBounds(111, 0, 135, 40);
		panelDifficulte.add(rdbtnFacile);
		
		rdbtnMoyen = new JRadioButton("Interm\u00E9diaire");
		rdbtnMoyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.changerDifficulte(Difficulte.MOYEN);
			}
		});
		rdbtnMoyen.setBackground(Color.RED);
		rdbtnMoyen.setBounds(143, 43, 135, 40);
		panelDifficulte.add(rdbtnMoyen);
		
		rdbtnDifficile = new JRadioButton("Expert");
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.changerDifficulte(Difficulte.DIFFICILE);
			}
		});
		rdbtnDifficile.setBackground(Color.RED);
		rdbtnDifficile.setBounds(184, 86, 135, 40);
		panelDifficulte.add(rdbtnDifficile);
		
		ButtonGroup rdbtnGroup = new ButtonGroup();
		rdbtnGroup.add(rdbtnFacile);
		rdbtnGroup.add(rdbtnMoyen);
		rdbtnGroup.add(rdbtnDifficile);
		
		panelAffichage = new JPanel();
		panelAffichage.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panelAffichage.setBackground(Color.RED);
		add(panelAffichage, "cell 1 7 5 3,grow");
		panelAffichage.setLayout(new GridLayout(0, 2, 0, 0));
		
		chckbxAffichageScientifique = new JCheckBox("Affichage Scientifique");
		chckbxAffichageScientifique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.affichageScientifique(chckbxAffichageScientifique.isSelected());
			}
		});
		chckbxAffichageScientifique.setBackground(Color.RED);
		chckbxAffichageScientifique.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxAffichageScientifique);
		
		chckbxModeInvulnerable = new JCheckBox("Mode invulnerable");
		chckbxModeInvulnerable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.activerGodMode(chckbxModeInvulnerable.isSelected());
			}
		});
		chckbxModeInvulnerable.setBackground(Color.RED);
		chckbxModeInvulnerable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxModeInvulnerable);
		
		chckbxVecteursAcceleration = new JCheckBox("Afficher vecteurs acceleration");
		chckbxVecteursAcceleration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.afficherVecteursAcceleration(chckbxVecteursAcceleration.isSelected());
			}
		});
		chckbxVecteursAcceleration.setBackground(Color.RED);
		chckbxVecteursAcceleration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxVecteursAcceleration);
		
		chckbxVecteursVitesse = new JCheckBox("Afficher vecteurs vitesse");
		chckbxVecteursVitesse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.afficherVecteursVitesse(chckbxVecteursVitesse.isSelected());
			}
		});
		chckbxVecteursVitesse.setBackground(Color.RED);
		chckbxVecteursVitesse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxVecteursVitesse);
		
		chckbxAfficherHitboxJoueurs = new JCheckBox("<html>Afficher zones de<br>collisions des joueurs</html>");
		chckbxAfficherHitboxJoueurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.afficherHitboxJoueurs(chckbxAfficherHitboxJoueurs.isSelected());
			}
		});
		chckbxAfficherHitboxJoueurs.setBackground(Color.RED);
		chckbxAfficherHitboxJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxAfficherHitboxJoueurs);
		
		chckbxAfficherHitboxMonstres = new JCheckBox("<html>Afficher zones de<br>collisions des monstres</html>");
		chckbxAfficherHitboxMonstres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.afficherHitboxMonstres(chckbxAfficherHitboxMonstres.isSelected());
			}
		});
		chckbxAfficherHitboxMonstres.setBackground(Color.RED);
		chckbxAfficherHitboxMonstres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxAfficherHitboxMonstres);
		
		chckbxAfficherMeshAI = new JCheckBox("<html>Afficher graphique<br>de navigation de l'I.A.</html>");
		chckbxAfficherMeshAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.afficherNavMesh(chckbxAfficherMeshAI.isSelected());
			}
		});
		chckbxAfficherMeshAI.setBackground(Color.RED);
		chckbxAfficherMeshAI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxAfficherMeshAI);
		
		chckbxActiverItemSpawn = new JCheckBox("<html>Activer la posibilite d'ajouter<br>des items dans l'inventaire</html>");
		chckbxActiverItemSpawn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.itemSpawnAcces(chckbxActiverItemSpawn.isSelected());
			}
		});
		chckbxActiverItemSpawn.setBackground(Color.RED);
		chckbxActiverItemSpawn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelAffichage.add(chckbxActiverItemSpawn);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				monde.setLargeur(slider.getValue());
			}
		});
		slider.setValue(40);
		slider.setMinimum(20);
		slider.setMaximum(300);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		panelAffichage.add(slider);
		
		btnAjouerExp = new JButton("Ajouer 5000 exp");
		btnAjouerExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				monde.getJoueur1().ajouterExperience(5000);
			}
		});
		btnAjouerExp.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAffichage.add(btnAjouerExp);
		add(btnRetournerAuJeu, "cell 1 11 2 1,grow");

		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sauvegarder();
			}
		});

		btnAide = new JButton("Aide");
		btnAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAide.setVisible(true);
			}
		});
		btnAide.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(btnAide, "cell 4 11 2 1,grow");
		btnSauvegarder.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(btnSauvegarder, "cell 8 11,grow");

		btnRetournerAuMenu = new JButton("<html>Retourner au<br>menu principal</html>");
		btnRetournerAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Enregistrer", "Retourner sans enregistrer", "Annuler"};
				int n = JOptionPane.showOptionDialog(null, "�tes-vous s�r de vouloir retourner au menu principal sans sauvegarder?", "Retour au menu principal",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				switch (n) {
				case 0 : 
					if (sauvegarder())
						monde.fermerMonde();
				break;
				case 1 : monde.fermerMonde();
				break;
				}
			}
		});
		btnRetournerAuMenu.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(btnRetournerAuMenu, "cell 10 11,grow");

		btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Enregistrer", "Quitter sans enregistrer", "Annuler"};
				int n = JOptionPane.showOptionDialog(null, "�tes-vous s�r de vouloir quitter sans sauvegarder?", "Quitter",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				switch (n) {
				case 0 : 
					if (sauvegarder())
						System.exit(0);
				break;
				case 1 : System.exit(0);
				break;
				}
			}
		});
		add(btnQuitter, "cell 12 11,grow");
		
		switch(Monde.difficulte) {
		case DIFFICILE:
			rdbtnDifficile.setSelected(true);
			break;
		case FACILE:
			rdbtnFacile.setSelected(true);
			break;
		case MOYEN:
			rdbtnMoyen.setSelected(true);
			break;
		default:
			//fait rien par defaut
			break;			
		}
	}
	/**
	 * Sauvegarde la partie
	 */
	private boolean sauvegarder() {
		boolean sauvegardeAvecSucces = false;
		String nomSauvegarde = (String) JOptionPane.showInputDialog(null, "Entrez le nom de la sauvegarde", 
				"Sauvegarder", JOptionPane.PLAIN_MESSAGE);
		if (nomSauvegarde != null) {
			if (nomSauvegarde.equals("")) monde.sauvegarder(monde.getNomSauvegarde());			
			else monde.sauvegarder(nomSauvegarde);
			sauvegardeAvecSucces = true;
		}
		return sauvegardeAvecSucces;
	}
	/**
	 * Definit le joueur 1
	 * @param joueur Le joueur 1
	 */
	public void setJoueur1(Joueur joueur) {
		panelCaracteristiquesJ1.updateJoueur(joueur);
	}
	/**
	 * Definit le joueur 2
	 * @param joueur Le joueur 2
	 */
	public void setJoueur2(Joueur joueur) {
		panelCaracteristiquesJ2.updateJoueur(joueur);
	}
}
