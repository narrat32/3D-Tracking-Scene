package Renderer;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Ground {
	
	public Ground() {
	}
	
	public void draw(GL2 gl, GLUT glut) {
		for(int i = 0; i < 10; i++) {
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3d(0,  1,  0);
			for(int j = 0; j < 10; j++) {
				gl.glVertex2d(i,  j);
				gl.glVertex2d(i + 1,  j);
				gl.glVertex2d(i + 1,  j + 1);
				gl.glVertex2d(i, j + 1);
			}
			gl.glEnd();
		}
	
	}
}
