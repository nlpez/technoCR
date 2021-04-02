package model;

import java.util.Date;

public class Proveedor {

    private int proveeid;
    private String cedulaJuridica;
    private String nombre;
    private String dirreccion;
    private int telefono;
    private String correo;
    private Date fechaIngre;

    public Proveedor() {
    }

    public Proveedor(int proveeid, String cedulaJuridica, String nombre, String dirreccion, int telefono, String correo, Date fechaIngre) {
        this.proveeid = proveeid;
        this.cedulaJuridica = cedulaJuridica;
        this.nombre = nombre;
        this.dirreccion = dirreccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaIngre = fechaIngre;
    }

    public int getProveeid() {
        return proveeid;
    }

    public void setProveeid(int proveeid) {
        this.proveeid = proveeid;
    }

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaIngre() {
        return fechaIngre;
    }

    public void setFechaIngre(Date fechaIngre) {
        this.fechaIngre = fechaIngre;
    }

    public String getNombreProveedor() {
        String texto = "";
        texto+=nombre!=null?nombre+" ":"";
        return texto;
    }
}
