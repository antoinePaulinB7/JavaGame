package tests;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TestKeyBindings extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAttack;
	private JButton btnNewButton;
	private JButton boutonActuel;
	private JButton btnNewButton_1;
	private int numJoueur = 0;
	private int action;
	private int keyJoueurs[][] = {{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, 
		KeyEvent.VK_A, KeyEvent.VK_V, KeyEvent.VK_B},
			//joueur 2
			{KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, 
			KeyEvent.VK_LEFT, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2}};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestKeyBindings frame = new TestKeyBindings();
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
	public TestKeyBindings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setFocusable(true);
		contentPane.requestFocus();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				boutonActuel.setText((char)e.getKeyCode()+"");
				lblAttack.requestFocus();
				keyJoueurs[numJoueur][action] = e.getKeyCode(); 
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAttack = new JLabel("Attack");
		lblAttack.setBounds(38, 44, 83, 38);
		contentPane.add(lblAttack);
		
		btnNewButton = new JButton("a");
		btnNewButton.setToolTipText("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.requestFocus();
				boutonActuel = btnNewButton;
				action = 3;
			}
		});
		btnNewButton.setBounds(131, 44, 104, 38);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
