package ies.pedro.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Levels {
        private HashMap<String,Level> levels;

    public Levels() {
        levels = new HashMap<>();
    }
    public HashMap<String,Level> getLevels() {
        return levels;
    }
    public void setLevels(HashMap<String,Level> levels) {
        this.levels = levels;
    }
    public void addLevel(Level level) {
        levels.put(level.getName(), level);
    }
    public void removeLevel(String level) {
       levels.remove(level);
    }
}
