package temporaire;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;
import physique.Position;

public class PlateformeTempObsolete implements Dessinable{
	double x, y;
	double largeur, hauteur;
	Rectangle2D.Double bloc;
	Color couleur;
	
	PlateformeTempObsolete(double x, double y, double largeur, double hauteur, Color couleur){
		bloc = new Rectangle2D.Double(x/*-largeur/2*/, y/*-hauteur/2*/, largeur, hauteur);
		this.couleur = couleur;
	}

	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		Color couleurDepart = g2d.getColor();
		g2d.setColor(couleur);
		g2d.fill(mat.createTransformedShape(bloc));
		g2d.setColor(couleurDepart);
	}
	
	public Rectangle2D.Double getBloc () {
		return bloc;
	}
	public double getLargeur(){
		return largeur;
	}
	
	public Area getArea(AffineTransform mat){
		
		return new Area(mat.createTransformedShape(bloc));
	}
	public Area getArea(){
		return new Area(bloc);
	}
	public Position getPosition(){
		return new Position(x,y);
	}
}
