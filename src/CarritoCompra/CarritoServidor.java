package CarritoCompra;

import java.io.*;
import java.net.*;

public class CarritoServidor extends Clases_del_Servidor {

    public static void main(String[] args) {
        /**
         * Declaramos la ruta donde se gurradara el archivo que vamos a descargar del cliente
         */
        File f = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1"
                + "\\CarritoCompra\\src");
        String ruta = f.getAbsolutePath();
        String carpeta = "Productos_en_servidor";
        String ruta_descarga = ruta + "\\" + carpeta + "\\";
        System.out.println("ruta: " + ruta_descarga);
        File f2 = new File(ruta_descarga);
        f2.mkdirs();
        f2.setWritable(true);

        /**
         * Creamos el arreglo de la lista de productos que vamos a mandar al cliente en el 
         * cual guardaremos los productos que estan disponibles
         */
        ListaProducto listProducto = new ListaProducto();

        /**
         * Creamos los arreglos que contendran los colores de nuestros productos y usando el 
         * constructor de Producto que se encuentra en la clase Clases_del_Servidor 
         * instanciamos nuestros prodductos 
         */
        String[] colores_goober_candy = new String[]{"Azul", "Amarillo"};
        Producto goobers_candy = new Producto(1, "Goober Candy", colores_goober_candy,
                "ImagenesProductos/Goober candy.png", "Cacahuate tostado cubierto de chocolate "
                + "oscuro", 19.50f, 50);

        String[] colores_Betty_Crocker_cake = new String[]{"Rojo", "Dorado"};
        Producto betty_crocker_cake = new Producto(2, "Betty Crocker cake mix devil's food",
                colores_Betty_Crocker_cake, "ImagenesProductos/devils-food-cake.png", "Mezcla "
                + "instantánea para pastel sabor chocolate", 35.90f, 10);

        String[] colores_caprince_naturals = new String[]{"Rojo"};
        Producto caprince_naturals = new Producto(3, "Caprince Naturals",
                colores_caprince_naturals, "ImagenesProductos/CapriceNaturals.png",
                "Shampoo con extracto de manzana", 33.20f, 15);

        String[] colores_Naturalmilk = new String[]{"Blanco", "Azul"};
        Producto Naturalmilk = new Producto(4, "Naturalmilk", colores_Naturalmilk,
                "ImagenesProductos/Naturalmilk.png", "Crema corporal antioxidante con extracto "
                + "de argan y frutos rojos", 19.90f, 30);

        String[] colores_Pinguinos_fresa = new String[]{"Rojo", "Rosa"};
        Producto Pinguinos_fresa = new Producto(5, "Pinguinos fresa", colores_Pinguinos_fresa,
                "ImagenesProductos/Pinguinos_fresa.jpg", "Panque de chocolate con relleno "
                + "cremoso sabor fresa y ganache de chocolate", 15.0f, 30);

        /**
         * Agregamos los productos a la lista de productos creada con anteriodidad 
         */ 
        listProducto.agregarListaProducto(goobers_candy);
        listProducto.agregarListaProducto(betty_crocker_cake);
        listProducto.agregarListaProducto(caprince_naturals);
        listProducto.agregarListaProducto(Naturalmilk);
        listProducto.agregarListaProducto(Pinguinos_fresa);

        try {

            // Inicamos el servidor usando la clase ServerSocket y lo iniciamos en el puerto 
            // 3070
            ServerSocket servidor = new ServerSocket(3070);
            servidor.setReuseAddress(true);
            System.out.println("\nEsperando conexion con el cliente...");

            // Declaramos un for infinito para que el servidor siempre este en espera de un 
            // cliente
            for (;;) {

                //Aceptamos la concexion con el cliente he imprimimos los datos desde donde 
                // nos esta visitando
                Socket cliente = servidor.accept();
                System.out.println("\nEstablecimos conección con el cliente\n"
                        + "Direccion del cliente: " + cliente.getInetAddress() + " en el "
                        + "Puerto: " + cliente.getPort());

                /**
                 * Buffer para guardar los datos provenientes por el teclado
                 */
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                // Comprobamos que ningun elemnto de nuestra lista de productos este sin stock 
                // y si lo esta decidimos si agregamos mas elementos al stock o solo lo 
                // eliminamos 
                for (int i = 0; i < listProducto.listaProductos.size(); i++) {

                    //  Comprobamos si es que hay algun producto que no tenga stock
                    if (listProducto.listaProductos.get(i).stock_Producto == 0) {
                        System.out.println("\nDesea agregar mas unidades al produto: "
                                + listProducto.listaProductos.get(i).nombre_Producto
                                + " Si = 1 No = 0");
                        int pregunta = Integer.parseInt(br.readLine());
                        if (pregunta == 1) {
                            System.out.println("\nCuantos elementos desea agregar: ");
                            int cantidad = Integer.parseInt(br.readLine());
                            listProducto.agregar_Stock_Producto(i, cantidad);
                            System.out.println("El producto: " + listProducto.listaProductos.
                                    get(i).nombre_Producto + " ahora tiene mas disponibilidad");
                        } else {
                            System.out.println("\n El producto: " + listProducto.listaProductos.
                                    get(i).nombre_Producto + " ya no esta disponible");
                            listProducto.eliminar_Producto_sin_stock(i);
                        }
                    }
                }// cierre del for 

                /**
                 * Iniciamos un segundo buffer para recibir la accion de lo que
                 * debe hacer el socket proveniente por parte del cliente
                 */
                BufferedReader br_accion = new BufferedReader(new InputStreamReader(cliente.
                        getInputStream()));
                int mensaje_respuesta = Integer.parseInt(br_accion.readLine());
                br_accion.close();
                /**
                 * Hacemos nuestro menu
                 */
                switch (mensaje_respuesta) {

                    case 1:
                        /**
                         * Esto es lo que haremos siempre que el servidor recibe
                         * 1 como respuesta del cliente, esto nos dira que nos
                         * esta solicitando que le mandemos el archivo de
                         * productos disponibles
                         *
                         * Serializamos nuestro archivo que es el que mandaremos
                         * al cliente
                         */
                        cliente = servidor.accept();
                        System.out.println("\nSerializando nuestra lista de articulos para el "
                                + "cliente");
                        FileOutputStream fos = new FileOutputStream("C:\\Users\\Alan\\Documents"
                                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                                + "Productos_en_servidor\\lista_productos_disponibles.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(listProducto);
                        oos.close();
                        fos.close();
                        
                        /**
                         * Preparamos las variables con los datos que vamos a
                         * necesitar para mandarlas al cliente
                         */
                        File archivo_al_cliente = new File("C:\\Users\\Alan\\Documents"
                                + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                                + "Productos_en_servidor\\lista_productos_disponibles.txt");
                        String ruta_archivo = archivo_al_cliente.getAbsolutePath();
                        String nombre_archivo = archivo_al_cliente.getName();
                        long tamaño_archivo = archivo_al_cliente.length();

                        /**
                         * Definimos dos flujos orientados a bytes, uno para
                         * leer el archivo y otro para mandarlo por el socket
                         */
                        DataOutputStream dos_envio = new DataOutputStream(cliente.
                                getOutputStream());
                        DataInputStream dis_envio = new DataInputStream(new FileInputStream
                                (ruta_archivo));

                        /**
                         * Enviamos los datos generales del archivo por el
                         * socket
                         */
                        dos_envio.writeUTF(nombre_archivo);
                        dos_envio.flush();
                        dos_envio.writeLong(tamaño_archivo);
                        dos_envio.flush();

                        /**
                         * Leemos los datos contenidos en el archivo en paquetes
                         * de 1024 bytes y lo enviamos por el socket
                         */
                        byte[] b_envio = new byte[1024];
                        long enviados = 0;
                        int porcentaje, n_envio;
                        while (enviados < tamaño_archivo) {
                            n_envio = dis_envio.read(b_envio);
                            dos_envio.write(b_envio, 0, n_envio);
                            dos_envio.flush();
                            enviados = enviados + n_envio;
                            porcentaje = (int) (enviados * 100 / tamaño_archivo);
                            System.out.print("\nArchivo enviado: " + porcentaje + "%\r");
                        }// cerramos el while

                        /**
                         * Cerramos los flujos, el socket, terminamos bloques y
                         * cerramos flujos
                         */
                        System.out.print("\n\nArchivo enviado\n");
                        dos_envio.close();
                        dis_envio.close();

                        break;
                    default:
                        System.out.println("\n Adios");
                        break;

                } // fin del sitch
            } // fin del for
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
