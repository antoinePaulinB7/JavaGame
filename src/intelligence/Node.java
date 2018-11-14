package intelligence;

import java.util.ArrayList;
/**
 * Objet utilisé dans le pathfinding
 * @author Olivier
 *
 */
public class Node {
	private float gCost, fCost, hCost;
	private int x, y;
	private boolean vide, hasParent;
	private ArrayList<Node> successors = new ArrayList<Node>();
	private Node parent;
	
	/**
	 * Constructeur
	 * @param x Position en x
	 * @param y Position en y
	 * @param vide Si la node est vide ou non
	 */
	public Node(int x, int y, boolean vide){
		this.x = x;
		this.y = y;
		this.vide = vide;
		this.gCost = 1000000;
		this.fCost = 1000000;
	}
	/**
	 * Constructeur vide
	 */
	public Node() { } 
	/**
	 * Constructeur d'un node
	 * @param node La node à copier
	 */
	public Node(Node node){
		this.x = node.getX();
		this.y = node.getY();
		this.gCost = node.getgCost();
		this.hCost = node.gethCost();
		this.fCost = node.getfCost();
		this.vide = node.isVide();
		this.successors = node.getSuccessors();
		this.vide = node.isVide();
		this.hasParent = node.hasParent();
		this.parent = node.getParent();
	}
	/**
	 * Envoye le hash code d'un node
	 * @return result Le hash code
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	/**
	 * La méthode equals adapté pour les nodes
	 * @param node La node à vérifier
	 * @return True si les 2 nodes sont pareils, sinon false
	 */
	public boolean equals(Node node) {
		if(node.getX()==this.x && node.getY()==this.y){
			return true;
		}
		return false;
	}
	/**
	 * Getter pour gCost
	 * @return gCost 
	 */
	public float getgCost() {
		return gCost;
	}
	/**
	 * Setter pour gCost
	 * @param gCost
	 */
	public void setgCost(float gCost) {
		this.gCost = gCost;
	}
	/**
	 * Getter pour fCost
	 * @return fCost
	 */
	public float getfCost() {
		return fCost;
	}
	/**
	 * Setter pour fCost
	 * @param fCost 
	 */
	public void setfCost(float fCost) {
		this.fCost = fCost;
	}
	/**
	 * Getter pour hCost
	 * @return hCost
	 */
	public float gethCost() {
		return hCost;
	}
	/**
	 * Setter pour hCost
	 * @param hCost
	 */
	public void sethCost(float hCost) {
		this.hCost = hCost;
	}
	/**
	 * Getter pour successors
	 * @return successors
	 */
	public ArrayList<Node> getSuccessors() {
		return successors;
	}
	/**
	 * Setter pour successors
	 * @param successors
	 */
	public void setSuccessors(ArrayList<Node> successors) {
		this.successors = successors;
	}
	/**
	 * Méthode qui rajoute une node a la liste de successors
	 * @param successor
	 */
	public void addSuccessor(Node successor){
		this.successors.add(successor);
	}
	/**
	 * Calcule la distance entre deux nodes
	 * @param node La node avec laquelle calculer la distance
	 * @return La distance 
	 */
	public float distance(Node node) {
		float distanceX = Math.abs(node.getX() - this.x);
		float distanceY = Math.abs(node.getY() - this.y);
		float distance = (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		return distance;
	}
	/**
	 * Getter pour x
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setter pour x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter pour y
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter pour y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Méthode qui vérifie si la node est vide ou non
	 * @return true si elle est vide, false sinon
	 */
	public boolean isVide(){
		return vide;
	}
	/**
	 * Getter pour parent
	 * @return parent
	 */
	public Node getParent() {
		return parent;
	}
	 /**
	  * Setter pour parent
	  * @param parent
	  */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	/**
	 * Méthode qui vérifie si la node a un parent
	 * @return true si elle a un parent, false sinon
	 */
	public boolean hasParent(){
		return hasParent;
	}
	/**
	 * Setter pour hasParent
	 * @param hasParent
	 */
	public void setHasParent(boolean hasParent){
		this.hasParent = hasParent;
	}
	/**
	 * Méthode equals()
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
