/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ClienteGestion;
import gestion.InventarioGestion;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Inventario;

/**
 *
 * @author theai
 */
@Named(value = "inventarioController")
@Dependent
public class InventarioController extends Inventario implements Serializable {

    /**
     * Creates a new instance of InventarioController
     */
    public InventarioController() {
    }

    public String insertInventario() {
        if (InventarioGestion.insertInventario(this)) {
            return ".xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al insertar el inventario");
            FacesContext.getCurrentInstance().addMessage("*:ID", message);
            return ".xhtml";
        }
    }

    public String updateinventario() {
        if (InventarioGestion.updateInventario(this)) {
            return "";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al actualizar el inventario");
            FacesContext.getCurrentInstance().addMessage("*:ID", message);
            return ".xhtml";
        }
    }

    public String deleteInventario() {
        if (InventarioGestion.deleteInventario(this)) {
            return "";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar el inventario");
            FacesContext.getCurrentInstance().addMessage("*:ID", message);
            return ".xhtml";
        }
    }

    public List<Inventario> getInventarios() {
        return InventarioGestion.getInventarios();
    }

    public String editaCliente(int inventarioid) {
        Inventario inventario = InventarioGestion.getInventario(inventarioid);
        if (inventario != null) {
            this.setProveeid(inventario.getInventarioid());
            this.setCodigoArticulo(inventario.getCodigoArticulo());
            this.setCantidadStock(inventario.getCantidadStock());
            this.setProveeid(inventario.getProveeid());
            return ".xhtml";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar el inventario");
            FacesContext.getCurrentInstance().addMessage("*:ID", message);
            return ".xhtml";
        }
    }

}
