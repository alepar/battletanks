package ru.alepar.battletanks.engine.opengl;

public class KeyState {

    public static final int PRESSED = 1;
    public static final int RELEASED = 0;

    public int lastEvent = RELEASED;
    public long lastTime = 0;

    public boolean isPressed() {
        return lastEvent == PRESSED || System.currentTimeMillis() - lastTime < 15;
    }
}
