package menus;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * L'affichage du panel aide
 * @author Antoine
 *
 */
public class PanelAide extends JPanel{

	private static final long serialVersionUID = 1L;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	/**
	 * Constructeur du panel aide
	 */
	public PanelAide() {
		setPreferredSize(new Dimension(800,325));
		setLayout(null);
		tabbedPane.setBounds(0, 0, 800, 325);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Guide d'utilisation", null, panel, null);
				panel.setLayout(null);
		
		JTextPane txtpnQuteVersLinfini = new JTextPane();
		txtpnQuteVersLinfini.setBounds(0, 0, 795, 60);
		panel.add(txtpnQuteVersLinfini);
		txtpnQuteVersLinfini.setText("Qu\u00EAte vers l\u2019infini est un jeu de type platformer qui permet \u00E0 un ou deux joueurs d\u2019explorer un monde infini g\u00E9n\u00E9r\u00E9 de fa\u00E7on proc\u00E9durale, de combattre des monstres, d\u2019acqu\u00E9rir des tr\u00E9sors et de gagner de l\u2019exp\u00E9rience pour monter de niveau. Pour toute question sur une des fonctionnalit\u00E9s, r\u00E9f\u00E9rez-vous \u00E0 l\u2019arbre ci-dessous.");
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(2, 67, 793, 230);
				panel.add(scrollPane);
		
				
				JTree tree = new JTree();
				scrollPane.setViewportView(tree);
				tree.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Fonctionnalit\u00E9s") {						
						private static final long serialVersionUID = 1L;

						{
							DefaultMutableTreeNode node_1;
							DefaultMutableTreeNode node_2;
							node_1 = new DefaultMutableTreeNode("Contr\u00F4les");
								node_2 = new DefaultMutableTreeNode("Mouvement");
									node_2.add(new DefaultMutableTreeNode("W, A, S, D pour joueur 1"));
									node_2.add(new DefaultMutableTreeNode("Fl\u00E8ches pour joueur 2"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Attaquer");
									node_2.add(new DefaultMutableTreeNode("V pour joueur 1"));
									node_2.add(new DefaultMutableTreeNode("1 sur le pav\u00E9 num. pour joueur 2"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Bloquer");
									node_2.add(new DefaultMutableTreeNode("B pour joueur 1"));
									node_2.add(new DefaultMutableTreeNode("2 sur le pav\u00E9 num. pour joueur 2"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Attaque propuls\u00E9e");
									node_2.add(new DefaultMutableTreeNode("Attaque + d\u00E9placement\t\t"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Roulade");
									node_2.add(new DefaultMutableTreeNode("Bloc + d\u00E9placement"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Attaque sp\u00E9ciale");
									node_2.add(new DefaultMutableTreeNode("Attaque + Bloc"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Inventaire");
									node_2.add(new DefaultMutableTreeNode("I pour joueur 1"));
									node_2.add(new DefaultMutableTreeNode("W et S pour naviguer l'inventaire"));
									node_2.add(new DefaultMutableTreeNode("V pour s\u00E9lectionner un item"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Pause");
									node_2.add(new DefaultMutableTreeNode("Bouton Esc"));
								node_1.add(node_2);
							add(node_1);
							node_1 = new DefaultMutableTreeNode("Lancer une partie");
								node_1.add(new DefaultMutableTreeNode("Jouer \u2192 Nouvelle Partie \u2192 Choisir la difficult\u00E9, le mode de jeu et le Seed \u2192 Choisir les contr\u00F4les \u2192 Cr\u00E9er un personnage \u2192 Commencer"));
							add(node_1);
							node_1 = new DefaultMutableTreeNode("Relancer une partie sauvegard\u00E9e");
								node_1.add(new DefaultMutableTreeNode("Jouer \u2192 Continuer \u2192 Le nom de la partie \u2192 Continuer"));
							add(node_1);
							node_1 = new DefaultMutableTreeNode("Jouer");
								node_2 = new DefaultMutableTreeNode("Se d\u00E9placer");
									node_2.add(new DefaultMutableTreeNode("Utiliser les boutons de mouvement"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Tuer un monstre");
									node_2.add(new DefaultMutableTreeNode("L'attaquer jusqu'\u00E0 ce que sa barre de vie tombe \u00E0 0"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Ramasser des items et de l'or");
									node_2.add(new DefaultMutableTreeNode("D\u00E9placer son personnage sur l'item"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Coffres");
									node_2.add(new DefaultMutableTreeNode("D\u00E9placer son personnage sur le coffre pour l'ouvrir"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Mort");
									node_2.add(new DefaultMutableTreeNode("Lorsque la barre de vie du joueur tombe \u00E0 0, le joueur est mort et une tombe est plac\u00E9e \u00E0 l'endroit ou le joueur est mort"));
									node_2.add(new DefaultMutableTreeNode("Pour recommencer \u00E0 jouer, cliquer sur recommencer"));
									node_2.add(new DefaultMutableTreeNode("Pour reprendre certains items perdus, retourner \u00E0 l'endroit de la mort et d\u00E9placer le personnage sur la tombe"));
								node_1.add(node_2);
								node_2 = new DefaultMutableTreeNode("Gagner de l'exp\u00E9rience et des niveaux");
									node_2.add(new DefaultMutableTreeNode("Tuer des monstres pour gagner de l'XP"));
									node_2.add(new DefaultMutableTreeNode("Utiliser l'XP pour gagner des niveaux\t"));
								node_1.add(node_2);
							add(node_1);
							node_1 = new DefaultMutableTreeNode("Changer le volume du son");
								node_1.add(new DefaultMutableTreeNode("Options, en dehors d'une partie"));
								node_1.add(new DefaultMutableTreeNode("Pause, durant une partie"));
							add(node_1);
						}
					}
				));
				
				JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Concepts scientifiques", null, tabbedPane_1, null);
				
				JPanel panel_1 = new JPanel();
				tabbedPane_1.addTab("Informatique", null, panel_1, null);
				panel_1.setLayout(null);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(0, 0, 790, 269);
				panel_1.add(scrollPane_1);
				
				JTextPane txtpncranPartag = new JTextPane();
				scrollPane_1.setViewportView(txtpncranPartag);
				txtpncranPartag.setEditable(false);
				txtpncranPartag.setText("\u00C9cran partag\u00E9"
						+"\n"
						+ "Une division dynamique de l�affichage requiert une �cam�ra intelligente� qui s�adapte aux diff�rentes situations en changeant le point de vue, afin d�afficher le plus d�informations utiles � l��cran. Ex : si les joueurs sont c�tes-�-c�tes, l��cran est fusionn� en un seul �cran. Par contre, si les joueurs s��loignent l�un de l�autre, l��cran se scinde en deux points de vue, comme si deux cam�ras allaient chacune chercher � voir un des personnages."
						+"\n"+"\n"
						+"Intelligence artificielle"
						+"\n"
						+"Un algorithme (A* ou dijkstra) qui g�re les monstres dont le but est de tuer un des joueurs (le plus proche g�n�ralement), incluant de la d�tection des joueurs, gestion des �tats (repos, attaque, d�placement) et pathfinding avec navigation mesh auto g�n�r�."
						+"\n"+"\n"
						+"Fichiers et sauvegardes"
						+"\n"
						+"Une classe qui �crit et une classe qui lit les fichiers de sauvegarde qui permettent aux joueurs de recommencer leur partie l� o� ils l�avaient laiss�e. Gr�ce � la g�n�ration proc�durale, il ne faut que sauvegarder les joueurs et les param�tres de la partie."
						+"\n"+"\n"
						+"D�tection de collisions"
						+"\n"
						+"Les collisions sont g�r�es par cas : elles sont distingu�es entre celles qui se font aux coins des zones de collisions, sur les bords, ainsi qu�en haut ou en bas."
						+"\n"+"\n"
						+"Syst�me de combat"
						+"\n"
						+"Le syst�me de combat permet l'interaction entre les joueurs et les monstres. Gr�ce au syst�me, il est possible d'attaquer, de bloquer des coups, de faire des attaques propuls�es et des attaques sp�ciales. Tout cela est d�montr� par des animations."
						+"\n"+"\n"
						+"Qu�tes infinies"
						+"\n"
						+"Des qu�tes autog�n�r�es � l'infini procurent aux joueurs des objectifs interactifs avec le monde."
						+"\n"+"\n"
						+"Contr�les personnalis�s"
						+"\n"
						+"Les joueurs peuvent choisir des contr�les personnalis�s pour jouer � leur fa�on et ces contr�les sont conserv�s avec les sauvegarde et sont modifiables n'importe quand."
						+"\n"+"\n"
						+"Syst�me de �Hand tracking�"
						+"\n"
						+"Ce syst�me permet de mettre des objets diff�rents dans les mains des personnages sans avoir � dessiner chaque image pour chaque personnage avec chaque arme."
						);
				
				JPanel panel_2 = new JPanel();
				tabbedPane_1.addTab("Math�matique", null, panel_2, null);
				panel_2.setLayout(null);
				
				JTextPane txtpnSeed = new JTextPane();
				txtpnSeed.setBounds(0, 0, 790, 269);
				txtpnSeed.setText("G�n�ration proc�durale"
						+"\n"
						+" Technique permettant de g�n�rer un monde � l�aide d�une simple s�rie de quelques chiffres appel�e �seed� et de simples op�rations lin�aires auxquelles on affecte des propri�t�s, qui donne un semblant de hasard tout en �tant parfaitement r�versible et r�utilisable."
						
						);
				panel_2.add(txtpnSeed);
				
				JPanel panel_3 = new JPanel();
				tabbedPane_1.addTab("Physique", null, panel_3, null);
				panel_3.setLayout(null);
				
				JTextPane txtpnCinmatique = new JTextPane();
				txtpnCinmatique.setBounds(0, 0, 790, 269);
				txtpnCinmatique.setText("Cin\u00E9matique"
						+"\n"
						+"Les personnages se d�placent dans le monde selon les lois de la cin�matique. Ils subissent les effets de la gravit� et de la friction. Leur vitesse de d�placement est une vitesse ponctuelle. Leur saut est aussi une vitesse ponctuelle."
						+"\n"+"\n"
						+"Dynamique"
						+"\n"
						+"Les d�g�ts de chute sont calcul�s en fonction de l'�nergie potentielle gravitationnelle. Une partie des d�g�ts de coups est calcul�e avec l'�nergie cin�tique."
						+"\n"+"\n"
						+"Fus�e"
						+"\n"
						+"Le mouvement fus�es (quoique rare) dans le jeu, est calcul� � l'aide de la formule de Tsiolkovsky."
						);
				panel_3.add(txtpnCinmatique);
				
				JPanel panel_4 = new JPanel();
				tabbedPane.addTab("� propos", null, panel_4, null);
				panel_4.setLayout(null);
				
				JTextPane txtpnJeuRalisPar = new JTextPane();
				txtpnJeuRalisPar.setText("Jeu r\u00E9alis\u00E9 par Mihai Floca, Olivier Lefebvre et Antoine Paulin-Bessette. 2017. Coll�ge de Maisonneuve."
						+"\n"
						+"INT�GRATION DES APPRENTISSAGES EN SCIENCES INFOS ET MATHS."
						);
				txtpnJeuRalisPar.setBounds(196, 75, 403, 70);
				panel_4.add(txtpnJeuRalisPar);
	}
}
