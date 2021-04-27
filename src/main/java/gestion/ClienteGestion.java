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
import model.Cliente;
import model.Conexion;
import model.GraficoGeneroCliente;

/**
 *
 * @author Steve AS
 */
public class ClienteGestion {

    private static final String SQL_INSERTCLIENTE = "insert into cliente(clienteId,cedula,nombreCliente,apellido1,apellido2,telefono,direccion,correo,genero) values (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATECLIENTE = "update cliente set cedula=?,nombreCliente=?,apellido1=?,apellido2=?,telefono=?,direccion=?,correo=?,genero=? where clienteId=?";
    private static final String SQL_DELETECLIENTE = "Delete FROM cliente where clienteId=?";
    private static final String SQL_GETCLIENTES = "SELECT * FROM cliente";
    private static final String SQL_GETCLIENTE = "SELECT * FROM cliente where clienteId=?";
    private static final String SQL_GETREPORTECLIENTE = "SELECT * FROM cliente where cedula=?";
    private static final String SQL_GENDER = "select genero, count(*) from cliente group by genero";
    
     public static ArrayList<GraficoGeneroCliente> getGraficoGeneroCliente() {
        ArrayList<GraficoGeneroCliente> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GENDER);
            ResultSet rs = sentencia.executeQuery();
            
            while (rs != null && rs.next()) {
                list.add(new GraficoGeneroCliente(
                        rs.getString(1),
                        rs.getInt(2)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Cliente buscarCliente(String cedula) {
        Cliente cliente = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETREPORTECLIENTE);
            sentencia.setString(1, cedula);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9).charAt(0)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return cliente;
    }

    public static boolean insertCliente(Cliente cliente) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_INSERTCLIENTE);
            sentence.setInt(1, cliente.getClienteId());
            sentence.setString(2, cliente.getCedula());
            sentence.setString(3, cliente.getNombreCliente());
            sentence.setString(4, cliente.getApellido1());
            sentence.setString(5, cliente.getApellido2());
            sentence.setString(6, cliente.getTelefono());
            sentence.setString(7, cliente.getDireccion());
            sentence.setString(8, cliente.getCorreo());
            sentence.setString(9, "" + cliente.getGenero());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateCliente(Cliente cliente) {
        try {

            PreparedStatement sentence = Conexion.getConexion().prepareCall(SQL_UPDATECLIENTE);
            sentence.setString(1, cliente.getCedula());
            sentence.setString(2, cliente.getNombreCliente());
            sentence.setString(3, cliente.getApellido1());
            sentence.setString(4, cliente.getApellido2());
            sentence.setString(5, cliente.getTelefono());
            sentence.setString(6, cliente.getDireccion());
            sentence.setString(7, cliente.getCorreo());
            sentence.setString(8, "" + cliente.getGenero());
            sentence.setInt(9, cliente.getClienteId());
            return sentence.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteCliente(Cliente cliente) {
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_DELETECLIENTE);
            sentence.setInt(1, cliente.getClienteId());
            return sentence.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> list = new ArrayList<>();
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETCLIENTES);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9).charAt(0)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Cliente getCliente(int clienteId) {
        Cliente cliente = null;
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETCLIENTE);
            sentence.setInt(1, clienteId);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9).charAt(0)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public static String generarJsonCliente() {
        Cliente cliente = null;
        String tiraJson = "";
        try {
            PreparedStatement sentence = Conexion.getConexion().prepareStatement(SQL_GETCLIENTES);
            ResultSet rs = sentence.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9).charAt(0)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("clienteId", cliente.getClienteId())
                        .add("cedula", cliente.getCedula())
                        .add("nombreCliente", cliente.getNombreCliente())
                        .add("apellido1", cliente.getApellido1())
                        .add("apellido2", cliente.getApellido2())
                        .add("telefono", cliente.getTelefono())
                        .add("direccion", cliente.getDireccion())
                        .add("correo", cliente.getCorreo())
                        .add("genero", cliente.getGenero())
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
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
}
