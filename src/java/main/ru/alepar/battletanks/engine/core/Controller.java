package ru.alepar.battletanks.engine.core;

import java.util.List;

public interface Controller {
    void stepModel(long nanos, List<Integer> pressedKeys);

    void drawTo(Frame frame);
}
