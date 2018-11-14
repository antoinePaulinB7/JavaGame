package tests;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import procedural.Seed;

public class TestSeed extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private Seed seed = new Seed(0);
	private int x = 0, y = 0;
	private PanelSeedTest test;
	private JButton btnDmarrer;
	private JSlider slider;
	private JCheckBox chckbxHauteur;
	private JCheckBox chckbxDanger;
	private JCheckBox chckbxBiome;
	private JCheckBox chckbxAxes;
	private JCheckBox chckbxLoot;
	private JCheckBox chckbxPlateformes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSeed frame = new TestSeed();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestSeed() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 1015);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHaut = new JButton("Haut");
		btnHaut.setEnabled(false);
		btnHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				y++;
				seed.calculerParametres(x, y);
				lblNewLabel.setText(seed.getCompteur()+"");
				System.out.println("Monter, "+seed);
				test.setVertical(test.getVertical()-5);
				test.setSeed(seed);
			}
		});
		btnHaut.setBounds(1175, 178, 89, 23);
		contentPane.add(btnHaut);
		
		JButton btnBas = new JButton("Bas");
		btnBas.setEnabled(false);
		btnBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				y--;
				seed.calculerParametres(x, y);
				lblNewLabel.setText(seed.getCompteur()+"");
				System.out.println("Descendre, "+seed);
				test.setVertical(test.getVertical()+5);
				test.setSeed(seed);
			}
		});
		btnBas.setBounds(1175, 239, 89, 23);
		contentPane.add(btnBas);
		
		JButton btnGauche = new JButton("Gauche");
		btnGauche.setEnabled(false);
		btnGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x--;
				seed.calculerParametres(x, y);
				lblNewLabel.setText(seed.getCompteur()+"");
				System.out.println("Aller à gauche, "+seed);
				test.setHorizontal(test.getHorizontal()+5);
				test.setSeed(seed);
			}
		});
		btnGauche.setBounds(1121, 208, 89, 23);
		contentPane.add(btnGauche);
		
		JButton btnDroite = new JButton("Droite");
		btnDroite.setEnabled(false);
		btnDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x++;
				seed.calculerParametres(x, y);
				lblNewLabel.setText(seed.getCompteur()+"");
				System.out.println("Aller à droite, "+seed);
				test.setHorizontal(test.getHorizontal()-5);
				test.setSeed(seed);
			}
		});
		btnDroite.setBounds(1220, 208, 89, 23);
		contentPane.add(btnDroite);
		
		JLabel lblSeed = new JLabel("Seed");
		lblSeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeed.setBounds(1121, 93, 46, 14);
		contentPane.add(lblSeed);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seed = new Seed(textField.getText());
				test.setSeed(seed);
				lblNewLabel.setText(textField.getText());
				x = 0;
				y = 0;
				System.out.println("Seed initialisé, "+seed);
				btnDmarrer.setEnabled(true);
			}
		});
		textField.setBounds(1175, 90, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSeedCalcul = new JLabel("Seed calcul\u00E9");
		lblSeedCalcul.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeedCalcul.setBounds(1081, 137, 86, 14);
		contentPane.add(lblSeedCalcul);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(1175, 137, 89, 14);
		contentPane.add(lblNewLabel);
		
		btnDmarrer = new JButton("D\u00E9marrer");
		btnDmarrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnHaut.setEnabled(true);
				btnBas.setEnabled(true);
				btnGauche.setEnabled(true);
				btnDroite.setEnabled(true);
				test.demarrer();
			}
		});
		btnDmarrer.setEnabled(false);
		btnDmarrer.setBounds(1175, 287, 89, 23);
		contentPane.add(btnDmarrer);
		
		test = new PanelSeedTest(seed);
		test.setBounds(0, 0, 1000, 1000);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				test.setHauteur(slider.getValue());
				test.setLargeur(slider.getValue());
				repaint();
			}
		});
		slider.setMinimum(20);
		slider.setMaximum(450);
		slider.setBounds(1081, 345, 200, 26);
		contentPane.add(slider);
		
		
		contentPane.add(test);
		
		chckbxHauteur = new JCheckBox("Hauteur");
		chckbxHauteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				chckbxDanger.setSelected(false);
				chckbxBiome.setSelected(false);
				chckbxLoot.setSelected(false);
				chckbxPlateformes.setSelected(false);
				
				test.setLoot(false);
				test.setBiome(false);
				test.setDanger(false);
				test.setHauteurBool(true);
				test.setDispoPlateformes(false);
				
				repaint();
			}
		});
		chckbxHauteur.setBounds(1167, 400, 97, 23);
		contentPane.add(chckbxHauteur);
		
		chckbxDanger = new JCheckBox("Danger");
		chckbxDanger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				chckbxHauteur.setSelected(false);
				chckbxBiome.setSelected(false);
				chckbxLoot.setSelected(false);
				chckbxPlateformes.setSelected(false);
				
				test.setLoot(false);
				test.setBiome(false);
				test.setDanger(true);
				test.setHauteurBool(false);
				test.setDispoPlateformes(false);
				
				repaint();
			}
		});
		chckbxDanger.setBounds(1167, 434, 97, 23);
		contentPane.add(chckbxDanger);
		
		chckbxBiome = new JCheckBox("Biome");
		chckbxBiome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				chckbxHauteur.setSelected(false);
				chckbxDanger.setSelected(false);
				chckbxLoot.setSelected(false);
				chckbxPlateformes.setSelected(false);
				
				test.setLoot(false);
				test.setBiome(true);
				test.setDanger(false);
				test.setHauteurBool(false);
				test.setDispoPlateformes(false);
				
				repaint();
			}
		});
		chckbxBiome.setBounds(1167, 472, 97, 23);
		contentPane.add(chckbxBiome);
		
		chckbxAxes = new JCheckBox("Axes");
		chckbxAxes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				test.setAxesBool(!test.isAxes());
				repaint();
			}
		});
		chckbxAxes.setBounds(1167, 589, 97, 23);
		contentPane.add(chckbxAxes);
		
		chckbxLoot = new JCheckBox("Loot");
		chckbxLoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				chckbxHauteur.setSelected(false);
				chckbxDanger.setSelected(false);
				chckbxBiome.setSelected(false);
				
				test.setLoot(true);
				test.setBiome(false);
				test.setDanger(false);
				test.setHauteurBool(false);
				
				repaint();
			}
		});
		chckbxLoot.setBounds(1167, 549, 97, 23);
		contentPane.add(chckbxLoot);
		
		chckbxPlateformes = new JCheckBox("Disposition des plateformes");
		chckbxPlateformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cliqué");
				
				chckbxHauteur.setSelected(false);
				chckbxDanger.setSelected(false);
				chckbxBiome.setSelected(false);
				
				test.setLoot(false);
				test.setBiome(false);
				test.setDanger(false);
				test.setHauteurBool(false);
				test.setDispoPlateformes(true);
				
				repaint();
			}
		});
		chckbxPlateformes.setBounds(1167, 510, 193, 23);
		contentPane.add(chckbxPlateformes);
		
	}
}
