package Renderer;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Ground {
	
	public Ground() {
	}
	private int y = -1;
	public void draw(GL2 gl, GLUT glut, Texture[] textures) {
	
		//Texture tex_one = textures[0];
		
		for(int i = -10; i < 10; i++) {
			gl.glEnable(GL2.GL_TEXTURE_2D);
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1,  1,  1);
			
			textures[0].bind(gl);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_WRAP_S,  GL2.GL_REPEAT);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_MIN_FILTER,  GL2.GL_LINEAR);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_MAG_FILTER,  GL2.GL_LINEAR);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_WRAP_T,  GL2.GL_REPEAT);
			
			for(int j = -10; j < 10; j++) {
				gl.glTexCoord2d(0, 0);
				gl.glVertex3d(i, y, j);
				gl.glTexCoord2d(1, 0);
				gl.glVertex3d(i + 1, y, j);
				gl.glTexCoord2d(1, 1);
				gl.glVertex3d(i + 1, y, j + 1);
				gl.glTexCoord2d(0, 1);
				gl.glVertex3d(i, y, j + 1);
			}
			gl.glEnd();
		}
		
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}
}
