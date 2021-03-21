/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author theai
 */
public class Inventario {
    
    private int inventarioid;
    private String codigoArticulo;
    private int cantidadStock;
    private int proveeid;

    public Inventario(int inventarioid, String codigoArticulo, int cantidadStock, int proveeid) {
        this.inventarioid = inventarioid;
        this.codigoArticulo = codigoArticulo;
        this.cantidadStock = cantidadStock;
        this.proveeid = proveeid;
    }

    public Inventario() {
    }

    public int getInventarioid() {
        return inventarioid;
    }

    public void setInventarioid(int inventarioid) {
        this.inventarioid = inventarioid;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public int getProveeid() {
        return proveeid;
    }

    public void setProveeid(int proveeid) {
        this.proveeid = proveeid;
    }
    
    
}
