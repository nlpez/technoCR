/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ProveedorGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import static javax.ws.rs.client.Entity.json;
import model.Conexion;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author AndySpino20
 */
@Named(value = "respaldoController")
@SessionScoped
public class RespaldoController implements Serializable {

    /**
     * Creates a new instance of RespaldoController
     */
    public RespaldoController() {
    }

    public void respaldo() {
        //Se crea el archivo en memoria de tipo .zip
        ZipOutputStream out = null;
        try {
            String jsonProveedor = ProveedorGestion.generarJsonProveedor();

            File f = new File(FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/respaldo") + "respaldo.zip");

            out = new ZipOutputStream(new FileOutputStream(f));

            //Para meter cosas dentro del zip
            ZipEntry p = new ZipEntry("Proveedor.json");
            out.putNextEntry(p);
            byte[] dataProvee = jsonProveedor.getBytes();
            out.write(dataProvee, 0, dataProvee.length);
            out.closeEntry();
            out.close();

            //Se encarga de ir a leer el servidor y generar el empaquetado para descargar
            File ZipPath = new File(FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/respaldo") + "respaldo.zip");
            byte[] zip = Files.readAllBytes(ZipPath.toPath());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResponse();
            ServletOutputStream flujo = respuesta.getOutputStream();
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-disposition", "attachment; filename=RespaldoBD.zip");

            flujo.write(zip);
            flujo.flush();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
