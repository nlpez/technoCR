/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ClienteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cliente;

/**
 *
 * @author Steve AS
 */
@Named(value = "clienteController")
@SessionScoped
public class ClienteController extends Cliente implements Serializable {

    /**
     * Creates a new instance of ClienteController
     */
    public ClienteController() {
    }

    public String registroCliente() {
        return "registroCliente.xhtml";
    }

    public String insertCliente() {
        if (ClienteGestion.insertCliente(this)) {
            return "listaCliente.xhtml";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al insertar el cliente");
            FacesContext.getCurrentInstance().addMessage("registroClienteForm:cedula", message);
            return "registroCliente.xhtml";
        }
    }

    public String updateCliente() {
        if (ClienteGestion.updateCliente(this)) {
            return "listaCliente.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al actualizar el cliente");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:cedula", message);
            return "editaCliente.xhtml";
        }
    }

    public String deleteCliente() {
        if (ClienteGestion.deleteCliente(this)) {
            return "listaCliente.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar el cliente");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:cedula", message);
            return "editaCliente.xhtml";
        }
    }

    public List<Cliente> getClientes() {
        return ClienteGestion.getClientes();
    }

    public String editaCliente(int clienteId) {
        Cliente client = ClienteGestion.getCliente(clienteId);
        if (client != null) {
            this.setClienteId(client.getClienteId());
            this.setCedula(client.getCedula());
            this.setNombreCliente(client.getNombreCliente());
            this.setApellido1(client.getApellido1());
            this.setApellido2(client.getApellido2());
            this.setTelefono(client.getTelefono());
            this.setDireccion(client.getDireccion());
            this.setCorreo(client.getCorreo());
            this.setGenero(client.getGenero());
            return "editaCliente.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El registro del cliente seleccionado no existe");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:cedula", message);
            return "listaCliente.xhtml";
        }
    }

    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscarCliente(String cedula) {
        Cliente client = ClienteGestion.buscarCliente(cedula);
        if (client != null) {
            this.setClienteId(client.getClienteId());
            this.setCedula(client.getCedula());
            this.setNombreCliente(client.getNombreCliente());
            this.setApellido1(client.getApellido1());
            this.setApellido2(client.getApellido2());
            this.setTelefono(client.getTelefono());
            this.setDireccion(client.getDireccion());
            this.setCorreo(client.getCorreo());
            this.setGenero(client.getGenero());
            noImprimir = false;
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El registro no existe");
            FacesContext.getCurrentInstance().addMessage("reporteClienteForm:cedula", mensaje);
            noImprimir = true;
        }
    }
}
