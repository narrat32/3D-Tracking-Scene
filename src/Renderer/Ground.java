package Renderer;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Ground {
	
	public Ground() {
	}

	public void draw(GL2 gl, GLUT glut, Texture[] textures) {
		gl.glEnable(GL2.GL_TEXTURE_2D);
		//Texture tex_one = textures[0];
		
		for(int i = 0; i < 10; i++) {
			gl.glBegin(GL2.GL_QUADS);
			//gl.glColor3d(0,  1,  0);
			
			textures[0].bind(gl);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_WRAP_S,  GL2.GL_REPEAT);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_MIN_FILTER,  GL2.GL_LINEAR);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_MAG_FILTER,  GL2.GL_LINEAR);
			textures[0].setTexParameterf(gl, GL2.GL_TEXTURE_WRAP_T,  GL2.GL_REPEAT);
			
			for(int j = 0; j < 10; j++) {
				gl.glTexCoord2d(0, 0);
				gl.glVertex3d(i, 0, j);
				gl.glTexCoord2d(1, 0);
				gl.glVertex3d(i + 1, 0, j);
				gl.glTexCoord2d(1, 1);
				gl.glVertex3d(i + 1, 0, j + 1);
				gl.glTexCoord2d(0, 1);
				gl.glVertex3d(i, 0, j + 1);
			}
			gl.glEnd();
		}
		
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}
}
