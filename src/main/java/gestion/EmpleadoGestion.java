package gestion;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Empleado;

/**
 *
 * @author Noel
 */
public class EmpleadoGestion {

    private static final String SQL_GET_EMPLEADOS = "SELECT * FROM empleado";
    private static final String SQL_GET_EMPLEADO = "SELECT * FROM empleado WHERE idEmpleado=?";
    private static final String SQL_GET_REPORT_EMPLEADO = "SELECT * FROM empleado WHERE cedula=?";
    private static final String SQL_INSERT_EMPLEADO = "INSERT INTO empleado (cedula, nombre, nombre2, apellido1, apellido2, puesto, fechaIngreso, "
            + "genero, sueldo) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_EMPLEADO = "UPDATE empleado SET cedula=?, nombre=?, nombre2=?, apellido1=?, apellido2=?, puesto=?, "
            + "fechaIngreso=?, genero=?, sueldo=? WHERE idEmpleado=?";
    private static final String SQL_DELETE_EMPLEADO = "DELETE FROM empleado WHERE idEmpleado=?";

    public static Empleado buscaEmpleados(String cedula) {
        Empleado emp = null;
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareCall(SQL_GET_REPORT_EMPLEADO);
            pst.setString(1, cedula);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                emp  = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getDate(8), rs.getString(9).charAt(0), rs.getFloat(10));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return emp;
    }

    public static boolean insertaEmpleado(Empleado em) {
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(SQL_INSERT_EMPLEADO);
            pst.setString(1, em.getCedula());
            pst.setString(2, em.getNombre());
            pst.setString(3, em.getNombre2());
            pst.setString(4, em.getApellido1());
            pst.setString(5, em.getApellido2());
            pst.setString(6, em.getPuesto());
            pst.setDate(7, sqlDate(em.getFechaIngreso().getTime()));
            pst.setString(8, "" + em.getGenero());
            pst.setFloat(9, em.getSueldo());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public static boolean actualizaEmpleado(Empleado em) {
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(SQL_UPDATE_EMPLEADO);
            pst.setString(1, em.getCedula());
            pst.setString(2, em.getNombre());
            pst.setString(3, em.getNombre2());
            pst.setString(4, em.getApellido1());
            pst.setString(5, em.getApellido2());
            pst.setString(6, em.getPuesto());
            pst.setDate(7, sqlDate(em.getFechaIngreso().getTime()));
            pst.setString(8, "" + em.getGenero());
            pst.setFloat(9, em.getSueldo());
            pst.setInt(10, em.getIdEmpleado());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public static boolean borrarEmpleado(Empleado em) {
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

    public static ArrayList<Empleado> getEmpleados() {
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

    private static Date sqlDate(Long dat) {
        java.sql.Date date = new java.sql.Date(dat);
        return date;
    }

    public static Empleado getEmpleado(int idEmpleado) {
        Empleado em = null;
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement(SQL_GET_EMPLEADO);
            pst.setInt(1, idEmpleado);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                em = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getDate(8), rs.getString(9).charAt(0), rs.getFloat(10));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return em;
    }
    
     public static String generarJsonEmpleado() {
        Empleado emp = null;
        String tiraJson = "";
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GET_EMPLEADOS);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                emp  = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getDate(8), rs.getString(9).charAt(0), rs.getFloat(10));
            
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("IdEmpleado", emp.getIdEmpleado())
                        .add("cedula", emp.getCedula())
                        .add("nombre", emp.getNombre())
                        .add("nombre2", emp.getNombre2())
                        .add("apellido1", emp.getApellido1())
                        .add("apellido2", emp.getApellido2())
                        .add("puesto", emp.getPuesto())
                        .add("fechaIngreso", emp.getFechaIngreso().getTime())
                        .add("genero", emp.getGenero())
                        .add("sueldo", emp.getSueldo())
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
            ex.printStackTrace(System.out);
        }
        return tiraJson;
    }
}
