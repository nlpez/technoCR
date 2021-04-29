/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ArticuloGestion;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
        this.setImagen(ArticuloImagenController.fileContents); //Obtiene la imagen
        if (ArticuloGestion.insertArticulo(this)) {
            return "registroArticulo.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al insertar el articulo");
            FacesContext.getCurrentInstance().addMessage("registroArticuloForm:ID", message);
            return "registroArticulo.xhtml";
        }

    }

    public String updateArticulo() {
        this.setImagen(ArticuloImagenController.fileContents); //Obtiene la imagen
        if (ArticuloGestion.updateArticulo(this)) {
            return "editaArticulo.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al actualizar el articulo");
            FacesContext.getCurrentInstance().addMessage("editaArticuloForm:articuloid", message);
            return "editaArticulo.xhtml";
        }

    }

    public String deleteArticulo() {
        if (ArticuloGestion.deleteArticulo(this)) {
            return "carouselArticulo.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar el articulo");
            FacesContext.getCurrentInstance().addMessage("editaArticuloForm:articuloid", message);
            return "editaArticulo.xhtml";
        }

    }

    public List getArticulos() {
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
            FacesContext.getCurrentInstance().addMessage("editaArticuloForm:articuloid", message);
            return "carouselArticulo.xhtml";
        }

    }

    private boolean noImprimir = true;

    public boolean isImprimir() {
        return noImprimir;
    }

    public void buscarArticulo(String codigoArticulo) {
        Articulo articulo = ArticuloGestion.buscarArticulo(codigoArticulo);
        if (articulo != null) {
            this.setCodigoArticulo(articulo.getCodigoArticulo());
            this.setArticuloid(articulo.getArticuloid());
            this.setMarca(articulo.getMarca());
            this.setNombre(articulo.getNombre());
            this.setDescripcion(articulo.getDescripcion());
            this.setPrecio(articulo.getPrecio());

            noImprimir = false;
        } else {

            clean();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El articulo  no fue existe");
            FacesContext.getCurrentInstance().addMessage("reporteArticuloForm:ID", message);
            noImprimir = true;
        }
    }

    private void clean() {
        this.setCodigoArticulo("");
        this.setNombre("");
        this.setMarca("");
        this.setDescripcion("");
        this.setArticuloid(0);
        this.setPrecio(0.0f);
        this.setCodigoArticulo("");
        this.setImagen(null);
    }

}
