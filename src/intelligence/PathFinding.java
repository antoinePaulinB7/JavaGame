package intelligence;

import java.util.ArrayList;

import entites.Monstre;
import physique.Position;

/**
 * Classe qui trouve le chemin que les monstres doivent prendre
 * @author Olivier
 *
 */
public class PathFinding {
	
	/**
	 * D�termine comment un monstre se d�place entre deux nodes
	 * @param orig Node d'origine
	 * @param but Node de fin
	 * @param monstre Monstre � d�placer
	 */
	public static void path(Node orig, Node but, Monstre monstre){
		int deplacementX = but.getX()-orig.getX();
		int deplacementY = but.getY()-orig.getY();
		switch(deplacementX){
		case 3:
			monstre.bougerDroite();
			break;
		case 2:
			monstre.bougerDroite();
			break;
		case 1:
			monstre.bougerDroite();
			break;
		case -1:
			monstre.bougerGauche();
			break;
		case -2:
			monstre.bougerGauche();
			break;
		case -3:
			monstre.bougerGauche();
			break;
		}
		if(deplacementY<0){
			monstre.descendre();
		} else if(deplacementY>0){
			if(!monstre.isAttacking()) {				
				monstre.sauter();
			}
		}
	}
	/**
	 * D�termine comment un monstre se d�place entre deux positions
	 * @param orig Position de d�part
	 * @param but Position de fin
	 * @param monstre Monstre � d�placer
	 */
	public static void path(Position orig, Position but, Monstre monstre){
		int deplacementX = (int) (but.getComposantePositionX()-(int)orig.getComposantePositionX());
		int deplacementY = (int) (but.getComposantePositionY()-(int)orig.getComposantePositionY());
		switch(deplacementX){
		case 3:
			monstre.bougerDroite();
			break;
		case 2:
			monstre.bougerDroite();
			break;
		case 1:
			monstre.bougerDroite();
			break;
		case -1:
			monstre.bougerGauche();
			break;
		case -2:
			monstre.bougerGauche();
			break;
		case -3:
			monstre.bougerGauche();
			break;
		}
		if(deplacementY<0){
			monstre.descendre();
		} else if(deplacementY>0){
			monstre.sauter();
		}

	}
	/**
	 * D�termine comment un monstre se d�place entre deux positions
	 * @param origX Origine en x
	 * @param origY Origine en y
	 * @param butX Fin en x
	 * @param butY Fin en y
	 * @param monstre Monstre � d�placer
	 */
	public static void path(int origX, int origY, int butX, int butY, Monstre monstre){
		int deplacementX = butX - origX;
		int deplacementY = butY - origY;
		switch(deplacementX){
		case 3:
			monstre.bougerDroite();
			break;
		case 2:
			monstre.bougerDroite();
			break;
		case 1:
			monstre.bougerDroite();
			break;
		case -1:
			monstre.bougerGauche();
			break;
		case -2:
			monstre.bougerGauche();
			break;
		case -3:
			monstre.bougerGauche();
			break;
		}
		if(deplacementY<0){
			monstre.descendre();
		} else if(deplacementY>0){
			monstre.sauter();
		}

	}
	/**
	 * D�termine comment un monstre se d�place en suivant une liste de nodes
	 * @param listNodes La liste � suivre
	 * @param monstre Le monstre � d�placer
	 * @param distX La distance en x du tableau
	 * @param distY La distance en y du tableau
	 */
	public static void follow(ArrayList<Node> listNodes, Monstre monstre, int distX, int distY){
		for(int i = 0; i<listNodes.size();i++){

			int deplacementX = (int) (listNodes.get(i).getX() + 50*distX - monstre.getPositionX());
			double deplacementY =  (listNodes.get(i).getY() + 50*distY - monstre.getPositionY());
			switch(deplacementX){
			case 3:
				monstre.deplaceMonstre((int)monstre.getPositionX()+3, (int)monstre.getPositionY(), 48);
				break;
			case 2:
				monstre.deplaceMonstre((int)monstre.getPositionX()+2, (int)monstre.getPositionY(), 32);
				break;
			case 1:
				monstre.deplaceMonstre((int)monstre.getPositionX()+1, (int)monstre.getPositionY(), 16);
				break;
			case -1:
				monstre.deplaceMonstre((int)monstre.getPositionX()-1, (int)monstre.getPositionY(), 16);
				break;
			case -2:
				monstre.deplaceMonstre((int)monstre.getPositionX()-2, (int)monstre.getPositionY(), 32);
				break;
			case -3:
				monstre.deplaceMonstre((int)monstre.getPositionX()-3, (int)monstre.getPositionY(), 48);
				break;
			}
			//System.out.println(deplacementY);
			if(deplacementY<0){
				//System.out.println("monstre descend");
				monstre.descendre();
			} else if(deplacementY>0){
				//System.out.println("monstre saute");				
				monstre.sauter();
			}
		}
	}
}
