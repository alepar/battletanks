package ru.alepar.battletanks.engine.core;

import java.util.List;

public class Engine {

    private final Controller controller;

    private long startFrame = -1;

    public Engine(Controller controller) {
        this.controller = controller;
    }

    public void updateModel(List<Integer> pressedKeys) {
        if (startFrame == -1) {
            controller.stepModel(0, pressedKeys);
            startFrame = System.nanoTime();
        }
        controller.stepModel(System.nanoTime() - startFrame, pressedKeys);
    }

    public void drawTo(Frame frame) {
        controller.drawTo(frame);
    }

}
