package ru.alepar.battletanks.model;

public enum Direction {
    // order is important!
    LEFT(180), UP(90), RIGHT(0), DOWN(-90);

    public final int angle;

    Direction(int angle) {
        this.angle = angle;
    }
}
