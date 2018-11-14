package tests;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_2;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickAxes;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickButtons;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickName;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.lwjgl.glfw.GLFWVidMode;

import bindings.BindingsManette;

public class TestBindingController extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAtk;
	private JButton btnY;
	private JButton boutonActuel;
	private boolean modif = false;
	private boolean joystick = true;
	private BindingsManette bm = new BindingsManette(true);
	private int action;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestBindingController frame = new TestBindingController();
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
	public TestBindingController() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAtk = new JLabel("Attack");
		lblAtk.setBounds(53, 83, 80, 44);
		contentPane.add(lblAtk);

		btnY = new JButton("Y");
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boutonActuel = btnY;
				modif = true;
				action = 3;
			}
		});
		btnY.setBounds(143, 83, 148, 44);
		contentPane.add(btnY);

		runControllers();

	}
	private void runControllers() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				if(!glfwInit()){
					throw new IllegalStateException("Failed to initialize GLFW!");
				}
				glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
				long window = glfwCreateWindow(640, 480, "Test manettes", 0, 0);

				if(window == 0){
					throw new IllegalStateException("Failed to create window!");
				}

				GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
				glfwSetWindowPos(window, (videoMode.width()-640)/2,(videoMode.height()-480)/2);


				//glfwShowWindow(window);

				while(!glfwWindowShouldClose(window)){
					glfwPollEvents();
					//System.out.println((glfwGetKey(window, GLFW_KEY_V)));


					//System.out.println(glfwGetJoystickName(GLFW_JOYSTICK_1)+"");
					FloatBuffer s = glfwGetJoystickAxes(GLFW_JOYSTICK_1);

					//System.out.println(sticks.get(5));
					//System.out.println(sticks.get(1));

					ByteBuffer b = glfwGetJoystickButtons(GLFW_JOYSTICK_1);
					if (boutonActuel != null && modif) {
						if (b.get(0) == 1) {
							boutonActuel.setText("A");		
							modif = false;
							bm.addBinding(action, false, 0);
						} else if (b.get(1) == 1) {
							boutonActuel.setText("B");		
							modif = false;
							bm.addBinding(action, false, 1);
						} else if (b.get(2) == 1) {
							boutonActuel.setText("X");
							modif = false;
							bm.addBinding(action, false, 2);
						} else if (b.get(3) == 1) {
							boutonActuel.setText("Y");
							modif = false;
							bm.addBinding(action, false, 3);
						} else if (b.get(4) == 1) {
							boutonActuel.setText("Bouton gauche");
							modif = false;
							bm.addBinding(action, false, 4);
						} else if (b.get(5) == 1) {
							boutonActuel.setText("Bouton droite");
							modif = false;
							bm.addBinding(action, false, 5);
						} else if (b.get(8) == 1) {
							boutonActuel.setText("Left joystick push");
							modif = false;
							bm.addBinding(action, false, 8);
						} else if (b.get(9) == 1) {
							boutonActuel.setText("Right joystick push");
							modif = false;
							bm.addBinding(action, false, 9);
						} else if (b.get(10) == 1 && joystick) {
							boutonActuel.setText("Fleche haut");
							modif = false;
							bm.addBinding(action, false, 10);
						} else if (b.get(11) == 1 && joystick) {
							boutonActuel.setText("Fleche droite");
							modif = false;
							bm.addBinding(action, false, 11);
						} else if (b.get(12) == 1 && joystick) {
							boutonActuel.setText("Fleche bas");
							modif = false;
							bm.addBinding(action, false, 12);
						} else if (b.get(13) == 1 && joystick) {
							boutonActuel.setText("Fleche gauche");
							modif = false;
							bm.addBinding(action, false, 13);
						} else if (s.get(4) == 1 ) {
							boutonActuel.setText("Trigger gauche");
							modif = false;
							bm.addBinding(action, true, 4);
						} else if (s.get(5) == 1) {
							boutonActuel.setText("Trigger droit");
							modif = false; 
							bm.addBinding(action, true, 5);
						}
						
						
						
							
						
						
					}

					//System.out.println(boutons.get(7));
					//System.out.println(boutons.hasArray());

					String controller2 = glfwGetJoystickName(GLFW_JOYSTICK_2);
					//System.out.println(controller2);
					if(controller2 != null){
						FloatBuffer sticks2 = glfwGetJoystickAxes(GLFW_JOYSTICK_2);
						System.out.println(sticks2.get(0));
						System.out.println(sticks2.get(1));

						ByteBuffer boutons2 = glfwGetJoystickButtons(GLFW_JOYSTICK_2);
						System.out.println(boutons2.getInt(0));
					}

				}

				glfwTerminate();
			}

		}); t.start();

	}

}
