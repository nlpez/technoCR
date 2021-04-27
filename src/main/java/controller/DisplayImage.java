/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;

/**
 *
 * @author Noel
 */
public class DisplayImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //Get las uploaded image
        try {
            //Parametro Articulo_id que se recibe de la invoncacion del servlet en el <p:graphicimage value={/image?id#{articuloid}}
            //se llama al servlet mediante /image y si usamos /image?Nombre_Parametro podemos pasar un parametro usando el bean que extienda del modelo
            //ejemplo /image?articuloid=#{bean.id}
            
            String id = req.getParameter("id"); 
            
            //Image bytes
            byte[] imageBytes = null;

            //Connection
            Connection con = Conexion.getConexion();
            PreparedStatement pst = con.prepareStatement("SELECT imagen from articulo where articuloid="+id); //Sentencia SQL para filtrar las imagenes por id
            ResultSet rs = pst.executeQuery();

            while (rs.next()) { //Obtenemos las imagenes de la base de datos
                imageBytes = rs.getBytes("imagen");
            }

            /*Si activamos esta linea no se podra obtener todas las imagenes de la base de datos, solo regresara el primer resultado y se cerrara la conexion
            y no permitira la obtencion de todas las imagenes*/
            //con.close(); 

            resp.getOutputStream().write(imageBytes);
            resp.getOutputStream().close();
        } catch (Exception e) {
            resp.getWriter().write(e.getMessage());
            resp.getWriter().close();
        }
    }

}
