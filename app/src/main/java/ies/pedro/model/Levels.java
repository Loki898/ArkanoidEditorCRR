package ies.pedro.model;

import java.util.ArrayList;

public class Levels {
    private ArrayList<Level> levels;

    public Levels() {
        levels = new ArrayList<>();
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }
    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
}
