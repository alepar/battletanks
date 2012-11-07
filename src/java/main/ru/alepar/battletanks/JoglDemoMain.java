package ru.alepar.battletanks;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 * this example was taken from http://schabby.de/jogl-example-hello-world/
 */
public class JoglDemoMain {

    public static void main(String[] args) {
    	// setup OpenGL Version 2
    	GLProfile profile = GLProfile.get(GLProfile.GL2);
    	GLCapabilities capabilities = new GLCapabilities(profile);

    	// The canvas is the widget that's drawn in the JFrame
    	GLCanvas glcanvas = new GLCanvas(capabilities);
    	glcanvas.addGLEventListener(new Renderer());
    	glcanvas.setSize( 300, 300 );

        JFrame frame = new JFrame( "Hello World" );
        frame.getContentPane().add( glcanvas);

        // shutdown the program on windows close event
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

        frame.setSize( frame.getContentPane().getPreferredSize() );
        frame.setVisible( true );
    }

    private static class Renderer implements GLEventListener
    {
        private GLU glu = new GLU();

        public void display(GLAutoDrawable gLDrawable)
        {
            final GL2 gl = gLDrawable.getGL().getGL2();
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();
            gl.glTranslatef(-1.5f, 0.0f, -6.0f);
            gl.glBegin(GL2.GL_TRIANGLES);
            gl.glVertex3f(0.0f, 1.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, 0.0f);
            gl.glEnd();
            gl.glTranslatef(3.0f, 0.0f, 0.0f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3f(-1.0f, 1.0f, 0.0f);
            gl.glVertex3f(1.0f, 1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
            gl.glEnd();
            gl.glFlush();
        }


        public void init(GLAutoDrawable gLDrawable)
        {
        	System.out.println("init() called");
            GL2 gl = gLDrawable.getGL().getGL2();
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            gl.glShadeModel(GL2.GL_FLAT);
        }

        public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
        {
        	System.out.println("reshape() called: x = "+x+", y = "+y+", width = "+width+", height = "+height);
            final GL2 gl = gLDrawable.getGL().getGL2();

            if (height <= 0) // avoid a divide by zero error!
            {
                height = 1;
            }

            final float h = (float) width / (float) height;

            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glLoadIdentity();
            glu.gluPerspective(45.0f, h, 1.0, 20.0);
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glLoadIdentity();
        }


    	public void dispose(GLAutoDrawable arg0)
    	{
    		System.out.println("dispose() called");
    	}
    }

}