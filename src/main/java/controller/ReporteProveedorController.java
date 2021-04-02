/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import model.Proveedor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author AndySpino20
 */
@Named(value = "reporteProveedorController")
@SessionScoped
public class ReporteProveedorController implements Serializable {

    public ReporteProveedorController() {
    }

    public void verPDF() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/reportes/ProveedorR.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);

            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDF() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/reportes/ProveedorR.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=reporteProveedorR.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);

            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Para el proveedor que se busco
    public void reporteProveedor(Proveedor proveedor) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cedulaJuridica", proveedor.getCedulaJuridica());
        parametros.put("nombre", proveedor.getNombreProveedor());
        try {
            File jasper = new File(FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/reportes/ProveedorR1.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), parametros, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);

            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
