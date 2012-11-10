package ru.alepar.battletanks.engine.opengl;

import ru.alepar.battletanks.engine.core.Frame;
import ru.alepar.battletanks.model.Tank;

import javax.media.opengl.GL2;

class OpenGLFrame implements Frame {

    private final GL2 gl;

    public OpenGLFrame(GL2 gl) {
        this.gl = gl;
    }

    @Override
    public void drawTank(Tank tank) {
        gl.glLoadIdentity();
        gl.glTranslatef(tank.x() + 5, 5, 0);
        gl.glRotatef(0, 0, 0, 1);
        gl.glLineWidth(2.0f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex3i(-5, -5, 0);
        gl.glVertex3i(-5, 5, 0);
        gl.glVertex3i(5, 5, 0);
        gl.glVertex3i(5, -5, 0);
        gl.glEnd();
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3i(0, 0, 0);
        gl.glVertex3i(5, 0, 0);
        gl.glEnd();
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3i(-5, -3, 0);
        gl.glVertex3i(5, -3, 0);
        gl.glEnd();
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3i(-5, 3, 0);
        gl.glVertex3i(5, 3, 0);
        gl.glEnd();
    }
}
