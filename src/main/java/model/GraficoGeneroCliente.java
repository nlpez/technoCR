/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Steve AS
 */
public class GraficoGeneroCliente {

    private String genero;
    private int total;

    public GraficoGeneroCliente(String genero, int total) {
        this.genero = genero;
        this.total = total;
    }
    
    public GraficoGeneroCliente() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
