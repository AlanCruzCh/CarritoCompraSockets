package CarritoCompra;

import java.util.*;
import java.io.*;

class ListaProducto implements Serializable {
    ArrayList<Producto> listaProductos;
    public ListaProducto() {
        listaProductos = new ArrayList<>();
    }
    public void agregarListaProducto(Producto nuevoProducto) {
        listaProductos.add(nuevoProducto);
    }
    public int buscar_indice_producto(int id) {
        int posicion = -1;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (id == listaProductos.get(i).id_Producto) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }
    public void eliminar_Producto_sin_stock(int posicion) {
        System.out.println("\nEl producto con el id: " + listaProductos.get(posicion).id_Producto               + " se eliminara de la lista" + "de productos");
        listaProductos.remove(posicion);
    }
    public void agregar_Stock_Producto(int posicion, int cantidad_agregar) {
        listaProductos.get(posicion).stock_Producto = cantidad_agregar;
        System.out.println("\nAl producto: " + listaProductos.get(posicion).nombre_Producto + 
                " se le han agregado: " + cantidad_agregar + " elementos mas");
    }
    public void mostrarProductoEspecifico(int posicion) {
        System.out.println("\nID del producto: " + listaProductos.get(posicion).id_Producto
                + " Nombre producto: " + listaProductos.get(posicion).nombre_Producto
                + "\nDescripcion del producto: " + listaProductos.get(posicion).
                descripcion_Producto + "\nColores disponibles: " + Arrays.toString(listaProductos.
                get(posicion).colores_Producto) + "\nCantidad disponible: " + listaProductos.get
                (posicion).stock_Producto + " Precio por piesa: " + listaProductos.get(posicion).
                precio_Producto);
        
    }
    public void mostrar_Productos() {
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println("\nID del producto: " + listaProductos.get(i).id_Producto
                    + " Nombre producto: " + listaProductos.get(i).nombre_Producto
                    + "\nDescripcion del producto: " + listaProductos.get(i).descripcion_Producto
                    + "\nColores disponibles: " + Arrays.toString(listaProductos.get(i).
                    colores_Producto) + "\nCantidad disponible: " + listaProductos.get(i).
                    stock_Producto + " Precio por piesa: " + listaProductos.get(i).
                    precio_Producto);
        }
    }
}
class Producto implements Serializable {
    public int id_Producto;
    public String nombre_Producto;
    public String colores_Producto[];
    public String ruta_Imagen_Producto;
    public String descripcion_Producto;
    public float precio_Producto;
    public int stock_Producto;
    public Producto(int id, String nombre, String colores[], String ruta_Imagen,
            String descripcion, float precio, int stock) {
        this.id_Producto = id;
        this.nombre_Producto = nombre;
        this.colores_Producto = colores;
        this.ruta_Imagen_Producto = ruta_Imagen;
        this.descripcion_Producto = descripcion;
        this.precio_Producto = precio;
        this.stock_Producto = stock;
    }
}
public class Clases_del_Servidor {
    public static void main(String[] args) {
    }
}
