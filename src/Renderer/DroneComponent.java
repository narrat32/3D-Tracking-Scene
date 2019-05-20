package Renderer;

import java.util.LinkedList;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

public abstract class DroneComponent {
	
	private LinkedList<DroneComponent> children;
	public double radius;
	public double height;
	private double xTransition;
	private double yTransition;
	private double zTransition;
	private double[] eqn;
	
	
	public DroneComponent(double radius, double height) {
		children = new LinkedList<>();
		this.radius = radius;
		this.height = height;
	}
	
	public void draw(GL2 gl, GLU glu, GLUT glut, GLUquadric quadric) {
		gl.glPushMatrix();
		
		transformNode(gl);
		
		drawNode(gl, glu, glut, quadric);
		
		for(DroneComponent child : children) {
			child.draw(gl, glu, glut, quadric);
		}
		gl.glPopMatrix();
	}
	
	public void addChild(DroneComponent child) {
		children.add(child);
	}
	
	private void transformNode(GL2 gl) {
		gl.glTranslated(xTransition, yTransition, zTransition);
	}
	
	public void setTranslations(double x, double y, double z) {
		xTransition = x;
		yTransition = y;
		zTransition = z;
	}
	
	public void setEqn(double[] eqn) {
		this.eqn = eqn;
	}
	
	public abstract void drawNode(GL2 gl, GLU glu, GLUT glut,GLUquadric quadric);
}
