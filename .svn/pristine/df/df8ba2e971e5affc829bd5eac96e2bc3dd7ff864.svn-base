package monde;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entites.Joueur;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Le panel des caracteristiques
 * @author Mihai
 *
 */
public class PanelCaracteristiques extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton btnRinitialiser;
	private JLabel lblTextePointsAttribuables;
	private JLabel lblTexteForce;
	private JLabel lblTexteAgilite;
	private JLabel lblTexteChance;
	private JLabel lblTexteResistance;
	private JButton btnForce;
	private JButton btnAgilite;
	private JButton btnChance;
	private JButton btnResistance;
	private JButton btnSave;
	private JLabel lblForce;
	private JLabel lblAgilite;
	private JLabel lblChance;
	private JLabel lblResistance;
	private int[] stats;
	private int[] INITIAL_STATS = new int[4];
	private int POINTS_ATTRIBUABLES_DEPART;
	private JLabel lblPointsAttribuables;
	
	/**
	 * Constructeur du panel des caracteristiques
	 * @param joueur Un joueur
	 */
	public PanelCaracteristiques(Joueur joueur) {
		stats = joueur.getStats();
		for (int i = 0; i < stats.length; i++)
			INITIAL_STATS[i] = stats[i];
		POINTS_ATTRIBUABLES_DEPART = joueur.getPointsAttribuables();
		
		setLayout(new MigLayout("", "[151.00][52.00][55.00][36.00][61.00]", "[58.00][10][55.00][10][55.00][10][55.00][10.00][55.00][10][61.00]"));
		
		lblTextePointsAttribuables = new JLabel("Points attribuables");
		add(lblTextePointsAttribuables, "cell 0 0,alignx center");
		
		lblPointsAttribuables = new JLabel(joueur.getPointsAttribuables()+"");
		add(lblPointsAttribuables, "cell 4 0");
		
		lblTexteForce = new JLabel("Force");
		add(lblTexteForce, "cell 0 2,alignx center");
		
		btnForce = new JButton("");
		btnForce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (joueur.getPointsAttribuables() > 0 && stats[0] < 10) {
					stats[0]++;
					lblForce.setText(stats[0]+"");
					joueur.enleverPointsAttribuables();
					lblPointsAttribuables.setText(joueur.getPointsAttribuables()+"");
				}				
			}
		});
		add(btnForce, "cell 2 2,grow");
		
		lblForce = new JLabel(stats[0]+"");
		add(lblForce, "cell 4 2,alignx center");
		
		lblTexteAgilite = new JLabel("Agilit\u00E9");
		add(lblTexteAgilite, "cell 0 4,alignx center");
		
		btnAgilite = new JButton("");
		btnAgilite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (joueur.getPointsAttribuables() > 0 && stats[2] < 10) {
					stats[2]++;
					lblAgilite.setText(stats[2]+"");
					joueur.enleverPointsAttribuables();
					lblPointsAttribuables.setText(joueur.getPointsAttribuables()+"");
				}				
			}
		});
		add(btnAgilite, "cell 2 4,grow");
		
		lblAgilite = new JLabel(stats[2]+"");
		add(lblAgilite, "cell 4 4,alignx center");
		
		lblTexteChance = new JLabel("Chance");
		add(lblTexteChance, "cell 0 6,alignx center");
		
		btnChance = new JButton("");
		btnChance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (joueur.getPointsAttribuables() > 0 && stats[1] < 10) {
					stats[1]++;
					lblChance.setText(stats[1]+"");
					joueur.enleverPointsAttribuables();
					lblPointsAttribuables.setText(joueur.getPointsAttribuables()+"");
				}
			}
		});
		add(btnChance, "cell 2 6,grow");
		
		lblChance = new JLabel(stats[1]+"");
		add(lblChance, "cell 4 6,alignx center");
		
		lblTexteResistance = new JLabel("R\u00E9sistance");
		add(lblTexteResistance, "cell 0 8,alignx center");
		
		btnResistance = new JButton("");
		btnResistance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (joueur.getPointsAttribuables() > 0 && stats[3] < 10) {
					stats[3]++;
					lblResistance.setText(stats[3] + "");
					joueur.enleverPointsAttribuables();
					lblPointsAttribuables.setText(joueur.getPointsAttribuables()+"");
				}
			}
		});
		add(btnResistance, "cell 2 8,grow");
		
		lblResistance = new JLabel(stats[3]+"");
		add(lblResistance, "cell 4 8,alignx center");
		
		btnRinitialiser = new JButton("R\u00E9initialiser");
		btnRinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < stats.length; i++) {
					stats[i] = INITIAL_STATS[i];
					System.out.println(stats[i]);
				}
				lblForce.setText(INITIAL_STATS[0]+"");
				lblChance.setText(INITIAL_STATS[1]+"");
				lblAgilite.setText(INITIAL_STATS[2]+"");
				lblResistance.setText(INITIAL_STATS[3]+"");

				joueur.setPointsAttribuables(POINTS_ATTRIBUABLES_DEPART);
				lblPointsAttribuables.setText(POINTS_ATTRIBUABLES_DEPART+"");
			}
		});
		add(btnRinitialiser, "cell 0 10,grow");
		
		btnSave = new JButton("<html>Sauvegarder<br>caract\u00E9ristiques</html>");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueur.setStats(stats);
				joueur.calculerStats();
			}
		});
		add(btnSave, "cell 3 10 2 1,grow");
		
	}
	public void updateJoueur(Joueur joueur) {
		stats = joueur.getStats();
		for (int i = 0; i < stats.length; i++) 
			INITIAL_STATS[i] = stats[i];
		POINTS_ATTRIBUABLES_DEPART = joueur.getPointsAttribuables();
		lblPointsAttribuables.setText(joueur.getPointsAttribuables()+"");
	}
}
