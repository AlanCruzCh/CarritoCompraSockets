package CarritoCompra;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarritoServidor extends Clases_del_Servidor {

    public static void main(String[] args) {
        /**
         * Declaramos la ruta donde se gurradara el archivo que vamos a
         * descargar del cliente
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
         * Creamos el arreglo de la lista de productos que vamos a mandar al
         * cliente en el cual guardaremos los productos que estan disponibles
         */
        ListaProducto listProducto = new ListaProducto();

        /**
         * Creamos los arreglos que contendran los colores de nuestros productos
         * y usando el constructor de Producto que se encuentra en la clase
         * Clases_del_Servidor instanciamos nuestros prodductos
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
         * Agregamos los productos a la lista de productos creada con
         * anteriodidad
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

                boolean accion = true;
                do {

                    /**
                     * Iniciamos un segundo buffer para recibir la accion de lo
                     * que debe hacer el socket proveniente por parte del
                     * cliente
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
                             * Aceptamos una nueva conexion de tipo socket para poder mandar los
                             * archivos al cliente
                             */
                            cliente = servidor.accept();
                            /**
                             * Esto es lo que haremos siempre que el servidor
                             * recibe 1 como respuesta del cliente, esto nos
                             * dira que nos esta solicitando que le mandemos el
                             * archivo de productos disponibles
                             *
                             * Serializamos nuestro archivo que es el que
                             * mandaremos al cliente
                             */
                            System.out.println("\nSerializando nuestra lista de articulos para "
                                    + "el cliente");
                            FileOutputStream fos = new FileOutputStream("C:\\Users\\Alan\\"
                                    + "Documents\\noveno_semestre\\Redes 2\\practica1\\"
                                    + "CarritoCompra\\src\\Productos_en_servidor\\"
                                    + "lista_productos_disponibles.txt");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(listProducto);
                            oos.close();
                            fos.close();

                            /**
                             * Preparamos las variables con los datos que vamos
                             * a necesitar para mandarlas al cliente
                             */
                            File archivo_al_cliente = new File("C:\\Users\\Alan\\Documents"
                                    + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src"
                                    + "\\Productos_en_servidor\\"
                                    + "lista_productos_disponibles.txt");
                            String ruta_archivo = archivo_al_cliente.getAbsolutePath();
                            String nombre_archivo = archivo_al_cliente.getName();
                            long tamaño_archivo = archivo_al_cliente.length();

                            /**
                             * Definimos dos flujos orientados a bytes, uno para
                             * leer el archivo y otro para mandarlo por el
                             * socket
                             */
                            DataOutputStream dos_envio = new DataOutputStream(cliente.
                                    getOutputStream());
                            DataInputStream dis_envio = new DataInputStream(new 
                                    FileInputStream(ruta_archivo));

                            /**
                             * Enviamos los datos generales del archivo por el
                             * socket
                             */
                            dos_envio.writeUTF(nombre_archivo);
                            dos_envio.flush();
                            dos_envio.writeLong(tamaño_archivo);
                            dos_envio.flush();

                            /**
                             * Leemos los datos contenidos en el archivo en
                             * paquetes de 1024 bytes y lo enviamos por el
                             * socket
                             */
                            byte[] b_envio = new byte[1024];
                            long enviados = 0;
                            int n_envio;
                            while (enviados < tamaño_archivo) {
                                n_envio = dis_envio.read(b_envio);
                                dos_envio.write(b_envio, 0, n_envio);
                                dos_envio.flush();
                                enviados = enviados + n_envio;
                            }// cerramos el while

                            /**
                             * Cerramos los flujos, el socket, terminamos
                             * bloques y cerramos flujos
                             */
                            
                            System.out.println("El cliente nos ha pedido la opcion 1\nMandamos"
                                    + "el archivo " + nombre_archivo);
                            System.out.print("\n\nArchivo enviado\n");
                            dos_envio.close();
                            dis_envio.close();

                            break;
                        case 2:
                            
                            System.out.println("\nEl cliente nos ha solicitado la opcion 2\n"
                                    + "Preparando al servidor para recibir el archivo que\n"
                                    + "contiene la lista del carrito para ser procesada");
                            
                            /**
                             * Aceptamos la conexion con el cliente por el cual nos mandara 
                             * el archivo 
                             */
                            cliente = servidor.accept();
                            DataInputStream dis = new DataInputStream(cliente.getInputStream());

                            /**
                             * Leemos los datos principales del archivo y creamos un flujo para
                             * escribir el archivo de salida
                             */
                            byte[] b = new byte[1024];
                            String nombre = dis.readUTF();
                            long tam = dis.readLong();
                            DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                                    ruta_descarga + nombre));

                            /**
                             * Preparamos los datos para recibir los paquetes de datos del
                             * archivo
                             */
                            long recibidos = 0;
                            int n;

                            /**
                             * Definimos el ciclo donde estaremos recibiendo los datos mandados
                             * por el cliente
                             */
                            while (recibidos < tam) {
                                n = dis.read(b);
                                dos.write(b, 0, n);
                                dos.flush();
                                recibidos = recibidos + n;
                            }// while
                            
                            System.out.println("\nArchivo " + nombre + " descargado");
                            FileInputStream fis = new FileInputStream("C:\\Users\\Alan\\"
                                + "Documents\\noveno_semestre\\Redes 2\\practica1\\"
                                + "CarritoCompra\\src\\Productos_en_servidor\\"
                                + "lista_productos_carrito.txt");
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            CarritoCompra productos_del_cliente = (CarritoCompra)ois.
                                    readObject();
                            ois.close();
                            fis.close();
                            dos.close();
                            dis.close();
                            
                            /**
                             * Declaramos un ciclo para que la lista de productos disponibles se
                             * actualize y posteriormente la regresamos al servidor
                             */
                            int id_producto_cliente;
                            int posicion_producto_disponible;
                            for (int i = 0; i < productos_del_cliente.carrito_de_Productos.
                                    size(); i++) {
                                id_producto_cliente = productos_del_cliente.carrito_de_Productos.
                                        get(i).id_ProductoCarrito;
                                posicion_producto_disponible = listProducto.
                                        buscar_indice_producto(id_producto_cliente);
                                listProducto.listaProductos.get(posicion_producto_disponible).
                                        stock_Producto = listProducto.listaProductos.get
                                        (posicion_producto_disponible).stock_Producto - 
                                        productos_del_cliente.carrito_de_Productos.get(i).
                                        cantidad_ProductoCarrito;
                            }

                            for (int i = 0; i < listProducto.listaProductos.size(); i++) {

                                //  Comprobamos si es que hay algun producto que no tenga stock
                                if (listProducto.listaProductos.get(i).stock_Producto == 0) {
                                    System.out.println("\nDesea agregar mas unidades al produto"
                                    + listProducto.listaProductos.get(i).nombre_Producto+ "\nSi "
                                    + "= 1 No = 0");
                                    int pregunta = Integer.parseInt(br.readLine());
                                    if (pregunta == 1) {
                                        System.out.println("\nCuantos elementos desea agregar");
                                        int cantidad = Integer.parseInt(br.readLine());
                                        listProducto.agregar_Stock_Producto(i, cantidad);
                                        System.out.println("El producto: " + listProducto.
                                        listaProductos.get(i).nombre_Producto + " ahora "
                                        + "tiene mas disponibilidad");
                                    } else {
                                        System.out.println("\n El producto: " + listProducto.
                                            listaProductos.get(i).nombre_Producto + " ya no "
                                            + "esta disponible");
                                        listProducto.eliminar_Producto_sin_stock(i);
                                    }
                                }
                            }// cierre del for 
                            
                            /**
                             * Mandamos de nuevo nuestra lista de productos al cliente
                             * actualizado
                             */
                            
                            cliente = servidor.accept();
                            
                            System.out.println("\nSerializando nuestra lista de articulos"
                                    + " actualizada para el cliente");
                            fos = new FileOutputStream("C:\\Users\\Alan\\"
                                    + "Documents\\noveno_semestre\\Redes 2\\practica1\\"
                                    + "CarritoCompra\\src\\Productos_en_servidor\\"
                                    + "lista_productos_disponibles.txt");
                            oos = new ObjectOutputStream(fos);
                            oos.writeObject(listProducto);
                            oos.close();
                            fos.close();
                            
                            /**
                             * Preparamos las variables con los datos que vamos
                             * a necesitar para mandarlas al cliente
                             */
                            archivo_al_cliente = new File("C:\\Users\\Alan\\Documents"
                                    + "\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src"
                                    + "\\Productos_en_servidor\\"
                                    + "lista_productos_disponibles.txt");
                            ruta_archivo = archivo_al_cliente.getAbsolutePath();
                            nombre_archivo = archivo_al_cliente.getName();
                            tamaño_archivo = archivo_al_cliente.length();

                            /**
                             * Definimos dos flujos orientados a bytes, uno para
                             * leer el archivo y otro para mandarlo por el
                             * socket
                             */
                            dos_envio = new DataOutputStream(cliente.getOutputStream());
                            dis_envio = new DataInputStream(new FileInputStream(ruta_archivo));

                            /**
                             * Enviamos los datos generales del archivo por el
                             * socket
                             */
                            dos_envio.writeUTF(nombre_archivo);
                            dos_envio.flush();
                            dos_envio.writeLong(tamaño_archivo);
                            dos_envio.flush();

                            /**
                             * Leemos los datos contenidos en el archivo en
                             * paquetes de 1024 bytes y lo enviamos por el
                             * socket
                             */
                            b_envio = new byte[1024];
                            enviados = 0;
                            n_envio = 0;
                            while (enviados < tamaño_archivo) {
                                n_envio = dis_envio.read(b_envio);
                                dos_envio.write(b_envio, 0, n_envio);
                                dos_envio.flush();
                                enviados = enviados + n_envio;
                            }// cerramos el while

                            /**
                             * Cerramos los flujos, el socket, terminamos
                             * bloques y cerramos flujos
                             */
                            
                            System.out.println("\n Mandamos el archivo " + nombre_archivo +
                                    " actualizado al cliente");
                            System.out.print("\n\nArchivo enviado\n");
                            dos_envio.close();
                            dis_envio.close();
                            break;
                            
                        default:
                            System.out.println("\n Adios");
                            accion = false;
                            break;

                    } // fin del sitch
                    
                    /**
                     * Comprobamos si es que el cliente ha mandado una opcion para que el 
                     * servidor la ejecute, si no ha mandado ninguna opcion salimos del ciclo
                     */
                    if( accion == true){
                        /**
                         * Aceptamos una nueva conexion para el br_accion
                        */
                        cliente = servidor.accept();
                    }
                    else{
                        System.out.println("\nSaliendo el bucle while");
                    }
                    
                } while (accion == true);

            } // fin del for
        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarritoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
