package CarritoCompraFinal;

import java.io.*;
import java.util.*;

class ProductoGeneral implements Serializable{
    public int id_Producto;
    public String nombre_Producto;
    public String colores_Producto[];
    public String color_Producto;
    public String ruta_Imagen_Producto;
    public String descripcion_Producto;
    public float precio_Producto;
    public int stock_Producto;
    public ProductoGeneral(int id, String nombre, String colores[], String rutaImagen, String 
            descripcion, float precio, int stock) {
        this.id_Producto = id;
        this.nombre_Producto = nombre;
        this.colores_Producto = colores;
        this.ruta_Imagen_Producto = rutaImagen;
        this.descripcion_Producto = descripcion;
        this.precio_Producto = precio;
        this.stock_Producto = stock;
    }
    
    public ProductoGeneral(int id, String nombre, String color, String rutaImagen, String 
            descripcion, float precio, int stock) {
        this.id_Producto = id;
        this.nombre_Producto = nombre;
        this.color_Producto = color;
        this.ruta_Imagen_Producto = rutaImagen;
        this.descripcion_Producto = descripcion;
        this.precio_Producto = precio;
        this.stock_Producto = stock;
    }
}

class ListaArticulos implements Serializable{
    ArrayList<ProductoGeneral> ListaDeProductos;
    public ListaArticulos(){
        ListaDeProductos = new ArrayList<>();
    }
    public int posicionArticuloPorID(int ID){
        int posicion = -1;
        for (int i = 0; i < ListaDeProductos.size(); i++) {
            if(ID == ListaDeProductos.get(i).id_Producto){
                posicion = i;
            }
        }
    return posicion;
    }
    public int eliminarProductoLista(int ID, int Posicion){
        if (ID == ListaDeProductos.get(Posicion).id_Producto) {
            ListaDeProductos.remove(Posicion);
            return 0;
        }
        return -1;
    }
    public void agregarProductosLista(ProductoGeneral nuevoProducto){
        ListaDeProductos.add(nuevoProducto);
    }
}
