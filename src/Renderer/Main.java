package Renderer;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Main implements GLEventListener{

	private static GLCanvas canvas;
	
	private GLU glu;
	private GLUT glut;
	private GLUquadric quadric;
	
	//tracking camera
	private TrackingCamera camera = new TrackingCamera(canvas);

	private Ground ground;
	
	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();
		
		// select and clear the model-view matrix
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		camera.draw(gl);
		
		ground.draw(gl, glut);
		/*gl.glDisable(GL2.GL_LIGHT0);
		gl.glDisable(GL2.GL_LIGHT1);
		
		
		
		//Re-enabling depth testing so that objects appear as they
		//should in the scene
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDisable(GL2.GL_BLEND);
		
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_LIGHT1);*/
		
		gl.glFlush();
	}


	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.setSwapInterval(1);
		
		//Sets the background colour of the scene
		gl.glClearColor(1f, 1f, 1f, 1.0f);
		
		glu = new GLU();
		
		camera.setDistance(15);
		camera.setLookAt(0, 0, 0);
		camera.setFieldOfView(40);
		//use the lights
		this.lights(gl);
		
		//Creates a quadratic
		quadric = glu.gluNewQuadric();
		
		//Passes in necessary variables into the objects
		glut = new GLUT();
		ground = new Ground();
	}

	private void lights(GL2 gl) {
		// lighting stuff
		float ambient[] = { 0, 0, 0, 1 };
		float diffuse[] = {1f, 1f, 1f, 1 };
		float specular[] = { 1, 1, 1, 1 };
		
		float[] ambientLight = { 0.1f, 0.1f, 0.1f,0f };  // weak RED ambient 
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_AMBIENT, ambientLight, 0); 
		
		float position0[] = { 5, 5, 5, 0 };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position0, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specular, 0);
		
		float position1[] = { -10, -10, -10, 0 };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position1, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, specular, 0);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_LIGHT1);
	
		//lets use use standard color functions
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		//normalise the surface normals for lighting calculations
		gl.glEnable(GL2.GL_NORMALIZE);
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	}

	public static void main(String[] args) {
		
		Frame frame = new Frame("Final Project");
		GLProfile profile =  GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		canvas = new GLCanvas(capabilities);
		Main background = new Main();
		
		canvas.addGLEventListener(background);
	
		frame.add(canvas);
		frame.setSize(650, 600);
		final FPSAnimator animator = new FPSAnimator(canvas, 60);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Thread(new Runnable() {
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas.requestFocusInWindow();
		
		animator.start();
	}
}
