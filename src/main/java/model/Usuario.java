/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author AndySpino20
 */
public class Usuario {

    private String userid;
    private String pwUsuario;
    private String nombreUsuario;
    private String idRol;
    
    public Usuario() {
    }

    public Usuario(String userid, String pwUsuario, String nombreUsuario, String idRol) {
        this.userid = userid;
        this.pwUsuario = pwUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idRol = idRol;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }

    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
}
