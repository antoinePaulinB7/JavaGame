package tests;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class RectangleCustom {
	private Rectangle2D.Double rect;
	private String biome;
	
	public RectangleCustom(double x, double y, double largeur, double longueur, String biome){
		rect = new Rectangle2D.Double(x,y,largeur,longueur);
		this.biome = biome;
	}
	
	public void dessiner(Graphics2D g2d, AffineTransform mat){
		Color avant = g2d.getColor();
		g2d.fill(mat.createTransformedShape(rect));
		g2d.setColor(Color.white);
		g2d.drawString(biome, (int)(rect.getCenterX()*mat.getScaleX()), (int)(rect.getCenterY()*mat.getScaleY()));
		g2d.setColor(avant);
	}
}
