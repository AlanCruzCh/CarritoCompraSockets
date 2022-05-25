package CarritoCompra;

import java.util.*;
import java.io.*;

class Producto_del_Carrito implements Serializable {
    public int id_ProductoCarrito;
    public String imagen_producto;
    public String nombre_Producto;
    public String color_Producto;
    public int cantidad_ProductoCarrito;
    public float precio_Producto;
    public Producto_del_Carrito(int id, String nombre,  String imagen, String color, int cantidad, float precio) {
        this.id_ProductoCarrito = id;
        this.nombre_Producto = nombre;
        this.color_Producto = color;
        this.cantidad_ProductoCarrito = cantidad;
        this.precio_Producto = precio;
        this.imagen_producto = imagen;
    }
}

class CarritoCompra implements Serializable{
    ArrayList<Producto_del_Carrito> carrito_de_Productos;
    public CarritoCompra() {
        carrito_de_Productos = new ArrayList<>();
    }
    public int buscar_indice_carrito(int id) {
        int posicion = -1;
        for (int i = 0; i < carrito_de_Productos.size(); i++) {
            if (id == carrito_de_Productos.get(i).id_ProductoCarrito) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }
    public void agregarProductoCarrito(Producto_del_Carrito nuevo_Producto) {
        carrito_de_Productos.add(nuevo_Producto);
    }
    public void eliminarProductoCarrito(int posicion, int id){
        if(id == carrito_de_Productos.get(posicion).id_ProductoCarrito){
            carrito_de_Productos.remove(posicion);
        }
    }
}

public class Clases_del_Cliente{

}