package tests;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_2;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
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

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.glfw.GLFWVidMode;

public class TestController {

	public static void main(String[] args) {
	
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

		ByteBuffer boutons = glfwGetJoystickButtons(GLFW_JOYSTICK_1);
		//glfwShowWindow(window);

		while(!glfwWindowShouldClose(window)){
			glfwPollEvents();
			//System.out.println((glfwGetKey(window, GLFW_KEY_V)));


			//System.out.println(glfwGetJoystickName(GLFW_JOYSTICK_1)+"");
			FloatBuffer sticks = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
	
			//System.out.println(sticks.get(5));
			System.out.println(sticks.get(1));

			
			//System.out.println(boutons.get(13));
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
		if (boutons.get(0) == 1) glfwDestroyWindow(window);
		glfwTerminate();
	}
	
}
