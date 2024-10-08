package ies.pedro.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.geometry.Point2D;

import java.io.IOException;

public class Point2DAdapterJson extends TypeAdapter<Point2D> {

    @Override
    public Point2D read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        String xy = reader.nextString();
        String[] parts = xy.split(",");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        return new Point2D(x, y);
    }
    @Override
    public void write(JsonWriter writer, Point2D value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        String xy = value.getX() + "," + value.getY();
        writer.value(xy);
    }
}
