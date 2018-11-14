package temporaire;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import entites.Joueur;
import entites.Sprite;
import interfaces.Enumerations.ModeDeJeu;
import personnages.Personnage;

/**
	 * Fenetre de l'editeur des personnages
	 * @author Mihai
	 *
	 */
	public class FenetreCreationPersonnageObsolete extends JPanel {

		private static final long serialVersionUID = 1L;

		//Composantes du JFrame
		private JPanel panelJ1, panelJ2;;
		private JTextField txtNomJ1, txtNomJ2, txtInfo, txtInfo2;
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
		private Personnage personnageJ1, personnageJ2;
		private List<Joueur> joueurs = new ArrayList<Joueur>();
		private ModeDeJeu mode = ModeDeJeu.SOLO;	
		Font base;
		Font title;
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FenetreCreationPersonnageObsolete frame = new FenetreCreationPersonnageObsolete();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		
		/**
		 * Creation de l'editeur de personnage
		 */
		public FenetreCreationPersonnageObsolete() {	
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

			txtInfo = new JTextField();
			txtInfo.setBounds(276, 11, 164, 326);
			txtInfo.setHorizontalAlignment(SwingConstants.CENTER);
			txtInfo.setText("Informations");
			txtInfo.setColumns(10);
			//fin boutons stats plus

			btnCreerPerso = new JButton("Cr\u00E9er personnage");
			btnCreerPerso.setBounds(276, 344, 164, 50);
			btnCreerPerso.setMargin(new Insets(0, 0, 0, 0));
			btnCreerPerso.setFont(base.deriveFont(18f));

			lblTexteChoisirPersonnages = new JLabel("Choisissez votre personnage");
			lblTexteChoisirPersonnages.setBounds(87, 0, 826, 54);
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

			txtInfo2 = new JTextField();
			txtInfo2.setBounds(276, 11, 164, 326);
			txtInfo2.setText("Informations");
			txtInfo2.setHorizontalAlignment(SwingConstants.CENTER);
			txtInfo2.setColumns(10);

			lblTextJoueur1 = new JLabel("Joueur 1");
			lblTextJoueur1.setBounds(196, 54, 109, 44);
			lblTextJoueur1.setFont(base.deriveFont(20f));

			btnRetour = new JButton("Retour");
			btnRetour.setBounds(40, 510, 153, 60);
			btnRetour.setFont(base.deriveFont(20f));
			btnRetour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
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
							System.out.println("Game start");
						} else {
							JOptionPane.showMessageDialog(null, "Veuillez creer un personnage pour tous les joueurs");
						}					
					} else {
						if (personnageJ1 != null) {							
							setVisible(false);
							System.out.println("Game start");						
						} else {
							JOptionPane.showMessageDialog(null, "Veuillez creer un personnage");
						}
					}				
				}
			});

			lblTextJoueur2 = new JLabel("Joueur 2");
			lblTextJoueur2.setBounds(682, 54, 103, 44);
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
						joueurs.add(new Joueur(25, 0, personnageJ1));
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
			panelJ1.add(txtInfo);
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
			panelJ2.add(txtInfo2);
			panelJ2.add(btnCreerPerso2);
			add(btnRetour);
			add(btnCommencer);
			add(lblTexteChoisirPersonnages);
			
			panelJ1.setBounds(25, 90, 450, 403);
			
		}
		
		public void setBtnCommencerDisabled() {
			if (personnageJ1 == null || personnageJ2 == null) btnCommencer.setEnabled(false);
		}
		/**
		 * Enleve des points du joueur 1 d'une characteristique
		 * @param nb Le numero de la characteristique
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
		 * Enleve des points du joueur 2 d'une characteristique
		 * @param nb Le numero de la characteristique
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
		 * Ajoute des points du joueur 1 d'une characteristique
		 * @param nb Le numero de la characteristique
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
		 * Ajoute des points du joueur 2 d'une characteristique
		 * @param nb
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
				xBounds = 134;
				yBounds = 121;
			} else {
				xBounds = 339;
				yBounds = 123;
			}			
			listeSpritesJ1.forEach(sprite -> sprite.setBounds( xBounds, yBounds, taille, taille));
			add(listeSpritesJ1.get(numeroPersonnageJ1));
			listeSpritesJ1.get(numeroPersonnageJ1).demarrer();
		}
		/**
		 * Affiche le sprite du joueur 2
		 */
		private void afficherPersonnagePourJ2() {
			listeSpritesJ2.forEach(sprite -> sprite.setBounds( 542, 121, taille, taille));
			add(listeSpritesJ2.get(numeroPersonnageJ2));
			listeSpritesJ2.get(numeroPersonnageJ2).demarrer();
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
		public void setMode(ModeDeJeu mode) {
			this.mode = mode;
			if (mode == ModeDeJeu.SOLO) {	
				remove(panelJ2);
				remove(lblTextJoueur2);
				afficherPersonnagePourJ1(mode);
				enleverPersonnagePourJ2();				
				//panelJ1.setBounds(216, 80, 402, 337);
				//lblTextJoueur1.setBounds(380, 47, 75, 22);
			} else {
				add(panelJ2);
				panelJ1.setBounds(25, 90, 450, 403);
				lblTextJoueur1.setBounds(174, 32, 75, 22);
				afficherPersonnagePourJ1(mode);
				afficherPersonnagePourJ2();			
				add(lblTextJoueur2);
			}			
		}
	}