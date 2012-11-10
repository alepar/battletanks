package ru.alepar.battletanks;

import ru.alepar.battletanks.engine.core.Controller;
import ru.alepar.battletanks.engine.core.Frame;
import ru.alepar.battletanks.engine.opengl.OpenGLEngine;
import ru.alepar.battletanks.model.Tank;

public class Main {

    public static void main(String[] args) {

        final OpenGLEngine engine = new OpenGLEngine(new Controller() {

            private final Tank tank = new Tank();

            @Override
            public void stepModel(long nanos) {
                tank.x = ((int) (nanos / 1000000 / 20)) % 90;
            }

            @Override
            public void drawTo(Frame frame) {
                frame.drawTank(tank);
            }
        });

    }

}
