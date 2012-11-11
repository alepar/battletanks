package ru.alepar.battletanks.engine.opengl;

public class KeyState {
    public static final int PRESSED = 1;
    public static final int RELEASED = 0;

    public long lastPressed = -1;
    public long lastReleased = 0;

    public boolean isPressed() {
        return lastPressed >= lastReleased || System.currentTimeMillis() - lastReleased < 10;
    }
}
