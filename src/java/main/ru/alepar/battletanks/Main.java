package ru.alepar.battletanks;

import ru.alepar.battletanks.engine.core.Controller;
import ru.alepar.battletanks.engine.core.Frame;
import ru.alepar.battletanks.engine.opengl.OpenGLEngine;
import ru.alepar.battletanks.model.Direction;
import ru.alepar.battletanks.model.Tank;

import java.awt.event.KeyEvent;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        new OpenGLEngine(new Controller() {

            private final Tank tank = new Tank();
            private long lastFrame;

            @Override
            public void stepModel(long nanos, List<Integer> pressedKeys) {
                if (!pressedKeys.isEmpty()) {
                    final int key = pressedKeys.get(0);
                    final int kx;
                    if(key == KeyEvent.VK_RIGHT){
                        kx = 1;
                    } else if(key == KeyEvent.VK_LEFT) {
                        kx = -1;
                    } else {
                        kx = 0;
                    }
                    final int ky;
                    if(key == KeyEvent.VK_UP){
                        ky = 1;
                    } else if(key == KeyEvent.VK_DOWN) {
                        ky = -1;
                    } else {
                        ky = 0;
                    }
                    final long delta = nanos - lastFrame;
                    tank.nanoX += kx * delta;
                    tank.nanoY += ky * delta;
                    tank.direction = Direction.values()[key - 37];
                }
                lastFrame = nanos;
            }

            @Override
            public void drawTo(Frame frame) {
                frame.drawTank(tank);
            }
        });

    }

}
