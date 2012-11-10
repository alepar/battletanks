package ru.alepar.battletanks.engine.opengl;

import com.jogamp.opengl.util.Animator;
import ru.alepar.battletanks.engine.core.Controller;
import ru.alepar.battletanks.engine.core.Engine;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OpenGLEngine extends Engine {

    private final JFrame window;
    private final GLU glu = new GLU();

    public OpenGLEngine(Controller controller) {
        super(controller);
        final GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));

        final GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(new Renderer());
        glcanvas.setSize(960, 720);

        window = new JFrame("Battle Tanks");
        window.getContentPane().add(glcanvas);

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

        window.setSize(window.getContentPane().getPreferredSize());
        window.setResizable(false);
        window.setVisible(true);

        final Animator animator = new Animator(glcanvas);
        animator.start();
    }

    private class Renderer implements GLEventListener {

        private OpenGLFrame frame = new OpenGLFrame();

        public void display(GLAutoDrawable gLDrawable) {
            updateModel();

            final GL2 gl = gLDrawable.getGL().getGL2();
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

            frame.setGl(gl);
            drawTo(frame);

            gl.glFlush();
        }

        public void init(GLAutoDrawable gLDrawable) {
            final GL2 gl = gLDrawable.getGL().getGL2();

            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            gl.glShadeModel(GL2.GL_FLAT);

            frame.setGl(gl);
            frame.init();
        }

        public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
            final GL2 gl = gLDrawable.getGL().getGL2();

            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glLoadIdentity();
            glu.gluOrtho2D(0.0, 320, 0.0, 240);
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glLoadIdentity();
        }


        public void dispose(GLAutoDrawable arg0) {
            // do nothing
        }
    }

}