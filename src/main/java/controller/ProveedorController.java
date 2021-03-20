/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ProveedorGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Proveedor;

/**
 *
 * @author AndySpino20
 */
@Named(value = "proveedorController")
@SessionScoped
public class ProveedorController extends Proveedor implements Serializable {

    public ProveedorController() {
    }

    public String insertProveedor() {
        if (ProveedorGestion.insertProveedor(this)) {
            return "listaProveedor.xhtml";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al insertar el proveedor");
            FacesContext.getCurrentInstance().addMessage("registroProveedorForm:ID", message);
            return "registroProveedor.xhtml";
        }
    }

    public String updateProveedor() {

        if (ProveedorGestion.updateProveedor(this)) {
            return "listaProveedor.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al actualizar al proveedor");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:ID", message);
            return "editaProveedor.xhtml";
        }
    }

    public String deleteProveedor() {
        if (ProveedorGestion.deleteProveedor(this)) {
            return "listaProveedor.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar al proveedor");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:ID", message);
            return "editaProveedor.xhtml";
        }
    }

    public List<Proveedor> getProveedor() {
        return ProveedorGestion.getProvedores();
    }

    public String editaProveedor(int proveeid) {
        Proveedor proveedor = ProveedorGestion.getProveedor(proveeid);
        if (proveedor != null) {
            this.setProveeid(proveedor.getProveeid());
            this.setCedulaJuridica(proveedor.getCedulaJuridica());
            this.setNombre(proveedor.getNombre());
            this.setDirreccion(proveedor.getDirreccion());
            this.setTelefono(proveedor.getTelefono());
            this.setCorreo(proveedor.getCorreo());
            this.setFechaIngre(proveedor.getFechaIngre());
            return "editaProveedor.xhtml";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El registro del proveedor seleccionado no existe");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:ID", message);
            return "listaProveedor.xhtml";
        }
    }
}
