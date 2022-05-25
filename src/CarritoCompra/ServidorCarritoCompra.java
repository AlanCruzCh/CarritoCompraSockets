package CarritoCompraFinal;

import java.io.*;

public class ServidorCarritoCompra {

    private static String ruta_descarga, ruta, carpeta;
    private static int IndicacionCliente;
    public static ListaArticulos listaProductoDisponibles, listaArticulosEnCarrito;
    
    
    public static void main(String[] args) {
        /**
         * Declaramos la ruta donde se gurradara el archivo que vamos a
         * descargar del cliente
         */
        
        File f = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1"
                + "\\CarritoCompra\\src");
        ruta = f.getAbsolutePath();
        carpeta = "Productos_en_servidor";
        ruta_descarga = ruta + "\\" + carpeta + "\\";
        System.out.println("ruta: " + ruta_descarga);
        File f2 = new File(ruta_descarga);
        f2.mkdirs();
        f2.setWritable(true);

        /**
         * Creamos el arreglo de la lista de productos que vamos a mandar al
         * cliente en el cual guardaremos los productos que estan disponibles
         */
        listaProductoDisponibles = new ListaArticulos();
        listaArticulosEnCarrito = new ListaArticulos();
        
        /**
         * Creamos los arreglos que contendran los colores de nuestros productos
         * y usando el constructor de Producto que se encuentra en la clase
         * Clases_del_Servidor instanciamos nuestros prodductos
         */
        String[] colores_goober_candy = new String[]{"Azul", "Amarillo"};
        ProductoGeneral goobers_candy = new ProductoGeneral(1, "Goober Candy",
                colores_goober_candy,
                "C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra"
                + "\\src\\ImagenesProductos\\Goober candy.png",
                "Cacahuate tostado cubierto de chocolate oscuro", 19.50f, 50);

        String[] colores_Betty_Crocker_cake = new String[]{"Rojo", "Dorado"};
        ProductoGeneral betty_crocker_cake = new ProductoGeneral(2, "Betty Crocker cake "
                + "mix devil's food",
                colores_Betty_Crocker_cake, "C:\\Users\\Alan\\Documents\\noveno_semestre\\"
                + "Redes 2\\practica1\\CarritoCompra\\src\\ImagenesProductos\\"
                + "devils-food-cake.png", "Mezcla instantánea para pastel sabor chocolate",
                35.90f, 10);

        String[] colores_caprince_naturals = new String[]{"Rojo"};
        ProductoGeneral caprince_naturals = new ProductoGeneral(3, "Caprince Naturals",
                colores_caprince_naturals, "C:\\Users\\Alan\\Documents\\noveno_semestre\\"
                + "Redes 2\\practica1\\CarritoCompra\\src\\ImagenesProductos\\"
                + "CaprinceNaturals.png", "Shampoo con extracto de manzana", 33.20f, 15);

        String[] colores_Naturalmilk = new String[]{"Blanco", "Azul"};
        ProductoGeneral Naturalmilk = new ProductoGeneral(4, "Naturalmilk", colores_Naturalmilk,
                "C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra"
                + "\\src\\ImagenesProductos\\Naturalmilk.png",
                "Crema corporal antioxidante con extracto de argan y frutos rojos", 19.90f, 30);

        String[] colores_Pinguinos_fresa = new String[]{"Rojo", "Rosa"};
        ProductoGeneral Pinguinos_fresa = new ProductoGeneral(5, "Pinguinos fresa",
                colores_Pinguinos_fresa,
                "C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra"
                + "\\src\\ImagenesProductos\\Pinguinos_fresa.jpg",
                "Panque de chocolate con relleno cremoso sabor fresa", 15.0f, 30);

        /**
         * Agregamos los productos a la lista de productos creada con
         * anteriodidad
         */
        listaProductoDisponibles.agregarProductosLista(goobers_candy);
        listaProductoDisponibles.agregarProductosLista(betty_crocker_cake);
        listaProductoDisponibles.agregarProductosLista(caprince_naturals);
        listaProductoDisponibles.agregarProductosLista(Naturalmilk);
        listaProductoDisponibles.agregarProductosLista(Pinguinos_fresa);

        MetodosSockets.levantarServidor(3070);

        for (;;) {
            boolean accion = true;
            do {
                                
                IndicacionCliente = MetodosSockets.recibirIndicacionesCliente();

                switch (IndicacionCliente) {

                    case 1:

                        System.out.println("\n**********************************************"
                                + "*******************************************************\n");
                        
                        System.out.println("El cliente ha selecionado la opcion 1\n"
                                + "Nos pidio que le mandaramos el archivo "
                                + "serializado con los productos disponibles");
                        System.out.println("Serializando nuestra lista de articulos para "
                                + "el cliente");
                        
                        MetodosSockets.SerializarListaProductos
                                ("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\"
                                + "practica1\\CarritoCompra\\src\\Productos_en_servidor\\"
                                + "lista_productos_disponibles.txt", listaProductoDisponibles);
                        
                        MetodosSockets.MandarArchivos("C:\\Users\\Alan\\Documents\\"
                                + "noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src"
                                + "\\Productos_en_servidor\\lista_productos_disponibles.txt");
                                
                        System.out.println("\n**********************************************"
                                + "*******************************************************\n");
                        
                        break;

                    case 2:
                        System.out.println("\n**********************************************"
                                + "*******************************************************\n");
                                                
                        System.out.println("\nEl cliente nos pidio la opcion 2\n"
                                + "Nos preparamos para que recibamos el archivo "
                                + "serializado con los productos que desea el cluente");
                        
                        MetodosSockets.RecibirArchivos(ruta_descarga);
                        
                        
                        System.out.println("\n**********************************************"
                                + "*******************************************************\n");
                        
                        
                        break;

                    default:
                        System.out.println("\n**********************************************"
                                + "*******************************************************\n");
                        
                        System.out.println("El cliente se ha ido");
                        accion = false;
                        
                        System.out.println("\n**********************************************"
                                + "*******************************************************\n");
                        
                        
                        break;
                }

            } while (accion);

        }

    }
}
