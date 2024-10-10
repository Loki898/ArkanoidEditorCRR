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
    public void addLevel(Level level) {
        levels.add(level);
    }
    public void removeLevel(String level) {
        for (int i = 0; i < levels.size(); i++) {
            if (level.equals(levels.get(i).getName())) {
                levels.remove(i);
            }
        }
    }
}
