package editeur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import physique.ModelePhysique;
/**
 * Le panel de l'editeur de tableaux
 * @author Antoine
 *
 */
public class PanelEditeurTableaux extends JPanel {

	private static final long serialVersionUID = 1L;
	private ModelePhysique modele;
	private final int COTE = 50;
	private ArrayList<Carre> listeCarres;
	private boolean pinceau;
	private Path2D.Double axes;
	/**
	 * Creation du panel
	 */
	public PanelEditeurTableaux() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				listeCarres.forEach(carre -> {
					if(carre.getCarre().contains(arg0.getX()/modele.getPixelsParUniteX(), arg0.getY()/modele.getPixelsParUniteY())){
						carre.setSelected(pinceau);
						System.out.println("clique!");
						listeCarres.forEach(carree -> { //symetrie
							if(carree.getCarre().contains(50-arg0.getX()/modele.getPixelsParUniteX(), arg0.getY()/modele.getPixelsParUniteY())) 
								carree.setSelected(pinceau);
						});
					};
				}
						);
				repaint();
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				listeCarres.forEach(carre -> {
					if(carre.getCarre().contains(arg0.getX()/modele.getPixelsParUniteX(), arg0.getY()/modele.getPixelsParUniteY())){
						carre.setSelected(pinceau);
						System.out.println("clique!");
					listeCarres.forEach(carree -> { //symetrie
						if(carree.getCarre().contains(50-arg0.getX()/modele.getPixelsParUniteX(), arg0.getY()/modele.getPixelsParUniteY())) 
							carree.setSelected(pinceau);
					});
				}
				repaint();
			});
			}
		});
		setPreferredSize(new Dimension(500,500));

		listeCarres = new ArrayList<>();

		for(int j = 0; j<COTE; j++){
			for(int i = 0; i<COTE; i++){
				listeCarres.add(new Carre(i,j));
			}
		}

	}
	/**
	 * Dessine les carres dans l'editeur
	 * @param g Le contexte graphique
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		modele = new ModelePhysique(getWidth(), getHeight(),COTE);
		AffineTransform mat = modele.getMatMC();
		g2d.setColor(Color.blue);
		listeCarres.forEach(carre -> carre.dessiner(g2d,mat));


		axes = new Path2D.Double();
		for(double cpt = 0;cpt<COTE;cpt++){			
			axes.moveTo(cpt*modele.getPixelsParUniteX()+getWidth()/2, getHeight());
			axes.lineTo(cpt*modele.getPixelsParUniteX()+getWidth()/2, 0);

			axes.moveTo(-cpt*modele.getPixelsParUniteX()+getWidth()/2, getHeight());
			axes.lineTo(-cpt*modele.getPixelsParUniteX()+getWidth()/2, 0);
		}

		for(double cpt2 = 0;cpt2<COTE;cpt2++){
			axes.moveTo(0,cpt2*modele.getPixelsParUniteY()+getHeight()/2);
			axes.lineTo(getWidth(),cpt2*modele.getPixelsParUniteY()+getHeight()/2);

			axes.moveTo(0,-cpt2*modele.getPixelsParUniteY()+getHeight()/2);
			axes.lineTo(getWidth(),-cpt2*modele.getPixelsParUniteY()+getHeight()/2);
		}

		g2d.setColor(Color.black);
		g2d.draw(axes);

	}
	/**
	 * Envoye la liste de carres
	 * @return listeCarres La liste de carres
	 */
	public ArrayList<Carre> getListeCarres(){
		return listeCarres;
	}
	/**
	 * Definit le pinceau 
	 * @param bool Le pinceau
	 */
	public void setPinceau(boolean pinceau){
		this.pinceau = pinceau;
	}
}
