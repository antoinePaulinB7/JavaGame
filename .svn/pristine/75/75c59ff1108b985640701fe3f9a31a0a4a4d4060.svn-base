package intelligence;

import java.util.ArrayList;
/**
 * Algorithme A* adapté en Java
 * @author Olivier
 *
 */
public class AStar {

	/**
	 * Algorithme A*
	 * @param debut Noeud de depart
	 * @param fin Noeud de fin
	 * @return path Le chemin a suivre
	 */
	public static ArrayList<Node> aStar(Node debut, Node fin){
		ArrayList<Node> closedSet = new ArrayList<Node>();
		ArrayList<Node> openSet = new ArrayList<Node>();
		ArrayList<Node> pathVide = new ArrayList<Node>();
		debut.setfCost(debut.distance(fin));
		openSet.add(debut);
		debut.setgCost(0);
		float tentative_gCost;
		Node current;
		//System.out.println(debut.getSuccessors().size());
		while(!openSet.isEmpty()){
			current = new Node(trouverPlusPetitCout(openSet));
			//System.out.println(current.getX() + "," + current.getY());
			//System.out.println(current.getSuccessors().size());
			if(current.equals(fin)){
				//System.out.println("Succes");
				return pathFrom(current);
			}
			openSet.remove(current);
			closedSet.add(current);
			for(int i = 0; i < current.getSuccessors().size(); i++){
				if(!closedSet.contains(current.getSuccessors().get(i))){
					tentative_gCost = current.getgCost() + current.distance(current.getSuccessors().get(i));
					if(!openSet.contains(current.getSuccessors().get(i))){
						openSet.add(current.getSuccessors().get(i));
						if(!(tentative_gCost>=current.getSuccessors().get(i).getgCost())){
							current.getSuccessors().get(i).setParent(current);
							current.getSuccessors().get(i).setHasParent(true);
							current.getSuccessors().get(i).setgCost(tentative_gCost);
							current.getSuccessors().get(i).setfCost(tentative_gCost + current.getSuccessors().get(i).distance(fin));
						}
					}
				}
			}
		}
		//System.out.println("Fail");
		return pathVide;
	}
	/**
	 * Cherche la liste et retourne le noeud avec le plus petit fCost
	 * @param listNode La liste de nodes
	 * @return current Le node actuel
	 */
	public static Node trouverPlusPetitCout(ArrayList<Node> listNode){
		Node current = new Node(listNode.get(0));
		for(int i = 0; i<listNode.size(); i++){
			if(current.getfCost()>listNode.get(i).getfCost()){
				current = new Node(listNode.get(i));
			}
		}
		return current;
	}
	/**
	 * Cherche la liste et vérifie si un noeud est a la meme position mais a un cout plus petit que le noeud en paramètre
	 * @param list Une liste de nodes
	 * @param node Un node
	 * @return Si un noeud est a la meme position 
	 */
	public static boolean memePositionCoutPlusBas(ArrayList<Node> list, Node node){
		for(int i=0; i<list.size(); i++){
			if(node.getX()==list.get(i).getX()&&node.getY()==list.get(i).getY()){
				if(list.get(i).getfCost()<node.getfCost()){
					return true;
				}
			}
		}return false;
	}

	/**
	 * Méthode qui retourne le trajet à parcourir pour atteindre la node en paramètre
	 * @param debut Le node de depart
	 * @return Le trajet à parcourir pour atteindre la node en paramètre
	 */
	public static ArrayList<Node> pathFrom(Node debut){
		ArrayList<Node> path = new ArrayList<Node>();
		path.add(debut);
		int i=0;
		while(path.get(i).hasParent()){
			path.add(i+1, path.get(i).getParent());
			i++;
		}
		return path;
	}
}
