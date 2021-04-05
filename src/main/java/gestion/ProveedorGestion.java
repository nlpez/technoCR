package gestion;

import java.io.StringWriter;
import java.math.BigDecimal;
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
import model.Conexion;
import model.Proveedor;
import org.primefaces.shaded.json.JSONObject;
import org.primefaces.shaded.json.JSONWriter;

public class ProveedorGestion {

    private static final String SQL_INSERTPROVEEDOR = "insert into proveedor(cedulaJuridica,nombre,direccion,telefono,correo,fechaIngre) values (?,?,?,?,?,?)";
    private static final String SQL_UPDATEPROVEEDOR = "update proveedor set cedulaJuridica=?,nombre=?,direccion=?,telefono=?,correo=?,fechaIngre=? where proveeid=?";
    private static final String SQL_DELETEPROVEEDOR = "Delete FROM proveedor where proveeid=?";
    private static final String SQL_GETPROVEEDORES = "SELECT * FROM proveedor";
    private static final String SQL_GETPROVEEDOR = "SELECT * FROM proveedor where proveeid=?";
    private static final String SQL_GETPROVEEDORReporte = "SELECT * FROM proveedor where cedulaJuridica=?";

    public static boolean insertProveedor(Proveedor proveedor) {
        try {

            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_INSERTPROVEEDOR);
            sentence.setString(1, proveedor.getCedulaJuridica());
            sentence.setString(2, proveedor.getNombre());
            sentence.setString(3, proveedor.getDirreccion());
            sentence.setInt(4, proveedor.getTelefono());
            sentence.setString(5, proveedor.getCorreo());
            sentence.setObject(6, proveedor.getFechaIngre());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateProveedor(Proveedor proveedor) {
        try {

            PreparedStatement sentence = Conexion.getConexion().prepareCall(SQL_UPDATEPROVEEDOR);
            sentence.setString(1, proveedor.getCedulaJuridica());
            sentence.setString(2, proveedor.getNombre());
            sentence.setString(3, proveedor.getDirreccion());
            sentence.setInt(4, proveedor.getTelefono());
            sentence.setString(5, proveedor.getCorreo());
            sentence.setObject(6, proveedor.getFechaIngre());
            sentence.setInt(7, proveedor.getProveeid());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteProveedor(Proveedor proveedor) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_DELETEPROVEEDOR);
            sentence.setInt(1, proveedor.getProveeid());
            return sentence.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ArrayList<Proveedor> getProvedores() {
        ArrayList<Proveedor> list = new ArrayList();
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETPROVEEDORES);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Proveedor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Proveedor getProveedor(int proveeid) {
        Proveedor proveedor = null;
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETPROVEEDOR);
            sentence.setInt(1, proveeid);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                proveedor = new Proveedor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }

    public static Proveedor buscarProveedor(String cedulaJuridica) {
        Proveedor proveedor = null;
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETPROVEEDORReporte);
            sentence.setString(1, cedulaJuridica);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                proveedor = new Proveedor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }

    public static String generarJsonProveedor() {
        Proveedor proveedor = null;
        String tiraJson = "";
        String fecha1 = "";
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETPROVEEDORES);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                proveedor = new Proveedor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7)
                );
                DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                fecha1 = sdf.format(proveedor.getFechaIngre());

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("proveeid", proveedor.getProveeid())
                        .add("cedulaJuridica", proveedor.getCedulaJuridica())
                        .add("nombre", proveedor.getNombre())
                        .add("direccion", proveedor.getDirreccion())
                        .add("telefono", proveedor.getTelefono())
                        .add("correo", proveedor.getCorreo())
                        .add("fechaIngre", fecha1)
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
