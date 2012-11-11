package ru.alepar.battletanks.engine.opengl;

import com.jogamp.opengl.util.Animator;
import ru.alepar.battletanks.engine.core.Controller;
import ru.alepar.battletanks.engine.core.Engine;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import static ru.alepar.battletanks.engine.opengl.KeyState.PRESSED;
import static ru.alepar.battletanks.engine.opengl.KeyState.RELEASED;

public class OpenGLEngine extends Engine {

    private final GLU glu = new GLU();
    private final Map<Integer, KeyState> keyStates = new HashMap<Integer, KeyState>();

    public OpenGLEngine(Controller controller) {
        super(controller);
        final GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));

        keyStates.put(KeyEvent.VK_LEFT, new KeyState());
        keyStates.put(KeyEvent.VK_UP, new KeyState());
        keyStates.put(KeyEvent.VK_RIGHT, new KeyState());
        keyStates.put(KeyEvent.VK_DOWN, new KeyState());

        final GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(new Renderer());
        glcanvas.addKeyListener(new KeyListener());
        glcanvas.setSize(960, 720);

        final JFrame window = new JFrame("Battle Tanks");
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
            updateModel(pressedKeys());

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

    private List<Integer> pressedKeys() {
        final List<Map.Entry<Integer, KeyState>> pressedKeys = new ArrayList<Map.Entry<Integer, KeyState>>(4);
        for (Map.Entry<Integer, KeyState> entry : keyStates.entrySet()) {
            if(entry.getValue().isPressed()) {
                pressedKeys.add(entry);
            }
        }
        Collections.sort(pressedKeys, new Comparator<Map.Entry<Integer, KeyState>>() {
            @Override
            public int compare(Map.Entry<Integer, KeyState> left, Map.Entry<Integer, KeyState> right) {
                return -Long.valueOf(left.getValue().lastTime).compareTo(right.getValue().lastTime);
            }
        });
        final List<Integer> result = new ArrayList<Integer>(pressedKeys.size());
        for (Map.Entry<Integer, KeyState> pressedKey : pressedKeys) {
            result.add(pressedKey.getKey());
        }
        return result;
    }

    private class KeyListener implements java.awt.event.KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            // ignored
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (KeyEvent.VK_LEFT <= e.getKeyCode() && e.getKeyCode() <= KeyEvent.VK_DOWN) {
                handleKeyEvent(keyStates.get(e.getKeyCode()), PRESSED);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (KeyEvent.VK_LEFT <= e.getKeyCode() && e.getKeyCode() <= KeyEvent.VK_DOWN) {
                handleKeyEvent(keyStates.get(e.getKeyCode()), RELEASED);
            }
        }

        private void handleKeyEvent(KeyState keyState, int state) {
            keyState.lastTime = System.currentTimeMillis();
            keyState.lastEvent = state;
        }
    }
}