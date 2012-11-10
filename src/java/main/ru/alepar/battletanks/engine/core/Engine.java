package ru.alepar.battletanks.engine.core;

public class Engine {

    private final Controller controller;

    private long startFrame = -1;

    public Engine(Controller controller) {
        this.controller = controller;
    }

    public void updateModel() {
        if (startFrame == -1) {
            controller.stepModel(0);
            startFrame = System.nanoTime();
        }
        controller.stepModel(System.nanoTime() - startFrame);
    }

    public void drawTo(Frame frame) {
        controller.drawTo(frame);
    }

}
