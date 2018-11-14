package menus;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
/**
 * Le frame qui affiche le panel d'aide
 * @author Mihai
 *
 */
public class FrameAide extends JFrame {
	private static final long serialVersionUID = 1L;
	private PanelAide panelAide;
	
	/**
	 * Constructeur du frame aide
	 */
	public FrameAide() {
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		panelAide = new PanelAide();
		panelAide.setSize(new Dimension(500,500));
		getContentPane().add(panelAide);
		
	}
}
