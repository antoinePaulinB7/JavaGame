package tests;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrameTestEntitePhys extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnHaut;
	private JButton btnBas;
	private JButton btnGauche;
	private JButton btnDroite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTestEntitePhys frame = new FrameTestEntitePhys();
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
	public FrameTestEntitePhys() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		PanelTestEntitePhys panel1 = new PanelTestEntitePhys();
		panel1.setBounds(5, 5, 400, 251);
		this.contentPane.add(panel1);
		panel1.setLayout(null);
		panel1.setFocusable(true);
		
		this.panel = new JPanel();
		this.panel.setBounds(415, 5, 159, 245);
		this.contentPane.add(this.panel);
		this.panel.setLayout(null);
		
		this.btnNewButton = new JButton("Demarrer");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel1.demarrer();
			}
		});
		this.btnNewButton.setBounds(35, 211, 89, 23);
		this.panel.add(this.btnNewButton);
		
		this.btnHaut = new JButton("Haut");
		this.btnHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.haut = !(panel1.haut);
			}
		});
		this.btnHaut.setBounds(35, 11, 89, 23);
		this.panel.add(this.btnHaut);
		
		this.btnBas = new JButton("Bas");
		this.btnBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.bas=!(panel1.bas);
			}
		});
		this.btnBas.setBounds(35, 80, 89, 23);
		this.panel.add(this.btnBas);
		
		this.btnGauche = new JButton("Gauche");
		this.btnGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnGauche.setBounds(10, 45, 62, 23);
		this.panel.add(this.btnGauche);
		
		this.btnDroite = new JButton("Droite");
		this.btnDroite.setBounds(87, 45, 62, 23);
		this.panel.add(this.btnDroite);
	}
}
