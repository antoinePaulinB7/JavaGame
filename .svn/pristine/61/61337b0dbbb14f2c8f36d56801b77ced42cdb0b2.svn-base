package monde;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;

import ecouteurs.MondeListenerInfosJ1;
import editeur.Outils;
import net.miginfocom.swing.MigLayout;

/**
 * La boite qui affiche les informations du jeu
 * @author Mihai
 *
 */
public class InformationsJeu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblNiveau;
	private JLabel lblExperience;
	private JLabel lblPointsDeVie;
	private JLabel lblScore;
	private JTextPane txtpnInfos;
	private JScrollPane scrlpnInfos;
	private Color couleurBackground = new Color(10, 30, 35, 129);
	
	/**
	 * Constructeur des informations du jeu
	 * @param monde Le monde
	 * @param score Le score
	 */
	public InformationsJeu(Monde monde, long score) {
		setBackground(couleurBackground);
		setLayout(new MigLayout("", "[60.00][130.00][100.00][100.00]", "[45.00][8.00][245.00,grow]"));
		
		lblNiveau = new JLabel("Niveau : " + monde.getJoueur1().getNiveau());
		lblNiveau.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNiveau.setForeground(Color.white);
		add(lblNiveau, "cell 0 0");
		
		lblExperience = new JLabel("Exp\u00E9rience : " + monde.getJoueur1().getExperience());
		lblExperience.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExperience.setForeground(Color.white);
		add(lblExperience, "cell 1 0,alignx center");
		
		lblPointsDeVie = new JLabel("Points de vie : " + monde.getJoueur1().getPointsDeVie());
		lblPointsDeVie.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPointsDeVie.setForeground(Color.white);
		add(lblPointsDeVie, "cell 2 0");
		
		lblScore = new JLabel("Score : " + score);
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScore.setForeground(Color.white);
		add(lblScore, "cell 3 0,alignx center");
		
		txtpnInfos = new JTextPane();
		txtpnInfos.setOpaque(false);
		txtpnInfos.setBackground(couleurBackground);
		txtpnInfos.setEditable(false);
		txtpnInfos.setHighlighter(null);
		txtpnInfos.setFocusable(false);
		
		scrlpnInfos = new JScrollPane(txtpnInfos);
		scrlpnInfos.setBackground(couleurBackground);
		scrlpnInfos.getViewport().setBackground(couleurBackground);
		add(scrlpnInfos, "flowx,cell 0 2 4 1,grow");
		
		monde.addMondeListenerInfosJ1(new MondeListenerInfosJ1() {
			public void experienceAjouteJ1(int niveau, double experience, double pointsDeVie) {
				setNiveau(niveau);
				setExperience(experience);
				setHp(pointsDeVie);
			}
			public void scoreAugmente(long score) {
				setScore(score);
			}
			public void updateVieJ1(double pointsDeVie) {
				setHp(pointsDeVie);
			}
		});
	}
	/**
	 * Met a jour le niveau
	 * @param niveau Un niveau
	 */
	private void setNiveau(int niveau) {
		lblNiveau.setText("Niveau : " + niveau);
	}
	/**
	 * Met a jour le score
	 * @param score Un score
	 */
	private void setScore(long score) {
		lblScore.setText("Score : " + score);
	}
	/**
	 * Met a jour l'experience
	 * @param experience L'experience
	 */
	private void setExperience(double experience) {
		lblExperience.setText("Experience : " + Outils.ajusterPrecision(experience, 2) + "   ");
	}
	/**
	 * Met a jour les points de vie
	 * @param hp Les points de vie
	 */
	private void setHp(double hp) {
		lblPointsDeVie.setText("Points de vie : " + Outils.ajusterPrecision(hp, 2));
	}
	/**
	 * Envoye le document du text pane informations
	 * @return Le document
	 */
	public Document getDocument() {
		return txtpnInfos.getDocument();
	}
	
}
