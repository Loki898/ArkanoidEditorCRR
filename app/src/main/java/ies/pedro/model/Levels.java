package ies.pedro.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.scene.control.Alert;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

@XmlRootElement
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
            saveLevelsXml(file, levels);
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
            return loadLevelsXml(file);

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
    private static void saveLevelsXml(File file, Levels levels) {
        try {
            JAXBContext context = JAXBContext.newInstance(Levels.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(levels, new FileWriter(file));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static Levels loadLevelsXml(File file) {
        Levels levels = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Levels.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            levels = (Levels) unmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return levels;
    }

    static public void XmlToHtml(File file) throws TransformerException {
        StreamSource xlsStreamSource = new StreamSource(Paths
                .get("levels.xsl")
                .toAbsolutePath().toFile());
        StreamSource xmlStreamSource = new StreamSource(file);
        TransformerFactory transformerFactory =
                TransformerFactory.newInstance("org.apache.xalan.processor.TransformerFactoryImpl", null);
        Path pathToHtmlFile = Paths.get("./levels.html");
        StreamResult result = new StreamResult(pathToHtmlFile.toFile());
        Transformer transformer =
                transformerFactory.newTransformer(xlsStreamSource);
        transformer.transform(xmlStreamSource, result);
    }
}
