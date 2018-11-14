package temporaire;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import physique.Position;

public class RayCastingObsolete {
	//constante
	private final static double E = 0.0001;
	private static ArrayList<Position> pos1 = new ArrayList<>();
	private static ArrayList<Position> pos2 = new ArrayList<>();
	
    private static boolean intersects(Position A, Position B, Position P) {
        if (A.getComposantePositionY() > B.getComposantePositionY())
            return intersects(B, A, P);
 
        if (P.getComposantePositionY() == A.getComposantePositionY() || P.getComposantePositionY() == B.getComposantePositionY())
            P.setComposantePositionY(P.getComposantePositionY() + E);
 
        if (P.getComposantePositionY() > B.getComposantePositionY() || P.getComposantePositionY() < A.getComposantePositionY() || P.getComposantePositionX() > max(A.getComposantePositionX(), B.getComposantePositionX()))
            return false;
 
        if (P.getComposantePositionX() < min(A.getComposantePositionX(), B.getComposantePositionX()))
            return true;
 
        double red = (P.getComposantePositionY() - A.getComposantePositionY()) / (double) (P.getComposantePositionX() - A.getComposantePositionX());
        double blue = (B.getComposantePositionY() - A.getComposantePositionY()) / (double) (B.getComposantePositionX() - A.getComposantePositionX());
        return red >= blue;
    }
 
   public static boolean contains(Rectangle2D.Double rect, Position point) {
        int len = 4; //nombre des cotes d'un rectangle
        pos1.clear();
        pos2.clear();
        Position[] coordRect = { new Position(rect.getX()    , rect.getY()      ),  //la coordonnee du cote en haut a gauche
        					     new Position(rect.getWidth(), rect.getY()      ),  //la coordonnee du cote en haut a droite
        					     new Position(rect.getWidth(), rect.getHeight() ),  //la coordonnee du cote en bas  a droite
        					     new Position(rect.getX()    , rect.getHeight() )}; //la coordonnee du cote en bas  a gauche
        for (int i = 0; i < len; i++) {
        	pos1.add(coordRect[i]);
    		pos2.add(coordRect[(i + 1) % len]);
            if (intersects(coordRect[i], coordRect[(i + 1) % len], point)) {
                return true;
            }
        }
        return false;
   	}   

	public static ArrayList<Position> getPos1() {
		return pos1;
	}
	public static ArrayList<Position> getPos2() {
		return pos2;
	}
}
