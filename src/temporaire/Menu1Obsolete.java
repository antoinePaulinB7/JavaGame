package temporaire;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Classe qui gere la creation des menus
 * @author Olivier
 *
 */

public class Menu1Obsolete extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creation du panel menu1
	 */
	public Menu1Obsolete() {

		
		setLayout(null);
		setBounds(0, 0, 850, 550);
		setBackground(new Color(178, 34, 34));

		
		JLabel lblAide = new JLabel("Aide");
		lblAide.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 41));
		lblAide.setHorizontalAlignment(SwingConstants.CENTER);
		lblAide.setBounds(155, 72, 540, 51);
		add(lblAide);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnRetour.setBounds(50, 420, 135, 51);
		add(btnRetour);
		
		JButton btnContinuer = new JButton("Continuer");
		btnContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnContinuer.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnContinuer.setBounds(665, 420, 135, 51);
		add(btnContinuer);
		
		
		String[] nomList = { "Swag", "swag2", "swag4"};
		JList<String> list  = new JList<String>(nomList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			}
		});
		JScrollPane listScroll = new JScrollPane(list);
		listScroll.setBounds(213, 198, 423, 153);
		add(listScroll);
	}
}
