package entites;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaces.Constantes;
import item.Item;
import physique.Force;
import physique.Vitesse;

public class EntiteItem extends EntitePhysique{
	
	private static final long serialVersionUID = 1L;
	private String nomFichier = null;
	private URL url = null;
	private ImageIcon image = null;
	private Item item;
	private boolean auSol = false;

	public EntiteItem(double positionX, double positionY, String id) {
		super(positionX, positionY);
		ajouterForce(new Force("Gravite", 0, -getMasse()*Constantes.K_GRAVITE));
	
		double pouseeX = ThreadLocalRandom.current().nextDouble(0.01, 0.15);
		double pouseeY = ThreadLocalRandom.current().nextDouble(0.1, 0.3);
		
		if (ThreadLocalRandom.current().nextBoolean())
			pouseeX = -pouseeX;
		
		setVitesse(new Vitesse(pouseeX, pouseeY));
		
		int nbId = Integer.parseInt(id);
		
		if (nbId == 28) {
			setLargeur(1.5);
			setHauteur(2);
		} else if (nbId > 17 && nbId < 28){
			setHauteur(0.5);
		}
		
		
		item = new Item(id);
		nomFichier = "item_"+id+".png";
		url = getClass().getClassLoader().getResource(nomFichier);

		try {
			image = new ImageIcon(ImageIO.read(url));
		} 
		catch (IOException e) {
			System.out.println("IOException lors de la lecture avec ImageIO");
		}
	}
	public void dessiner(Graphics2D g2d, AffineTransform mat) {		
		AffineTransform matLocale = new AffineTransform(mat);
		super.dessiner(g2d, matLocale);
		matLocale.translate(getPositionX(), getPositionY() + getHauteur());
		matLocale.scale(0.03, -0.03);		
		g2d.drawImage(image.getImage(), matLocale, null);
	}
	public Item getItem() {
		return item;
	}
	@Override
	public boolean isAuSol() {
		return auSol;
	}
	@Override
	public void setAuSol(boolean auSol) {
		this.auSol = auSol;
	}
	
}
