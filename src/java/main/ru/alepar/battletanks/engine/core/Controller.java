package ru.alepar.battletanks.engine.core;

public interface Controller {
    void stepModel(long nanos);

    void drawTo(Frame frame);
}
