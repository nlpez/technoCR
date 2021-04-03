/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Steve AS
 */
public class Cliente {

    private int clienteId;
    private String cedula;
    private String nombreCliente;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String direccion;
    private String correo;
    private char genero;

    public Cliente() {
    }

    public Cliente(int clienteId, String cedula, String nombreCliente, String apellido1, String apellido2, String telefono, String direccion, String correo, char genero) {
        this.clienteId = clienteId;
        this.cedula = cedula;
        this.nombreCliente = nombreCliente;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.genero = genero;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getNombreCompleto() {
        String texto = "";
        texto += this.nombreCliente != null ? this.nombreCliente + " " : "";
        texto += this.apellido1 != null ? this.apellido1 + " " : "";
        texto += this.apellido2 != null ? this.apellido2 + " " : "";
        return texto;
    }

}
