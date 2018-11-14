package temporaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import entites.Joueur;
import environnement.Plateforme;
import interfaces.Constantes;
import interfaces.Enumerations.Controle;
import interfaces.Enumerations.Difficulte;
import personnages.Personnage;
import physique.Force;
import physique.ModelePhysique;
import physique.Vitesse;
import procedural.Seed;

public class MondeObsolete extends JPanel implements Runnable{
	
	private static final long serialVersionUID = -2975268265283354556L;
	//private ArrayList<Item> listeItems;
	private ArrayList<Plateforme> listePlateformes = new ArrayList<>();
	private ArrayList<Plateforme> listePlateformesVisibles = new ArrayList<>();
	private Joueur[] joueurs;
	//private ArrayList<Monstre> listeMonstres;//Structures de données à voir
	private ModelePhysique modeles[] = new ModelePhysique[2];
	private boolean duo;
	private double uniteParEcran = 50;
	private CameraObsolete camPrincipale, camSecondaire;
	private boolean animationEnCours = false;
	private Color backGroundColor = new Color(100, 150, 60);
//	private int distance = 1;
//	private Difficulte difficulte;
	
	//temp
	//private double largeur = 50, hauteur = largeur*9/16, horizontal =0, vertical=0;
	//private double largeurPlateforme = 4, hauteurPlateforme = 1;

	public MondeObsolete(int nombreDeJoueurs, Difficulte difficulte, Seed seed, Controle[] controleParJoueur, Personnage[] personnages){
		this.joueurs = new Joueur[nombreDeJoueurs];		
		duo = (joueurs.length == 2) ? true : false;
		
		if(duo) {
			camSecondaire = new CameraObsolete(this);
			camPrincipale = new CameraObsolete(this, camSecondaire, duo);
		} else {
			camPrincipale = new CameraObsolete(this);
		}
		
		listenersControles();
		setBackground(backGroundColor);
		setFocusable(true);	
		
		//listePlateformes = tab.getListePlateformes();
		
		/*//gere la creation des joueurs
		for (int i = 0; i < joueurs.length; i++) {
			joueurs[i] = new Joueur( i, 5, personnages[i]);			
			joueurs[i].ajouterForce(new Force("Gravite", 0, -Constantes.K_GRAVITE*joueurs[i].getMasse()));
		}*/
		
		//temp 
		//joueurs[0] = new Joueur( 0, 0, 0.25, 0.25, 69, 1, 1); //a enlever SI classe Personnage
		joueurs[0].ajouterForce(new Force("Gravite", 0, -Constantes.K_GRAVITE*joueurs[0].getMasse()));
		
		//joueurs[1] = new Joueur( 5, 5, 0.25, 0.25, 60, 1, 1); //a enlever SI classe Personnage
		joueurs[1].ajouterForce(new Force("Gravie", 200, -Constantes.K_GRAVITE*joueurs[1].getMasse()));
		
		demarrer();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform mat[] = new AffineTransform[joueurs.length];
		
		

		double ecranLargeur = getWidth();
		double ecranHauteur = getHeight();

		modeles[0] = new ModelePhysique(ecranLargeur/2.0, ecranHauteur/2.0,uniteParEcran/2.0);
		
		
		mat[0] = modeles[0].getMatMC();		
		mat[0].translate(-modeles[0].getLargUnitesReelles(), modeles[0].getHautUnitesReelles());
		mat[0].scale(1, -1);
		
	
		
		//camera fonctionne si 1 joueur ou 2 joueurs proches de chacun
		if (duo) {
			//mat[1].translate(ecranLargeur/2, 0);
			mat[1] = modeles[0].getMatMC();
			mat[1].scale(1, -1);
			System.out.println(camPrincipale);			
			mat[1].translate(getWidth()/2/modeles[0].getPixelsParUniteX() - camSecondaire.getCamX(), -(getHeight()/2)/modeles[0].getPixelsParUniteY() - camSecondaire.getCamY());
		} 
		mat[0].translate(getWidth()/2/modeles[0].getPixelsParUniteX() - camPrincipale.getCamX(), -(getHeight()/2)/modeles[0].getPixelsParUniteY() - camPrincipale.getCamY());
		


		for(int i = 0; i<listePlateformes.size(); i++){
			listePlateformes.get(i).dessiner(g2d, mat[0]);
		}
		
		for ( int i = 0; i < joueurs.length; i++) {

			AffineTransform matLocale = new AffineTransform(mat[i]);
			joueurs[i].dessiner(g2d, matLocale);
			matLocale.translate(joueurs[i].getPositionX(), joueurs[i].getPositionY());
			
			if(joueurs[i].isEnChute()) {
				g2d.setColor(Color.white);
				joueurs[i].getVitesse().dessiner(g2d, matLocale);
				g2d.setColor(Color.red);
				joueurs[i].getAcceleration().dessiner(g2d, matLocale);
			}		
			matLocale.translate(joueurs[i].getPositionX(), joueurs[i].getPositionY());
		}
		
		
		
		//Affichage temporaire de l'affichage scientifique
		//for (Joueur joueur : joueurs) {
//			g2d.setColor(Color.white);
//			g2d.drawString(listePlateformesVisibles.size()+"", 15, 15);
//			g2d.drawString("position x : "   + joueurs[0].getPositionX(), 15, 30);
//			g2d.drawString("position y : "   + joueurs[0].getPositionY(), 15, 45);
//			g2d.drawString("vitesse x : "    + joueurs[0].getVitesse().getComposanteVitesseX(), 15, 60);
//			g2d.drawString("vitesse y : "    + joueurs[0].getVitesse().getComposanteVitesseY(), 15, 75);		
//			g2d.drawString("accel x: "       + joueurs[0].getAcceleration().getComposanteAccelX(), 15, 90);
//			g2d.drawString("accel y: "       + joueurs[0].getAcceleration().getComposanteAccelY(), 15, 105);
//			g2d.drawString("Au sol : "       + joueurs[0].isAuSol(), 15, 120);
//			g2d.drawString("En Chute : "     + joueurs[0].isEnChute(), 15, 135);
//			g2d.drawString("En mouvement : " + joueurs[0].isEnMouvement(), 15, 150);
//		//}
		
		/*System.out.println("caméra principale "+camPrincipale);
		System.out.println("caméra secondaire "+camSecondaire);
		System.out.println(joueurs[0]);
		System.out.println(joueurs[1]);*/
	}	

	@Override
	public void run() {
		while(animationEnCours){
			for(Joueur j : joueurs){
				
				//ajouter controles + les variables qu'il faut modifier avec les controles
				
				if( j.getVitesse().getComposanteVitesseX() > -1E-2 && j.getVitesse().getComposanteVitesseX() < 1E-2) {
					j.setEnMouvement(false);
					j.getVitesse().setComposanteVitesseX(0);
				}
				if(j.getVitesse().getComposanteVitesseY() < 0)
					j.setEnChute(true);
				if (j.isEnMouvement() && !j.surQuelqueChose()) {
					j.setAuSol(false);
					j.enleverForce(new Force("Normale", 0, Constantes.K_GRAVITE*j.getMasse()));
				}
				if (j.isEnChute() || (j.isEnMouvement() && !j.surQuelqueChose())) {	
					trouverCollisionAvecPlateforme(j);
					//trouverCollisionAvecEntite(j); //fonctionne avec l'ajout des monstres
				}
				else if(j.isEnMouvement()) {
					trouverCollisionAvecPlateforme(j);
					//trouverCollisionAvecEntite(j); //fonctionne avec l'ajout des monstres
				}				
				j.update();
			}
						
			//listeMonstres.forEach(monstre -> monstre.update());//à faire
				
			camPrincipale.update();
			repaint();

			try {
				Thread.sleep((long)(1000/Constantes.FPS));				
			}
			catch (InterruptedException e) {
				System.out.println("Erreur pendant le sleep de l'animation");
			}
		}

	}
	
	/*private double calculerDifficulte() {
		switch(difficulte) {
			case FACILE:    return (Math.sin(distance) + 1.0544*distance) * 0.75;
			case MOYEN:     return (Math.sin(distance) + 1.0544*distance) * 0.50;
			case DIFFICILE: return (Math.sin(distance) + 1.0544*distance) * 1.00;
			default:        return 0;
		}
	}*/
	

	
	//temp
	/*private void choisirPlateformesVisibles() {
		listePlateformesVisibles.clear();

		for(int i=0;i<listePlateformes.size();i++){
			Plateforme temp = listePlateformes.get(i);
			Area ecran = new Area(new Rectangle2D.Double(-largeur/2-horizontal-largeurPlateforme,-hauteur/2-vertical-hauteurPlateforme,largeur+largeurPlateforme*2,hauteur+hauteurPlateforme*2));
			if(ecran.contains(temp.getZoneCollision())){
				listePlateformesVisibles.add(listePlateformes.get(i));
			}
		}

	}*/
	
/*	private void trouverCollisionAvecEntite(Joueur j) {
		
		for ( int i = 0; i < joueurs.length; i++) {			
			if (!j.equals(joueurs[i])) {
				if(j.contact(joueurs[i])) {
					System.out.println("touchee joueur");
				}
			}
		}
		
		for( int i = 0; i < listeMonstres.size(); i++) { //add 
			if(j.contact(listeMonstres.get(i))) {
				System.out.println("touchee monstre");
				//listeMonstres.get(i).updateCombat(j.updateCombat(listeMonstres.get(i).getDegatsFaits()));
				if (listeMonstres.get(i).isDead()) {
					//j.addExperience(listeMonstres.get(i).getExpDonnee());
					listeMonstres.remove(i);
				}
				//if (j.isDead()) 
					//game over
				//reste du traitement des collisions
			}
		}
	}*/
	
	private void trouverCollisionAvecPlateforme(Joueur j) {
		for ( int i = 0; i < listePlateformesVisibles.size(); i++) //changer en advanced for loop?
			if (j.contactPieds(listePlateformesVisibles.get(i), 1)) {	//changer en advanced for loop?	
				j.ajouterForce(new Force("Normale",0, Constantes.K_GRAVITE*j.getMasse()));
				j.setVitesse(new Vitesse(j.getVitesse().getComposanteVitesseX(),0));			
				j.setPositionY(listePlateformesVisibles.get(i).getZoneCollision().getY()+1);
				//j.setPlateformeSousLesPieds(listePlateformesVisibles.get(i));
				j.setEnChute(false);
				j.setAuSol(true);				
				System.out.println("trouvee plateforme! " + j.getMasse() );
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
	
	private void listenersControles() {
		//a faire
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}
}
