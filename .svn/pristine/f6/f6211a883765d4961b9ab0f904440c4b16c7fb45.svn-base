package temporaire;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;
import monde.Monde;
import physique.Energie;

public class JoueurTempObsolete implements Dessinable{
	double x, y;
	double largeur, hauteur;
	double vitesseX, vitesseY;
	Rectangle2D.Double bloc, pieds;
	double FPS = 60;
	Monde monde;
	boolean sautDisponible = false;
	boolean auSol = false;
	double energie = 0;

	public JoueurTempObsolete(Monde monde){
		x = 0;
		y = 0.5;
		largeur = 1.5;
		hauteur = 2.5;
		vitesseX = 0;
		vitesseY = 0;

		bloc = new Rectangle2D.Double(x-largeur/2,y,largeur, hauteur);
		pieds = new Rectangle2D.Double(x-largeur/2,y,largeur, hauteur/4);

		this.monde = monde;
	}

	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		Color avant = g2d.getColor();
		g2d.setColor(Color.blue);

		bloc = new Rectangle2D.Double(x-largeur/2,y,largeur, hauteur);
		pieds = new Rectangle2D.Double(x-largeur/2,y,largeur, hauteur/4);

		g2d.fill(mat.createTransformedShape(bloc));
		g2d.setColor(Color.darkGray);
		g2d.fill(mat.createTransformedShape(pieds));
		g2d.setColor(avant);
	}

	public void update(){
		double vitesseYprec= vitesseY;

		if(vitesseX>0){
			vitesseX-=4*0.25/FPS;
		}
		if(vitesseX<0){
			vitesseX+=4*0.25/FPS;
		}
		if(vitesseX<=0.0125
				&& vitesseX>=-0.0125){
			vitesseX=0;
		}
		if(!auSol){
			vitesseY-=0.75/FPS;
			isOnGround();
		}else if(auSol){
			vitesseY=0;
		}
		
		x+=vitesseX;
		y+=vitesseY;
		
		this.energie = Energie.energieCinetique(1.00, vitesseY);
		
		
		if(vitesseY-vitesseYprec>0.750){
			System.out.println("RIP");
		}
		
		isOnGround();
	}

	public boolean isOnGround(){
		boolean trouve = false;
		auSol = false;
		
		/*int index = 0;
		while(!trouve&&index<monde.listePlateformesVisibles.size()){
			PlateformeTemp temp = monde.listePlateformesVisibles.get(index);

			if(this.pieds.intersects(temp.bloc)) trouve = true;
			if(this.y==temp.y) trouve = true;
			if(!trouve)index++;
		}*/
		if(trouve){
			//monter(monde.listePlateformesVisibles.get(index).bloc.getMaxY());
			trouve = false;
			if(vitesseY<0){
				//y = monde.listePlateformesVisibles.get(index).bloc.y+1;
				vitesseY = 0;
				sautDisponible = true;
				trouve = true;
				auSol = true;
				
			}
		}
		return trouve;
	}

	public void monter(double hauteur){
		if(y-this.hauteur/2<hauteur){
			vitesseY=0.125/FPS;
		}else{
			vitesseY=0;
		}
	}

	public void saut(){
		
		if(sautDisponible){
			vitesseY=0.25;
			sautDisponible = false;
			auSol = false;
		}
		isOnGround();
	}

	public void descendre(){
		if(!isOnGround()){
			if(vitesseY>0)vitesseY=0;
			vitesseY-=0.5/FPS;
		}else{
			y-=hauteur/4+1;
			vitesseY=0;
		}
	}

	public void bougerDroite(){
		//x+=0.25;
		
		vitesseX=0.25;
		isOnGround();
	}

	public void bougerGauche(){
		//x-=0.25;
		
		vitesseX=-0.25;
		isOnGround();
	}

	public void bougerHaut(){
		y+=0.25;
	}

	public void bougerBas(){
		y-=0.25;
	}
}
