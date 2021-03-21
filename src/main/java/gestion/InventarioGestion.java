/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Inventario;

/**
 *
 * @author theai
 */
public class InventarioGestion {

    private static final String SQL_INSERTARTICULO = "INSERT into inventario"
            + "(inventarioid,codigoArticulo,cantidadStock,proveeid) "
            + "values (?,?,?,?)";

    private static final String SQL_UPDATEINVENTARIO = "UPDATE inventario set "
            + "codiigoArticulo=?, cantidadStock=?, proveeid=? where inventarioid = ?";

    private static final String SQL_DELETEINVENTARIO = "DELETE from inventario "
            + "where inventarioid=?";

    private static final String SQL_GETINVENTARIOS = "SELECT * FROM inventario";

    private static final String SQL_GETINVENTARIO = "SELECT * FROM inventario "
            + "where inventarioid=?";

    public static boolean insertInventario(Inventario inventario) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_INSERTARTICULO);
            sentence.setInt(1, inventario.getInventarioid());
            sentence.setString(2, inventario.getCodigoArticulo());
            sentence.setInt(3, inventario.getCantidadStock());
            sentence.setInt(4, inventario.getProveeid());

            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(InventarioGestion.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return false;
    }

    public static boolean updateInventario(Inventario inventario) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_UPDATEINVENTARIO);
            sentence.setString(1, inventario.getCodigoArticulo());
            sentence.setInt(2, inventario.getCantidadStock());
            sentence.setInt(3, inventario.getProveeid());
            sentence.setInt(4, inventario.getInventarioid());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(InventarioGestion.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return false;
    }

    public static boolean deleteInventario(Inventario inventario) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_DELETEINVENTARIO);
            sentence.setInt(1, inventario.getInventarioid());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(InventarioGestion.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return false;
    }

    public static ArrayList<Inventario> getInventarios() {
        ArrayList<Inventario> lista = new ArrayList<>();
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETINVENTARIOS);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                lista.add(new Inventario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static Inventario getInventario(int inventarioid) {
        Inventario invetario = null;
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETINVENTARIO);
            sentence.setInt(1, inventarioid);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                invetario = new Inventario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return invetario;
    }

}
