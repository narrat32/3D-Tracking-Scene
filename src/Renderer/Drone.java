package Renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class Drone {
	
	public static float x, y, z;
	//private float vx, vy, vz;
	
	private float height;
	private float radius;
	
	private DroneComponent root;
	
	public Drone() {
		radius = 0.5f;
		height = 0.25f;
		
		root = new DroneBody(radius, height);
		
		drawTopRightRotor();
	}
	
	private void drawTopRightRotor() {
		Rotor topRightRotor = new Rotor(radius, height);
		topRightRotor.setTranslations(radius,  height, (radius * 2) * 0.1);
		root.addChild(topRightRotor);
	}
	
	private void drawTopLeftRotor() {
		Rotor topLeftRotor = new Rotor(radius, height);
		topLeftRotor.setTranslations(radius,  height, (radius * 2) * 0.1);
		root.addChild(topLeftRotor);
	}
	
	private void drawBottomRightRotor() {
		Rotor bottomRightRotor = new Rotor(radius, height);
		bottomRightRotor.setTranslations(radius, height, (radius * 2) * 0.1);
		root.addChild(bottomRightRotor);
	}
	
	private void drawBottomLeftRotor() {
		Rotor bottomLeftRotor = new Rotor(radius, height);
		bottomLeftRotor.setTranslations(radius,  height, (radius * 2) * 0.1);
		root.addChild(bottomLeftRotor);
	}
	
	public void draw(GL2 gl, GLU glu, GLUquadric quadric) {
		gl.glPushMatrix();
		gl.glTranslated(x, y, z);
		gl.glColor3d(1, 1, 1);
		root.draw(gl, glu, quadric);
		gl.glPopMatrix();
	}
	
	public class DroneBody extends DroneComponent{
		DroneBody(double radius, double height){
			super(radius, height);
		}
		public void drawNode(GL2 gl, GLU glu, GLUquadric quadric) {
			gl.glPushMatrix();
			gl.glScaled(radius,  height,  height);
			glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
			glu.gluCylinder(quadric, 1, 1, 3, 30, 30);
			gl.glPopMatrix();
		}
	}
	
	public class Rotor extends DroneComponent{
		public Rotor(double radius, double height) {
			super(radius, height);
		}
		public void drawNode(GL2 gl, GLU glu, GLUquadric quadric) {
			gl.glPushMatrix();
			gl.glColor3f(0, 0, 0);
			
			gl.glPopMatrix();
		}
	}
	
	/*public void animate(float speed) {
		x += vx * speed;
		y += vy * speed;
		z += vz * speed;
	}*/
}
