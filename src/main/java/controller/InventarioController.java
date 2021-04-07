/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ArticuloGestion;
import gestion.ClienteGestion;
import gestion.InventarioGestion;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Articulo;
import model.Inventario;

/**
 *
 * @author theai
 */
@Named(value = "inventarioController")
@SessionScoped
public class InventarioController extends Inventario implements Serializable {

    /**
     * Creates a new instance of InventarioController
     */
    public InventarioController() {
    }
  public String registroInventario(){
        return "registroInventario.xhtml";
    }
    public String insertInventario() {
        if (InventarioGestion.insertInventario(this)) {
            return "listaInventario.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al insertar el inventario");
            FacesContext.getCurrentInstance().addMessage("registroInventarioForm:codigoArticulo", message);
               return "registroInventario.xhtml";
        }
    }

    public String updateinventario() {
        if (InventarioGestion.updateInventario(this)) {
           return "listaInventario.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al actualizar el inventario");
            FacesContext.getCurrentInstance().addMessage("editaInventarioForm:codigoArticulo", message);
             return "editaInventario.xhtml";
        }
    }

    public String deleteInventario() {
        if (InventarioGestion.deleteInventario(this)) {
           return "listaInventario.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar el inventario");
            FacesContext.getCurrentInstance().addMessage("editaInventarioForm:codigoArticulo", message);
            return "editaInventario.xhtml";
        }
    }

    public List<Inventario> getInventarios() {
        return InventarioGestion.getInventarios();
    }

    public String editaInventario(int inventarioid) {
        Inventario inventario = InventarioGestion.getInventario(inventarioid);
        if (inventario != null) {
            this.setInventarioid(inventario.getInventarioid());
            this.setCodigoArticulo(inventario.getCodigoArticulo());
            this.setCantidadStock(inventario.getCantidadStock());
            this.setProveeid(inventario.getProveeid());
            return "editaInventario.xhtml";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar el inventario");
            FacesContext.getCurrentInstance().addMessage("editaInventarioForm:codigoArticulo", message);
            return "listaInventario.xhtml";
        }
    }
     private boolean noImprimir = true;

    public boolean isImprimir() {
        return noImprimir;
    }
        public void buscarArticulo(String codigoArticulo) {
        Inventario inventario = InventarioGestion.buscarArticulo(codigoArticulo);
        if (inventario != null) {
            this.setCodigoArticulo(inventario.getCodigoArticulo());
            this.setCantidadStock(inventario.getCantidadStock());
            
           
            noImprimir = false;
        } else {
            
            this.setCodigoArticulo("");
            this.setCantidadStock(0);
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El registro de inventario no existe");
            FacesContext.getCurrentInstance().addMessage("reporteInventarioForm:ID", message);
            noImprimir = true;
        }

    }

}
