package tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import physique.ModelePhysique;
import procedural.Tableau;

public class PanelTestTableau extends JPanel {

	private static final long serialVersionUID = 1L;
	private Tableau tableau;
	private ModelePhysique modele;
	/**
	 * Create the panel.
	 */
	public PanelTestTableau(Tableau tab) {
		this.tableau = tab;
		setPreferredSize(new Dimension(500,550));
		setBackground(Color.blue);

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		modele = new ModelePhysique(getWidth(), getHeight(),50);
		AffineTransform mat = modele.getMatMC();
		
		//g2d.translate(getWidth()/2, getHeight()/2);
		tableau.getListePlateformes().forEach(plat -> {
			System.out.println(plat);
			plat.dessiner(g2d, mat);
		});
	}


}
