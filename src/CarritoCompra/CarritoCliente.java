package CarritoCompra;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Clase que nos guarda el id del producto y la cantidad de este
 * Clase para el cliente
 */
class ProductoAlCarrito{
    public int id_ProductoCarrito;
    public int cantidad_ProductoCarrito;
    public String nombre_Producto;
    public ProductoAlCarrito( int id, String nombre ,int cantidad){
        this.id_ProductoCarrito = id;
        this.nombre_Producto = nombre;
        this.cantidad_ProductoCarrito = cantidad;
    }
}

/**
 * Clase que nos guarda el producto al carrito de compra
 * Clase para el cliente
 */
class CarritoCompra{
    ArrayList <ProductoAlCarrito> carritoProductos;
    public CarritoCompra(){
        carritoProductos = new ArrayList<>();
    }
    public int buscar_indice_carrito(int id){
        int posicion = 0;
        for (int i = 0; i < carritoProductos.size(); i++) {
            if(id == carritoProductos.get(i).id_ProductoCarrito){
                posicion = i;
                break;
            }
        }
        return posicion;
    }
    
    public void agregarProductoCarrito(ProductoAlCarrito nuevo_Producto){
        carritoProductos.add(nuevo_Producto);
    }
}

public class CarritoCliente {
    public static void main(String[] args) {
    
    }
}    