package editeur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
/**
 * JFrame de l'editeur de tableaux
 * @author Antoine
 *
 */
public class EditeurTableaux extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panel_1, panel_2, panel_3;
	private PanelEditeurTableaux edit;
	private JRadioButton rdbtnPinceau, rdbtnEfface;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNomDuFichier;
	private JTextField textNomFichier;
	private JButton btnToutPeindre, btnToutEffacer, btnToutInverser, btnEnregistrer;
	/**
	 * Demarre l'editeur de tableaux
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditeurTableaux frame = new EditeurTableaux();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creation de l'editeur de tableaux
	 */
	public EditeurTableaux() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		edit = new PanelEditeurTableaux();
		edit.setBounds(5, 5, 500, 500);
		edit.setFocusable(true);
		contentPane.add(edit);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pinceaux", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(515, 5, 159, 82);
		contentPane.add(panel);
		panel.setLayout(null);

		rdbtnPinceau = new JRadioButton("Pinceau");
		rdbtnPinceau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edit.setPinceau(true);
			}
		});
		buttonGroup.add(rdbtnPinceau);
		rdbtnPinceau.setBounds(25, 17, 109, 23);
		panel.add(rdbtnPinceau);

		rdbtnEfface = new JRadioButton("Efface");
		rdbtnEfface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit.setPinceau(false);
			}
		});
		buttonGroup.add(rdbtnEfface);
		rdbtnEfface.setBounds(25, 43, 109, 23);
		panel.add(rdbtnEfface);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Enregistrement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(515, 262, 159, 108);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNomDuFichier = new JLabel("Nom du fichier");
		lblNomDuFichier.setBounds(10, 24, 139, 14);
		panel_1.add(lblNomDuFichier);

		textNomFichier = new JTextField();
		textNomFichier.setBounds(10, 39, 139, 20);
		panel_1.add(textNomFichier);
		textNomFichier.setColumns(10);

		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNomFichier.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Entrez un nom pour le fichier");
				}else{
					ArrayList<Carre>listeCarres = edit.getListeCarres();
					String ligne = "";
					try {
						PrintWriter writer = new PrintWriter(textNomFichier.getText()+".txt");
						for(int k = 0; k<listeCarres.size();k++){
							if((k)%50==0&&k>1){
								System.out.println(ligne);
								writer.println(ligne);
								ligne = "";
							}
							if(!(k%50==0))ligne+=" ";
							System.out.print(listeCarres.get(k).isSelected());
							if(listeCarres.get(k).isSelected())ligne+="1";
							if(!listeCarres.get(k).isSelected())ligne+="0";

						}
						writer.println(ligne);
						writer.close();
						JOptionPane.showMessageDialog(null, textNomFichier.getText()+".txt enregistré.");
					} catch (FileNotFoundException e1) {						
						e1.printStackTrace();
					}

				}
			}
		});
		btnEnregistrer.setBounds(37, 70, 112, 23);
		panel_1.add(btnEnregistrer);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Simulation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(515, 381, 159, 119);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Effets instantan\u00E9s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(515, 98, 159, 153);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		btnToutPeindre = new JButton("Tout peindre");
		btnToutPeindre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit.getListeCarres().forEach(carres -> carres.setSelected(true));
				repaint();
			}
		});
		btnToutPeindre.setBounds(44, 40, 105, 23);
		panel_3.add(btnToutPeindre);
		
		btnToutEffacer = new JButton("Tout effacer");
		btnToutEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edit.getListeCarres().forEach(carres -> carres.setSelected(false));
				repaint();
			}
		});
		btnToutEffacer.setBounds(44, 74, 105, 23);
		panel_3.add(btnToutEffacer);
		
		btnToutInverser = new JButton("Tout inverser");
		btnToutInverser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edit.getListeCarres().forEach(carres -> carres.setSelected(!carres.isSelected()));
				repaint();
			}
		});
		btnToutInverser.setBounds(44, 108, 105, 23);
		panel_3.add(btnToutInverser);
	}
}
