package CarritoCompra;

import java.io.*;
import java.net.*;

public class CarritoServidor extends Clases_del_Servidor {

    public static void main(String[] args) {
        try {
            // Creamos el arreglo de la lista de productos que vamos a mandar al cliente en el cual guardaremos los productos                   que estan disponibles 
            ListaProducto listProducto = new ListaProducto();

            // Creamos los arreglos que contendran los colores de nuestros productos y usando el constructor de Producto que se                 encuentra en la clase Clases_del_Servidor instanciamos nuestros prodductos
            String[] colores_goober_candy = new String[]{"Azul", "Amarillo"};
            Producto goobers_candy = new Producto(1, "Goober Candy", colores_goober_candy, "ImagenesProductos/Goober candy.png"              , "Cacahuate tostado cubierto de chocolate oscuro", 19.50f, 50);
            
            String[] colores_Betty_Crocker_cake = new String[]{"Rojo", "Dorado"};
            Producto betty_crocker_cake = new Producto(2, "Betty Crocker cake mix devil's food", colores_Betty_Crocker_cake ,                 "ImagenesProductos/devils-food-cake.png", "Mezcla instantánea para pastel sabor chocolate", 35.90f, 10);
            
            String[] colores_caprince_naturals = new String[]{"Rojo"};
            Producto caprince_naturals = new Producto(3, "Caprince Naturals", colores_caprince_naturals,                                     "ImagenesProductos/CapriceNaturals.png", "Shampoo con extracto de manzana" , 33.20f, 15);
            
            String[] colores_Naturalmilk = new String[]{"Blanco", "Azul"};
            Producto Naturalmilk = new Producto(4, "Naturalmilk", colores_Naturalmilk, "ImagenesProductos/Naturalmilk.png",                  "Crema corporal antioxidante con extracto de argan y frutos rojos", 19.90f, 30);
            
            String[] colores_Pinguinos_fresa = new String[]{"Rojo", "Rosa"};
            Producto Pinguinos_fresa = new Producto(5, "Pinguinos fresa", colores_Pinguinos_fresa,                                           "ImagenesProductos/Pinguinos_fresa.jpg", "Panque de chocolate con relleno cremoso sabor fresa y ganache de"
            + "chocolate", 15.0f, 30);

            // Agregamos los productos a la lista de productos creada con anteriodidad 
            listProducto.agregarListaProducto(goobers_candy);
            listProducto.agregarListaProducto(betty_crocker_cake);
            listProducto.agregarListaProducto(caprince_naturals);
            listProducto.agregarListaProducto(Naturalmilk);
            listProducto.agregarListaProducto(Pinguinos_fresa);
            
            // Inicamos el servidor usando la clase ServerSocket y lo iniciamos en el puerto 3070
            ServerSocket servidor = new ServerSocket(3070);
            servidor.setReuseAddress(true);
            System.out.println("\nEsperando conexion con el cliente...");
            
            // Declaramos un for infinito para que el servidor siempre este en espera de un cliente
             for(;;){
                
                //Aceptamos la concexion con el cliente he imprimimos los datos desde donde nos esta visitando
                Socket cliente = servidor.accept();
                System.out.println("\nEstablecimos conección con el cliente\nDireccion del cliente: " + cliente.getInetAddress()                + " en el Puerto: " + cliente.getPort());
                
                // Comprobamos que ningun elemnto de nuestra lista de productos este sin stock y si lo esta decidimos si                           agregamos mas elementos al stock o solo lo eliminamos 
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                for (int i = 0; listProducto.listaProductos.size() < 10; i++) {
                    if (listProducto.listaProductos.get(i).stock_Producto == 0) {
                        System.out.println("\nDesea agregar mas unidades al produto: " + listProducto.listaProductos.get(i).
                                nombre_Producto + " Si = 1 No = 0");
                        int pregunta = Integer.parseInt(br.readLine());
                        if(pregunta == 1){
                            System.out.println("\nCuantos elementos desea agregar: ");
                            int cantidad = Integer.parseInt(br.readLine());
                            listProducto.agregar_Stock_Producto(i, cantidad);
                            System.out.println("El producto: " + listProducto.listaProductos.get(i).nombre_Producto
                            + " ahora tiene mas disponibilidad");
                        }
                        else{
                            System.out.println("\n El producto: " + listProducto.listaProductos.get(i).nombre_Producto
                            + " ya no esta disponible");
                            listProducto.eliminar_Producto_sin_stock(i);
                        }
                    }
                }
                
                // Serializamos el objeto listProducto que le mandaremos al cliente y lo guardaremos en la ruta indicada
                FileOutputStream fos = new FileOutputStream("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1"
                + "\\CarritoCompra\\src\\Productos_en_servidor\\lista_productos.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(listProducto);
                oos.close();
                fos.close();
                
                // Procedemos a mandar el objeto ya serializado a nuestro cliente
                // Abrinmos un flujo de datos de tipo file para escribir los datos del txt que sera enviado al cliente
                File doc_serializado = new File("C:\\Users\\Alan\\Documents\\noveno_semestre\\Redes 2\\practica1"
                + "\\CarritoCompra\\src\\Productos_en_servidor\\lista_productos.txt");
                
                //Guardammos la ruta del documento serializado, el nombre y el tamaño del mismo
                String ruta_documento = doc_serializado.getAbsolutePath();
                String nombre_documento = doc_serializado.getName();
                long tam_documento = doc_serializado.length();
                
                // abrimos dos flujos, uno de ellos nos ayudara a escribir el archivo en un flujo de bytes y el segundo lo                         usaremos para mandar datos al cliente
                DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                DataInputStream dis = new DataInputStream(new FileInputStream(ruta_documento));
                
                // Escribimos los datos generales del archivo que mandaremos por el socket
                dos.writeUTF(nombre_documento);
                dos.flush();
                dos.writeLong(tam_documento);
                dos.flush();
                
                // escribimos los datos del socket en paquetes de 1024 bytes y se encvian por el socket
                byte[] b = new byte[1024];
                long enviados = 0;
                int n;
                while(enviados < tam_documento){
                    n = dis.read(b);
                    dos.write(b, 0, n);
                    dos.flush();
                    enviados = enviados + n;                 
                }
                if(enviados == tam_documento){
                    System.out.println("\nEl docuemnto " + nombre_documento + " ha sido enviado con exito");
                }
              
                // cerramos los flijos de salida
                dos.close();
                dis.close();
                
                
                
                
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
