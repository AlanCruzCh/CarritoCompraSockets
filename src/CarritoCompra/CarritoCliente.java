package CarritoCompra;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarritoCliente extends Clases_del_Cliente {

    public static void main(String[] args) {

        try {

            // Declaramos la ruta donde se gurradara el archivo que vamos a descargar del cliente
            File f = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1\\CarritoCompra\\src");
            String ruta = f.getAbsolutePath();
            String carpeta = "Productos_en_cliente";
            String ruta_descarga = ruta + "\\" + carpeta + "\\";
            System.out.println("ruta: " + ruta_descarga);
            File f2 = new  File(ruta_descarga);
            f2.mkdirs();
            f2.setWritable(true);

            // Declaramos el buffer de entrada ppara guardar los datos del servidor al que nos queremos conectar
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nEscriba la direccion del servidor: ");
            String host = br.readLine();
            System.out.println("\nEscriba el puerto del servidor: ");
            int puerto = Integer.parseInt(br.readLine());

            // Inicializamos el cliente y la conexion con el servidor
            Socket cliente = new Socket(host, puerto);
            System.out.println("\nConexion establecida con el servidor");

            DataInputStream dis = new DataInputStream(cliente.getInputStream());
            String nombre_archivo = dis.readUTF();
            long tam_archivo = dis.readLong();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream( ruta_descarga + nombre_archivo));
            long recibidos = 0;
            int n = 0;
            byte[] b = new byte[1024];
            while (recibidos < tam_archivo) {
                n = dis.read(b);
                dos.write(b, 0, n);
                dos.flush();
                recibidos = recibidos + n;
            }
            if (recibidos == tam_archivo) {
                System.out.println("Descarga completa al 100%");
            }
            dos.close();
            dis.close();
            
            // Deserializamos la lista de productos que nos mando el servidor  
            FileInputStream fis = new FileInputStream("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1\\"
                    + "CarritoCompra\\src\\Productos_en_cliente\\lista_productos.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ListaProducto lista_productos_disponibles = (ListaProducto) ois.readObject();
            ois.close();
            fis.close();
            
            // Hacemos el menu con el cual el usuario interactuara
            lista_productos_disponibles.mostrar_Productos();
            int seguir_comprando = 1;
            
            // Inicializamos nuestra lista de productos que contendra nuestro carrito de compra
            CarritoCompra lista_del_carrito = new CarritoCompra();
            
            // Hacemos un ciclo para que la accion de comprar se repita hasta que el usuario tenga todos sus productos que desea
            while (seguir_comprando == 1) {                
                System.out.println("\n*****************************************************");
                System.out.println("\nElija una opción para hacer en el menu"
                        + "\nAgregar producto al carrito = 1 \nConsultar carrito = 2"
                        + "\nEliminar producto del carrito = 3 \nComprar productos del carrito = 4");
                int opcion_menu = Integer.parseInt(br.readLine());
                switch(opcion_menu){
                    
                    // Opcion que nos permite agregar un producto al carrito de compra
                    case 1:
                        System.out.println("\nIngrese el id del producto: ");
                        int id_producto = Integer.parseInt(br.readLine());
                        int posicion_producto_deseado = lista_productos_disponibles.buscar_indice_producto(id_producto);
                        
                        // Corroboramos que el producto este disponible para agregarse al producto
                        if(posicion_producto_deseado != -1){
                            lista_productos_disponibles.mostrarProductoEspecifico(posicion_producto_deseado);
                            System.out.println("\nEL siguiente producto se añadira a su carrito de compra\n Confirmar = 1"
                                    + "Cancelar = 0");
                            int confirmacion = Integer.parseInt(br.readLine());
                            
                            // Corroboramos que ese sea el producto que esta queriendo agregar al carrito
                            if (confirmacion == 1) {
                                
                                // Solicitamos los datos que faltan para que se pueda crear el producto que ira al carrito
                                System.out.println("\nDe que color seria su producto");
                                String color = br.readLine();
                                System.out.println("\nElija la cantidad del producto que desea");
                                int cantidad = Integer.parseInt(br.readLine());        
                                
                                // Corroboramos si es que el producto es nuevo o ya existe en nuestro carrito de compras
                                int posicion_prodcuto_en_carrito = lista_del_carrito.buscar_indice_carrito(id_producto);
                                if(posicion_prodcuto_en_carrito == -1){
                                    
                                    // Producto nuevo
                                    do {
                                        
                                        // corroboramos que la cantidad deseada sea menor que el stock disponible
                                        if(lista_productos_disponibles.listaProductos.get(posicion_producto_deseado).
                                                stock_Producto >= cantidad){
                                            
                                            // Creamos nuestro constructor del carrito de compra 
                                            Producto_del_Carrito nuevoProducto = new Producto_del_Carrito(id_producto, 
                                            lista_productos_disponibles.listaProductos.get(posicion_producto_deseado).
                                            nombre_Producto, color, cantidad, 
                                            lista_productos_disponibles.listaProductos.get(posicion_producto_deseado).
                                            precio_Producto, cantidad * lista_productos_disponibles.listaProductos.get
                                            (posicion_producto_deseado).precio_Producto);
                                            
                                            // Agregamos el producto al carrito
                                            lista_del_carrito.agregarProductoCarrito(nuevoProducto);
                                            nuevoProducto.mostrar_Producto_en_carrito();
                                            System.out.println("\nNUEVO PRODUCTO AÑADIDO AL CARRITO");
                                        }
                                        
                                        // si la cantidad es mayor al stock le pedimos al usuario que meta una cantidad correcta                                           y volvemos a comprobar que la cantidad sea menor
                                        else{
                                            System.out.println("\nLa cantidad que desea del producto supera la cantidad "
                                                + "disponible del producto");
                                            System.out.println("\nElija la cantidad del producto que desea");
                                            int newcantidad = Integer.parseInt(br.readLine());
                                            cantidad = newcantidad;
                                        }
                                        
                                    // el ciclo que nos ayudara a que se compruebe nuevamente que la cantidad es correcta
                                    } while (cantidad > lista_productos_disponibles.listaProductos.get
                                        (posicion_producto_deseado).stock_Producto);
                                }else{
                                    
                                    // El producto ya existe
                                    int cantidad_mas_producto = lista_del_carrito.carrito_de_Productos.
                                    get(posicion_prodcuto_en_carrito).cantidad_ProductoCarrito + cantidad;
                                    
                                    //Comprobamos que la cantidad no pase del stock diponible
                                    do{
                                        if(lista_productos_disponibles.listaProductos.get(posicion_producto_deseado).
                                            stock_Producto >= cantidad_mas_producto){
                                            
                                            // Creamos nuestro constructor del carrito de compra 
                                            Producto_del_Carrito nuevoProducto = new Producto_del_Carrito(id_producto, 
                                            lista_productos_disponibles.listaProductos.get(posicion_producto_deseado).
                                            nombre_Producto, color, cantidad, 
                                            lista_productos_disponibles.listaProductos.get(posicion_producto_deseado).
                                            precio_Producto, cantidad * lista_productos_disponibles.listaProductos.get
                                            (posicion_producto_deseado).precio_Producto);
                                            
                                            // Agregamos el producto al carrito
                                            lista_del_carrito.agregarProductoCarrito(nuevoProducto);
                                            nuevoProducto.mostrar_Producto_en_carrito();
                                            System.out.println("\nNUEVO PRODUCTO AÑADIDO AL CARRITO");
                                        }
                                        
                                        // si la cantidad es mayor al stock le pedimos al usuario que meta una cantidad correcta                                           y volvemos a comprobar que la cantidad sea menor
                                        else{
                                            System.out.println("\nLa cantidad que desea del producto supera la cantidad "
                                                + "disponible del producto");
                                            System.out.println("\nCantidad disponible: " + (lista_productos_disponibles.
                                            listaProductos.get(posicion_producto_deseado).stock_Producto - lista_del_carrito.
                                            carrito_de_Productos.get(posicion_prodcuto_en_carrito).cantidad_ProductoCarrito));
                                            System.out.println("\nElija la cantidad del producto que desea");
                                            int newcantidad = Integer.parseInt(br.readLine());
                                            cantidad = newcantidad;
                                        }
                                        
                                    // el ciclo que nos ayudara a que se compruebe nuevamente que la cantidad es correcta
                                    } while (cantidad_mas_producto > lista_productos_disponibles.listaProductos.get
                                        (posicion_producto_deseado).stock_Producto);
                                }
                            } else {
                                System.out.println("Accion de agregar producto al carrito cancelada");
                            }
                        }
                        else{
                            System.out.println("\nEl producto que usted desea no se encuentra disponible");
                        }
                        break;
                    case 2:
                        
                        // Consultamos nuestro carrito de compra
                        System.out.println("\nConsultando el carrito");
                        lista_del_carrito.mostrarCarritoProductos();
                        break;
                        
                    case 3:
                        
                        // eliminamos el producto con el id del indice que deseamos eliminar 
                        System.out.println("\nIngre el id del producto que desea eliminar");
                        int id_eliminar = Integer.parseInt(br.readLine());
                        int posicion_en_carrito = lista_del_carrito.buscar_indice_carrito(id_eliminar);
                        
                        // comprobamos que el id del producto que vamos eliminar este en nuestro carrito
                        if (posicion_en_carrito != 1) {
                            lista_del_carrito.eliminarProductoCarrito(posicion_en_carrito, id_eliminar);
                        } else {
                            System.out.println("\nEl id que proporciono no es correcto\nOperacion cancelada");
                        }
                        break;
                        
                    default:
                        
                        // COMPRA DE PRODUCTOS DEL CARRITO
                        break;
                }
                
                
            }
                
            
            cliente.close();
        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarritoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
