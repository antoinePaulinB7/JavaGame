package tests;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entites.Sprite;
import interfaces.Enumerations.Etat;

public class testSprite extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private ArrayList<Sprite> listeSprite;
	private Sprite viking;
	private Sprite cowboy;
	private Sprite goblin;
	private Sprite leprechaun;
	private Sprite thief;
	private Sprite golem;
	private JButton btnSaut;
	private JButton btnDemarrer;
	private JButton btnPause;
	private JButton btnImmobile;
	private JButton btnMouvement;
	private JButton btnAttaque;
	private int k = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testSprite frame = new testSprite();
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
	public testSprite() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 816);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		contentPane.setLayout(null);
		
		viking = new Sprite("viking");
		viking.setBounds(10*k,16*k,16*k,16*k);
		contentPane.add(viking);
		
		cowboy = new Sprite("cowboy");
		cowboy.setBounds(26*k,16*k,16*k,16*k);
		contentPane.add(cowboy);
		
		goblin = new Sprite("goblin");
		goblin.setBounds(42*k,16*k,16*k,16*k);
		contentPane.add(goblin);
		
		leprechaun = new Sprite("leprechaun");
		leprechaun.setBounds(58*k,16*k,16*k,16*k);
		contentPane.add(leprechaun);
		
		thief = new Sprite("thief");
		thief.setBounds(74*k,16*k,16*k,16*k);
		contentPane.add(thief);
		
		golem = new Sprite("golem");
		golem.setBounds(90*k,0*k,32*k,32*k);
		contentPane.add(golem);
		
		btnDemarrer = new JButton("Demarrer");
		btnDemarrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viking.demarrer();
				cowboy.demarrer();
				goblin.demarrer();
				leprechaun.demarrer();
				thief.demarrer();
				golem.demarrer();
			}
		});
		btnDemarrer.setBounds(668, 22, 89, 23);
		contentPane.add(btnDemarrer);
		
		btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viking.pause();
				cowboy.pause();
				goblin.pause();
				leprechaun.pause();
				thief.pause();
				golem.pause();
			}
		});
		btnPause.setBounds(668, 56, 89, 23);
		contentPane.add(btnPause);
		
		btnImmobile = new JButton("Immobile");
		btnImmobile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viking.setEtat(Etat.IMMOBILE);
				cowboy.setEtat(Etat.IMMOBILE);
				goblin.setEtat(Etat.IMMOBILE);
				leprechaun.setEtat(Etat.IMMOBILE);
				thief.setEtat(Etat.IMMOBILE);
				golem.setEtat(Etat.IMMOBILE);
			}
		});
		btnImmobile.setBounds(668, 90, 89, 23);
		contentPane.add(btnImmobile);
		
		btnMouvement = new JButton("Mouvement");
		btnMouvement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viking.setEtat(Etat.MOUVEMENT);
				cowboy.setEtat(Etat.MOUVEMENT);
				goblin.setEtat(Etat.MOUVEMENT);
				leprechaun.setEtat(Etat.MOUVEMENT);
				thief.setEtat(Etat.MOUVEMENT);
				golem.setEtat(Etat.MOUVEMENT);
			}
		});
		btnMouvement.setBounds(668, 124, 89, 23);
		contentPane.add(btnMouvement);
		
		btnSaut = new JButton("Saut");
		btnSaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viking.setEtat(Etat.SAUT);
				cowboy.setEtat(Etat.SAUT);
				goblin.setEtat(Etat.SAUT);
				leprechaun.setEtat(Etat.SAUT);
				thief.setEtat(Etat.SAUT);
			}
		});
		btnSaut.setBounds(668, 158, 89, 23);
		contentPane.add(btnSaut);
		
		btnAttaque = new JButton("Attaque");
		btnAttaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				golem.setEtat(Etat.ATTAQUE_CHARGE);
				viking.setEtat(Etat.ATTAQUE_CHARGE);
			}
		});
		btnAttaque.setBounds(668, 192, 89, 23);
		contentPane.add(btnAttaque);
		
		
	}
}
