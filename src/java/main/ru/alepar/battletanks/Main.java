package ru.alepar.battletanks;

import ru.alepar.battletanks.engine.core.Controller;
import ru.alepar.battletanks.engine.core.Frame;
import ru.alepar.battletanks.engine.opengl.OpenGLEngine;
import ru.alepar.battletanks.model.Tank;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        new OpenGLEngine(new Controller() {

            private final Tank tank = new Tank();

            @Override
            public void stepModel(long nanos, List<Integer> pressedKeys) {
                if(!pressedKeys.isEmpty()) {
                    System.out.println("pressed "+ pressedKeys.get(0));
                }
            }

            @Override
            public void drawTo(Frame frame) {
                frame.drawTank(tank);
            }
        });

    }

}
