
package gestion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Empleado;

/**
 *
 * @author Noel
 */
public class EmpleadoGestion {
    
    private static final String SQL_GET_EMPLEADOS = "SELECT * FROM empleado";
    private static final String SQL_GET_EMPLEADO = "SELECT * FROM empleado WHERE idEmpleado=?";
    private static final String SQL_INSERT_EMPLEADO = "INSERT INTO empleado (cedula, nombre, nombre2, apellido1, apellido2, puesto, fechaIngreso, "
            + "genero, sueldo) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_EMPLEADO = "UPDATE empleado SET cedula=?, nombre=?, nombre2=?, apellido1=?, apellido2=?, puesto=?, "
            + "fechaIngreso=?, genero=?, sueldo=? WHERE idEmpleado=?";
    private static final String SQL_DELETE_EMPLEADO = "DELETE FROM empleado WHERE idEmpleado=?";
    
    public static boolean insertaEmpleado(Empleado em){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(SQL_INSERT_EMPLEADO);
            pst.setString(1, em.getCedula());
            pst.setString(2, em.getNombre());
            pst.setString(3, em.getNombre2());
            pst.setString(4, em.getApellido1());
            pst.setString(5, em.getApellido2());
            pst.setString(6, em.getPuesto());
            pst.setDate(7, sqlDate( em.getFechaIngreso().getTime()));
            pst.setString(8, "" + em.getGenero());
            pst.setFloat(9, em.getSueldo());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
    
    public static boolean actualizaEmpleado(Empleado em){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(SQL_UPDATE_EMPLEADO);
            pst.setString(1, em.getCedula());
            pst.setString(2, em.getNombre());
            pst.setString(3, em.getNombre2());
            pst.setString(4, em.getApellido1());
            pst.setString(5, em.getApellido2());
            pst.setString(6, em.getPuesto());
            pst.setDate(7, sqlDate( em.getFechaIngreso().getTime()));
            pst.setString(8, "" + em.getGenero());
            pst.setFloat(9, em.getSueldo());
            pst.setInt(10, em.getIdEmpleado());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
    
    public static boolean borrarEmpleado(Empleado em){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(SQL_DELETE_EMPLEADO);
            pst.setInt(1, em.getIdEmpleado());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
    
    public static ArrayList<Empleado> getEmpleados(){
        ArrayList<Empleado> list = new ArrayList<>();
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareCall(SQL_GET_EMPLEADOS);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getDate(8), rs.getString(9).charAt(0), rs.getFloat(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    private static Date sqlDate(Long dat){
        java.sql.Date date = new java.sql.Date(dat);
        return date;
    }
    
    public static Empleado getEmpleado(int idEmpleado){
        Empleado em = null;
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(SQL_GET_EMPLEADO);
            pst.setInt(1, idEmpleado);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                em = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getDate(8), rs.getString(9).charAt(0), rs.getFloat(10));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return em;
    }
}
