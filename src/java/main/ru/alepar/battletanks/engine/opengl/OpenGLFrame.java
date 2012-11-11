package ru.alepar.battletanks.engine.opengl;

import ru.alepar.battletanks.engine.core.Frame;
import ru.alepar.battletanks.model.Tank;

import javax.media.opengl.GL2;

class OpenGLFrame implements Frame {

    private GL2 gl;
    private int lTank;

    public void setGl(GL2 gl) {
        this.gl = gl;
    }

    public void init() {
        lTank = createTankList();
    }

    private int createTankList() {
        final int lTank = gl.glGenLists(1);
        gl.glNewList(1, GL2.GL_COMPILE);
            // bounding square
            gl.glBegin(GL2.GL_LINE_LOOP);
                gl.glVertex3i(-5, -5, 0);
                gl.glVertex3i(-5,  5, 0);
                gl.glVertex3i( 5,  5, 0);
                gl.glVertex3i( 5, -5, 0);
            gl.glEnd();

            // gun
            gl.glBegin(GL2.GL_LINES);
                gl.glVertex3i(0, 0, 0);
                gl.glVertex3i(5, 0, 0);
            gl.glEnd();

            // bottom track
            gl.glBegin(GL2.GL_LINES);
                gl.glVertex3i(-5, -3, 0);
                gl.glVertex3i( 5, -3, 0);
            gl.glEnd();

            // top track
            gl.glBegin(GL2.GL_LINES);
                gl.glVertex3i(-5, 3, 0);
                gl.glVertex3i( 5, 3, 0);
            gl.glEnd();
        gl.glEndList();
        return lTank;
    }

    @Override
    public void drawTank(Tank tank) {
        gl.glLineWidth(2.0f);
        gl.glColor3f(0, 1.0f, 0);
        gl.glLoadIdentity();
        gl.glTranslatef(tank.x(), tank.y(), 0);
        gl.glRotatef(0, 0, 0, 1);
        gl.glCallList(lTank);
    }
}
