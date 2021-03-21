package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Articulo;
import model.Cliente;
import model.Conexion;
import net.bootsfaces.render.A;

public class ArticuloGestion {

    private static final String SQL_INSERTARTICULO = "insert into articulo(articuloid,marca,nombre,descripcion,codigoArticulo,precio) values (?,?,?,?,?,?)";
    private static final String SQL_UPDATEARTICULO = "update articulo set marca=?,nombre=?,descripcion=?,codigoArticulo=?,precio=? where articuloid=?";
    private static final String SQL_DELETEARTICULO = "Delete FROM articulo where articuloid=?";
    private static final String SQL_GETARTICULOS = "SELECT articuloid, marca, nombre, descripcion, codigoArticulo, precio FROM articulo";
    private static final String SQL_GETARTICULO = "SELECT articuloid, marca, nombre, descripcion, codigoArticulo, precio FROM articulo where articuloid=?";

    public static boolean insertArticulo(Articulo articulo) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_INSERTARTICULO);
            sentence.setInt(1, articulo.getArticuloid());
            sentence.setString(2, articulo.getMarca());
            sentence.setString(3, articulo.getNombre());
            sentence.setString(4, articulo.getDescripcion());
            sentence.setString(5, articulo.getCodigoArticulo());
            sentence.setString(6, "" + articulo.getPrecio());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateArticulo(Articulo articulo) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_UPDATEARTICULO);

            sentence.setString(1, articulo.getMarca());
            sentence.setString(2, articulo.getNombre());
            sentence.setString(3, articulo.getDescripcion());
            sentence.setString(4, articulo.getCodigoArticulo());
            sentence.setString(5, "" + articulo.getPrecio());
            sentence.setInt(6, articulo.getArticuloid());
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
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETARTICULOS);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {

                list.add(new Articulo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6)
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

}
