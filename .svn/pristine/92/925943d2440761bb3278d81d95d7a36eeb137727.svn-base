package monde;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bindings.BindingsClavier;
import bindings.BindingsManette;
import entites.Joueur;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import net.miginfocom.swing.MigLayout;
import procedural.Seed;
import save.LireSauvegarde;
import save.Sauvegarde;
import save.SaveJoueur;

/**
 * L'affichage quand le joueur est mort
 * @author Mihai
 *
 */
public class MenuMort extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblScore;
	private JButton btnDernierSave;
	private JButton btnRetourMenu;
	private JButton btnQuitter;
	private Monde monde;
	
	/**
	 * Constructeur de l'affichage
	 */
	public MenuMort(Monde monde) {
		this.monde = monde;
		setOpaque(false);
		setLayout(new MigLayout("", "[97.00,grow][228.00][117.00,grow]", "[26.00,grow][61.00][25][70][25][70][25][70][19.00,grow]"));
		
		lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblScore, "cell 1 1,alignx center");
		
		btnDernierSave = new JButton("<html>Ouvrir la sauvegarde<br> la plus r\u00E9cente</html>");
		btnDernierSave.setBackground(new Color(255, 255, 255));
		btnDernierSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDernierSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ouvrirSauvegarde();
			}
		});
		add(btnDernierSave, "cell 1 3,alignx center,growy");
		
		btnRetourMenu = new JButton("Retour au menu");
		btnRetourMenu.setBackground(new Color(255, 255, 255));
		btnRetourMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRetourMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monde.fermerMonde();
			}
		});
		add(btnRetourMenu, "cell 1 5,grow");
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setBackground(new Color(255, 255, 255));
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnQuitter, "cell 1 7,grow");
	}
	private void ouvrirSauvegarde() {
		
		String dernierSave = lastFileModified("sauvegarde").getName().substring(0, lastFileModified("sauvegarde").getName().length() - 4);
		Sauvegarde save = LireSauvegarde.loaderSauvegarde(dernierSave);
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
		Seed seed = new Seed(save.getSeed());
		long score = save.getScore();
		Controle[] controles = save.getControles();					
		BindingsManette[] bindings = save.getBindings();
		BindingsClavier keyJoueurs = save.getKeyJoueurs();
		boolean[] biomesDecouverts = save.getBiomesDejaDecouverts();
		
		monde.getFrame().remove(monde);
		Monde nouveauMonde = new Monde(joueurs, difficulte, seed, controles, bindings, keyJoueurs, monde.getMenuPrincipal(), monde.getFrame(), score, biomesDecouverts);
		monde.getFrame().getContentPane().add(nouveauMonde);
		nouveauMonde.requestFocus(true);
		setVisible(false);
		nouveauMonde.setNightAlpha(0);
	}
	private File lastFileModified(String dir) {
	    File fl = new File(dir);
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    return choice;
	}
	public void setScore(String score) {
		lblScore.setText("Score : " + score);
	}
}
