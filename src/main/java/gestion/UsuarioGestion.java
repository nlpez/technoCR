/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Usuario;

/**
 *
 * @author AndySpino20
 */
public class UsuarioGestion {

    private static final String SQL_GETUSUARIO = "Select * from usuario where userid=? and pwUsuario=MD5(?)";

    public static Usuario getUsuario(String userid, String pwUsuario) {
        Usuario usuario = null;

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_GETUSUARIO);
            consulta.setString(1, userid);
            consulta.setString(2, pwUsuario);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setUserid(userid);
                usuario.setPwUsuario(pwUsuario);
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setIdRol(rs.getString("idRol"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuario;
    }
}
