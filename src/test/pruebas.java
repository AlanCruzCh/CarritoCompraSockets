package test;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Clase que nos guarda el producto en una lista para que la mandemos al cliente
 * Clase del servidor
 */
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
}

/**
 * Clase que nos crea los productos que guardaremos en una lista y la mandaremos
 * al cliente Clase del servidor
 */
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

/**
 * Clase que nos guarda el id del producto y la cantidad de este Clase para el
 * cliente
 */
class ProductoAlCarrito {
    public int id_ProductoCarrito;
    public int cantidad_ProductoCarrito;
    public String nombre_Producto;
    public ProductoAlCarrito(int id, String nombre, int cantidad) {
        this.id_ProductoCarrito = id;
        this.nombre_Producto = nombre;
        this.cantidad_ProductoCarrito = cantidad;
    }
}

/**
 * Clase que nos guarda el producto al carrito de compra Clase para el cliente
 */
class CarritoCompra {

    ArrayList<ProductoAlCarrito> carritoProductos;

    public CarritoCompra() {
        carritoProductos = new ArrayList<>();
    }

    public int buscar_indice_carrito(int id) {
        int posicion = 0;
        for (int i = 0; i < carritoProductos.size(); i++) {
            if (id == carritoProductos.get(i).id_ProductoCarrito) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    public void agregarProductoCarrito(ProductoAlCarrito nuevo_Producto) {
        carritoProductos.add(nuevo_Producto);
    }
}

public class pruebas {

    public static void main(String[] args) {
        /**
         * Iniciamos el arraylist llamada listProducto en la cual guardaremos
         * nuestros productos
         */
        try {
            ListaProducto listProducto = new ListaProducto();

            String[] colores_goober_candy = new String[]{"Azul", "Amarillo"};
            Producto goobers_candy = new Producto(1, "Goober Candy", colores_goober_candy,
                    "ImagenesProductos/Goober candy.png", "Cacahuate tostado cubierto de chocolate oscuro",
                    19.50f, 50);

            String[] colores_Betty_Crocker_cake = new String[]{"Rojo", "Dorado"};
            Producto betty_crocker_cake = new Producto(2, "Betty Crocker cake mix devil's food",
                    colores_Betty_Crocker_cake, "ImagenesProductos/devils-food-cake.png",
                    "Mezcla instantánea para pastel sabor chocolate", 35.90f, 10);

            String[] colores_caprince_naturals = new String[]{"Rojo"};
            Producto caprince_naturals = new Producto(3, "Caprince Naturals", colores_caprince_naturals,
                    "ImagenesProductos/CapriceNaturals.png", "Shampoo con extracto de manzana",
                    33.20f, 15);

            String[] colores_Naturalmilk = new String[]{"Blanco", "Azul"};
            Producto Naturalmilk = new Producto(4, "Naturalmilk", colores_Naturalmilk,
                    "ImagenesProductos/Naturalmilk.png", "Crema corporal antioxidante con extracto de argan "
                    + "y frutos rojos", 19.90f, 30);

            String[] colores_Pinguinos_fresa = new String[]{"Rojo", "Rosa"};
            Producto Pinguinos_fresa = new Producto(5, "Pinguinos fresa", colores_Pinguinos_fresa,
                    "ImagenesProductos/Pinguinos_fresa.jpg", "Panque de chocolate con relleno cremoso sabor "
                    + "fresa y ganache de chocolate", 15.0f, 100);

            listProducto.agregarListaProducto(goobers_candy);
            listProducto.agregarListaProducto(betty_crocker_cake);
            listProducto.agregarListaProducto(caprince_naturals);
            listProducto.agregarListaProducto(Naturalmilk);
            listProducto.agregarListaProducto(Pinguinos_fresa);

            /**
             * br es el buffer para solicitar los datos de entrada al usuario
             * productos_Carrito es el arraylist que contendra los productos que
             * seran agregados al carrito de compra condicion_Seguir_Comprando
             * es la condicion de paro del bucle while para seguir agregando
             * producos al carrito de compra
             */
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            CarritoCompra productos_Carrito = new CarritoCompra();
            boolean condicion_Seguir_Comprando = true;

            while (condicion_Seguir_Comprando == true) {
                System.out.println("\n ¿Qué producto desea adquirir?");
                // Ciclo que nos mostrara los productos
                for (int i = 0; i < listProducto.listaProductos.size(); i++) {
                    // Solo nos mostrara los productos que tengan stock disponible
                    if (listProducto.listaProductos.get(i).stock_Producto != 0) {
                        System.out.println("\nId producto: " + listProducto.listaProductos.get(i).id_Producto
                                + " Nombre producto: " + listProducto.listaProductos.get(i).nombre_Producto
                                + " Cantidad disponible: " + listProducto.listaProductos.get(i).stock_Producto);
                    }
                }
                System.out.println("\nEscoja un producto por su id: ");
                int id_producto_solicitado = Integer.parseInt(br.readLine());
                // buscamos ese producto en nuestro arraylis que contiene a nuestra lista
                // de productos
                int posicio_id_producto_encontrado = listProducto.buscar_indice_producto(id_producto_solicitado);
                if(posicio_id_producto_encontrado != -1){
                    // encontro el id del producto deseado y nos regresa su posicion
                    System.out.println("\nElija la cantidad del producto que desea: ");
                    int cantidad_producto_solicitado = Integer.parseInt(br.readLine());
                    // verificamos si ese producto ya se encuentra en nuestra lista del carrito o si es nuevo
                    int posicion_producto_solicitado = productos_Carrito.buscar_indice_carrito(id_producto_solicitado);
                    if(posicion_producto_solicitado == -1){
                        // el producto es  nuevo
                        if(cantidad_producto_solicitado >= 1 && cantidad_producto_solicitado <= 
                                listProducto.listaProductos.get(posicio_id_producto_encontrado).stock_Producto){
                            ProductoAlCarrito nuevo_producto = new ProductoAlCarrito(id_producto_solicitado, 
                                    listProducto.listaProductos.get(posicio_id_producto_encontrado).nombre_Producto, 
                                    cantidad_producto_solicitado);
                            productos_Carrito.agregarProductoCarrito(nuevo_producto);
                        }
                        //el producto ya esta en el carrito
                        else{
                            int suma_cantidad_producto = productos_Carrito.carritoProductos.get(posicion_producto_solicitado).
                                    cantidad_ProductoCarrito + cantidad_producto_solicitado;
                            //comprobamos que la suma de la canntidad de ese producto mas la nueva no pase el stock
                            if(suma_cantidad_producto <= listProducto.listaProductos.get(posicio_id_producto_encontrado).stock_Producto){
                                productos_Carrito.carritoProductos.get(posicion_producto_solicitado).cantidad_ProductoCarrito 
                                        = suma_cantidad_producto;
                            }
                            else{
                                System.out.println("\nNo se puede actualizar la cantidad del producto por que no se tiene el stock suficiente");
                            }
                            
                        }
                    }
                }
                else{
                    System.out.println("\nEste articulo no existe");
                }
                System.out.println("\nDesea seguir comprando? si = 1, no = 0");
                int desicion_compra = Integer.parseInt(br.readLine());
                if(desicion_compra == 0){
                    condicion_Seguir_Comprando = false;
                    System.out.println("\nGracias por su compra");
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
