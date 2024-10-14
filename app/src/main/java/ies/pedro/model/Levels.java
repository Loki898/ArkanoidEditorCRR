package ies.pedro.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    public static void saveLevels(File file, Levels levels) {
        Boolean saved =false;
        if (file == null || levels == null){
            return;
        }
        if (file.getName().endsWith(".json")){
            saveLevelsJson(file, levels);
            saved = true;
        }
        if (file.getName().endsWith(".xml")){
            //saveLevelsXml(file, level);
            saved = true;
        }
        if (!saved){
            mostrarMensajeError("Error con el fichero", "El fichero no es válido, introduzca un fichero en formato .xml o .json");
        }
    }
    public static Levels loadLevels(File file) {
        Boolean saved =false;
        if (file == null){
            return null;
        }
        if (file.getName().endsWith(".json")){
            saved = true;
            return loadLevelsJson(file);
        }
        if (file.getName().endsWith(".xml")){
            saved = true;
            //return loadLevelsXml(file);

        }
        if (!saved){
            mostrarMensajeError("Error con el fichero", "El fichero no es válido, introduzca un fichero en formato .xml o .json");
        }
        return null;
    }
    private static void mostrarMensajeError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private static void saveLevelsJson(File file, Levels levels) {
        try {
            FileWriter fw = new FileWriter(file);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(levels, fw);
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static Levels loadLevelsJson(File file) {
        Levels levels = null;

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            levels = gson.fromJson(reader, Levels.class);
            System.out.println(levels);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        }

        return levels;
    }
}
