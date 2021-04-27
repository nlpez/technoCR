/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Noel
 */
@Named(value = "articuloImagenController")
@RequestScoped
public class ArticuloImagenController {

    private Part uploadedFile;
    private String fileName;
    protected static byte[] fileContents;

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public byte[] getFileContents() {
        return fileContents;
    }

    public void setFileContents(byte[] fileContents) {
        this.fileContents = fileContents;
    }

    public ArticuloImagenController() {
    }

    public void upload() {
    fileName = Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

    try (InputStream input = uploadedFile.getInputStream()) {
        fileContents = input.readAllBytes();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Se cargo el archivo correctamente");
        FacesContext.getCurrentInstance().addMessage("editaArticuloForm:newImage", msg);
    }
    catch (IOException e) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo cargar la imagen en backend", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("editaArticuloForm:newImage", msg);
    }
}
}
