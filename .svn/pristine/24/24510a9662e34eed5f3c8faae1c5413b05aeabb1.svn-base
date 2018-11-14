package temporaire;

import java.util.ArrayList;

import intelligence.Node;

public class AStarTemp {
	
	public static ArrayList<Node> AStar(Node debut, Node fin){
		ArrayList<Node> closedSet = new ArrayList<Node>();
		ArrayList<Node> openSet = new ArrayList<Node>();
		ArrayList<Node> pathVide = new ArrayList<Node>();
		debut.setfCost(debut.distance(fin));
		openSet.add(debut);
		float tentative_gCost;
		Node current;
		while(!openSet.isEmpty()){
			current = trouverPlusPetitCout(openSet);
			if(current.equals(fin)){
				return pathFrom(current);
			}
			openSet.remove(current);
			closedSet.add(current);
			for(int i = 0; i < current.getSuccessors().size(); i++){
				if(!closedSet.contains(current.getSuccessors().get(i))){
					tentative_gCost = current.getgCost() + current.distance(current.getSuccessors().get(i));
					if(!openSet.contains(current.getSuccessors().get(i))){
						openSet.add(current.getSuccessors().get(i));
					}else if(tentative_gCost<current.getSuccessors().get(i).getgCost()){
						current.getSuccessors().get(i).setParent(current);
						current.getSuccessors().get(i).setHasParent(true);
						current.getSuccessors().get(i).setgCost(tentative_gCost);
						current.getSuccessors().get(i).setfCost(tentative_gCost + current.getSuccessors().get(i).distance(fin));
					}
				}
			}
		}
			return pathVide;
	}
	public static Node trouverPlusPetitCout(ArrayList<Node> listNode){
		Node current = new Node(listNode.get(0));
		for(int i = 0; i<listNode.size(); i++){
			if(current.getfCost()>listNode.get(i).getfCost()){
				current = new Node(listNode.get(i));
			}
		}
		return current;
	}
	public static ArrayList<Node> pathFrom(Node debut){
		ArrayList<Node> path = new ArrayList<Node>();
		path.add(debut);
		int i=0;
		while(path.get(i).hasParent()){
			path.add(path.get(i).getParent());
			i++;
		}
		return path;
	}
}
