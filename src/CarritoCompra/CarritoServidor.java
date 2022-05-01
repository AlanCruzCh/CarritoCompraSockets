package CarritoCompra;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Clase que nos guarda el producto en una lista para que la mandemos al cliente
 * Clase del servidor
 */
class ListaProducto implements Serializable{
    ArrayList <Producto> listaProductos;
    public ListaProducto(){
        listaProductos = new ArrayList<>();
    }
    public void agregarListaProducto(Producto nuevoProducto){
        listaProductos.add(nuevoProducto);
    }
    public int buscar_indice_producto(int id){
        int posicion = 0;
        for (int i = 0; i < listaProductos.size(); i++) {
            if(id == listaProductos.get(i).id_Producto){
                posicion = i;
                break;
            }
        }
        return posicion;
    }
}

/**
 * Clase que nos crea los productos que guardaremos en una lista y la mandaremos 
 * al cliente
 * Clase del servidor
 */
class Producto implements Serializable{
    public int id_Producto;
    public String nombre_Producto;
    public String colores_Producto[];
    public String ruta_Imagen_Producto;
    public String descripcion_Producto;
    public float precio_Producto;
    public int stock_Producto;    
    public Producto(int id,String nombre, String colores[],String ruta_Imagen 
            ,String descripcion, float precio, int stock){
        this.id_Producto = id;
        this.nombre_Producto = nombre;
        this.colores_Producto = colores;
        this.ruta_Imagen_Producto = ruta_Imagen;
        this.descripcion_Producto = descripcion;
        this.precio_Producto = precio;
        this.stock_Producto = stock;
    }
}

public class CarritoServidor {
    public static void main(String[] args) {
        
        
    }   
}
