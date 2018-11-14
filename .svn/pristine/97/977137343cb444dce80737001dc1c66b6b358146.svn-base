package temporaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import personnages.Personnage;
import procedural.Seed;

public class App10PlateformeObsolete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MondeObsolete monde;
	private Menu1 menu1;

	/**
	 * Lance la fenetre principale
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App10PlateformeObsolete frame = new App10PlateformeObsolete();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creation de l'application
	 */
	public App10PlateformeObsolete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1253, 701);
		setTitle("Jeu de plateformes");
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		Personnage[] personnages = new Personnage[2];
		int[] stats = new int[4];
		//les personnage seront lus de quelque part d autre
		personnages[0] = new Personnage("Mihai", "thief", stats);
		personnages[1] = new Personnage("Antoine", "viking", stats);
		monde = new MondeObsolete(2, Difficulte.FACILE, new Seed(12423), new Controle[2], personnages);
		contentPane.add(monde);
		menu1 = new Menu1();
		//contentPane.add(menu1);
		
	}
	/**
	 * 
	 * @author Mihai
	 *
	 */
	public class Menu1 extends JPanel {

		private static final long serialVersionUID = 1L;

		public Menu1() {
			setBackground(Color.RED);
			setLayout(null);
			
			JLabel lblTitre = new JLabel("Random Quest");
			lblTitre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblTitre.setBounds(164, 11, 130, 30);
			add(lblTitre);
			
			JButton btnJouer = new JButton("Jouer");
			btnJouer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnJouer.setBounds(161, 52, 115, 57);
			btnJouer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//debut
					monde.demarrer();
					
					//fin
				}
			});
			add(btnJouer);
			
			JButton btnOption = new JButton("Options");
			btnOption.setBounds(174, 120, 89, 23);
			add(btnOption);
			
			JButton btnAide = new JButton("Aide");
			btnAide.setBounds(174, 154, 89, 23);
			add(btnAide);
			
			JButton btnQuitter = new JButton("Quitter");
			btnQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//debut
					System.exit(0);
					//fin
				}
			});
			btnQuitter.setBounds(174, 188, 89, 23);
			add(btnQuitter);
			
			JButton btnNewButton = new JButton("Jouer Imm\u00E9diatement (temp)");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.add(monde);
					menu1.setVisible(false);
					monde.setVisible(true);
					monde.requestFocusInWindow();
				}
			});
			btnNewButton.setBounds(119, 243, 228, 23);
			add(btnNewButton);

		}
	}
}
