/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Articulo;
import model.Conexion;
import model.Inventario;
import model.Proveedor;

/**
 *
 * @author theai
 */
public class InventarioGestion {

    private static final String SQL_INSERTARTICULO = "insert into inventario(codigoArticulo,cantidadStock,proveeid) values (?,?,?)";

    private static final String SQL_UPDATEINVENTARIO = "UPDATE inventario set codigoArticulo=?, cantidadStock=?, proveeid=? where inventarioid = ?";

    private static final String SQL_DELETEINVENTARIO = "DELETE from inventario where inventarioid=?";

    private static final String SQL_GETINVENTARIOS = "SELECT * FROM inventario";

    private static final String SQL_GETINVENTARIO = "SELECT * FROM inventario where inventarioid=?";
    
    private static final String SQL_GETARTICULOREPORTE = "SELECT codigoArticulo, cantidadStock FROM inventario where codigoArticulo=?";

    public static boolean insertInventario(Inventario inventario) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_INSERTARTICULO);
  
            sentence.setString(1, inventario.getCodigoArticulo());
            sentence.setInt(2, inventario.getCantidadStock());
            sentence.setInt(3, inventario.getProveeid());

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
            Logger.getLogger(InventarioGestion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InventarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return invetario;
    }

    public static Inventario buscarArticulo(String codigoArticulo) {
        Inventario inventario = null;
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETARTICULOREPORTE);
            sentence.setString(1, codigoArticulo);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {

                inventario = new Inventario(
                        rs.getString(1),
                        rs.getInt(2)
                        
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(InventarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inventario;
    }
        public static String generarJsonInventario() {
        Inventario inventario = null;
        String tiraJson = "";
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETINVENTARIOS);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                inventario = new Inventario(
                        
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                       
                );
                
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson
                        .add("inventarioid", inventario.getInventarioid())
                        .add("codigoArticulo", inventario.getCodigoArticulo())
                        .add("cantidadStock", inventario.getCantidadStock())
                        .add("proveeid", inventario.getProveeid())
                        .build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objetoJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira + "\n";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
}
