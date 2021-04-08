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
            return "confirmacion.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "No se pudo insertar el nuevo registro");
            FacesContext.getCurrentInstance().addMessage("registroEmpleadosForm:cedula", message);
            return "registroEmpleado.xhtml";
        }
    } 
    
    public String actualizaEmpleado() {
        if (EmpleadoGestion.actualizaEmpleado(this)) {
            return "listaEmpleado.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "No se pudo actualizar el registro");
            FacesContext.getCurrentInstance().addMessage("editaEmpleados:idEmpleado", message);
            return "editaEmpleado.xhtml";
        }
    } 

    public String borraEmpleado() {
        if (EmpleadoGestion.borrarEmpleado(this)) {
            return "listaEmpleado.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "No se pudo borrar el registro");
            FacesContext.getCurrentInstance().addMessage("editaEmpleados:idEmpleado", message);
            return "editaEmpleado.xhtml";
        }
    } 
    
    public String editaEmpleado(int idEmpleado) {
        Empleado emp = EmpleadoGestion.getEmpleado(idEmpleado);
        if (emp != null) {
            this.setIdEmpleado(emp.getIdEmpleado());
            this.setCedula(emp.getCedula());
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

     private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscarEmpleado(String cedula) {
        Empleado emp = EmpleadoGestion.buscaEmpleados(cedula);
        if (emp != null) {
            this.setIdEmpleado(emp.getIdEmpleado());
            this.setCedula(emp.getCedula());
            this.setNombre(emp.getNombre());
            this.setNombre2(emp.getNombre2());
            this.setApellido1(emp.getApellido1());
            this.setApellido2(emp.getApellido2());
            this.setPuesto(emp.getPuesto());
            this.setFechaIngreso(emp.getFechaIngreso());
            this.setGenero(emp.getGenero());
            this.setSueldo(emp.getSueldo());
            noImprimir = false;
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El registro no existe");
            FacesContext.getCurrentInstance().addMessage("reporteEmpleadoForm:cedula", mensaje);
            noImprimir = true;
        }
    }
    
    public void limpiar() {
        setIdEmpleado(0);
        setCedula(null);
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
