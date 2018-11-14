package aaplication;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.Enumerations.Location;

/**
 * Fenetre qui affiche tous les donnees scientifiques de l'application
 * @author Mihai
 *
 */
public class FenetreAffichageScientifique extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPosition, lblVitesse, lblAcceleration, lblFrames;
	private JLabel lblDistanceX, lblDistanceY, lblBiome, lblDanger, lblDifficulte, lblSeed;
	private JLabel lblNombreDePlateformes, lblNombreDeMonstres, lblVitesseDeDeplacement, lblHauteurDeSaut;
	private JLabel lblExperience, lblPointsDeVie, lblNiveau, lblPointsAttribuables;
	private JLabel lblTexteEtat;
	private JLabel lblEtat;
	private JLabel lblTexteScore;
	private JLabel lblScore;

	/**
	 * Creation de la fenetre
	 */
	public FenetreAffichageScientifique(String title, Location loc) {
		setAlwaysOnTop(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(0, 0, 352, 548);
		setLocation(loc);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblTexteFrames = new JLabel("Frames par seconde: ");
		lblTexteFrames.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteFrames);

		lblFrames = new JLabel("");
		lblFrames.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblFrames);

		JLabel lbTextePosition = new JLabel("Position du joueur: ");
		lbTextePosition.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lbTextePosition);

		lblPosition = new JLabel("");
		lblPosition.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblPosition);

		JLabel lblTexteVitesse = new JLabel("Vitesse du joueur: ");
		lblTexteVitesse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteVitesse);

		lblVitesse = new JLabel("");
		lblVitesse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblVitesse);

		JLabel lblTexteAcceleration = new JLabel("Acceleration du joueur: ");
		lblTexteAcceleration.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteAcceleration);

		lblAcceleration = new JLabel("");
		lblAcceleration.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblAcceleration);

		JLabel lbTextelDistanceX = new JLabel("Distance en x");
		lbTextelDistanceX.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lbTextelDistanceX);

		lblDistanceX = new JLabel("");
		lblDistanceX.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblDistanceX);

		JLabel lblTexteDistanceY = new JLabel("Distance en y");
		lblTexteDistanceY.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteDistanceY);

		lblDistanceY = new JLabel("");
		lblDistanceY.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblDistanceY);

		JLabel lblTexteBiome = new JLabel("Biome: ");
		lblTexteBiome.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteBiome);

		lblBiome = new JLabel("");
		lblBiome.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblBiome);

		JLabel lblTexteDanger = new JLabel("Danger: ");
		lblTexteDanger.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteDanger);

		lblDanger = new JLabel("");
		lblDanger.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblDanger);

		JLabel lblTexteDifficulte = new JLabel("Difficulte: ");
		lblTexteDifficulte.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteDifficulte);

		lblDifficulte = new JLabel("");
		lblDifficulte.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblDifficulte);

		JLabel lblTexteSeed = new JLabel("Seed: ");
		lblTexteSeed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteSeed);

		lblSeed = new JLabel("");
		lblSeed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblSeed);

		JLabel lblTexteNombreDePlateformes = new JLabel("Nombre de plateformes: ");
		lblTexteNombreDePlateformes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteNombreDePlateformes);

		lblNombreDePlateformes = new JLabel("");
		lblNombreDePlateformes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblNombreDePlateformes);

		JLabel lblTexteNombreDeMonstres = new JLabel("Nombre de monstres: ");
		lblTexteNombreDeMonstres.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteNombreDeMonstres);

		lblNombreDeMonstres = new JLabel("");
		lblNombreDeMonstres.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblNombreDeMonstres);

		JLabel lblTexteVitesseDeDeplacement = new JLabel("Vitesse de deplacement: ");
		lblTexteVitesseDeDeplacement.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteVitesseDeDeplacement);

		lblVitesseDeDeplacement = new JLabel("");
		lblVitesseDeDeplacement.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblVitesseDeDeplacement);

		JLabel lblTexteHauteurDeSaut = new JLabel("Hauteur de saut:");
		lblTexteHauteurDeSaut.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteHauteurDeSaut);

		lblHauteurDeSaut = new JLabel("");
		lblHauteurDeSaut.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblHauteurDeSaut);

		JLabel lblTexteExperience = new JLabel("Experience:");
		lblTexteExperience.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteExperience);

		lblExperience = new JLabel("");
		contentPane.add(lblExperience);

		JLabel lblTexteNiveau = new JLabel("Niveau:");
		lblTexteNiveau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteNiveau);

		lblNiveau = new JLabel("");
		lblNiveau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblNiveau);

		JLabel lblTextePointsAttribuables = new JLabel("Points attribuables: ");
		lblTextePointsAttribuables.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTextePointsAttribuables);

		lblPointsAttribuables = new JLabel("");
		lblPointsAttribuables.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblPointsAttribuables);

		JLabel lblTextePointsDeVie = new JLabel("Points de vie:");
		lblTextePointsDeVie.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTextePointsDeVie);

		lblPointsDeVie = new JLabel("");
		lblPointsDeVie.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblPointsDeVie);
		
		lblTexteEtat = new JLabel("Etat du joueur: ");
		lblTexteEtat.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteEtat);
		
		lblEtat = new JLabel("");
		lblEtat.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblEtat);
		
		lblTexteScore = new JLabel("Score:");
		lblTexteScore.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblTexteScore);
		
		lblScore = new JLabel("");
		lblScore.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblScore);
	}
	/**
	 * Change la position de la fenetre selon un parametre location
	 * @param loc Une position
	 */
	private void setLocation(Location loc) {
		int posX = 0, posY = 0;
		switch (loc) {
		case TOP_LEFT:
			posX = 0;
			posY = 0;
			break;
		case TOP_RIGHT:
			posX = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() - this.getWidth());
			posY = 20;
			break;		
		}
		setLocation(posX, posY);
	}
	/**
	 * Definit le texte des labels selon l'affichage par frame
	 * @param pos Une position
	 * @param vit Une vitesse
	 * @param accel Une acceleration	 
	 * @param etat Un etat
	 * @param score Un score
	 */
	public void setLabelsAffichageParFrame(String pos, String vit, String accel, String etat, String score) {
		lblPosition.setText(pos);
		lblVitesse.setText(vit);
		lblAcceleration.setText(accel);
		lblEtat.setText(etat);
		lblScore.setText(score);
	}
	/**
	 * Definit le texte des labels selon l'affichage par changement de tableau
	 * @param distX Une distance en x
	 * @param distY Une distance en y
	 * @param biome Un numero de biome
	 * @param danger Un numero de danger
	 * @param difficulte Une difficulte
	 * @param seed Un seed
	 * @param nbPlateformes Un numero de plateformes
	 * @param nbMonstres Un numero des monstres
	 * @param vitesseDeplacement Une vitesse de deplacement
	 * @param hauteurSaut Une hauteur de saut
	 */
	public void setLabelsAffichageParTableau(String distX, String distY, String biome, String danger, String difficulte,
			String seed, String nbPlateformes, String nbMonstres, String vitesseDeplacement, String hauteurSaut) {
		lblDistanceX.setText(distX);
		lblDistanceY.setText(distY);
		lblBiome.setText(biome);
		lblDanger.setText(danger);
		lblDifficulte.setText(difficulte);
		lblSeed.setText(seed);
		lblNombreDePlateformes.setText(nbPlateformes);
		lblNombreDeMonstres.setText(nbMonstres);
		lblVitesseDeDeplacement.setText(vitesseDeplacement);
		lblHauteurDeSaut.setText(hauteurSaut);	
	}
	/**
	 * Definit le texte des labels selon l'affichage par changement de tableau
	 * @param experience L'experience
	 * @param pointsDeVie Des points de vie
	 * @param niveau Un niveau
	 * @param pointsAttribuables Des points attribuables
	 */
	public void setLabelAffichageStats(String experience, String pointsDeVie, String niveau, String pointsAttribuables) {
		lblExperience.setText(experience);
		lblPointsDeVie.setText(pointsDeVie);
		lblNiveau.setText(niveau);
		lblPointsAttribuables.setText(pointsAttribuables);
	}
	/**
	 * Definit le texte du label des frames
	 * @param frames Des frames
	 */
	public void setLabelCompteurFrames(String frames) {
		lblFrames.setText(frames);
	}
}
