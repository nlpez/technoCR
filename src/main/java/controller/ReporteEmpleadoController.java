/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import model.Empleado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Noel
 */
@Named(value = "reporteEmpleadoController")
@SessionScoped
public class ReporteEmpleadoController implements Serializable {

    /**
     * Creates a new instance of ReporteEmpleadoController
     */
    public ReporteEmpleadoController() {
    }

    public void verPDF() {
        try {
            File JS = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/repoEmpleados.jasper"));
            JasperPrint ReportJasperSoft = JasperFillManager.fillReport(JS.getPath(), null, Conexion.getConexion());
            HttpServletResponse answer = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            answer.setContentType("application/pdf");
            answer.addHeader("Content-Type", "application/pdf");
            ServletOutputStream flujo = answer.getOutputStream();
            JasperExportManager.exportReportToPdfStream(ReportJasperSoft, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDF() {
            try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/repoEmpleados.jasper"));
            JasperPrint ReportJasperSoft = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());
            HttpServletResponse answer = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            answer.addHeader("Content-disposition", "attachment; filename=reporteEmpleados.pdf");
            ServletOutputStream flujo = answer.getOutputStream();
            JasperExportManager.exportReportToPdfStream(ReportJasperSoft, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void imprimirCliente(Empleado emp) {
        Map<String, Object> parametroCedula = new HashMap();
        parametroCedula.put("cedula",emp.getCedula());
        parametroCedula.put("nombre", emp.getNombre());
        try {
            File JS = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/repoEmpleados2.jasper"));
            JasperPrint ReportJasperSoft = JasperFillManager.fillReport(JS.getPath(), parametroCedula, Conexion.getConexion());
            HttpServletResponse answer = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            answer.setContentType("application/pdf");
            answer.addHeader("Content-Type", "application/pdf");
            ServletOutputStream flujo = answer.getOutputStream();
            JasperExportManager.exportReportToPdfStream(ReportJasperSoft, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
