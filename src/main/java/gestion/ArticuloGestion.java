package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Articulo;
import model.Conexion;

public class ArticuloGestion {

    private static final String SQL_INSERTARTICULO = "insert into articulo(articuloid,marca,nombre,descripcion,imagen, codigoArticulo,precio) values (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATEARTICULO = "update articulo set marca=?,nombre=?,descripcion=?,codigoArticulo=?,precio=? where articuloid=?";
    private static final String SQL_UPDATEARTICULO2 = "update articulo set marca=?,nombre=?,descripcion=?,imagen=?,codigoArticulo=?,precio=? where articuloid=?";
    private static final String SQL_DELETEARTICULO = "Delete FROM articulo where articuloid=?";
    private static final String SQL_GETARTICULOS = "SELECT articuloid, marca, nombre, descripcion, codigoArticulo, precio FROM articulo";
    private static final String SQL_GETARTICULOS2 = "SELECT * FROM articulo";
    private static final String SQL_GETARTICULO = "SELECT articuloid, marca, nombre, descripcion, codigoArticulo, precio FROM articulo where articuloid=?";
    private static final String SQL_GETARTICULOREPORTE = "SELECT articuloid, marca, nombre, descripcion, codigoArticulo, precio FROM articulo where codigoArticulo=?";

    public static boolean insertArticulo(Articulo articulo) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_INSERTARTICULO);
            sentence.setInt(1, articulo.getArticuloid());
            sentence.setString(2, articulo.getMarca());
            sentence.setString(3, articulo.getNombre());
            sentence.setString(4, articulo.getDescripcion());
            if (articulo.getImagen() == null) {
                sentence.setBytes(5, null);
            } else {
                sentence.setBytes(5, articulo.getImagen());
            }
            sentence.setString(6, articulo.getCodigoArticulo());
            sentence.setString(7, "" + articulo.getPrecio());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateArticulo(Articulo articulo) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_UPDATEARTICULO2);

            
            sentence.setString(1, articulo.getMarca());
            sentence.setString(2, articulo.getNombre());
            sentence.setString(3, articulo.getDescripcion());
            if (articulo.getImagen() == null) {
                sentence.setBytes(4, null);
            } else {
                sentence.setBytes(4, articulo.getImagen());
            }
            sentence.setString(5, articulo.getCodigoArticulo());
            sentence.setString(6, "" + articulo.getPrecio());
            sentence.setInt(7, articulo.getArticuloid());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteArticulo(Articulo articulo) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_DELETEARTICULO);

            sentence.setInt(1, articulo.getArticuloid());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ArrayList<Articulo> getArticulos() {
        ArrayList<Articulo> list = new ArrayList<>();

        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETARTICULOS2);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {

                list.add(new Articulo(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBytes(5),
                        rs.getString(6).toString(),
                        rs.getFloat(7)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public static Articulo getArticulo(int articuloid) {
        Articulo articulo = null;
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETARTICULO);
            sentence.setInt(1, articuloid);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {

                articulo = new Articulo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6)
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return articulo;
    }

    public static Articulo buscarArticulo(String codigoArticulo) {
        Articulo articulo = null;
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETARTICULOREPORTE);
            sentence.setString(1, codigoArticulo);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {

                articulo = new Articulo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6)
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return articulo;
    }

    public static String generarJson() {
        Articulo articulo = null;
        String tiraJson = "";
        String fecha1 = "";

        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETARTICULOS);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {

                articulo = new Articulo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("articuloid", articulo.getArticuloid())
                        .add("marca", articulo.getMarca())
                        .add("nombre", articulo.getNombre())
                        .add("descripcion", articulo.getDescripcion())
                        .add("codigoArticulo", articulo.getCodigoArticulo())
                        .add("precio", articulo.getPrecio()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objectJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }

}
