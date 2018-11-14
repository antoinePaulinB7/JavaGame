package tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import entites.EntitePhysique;
import physique.Force;

public class PanelTestEntitePhys extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntitePhysique ent1;
	private boolean animationEnCours = false;
	public boolean gauche = false, droite = false, haut=false,  bas = false;
	private Force fGauche, fDroite, fHaut, fBas;
	/**
	 * Create the panel.
	 */
	public PanelTestEntitePhys() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case 37 : //horizontal+=1;
					
					gauche =true;
					break;
				case 38 : //vertical-=1;
					
					haut =true;
					break;
				case 39 : //horizontal-=1;
					//player1.bougerDroite();
					
					droite =true;
					break;
				case 40 : //vertical+=1;
					//player1.bougerBas();
					
					bas =true;

					break;
				}

			}
			public void keyReleased(KeyEvent e){
				switch(e.getKeyCode()){
				case 37 : //horizontal+=1;
					//player1.bougerGauche();
					gauche = false;

					break;
				case 38 : //vertical-=1;
					//player1.bougerHaut();
					//player1.saut();

					break;
				case 39 : //horizontal-=1;
					//player1.bougerDroite();
					droite = false;
					break;
				case 40 : //vertical+=1;
					//player1.bougerBas();
					bas = false;
					break;
				}

			}


		});
		setLayout(null);
		setBackground(Color.white);
		ent1 = new EntitePhysique(50.0, 50.0);
		Force gravite = new Force("gravite", 0.0,-5);
		ent1.ajouterForce(gravite);
		//Force vent = new Force("vent",2.5,0);
		//ent1.ajouterForce(vent);
		
		fGauche = new Force("gauche",-5,0);
		fDroite = new Force("droite", 5,0);
		fHaut = new Force("haut",0,10);
		fBas = new Force("bas",0,-10);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(getWidth()/2.0, getHeight()/2.0);
		g2d.scale(2, -2);
		
		g2d.setColor(Color.black);
		g2d.fill(new Rectangle2D.Double(ent1.getPositionX(), ent1.getPositionY(), 30, 30));
		g2d.translate(ent1.getPositionX(), ent1.getPositionY());
		g2d.setColor(Color.red);
		ent1.getVitesse().dessiner(g2d, new AffineTransform());
		g2d.setColor(Color.blue);
		ent1.getAcceleration().dessiner(g2d, new AffineTransform());
	}
	@Override
	public void run() {
		while(animationEnCours){
			
			if(gauche)ent1.ajouterForce(fGauche);
			else ent1.enleverForce(fGauche);
			if(droite)ent1.ajouterForce(fDroite);
			else ent1.enleverForce(fDroite);
			if(haut)ent1.ajouterForce(fHaut);
			else ent1.enleverForce(fHaut);
			if(bas)ent1.ajouterForce(fBas);
			else ent1.enleverForce(fBas);
			
			System.out.println(ent1.getPosition()+" "+ent1.getVitesse());
			ent1.update();
			
			repaint();
			try {
				Thread.sleep((long)(50));				
			}
			catch (InterruptedException e) {
				System.out.println("Erreur pendant le sleep de l'animation");
			}
		}
		
	}
	
	public void demarrer(){
		if (!animationEnCours ) { 
			System.out.println("Animation démarrée");
			Thread processusAnim = new Thread(this);
			processusAnim.start();
			animationEnCours = true;
		}
	}
	
	
}
