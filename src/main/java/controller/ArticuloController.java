/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ArticuloGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Articulo;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Named(value = "articuloController")
@SessionScoped
public class ArticuloController extends Articulo implements Serializable {

    /**
     * Creates a new instance of ArticuloController
     */
    public ArticuloController() {
    }

    public String registrarArticulo() {
        return "registroArticulo.xhtml";
    }

    public String insertArticulo() {
        if (ArticuloGestion.insertArticulo(this)) {
            return "listaArticulo.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al insertar el articulo");
            FacesContext.getCurrentInstance().addMessage("registroArticuloForm:ID", message);
            return "registroArticulo.xhtml";
        }

    }

    public String updateArticulo() {
        if (ArticuloGestion.updateArticulo(this)) {
            return "listaArticulo.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al actualizar el articulo");
            FacesContext.getCurrentInstance().addMessage("editaArticuloForm:ID", message);
            return "editaArticulo.xhtml";
        }

    }

    public String deleteArticulo() {
        if (ArticuloGestion.deleteArticulo(this)) {
            return "listaArticulo.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar el articulo");
            FacesContext.getCurrentInstance().addMessage("editaArticuloForm:ID", message);
            return "editaArticulo.xhtml";
        }

    }

    public List<Articulo> getArticulos() {
        return ArticuloGestion.getArticulos();
    }

    public String editaArticulo(int articuloid) {
        Articulo articulo = ArticuloGestion.getArticulo(articuloid);
        if (articulo != null) {
            this.setArticuloid(articulo.getArticuloid());
            this.setMarca(articulo.getMarca());
            this.setNombre(articulo.getNombre());
            this.setDescripcion(articulo.getDescripcion());
            this.setCodigoArticulo(articulo.getCodigoArticulo());
            this.setPrecio(articulo.getPrecio());
            return "editaArticulo.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El articulo seleccionado no existe");
            FacesContext.getCurrentInstance().addMessage("editaArticuloForm:ID", message);
            return "listaArticulo.xhtml";
        }

    }
    private boolean noImprimir = true;

    public boolean isImprimir() {
        return noImprimir;
    }

    public String buscarArticulo(int id) {
        Articulo articulo = ArticuloGestion.getArticulo(id);
        if (articulo != null) {
            this.setArticuloid(articulo.getArticuloid());
            this.setCodigoArticulo(articulo.getCodigoArticulo());
            this.setNombre(articulo.getNombre());
            this.setMarca(articulo.getMarca());
            this.setDescripcion(articulo.getDescripcion());
            this.setPrecio(articulo.getPrecio());
            noImprimir = false;
        } else {

            this.setCodigoArticulo("");
            this.setNombre("");
            this.setMarca("");
            this.setDescripcion("");
            this.setPrecio(0.0f);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El articulo  no fue existe");
            FacesContext.getCurrentInstance().addMessage("certificacionArticuloForm:ID", message);
            noImprimir = true;
        }
        return "reporteArticulo.xhtml";
    }

}
