package item;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Font;

/**
 * Classe qui gere les items de l'inventaire et leur affichage
 * @author Mihai
 *
 */
public class Inventaire extends JPanel{

	private static final long serialVersionUID = 1L;
	private String nomFichier;
	private URL url = null;
	private JLabel lblItem1, lblItem2, lblItem3, lblItem4, lblItem5, lblItem6;
	private JLabel lblDescitem1, lblDescitem2, lblDescitem3, lblDescitem4, lblDescitem5, lblDescitem6;
	private JPanel panelItem1, panelItem2, panelItem3, panelItem4, panelItem5, panelItem6;
	private JPanel panelDescItem1, panelDescItem2, panelDescItem3, panelDescItem4, panelDescItem5, panelDescItem6;
	private Set<Item> inventaire = new LinkedHashSet<Item>();
	private List<JLabel> listeLabelsItems = new ArrayList<JLabel>();
	private List<JLabel> listeLabelDescriptionItems = new ArrayList<JLabel>();
	private Color couleurUnselected = new Color(255, 165, 0, 140);
	private Color couleurSelected = new Color(240, 10, 10, 140);
	private int pos = 0, posAffichee = -1;
	private boolean firstItem = true;
	private JLabel lblGold;
	private JLabel lblNombreDitems;

	/**
	 * Constructeur de l'inventaire
	 */
	public Inventaire() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		setBackground(new Color(255, 239, 213, 170));
		setLayout(new MigLayout("", "[45.00][450.00]", "[][50.00][50.00][50.00][50.00][50.00][50.00]"));

		lblNombreDitems = new JLabel("Nombre d'items :  0        ");
		lblNombreDitems.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreDitems.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNombreDitems, "flowx,cell 1 0");


		lblGold = new JLabel(" -------  Gold  :  0");
		lblGold.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGold.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGold, "cell 1 0,alignx right");

		panelItem1 = new JPanel();
		add(panelItem1, "cell 0 1,grow");
		panelItem1.setLayout(new GridLayout(0, 1, 0, 0));

		panelDescItem1 = new JPanel();
		add(panelDescItem1, "cell 1 1,grow");
		panelDescItem1.setLayout(new GridLayout(0, 1, 0, 0));

		lblItem1 = new JLabel(); 		
		lblItem1.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem1.setBackground(couleurUnselected);
		lblItem1.setOpaque(true);
		listeLabelsItems.add(lblItem1);
		panelItem1.add(lblItem1);

		lblDescitem1 = new JLabel("");		
		lblDescitem1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescitem1.setBackground(couleurUnselected);
		lblDescitem1.setOpaque(true);
		panelDescItem1.add(lblDescitem1);
		listeLabelDescriptionItems.add(lblDescitem1);

		panelItem2 = new JPanel();
		add(panelItem2, "cell 0 2,grow");
		panelItem2.setLayout(new GridLayout(1, 0, 0, 0));

		lblItem2 = new JLabel();
		lblItem2.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem2.setBackground(couleurUnselected);
		panelItem2.add(lblItem2);
		lblItem2.setOpaque(true);
		listeLabelsItems.add(lblItem2);

		panelItem3 = new JPanel();
		add(panelItem3, "cell 0 3,grow");
		panelItem3.setLayout(new GridLayout(1, 0, 0, 0));

		lblItem3 = new JLabel();
		lblItem3.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem3.setBackground(couleurUnselected);
		panelItem3.add(lblItem3);
		lblItem3.setOpaque(true);
		listeLabelsItems.add(lblItem3);

		panelItem4 = new JPanel();
		add(panelItem4, "cell 0 4,grow");
		panelItem4.setLayout(new GridLayout(1, 0, 0, 0));

		lblItem4 = new JLabel();
		lblItem4.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem4.setBackground(couleurUnselected);
		panelItem4.add(lblItem4);
		lblItem4.setOpaque(true);
		listeLabelsItems.add(lblItem4);

		panelItem5 = new JPanel();
		add(panelItem5, "cell 0 5,grow");
		panelItem5.setLayout(new GridLayout(1, 0, 0, 0));

		lblItem5 = new JLabel();
		lblItem5.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem5.setBackground(couleurUnselected);
		panelItem5.add(lblItem5);
		lblItem5.setOpaque(true);
		listeLabelsItems.add(lblItem5);

		panelItem6 = new JPanel();
		add(panelItem6, "cell 0 6,grow");
		panelItem6.setLayout(new GridLayout(1, 0, 0, 0));

		lblItem6 = new JLabel();
		lblItem6.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem6.setBackground(couleurUnselected);
		panelItem6.add(lblItem6);
		lblItem6.setOpaque(true);
		listeLabelsItems.add(lblItem6);

		panelDescItem2 = new JPanel();
		add(panelDescItem2, "cell 1 2,grow");
		panelDescItem2.setLayout(new GridLayout(1, 0, 0, 0));

		lblDescitem2 = new JLabel("");
		lblDescitem2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescitem2.setBackground(couleurUnselected);
		panelDescItem2.add(lblDescitem2);
		lblDescitem2.setOpaque(true);
		listeLabelDescriptionItems.add(lblDescitem2);

		panelDescItem3 = new JPanel();
		add(panelDescItem3, "cell 1 3,grow");
		panelDescItem3.setLayout(new GridLayout(1, 0, 0, 0));

		lblDescitem3 = new JLabel("");
		lblDescitem3.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescitem3.setBackground(couleurUnselected);
		panelDescItem3.add(lblDescitem3);
		lblDescitem3.setOpaque(true);
		listeLabelDescriptionItems.add(lblDescitem3);

		panelDescItem4 = new JPanel();
		add(panelDescItem4, "cell 1 4,grow");
		panelDescItem4.setLayout(new GridLayout(1, 0, 0, 0));

		lblDescitem4 = new JLabel("");
		lblDescitem4.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescitem4.setBackground(couleurUnselected);
		panelDescItem4.add(lblDescitem4);
		lblDescitem4.setOpaque(true);
		listeLabelDescriptionItems.add(lblDescitem4);

		panelDescItem5 = new JPanel();
		add(panelDescItem5, "cell 1 5,grow");
		panelDescItem5.setLayout(new GridLayout(1, 0, 0, 0));

		lblDescitem5 = new JLabel("");
		lblDescitem5.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescitem5.setBackground(couleurUnselected);
		panelDescItem5.add(lblDescitem5);
		lblDescitem5.setOpaque(true);
		listeLabelDescriptionItems.add(lblDescitem5);

		panelDescItem6 = new JPanel();
		add(panelDescItem6, "cell 1 6,grow");
		panelDescItem6.setLayout(new GridLayout(1, 0, 0, 0));

		lblDescitem6 = new JLabel("");
		lblDescitem6.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescitem6.setBackground(couleurUnselected);
		panelDescItem6.add(lblDescitem6);
		lblDescitem6.setOpaque(true);
		listeLabelDescriptionItems.add(lblDescitem6);
	}
	/**
	 * Ajoute un item a l'inventaire
	 * @param item Le item a ajouter
	 */
	public void addItem(Item item) {
		boolean existeDeja = inventaire.contains(item);
		inventaire.add(item); // ajoute l'item dans la liste
		updateInventaire(); 

		if (firstItem && item.getId() != "28") {			
			listeLabelsItems.get(0).setBackground(couleurSelected);
			listeLabelDescriptionItems.get(0).setBackground(couleurSelected);					
			posAffichee++;
			firstItem = false;
		}	
		else if (!existeDeja && inventaire.size() < 7 && item.getId() != "28") {
			posAffichee++;

			//debut modifcation selection
			listeLabelsItems.get(posAffichee).setBackground(couleurSelected);
			listeLabelDescriptionItems.get(posAffichee).setBackground(couleurSelected);
			listeLabelsItems.get(posAffichee - 1).setBackground(couleurUnselected);
			listeLabelDescriptionItems.get(posAffichee - 1).setBackground(couleurUnselected);
			//fin modifcation selection
		}	
	}
	/**
	 * Parcours l'inventaire vers le bas
	 */
	public void parcourirEnBas() {	
		if (posAffichee == 5 && pos > 0) {					
			pos--;
			updateInventaire();
		}

		int parcoursMax = inventaire.size() < 7 ? inventaire.size() - 1 : 5;
		if (posAffichee < parcoursMax) { 
			posAffichee++;

			//debut modification selection
			listeLabelsItems.get(posAffichee).setBackground(couleurSelected);
			listeLabelDescriptionItems.get(posAffichee).setBackground(couleurSelected);
			listeLabelsItems.get(posAffichee - 1).setBackground(couleurUnselected);
			listeLabelDescriptionItems.get(posAffichee - 1).setBackground(couleurUnselected);
			//fin modification selection
		}
	}
	/**
	 * Parcours l'inventaire vers le haut
	 */
	public void parcourirEnHaut() {
		if (posAffichee == 0 && pos < inventaire.size() - 6) {
			pos++;			
			updateInventaire();			
		}
		if (posAffichee > 0) {
			posAffichee--;

			//debut modification selection
			listeLabelsItems.get(posAffichee).setBackground(couleurSelected);
			listeLabelDescriptionItems.get(posAffichee).setBackground(couleurSelected);
			listeLabelsItems.get(posAffichee + 1).setBackground(couleurUnselected);
			listeLabelDescriptionItems.get(posAffichee + 1).setBackground(couleurUnselected);
			//fin modifcation selection
		} 
	}
	/**
	 * Envoye le item selectionne
	 * @return item Le item selectionne
	 */
	private Item getSelectedItem() {
		Item item = null;
		Iterator<Item> iterateur = inventaire.iterator();
		int length = inventaire.size() > 6 ? (6 - posAffichee + pos) : (inventaire.size() - posAffichee);
		for(int i = 0; i < length; i++) 
			if(iterateur.hasNext())
				item = iterateur.next();
		return item;
	}
	/**
	 * Envoye le item que le joueur va porter et place le item du meme type que le joueur portait avant dans l'inventaire
	 * @param itemList La liste des items portes actuellement par le joueur
	 * @return itemToEquip Le item que le joueur va porter
	 */
	public Item equipItem(List<Item> itemList) {
		if (getSelectedItem() == null)
			return null;

		Item itemToEquip = getSelectedItem(); //le item que le joueur va porter
		int type = getItemType(itemToEquip); //le type du item a porter

		if (type == 2) {
			sellItem();
			return itemToEquip;
		}		
		Item itemToInventory = itemList.get(type); //le item a mettre dans l'inventaire

		boolean existeDeja = inventaire.contains(itemToInventory);

		Object[] tempInv = inventaire.toArray(); //convertit le set en array
		List<Item> tempInvList = new ArrayList<Item>(); 
		for (int i = 0; i < tempInv.length; i++)  //convertit le array en arraylist
			tempInvList.add((Item)tempInv[i]);

		int index = tempInvList.indexOf(itemToEquip); //get le index du item selectionne
		tempInvList.remove(index); //enleve le item selectionne
		tempInvList.add(index, itemToInventory); //ajoute l'item que le joueur etait entrain de porter dans l'inventaire


		inventaire.clear(); //enleve tous les elements du set
		for(int i = 0; i < tempInvList.size(); i++)  //ajoute les elements du arraylist dans le set
			inventaire.add(tempInvList.get(i));	

		//verifie si le item a ajouter n'est pas deja dessine dans l'inventaire
		if (!existeDeja) {
			//dessine le item dans l'inventaire et la description
			listeLabelsItems.get(posAffichee).setIcon(ouvrirImage(itemToInventory.getId()));
			listeLabelDescriptionItems.get(posAffichee).setText(itemToInventory.toString());
		} else 
			updateInventaire();

		return itemToEquip; //envoye l'item que le joueur va porter
	}	
	/**
	 * Envoye le type de l'item. ex: epee, bouclier, etc
	 * @param item On cherche le type de ce item
	 * @return Le type
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
	/**
	 * Envoye le item a vendre et l'enleve de l'inventaire
	 * @return item Le item a vendre
	 */
	public Item sellItem() {
		if (getSelectedItem() == null) 
			return null;
		Item item = getSelectedItem();
		inventaire.remove(item);
		if (inventaire.size() < 6) {			
			if (inventaire.isEmpty()) {
				posAffichee--;
				firstItem = true;				
				listeLabelsItems.get(0).setBackground(couleurUnselected);
				listeLabelDescriptionItems.get(0).setBackground(couleurUnselected);
			} else if (posAffichee > 0){	
				posAffichee--;
				//debut modification selection
				listeLabelsItems.get(posAffichee).setBackground(couleurSelected);
				listeLabelDescriptionItems.get(posAffichee).setBackground(couleurSelected);
				listeLabelsItems.get(posAffichee + 1).setBackground(couleurUnselected);
				listeLabelDescriptionItems.get(posAffichee + 1).setBackground(couleurUnselected);
				//fin modifcation selection
			}			
		} else if (pos > 0) pos--;	

		updateInventaire();

		return item;
	}
	/**
	 * Selection la case choisi
	 * @param i La case choisi
	 */
	public void setSelected(int i) {
		posAffichee = i;
		listeLabelsItems.get(i).setBackground(couleurSelected);
		listeLabelDescriptionItems.get(i).setBackground(couleurSelected);
	}
	/**
	 * Deselectionne la premiere case et garde la posAffiche comme elle etait
	 * @param posAffichee La position affichee
	 */
	public void setFirstUnselected(int posAffichee) {	
		if (inventaire.size() < 8) {
			this.posAffichee = posAffichee;
			if (inventaire.size() < 7) this.posAffichee++;
			for (JLabel ite : listeLabelsItems) 
				ite.setBackground(couleurUnselected);
			for (JLabel desc : listeLabelDescriptionItems)
				desc.setBackground(couleurUnselected);
			listeLabelsItems.get(this.posAffichee).setBackground(couleurSelected);
			listeLabelDescriptionItems.get(this.posAffichee).setBackground(couleurSelected);
		}
	}
	/**
	 * Envoye la position affiche actuelle
	 * @return posAffiche La position affichee
	 */
	public int getPosAffiche() {
		return posAffichee;
	}
	/**
	 * Met a jour l'inventaire en redessinant les 6 items qui sont visibles
	 */
	public void updateInventaire() {
		Iterator<Item> iterateur = inventaire.iterator();
		Item item = null;

		//reset les labels avant de redessiner
		listeLabelsItems.forEach(label -> label.setIcon(null));
		listeLabelDescriptionItems.forEach(label -> label.setText(""));

		for (int i = 0; i < pos; i++)
			if (iterateur.hasNext())
				iterateur.next();
		int debut = inventaire.size() > 6 ? (listeLabelsItems.size() - 1) : (inventaire.size() - 1); 
		for (int i = debut; i >= 0; i--) {
			//get le item
			if (iterateur.hasNext()) item = iterateur.next();
			else item = null;

			//dessine le item dans l'inventaire
			if (item == null) {
				listeLabelsItems.get(i).setIcon(null);
				listeLabelDescriptionItems.get(i).setText("");
			} else {
				listeLabelsItems.get(i).setIcon(ouvrirImage(item.getId()));
				listeLabelDescriptionItems.get(i).setText(item.toString());
			}
		} 
		updateInventorySize();
	}
	/**
	 * Envoye la liste d'items du inventaire
	 * @return inventaire La liste d'items
	 */
	public Set<Item> getItemsDuInventaire() {
		return inventaire;
	}
	/**
	 * Definit l'or dans l'inventaire
	 * @param gold L'or
	 */
	public void setGold(long gold) {
		String goldText = Long.toString(gold);
		StringBuilder sb = new StringBuilder(goldText);
		int nbZeros = (6 - goldText.length());

		for (int i = 0; i < nbZeros; i++) 
			sb.insert(0, "0");
		goldText = sb.toString();

		lblGold.setText("       Gold  :  " + goldText);
	}
	private void updateInventorySize() {
		lblNombreDitems.setText("Nombre d'items : " + inventaire.size() + "       ");
	}
	/**
	 * Ouvre et envoye l'image d'un item 
	 * @param nom Le nom de l'item 
	 * @return image L'image de l'item
	 */
	private ImageIcon ouvrirImage(String nom) {
		ImageIcon image = null;
		nomFichier = "item_"+nom+".png";
		url = getClass().getClassLoader().getResource(nomFichier);

		try {
			image = new ImageIcon(ImageIO.read(url));
		} 
		catch (IOException e) {
			System.out.println("IOException lors de la lecture avec ImageIO");			
		}
		return image;
	}
}
