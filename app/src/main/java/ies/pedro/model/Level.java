/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.pedro.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.annotations.JsonAdapter;
import ies.pedro.utils.Size;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;


import java.io.*;


import java.util.Arrays;



@XmlRootElement
public class Level implements Serializable {
    private Size size;
    private Block[][] blocks;
    private double time;
    private String sound;
    private String name;


    @JsonAdapter(ies.pedro.utils.Point2DAdapterJson.class)

    private Point2D backgroundPosition;

    private static Size s= new Size(224 / 16, 240 / 8);


    public Level(String name) {
        this.name = name;
        this.setSize(s);
        this.init();

    }

    public Level() {
        this.setSize(s);
        this.init();
        this.name="";
    }

    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                if (this.getBlocks()[i][j] == null)
                    this.getBlocks()[i][j] = new Block();

            }
        }
    }

    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            Arrays.fill(this.getBlocks()[i], null);
        }
        this.backgroundPosition = null;
        this.sound = null;
        this.init();

    }


    public void setBlockValue(String value, int row, int col) {

        this.getBlocks()[row][col].setValue(value);
    }

    public String getBlockValue(int row, int col) {
        if (this.getBlocks()[row][col] == null || this.getBlocks()[row][col].getValue() == null)
            return null;
        else
            return this.getBlocks()[row][col].getValue();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    @XmlJavaTypeAdapter(ies.pedro.utils.Point2DAdapterXML.class)
    public Point2D getBackgroundPosition() {
        return backgroundPosition;
    }

    public void setBackgroundPosition(Point2D backgroundPosition) {
        this.backgroundPosition = backgroundPosition;
    }

    public static void saveLevel(File file, Level level) {
        Boolean saved =false;
        if (file == null || level == null){
            return;
        }
        if (file.getName().endsWith(".json")){
            saveLevelJson(file, level);
            saved = true;
        }
        if (file.getName().endsWith(".xml")){
            saveLevelXml(file, level);
            saved = true;
        }
        if (!saved){
            mostrarMensajeError("Error con el fichero", "El fichero no es válido, introduzca un fichero en formato .xml o .json");
        }
    }

    public static Level loadLevel(File file) {
        Boolean saved =false;
        if (file == null){
            return null;
        }
        if (file.getName().endsWith(".json")){
            saved = true;
            return loadLevelJson(file);
        }
        if (file.getName().endsWith(".xml")){
            saved = true;
            return loadLevelXml(file);

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

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        String tempo;
        cadena.append("Nivel ").append(this.getName()).append("\n");
        cadena.append("Backgroud:").append(this.backgroundPosition).append("\n");
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                //cadena.append("["+i+","+j+"]"+ this.getBlocks()[i][j]!=null?this.getBlocks()[i][j].toString():""+" "+"");
                tempo = this.getBlocks()[i][j] != null ? this.getBlocks()[i][j].toString() : "";
                cadena.append("[").append(i).append(",").append(j).append("]").append(tempo).append(" ");
            }
            cadena.append("\n");
        }
        return cadena.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static void saveLevelJson(File file, Level level) {
        try {
            FileWriter fw = new FileWriter(file);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(level, fw);
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Level loadLevelJson(File file) {
        Level level = null;

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            level = gson.fromJson(reader, Level.class);
            System.out.println(level);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        }

        return level;
    }

    static private void saveLevelXml(File file, Level level) {
        try {
            JAXBContext context = JAXBContext.newInstance(Level.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(level, new FileWriter(file));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static public Level loadLevelXml(File file) {
        Level level = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Level.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            level = (Level) unmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return level;
    }
}
