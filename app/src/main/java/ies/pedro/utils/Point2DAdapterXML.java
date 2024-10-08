package ies.pedro.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import javafx.geometry.Point2D;

public class Point2DAdapterXML extends XmlAdapter<String, Point2D> {
    @Override
    public Point2D unmarshal(String s) throws Exception {
        String[] split = s.split(",");
        Point2D point = new Point2D(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        return point;
    }

    @Override
    public String marshal(Point2D point2D) throws Exception {
        String retorno = point2D.getX()+","+point2D.getY();
        return retorno;
    }
}
