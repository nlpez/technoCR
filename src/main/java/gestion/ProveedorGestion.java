package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Proveedor;

public class ProveedorGestion {

    private static final String SQL_INSERTPROVEEDOR = "insert into proveedor(cedulaJuridica,nombre,direccion,telefono,correo,fechaIngre) values (?,?,?,?,?,?)";
    private static final String SQL_UPDATEPROVEEDOR = "update proveedor set cedulaJuridica=?,nombre=?,direccion=?,telefono=?,correo=?,fechaIngre=? where proveeid=?";
    private static final String SQL_DELETEPROVEEDOR = "Delete FROM proveedor where proveeid=?";
    private static final String SQL_GETPROVEEDORES = "SELECT * FROM proveedor";
    private static final String SQL_GETPROVEEDOR = "SELECT * FROM proveedor where proveeid=?";

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
}
