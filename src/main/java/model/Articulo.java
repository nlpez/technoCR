package model;

public class Articulo {

    private int articuloid;
    private String marca;
    private String nombre;
    private String descripcion;
    private String codigoArticulo;
    private Float precio;

    public Articulo() {
    }

    public Articulo(int articuloid, String marca, String nombre, String descripcion, String codigoArticulo, Float precio) {
        this.marca = marca;
        this.articuloid = articuloid;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigoArticulo = codigoArticulo;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getArticuloid() {
        return articuloid;
    }

    public void setArticuloid(int articuloid) {
        this.articuloid = articuloid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getNombreCompletoArticulo() {
        String texto="";
        
        texto+=this.descripcion!=null?this.descripcion+" ":" ";
        texto+=this.marca!=null?this.marca+" ":" ";
        texto+=this.nombre!=null?this.nombre+" ":" ";

       return texto;
    }

}
