package ru.alepar.battletanks.model;

public class Tank {

    private static final int TANK_SPEED = 35000000;

    public long nanoX;
    public long nanoY;

    public int x() {
        return (int) (nanoX / TANK_SPEED);
    }

    public int y() {
        return (int) (nanoY / TANK_SPEED);
    }

}
