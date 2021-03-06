package entites;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import environnement.Coffre;
import environnement.Environnement;
import environnement.Plateforme;
import fusee.Fusee;
import interfaces.Collision;
import interfaces.Constantes;
import interfaces.Dessinable;
import interfaces.Enumerations.Direction;
import interfaces.Enumerations.Etat;
import physique.Acceleration;
import physique.Force;
import physique.Position;
import physique.Vitesse;

/**
 * Classe qui cree et gere le mouvement d'une entite physique dans le monde
 * @author Mihai
 * 
 */
public class EntitePhysique extends JPanel implements Dessinable, Collision{

	private static final long serialVersionUID = 1L;
	private double vitesseDeDeplacement, hauteurDeSaut, pointsDeVieMaximum, pointsDeVie;
	private double largeur, hauteur, masse;	
	private Set<Force> forces;
	private Acceleration acceleration;
	private Vitesse vitesse;
	private Position position;
	private Rectangle2D.Double pieds, corps;
	private Environnement objetEnvironnement = null;
	private Line2D.Double trajectoirePieds1, trajectoirePieds2, trajectoirePieds3, trajectoirePieds4;
	private Line2D.Double trajectoireCorps1, trajectoireCorps2, trajectoireCorps3, trajectoireCorps4;
	private Etat etat = Etat.IMMOBILE;
	private Fusee fusee = null;
	private Direction direction = Direction.DROITE;
	private double vieRegenereParSeconde = 0.2;

	//etats de mouvement
	private boolean auSol = false, enMouvement = false, enChute = false; 
	private boolean dashing = false, blocking = false, attacking = false, vulnerable = true, doingSpecial = false, rolling = false;
	private boolean peutAttaquer = true, horsCombat = true, stunned = false;

	private boolean afficherHitbox, fuseeUtilisee, fuseeEnAir, fuseeVisible = false;

	/**
	 * Constructeur de base qui prend seuelement une position
	 * @param positionX La position en x
	 * @param positionY La position en y
	 */
	public EntitePhysique(double positionX, double positionY) {
		this.position			  = new Position(positionX, positionY);
		this.vitesseDeDeplacement = 0.0625;
		this.hauteurDeSaut        = 0.2;
		this.masse 			      = 69;
		this.hauteur 	          = 1;		
		this.largeur 			  = 0.5;
		initialiserComposantes();
	}

	/**
	 * Constructeur d'une entite avec plus de parametres
	 * @param positionX La position en x
	 * @param positionY La position en y
	 * @param vitesseDeDeplacement La vitesse du mouvement gauche/droite du joueur
	 * @param hauteurDeSaut La hateau d'un saut
	 * @param masse La masse du joueur
	 * @param largeur La largeur de la taille du joueur
	 * @param hauteur La hauteur de la taille du joueur
	 */
	public EntitePhysique(double positionX, double positionY, double vitesseDeDeplacement, double hauteurDeSaut, double masse, double largeur, double hauteur, double vieRegenereParSeconde) {
		this.position			   = new Position(positionX, positionY);
		this.vitesseDeDeplacement  = vitesseDeDeplacement;
		this.hauteurDeSaut 		   = hauteurDeSaut;
		this.masse  			   = masse;
		this.largeur 			   = largeur;
		this.hauteur 			   = hauteur;
		this.vieRegenereParSeconde = vieRegenereParSeconde;
		initialiserComposantes();
		ajouterForce(new Force("Gravite", 0, -Constantes.K_GRAVITE*masse));		
	}
	/**
	 * Constructeur d'une entite avec plus de parametres
	 * @param positionX La position en x
	 * @param positionY La position en y
	 * @param masse La masse du joueur
	 * @param largeur La largeur de la taille du joueur
	 * @param hauteur La hauteur de la taille du joueur
	 */
	public EntitePhysique(double positionX, double positionY, double masse, double largeur, double hauteur, double vieRegenereParSeconde) {
		this.position              = new Position(positionX, positionY);
		this.masse                 = masse;
		this.largeur               = largeur;
		this.hauteur               = hauteur;
		this.vieRegenereParSeconde = vieRegenereParSeconde;
		initialiserComposantes();
		ajouterForce(new Force("Gravite", 0, -Constantes.K_GRAVITE*masse));		
	}
	/**
	 * Initialize les composantes de depart
	 */
	private void initialiserComposantes() {
		forces       = Collections.synchronizedSet(new HashSet<Force>());
		acceleration = new Acceleration();
		vitesse      = new Vitesse();
		corps        = new Rectangle2D.Double(position.getComposantePositionX(), position.getComposantePositionY(),largeur, hauteur);
		pieds        = new Rectangle2D.Double(position.getComposantePositionX(), position.getComposantePositionY(), largeur, hauteur/4);
	}
	/**
	 * Dessine les donnees scientifiques de l'entite
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		Color avant = g2d.getColor();

		//les hitbox du corps et des pieds
		//a trouver des nouvelles valeurs pour les hitbox
		corps = new Rectangle2D.Double(position.getComposantePositionX() ,position.getComposantePositionY(),largeur, hauteur);
		pieds = new Rectangle2D.Double(position.getComposantePositionX() ,position.getComposantePositionY(),largeur, hauteur/4); //ajustement de la hitbox

		if(afficherHitbox) {
			g2d.setColor(Color.blue);
			g2d.fill(mat.createTransformedShape(corps));
			g2d.setColor(Color.darkGray);
			g2d.fill(mat.createTransformedShape(pieds));
		}

		g2d.setColor(avant);
	}
	/**
	 * Methode qui teste si l'entite est sur quelque chose
	 * @return si l'entite est sur quelquechose
	 */
	public boolean surQuelqueChose() {	
		//a modifier faut encapsuler les chiffres random	
		if (objetEnvironnement == null) 
			return false;
		return (position.getComposantePositionX()+largeur/2.8 > objetEnvironnement.getZoneCollision().getX() &&
				position.getComposantePositionX()+0.3 < objetEnvironnement.getZoneCollision().getX()+objetEnvironnement.getZoneCollision().getWidth()+Double.MIN_VALUE);

	}
	/**
	 * Verifie si l'entite est en haut d'une autre entite
	 * @param ent Une entite
	 * @return Si l'entite est en haut d'une autre entite
	 */
	public boolean enHaut(EntitePhysique ent) {
		return (getPositionY() > (ent.getPositionY() + hauteur - 0.01));
	}
	/**
	 * Methode qui met a jour la position de l'entite
	 */
	public void update() {			
		//verifier si l'entite est toujours en mouvement
		if(vitesse.getComposanteVitesseX()>1E-5||vitesse.getComposanteVitesseX()<-1E-5) enMouvement = true;//YA UN BUG VRAIMENT COOL SI ON ENLEVE CETTE LIGNE :)

		if ((nextFrameVitesse().getComposanteVitesseX() > 1E-5 && nextFrameVitesse().getComposanteVitesseX() < 1E-5) ||
				(vitesse.getComposanteVitesseX()> -1E-2 && vitesse.getComposanteVitesseX()< 1E-2)) {
			vitesse.setComposanteVitesseX(0);
			enMouvement = false;
		}
		if(vitesse.getComposanteVitesseY() < 0)
			enChute = true;

		if (enMouvement && !surQuelqueChose() || !surQuelqueChose() || (vitesse.getComposanteVitesseY() > 0.005 && fuseeUtilisee)) {
			auSol = false;
			enleverForce(new Force("Normale", 0, Constantes.K_GRAVITE*masse));
		}
		
		//enleve la force de friction		
		enleverForce(new Force("Friction", 0, 0));
		//ajouter la friction si necessaire
		if (enMouvement && auSol && !rolling) { 
			if (objetEnvironnement.getClass() == Plateforme.class) { //friction plateforme
				((Plateforme)objetEnvironnement).effetContactBloc(this);
				ajouterForce(new Force("Friction", ((Plateforme)objetEnvironnement).getForceFriction(), 0));
			} else if (objetEnvironnement.getClass() == Coffre.class) { //friction coffre
				objetEnvironnement.effetContactBloc(this);
				ajouterForce(new Force("Friction", objetEnvironnement.getForceFriction(), 0));
			}
		}
		if (!enMouvement && auSol && !blocking && !attacking && !rolling) {
			setEtat(Etat.IMMOBILE);
		}
		//calcul forces, acceleration, vitesse et position
		Force forceTotale = new Force("Force totale");	
		Object[] forcesArray = forces.toArray();
		for (int i = 0; i < forcesArray.length; i++) 
			forceTotale.additionner((Force)forcesArray[i]);
		acceleration = forceTotale.calculerAcceleration(masse);
		vitesse.ajouterAcceleration(acceleration.diviser((double)Constantes.FPS));
		position.ajouterVitesse(vitesse); 

		if (fusee != null) fusee.setPosition(position);
		else fuseeUtilisee = false;
	}	
	/**
	 * Calcule la vitesse dans la prochaine frame
	 * @return vitesse La vitesse a la prochaine frame
	 */
	public Vitesse nextFrameVitesse() {
		Force forceTotaleLocale = new Force("Force totale");
		//Set<Force> forcesLocale = Collections.synchronizedSet(new HashSet<Force>());
		//forcesLocale.addAll(forces);
		Vitesse vitesse = new Vitesse(this.vitesse.getComposanteVitesseX(), this.vitesse.getComposanteVitesseY());

		//forc.forEach(forca -> forceTotaleLocale.additionner(forca));	
		Object[] forcesArray = forces.toArray();
		for (int i = 0; i < forcesArray.length; i++) {
			forceTotaleLocale.additionner((Force)forcesArray[i]);
		}

		Acceleration accel = forceTotaleLocale.calculerAcceleration(masse);		
		vitesse.ajouterAcceleration(accel.diviser((double)Constantes.FPS));
		return vitesse;
	}
	public void utiliserFusee(boolean fuseeUtilisee) {
		if (fusee != null && fuseeVisible) {
			this.fuseeUtilisee = fuseeUtilisee;
			double vitesseFuseeX = fusee.getVitesse2().getComposanteVitesseX() + getVitesse().getComposanteVitesseX();
			double vitesseFuseeY = fusee.getVitesse2().getComposanteVitesseY() + getVitesse().getComposanteVitesseY();
			if (vitesseFuseeY > 0.4) vitesseFuseeY = 0.4;
			vitesse = new Vitesse(vitesseFuseeX, vitesseFuseeY);
			fusee.update(fuseeUtilisee);
			fuseeEnAir = (vitesseFuseeY > 0.01) ? true : false;
			
			if (fuseeUtilisee) etat = Etat.IMMOBILE;			
			if (fusee.getCarburant() < 0 && auSol)
				fusee = null;
		}
	}
	/**
	 * Gere le saut de l'entite
	 */
	public void sauter() {
		if (auSol && !stunned && !fuseeUtilisee) {
			enleverForce(new Force("Normale", 0, Constantes.K_GRAVITE*masse));
			setPositionY(getPositionY() + 5E-3);
			vitesse.setComposanteVitesseY(hauteurDeSaut);
			auSol = false;
		}
	}
	/**
	 * Gere la descente de l'entite
	 */
	public void descendre() {
		if (auSol && !stunned && !fuseeUtilisee) {
			setPositionY(getPositionY() - (hauteur/4 + 1));
			enleverForce(new Force("Normale", 0, Constantes.K_GRAVITE*masse));
			auSol = false;
		}
	}
	/**
	 * Gere le deplacement vers la droite
	 */
	public void bougerDroite(){
		if (!stunned && !blocking) {
			if (!attacking && !fuseeEnAir) setEtat(Etat.MOUVEMENT);
			setDirection(Direction.DROITE);
			enMouvement = true;
			if (vitesse.getComposanteVitesseX() < 0) vitesse.setComposanteVitesseX(0);
			if (vitesse.getComposanteVitesseX() < vitesseDeDeplacement){
				if(auSol){
					vitesse.setComposanteVitesseX(vitesse.getComposanteVitesseX() + vitesseDeDeplacement);
				}else{
					vitesse.setComposanteVitesseX(vitesse.getComposanteVitesseX() + vitesseDeDeplacement/4);
				}
			}
			if (!auSol) enleverForce(new Force("Normale", 0, Constantes.K_GRAVITE*masse));
		}
	}
	/**
	 * Gere le deplacement vers la gauche
	 */
	public void bougerGauche(){
		if (!stunned && !blocking) {
			if (!attacking && !fuseeEnAir) setEtat(Etat.MOUVEMENT);
			setDirection(Direction.GAUCHE);
			enMouvement = true;
			if (vitesse.getComposanteVitesseX() > 0) vitesse.setComposanteVitesseX(0);
			if (vitesse.getComposanteVitesseX() > -vitesseDeDeplacement){
				if(auSol){
					vitesse.setComposanteVitesseX(vitesse.getComposanteVitesseX() - vitesseDeDeplacement);
				}else{
					vitesse.setComposanteVitesseX(vitesse.getComposanteVitesseX() - vitesseDeDeplacement/4);
				}
			}
			if (!auSol) enleverForce(new Force("Normale", 0, Constantes.K_GRAVITE*masse));
		}
	}
	/**
	 * Arr�te de d�placement en x
	 */
	public void arreterBouger(){
		enMouvement = false;
		vitesse.setComposanteVitesseX(0);
	}
	/**
	 * Teste si l'entite est en contact avec le corps d'une autre entite
	 * @return Si l'entite est en contact
	 */
	public boolean contact(EntitePhysique ent) {
		if (!fuseeUtilisee) {
			Rectangle2D.Double rect = ent.getZoneCollision();
			double x = position.getComposantePositionX() + 0.25;
			double y = position.getComposantePositionY();
			double largeur = this.largeur/2;
			double vitX = nextFrameVitesse().getComposanteVitesseX();
			double vitY = nextFrameVitesse().getComposanteVitesseY();

			trajectoireCorps1 = new Line2D.Double(x, y, x+vitX, y+vitY);
			if(trajectoireCorps1.intersects(rect))
				return true;

			trajectoireCorps2 = new Line2D.Double(x+largeur, y,	x+largeur+vitX,	y+vitY);
			if(trajectoireCorps2.intersects(rect))
				return true;

			trajectoireCorps3 = new Line2D.Double(x, y+hauteur/4, x+vitX, y+hauteur/4+vitY);
			if(trajectoireCorps3.intersects(rect))
				return true;

			trajectoireCorps4 = new Line2D.Double( x+largeur, y+hauteur/4, x+largeur+vitX, y+hauteur/4+vitY);
			if(trajectoireCorps4.intersects(rect))
				return true;		
		}
		return false;
	}
	/**
	 * Teste si l'entite est en contact avec un objet environnement
	 * @return Si l'entite est en contact avec un objet environnement
	 */
	public boolean contactPieds(Environnement env, int cas) {
		if (env.getZoneCollision() != null && !fuseeEnAir) {
			if (cas == 0) { //cas normal lorsque l'entite tombe
				if (position.getComposantePositionY() > env.getZoneCollision().getY()+0.5) { //fix waveland
					Rectangle2D.Double rect = env.getZoneCollision();
					double x = position.getComposantePositionX() + 0.3;
					double y = position.getComposantePositionY();
					double largeur = this.largeur/2.8;
					double vitX = nextFrameVitesse().getComposanteVitesseX();
					double vitY = nextFrameVitesse().getComposanteVitesseY();

					trajectoirePieds1 = new Line2D.Double(x, y, x+vitX, y+vitY);
					if(trajectoirePieds1.intersects(rect))
						return true;

					trajectoirePieds2 = new Line2D.Double(x+largeur, y,	x+largeur+vitX,	y+vitY);
					if(trajectoirePieds2.intersects(rect))
						return true;

					trajectoirePieds3 = new Line2D.Double(x, y+hauteur/4, x+vitX, y+hauteur/4+vitY);
					if(trajectoirePieds3.intersects(rect)) 
						return true;

					trajectoirePieds4 = new Line2D.Double(x+largeur, y+hauteur/4, x+largeur+vitX, y+hauteur/4+vitY);
					if(trajectoirePieds4.intersects(rect))
						return true;
				}
			} else { //cas lorsque l'entite rentre de cote dans un environnement
				Rectangle2D.Double rect = env.getZoneCollision();
				double x = position.getComposantePositionX() + 0.3;
				double y = position.getComposantePositionY();
				double largeur = this.largeur/2.8;
				double vitX = nextFrameVitesse().getComposanteVitesseX();
				double vitY = nextFrameVitesse().getComposanteVitesseY();

				trajectoirePieds1 = new Line2D.Double(x, y, x+vitX, y+vitY);
				if(trajectoirePieds1.intersects(rect))
					return true;

				trajectoirePieds2 = new Line2D.Double(x+largeur, y,	x+largeur+vitX,	y+vitY);
				if(trajectoirePieds2.intersects(rect))
					return true;

				trajectoirePieds3 = new Line2D.Double(x, y+hauteur/4, x+vitX, y+hauteur/4+vitY);
				if(trajectoirePieds3.intersects(rect)) 
					return true;

				trajectoirePieds4 = new Line2D.Double(x+largeur, y+hauteur/4, x+largeur+vitX, y+hauteur/4+vitY);
				if(trajectoirePieds4.intersects(rect))
					return true;
			}
		}
		return false;
	}
	/**
	 * Verifie s'il y a un objet environnement proche de l'entite
	 * @param environnement Un environnement
	 * @param rangeDetection Le range de detection
	 * @return S'il y a un objet environnement proche de l'entite
	 */
	public boolean nearEnvironnement(Environnement environnement, int rangeDetection) {
		Rectangle2D.Double rectEnv = environnement.getZoneCollision();
		Rectangle2D.Double rectEnt = getZoneCollision();

		if (rectEnt.getX() > rectEnv.getX() - rangeDetection && rectEnt.getX() < rectEnv.getX() + rangeDetection + 1 &&
				rectEnt.getY() > rectEnv.getY() - 2 && rectEnt.getY() < rectEnv.getY() + 3)
			return true;		
		return false;
	}
	/**
	 * Gere l'effet du contact avec un objet
	 */
	public void effetContact(Object obj) {
		//a faire
	}
	/**
	 * Ajoute une force aux forces deja existantes si elle n'existe pas deja
	 * @param e Une force
	 */
	public void ajouterForce(Force e){
		forces.add(e);
	}
	/**
	 * Enleve une force aux forces deja existantes si elle n'existe pas deja
	 * @param e Une force
	 */
	public void enleverForce(Force e){
		forces.remove(e);
	}
	/**
	 * Ajoute des points de vie a l'entite
	 * @param pointsDeVie Les points de vie
	 */
	public void ajouterPointsDeVie(double pointsDeVie) {
		this.pointsDeVie += pointsDeVie;
	}
	/**
	 * Enleve des points de vie a l'entite
	 * @param pointsDeVie Les points de vie
	 */
	public void enleverPointsDeVie(double pointsDeVie) {
		if (vulnerable) this.pointsDeVie -= pointsDeVie;
	}
	/**
	 * Cree un string significatif a la classe EntitePhysique
	 */
	public String toString() {
		return "EntitePhysique [position=" + position + "]";
	}
	//debut getters
	/**
	 * Envoye la zone de collision de l'entite
	 * @return corps La zone de collision
	 */
	public Rectangle2D.Double getZoneCollision() {
		return corps;
	}
	/**
	 * Envoye les pieds de l'entite
	 * @return pieds Les pieds de l'entite
	 */
	public Rectangle2D.Double getPieds() {
		return pieds;
	}
	/**
	 * Envoye la position de l'entite
	 * @return position La position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * Envoye la composante x de la position de l'entite
	 * @return La composante x de la position 
	 */
	public double getPositionX() {
		return position.getComposantePositionX();
	}
	/**
	 * Envoye la composante y de la position de l'entite
	 * @return La composante y de la position 
	 */
	public double getPositionY() {
		return position.getComposantePositionY();
	}
	/**
	 * Envoye la vitesse de l'entite
	 * @return vitesse La vitesse
	 */
	public Vitesse getVitesse() {
		return vitesse;
	}
	/**
	 * Envoye l'acceleration de l'entite
	 * @return acceleration L'acceleration
	 */
	public Acceleration getAcceleration() {
		return acceleration;
	}
	/**
	 * Envoye la masse de l'entite
	 * @return masse La masse
	 */
	public double getMasse() {
		return masse;
	}

	public double getHauteurDeSaut() {
		return hauteurDeSaut;
	}
	/**
	 * Envoye si l'entite est au sol
	 * @return auSol Si l'entite est au sol
	 */
	public boolean isAuSol() {
		return auSol;
	}
	/**
	 * Envoye si l'entite est en mouvement
	 * @return enMouvement Si l'entite est en mouvement
	 */
	public boolean isEnMouvement() {
		return enMouvement;
	}
	/**
	 * Envoye si l'entite est en chute
	 * @return enChute Si l'entite est en chute
	 */
	public boolean isEnChute() {
		return enChute;
	}
	/**
	 * Envoye les points de vie de l'entite
	 * @return pointsDeVie Les points de vie
	 */
	public double getPointsDeVie() {
		return pointsDeVie;
	}
	/**
	 * Envoye si l'entite peut attaquer
	 * @return peutAttaquer La condition d'attaque
	 */
	public boolean peutAttaquer() {
		return peutAttaquer;
	}
	/**
	 * Envoye l'objet de l'environnement sous l'entite
	 * @return objetEnvironnement L'objet dans l'environnement
	 */
	public Environnement getObjetEnvironnement() {
		return objetEnvironnement;
	}
	/**
	 * Envoye la largeur de l'entite
	 * @return largeur La largeur
	 */
	public double getLargeur() {
		return largeur;
	}
	/**
	 * Envoye la hauteur de l'entite
	 * @return hauteur La hauteur
	 */
	public double getHauteur() {
		return hauteur;
	}
	/**
	 * Envoye la vitesse de deplacement de l'entite
	 * @return viitesseDeDeplacement La vitesse de deplacement
	 */
	public double getVitesseDeDeplacement() {
		return vitesseDeDeplacement;
	}
	/**
	 * Envoye les points de vie maximum de l'entite
	 * @return pointsDeVieMaximum Les points de vie maximum
	 */
	public double getPointsDeVieMaximum() {
		return pointsDeVieMaximum;
	}
	/**
	 * Envoye l'etat de l'entite
	 * @return etat L'etat
	 */
	public Etat getEtat() {
		return etat;
	}
	/**
	 * Envoye la direction de l'entite
	 * @return direcction La direction
	 */
	public Direction getDirection() {
		return direction;
	}
	/**
	 * Envoye si l'entite est entrain de dash
	 * @return dashing Si l'entite est entrain de dash
	 */
	public boolean isDashing() {
		return dashing;
	}
	/**
	 * Envoye si l'entite est entrain de bloquer
	 * @return blocking Si l'entite est entrain de bloquer
	 */
	public boolean isBlocking() {
		return blocking;
	}

	/**
	 * Envoye si l'entite est entrain d'attaquer
	 * @return attacking Si l'entite est entrain d'attaquer
	 */
	public boolean isAttacking() {
		return attacking;
	}
	/**
	 * Envoye si l'entite est vulnerable
	 * @return vulnerable Si l'entite est vulnerable
	 */
	public boolean isVulnerable() {
		return vulnerable;
	}
	/**
	 * Envoye si l'entite est entrain de faire son attaque speciale
	 * @return doingSpecial Si l'entite est entrain de faire son attaque speciale
	 */
	public boolean isDoingSpecial() {
		return doingSpecial;
	}
	/**
	 * Envoye si l'entite est entrain de rouler
	 * @return rolling Si l'entite est entrain de rouler
	 */
	public boolean isRolling() {
		return rolling;
	}
	/**
	 * Envoye si l'entite est hors combat
	 * @return horsCombat Si l'entite est hors combat
	 */
	public boolean isHorsCombat() {
		return horsCombat;
	}
	public boolean getAfficherHitbox() {
		return afficherHitbox;
	}
	/**
	 * Envoye si l'entite est morte
	 * @return Si elle est morte
	 */
	public boolean isDead() {
		return getPointsDeVie() <= 0;
	}
	/**
	 * Envoye si l'entite est etourdi
	 * @return stunned Si l'entite est etourdi
	 */
	public boolean isStunned() {
		return stunned;
	}
	/**
	 * Envoye la vie regenere par seconde de cette entite
	 * @return vieRegenereParSeconde La vie regenere par seconde
	 */
	public double getVieRegenereParSeconde() {
		return vieRegenereParSeconde;
	}
	/**
	 * Envoye la fusee
	 * @return fusee La fusee
	 */
	public Fusee getFusee() {
		return fusee;
	}
	/**
	 * Envoye si la fusee est visible
	 * @return fuseeVisible Si la fusee est visible
	 */
	public boolean isFuseeVisible() {
		return fuseeVisible;
	}
	//fin getters

	//debut setters
	/**
	 * Definit la position de l'entite
	 * @param position Une position
	 */
	public void setPosition(double composantePositionX, double composantePositionY) {
		this.position.setComposantePositionX(composantePositionX);
		this.position.setComposantePositionY(composantePositionY);
	}
	public void setFusee(Fusee fusee) {
		this.fusee = fusee;
	}
	/**
	 * Definit la position de l'entite
	 * @param position Une position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	/**
	 * Definit la composante x de la position de l'entite
	 * @param composantePositionX La composante x d'une position 
	 */
	public void setPositionX(double composantePositionX) {
		this.position.setComposantePositionX(composantePositionX);
	}
	/**
	 * Definit la composante y de la position de l'entite
	 * @param composantePositionY La composante y d'une position
	 */
	public void setPositionY(double composantePositionY) {
		this.position.setComposantePositionY(composantePositionY);
	}
	/**
	 * Definit la vitesse de l'entite
	 * @param vitesse Une vitesse
	 */
	public void setVitesse(Vitesse vitesse) {
		this.vitesse = vitesse;
	}
	/**
	 * Definit l'acceleration de l'entite
	 * @param acceleration Une acceleration
	 */
	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = acceleration;
	}
	/**
	 * Definit la masse de l'entite
	 * @param masse Une masse
	 */
	public void setMasse(double masse) {
		this.masse = masse;
	}
	/**
	 * Definit un objet de l'environnement qui est sous les pieds de l'entite en ce moment
	 * @param objetEnvironnement Un objet environnement
	 */
	public void setObjetEnvironnementSousLesPieds(Environnement objetEnvironnement) {
		this.objetEnvironnement  = objetEnvironnement;
	}
	/**
	 * Definit si l'entite est au sol
	 * @param auSol Si l'entite est au sol
	 */
	public void setAuSol(boolean auSol) {
		this.auSol = auSol;
	}
	/**
	 * Definit si l'entite est en mouvement
	 * @param enMouvement Si l'entite est en mouvement
	 */
	public void setEnMouvement(boolean enMouvement) {
		this.enMouvement = enMouvement;
	}	
	/**
	 * Definit si l'entite est en chute
	 * @param enChute Si l'entite est en chute
	 */
	public void setEnChute(boolean enChute) {
		this.enChute = enChute;
	}
	/**
	 * Definit la vitesse de deplacement de l'entite
	 * @param vitesseDeDeplacement La vitesse de deplacement
	 */
	public void setVitesseDeDeplacement(double vitesseDeDeplacement) {
		this.vitesseDeDeplacement = vitesseDeDeplacement;
	}
	/**
	 * Definit la hauteur de saut de l'entite
	 * @param hauteurDeSaut La hauteur de saut
	 */
	public void setHauteurDeSaut(double hauteurDeSaut) {
		this.hauteurDeSaut = hauteurDeSaut;
	}
	/**
	 * Definit les points de vie de l'entite
	 * @param pointsDeVie Les points de vie
	 */
	public void setPointsDeVie(double pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}
	/**
	 * Definit la largeur de l'entite
	 * @param largeur Une largeur
	 */
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}
	/**
	 * Definit la hauteur de l'entite
	 * @param hauteur Une hauteur
	 */
	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
	/**
	 * Definit si l'entite peut attaquer
	 * @param peutAttaquer La posibilite d'attaque
	 */
	public void setPeutAttaquer(boolean peutAttaquer) {
		this.peutAttaquer = peutAttaquer;
	}
	/**
	 * Definit les points de vie maximum de l'entite et update les points de vie actuels
	 * @param pointsDeVieMaximum Les points de vie maximum
	 */
	public void setPointsDeVieMaximum(double pointsDeVieMaximum) {
		this.pointsDeVieMaximum = pointsDeVieMaximum;
		//initialise les points de vie en meme temps
		pointsDeVie = pointsDeVieMaximum;
	}
	/**
	 * Definit l'etat de l'entite
	 * @param etat Un etat
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	/**
	 * Definit la direction de l'entite
	 * @param direction Une direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	/**
	 * Definit si l'entite est entrain de dash
	 * @param dashing Si l'entite est entrain de dash
	 */
	public void setDashing(boolean dashing) {
		this.dashing = dashing;
	}
	/**
	 * Definit si l'entite est entrain de bloquer
	 * @param blocking Si l'entite est entrain de bloquer
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}
	/**
	 * Definit si l'entite est entrain d'attaquer
	 * @param attacking Si l'entite est entrain d'attaquer
	 */
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	/**
	 * Definit si l'entite est vulnerable
	 * @param vulnerable Si l'entite est vulnerable
	 */
	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}
	/**
	 * Definit si l'entite est entrain de faire son attaque speciale
	 * @param doingSpecial Si l'entite est entrain de faire son attaque speciale
	 */
	public void setDoingSpecial(boolean doingSpecial) {
		this.doingSpecial = doingSpecial;
	}
	/**
	 * Definit si l'entite est entrain de rouler par terre
	 * @param rolling Si l'entite est entrain de rouler par terre
	 */
	public void setRolling(boolean rolling) {
		this.rolling = rolling;
	}
	/**
	 * Definit l'etourdissement de l'entite
	 * @param stunned L'etourdissement
	 */
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	/**
	 * Definit si l'entite est hors combat
	 * @param horsCombat Si l'entite est hors combat
	 */
	public void setHorsCombat(boolean horsCombat) {
		this.horsCombat = horsCombat;
	}
	/**
	 * Definit la vie regenere par seconde
	 * @param vieRegenereParSeconde La vie regenere par seconde
	 */
	public void setVieRegenereParSeconde(double vieRegenereParSeconde) {
		this.vieRegenereParSeconde = vieRegenereParSeconde;
	}
	/**
	 * Definit si la hitbox des collision est affiche
	 * @param afficherHitbox Si la hitbox des collisions est affiche
	 */
	public void setAfficherHitbox(boolean afficherHitbox) {
		this.afficherHitbox = afficherHitbox;
	}
	/**
	 * Definit si la fusee est visible
	 * @param fuseeVisible La visibilite de la fusee
	 */
	public void setFuseeVisibile(boolean fuseeVisible) {
		this.fuseeVisible = fuseeVisible;
	}
	//fin setters		
}
