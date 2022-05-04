package CarritoCompra;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarritoCliente extends Clases_del_Cliente {

    public static void main(String[] args) {

        /**
         * Declaramos la ruta donde se gurradara el archivo que vamos a
         * descargar del cliente
         */
        File f = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1"
                + "\\CarritoCompra\\src");
        String ruta = f.getAbsolutePath();
        String carpeta = "Productos_en_cliente";
        String ruta_descarga = ruta + "\\" + carpeta + "\\";
        System.out.println("ruta: " + ruta_descarga);
        File f2 = new File(ruta_descarga);
        f2.mkdirs();
        f2.setWritable(true);

        try {
            /**
             * Declaramos el buffer de entrada para obtener datos que le
             * solicitaremos al usuario
             */
            BufferedReader datos_usuario = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nIngrese la direccion del servidor");
            String direccion_ip = datos_usuario.readLine();
            System.out.println("\nIngrese el puerto");
            int puerto = Integer.parseInt(datos_usuario.readLine());

            /**
             * Instanciamos nuestro socket cliente con los datos del servidor
             */
            Socket cliente = new Socket(direccion_ip, puerto);
            System.out.println("\nConexion establecida con el servidor");

            /**
             * Solicitamos que el cliente nos mande los datos de nuestro carrito
             * de compra bandera = 1 si solicitamos la lista de productos
             * disponibles bandera = 2 si mandamos los datos de lo que vamos a
             * comprar bandera = 3 si solicitamos el pdf de nuestros articulos
             */
            int bandera = 1;
            PrintWriter solicitud = new PrintWriter(new OutputStreamWriter(
                    cliente.getOutputStream()));
            solicitud.println(bandera);
            solicitud.flush();
            solicitud.close();
            cliente.close();

            /**
             * Abrimos otra instancia de tipo socket para la descarga del
             * archivo que nos manda el servidor para poder ser tratado
             */
            cliente = new Socket(direccion_ip, puerto);
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

            FileInputStream fis = new FileInputStream("C:\\Users\\Alan\\Documents\\"
                    + "noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src\\"
                    + "Productos_en_cliente\\lista_productos_disponibles.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ListaProducto productos_disponibles = (ListaProducto) ois.readObject();
            ois.close();
            fis.close();
            dos.close();
            dis.close();
            cliente.close();

            int seguir_comprando = 1;
            productos_disponibles.mostrar_Productos();
            /**
             * Inicializamos nuestra lista de productos que contendra nuestro
             * carrito de compra
             */
            CarritoCompra lista_del_carrito = new CarritoCompra();

            /**
             * Hacemos un ciclo para que la accion de comprar se repita hasta
             * que el usuario tenga todos sus productos que desea
             */
            while (seguir_comprando == 1) {
                System.out.println("\n*****************************************************");
                System.out.println("\nElija una opción para hacer en el menu"
                        + "\nAgregar producto al carrito = 1 \nConsultar carrito = 2"
                        + "\nEliminar producto del carrito = 3 \nComprar productos del carrito "
                        + "= 4");
                int opcion_menu = Integer.parseInt(datos_usuario.readLine());
                switch (opcion_menu) {

                    /**
                     * Opcion que nos permite agregar un producto al carrito de
                     * compra
                     */
                    case 1:
                        System.out.println("\nIngrese el id del producto: ");
                        int id_producto = Integer.parseInt(datos_usuario.readLine());
                        int posicion_producto_deseado = productos_disponibles.
                                buscar_indice_producto(id_producto);

                        /**
                         * Corroboramos que el producto este disponible para
                         * agregarse al producto
                         */
                        if (posicion_producto_deseado != -1) {
                            productos_disponibles.mostrarProductoEspecifico(posicion_producto_deseado);
                            System.out.println("\nEL siguiente producto se añadira a su carrito"
                                    + " de compra\n Confirmar = 1\nCancelar = 0");
                            int confirmacion = Integer.parseInt(datos_usuario.readLine());

                            /**
                             * Corroboramos que ese sea el producto que esta
                             * queriendo agregar al carrito
                             */
                            if (confirmacion == 1) {

                                /**
                                 * Solicitamos los datos que faltan para que se
                                 * pueda crear el producto que ira al carrito
                                 */
                                System.out.println("\nDe que color seria su producto");
                                String color = datos_usuario.readLine();
                                System.out.println("\nElija la cantidad del producto que desea");
                                int cantidad = Integer.parseInt(datos_usuario.readLine());

                                /**
                                 * Corroboramos si es que el producto es nuevo o
                                 * ya existe en nuestro carrito de compras
                                 */
                                int posicion_prodcuto_en_carrito = lista_del_carrito.
                                        buscar_indice_carrito(id_producto);
                                if (posicion_prodcuto_en_carrito == -1) {

                                    /**
                                     * Producto nuevo
                                     */
                                    do {

                                        /**
                                         * corroboramos que la cantidad deseada
                                         * sea menor que el stock disponible
                                         */
                                        if (productos_disponibles.listaProductos.get(posicion_producto_deseado).stock_Producto
                                                >= cantidad) {

                                            /**
                                             * Creamos nuestro constructor del
                                             * carrito de compra
                                             */
                                            Producto_del_Carrito nuevoProducto = new Producto_del_Carrito(id_producto,
                                                    productos_disponibles.listaProductos.get(posicion_producto_deseado).nombre_Producto,
                                                    color, cantidad, productos_disponibles.listaProductos.get(posicion_producto_deseado).precio_Producto,
                                                    (cantidad * productos_disponibles.listaProductos.get(posicion_producto_deseado).precio_Producto));
                                            /**
                                             * Agregamos el producto al carrito
                                             */
                                            lista_del_carrito.agregarProductoCarrito(nuevoProducto);
                                            nuevoProducto.mostrar_Producto_en_carrito();
                                            System.out.println("\nNUEVO PRODUCTO AÑADIDO AL "
                                                    + "CARRITO");
                                        } /**
                                         * si la cantidad es mayor al stock le
                                         * pedimos al usuario que meta una
                                         * cantidad correcta y volvemos a
                                         * comprobar que la cantidad sea menor
                                         */
                                        else {
                                            System.out.println("\nLa cantidad que desea del "
                                                    + "producto supera la cantidad disponible "
                                                    + "del producto");
                                            System.out.println("\nElija la cantidad del producto"
                                                    + " que desea");
                                            int newcantidad = Integer.parseInt(datos_usuario.
                                                    readLine());
                                            cantidad = newcantidad;
                                        }

                                        /**
                                         * el ciclo que nos ayudara a que se
                                         * compruebe nuevamente que la cantidad
                                         * es correcta
                                         */
                                    } while (cantidad > productos_disponibles.listaProductos.get(posicion_producto_deseado).stock_Producto);
                                } else {

                                    /**
                                     * El producto ya existe
                                     */
                                    int cantidad_mas_producto = lista_del_carrito.carrito_de_Productos.get(posicion_prodcuto_en_carrito).cantidad_ProductoCarrito + cantidad;

                                    /**
                                     * Comprobamos que la cantidad no pase del
                                     * stock diponible
                                     */
                                    do {
                                        if (productos_disponibles.listaProductos.get(posicion_producto_deseado).stock_Producto
                                                >= cantidad_mas_producto) {

                                            /**
                                             * Creamos nuestro constructor del
                                             * carrito de compra
                                             */
                                            Producto_del_Carrito nuevoProducto = new Producto_del_Carrito(id_producto,
                                                    productos_disponibles.listaProductos.get(posicion_producto_deseado).nombre_Producto,
                                                    color, cantidad, productos_disponibles.listaProductos.get(posicion_producto_deseado).precio_Producto,
                                                    (cantidad * productos_disponibles.listaProductos.get(posicion_producto_deseado).precio_Producto));

                                            /**
                                             * Agregamos el producto al carrito
                                             */
                                            lista_del_carrito.agregarProductoCarrito(nuevoProducto);
                                            nuevoProducto.mostrar_Producto_en_carrito();
                                            System.out.println("\nNUEVO PRODUCTO AÑADIDO AL "
                                                    + "CARRITO");
                                        } /**
                                         * si la cantidad es mayor al stock le
                                         * pedimos al usuario que meta una
                                         * cantidad correcta y volvemos a
                                         * comprobar que la cantidad sea menor
                                         */
                                        else {
                                            System.out.println("\nLa cantidad que desea del"
                                                    + " producto supera la cantidad "
                                                    + "disponible del producto");
                                            System.out.println("\nCantidad disponible: "
                                                    + (productos_disponibles.listaProductos.get(posicion_producto_deseado).stock_Producto
                                                    - lista_del_carrito.carrito_de_Productos.get(posicion_prodcuto_en_carrito).cantidad_ProductoCarrito));
                                            System.out.println("\nElija la cantidad del "
                                                    + "producto que desea");
                                            int newcantidad = Integer.parseInt(datos_usuario.
                                                    readLine());
                                            cantidad = newcantidad;
                                        }

                                        /**
                                         * el ciclo que nos ayudara a que se
                                         * compruebe nuevamente que la cantidad
                                         * es correcta
                                         */
                                    } while (cantidad_mas_producto > productos_disponibles.listaProductos.get(posicion_producto_deseado).stock_Producto);
                                }
                            } else {
                                System.out.println("Accion de agregar producto al carrito"
                                        + " cancelada");
                            }
                        } else {
                            System.out.println("\nEl producto que usted desea no se encuentra"
                                    + " disponible");
                        }
                        break;
                    case 2:

                        /**
                         * Consultamos nuestro carrito de compra
                         */
                        System.out.println("\nConsultando el carrito");
                        lista_del_carrito.mostrarCarritoProductos();
                        break;

                    case 3:

                        /**
                         * eliminamos el producto con el id del indice que
                         * deseamos eliminar
                         */
                        System.out.println("\nIngre el id del producto que desea eliminar");
                        int id_eliminar = Integer.parseInt(datos_usuario.readLine());
                        int posicion_en_carrito = lista_del_carrito.buscar_indice_carrito(id_eliminar);

                        /**
                         * comprobamos que el id del producto que vamos eliminar
                         * este en nuestro carrito
                         */
                        if (posicion_en_carrito != -1) {
                            lista_del_carrito.eliminarProductoCarrito(posicion_en_carrito,
                                    id_eliminar);
                        } else {
                            System.out.println("\nEl id que proporciono no es correcto\n"
                                    + "Operacion cancelada");
                        }
                        break;

                    case 4:

                        /**
                         * Opcion para mandar los archivos al carrito
                         */
                        cliente = new Socket(direccion_ip, puerto);
                        bandera = 2;
                        solicitud = new PrintWriter(new OutputStreamWriter(
                                cliente.getOutputStream()));
                        solicitud.println(bandera);
                        solicitud.flush();
                        solicitud.close();
                        cliente.close();

                        /**
                         * Serializamos el archivo que vamos a mandar al
                         * servidor
                         */
                        System.out.println("\nSerializando nuestra lista de articulos para "
                                + "el servidor");
                        FileOutputStream fos = new FileOutputStream("C:\\Users\\Alan\\"
                                + "Documents\\noveno_semestre\\Redes 2\\practica1\\"
                                + "CarritoCompra\\src\\Productos_en_cliente\\"
                                + "lista_productos_carrito.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(lista_del_carrito);
                        oos.close();
                        fos.close();

                        cliente = new Socket(direccion_ip, puerto);
                        /**
                         * Preparamos las variables con los datos que vamos a
                         * necesitar para mandarlas al cliente
                         */
                        File archivo_al_cliente = new File("C:\\Users\\Alan\\"
                                + "Documents\\noveno_semestre\\Redes 2\\practica1\\"
                                + "CarritoCompra\\src\\Productos_en_cliente\\"
                                + "lista_productos_carrito.txt");
                        String ruta_archivo = archivo_al_cliente.getAbsolutePath();
                        String nombre_archivo = archivo_al_cliente.getName();
                        long tamaño_archivo = archivo_al_cliente.length();

                        /**
                         * Definimos dos flujos orientados a bytes, uno para
                         * leer el archivo y otro para mandarlo por el socket
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
                         * Leemos los datos contenidos en el archivo en paquetes
                         * de 1024 bytes y lo enviamos por el socket
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
                         * Cerramos los flujos, el socket, terminamos bloques y
                         * cerramos flujos
                         */
                        System.out.print("\n\nArchivo enviado\n");
                        dos_envio.close();
                        dis_envio.close();
                        cliente.close();
                        break;

                    default:
                        seguir_comprando = 0;
                        System.out.println("\nAdios");
                        // COMPRA DE PRODUCTOS DEL CARRITO
                        break;
                }

            }

        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarritoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
