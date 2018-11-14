package entites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.event.EventListenerList;

import ecouteurs.JoueurListener;
import fusee.Fusee;
import interfaces.Constantes;
import interfaces.Dessinable;
import interfaces.Enumerations.Direction;
import interfaces.Enumerations.Etat;
import item.Inventaire;
import item.Item;
import personnages.Balle;
import personnages.Personnage;
import physique.Energie;
import save.SaveJoueur;

/**
 * Classe qui gere la creation et le combat d'un joueur 
 * @author Mihai
 *
 */
public class Joueur extends EntitePhysique implements Dessinable, Constantes{

	private static final long serialVersionUID = 1L;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private Personnage personnage;
	private int att, def, spd, hp, niveau = 1, pointsAttribuables = 0, pointsParNiveau = 2;
	private int[] stats = new int[4];
	private double experience = 0;
	private double saut = 0.25;
	private double damage;
	private Sprite sprite;
	private Etat etat;
	private Direction direction;
	private List<Item> listeItems;
	private double[] positionMains;
	private int delay = 10;
	private long gold = 0;
	private int nbBalles;
	private Area zoneCollision = new Area();
	private Monstre monstre = null;
	private Inventaire inventaire = new Inventaire();
	private int dotDuration = 3000;
	private int dotIteration = 500;

	//cooldown timers en millisecondes
	private int cdDash = 5000;
	private int cdBlock = 5000;
	private int cdRoll = 1500;	
	private int cdSpecial[] = {10000, 7000, 150, 10000, 100}; //viking : thief : leprechaun : goblin : cowboy 

	//cooldown timers en frames
	private int cdAttack = 14;

	//duration en frames par animation
	private int attackDuration = 8;
	private int dashDuration = 2;
	private int blockDuration = 5;
	private int rollDuration = 8;
	private int specialDuration[] = {20, 3, 15 , 10, 15}; //viking : thief : leprechaun : goblin : cowboy 
	private int rechargeDuration = 12; //recharge pistolet cowboy


	//vitesse des mouvements
	private double vitesseDash = 8;
	private double vitesseBlock = 0.3;

	//la possibilite de faire des actions
	private boolean peutDash = true;
	private boolean peutBlock = true;
	private boolean peutRoll = true;
	private boolean peutAttack = true;
	private boolean peutSpecial = true;
	private boolean peutActiverBerserk = true;

	private Area zoneSpecial = new Area();
	private boolean monstreFrappe = false, revivable = true;
	private double revivePercent = 0.3;
	private double specialDamageMultiplier = 0;
	private int stunDuration = 20;
	private List<Balle> balles = new ArrayList<Balle>();

	/**
	 * Constructeur d'un joueur de base
	 * @param positionX La composante x de la position
	 * @param positionY La composante y de la position
	 */
	public Joueur(double positionX, double positionY) {
		super(positionX, positionY);
	}	
	/**
	 * Constructeur d'un joueur en lui passant un personnage
	 * @param positionX La composante x de la position
	 * @param positionY La composante y de la position
	 * @param personnage Un personnage qui a des caracteristiques personalisees
	 */
	public Joueur(double positionX, double positionY, Personnage personnage) {
		super(positionX, positionY, personnage.getMasse(), personnage.getLargeur(), personnage.getHauteur(), personnage.getVieRegenereParSeconde());
		this.personnage = personnage;
		this.stats = personnage.getStats();
		sprite = personnage.getSprite();
		//reset l'etat du save (fix pour les saves)
		sprite.setEnCoursDAnimation(false);
		sprite.demarrer();

		//sprite.demarrer();
		if (sprite.getNom().equals("cowboy")) {
			nbBalles = 6;
		}
		listeItems = new ArrayList<>();
		inventaire.setGold(gold);

		calculerStats();	
		
		Item epeeDepart     = Constantes.armes[ThreadLocalRandom.current().nextInt(0,5)];		
		Item bouclierDepart = Constantes.boucliers[ThreadLocalRandom.current().nextInt(0,2)];

		listeItems.add(epeeDepart);
		listeItems.add(bouclierDepart);
	}		

	/**
	 * Dessine le joueur avec son sprite
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat){
		Color avant = g2d.getColor();
		g2d.setColor(Color.blue);

		//update l'etat et la direction
		etat = getEtat();
		direction = getDirection();
		
		if (getFusee() == null && listeItems.contains(new Item("28"))) 
			listeItems.remove(new Item("28"));
		if (getFusee() != null && isFuseeVisible()) getFusee().dessiner(g2d, mat); //DESSIN FUSEE

		if(sprite.getEtat() != etat) sprite.setEtat(etat);
		sprite.demarrer();

		Sprite temp = sprite;

		if (zoneSpecial != null && getAfficherHitbox()) g2d.draw(mat.createTransformedShape(zoneSpecial));

		super.dessiner(g2d, mat);

		for (int i = 0; i < balles.size(); i++) {
			Balle balle = balles.get(i);
			balle.dessiner(g2d, mat);							
		}

		AffineTransform matSprite = new AffineTransform(mat);
		positionMains = sprite.getPositionsMains();

		dessinerBarreDeVie(g2d);

		if(direction == Direction.DROITE){
			matSprite.translate(getPositionX(), getPositionY()+this.getHauteur());
			matSprite.scale(personnage.getLargeur()/temp.getLargeur(), -personnage.getHauteur()/temp.getHauteur());			
		}
		if(direction == Direction.GAUCHE){
			matSprite.translate(getPositionX() + this.getLargeur(), getPositionY() + this.getHauteur());
			matSprite.scale(-personnage.getLargeur()/temp.getLargeur(), -personnage.getHauteur()/temp.getHauteur());
		}
		//debut dessin fade dash
		if (isDashing()) {
			AffineTransform matFade = new AffineTransform(matSprite);
			temp.setDashing(true);
			for (int i = 0; i < 8; i++) {
				matFade.translate(-6, 0);
				temp.setParametreFade((float)(0.8 - 0.1*i));
				temp.dessiner(g2d, matFade);				
			}
			temp.setDashing(false);
		}
		//fin dessin fade dash

		sprite.dessiner(g2d, matSprite);

		AffineTransform matMainPrincipale = new AffineTransform(matSprite);
		AffineTransform matMainSecondaire = new AffineTransform(matSprite);

		if(!listeItems.isEmpty()) {
			matMainPrincipale.translate(positionMains[0] - listeItems.get(0).getLargeur()/4, -positionMains[1]+1);
			matMainPrincipale.rotate(Math.toRadians(positionMains[4]), 4, 14);
			matMainPrincipale.scale(0.5, 0.5);

			matMainSecondaire.translate(positionMains[2] - listeItems.get(1).getLargeur()/4, positionMains[3]);
			matMainSecondaire.scale(0.5, 0.5);
		}
		if(isAttacking()) {
			if(!listeItems.isEmpty())listeItems.get(0).dessiner(g2d, matMainPrincipale);
			Rectangle2D.Double rectCollision = new Rectangle2D.Double(getPositionX() - 0.2, getPositionY() + 0.2, 0.7, 1.5); //cree un rectangle a peu pres a la vraie position du item ds le monde et a peu pres de sa taille
			AffineTransform matEpeeHitbox = new AffineTransform(); //on cree un matrice de transformation vide
			if (direction == Direction.GAUCHE) matEpeeHitbox.rotate(Math.toRadians(positionMains[4]), getPositionX() - 0.2 , getPositionY() +0.2); //on choisi la direction de rotation selon la direction du joueur
			if (direction == Direction.DROITE) matEpeeHitbox.rotate(Math.toRadians(-positionMains[4]), getPositionX() - 0.2 , getPositionY() +0.2);
			zoneCollision = new Area(matEpeeHitbox.createTransformedShape(rectCollision)); //on applique les transformations faites sur le rectangle et on le met dans un area qui va servir pour detecter les collisions
			g2d.setColor(Color.green);


			if (getAfficherHitbox()) g2d.draw(mat.createTransformedShape(zoneCollision)); //dessine la zone avec la matrice de base du joueur sans aucune modification (tous les modifications sont faites sur le area lui-meme)
			//System.out.println(zoneCollision.getBounds2D());
		}
		if(sprite.getEtat().equals(Etat.BLOCK)) {
			if(!listeItems.isEmpty())listeItems.get(1).dessiner(g2d, matMainSecondaire);
		}		

		g2d.setColor(avant);

	}
	/**
	 * Dessine la barre de vie du joueur
	 * @param g2d Le contexte graphique
	 */
	private void dessinerBarreDeVie(Graphics2D g2d) {
		double largeurBarre = 200;
		double hauteurBarre = 30;
		Rectangle2D.Double rectRouge = new Rectangle2D.Double(10, 5, largeurBarre, hauteurBarre);

		double longueurVieRestante = largeurBarre * (getPointsDeVie() / getPointsDeVieMaximum());
		Rectangle2D.Double rectVert  = new Rectangle2D.Double(10, 5, longueurVieRestante, hauteurBarre);

		//dessine la partie rouge qui represente la vie perdue du monstre
		g2d.setColor(Color.red);
		g2d.fill(rectRouge);

		//dessine la partie verte qui represente la vie restante du monstre
		g2d.setColor(Color.green);
		g2d.fill(rectVert);

		//dessine contour noir
		g2d.setColor(Color.black);
		g2d.draw(rectRouge);
	}
	/**
	 * Calcule et modifie les characterisques du joueur
	 */
	public void calculerStats() {		
		int[] tempStats = new int[4];
		//augmente les stats selon les bonus des items
		for (int i = 0; i < listeItems.size(); i++) {
			Item item  = listeItems.get(i);
			tempStats[0] += item.getBonus()[0];
			tempStats[2] += item.getBonus()[1];
			tempStats[1] += item.getBonus()[2];
			tempStats[3] += item.getBonus()[3];
		}

		//fonctions qui decrivent les stats du joueur		
		att  = (int) (1.5*(stats[0] + tempStats[0])+ 0.5*(stats[1] + tempStats[1]));
		def  = (int) (0.375*(stats[2] + tempStats[2]) + 1.375*(stats[3] + tempStats[3]) + 0.25*(stats[1] + tempStats[1]));
		hp   = (int) (stats[0] + stats[3] + 0.25*(stats[1] + tempStats[1]) + 10*niveau + tempStats[0] + tempStats[3]);
		spd  = (int) (1.5*(stats[2] + tempStats[2]) + 0.5*(stats[1] + tempStats[1]));		
		saut = 0.3 + 0.1*(stats[0] + tempStats[0] + stats[2] + tempStats[2])/40  ;

		setVitesseDeDeplacement(spd * 0.1/60 + 0.075);
		setHauteurDeSaut(saut);
		setPointsDeVie(hp);
		setPointsDeVieMaximum(hp);	
	}
	/**
	 * Calcule les degats fait par les joueurs et le degats recus du monstre
	 * @param degatsMonstre Les degats faits par le monstre
	 * @param enemeyHitDirection La direction d'ou l'enemi a frappe
	 */
	public void updateCombat(double degatsMonstre, Direction enemeyHitDirection) {	
		//debut algorithme qui gere le blocage apres que le joueur soit frappe par un enemy
		if (getDirection() == Direction.DROITE && enemeyHitDirection == Direction.GAUCHE ||
				getDirection() == Direction.GAUCHE && enemeyHitDirection == Direction.DROITE) {
			if (isBlocking()) {
				Thread blockAnimation = new Thread(new Runnable() {
					public void run() {
						try {
							setVulnerable(false); //le joueur ne prend pas de damage
							Thread.sleep((long) (blockDuration * FPS)); //attend que l'animation du block fini

							peutBlock = false; 
							setVulnerable(true); //le joueur est vulnerable

							Thread.sleep(cdBlock); //commence le cooldown du prochain block		
							peutBlock = true; //peut bloquer encore
						} catch (InterruptedException e) {
							e.printStackTrace();
						}					
					}
				}); blockAnimation.start();				
			}
			if (isRolling()) { //s'il roll et s'il est dans la direction oppose du degat
				setVulnerable(false); //le joueur ne prend pas de damage
			}
		} //fin algorithme

		if (isVulnerable()) { //enleve la vie si le joueur est vulnerable
			double degatsRecus = degatsMonstre - def;
			setPointsDeVie(getPointsDeVie() - degatsRecus);
		}
	}
	/**
	 * Revit le joueur
	 */
	public void revivre() {
		setPointsDeVie(revivePercent * getPointsDeVieMaximum());
	}
	/**
	 * Envoye le joueur au tableau de depart
	 */
	public void respawn() {
		setPositionX(25);
		setPositionY(0);
		setPointsDeVie(getPointsDeVieMaximum());

		//reset tous ses equipements
		listeItems.clear();
		gold = 0;

		pointsAttribuables = 0;
		int xpTemp = 0;
		for (int i = 2; i <= (niveau); i++) 
			xpTemp += (i - 1) * 1000;
		double xpEnleve = experience - xpTemp;
		experience -= xpEnleve;
	}
	/**
	 * Ajoute l'experience au joueur
	 * @param experience L'experience du joueur
	 */
	public void ajouterExperience(double experience) {
		this.experience += experience;
		//verifie si le joueur a augmente de niveau
		if (niveau < Constantes.NIVEAU_MAX) testLevelUp();		
	}	
	/**
	 * Verifie si le joueur a augmente de niveau
	 */
	private void testLevelUp() {
		int xpTemp = 0;
		for (int i = 2; i <= (niveau + 1); i++) 
			xpTemp += (i - 1) * 1000;	
		//regarde si l'experience a change pour savoir si le joueur a avance d'un niveau
		if (experience >= xpTemp) {
			niveau++;
			pointsAttribuables += pointsParNiveau;
			leverEvenLevelUp();
			testLevelUp();
		}
	}
	/**
	 * Enleve des points de vie a l'entite
	 * @param pointsDeVie Les points de vie
	 */
	public void enleverPointsDeVie(double pointsDeVie) {
		if (def < pointsDeVie) {
			super.enleverPointsDeVie(pointsDeVie - def);
			if (sprite.getNom().equals("viking"))
				if (peutActiverBerserk)
					activateBerserk();
		}
	}
	private void activateBerserk() {
		Thread berserk = new Thread(new Runnable() {
			public void run() {
				try {
					//ajoute les bonus des stats
					att *= 2;
					def *= 2;
					spd *= 2;
					saut *= 1.11;
					
					setVitesseDeDeplacement(spd * 0.1/60 + 0.075);
					setHauteurDeSaut(saut);
					
					peutActiverBerserk = false;
					Thread.sleep(5000); //garde les bonus pendant 5 secondes
					
					//enleve les bonus des stats
					att /= 2;
					def /= 2;
					spd /= 2;
					saut /= 1.11;
					
					setVitesseDeDeplacement(spd * 0.1/60 + 0.075);
					setHauteurDeSaut(saut);
										
					Thread.sleep(8000); //cooldown de 8 secondes avant pouvoir reactiver ce mode
					peutActiverBerserk = true;
				} catch (InterruptedException e) { }
			}
		}); berserk.start();
	}
	//debut systeme de combat
	/**
	 * Attaque normale
	 */
	public void normalAttack(boolean specialNinja) {				
		if (canAttack() || specialNinja) {
			Thread attack = new Thread(new Runnable() {
				public void run() {
					try {
						//Thread.sleep(delay);
						System.out.println("normal attack");

						setEtat(Etat.ATTAQUE_CHARGE); //commence l'animation de l'attaque normal
						setAttacking(true); //entrain d'attaquer
						peutAttack = false;

						Thread.sleep((long) (attackDuration * FPS));

						setAttacking(false); // apres que l'animation d'attaque fini						
						setEtat(Etat.IMMOBILE); //reset l'etat
						listeItems.get(0).setZoneCollision(new Area());						

						Thread.sleep((long) (cdAttack * FPS)); 
						peutAttack = true;
						monstreFrappe = false;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}			
				}
			}); attack.start();
		}

	}
	/**
	 * L'attaque propulse
	 */
	public void dashAttack() {
		if (canDash()) {
			Thread dash = new Thread(new Runnable() {
				public void run() {											
					try {
						setEtat(Etat.ATTAQUE_CHARGE);
						setAttacking(true); //entrain d'attaquer
						setDashing(true); //entrain de dash

						getVitesse().setComposanteVitesseX(getVitesse().getComposanteVitesseX() * vitesseDash);
						peutDash = false;

						Thread.sleep((long) (dashDuration * FPS)); //attend que la duration du dash fini					
						getVitesse().setComposanteVitesseX(0); 
						setDashing(false); //dash fini					

						Thread.sleep((long) (attackDuration * FPS));
						setAttacking(false);

						Thread.sleep(cdDash); //commence le cooldown du dash
						peutDash = true;
						monstreFrappe = false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}												
				}
			}); dash.start();
		}
	}
	/**
	 * Bloquer un coup
	 */
	public void block() { 
		if (canBlock()) {
			setBlocking(true); //commence a bloquer
			setEtat(Etat.BLOCK); // commence l'animation de blocage
			getVitesse().setComposanteVitesseX(getVitesse().getComposanteVitesseX() * vitesseBlock);
		}
	}
	/**
	 * L'action de rouler
	 */
	public void roll(Direction direction) {
		if (canRoll()) {
			Thread dash = new Thread(new Runnable() {
				public void run() {										
					try {
						peutRoll = false;	
						setBlocking(false);						
						setRolling(true); //commence le roll
						setEtat(Etat.ROLLING); // commence l'animation du roll

						double vitesseRoll = 2*getVitesseDeDeplacement();
						if(direction == Direction.GAUCHE)
							vitesseRoll = -vitesseRoll;
						getVitesse().setComposanteVitesseX(vitesseRoll);						

						Thread.sleep((long) (rollDuration * FPS)); //attend que la duration du roll fini

						setRolling(false); //roll fini					
						setVulnerable(true);

						Thread.sleep(cdRoll); //commence le cooldown du roll
						peutRoll = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}												
				}
			}); dash.start();
		}
	}
	/**
	 * Choisi l'attaque speciale selon la classe
	 */
	public void choisirSpecial() {
		if (canSpecial()) {		
			switch (personnage.getNomClasse()) {
			case "viking" :
				specialViking();
				specialDamageMultiplier = 1.5;
				break;
			case "thief" :
				specialThief();
				specialDamageMultiplier = 5;
				break;
			case "leprechaun" :
				specialLeprechaun();
				specialDamageMultiplier = 4;
				break;
			case "goblin" :
				specialGoblin();
				specialDamageMultiplier = 6;
				break;
			case "cowboy" :
				specialCowboy();
				specialDamageMultiplier = 3;
				break;		
			}
		}
	}
	/**
	 * L'attaque speciale du viking
	 */
	private void specialViking() {
		if (canSpecial() && isAuSol()) {
			Thread threadViking = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(delay);
						setEtat(Etat.SPECIAL);						
						setDoingSpecial(true);
						//definit le stun area
						zoneSpecial = (new Area(new Rectangle2D.Double(getPositionX() - 2*getLargeur(), getPositionY() - getHauteur() , 5*getLargeur(), 2*getHauteur())));
						setVulnerable(false);
						setStunned(true);
						peutSpecial = false;
						Thread.sleep((long) (specialDuration[0] * FPS));

						//reset tout apres que l'animation soit fini
						setStunned(false);
						zoneSpecial = (new Area());
						setVulnerable(true);
						setDoingSpecial(false); //quand le special fini

						Thread.sleep(cdSpecial[0]); //commence le cooldown du special
						peutSpecial = true;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
				}

			}); threadViking.start();
		}

	}
	/**
	 * L'attaque speciale du thief
	 */
	private void specialThief() {
		if (canSpecial() && !isHorsCombat() && nearMonstre()) {
			Thread threadThief = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(delay);
						setEtat(Etat.SPECIAL);

						//teleporte derriere le monstre
						if (getDirection() == Direction.DROITE) {							
							setPositionX( monstre.getPositionX() + monstre.getLargeur());
							setDirection(Direction.GAUCHE);							
						}
						else if (getDirection() == Direction.GAUCHE) {
							setPositionX( monstre.getPositionX() - getLargeur());
							setDirection(Direction.DROITE);		
						}
						setDoingSpecial(true);
						setStunned(true);						
						setVulnerable(false);					

						peutSpecial = false;
						Thread.sleep((long) (specialDuration[1] * FPS));						

						//reset tout apres que l'animation soit fini

						setStunned(false);
						setVulnerable(true);
						monstreFrappe = false;
						normalAttack(true);
						Thread.sleep((long) (attackDuration * FPS)); 

						setDoingSpecial(false); //quand le special fini

						Thread.sleep(cdSpecial[1]); //commence le cooldown du special
						peutSpecial = true;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					setDoingSpecial(false); //quand le special fini
				}

			}); threadThief.start();
		}
	}
	/**
	 * L'attaque speciale du leprechaun
	 */
	private void specialLeprechaun() {
		if (canSpecial() && gold > 0) {
			Thread threadLeprechaun = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(delay);
						setEtat(Etat.SPECIAL);						
						setDoingSpecial(true);

						double vitX = getDirection() == Direction.DROITE ? 0.2 : -0.2;
						Balle balle = new Balle(getPositionX(), getPositionY() + 0.3*getHauteur(), 0.35, 0.35, vitX, att*specialDamageMultiplier, "mediumcoin");
						balles.add(balle);
						gold--; 

						System.out.println(gold);

						peutSpecial = false;
						Thread.sleep((long) (specialDuration[2] * FPS));

						//reset tout apres que l'animation soit fini
						setDoingSpecial(false); //quand le special fini

						Thread.sleep(cdSpecial[2]); //commence le cooldown du special
						peutSpecial = true;


					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					setDoingSpecial(false); //quand le special fini
				}

			}); threadLeprechaun.start();
		}
	}
	/**
	 * L'attaque speciale du goblin
	 */
	private void specialGoblin() {
		if (canSpecial()) {
			Thread threadGoblin = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(delay);						
						setEtat(Etat.SPECIAL);
						setVulnerable(false);		
						setDoingSpecial(true);
						//add dot dmg
						if (getDirection() == Direction.GAUCHE) 
							zoneSpecial = (new Area(new Rectangle2D.Double(getPositionX() - getLargeur(), getPositionY() - 0.5*getHauteur() , 1.5*getLargeur(), 2*getHauteur())));
						if (getDirection() == Direction.DROITE)
							zoneSpecial = (new Area(new Rectangle2D.Double(getPositionX() + 0.5*getLargeur(), getPositionY() - 0.5*getHauteur() , 1.5*getLargeur(), 2*getHauteur())));
						setStunned(true);						

						peutSpecial = false;
						Thread.sleep((long) (specialDuration[3] * FPS));

						//reset tout apres que l'animation soit fini
						zoneSpecial = new Area();
						setVulnerable(true);
						setStunned(false);
						setDoingSpecial(false); //quand le special fini

						Thread.sleep(cdSpecial[3]); //commence le cooldown du special
						peutSpecial = true;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
				}

			}); threadGoblin.start();
		}
	}
	/**
	 * L'attaque speciale du cowboy
	 */
	private void specialCowboy() {
		if (canSpecial()) {
			if (nbBalles > 0) {
				Thread threadCowboy = new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(delay);
							setEtat(Etat.SPECIAL);

							setDoingSpecial(true);

							double vitX = getDirection() == Direction.DROITE ? 0.3 : -0.3;
							Balle balle = new Balle(getPositionX(), getPositionY() + 0.3*getHauteur(), 0.4, 0.15, vitX, att*specialDamageMultiplier, Color.gray);
							balles.add(balle);
							nbBalles--;


							peutSpecial = false;
							Thread.sleep((long) (specialDuration[4] * FPS));

							//reset tout apres que l'animation soit fini
							setDoingSpecial(false); //quand le special fini

							Thread.sleep(cdSpecial[4]); //commence le cooldown du special
							peutSpecial = true;

						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						setDoingSpecial(false); //quand le special fini
					}

				}); threadCowboy.start();
			} else {
				Thread threadRecharger = new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(delay);
							setEtat(Etat.RECHARGE);

							Thread.sleep((long) (rechargeDuration * FPS));
							nbBalles = 6; 

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}					
				}); threadRecharger.start();
			}
		}
	}
	/**
	 * Verifie si le joueur est proche du monstre avec lequel il est en combat
	 * @return Si le joueur est proche du monstre
	 */
	private boolean nearMonstre() {
		if (monstre != null) {
			Rectangle2D.Double rectMonst = monstre.getZoneCollision();
			Rectangle2D.Double rectJoueur = getZoneCollision();			
			if (rectJoueur.getX() > rectMonst.getX() - 2 && rectJoueur.getX() < rectMonst.getX() + monstre.getLargeur() + 2 &&
					rectJoueur.getY() >= rectMonst.getY() && rectJoueur.getY() < rectMonst.getY() + monstre.getHauteur()) 
				return true;
		}
		return false;
	}
	/**
	 * Verifie si le joueur est proche d'un monstre 
	 * @param monstre Un monstre
	 * @return Si le joueur est proche d'un monstre
	 */
	public boolean nearMonstre(Monstre monstre) {
		Rectangle2D.Double rectMonst = monstre.getZoneCollision();
		Rectangle2D.Double rectJoueur = getZoneCollision();			
		if (rectJoueur.getX() > rectMonst.getX() - 10 && rectJoueur.getX() < rectMonst.getX() + 11 &&
				rectJoueur.getY() > rectMonst.getY() - 10 && rectJoueur.getY() < rectMonst.getY() + 11) 
			return true;

		return false;
	}
	/**
	 * Envoye si le joueur peut attaquer
	 * @return Si le joueur peut attaquer
	 */
	public boolean canAttack() {
		return (peutAttack && !isBlocking() && !isStunned() && !isDashing());
	}
	/**
	 * Envoye si le joueur peut dash
	 * @return Si le joueur peut dash
	 */
	public boolean canDash() {
		return (peutDash && !isBlocking() && !isAttacking());
	}
	/**
	 * Envoye si le joueur peut bloquer
	 * @return Si le joueur peut bloquer
	 */
	public boolean canBlock() {
		return (peutBlock && !isAttacking() && !isRolling() && isAuSol());
	}
	/**
	 * Envoye si le joueur peut rouler 
	 * @return Si le joueur peut rouler
	 */
	public boolean canRoll() {
		return (peutRoll && !isAttacking() && !isBlocking() && isAuSol());
	}
	/**
	 * Envoye si le joueur peut faire son attaque speciale
	 * @return Si le joueur peut faire son attaque speciale
	 */
	public boolean canSpecial() {
		return (peutSpecial && !isBlocking());
	}
	/**
	 * Detecte s'il y a eu collision entre l'epee du personnage et le monstre
	 * @param monstre Un monstre
	 * @return S'il y a eu collision entre l'epee du personnage et le monstre
	 */
	public boolean contactMonstre(Monstre monstre) {
		Rectangle2D.Double rectMonstre = monstre.getZoneCollision();	
		Area zoneCollision = new Area();
		if (isAttacking()) zoneCollision = new Area(this.zoneCollision);
		else if (isDoingSpecial()) zoneCollision = new Area(zoneSpecial);
		if(!monstreFrappe && zoneCollision.intersects(rectMonstre)) {
			monstreFrappe = true;
			setMonstre(monstre);

			String spriteName = sprite.getNom();
			if (isDoingSpecial()) {
				damage = att * specialDamageMultiplier;

				if(spriteName.equals("viking")) 
					startStunTimer();
				if(spriteName.equals("goblin")) {
					damage = 0;
					if (isDoingSpecial())
						startDotDamage();
				}						
			}
			//swing dat stick	
			if (isAttacking() || getEtat() == Etat.ATTAQUE_CHARGE) {
				double bonusEnergie = 3*Energie.energieCinetique(getMasse(), getVitesse().getComposanteVitesseX());
				if (bonusEnergie > 30) bonusEnergie = 30;
				damage = Math.max(att, att + bonusEnergie);
				if (spriteName.equals("thief") && isDoingSpecial()) 
					damage = att * specialDamageMultiplier;
			}
			monstre.enleverPointsDeVie(damage);	
			System.out.println("Le monstre a pris " + damage + " , il lui reste " + monstre.getPointsDeVie() + " points de vie");
			return true;
		}					
		return false;
	}
	/**
	 * Commence le temps d'etourdissement du monstre avec lequel le joueur est en combat
	 */
	private void startStunTimer() {
		Thread stunTimer = new Thread(new Runnable() {
			public void run() {
				try {				
					monstre.setStunned(true); //stun le monstre pendant la duration du stun		
					monstre.getSprite().pause(); //arrete son animation
					//monstre.enleverPointsDeVie(damage);
					Thread.sleep((long) (stunDuration * FPS));	

					monstre.setStunned(false);				
					monstre.getSprite().demarrer(); //redemarre son animation

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}); stunTimer.start();
	}
	/**
	 * Commence les degats repartits en plusieurs secondes sur le monstre avec lequel le joueur est en combat
	 */
	private void startDotDamage() {
		Thread dot = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i <= dotDuration; i += dotIteration) {
					if(monstre == null) continue;

					double dotDamage = ((double)dotIteration/dotDuration) * (att*specialDamageMultiplier);		
					monstre.enleverPointsDeVie(dotDamage);					
					System.out.println("Le monstre a pris " + dotDamage + " , il lui reste " + monstre.getPointsDeVie() + " points de vie");

					try {
						Thread.sleep(dotIteration);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}			
		}); dot.start();
	}
	//fin systeme de combat	

	/**
	 * Porte un item
	 * @param item L'item a porter
	 */
	public void equipItem(Item item) {
		if (item != null) {
			int type = getItemType(item);
			if (type == 2) {
				setFuseeVisibile(true);
				if (listeItems.size() == 2)
					listeItems.add(type, item);
			}
			else {
				listeItems.remove(type);			
				listeItems.add(type, item);	
			}
		}
	}
	/**
	 * Envoye le type du item
	 * @param item Un item
	 * @return Le type du item 
	 */
	private int getItemType(Item item) {
		int id = Integer.parseInt(item.getId());			
		if ( -1 < id && id < 18) 
			return 0;
		else if (17 < id && id < 28)
			return 1;	
		else if (id == 28) 	
			return 2; //fusee
		return -1;
	}
	public void unequipFusee() {
		if (isFuseeVisible() && isAuSol()) {
			setFuseeVisibile(false);			
			int posAffichee = inventaire.getPosAffiche();
			inventaire.addItem(listeItems.remove(2));
			inventaire.setFirstUnselected(posAffichee);
		}
	}
	/**
	 * Ouvre une sauvegarde
	 * @param gold L'or
	 * @param experience L'experience
	 * @param stats Les caracteristiques
	 * @param pointsAttribuables Les points attribuables
	 * @param listeNomItems La liste des items
	 * @param pointsDeVie Les points de vie
	 * @param niveau Le niveau
	 * @param listeItemsDuInventaire Une liste d'items
	 */
	public void loadSave(long gold, double experience, int[] stats, int pointsAttribuables, List<String> listeNomItems,
			double pointsDeVie, int niveau, Set<String> listeItemsDuInventaire, double carburantFusee) {
		this.gold			   = gold;
		this.experience 	   = experience;
		this.stats 			   = stats;
		this.pointsAttribuables = pointsAttribuables;
		this.niveau 		   = niveau;
		setFusee(new Fusee(carburantFusee, 0, getMasse(), 10, 50, getPositionX(), getPositionY()));
		setPointsDeVie(pointsDeVie);		
		
		listeItems.clear();
		for (int i = 0; i < listeNomItems.size(); i++) 
			listeItems.add(new Item(listeNomItems.get(i)));
		listeItemsDuInventaire.forEach(itemId -> inventaire.getItemsDuInventaire().add(new Item(itemId)));
		if (!listeItemsDuInventaire.isEmpty())
			inventaire.setSelected(0);
		inventaire.updateInventaire();		
		
		if (listeItems.contains(new Item("28")))
			setFuseeVisibile(true);
	}

	//debut getters
	/**
	 * Envoye l'experience du joueur
	 * @return experience L'experience
	 */
	public double getExperience() {
		return experience;
	}
	/**
	 * Envoye le niveau du joueur
	 * @return niveau Le niveau
	 */
	public int getNiveau() {
		return niveau;
	}
	/**
	 * Envoye le caracteristiques du joueur
	 * @return stats Les caracteristiques
	 */
	public int[] getStats() {
		return stats;
	}
	/**
	 * Envoye les points attribuables aux stats
	 * @return pointsAttribuables Les points attribuables
	 */
	public int getPointsAttribuables() {
		return pointsAttribuables;
	}
	/**
	 * Envoye le dommage fait par le joueur
	 * @return damage Le dommage
	 */
	public double getDamage() {		
		return damage;
	}
	/**
	 * Envoye la liste des items
	 * @return listeItems La liste des items
	 */
	public List<Item> getListeItems() {
		return listeItems;
	}
	/**
	 * Envoye l'aire ou les entites entrantes se font etourdir
	 * @return stunArea L'aire d'etourdissement
	 */
	public Area getStunArea() {
		return zoneSpecial;
	}
	/**
	 * Envoye la liste de balles vivantes
	 * @return balles La liste des balles vivantes
	 */
	public List<Balle> getBalles() {
		return balles;
	}
	/**
	 * Envoye les objets a sauvegarder 
	 * @return Les objets a sauvegarder
	 */
	public SaveJoueur getSaveJoueur() {
		List<String> listeNomItems = new ArrayList<String>();
		for (Item item : listeItems) 
			listeNomItems.add(item.getId());	
		
		Set<String> listeNomItemsInventaire = new LinkedHashSet<String>();
		for (Item item : inventaire.getItemsDuInventaire())
			listeNomItemsInventaire.add(item.getId());
		double carburantFusee = getFusee() == null ? 0 : getFusee().getCarburant();
		
		return new SaveJoueur(getPositionX(), getPositionY(), gold, experience, stats, 
				pointsAttribuables, listeNomItems, getPointsDeVie(), niveau, personnage,
				listeNomItemsInventaire, carburantFusee);
	}	
	/**
	 * Si le joueur peut revivre
	 * @return revivable Si le joueur peut revivre
	 */
	public boolean isRevivable() {
		return revivable;
	}
	/**
	 * Envoye le monstre avec lequel le joueur est en combat
	 * @return monstre Le monstre
	 */
	public Monstre getMonstre() {
		return monstre;
	}
	/**
	 * Envoye l'inventaire du joueur
	 * @return inventaire L'inventaire
	 */
	public Inventaire getInventaire() {
		return inventaire;
	}
	/**
	 * Envoye les pieces d'or du joueur
	 * @return gold Les pieces d'or
	 */
	public long getGold() {
		return gold;
	}
	/**
	 * Enovoye le personnage
	 * @return personnage Le personnage
	 */
	public Personnage getPersonnage() {
		return personnage;
	}
	//fin getters

	//debut setters
	/**
	 * Ajoute un item a la liste des items
	 * @param item Un item
	 */
	public void ajouterItem(Item item){
		listeItems.add(item);
	}
	/**
	 * Ajoute de l'or au joueur
	 * @param gold Les pieces d'or a ajouter
	 */
	public void ajouterGold(int gold) {
		this.gold += gold;
		inventaire.setGold(this.gold);
	}
	/**
	 * Enleve un points attribuable
	 */
	public void enleverPointsAttribuables() {
		this.pointsAttribuables--;
	}
	/**
	 * Definit les points attribuables
	 * @param pointsAttribuables Les points attribuables
	 */
	public void setPointsAttribuables(int pointsAttribuables) {
		this.pointsAttribuables = pointsAttribuables;
	}
	/**
	 * Definit si le joueur peut revivre
	 * @param revivable Si le joueur peut revivre
	 */
	public void setRevivable(boolean revivable) {
		this.revivable = revivable;
	}
	/**
	 * Definit le monstre avec lequel le joueur est en combat
	 * @param monstre Le monstre
	 */
	public void setMonstre(Monstre monstre) {
		this.monstre = monstre;
	}
	/**
	 * Envoye les caracteristiques du joueur
	 * @param stats Les caracteristiques
	 */
	public void setStats(int[] stats) {
		this.stats = stats;
	}
	//fin setters

	/**
	 * Ajoute un ecouteur au joueur
	 * @param objEcouteur Un objet ecouteur
	 */
	public void addJoueurListener(JoueurListener objEcouteur) {
		OBJETS_ENREGISTRES.add(JoueurListener.class, objEcouteur);
	}
	/**
	 * Detecte les changements de niveau
	 */
	private void leverEvenLevelUp() {
		for(JoueurListener ecout : OBJETS_ENREGISTRES.getListeners(JoueurListener.class) ) {
			ecout.levelUp();
		}
	}
}