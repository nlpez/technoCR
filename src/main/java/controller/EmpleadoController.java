/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.EmpleadoGestion;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Empleado;

/**
 *
 * @author Noel
 */
@Named(value = "empleadoController")
@SessionScoped
public class EmpleadoController extends Empleado implements Serializable {

    public EmpleadoController() {
    }

    public List getEmpleados() {
        return EmpleadoGestion.getEmpleados();
    }

    public String insertaEmpleado() {
        if (EmpleadoGestion.insertaEmpleado(this)) {
            return "listaEmpleado.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "No se pudo insertar el nuevo registro");
            FacesContext.getCurrentInstance().addMessage("registroEmpleados:idEmpleado", message);
            return "listaEmpleado.xhtml";
        }
    } 
    
    public String actualizaEmpleado() {
        if (EmpleadoGestion.insertaEmpleado(this)) {
            return "listaEmpleado.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "No se pudo actualizar el registro");
            FacesContext.getCurrentInstance().addMessage("editaEmpleados:idEmpleado", message);
            return "listaEmpleado.xhtml";
        }
    } 

    public String borraEmpleado() {
        if (EmpleadoGestion.insertaEmpleado(this)) {
            return "listaEmpleado.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "No se pudo borrar el registro");
            FacesContext.getCurrentInstance().addMessage("editaEmpleados:idEmpleado", message);
            return "listaEmpleado.xhtml";
        }
    } 
    
    public String editaEmpleado(int idEmpleado) {
        Empleado emp = EmpleadoGestion.getEmpleado(idEmpleado);
        if (emp != null) {
            this.setIdEmpleado(emp.getIdEmpleado());
            this.setNombre(emp.getNombre());
            this.setNombre2(emp.getNombre2());
            this.setApellido1(emp.getApellido1());
            this.setApellido2(emp.getApellido2());
            this.setPuesto(emp.getPuesto());
            this.setFechaIngreso(emp.getFechaIngreso());
            this.setGenero(emp.getGenero());
            this.setSueldo(emp.getSueldo());
            return "editaEmpleado.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El registro del empleado seleccionado no existe");
            FacesContext.getCurrentInstance().addMessage("editaEmpleados:idEmpleado", message);
            return "listaEmpleado.xhtml";
        }
    }

    public void limpiar() {
        setIdEmpleado(0);
        setNombre(null);
        setNombre2(null);
        setApellido1(null);
        setApellido2(null);
        setPuesto(null);
        setFechaIngreso(null);
        setGenero('\0');
        setSueldo(0);
    }
}
