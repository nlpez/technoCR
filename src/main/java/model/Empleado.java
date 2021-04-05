package model;

import java.util.Date;

/**
 *
 * @author Noel
 */
public class Empleado {

    private int idEmpleado;
    private String cedula;
    private String nombre;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String puesto;
    private Date fechaIngreso;
    private char genero;
    private float sueldo;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String cedula, String nombre, String nombre2, String apellido1, String apellido2, String puesto, Date fechaIngreso, char genero, float sueldo) {
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.genero = genero;
        this.sueldo = sueldo;
    }

    public Empleado(String cedula, String nombre, String nombre2, String apellido1, String apellido2, String puesto, Date fechaIngreso, char genero, float sueldo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.genero = genero;
        this.sueldo = sueldo;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "{\"Empleado\":{\n\"cedula\":\""
                + cedula + "\",\n\"nombre\":\""
                + nombre + "\",\n\"nombre2\":\""
                + nombre2 + "\",\n\"apellido1\":\""
                + apellido1 + "\",\n\"apellido2\":\""
                + apellido2 + "\",\n\"puesto\":\""
                + puesto + "\",\n\"fechaIngreso\":\""
                + fechaIngreso + "\",\n\"genero\":\""
                + genero + "\",\n\"sueldo\":\""
                + sueldo + "\"\n}\n}";
    }
}
